/*
 * TCES360 Conference Management System
 * Warrick Holfeld
 * Jacob Hall
 * Aarron Merril
 * Tyler Powers
 * Seth Kramer
 * David Swanson
 * 11/10/13
 */

package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import view.GUIEnum;
import view.GUIEnum.StateOfGUI;
import view.GUIEnum.paperRelation;
import view.GUIEnum.paperStatusAdminViewable;
import view.GUIEnum.paperStatusAuthorViewable;


/**
 * The Controller handle all business logic and act as an interface between the GUI
 * and the database.
 * 
 * @author Jacob Hall
 * @author Seth Kramer
 * @author Tyler Powers
 * @author Aaron Merril
 * @author David Swanson
 * @version 90 Date: 11/27/13
 */
public class Controller extends Observable{
	
	/** 
	 * The maximum number of papers a user in the system is allowed to
	 * submit to a conference.
	 */
	public static final int MAX_NUMBER_USER_SUBMITTED_PAPERS = 4;
	
	/** 
	 * The maximum number of reviews a paper can have in the system.
	 */
	public static final int MAX_NUMBER_OF_REVIEWS = 4;
	
	/**
	 * The connection the controller has to the Database.
	 * This connection is set up during construction.
	 */
	private Connection connect = null;
	
	/**
	 * The resultSet is what is returned from the database
	 * after the statement has been sent.
	 */
	private ResultSet resultSet = null;

	/**
	 * The current state (mode of operation) the GUI is currently in.
	 * @see StateOfGUI
	 */
	private StateOfGUI state;
	
	/**
	 * The current user logged in.
	 */
	private String current_user = "";
	
	/**
	 * The current conference (if any) that is being evaluated by the user 
	 */
	private Conference current_conference;
	
	/**
	 * The current paper (if any) that is being evaluated by the user
	 */
	private static String current_paper = "";
	
	/**
	 * A list of all conferences
	 */
	List<Conference> listOfAllConferences;
	
	
	/**
	 * Constructor for the Controller.  State initially set to LOGIN.
	 * Sets up the connection to the Derby Database on port 1527.
	 * If the Controller can't connect to the database, it prints
	 * to the console that it failed to connect.
	 * 
	 * <dt><b>Preconditions: The Derby Network has been started.</b>
	 * <dd>
	 * <dt><b>Postconditions: A Controller object is created.</b>
	 * <dd>
	 */
	public Controller() {
		state = StateOfGUI.LOGIN;

		try {		      
		      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();	      	
		      connect = DriverManager.getConnection("jdbc:derby://localhost:1527/CMSDB;");
		      
		} catch(Exception e) {
			System.out.println("Failed to connect!");
		} 
	}
	
	
	/**
	 * Set the next state the GUI should transition to.  Note: setting this to a new state
	 * causes the Observable Object Controller to change states and notify Observers.
	 * 
	 * @param the_state An enumeration of the GUI which will be set as the current state.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and a valid enum
	 * 							has been passed in.</b>
	 * <dd>
	 * <dt><b>Postconditions: State is set to the passed in state.</b>
	 * <dd>
	 */
	public void setStateOfGUI(StateOfGUI the_state){	
		if (the_state != state) {
			state = the_state;	
			setChanged();
			notifyObservers();
		}	
	}
	
	/**
	 * Gets the next state the GUI should transition to.
	 * 
	 * @return The updated current state.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: StateOfGUI enum returned.</b>
	 * <dd>
	 */
	public StateOfGUI getStateOfGUI(){
		return state;
	}

	/**
	 * Checks the username in the database and returns true if it is the valid 
	 * username of someone already in the system.
	 * 
	 * @param the_username The username to be check in the database.
	 * @return Returns true if the username exists in the database.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns boolean whether the username exists in the Database.</b>
	 * <dd>
	 */	
	public Boolean checkValidUsername(final String the_username) {
		Boolean valid = false;		
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM users WHERE username='" + the_username + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				valid = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
		return valid;
	}
	
	
	/**
	 * Checks the username and password combination against the database.
	 * 
	 * @param the_username The username to check in the database.
	 * @param the_password The corresponding password to check in the database.
	 * @return Returns true if the username/password combo are verified, false otherwise.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns boolean whether the username Password
	 * 							pair are in the Database.</b>
	 * <dd>
	 */
	public Boolean checkValidUsernamePassword(final String the_username, 
												final String the_password){
		Boolean valid = false;		
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM users WHERE username='" + the_username + "' AND password='" + the_password +
					"'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				valid = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
		return valid;
	}
	
	/**
	 * Adds a new user to the database. GUI must first use checkValidUsername() 
	 * to ensure no duplicates.
	 * 
	 * @param the_username The login name for the user.
	 * @param the_password The password that the username will use for login.
	 * @param the_first_name The user's first name.
	 * @param the_middle_name The user's middle initial.
	 * @param the_last_name The user's last name.
	 * @param the_specialty The user's engineering specialty (String from GUI).
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and 
	 * 							checkValidUsername() has been called first and
	 * 							the name doesn't already exist in the system.</b>
	 * <dd>
	 * <dt><b>Postconditions: The new user is added to the database.</b>
	 * <dd>
	 */
	public void addNewUser(final String the_username, final String the_password, 
							final String the_first_name, final String the_middle_name, 
							final String the_last_name, final String the_specialty)
	{
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO users VALUES ('" + the_username + "', '" + the_first_name + "', '" +
					the_middle_name + "', '" + the_last_name + "', '" + the_specialty + "', '" + the_password + "')");
			statement.execute();
			System.out.println(the_username + " Successfully added user");
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the current user to the username of the person currently logged in.
	 * 
	 * @param the_username The username of the current person logged in.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Current user is set to the passed in username.</b>
	 * <dd>
	 */
	public void setCurrentUsername(final String the_username){
		current_user = the_username;
	}
	
	/**
	 * Returns the username of the current person logged in.
	 * 
	 * @return Returns the current user.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String of the current user.</b>
	 * <dd>
	 */
	public String getCurrentUsername(){
		return current_user;
	}
	
	/**
	 * Returns the full name of the individual when the username is passed in.
	 * 
	 * @param the_username The username of the individual being queried.
	 * @return The full name of the person.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the
	 * 							passed in username exists in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String representation of the users full name.</b>
	 * <dd>
	 */
	public String getFullName(final String the_username){
		
		String result = "";
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM users WHERE username=" +"'" + the_username +"'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				result += resultSet.getString(2) + " ";
				result += resultSet.getString(3) + " ";
				result += resultSet.getString(4);
			}
			
		} catch (Exception e) {
			System.out.println("Get full name failed!");
		}
		
		return result;
	}
	
	/*
	 * @author David
	 */
	/**
	 * Checks to see if the conference currently exists 
	 * in the database.
	 * 
	 * @param the_conference_title The title of the conference.
	 * @return Returns true if the conference title exists in database
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a boolean of whether the conference exists.</b>
	 * <dd>
	 */
	public Boolean checkConferenceExists(final String the_conference_title) {
		Boolean valid = false;

		try {
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM conferences WHERE name='" + the_conference_title + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				valid = true;
			}
		} catch (SQLException e) {
			System.out.println("Check for valid conference name failed");
			e.printStackTrace();
		}
		return valid;
	}
	
	/*
	 * @author David 
	 */
	/**
	 * Creates a new conference. The GUI code must first checkConferenceExists() 
	 * so duplicate conferences don't get entered into the database.
	 * 
	 * @param the_conference The conference object to be entered into the database.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and checkConferenceExists()
	 * 							has been called to ensure there are no duplicate conferences.</b>
	 * <dd>
	 * <dt><b>Postconditions: Conference added to the Database.</b>
	 * <dd>
	 */
	public void createNewConference(final Conference the_conference){
		listOfAllConferences = new ArrayList<Conference>();
		
		//TODO: I changed the fields in the NewConferenceGUI to be date objects.  I'm passing those in for dates now.  
		//		SQLDataException is still occuring because of the format of the Date Object.  I'm not sure how you want
		//		to handle it, but you should be able to format the date object now and pull out what you need. (Jacob)
		try {
			@SuppressWarnings("deprecation")
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO conferences VALUES ('" + the_conference.getConfTitle() + "', '" +
					the_conference.getProgramChair() + "', '" + the_conference.getConfDate().toLocaleString() +
					"', '" + the_conference.getSubmissionDead().toLocaleString() + "', '" +
					the_conference.getReviewDead().toLocaleString() + "', '" +
					the_conference.getSubPCReccomendDead().toLocaleString() + "', '" +
					the_conference.getAuthorNotificationDead().toLocaleString() + "', '" +
					the_conference.getConfSummary() + "')");
			statement.execute();
			System.out.println(the_conference.getConfTitle() + " Successfully added conference");
			listOfAllConferences.add(the_conference);
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
		current_conference = the_conference;
	}
	
	/**
	 * Sets the current conference to the conference of the person currently being looked at by the user.
	 * 
	 * @param the_conference_name The conference to set as the current conference.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Sets the current conference to the Conference passed in.</b>
	 * <dd>
	 */
	public void setCurrentConference(final Conference the_conference_name){
		current_conference = the_conference_name;
	}
	
	/**
	 * Returns the conference that is being looked at by the user.
	 * 
	 * @return Returns the current conference.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns the current conference object.</b>
	 * <dd>
	 */
	public Conference getCurrentConference(){
		return current_conference;
	}
	
	
	/*
	 *  @author David
	 */
	/**
	 * This method adds a new paper to database as long as MAX_NUMBER_USER_SUBMITTED_PAPERS
	 * hasn't been reached. If it has, this method throws an exception that needs to 
	 * be caught by the calling function.
	 * 
	 * @param the_conference The conference that the paper is being added to.
	 * @param the_username The username of the author.
	 * @param the_paper_title The title of the paper.
	 * @param the_file_submitted The path of the file being submitted.
	 * @param the_user_viewable_status The paper status that's viewable for an author's purposes. 
	 * @param the_admin_viewable_status The paper status that's viewable for administrative purposes. 
	 * @throws Exception Throws exception if MAX_NUMBER_USER_SUBMITTED_PAPERS reached.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the paper doesn't already
	 * 							exist in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Either the paper is added to the Database, or an exception
	 * 							is thrown meaning the paper wasn't added due to too many
	 * 							papers being submitted to the passed in conference by the
	 * 							passed in user.</b>
	 * <dd>
	 */
	public void createNewPaper(final Conference the_conference, final String the_username, final String the_paper_title, 
			final String the_file_submitted, paperStatusAuthorViewable the_user_viewable_status, 
			paperStatusAdminViewable the_admin_viewable_status) throws Exception{
		//TODO: add the paper and info related to it to the database.  Note:  this is called in the GUI in a try-catch statement
		//		Return Exception "Author cannot submit more than 4 papers to a single conference." 
		//		implement the Logic to prevent that here.  The GUI will print out the Exception string in a JDialog box.
		int total = 0;
		int i = 0;
		try {
			
			PreparedStatement statement = connect.prepareStatement("SELECT id FROM papers ORDER BY id ASC");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				if (!(resultSet.getInt(1) == total)) {
					break;
				} else {
					total+=1;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE author='" + the_username + "' AND confname ='"+the_conference.getConfTitle()+"'");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				i+=1;
			}
			
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
		
		// Checks to see if the maximum number of papers submitted has been reached.
		if(i < MAX_NUMBER_USER_SUBMITTED_PAPERS) {
			try {			
				PreparedStatement statement = connect.prepareStatement(
						"INSERT INTO papers (ID,  AUTHOR,  NAME,  TEXT,  CONFNAME,  STATUS, ADMINSTAT)  VALUES " +
						"(" + total +", '" + the_username + "', '" + the_paper_title + "', '" +
								the_file_submitted + "', '" + the_conference.getConfTitle() +
								"', '" + the_user_viewable_status + "', '" + the_admin_viewable_status + "')");
				
				statement.execute();
				System.out.println(the_paper_title + " Successfully added paper");
			} catch (SQLException e) {
				System.out.println("Check for valid paper failed");
				e.printStackTrace();
			}
		} else {
			throw new Exception("Author cannot submit any more papers to this conference.");
		}
	}
	
	/**
	 * Returns the relation of the person passed in with the paper passed in.  Tells if they are Author,
	 * Reviewer, SUBPC, or PC.
	 * If they have multiple designations to a single paper this method returns the relation
	 * that is of highest priority:
	 * PC > SubPC > Reviewer > Author.
	 * 
	 * @param the_conference The conference that the paper belongs to.
	 * @param the_paper_title The title of the paper to get relation for.
	 * @param the_username The username of the person to get relation for.
	 * @return A paperRelation enum of the highest priority that the user has with the paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the conference, paper title,
	 * 							and username are all linked together in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: A paperRelation enum of the highest priority is returned.</b>
	 * <dd>
	 */
	public paperRelation getRelationToPaper(final Conference the_conference, final String the_paper_title, 
			final String the_username){
		
		ResultSet resultSet2;
		String username = "";
		paperRelation relation = paperRelation.AUTHOR;
		
		//First Check if they are the author
		try {
			
			//Get all papers that conference, paper title, and author match
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE confname='" + the_conference.getConfTitle() + 
					"' AND name='" + the_paper_title + "' AND author='" + the_username + "'");
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				relation = paperRelation.AUTHOR;
			}
			
		} catch (Exception e) {
			System.out.println("Get paperRelation failed in author check");
		}
		
		//Second Check if they are a reviewer
		try {
			int paperid = -1;
			
			//Get all papers that conference, paper title, and author match
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE name=" +"'" + the_paper_title + "' AND confname='" + 
					the_conference.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				paperid = resultSet.getInt(1);//paperid in papers table
				
				statement = connect.prepareStatement("SELECT * FROM reviews WHERE paperid=" + paperid);
				resultSet2 = statement.executeQuery();
				
				while (resultSet2.next()) {
					username = resultSet2.getString(3); //reviewer in reviews table
					
					if(username.equals(the_username)) {
						
						//Means they are a reviewer
						relation = paperRelation.REVIEWER;
						break;
					}
				}
			}	
			
		} catch (Exception e) {
			System.out.println("Get paperRelation failed in reviewer check");
		}
		
		//Third Check if they are a subPC
		try {
			int paperid = -1;
			
			//Get all papers that conference, paper title, and author match
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE name=" +"'" + the_paper_title + "' AND confname='" + 
					the_conference.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				paperid = resultSet.getInt(1);//paperid in papers table
				statement = connect.prepareStatement("SELECT * FROM recommendations WHERE paperid=" + paperid);
				resultSet2 = statement.executeQuery();
				
				while (resultSet2.next()) {
					username = resultSet2.getString(3); //subPC in recommendations table
					if(username.equals(the_username)) {
						//Means they are the SUBPCS
						relation = paperRelation.SUBPC;
						break;
					}
				}
			}	
			
		} catch (Exception e) {
			System.out.println("Get paperRelation failed in subpc check");
		}
		
		//Last Check if they are the PC
		try {
			
			//Get all papers that conference, paper title, and author match
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM conferences WHERE name=" +"'" + the_conference.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				username = resultSet.getString(2); //Progchair
				if(username.equals(the_username)){
					//Means they are the PC
					relation = paperRelation.PC;
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Get paperRelation failed in pc check");
		}
		return relation;
	}
	
	/*
	 * @author Aaron 
	 */
	/**
	 * Sets the author and admin status of a paper.
	 * 
	 * @param the_conference The conference the paper belongs to.
	 * @param the_paper_title The title of the paper.
	 * @param the_author_viewable_status The author status to be updated to.
	 * @param the_admin_viewable_status The admin status to be updated to.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the conference and
	 * 							paper title are linked together in the database.</b>
	 * <dd>
	 * <dt><b>Postconditions: The status of the paper is updated to the passed in values.</b>
	 * <dd>
	 */
	public void setPaperStatus(final Conference the_conference, final String the_paper_title, 
			paperStatusAuthorViewable the_author_viewable_status, paperStatusAdminViewable the_admin_viewable_status){
		
		try {
			PreparedStatement statement = connect.prepareStatement("UPDATE papers SET status='" + the_author_viewable_status + "', " +
					"adminstat='" + the_admin_viewable_status + "' WHERE name='" + the_paper_title + "' AND confname='" +
					the_conference.getConfTitle() + "'");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error setting paper status." + e.getMessage());
		}
		
		
	}
	
	/**
	 * Gets the admin status of a paper in a given conference.
	 * Returns paperStatusAdminViewable.isNULL if the paper doesn't exist in
	 * the passed in conference.
	 * 
	 * @param the_conference The conference the paper belongs to.
	 * @param the_paper_title The title of the paper to get the admin status for.
	 * @return Returns a paperStatusAdminViewable enum indicating the admin status.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the conference and
	 * 							paper title are linked together in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: paperStatusAdminViewable enum returned. If the conference
	 * 							and paper title aren't linked, returns
	 * 							paperStatusAdminViewable.isNULL.</b>
	 * <dd>
	 */
	public paperStatusAdminViewable getAdminPaperStatus(final Conference the_conference, final String the_paper_title){
		
		paperStatusAdminViewable adminStatus = paperStatusAdminViewable.isNULL;
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE name=" +"'" + the_paper_title +"'");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				if (resultSet.getString(5).equals(the_conference.getConfTitle())) {		// Tests that the paper is associated
																    // with the desired conference.
					
					adminStatus = GUIEnum.stringToAdminViewable(resultSet.getString(7));	
					break;
				}			
			}
						
		} catch (Exception e) {
			System.out.println("paperStatusAdminViewable failed in SQL!");
		}
		
		return adminStatus;
	}
	
	/**
	 * Gets the author status of a paper in a given conference.
	 * Returns paperStatusAuthorViewable.isNULL if the paper doesn't exist in
	 * the passed in conference.
	 * 
	 * @param the_conference The conference the paper belongs to.
	 * @param the_paper_title The title of the paper to get the author status for.
	 * @return Returns a paperStatusAuthorViewable enum indicating the author status.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the conference and
	 * 							paper title are linked together in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: paperStatusAuthorViewable enum returned. If the conference
	 * 							and paper title aren't linked, returns
	 * 							paperStatusAuthorViewable.isNULL.</b>
	 * <dd>
	 */
	public paperStatusAuthorViewable getStatusAuthorView(final Conference the_conference, final String the_paper_title) {
		
		paperStatusAuthorViewable authorStatus = paperStatusAuthorViewable.isNULL;
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE name=" +"'" + the_paper_title +"'");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				if (resultSet.getString(5).equals(the_conference.getConfTitle())) {		// Tests that the paper is associated
																    // with the desired conference.
					
					authorStatus = GUIEnum.stringToAuthorViewable(resultSet.getString(6));
					break;
				}			
			}
						
		} catch (Exception e) {
			System.out.println("paperStatusAuthorViewable failed!");
		}
		
		return authorStatus;
	}
	
	/**
	 * Sets the current paper in the controller.
	 * 
	 * @param the_paper The paper to be assigned to current paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Sets the current paper to the passed in String.</b>
	 * <dd>
	 */
	public void setCurrentPaper(final String the_paper){
		current_paper = the_paper;
	}
	
	/**
	 * Returns the current paper of the controller.
	 * 
	 * @return The current paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String representation of the current paper.</b>
	 * <dd>
	 */
	public String getCurrentPaper(){
		return current_paper;
	}
	
	/**
	 * Returns the text of the paper that is passed in.
	 * 
	 * @param the_conf The conference the paper being examined belongs to.
	 * @param the_paper_title The title of the paper that is getting its text returned.
	 * @return Returns the text of the paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the conference and
	 * 							paper title are related in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String of the file path to the paper.</b>
	 * <dd>
	 */
	public String getPaperFilePath(final Conference the_conf, final String the_paper_title){
		String text = "";
		try {
			PreparedStatement statement = connect.prepareStatement(
					"SELECT text FROM papers WHERE name=" +"'" + the_paper_title +"' AND confname='" +
					the_conf.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				text = resultSet.getString(1);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fail to get paper text");
		}
		return text;
	}
	
	/*
	 * @author Aaron 
	 */
	/**
	 * Deletes the paper from the table.
	 * 
	 * @param the_conference The conference object.
	 * @param the_username The username of the current user.
	 * @param the_paper_title The title of the paper to be deleted.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and a paper related
	 * 							by conference, username, and paper title exists in the
	 * 							Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Paper in Database linked by all three passed in parameters
	 * 							is removed from the Database.</b>
	 * <dd>
	 */
	public void deletePaper(final Conference the_conference, final String the_username, final String the_paper_title){
		
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DELETE FROM papers WHERE name='" + the_paper_title + "' AND " +
					"confname='" + the_conference.getConfTitle() + "'");
			deleteReviews(the_conference, the_username, the_paper_title);
			deleteRec(the_conference, the_username, the_paper_title);
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error deleting paper." + e.getMessage());
		}
		
	}
	
	/**
	 * Deletes all the reviews related to the paper passed in.
	 * 
	 * @param the_conference The conference that the reviews belongs to.
	 * @param the_username The author of the paper whose reviews are being deleted.
	 * @param the_paper_title The name of the paper whose reviews are being deleted.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and a Review related
	 * 							by conference, username, and paper title exists in the
	 * 							Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Review in Database linked by all three passed in parameters
	 * 							is removed from the Database.</b>
	 * <dd>
	 */
	private void deleteReviews(final Conference the_conference, final String the_username, final String the_paper_title){
		int paperID = -1;
		
		try {
			PreparedStatement statement = connect.prepareStatement(
				"SELECT * FROM papers WHERE name='" + the_paper_title +"' AND confname='" +
			the_conference.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				paperID = resultSet.getInt(1);
			}
			
		} catch(Exception e) {
			System.out.println("Error in deleteReview get ID." + e.getMessage());
		}
		
		try {
			PreparedStatement statement = connect.prepareStatement("DELETE FROM reviews WHERE paperid=" +
					paperID + " AND " + "id=" + paperID);
			statement.execute();
			
		} catch (SQLException e) {
			System.out.println("Error deleting review." + e.getMessage());
		}
		
	}
	
	/**
	 * Deletes all the recommendations related to the paper passed in.
	 * 
	 * @param the_conference The conference the recommendations belongs to.
	 * @param the_username The author of the paper whose recommendations are being deleted.
	 * @param the_paper_title The name of the paper whose recommendations are being deleted.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and a recommendation related
	 * 							by conference, username, and paper title exists in the
	 * 							Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Recommendation in Database linked by all three passed in parameters
	 * 							is removed from the Database.</b>
	 * <dd>
	 */
	private void deleteRec(final Conference the_conference, final String the_username, final String the_paper_title){
		//		delete recommendations related to the paper passed in
		int paperID = -1;
		
		try {
			PreparedStatement statement = connect.prepareStatement(
				"SELECT id FROM papers WHERE name=" +"'" + the_paper_title +"' AND confname='" +
			the_conference.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				paperID = resultSet.getInt(1);
			}
			
		} catch(Exception e) {
			System.out.println("Error in deleteRed for get id." + e.getMessage());
		}
		
		try {
			PreparedStatement statement = connect.prepareStatement("DELETE FROM recommendations WHERE id=" +
					paperID + " AND " + "paperid=" + paperID);
			statement.execute();
			
		} catch (SQLException e) {
			System.out.println("Error deleting rec." + e.getMessage());
		}
		
	}
	
	/**
	 * Creates a new review in the database.
	 * 
	 * @param the_reviewer_username The username of the reviewer.
	 * @param the_conf The conference the review is associated with.
	 * @param the_paper The name of the paper the review is for.
	 * @param the_paper_author The author of the paper being reviewed.
	 * @param the_comments_to_subpc The comments to the sub program chair.
	 * @param the_answersRadioBtn The answers to the review questions.
	 * @param the_summary_comments The comments on the paper. 
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: If a Review linked by conference, username, and
	 * 							paper title exists- the Review is updated with
	 * 							the passed in values. If not, a new Review is
	 * 							added to the Database.</b>
	 * <dd>
	 */
	public void createNewReview(final String the_reviewer_username, final Conference the_conf, 
			final String the_paper, final String the_paper_author, final String the_comments_to_subpc, 
			final int[] the_answersRadioBtn, final String the_summary_comments){
		
		int paperId = -1;
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT id FROM papers WHERE name='" +
					the_paper + "' AND confname='" + the_conf.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				paperId = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("Error getting paperID in createNewReview." + e.getMessage());
		}
		
		try {			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM reviews WHERE papername=" +"'" + the_paper +"' AND conference='" +
					the_conf.getConfTitle() + "' AND reviewer='" + the_reviewer_username +"'");
			resultSet = statement.executeQuery();
			if(!resultSet.next()) {
				statement = connect.prepareStatement("INSERT INTO reviews VALUES (" + paperId + ", " + paperId + ", " +
						"'" + the_reviewer_username + "', '" + the_conf.getConfTitle() + "', '" + the_paper + "', " +
						"'" + the_paper_author + "', " + the_answersRadioBtn[0] + ", " + the_answersRadioBtn[1] + ", " +
						the_answersRadioBtn[2] + ", " + the_answersRadioBtn[3] + ", " + the_answersRadioBtn[4] + ", " +
						the_answersRadioBtn[5] + ", " + the_answersRadioBtn[6] + ", " + the_answersRadioBtn[7] + ", " +
						the_answersRadioBtn[8] + ", " + the_answersRadioBtn[9] + ", '" + the_summary_comments + "', '" +
						the_comments_to_subpc + "')");
				statement.execute();
			} else {
				statement = connect.prepareStatement("UPDATE reviews SET id=" + paperId + ", paperid=" + paperId +
						", reviewer='" + the_reviewer_username + "', conference='" + the_conf.getConfTitle() + 
						"', papername='" + the_paper + "', paperauthor='" + the_paper_author + "', q1=" +
						the_answersRadioBtn[0] + ", q2=" + the_answersRadioBtn[1] + ", q3=" + the_answersRadioBtn[2] +
						", q4=" + the_answersRadioBtn[3] + ", q5=" + the_answersRadioBtn[4] + ", q6=" +
						the_answersRadioBtn[5] + ", q7=" + the_answersRadioBtn[6] + ", q8=" + the_answersRadioBtn[7] +
						", q9=" + the_answersRadioBtn[8] + ", rating=" + the_answersRadioBtn[9] + ", comments='" +
						the_summary_comments + "', subcomments='" + the_comments_to_subpc + "' WHERE papername=" 
						+ "'" + the_paper + "' AND conference='" + the_conf.getConfTitle() + "' AND reviewer='" +
						the_reviewer_username + "'");
				statement.execute();
			}
		} catch (SQLException e) {
			System.out.println("Error creating new review part." + e.getMessage());
		}	
	}

	/*
	 * @author David 
	 */
	/**
	 * A method that is called to see if the number of reviews on a paper is less than 
	 * MAX_NUMBER_OF_REVIEWS and that the current user hasn't already submitted a review
	 * for the passed in paper.
	 * 
	 * @param the_conf the conference that the paper is in
	 * @param the_paper the name of the paper
	 * @param the_username the user that is trying to submit the paper.
	 * @return true if the current user has not submitted a review and the number of reviews is 
	 * 				less than or equal to MAX_NUMBER_OF_REVIEWS.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the passed in Conference
	 * 							and paper are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Boolean returned of whether another Review from that user
	 * 							can be added to the Database.</b>
	 * <dd>
	 */
	public boolean canAddReview(final Conference the_conf, final String the_paper, final String the_username){
		boolean permission_to_add = true;
		List<String> al = new ArrayList<String>();

		try {
			
			PreparedStatement statement = connect.prepareStatement("SELECT reviewer FROM reviews WHERE papername='"+
					the_paper+"'");
			resultSet = statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			  
	    	for (int i = 1; i <= numberOfColumns; i++) {
	    		if (i > 1) System.out.print(",  ");
	    		String columnName = rsmd.getColumnName(i);
	    		System.out.print(columnName);
	    	}
	    	System.out.println("");
	        while (resultSet.next()) {
	                ArrayList<String> record = new ArrayList<String>();
	                for (int i = 1; i <= numberOfColumns; i++) {
	                        String value = resultSet.getString(i);
	                        record.add(value);
	                }
	                String value = infoForSubmittingReview(record);
					al.add(value);
	        }    
			
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
		
		if(al.size() > MAX_NUMBER_OF_REVIEWS || al.contains(the_username)) {
			permission_to_add = false;
		}
		return permission_to_add;
	}
	
	/**
	 * Returns the info for submitting a Review.
	 * 
	 * @param record A list of records.
	 * @return Returns a String representing the name of the conference.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String representing the Conference.
	 * 							If list is empty: returns "".</b>
	 * <dd>
	 */
	private String infoForSubmittingReview(ArrayList<String> record) {
		String conference = "";
		for(int i = 0; i < record.size();i++) {
			if(i+1<record.size()) {
				conference +=record.get(i)+ ", ";
			} else {
				conference +=record.get(i);
			}
		}
		return conference;
	}

	/**
	 * Used by an administrative user to see the author of the paper that they are examining. 
	 * Returns a blank string if nothing was found.
	 * 
	 * @param the_conf The conference that the paper is a part of.
	 * @param the_paper The name of the paper the current user is looking at (not the author of the paper).
	 * @return Returns the username of the author of the paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String of the author's username.</b>
	 * <dd>
	 */
	public String getPaperAuthor(final Conference the_conf, final String the_paper){
		String username = "";

		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE confname=" +"'" + the_conf.getConfTitle() +"' AND name='" +
					the_paper + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				username = resultSet.getString(2);
			}
			
		} catch (Exception e) {
			System.out.println("Get full name failed!");
		}
		
		return username;
	}
	
	/**
	 * Adds a recommendation of the SubProgramChair to the database with their grade and rationale of
	 * the paper passed in.
	 * 
	 * @param the_sub_pc_username The subprogramchair username recommending the paper.
	 * @param current_conf The conference the paper being recommended belongs to.
	 * @param current_paper_being_recommended The paper that is being recommended.
	 * @param the_paper_author The author of the paper being recommended.
	 * @param the_numerical_value The grade the subprogramchair gave the paper.
	 * @param the_rational_for_recommendation The rationale behind the subprogramchair's grade.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and all passed in parameters
	 * 							are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: If the recommendation exists, the recommendation in the Database
	 * 							is updated, otherwise it is added to the Database.</b>
	 * <dd>
	 */
	public void addPaperRecommendation(final String the_sub_pc_username, final Conference current_conf, 
			final String current_paper_being_recommended, final String the_paper_author, final int the_numerical_value,
			final String the_rational_for_recommendation){

		int paperID = -1;	//not a paper
		
		//This try catch just gets the paperID from the author and papername passed in
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE name=" +"'" + current_paper_being_recommended +
					"' AND author='" + the_paper_author + "'");
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
					paperID = resultSet.getInt(1);
			}
		} catch(Exception e) {
			e.getStackTrace();
			System.out.println("Failed to addREC and get paperID");
		}
		
		// If the recommendation exists update it- otherwise add it
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM recommendations WHERE paperid=" + paperID);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				
				statement = connect.prepareStatement("UPDATE recommendations SET paperid=" + paperID +
						", subchair='" + the_sub_pc_username + "', conference='" + current_conf.getConfTitle() +
						"', papername='" + current_paper_being_recommended + "', paperauthor='" +
						the_paper_author + "', q1=" + the_numerical_value + ", rationale='" + 
						the_rational_for_recommendation + "' WHERE id=" + paperID);	
				statement.execute();
			} else {
				statement = connect.prepareStatement("INSERT INTO recommendations VALUES (" +
				paperID + "," + paperID + ",'" + the_sub_pc_username + "','" + current_conf.getConfTitle() +
				"','" + current_paper_being_recommended + "','" + the_paper_author + "'," + 
				the_numerical_value + ", '" + the_rational_for_recommendation + "')");
				statement.execute();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to just addREC" + e.getMessage());
		}
	}
	
	/**
	 * Retrieves the SubPC's recommendation for a paper from the database
	 * given a conference and a paper within that conference. If the paper does
	 * not exist in that conference the sentinel value paperID = -1 and it returns
	 * a blank string for the rationale.
	 * 
	 * @param the_conf The conference the recommendation belongs to.
	 * @param the_paper The paper the recommendation belongs to.
	 * @return Returns the recommendation of the paper being examined.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String representation of the rationale from
	 * 							the Database.</b>
	 * <dd>
	 */
	public String getPaperRecommendationRationale(final Conference the_conf, final String the_paper){
		
		int paperID = -1;		// Sentinel -1: if selected paper doesn't exist in that conference.
		String result = "";
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE name=" +"'" + the_paper +"'");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				if (resultSet.getString(5).equals(the_conf.getConfTitle())) {		// Tests that the paper is associated
																    // with the desired conference.
					paperID = resultSet.getInt(1);
					break;
				}			
			}
			
			if (paperID != -1) {
				statement = connect.prepareStatement("SELECT * FROM recommendations WHERE paperid=" + paperID);
				resultSet = statement.executeQuery();
				if(resultSet.next()) {
					result = resultSet.getString(8);			// Location of the rationale in the Database
				}
				
			} else {
				System.out.println("Requested Paper doesn't exist in that conference");
			}
			
		} catch (Exception e) {
			System.out.println("getPaperRecommendationRationale failed!");
		}
		
		return result;
	}

	/**
	 * Retrieves the numerical grade given to the_paper passed by the subprogramchair of that paper 
	 * relating to the_conf passed in. 
	 * 
	 * @param the_conf The conference the paper is in whose grade is being retrieved.
	 * @param the_paper The paper whose grade is being retrieved.
	 * @return Returns the numerical grade of the paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns an int reprenting the recommendation's value
	 * 							from the Database.</b>
	 * <dd>
	 */
	public int getPaperRecommendationNumericalValuefinal(final Conference the_conf, final String the_paper){

		int value = 0;
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM recommendations WHERE conference=" +"'" + the_conf.getConfTitle() + 
					"' AND papername='" + the_paper + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				value = resultSet.getInt(7);
			}
			
		} catch (Exception e) {
			System.out.println("Get subpc grade failed!");
		}
		return value;
	}
	
	/**
	 * Retrieves the SubProgramChair's username from the recommendation of the paper passed in from the
	 * conference passed in.
	 * 
	 * @param the_conf The conference the paper whose recommendation is being retrieved.
	 * @param the_paper The paper whose recommendation is being retrieved.
	 * @return Returns the SubProgramChair's username.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String representation of the SubPC's name
	 * 							from the Database.</b>
	 * <dd>
	 */
	public String getPaperRecommendationSubPCName(final Conference the_conf, final String the_paper){
		
		String subPCName = "";
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT subchair FROM recommendations WHERE " +
					"papername='" + the_paper + "' AND conference='" + the_conf.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()){
				subPCName = resultSet.getString(1);
			}
			
		} catch (SQLException e) {
			System.out.println("Error getting paper recommendation subpc name." + e.getMessage());
		}
		return subPCName;
	}
	
	/**
	 * This method returns all of the userNames of the reviewers that are assigned to a particular
	 * paper. The length of the array indicates how many are assigned (i.e. 0 = no reviewers). This
	 * method also prints to the console if the paper passed in has no association to the conference
	 * passed in.
	 * 
	 * @param the_conference The conference the paper belongs to.
	 * @param current_paper The paper whose reviewers are being retrieved.
	 * @return Returns an array of reviewers of the passed in paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns an array of all the username's of people reviewing
	 * 							the paper. The length of this array is a direct representation
	 * 							of how many reviewers there are for it.</b>
	 * <dd>
	 */
	public String[] getUsersAssignedAsReviewers(final Conference the_conference, final String current_paper){
		
		int count = 0;			// Keeps track of the number of reviewers for creating the array
		int paperID = -1;		// ID # of the paper, to search for the reviewers. Sentinel -1 if
								// the current_paper isn't associated with the_conference.
		
		String[] temp = new String[4];			// Temporarily stores the result from the DB
		String[] result = new String[0];		// Default length 0 if no reviews- reassigned later if some exist in the DB
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE name=" +"'" + current_paper +"'");
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				if (resultSet.getString(5).equals(the_conference.getConfTitle())) {		// Tests that the paper is associated
																    		// with the desired conference.
					paperID = resultSet.getInt(1);
					break;
				}			
			}	
			
			// If a valid paperID was found, gets all reviews associated with that paperID
			if (paperID != -1) {
				statement = connect.prepareStatement(
						"SELECT * FROM reviews WHERE paperid= " + paperID);
				resultSet = statement.executeQuery();
				
				// Goes through the resultSet of reviews which are associated with the paper
				while (resultSet.next()) {				
					temp[count] = resultSet.getString(3);
					count++;
				}
				
				// Copies the temp array into the result array
				result = new String[count];
				for (int i = 0; i < count; i++) {
					result[i] = temp[i];
				}
				
			} else {
				System.out.println("Requested Paper doesn't exist in that conference");
			}
			
		} catch (Exception e) {
			System.out.println("getUsersAssignedAsReviewers failed!");
		}		
		
		return result;
	}
	
	/**
	 * Method that returns the PC for a given conference.
	 * This method returns null if the conference can't be found in the database.
	 * 
	 * @param the_conference The conference to get PC of.
	 * @return Returns the username of the PC given the passed in conference.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference
	 * 							exists in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String representation of the PC's username.</b>
	 * <dd>
	 */
	public String getUserAssignedAsPC(final Conference the_conference){
		
		String result = null;
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT progchair FROM conferences WHERE name=" +"'" + the_conference.getConfTitle() +"'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				result = resultSet.getString(1);		
			}	
			
			
		} catch (Exception e) {
			System.out.println("getUserAssignedAsPC failed!");
		}
		
		return result;
	}
	
	/*
	 * @author David 
	 */
	/**
	 * Retrieves the username of the subprogram chair given a passed in
	 * Conference and paper.
	 * 
	 * @param the_conference The conference being examined.
	 * @param current_paper The paper being examined.
	 * @return Returns the subprogram chair's username.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper ID are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String representation of the SubPC's username from
	 * 							the Database.</b>
	 * <dd>
	 */
	public String getUserAssignedAsSubPC(final Conference the_conference, final int current_paper){
		
		String subPC = null;
		try {
			PreparedStatement statement = connect.prepareStatement(
					"SELECT subchair FROM recommendations WHERE conference=" +"'" + the_conference.getConfTitle() + 
					"' AND paperid=" + current_paper);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				subPC = resultSet.getString(1);
			}
			
		} catch (Exception e) {
			System.out.println("getUserAssignedAsSubPC failed!");
		}
		return subPC;
	}
	
	
	/**
	 * Method that returns all of the available reviewers for a given paper.
	 * This method follows the business rules:
	 * 			- the reviewer can't be the author of the paper.
	 *			- the reviewer can't be the person assigning the review, unless they're the PC.
	 *			- the reviewer in this list can't be one of the reviewers already assigned to this paper (they can't be assigned twice).
	 * 
	 * @param the_conference The conference the paper being looked at belongs to.
	 * @param the_paper The paper being checked for available reviewers.
	 * @param the_person_assigning The subpc/pc assigning the reviewers to the paper.
	 * @return Returns an array of available users who can review this paper following the above business rules.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns an array of all the usernames that are available
	 * 							to review the passed in paper in the passed in Conference.</b>
	 * <dd>
	 */
	public String[] getAvailableReviewers(final Conference the_conference, final String the_paper, final String the_person_assigning){
		
		List<String> result = new ArrayList<String>();
		String[] reviewers = getUsersAssignedAsReviewers(the_conference, the_paper);		// Retrieves reviewers assigned to paper
		
		String user = "";		// User obtained from the database to test against business rules
		String author = "";
		String PC = "";
		
		boolean ruleViolated;		// boolean to keep track if one of the business rules has been violated
		
		try {
			
			// Get the author of the paper to check against usernames
			PreparedStatement statement = connect.prepareStatement(
					"SELECT author FROM papers WHERE name='" + the_paper + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				author = resultSet.getString(1);
			}
			
			// Get program chair of conference to check against the_person_assigning
			statement = connect.prepareStatement(
					"SELECT progchair FROM conference WHERE name='" + the_conference.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				PC = resultSet.getString(1);
			}
			
			// Gets a list of all users in the DB to check against business rules
			statement = connect.prepareStatement(
					"SELECT username FROM users");
			resultSet = statement.executeQuery();			
			
			while (resultSet.next()) {
				user = resultSet.getString(1);
				
				// The reviewer can't be the same as the author
				if (!(author.equals(user))) {				
					
					// The reviewer can't be the person assigning unless they're the PC
					if (user.equals(PC) || (!(user.equals(the_person_assigning)))) {
						ruleViolated = false;
						
						// The reviewer can't have already been assigned to this paper
						for (int i = 0; i < reviewers.length && !ruleViolated; i++) {
							if (user.equals(reviewers[i])) {
								ruleViolated = true;
							}
						}
						
						// If no rules were violated, add to list
						if (!ruleViolated) {
							result.add(user);
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("getAvailableReviewers failed!");
		}
					
		return result.toArray(new String[result.size()]);
	}
	
	/*
	 * @author Aaron 
	 */
	/**
	 * Adds the reviewers to the passed in paper.
	 * 
	 * @param the_conference The conference the paper is in.
	 * @param the_paper The paper the reviewers are reviewing.
	 * @param the_reviewers The users who are reviewing the paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Adds the Reviewers to the database and submits a "blank"
	 * 							review for each one that can be updated in the future.</b>
	 * <dd>
	 */
	public void addReviewers(final Conference the_conference, final String the_paper, final String[] the_reviewers){

		int paperId = 0;
		String paperAuthor = "";
		
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM papers WHERE name='" + the_paper + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				paperId = Integer.parseInt(resultSet.getString(1));
				paperAuthor = resultSet.getString(2);
			}
			System.out.println(paperId);
			
		} catch (SQLException e) {
			System.out.println("Error creating new review. 1" + e.getMessage());
		}
		
		for (int i = 0; i < the_reviewers.length; i++){
			PreparedStatement statement;
			try {
				
				statement = connect.prepareStatement("SELECT * FROM reviews");
				resultSet = statement.executeQuery();
				
				//iterates through and adds unique ids to the reviews.
				int j = 0;
				int otherId = 0;
				int id = 0;
				while (resultSet.next()){
					otherId = Integer.parseInt(resultSet.getString(1));
					if (j != otherId){
						id = j;
						
					}
					else {
						id = j + 1;
					}
					j++;
				}
				
				//Adds the reviewer to the table by adding a "blank" review. 
				statement = connect.prepareStatement("INSERT INTO reviews VALUES (" + id + "," + paperId + ",'" +
			    the_reviewers[i] + "','" + the_conference.getConfTitle() + "','" + the_paper + "','" +
			    paperAuthor + "',0,0,0,0,0,0,0,0,0,0,'No comments at this time.','No comments at this time.')");
				statement.execute();
			} catch (SQLException e) {
				System.out.println("Error adding reviewers. 42" + e.getMessage());
			}	
		}
		
		
	}
	
	/*
	 * @author David
	 */
	/**
	 * A method that populates a string array of all users that are capable of being a subprogram chair.
	 * 
	 *Note: - the PC can't be the SubPC.
	 *		- the Author of the paper can't be the SubPC of their own paper.
	 *		- the reviewer can't be the SubPC, although we shouldn't have to check this.  The SubPC is the person
	 *		  who assigns the reviewer, so it's the chicken and egg thing.
	 *		- any other business rules I'm forgetting?  (Jacob)
	 * 
	 * @param current_conf the conference the paper is in
	 * @param current_paper the paper that is having a subprogram chair added.
	 * @param the_pc the name of the program chair.
	 * @return a string array that contains all usernames of users that are available for subprogram chair.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper ID are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns an array of usernames that are eligible to be the SubPC.</b>
	 * <dd>
	 */
	//TODO: returns the author of the paper as available username THIS IS INCORRECT
	public String[] getAvailableForSubPC(final Conference current_conf, final int current_paper, final String the_pc){
		//TODO: the AssignSubPCGUI needs an array of usernames of the people capable of being a SubPC.
		//		Note: - the PC can't be the SubPC.
		//			  - the Author of the paper can't be the SubPC of their own paper.
		//			  - the reviewer can't be the SubPC, although we shouldn't have to check this.  The SubPC is the person
		//				who assigns the reviewer, so it's the chicken and egg thing.
		//			  - any other business rules I'm forgetting?  (Jacob)
		ArrayList<String> al = new ArrayList<String>();
		PreparedStatement statement;
		//1) List of all users registered
		try {
			statement = connect.prepareStatement("SELECT * FROM users");
			
			resultSet = statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
						
			int numberOfColumns = rsmd.getColumnCount();
			  
//		    	for (int i = 1; i <= numberOfColumns; i++) {
//		    		if (i > 1) System.out.print(",  ");
//		    		String columnName = rsmd.getColumnName(i);
//		    		System.out.print(columnName);
//		    	}
//		    	System.out.println("");
		    	
		    	while (resultSet.next()) {
	                ArrayList<String> record = new ArrayList<String>();
		            for (int i = 1; i <= numberOfColumns; i++) {
//		            	if (i > 1) System.out.print(",  ");
		            	String columnValue = resultSet.getString(i);
                        record.add(columnValue);
//		            	System.out.print(columnValue);
                }
                String value = infoForAUser(record);
                al.add(value); 
//		            System.out.println("");
		        }
		} catch (SQLException e) {
			System.out.println("Error printing table. " + e.getMessage());
		}
		//2) take PC user name out of array
		al.remove(the_pc);
//
//		String pc = getUserIdForPC(current_conf);
//		for(int i = 0; i < al.size(); i++) {
//			if(al.get(i).equals(pc)) {
//				al.remove(the_pc);
//			}
//		}
		//3) take author of paper out
		String authorUsername = getAuthorUsernameForPaper(current_paper);
		for(int i = 0; i < al.size(); i++) {
			if(al.get(i).equals(authorUsername)) {
				al.remove(authorUsername);
			}
		}
		//4) don't allow reviewers to be sub PC
					//temporary:
					String[] reviewers;
					if(al.size()>0) {
						reviewers = new String[al.size()];
						for(int i=0;i<al.size();i++){
							reviewers[i]=al.get(i);
						}
					} else {
						reviewers = new String[1];
						reviewers[0] = "";
					}
		return reviewers;
	}
	
	/**
	 * Gets the username of the author of a paper given a paperID.
	 * 
	 * @param the_paper_id The ID of the paper to get the author for.
	 * @return Returns the username of the author.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and paper ID exists
	 * 							in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns a String representation of the author's username from
	 * 							the Database.</b>
	 * <dd>
	 */
	private String getAuthorUsernameForPaper(int the_paper_id) {
		String authorid = "";
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT author FROM papers WHERE id=" + the_paper_id);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				authorid = resultSet.getString(1);
			}
			
		} catch (Exception e) {
			System.out.println("Get full name failed!");
		}
		return authorid;
	}
	
	/**
	 * Returns the user information from a conference.
	 * 
	 * @param the_conference The list of conferences.
	 * @return Returns user info.
	 */
	private String infoForAUser(ArrayList<String> the_conference) {
		String conference = the_conference.get(0);
		return conference;
	}
	
	/*
	 * @author David
	 */
	/**
	 * Adds a user as a SubPC to a given conference and paper.
	 * 
	 * @param the_conference The conference the paper belongs to.
	 * @param the_paper The paper being assigned to the SubPC.
	 * @param the_sub_pc The username of the SubPC.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Adds the SubPC to the Database linked to both the passed in
	 * 							Conference and paper.</b>
	 * <dd>
	 */
	public void addSubPC(final Conference the_conference, final String the_paper, final String the_sub_pc){
		
		int paperID = -1;
		String author = "";
		
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM papers WHERE name='" + the_paper +
					"' AND confname='" + the_conference.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			//First get the paperid from papers table this is the same as id for recommendation
			if(resultSet.next()) {
				paperID = resultSet.getInt(1);
				author = resultSet.getString(2);
				System.out.println("got paper id in addSubPC");
			} else {
				System.out.println("DIDNT GET PAPERID IN ADDSUBPC");
			}
			
		} catch (SQLException e) {
			System.out.println("getting paper id failed in addSubPC");
			e.printStackTrace();
		}
		
		try {
			//Now insert into recommendations the subpc passed in
			PreparedStatement statement = connect.prepareStatement("INSERT INTO recommendations VALUES (" + 
					paperID + ", " + paperID + ", '" + the_sub_pc+ "', '" + the_conference.getConfTitle() + 
					"', '" + the_paper + "', '" + author + "', " + 0 + ", '')");
			statement.execute();
			System.out.println("added SubPC in addSubPC");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed to addSubPC!");
		}
		
	}
	
	/**
	 * Returns an array of Review Objects of the given paper in the given conference.
	 * 
	 * @param the_conference The conference the paper belongs to.
	 * @param the_paper The paper whose reviews are being retrieved.
	 * @return Returns an array of Review objects of the given paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns an array of Reviews from the Database.</b>
	 * <dd>
	 */
	public Review[] getReviews(final Conference the_conference, final String the_paper){
		
		int paperID = -1;
		List<Review> reviewarray = new ArrayList<Review>();
		
		//Retrieve the PaperID from papers table to use for getting reviews from the reviews table
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM papers WHERE name='" + the_paper +
					"' AND confname='" + the_conference.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				paperID = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("failed at getting paper in getReviews");
		}
		
		//Retrieve reviews from reviews table using the paperid
		try {
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM reviews WHERE paperid=" + paperID);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				reviewarray.add(new Review(resultSet.getString(3), the_conference, resultSet.getString(3),
						the_paper, resultSet.getString(6), resultSet.getString(18), 
						new int[]{resultSet.getInt(7), resultSet.getInt(8),
						resultSet.getInt(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12),
						resultSet.getInt(13), resultSet.getInt(14), resultSet.getInt(15), resultSet.getInt(16)},
						resultSet.getString(17)));
			}
		} catch (Exception e) {
			System.out.println("failed at retrieving reviews in getReviews");
		}
		
		return reviewarray.toArray(new Review[reviewarray.size()]);
	}
	
	/**
	 * Returns an array of Conference objects that the user that is passed in belongs to.
	 * 
	 * @param the_username The username of the logged in person.
	 * @return Returns an array of conferences that the user is associated with.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and username exists
	 * 							in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns an array of Conferences that the user has some
	 * 							association with.</b>
	 * <dd>
	 */
	public Conference[] getMyConferences(final String the_username){
		ResultSet resultSet2;
		PreparedStatement statement;
		List<Conference> the_conf_array = new ArrayList<Conference>();
		
		//First add conferences they are PC of	
		try {
			
			//Get all conferences with a match for the username as the PC of the conference
			statement = connect.prepareStatement(
					"SELECT * FROM conferences WHERE progchair=" +"'" + the_username + "'");
			resultSet = statement.executeQuery();
			
			//Add the conferences the user is PC of to the array
			while (resultSet.next()) {			//(1) = confname		(2) = progchair
				Conference con = new Conference(resultSet.getString(1), resultSet.getString(2),
						resultSet.getDate(3), "", "", "", "", resultSet.getDate(4), resultSet.getDate(5),
						resultSet.getDate(6), resultSet.getDate(7), resultSet.getString(8));
				the_conf_array.add(con);
			}
			
		} catch (Exception e) {
			System.out.println("getMyConferences failed in adding PC conf");
		}
		
	
		//Second add conferences they are SUBPC of
		try {
			
			//Get all recommendations with a match for the username as the subPC of the conference
			statement = connect.prepareStatement(
					"SELECT * FROM recommendations WHERE subchair=" +"'" + the_username + "'");
			resultSet = statement.executeQuery();
			//Add the conferences the user is subPC of to the array
			
			while (resultSet.next()) {
				
				//gets the data for the conference to create a conference object to add to the array
				PreparedStatement statement2 = connect.prepareStatement(
						"SELECT * FROM conferences WHERE name=" +"'" + resultSet.getString(4) + "'");
				resultSet2 = statement2.executeQuery();
				
				if (resultSet2.next()) {
					Conference con = new Conference(resultSet2.getString(1), resultSet2.getString(2),
						resultSet2.getDate(3), "", "", "", "", resultSet2.getDate(4), resultSet2.getDate(5),
						resultSet2.getDate(6), resultSet2.getDate(7), resultSet2.getString(8));
					
					// Add only if the conference isn't in the list yet
					if (!isConfInList(con, the_conf_array)) {
						the_conf_array.add(con);
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("getMyConferences failed in adding subPC conf");
		}
		
		//Third add conferences they are reviewers for
		try {
			
			//Get all reviews with a match for the username as the reviewer of the conference
			statement = connect.prepareStatement(
					"SELECT * FROM reviews WHERE reviewer=" +"'" + the_username + "'");
			resultSet = statement.executeQuery();
			//Add the conferences the user is a reviewer of to the array
			while (resultSet.next()) {
				//gets the data for the conference to create a conference object to add to the array
				PreparedStatement statement2 = connect.prepareStatement(
						"SELECT * FROM conferences WHERE name=" +"'" + resultSet.getString(4) + "'");
				resultSet2 = statement2.executeQuery();
				
				if (resultSet2.next()) {
					Conference con = new Conference(resultSet2.getString(1), resultSet2.getString(2),
						resultSet2.getDate(3), "", "", "", "", resultSet2.getDate(4), resultSet2.getDate(5),
						resultSet2.getDate(6), resultSet2.getDate(7), resultSet2.getString(8));
					
					// Add only if the conference isn't in the list yet
					if (!isConfInList(con, the_conf_array)) {
						the_conf_array.add(con);
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("getMyConferences failed in adding reviewer conf");
		}
		
		//Fourth add conferences they are authors for papers of
		try {
			
			//Get all papers with a match for the username as the author 
			statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE author='" + the_username + "'");
			resultSet = statement.executeQuery();
				
			//Add the conferences the author has a paper in to the array
			while (resultSet.next()) {
				//gets the data for the conference to create a conference object to add to the array
				PreparedStatement statement2 = connect.prepareStatement(
						"SELECT * FROM conferences WHERE name='" + resultSet.getString(5) + "'");
				resultSet2 = statement2.executeQuery();
				
				if (resultSet2.next()) {
					Conference con = new Conference(resultSet2.getString(1), resultSet2.getString(2),
						resultSet2.getDate(3), "", "", "", "", resultSet2.getDate(4), resultSet2.getDate(5),
						resultSet2.getDate(6), resultSet2.getDate(7), resultSet2.getString(8));
					
					// Add only if the conference isn't in the list yet
					if (!isConfInList(con, the_conf_array)) {
						the_conf_array.add(con);
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("getMyConferences failed in adding author conf" + e.getMessage());
		}
		
		return the_conf_array.toArray(new Conference[the_conf_array.size()]);
	}
	
	/*
	 * @author Aaron 
	 */
	/**
	 * Gets all conferences that are upcoming but not in myConferences.
	 * 
	 * @param the_username The username of the current user.
	 * @return Returns an array of Conferences that contains all upcoming conferences.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and username exists
	 * 							 in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: An array of Conferences is returned containing all future
	 * 							Conferences that they have no association with.</b>
	 * <dd>
	 */
	public Conference[] getUpcommingConferences(final String the_username) {
    	ArrayList<Conference> upcomingconf = new ArrayList<Conference>();
    	ArrayList<Conference> copy = new ArrayList<Conference>();
    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Conference[] myConferences = getMyConferences(the_username);

		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM conferences WHERE NOTIFYDATE >= '"+formatter.format(new Date())+"'");
			resultSet = statement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			  
	    	for (int i = 1; i <= numberOfColumns; i++) {
	    		if (i > 1) System.out.print(",  ");
	    		String columnName = rsmd.getColumnName(i);
	    		System.out.print(columnName);
	    	}
	    	System.out.println("");
			while (resultSet.next()) {			
				Conference con = new Conference(resultSet.getString(1), resultSet.getString(2),
						resultSet.getDate(3), "", "", "", "", resultSet.getDate(4), resultSet.getDate(5),
						resultSet.getDate(6), resultSet.getDate(7), resultSet.getString(8));
				upcomingconf.add(con);
			}	
			copy = new ArrayList<Conference>(upcomingconf);
			for (Conference c : upcomingconf){
				for(int i = 0; i < myConferences.length; i++){
					if (myConferences[i].getConfTitle().equals(c.getConfTitle())){
						copy.remove(c);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Check for valid conference name failed");
			e.printStackTrace();
		}
		
		return copy.toArray(new Conference[copy.size()]);
	}	
	
	/*
	 * @author David 
	 * @author Seth Kramer
	 */
	/**
	 * Returns an array of Papers that are in the conference passed in and are associated 
	 * with the user.
	 * 
	 * @param the_conf The conference whose papers are being examined.
	 * @param the_username The username of the person who is associated with the returned papers.
	 * @return Returns an array of Papers associated with the user.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns an array of papers that the user has some association to.</b>
	 * <dd>
	 */
	public Paper[] getMyPapers(final Conference the_conf, final String the_username){
			
			List<Paper> paperList = new ArrayList<Paper>();
			ResultSet resultSet2;
			
			try {	
				
				// Retrieves all papers if PC of conference
				if (the_username.equals(getUserAssignedAsPC(the_conf))) {
					PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE confname=" +"'" + the_conf.getConfTitle() +"'");
					resultSet = statement.executeQuery();
							  
					while (resultSet.next()) {
						createPaperObjects(paperList, the_conf, the_username, resultSet);
					}	
					
				} else {	// Not PC of conference
					
					// Checks second for being an author of papers in the given conference
					PreparedStatement statement = connect.prepareStatement(
							"SELECT * FROM papers WHERE confname=" +"'" + the_conf.getConfTitle() +
							"' AND author='" + the_username + "'");
					resultSet = statement.executeQuery();
				  
					while (resultSet.next()) {
						createPaperObjects(paperList, the_conf, the_username, resultSet);
					}
					
					// Checks third if they are the SubPC of any papers in the conference
					statement = connect.prepareStatement(
							"SELECT paperid FROM recommendations WHERE conference=" +"'" + the_conf.getConfTitle() +
							"' AND subchair='" + the_username + "'");
					resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						statement = connect.prepareStatement(
								"SELECT * FROM papers WHERE id=" + resultSet.getInt(1));
						resultSet2 = statement.executeQuery();
						
						if (resultSet2.next()) {
							createPaperObjects(paperList, the_conf, the_username, resultSet2);
						}
					}
										
					// Checks fourth if they are a Reviewer for any paper in the conference
					statement = connect.prepareStatement(
							"SELECT paperid FROM reviews WHERE conference=" +"'" + the_conf.getConfTitle() +
							"' AND reviewer='" + the_username + "'");
					resultSet = statement.executeQuery();
					
					while (resultSet.next()) {
						statement = connect.prepareStatement(
								"SELECT * FROM papers WHERE id=" + resultSet.getInt(1));
						resultSet2 = statement.executeQuery();
						
						if (resultSet2.next()) {
							createPaperObjects(paperList, the_conf, the_username, resultSet2);
						}
					}
					
				}
		    	
			} catch (Exception e) {
				System.out.println("getMyPapers failed!" + e.getMessage());
			}
			
			
			return paperList.toArray(new Paper[paperList.size()]);
		}

	/**
	 * Gets the paperID associated with the name of the paper.
	 * TODO: Need more than this to get paperID effectively.
	 * 
	 * @param current_paper The name of the paper.
	 * @return Returns the ID# of the paper.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and paper exists
	 * 							in the Database.</b>
	 * <dd>
	 * <dt><b>Postconditions: Returns the ID of the paper.</b>
	 * <dd>
	 */
	public int getPaperID(String current_paper) {
		int paper = 0;
		try {
			
			PreparedStatement statement = connect.prepareStatement("SELECT id FROM papers WHERE name='"+current_paper+"'");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				paper = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
		return paper;
	}
	
	/*
	 * @author Seth Kramer
	 */
	/**
	 * This method adds Paper objects to the passed in list by using the ResultSet
	 * (which MUST be rows from the papers table). All rows passed in are packaged
	 * as paper objects and added to the list. The Conference and username must also
	 * be the ones used to obtain the ResultSet in the calling method. 
	 * ResultSet.next() must also have been checked / called before entering this method.
	 * 
	 * @param the_paper_list The list to add papers to.
	 * @param the_conference The conference used to obtain the ResultSet.
	 * @param the_username The username used to obtain the ResultSet.
	 * @param rs The ResultSet to package Papers from.
	 * 
	 * <dt><b>Preconditions: A Controller object has been created and the Conference and
	 * 							paper title are linked in the Database. The ResultSet passed
	 * 							in must be pointing to a valid row.</b>
	 * <dd>
	 * <dt><b>Postconditions: Modifys the passed in List by adding all paper's from the
	 * 							passed in ResultSet to it.</b>
	 * <dd>
	 */
	private void createPaperObjects(List<Paper> the_paper_list, final Conference the_conference, 
			final String the_username, final ResultSet rs) {
		
		try {
			do {
			
				int paperID = rs.getInt(1);
				String paperName = rs.getString(3);
				
				if (!isPaperInList(paperName, the_paper_list)) {
					
					// Adds new paper to the list
					the_paper_list.add(new Paper(rs.getString(5), rs.getString(3), rs.getString(2), 
							getStatusAuthorView(the_conference, paperName),
							getAdminPaperStatus(the_conference, paperName),
							getUserAssignedAsSubPC(the_conference, paperID), 
							getUserAssignedAsPC(the_conference), 
							getUsersAssignedAsReviewers(the_conference, paperName),
							paperID));
				}
			
			} while (rs.next());	// Continually adding until all rows are exhausted.
			
		} catch (Exception e) {
			System.out.println("createPaperObjects failed!");
		}
	}
	
	/*
	 * @author Seth Kramer
	 */
	/**
	 * Returns a boolean indicating if a paper is already in the passed in list.
	 * This ONLY checks the name of the paper title, so all papers must be from the
	 * same conference.
	 * 
	 * @param the_paperTitle The title of the paper.
	 * @param the_paperList The list to check.
	 * @return Returns true if the paper is in the passed in list.
	 */
	private boolean isPaperInList(String the_paperTitle, List<Paper> the_paperList) {
		boolean result = false;
		
		for (int i = 0; (i < the_paperList.size()) && !result; i++) {
			
			// If the paper title is the same as the title in the list: result = true
			if (the_paperTitle.equals(the_paperList.get(i).getPaperTitle())) {
				result = true;
			}
		}
		
		return result;
	}
	
	/*
	 * @author Seth Kramer
	 */
	/**
	 * Returns a boolean indicating if a Conference is already in the passed in list.
	 * Checks by comparing the String titles of the Conferences.
	 * 
	 * @param the_conference The Conference to check.
	 * @param the_conferenceList The list of Conferences to check against.
	 * @return Returns true if the Conference is in the list.
	 */
	private boolean isConfInList(Conference the_conference, List<Conference> the_conferenceList) {
		boolean result = false;
		
		for (int i = 0; (i < the_conferenceList.size()) && !result; i++) {
			
			// If the conference title is the same as the title in the list: result = true
			if (the_conference.getConfTitle().equals(the_conferenceList.get(i).getConfTitle())) {
				result = true;
			}
		}
		
		return result;
	}
}
	
