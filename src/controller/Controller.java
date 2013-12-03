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
import java.sql.Statement;
import java.text.ParseException;
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
import database.ManageDatabase;


/**
 * The Controller handle all business logic and act as an interface between the GUI
 * and the database.
 * 
 * @author Jacob Hall
 * @version 90 Date: 11/27/13
 */
public class Controller extends Observable{
	
	/**
	 * The connection the controller has to the Database.
	 * This connection is set up during construction.
	 */
	private Connection connect = null;
	
	/**
	 * The statement is the String that is fed to the database 
	 * to issue SQL commands.
	 */
	private Statement statement = null;
	
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
	 * Constructor for the Controller.  State initially set to LOGIN
	 * @throws Exception 
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
	 * @param the_state
	 */
	public void setStateOfGUI(StateOfGUI the_state){	
		if (the_state != state) {
			state = the_state;	
			setChanged();
			notifyObservers();
		}	
	}
	
	/**
	 * Get the next state the GUI should transition to.
	 * 
	 * @return state The updated current state.
	 */
	public StateOfGUI getStateOfGUI(){
		return state;
	}

	/**
	 * Check the username in the database and return true if it is the valid 
	 * username of someone already in the system
	 * 
	 * @param the_username The username entered in some GUI field
	 * @return valid  Returns true if the username exists in the database.
	 */
	//Tyler Powers was here
	
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
	 * @param the_username The username entered
	 * @param the_password The password entered
	 * @return valid Returns True is the username/password combo are verified
	 * 			Returns False if the combination is not recognized by the database
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
	 * Adds a new user to the database. GUI first uses checkValidUsername(final String the_username, 
	 * final String the_password) to ensure no duplicates.
	 * 
	 * @param the_username the login name for user
	 * @param the_password password that is used to login
	 * @param the_first_name user's first name
	 * @param the_middle_name user's middle initial
	 * @param the_last_name user's last name
	 * @param the_specialty the field that the user specializes in or focuses on
	 */
	//Tyler Powers was here
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
	 * @param the_username the username of the current person logged in
	 */
	public void setCurrentUsername(final String the_username){
		current_user = the_username;
	}
	
	/**
	 * Returns the username of the current person logged in.
	 * 
	 * @return current_user The current user
	 */
	public String getCurrentUsername(){
		return current_user;
	}
	
	/**
	 * Returns the full name of the individual when the username is passed in
	 * 
	 * @param the_username The username of the individual being queried
	 * @return The full name of the person.
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
	/**
	 * checks to see if the conference currently exists 
	 * in the database
	 * @author David
	 * @param the_conference_title the title of conference
	 * @return Returns True if the conference title exists in database
	 * 			Returns False if the title is not in the database
	 */
	public Boolean checkConferenceExists(final String the_conference_title) {
		Boolean valid = false;
		// (SETH) It's actually "conferences" that's in the database
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

	
	/**
	 * creates a new conference. GUI code first sees if checkConferenceExists(final String the_conference_title)
	 * returns false
	 * @author David
	 * @param the_conference the current conference object that is being created
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
	 * Sets the current conference to the conference of the person currently being looked at by the user
	 * 
	 * @param the_conference_name
	 */
	public void setCurrentConference(final Conference the_conference_name){
		current_conference = the_conference_name;
	}
	/**
	 * Returns the conference that is being looked at by the user
	 * @return the current conference
	 */
	public Conference getCurrentConference(){
		return current_conference;
	}
	/**
	 * Used to add a new paper to database and see if number of submissions is at max.
	 * @author David
	 * @param the_conference
	 * @param the_username
	 * @param the_paper_title
	 * @param the_file_submitted
	 * @param the_user_viewable_status
	 * @param the_admin_viewable_status
	 * @throws Exception
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
		//	  	add the user viewable status and the admin viewable status.  These are already set up in the GUIEnum class.
		//		We need two because the user should only see a general indication of the progress and the admin needs to see
		//		a detailed status update according to deadlines and where it's at in the whole review process. (Jacob)
		if(i<4) {
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
			throw new Exception("Author cannot submit more than 4 papers to a single conference.");
		}
		setCurrentPaper(the_paper_title);
	}
	
	/**
	 * Returns the relation of the person passed in with the paper passed in.  Tells if they are Author,
	 * Reviewer, SUBPC, or PC.
	 * 
	 * @param the_conference The conference the conference the paper belongs to
	 * @param the_paper_title The name of the paper being examined for relation to
	 * @param the_username the name of the person looking to see the relation of the paper
	 * @return an enum that tells what the relation is of the person to the paper.
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
				/*TESTING
				username = resultSet.getString(2);//Author in papers table
				//Means they are an author
				if(the_username.equals(username)) {
					relation = paperRelation.AUTHOR;
				}
				*/
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
		System.out.println("Relation: " + relation);
		return relation;
	}
	 
	public void setPaperRelation(final Conference the_conference, final String the_paper_title, 
			final String the_username, final paperRelation the_relation){
		//TODO: The GUI needs to be able to set a person's relation to a paper to be later queried.  (Jacob)
		//kind of the same idea as getRelationToPaper()
		//TODO: Kind of wondering what the point of this method is. If we know the conference and paper,
		//doesn't that make this kind of pointless? Or maybe I don't get what this method is supposed to do.
		//It seems like we would never need to set a user's relation in regards to a given paper when we know
		//the conference. For example, if we know the conference we can find out who the pc is. Why would we
		//need to set the pc in a conference that already has one? 
		/*
		if (the_relation == paperRelation.PC){ //This case also makes no sense.
			try {
				PreparedStatement statement = connect.prepareStatement("");
				resultSet = statement.executeQuery();
				
			} catch (SQLException e) {
				System.out.println("Error setting paper relation." + e.getMessage());
			}
		} else if (the_relation == paperRelation.SUBPC) { //Same with this one?
			addSubPC(the_conference, the_paper_title, the_username);
		} else if (the_relation == paperRelation.REVIEWER) { //This one could have a use...
			String[] reviewer = new String[1];
			reviewer[0] = the_username;
			addReviewers(the_conference, the_paper_title, reviewer);
		} else { //Isn't this a trivial case? When would we set an user as an author of paper?
			try {
				PreparedStatement statement = connect.prepareStatement("UPDATE papers SET author='" + the_username + "'");
				statement.execute();								//TODO: fix this to where= otherwise sets entire table 
				
			} catch (SQLException e) {
				System.out.println("Error setting paper relation." + e.getMessage());
			}
			
		}*/
		
	}
	
	
	/**
	 * Sets the statuses of a paper.
	 * 
	 * @param the_conference The conference object
	 * @param the_paper_title The title of the paper.
	 * @param the_author_viewable_status The status to be updated to.
	 * @param the_admin_viewable_status The status to be updated to.
	 * @author Aaron
	 */
	public void setPaperStatus(final Conference the_conference, final String the_paper_title, 
			paperStatusAuthorViewable the_author_viewable_status, paperStatusAdminViewable the_admin_viewable_status){
		
		//		this is a generic update of the Paper status for both author viewable and admin viewable called at different
		//		points in the program when reviews are submitted and so on.
		
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
	 * 
	 * @param the_conference
	 * @param the_paper_title
	 * @return
	 */
	public paperStatusAdminViewable getAdminPaperStatus(final Conference the_conference, final String the_paper_title){
		
		paperStatusAdminViewable adminStatus = null;
		
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
		/*
		if (adminStatus.equals(null)) {
			System.out.println("paperStatusAdminViewable failed in null check!");
		}
		*/
		
		return adminStatus;
	}
	
	/**
	 * Returns the status of the paper that an author is privy to given the title of a paper
	 * and its corresponding conference. This method returns null if there is either a SQL exception
	 * or if the paper title isn't associated with the passed in conference.
	 */
	public paperStatusAuthorViewable getStatusAuthorView(final Conference the_conference, final String the_paper_title) {
		
		paperStatusAuthorViewable authorStatus = null;
		
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
		/*
		if (authorStatus.equals(null)) {
			System.out.println("paperStatusAuthorViewable failed!");
		}
		*/
		return authorStatus;
	}
	
	/**
	 * 
	 * @param the_paper
	 */
	public void setCurrentPaper(final String the_paper){
		current_paper = the_paper;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCurrentPaper(){
		return current_paper;
	}
	
	/**
	 * Returns the text of the paper that is passed in.
	 * 
	 * @param the_conf the conference the paper being examined belongs to
	 * @param the_paper_title the paper that is getting its text returned
	 * @return the text of the papers
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
			System.out.println("fail to get paper text");
		}
		return text;
	}
	
	/**
	 * Deletes the paper from the table.
	 * 
	 * @param the_conference The conference object.
	 * @param the_username The username of the current user.
	 * @param the_paper_title The title of the paper to be deleted.
	 * @author Aaron
	 */
	public void deletePaper(final Conference the_conference, final String the_username, final String the_paper_title){
		//		Remove this paper from the database
		
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
	 * @param the_conference the conference the reviews belongs to
	 * @param the_username the author of the paper whose reviews are being deleted
	 * @param the_paper_title the name of the paper whose reviews are being deleted
	 */
	private void deleteReviews(final Conference the_conference, final String the_username, final String the_paper_title){
		//		deletes reviews associated to the paper passed in
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
	 * @param the_conference the conference the recommendations belongs to
	 * @param the_username the author of the paper whose recommendations are being deleted
	 * @param the_paper_title the name of the paper whose recommendations are being deleted
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
	 * Creates a new review.
	 * 
	 * @param the_reviewer_username The username of the reviewer.
	 * @param the_conf The conference object.
	 * @param the_paper The name of the paper the review is for.
	 * @param the_paper_author The author of the paper being reviewed.
	 * @param the_comments_to_subpc The comments to the sub program chair.
	 * @param the_answersRadioBtn The answers to the review questions.
	 * @param the_summary_comments The comments on the paper. 
	 */
	public void createNewReview(final String the_reviewer_username, final Conference the_conf, 
			final String the_paper, final String the_paper_author, final String the_comments_to_subpc, 
			final int[] the_answersRadioBtn, final String the_summary_comments){
		//		add these elements to the database as one single review item
		
		int paperId = -1;
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT id FROM papers WHERE name='" +
					the_paper + "' AND confname='" + the_conf.getConfTitle() + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				paperId = resultSet.getInt(1);
			}
			System.out.println(paperId);
			
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

	/**
	 * 
	 * @author David
	 * @param the_conf
	 * @param the_paper
	 * @param the_username
	 * @return
	 */
	public boolean canAddReview(final Conference the_conf, final String the_paper, final String the_username){
		boolean permission_to_add = true;
		List<String> al = new ArrayList<String>();
		//TODO: the GUI will call this method to verify that there aren't already 4 reviews and that the user
		//		attempting to submit the review hasn't already submitted one.  If there are already 4 reviews,
		//		or the user has already submitted one, return false.
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
		
		if(al.size()>4 || al.contains(the_username)) {
			permission_to_add = false;
		}
		return permission_to_add;
	}
	
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
	 * Used by another user to see the author of the paper that they are examining.  Returns blank string
	 * if nothing was found.
	 * 
	 * @param the_conf The conference that the paper is a part of
	 * @param the_paper The name of the paper the current user is looking at (not the author of the paper)
	 * @return The username of the author of the paper
	 */
	public String getPaperAuthor(final Conference the_conf, final String the_paper){
		String username = "";
		//		the GUI will call this method when another user (administrator of some sort) needs to review
		//		or recommend a paper.  They will be the current user and will need to retrieve the username
		//		of the person who Authored the paper.  (Jacob)
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
	 * the paper passed in
	 * 
	 * @param the_sub_pc_username the subprogramchair recommending the paper
	 * @param current_conf the conference the paper being recommended belongs to
	 * @param current_paper_being_recommended the paper that is being recommended
	 * @param the_paper_author the author of the paper being recommended
	 * @param the_numerical_value the grade the subprogramchair gave the paper
	 * @param the_rational_for_recommendation the rationale behind the subprogramchairs grade
	 */
	public void addPaperRecommendation(final String the_sub_pc_username, final Conference current_conf, 
			final String current_paper_being_recommended, final String the_paper_author, final int the_numerical_value,
			final String the_rational_for_recommendation){
		//		the MakeRecommendationGUI will call this when it needs to add a single recommendation to a paper.  There should
		//		only be one recommendation per paper.  Right now, I'm going on the assumption that if a Sub-PC goes back in
		//		to submit a recommendation again, they will just be re-writing over a previous recommendation.  If you want to
		//		handle this differently, let me know because I'll have to insert a try/catch and print out the exception message
		//		to let them know they can only make a recommendation once. Add this info to the database linked to the paper
		//		it's associated with. (Jacob)	
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
		//This either if the rec exists update it or if not add it
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
	 * @param the_conf the conference the recommendation belongs to
	 * @param the_paper the paper the recommendation belongs to
	 * @return the recommendation of the paper being examined
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
					result = resultSet.getString(8);			// Location of the rationale in the DatabaseS
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
	 * for the_conf passed in. 
	 * 
	 * @param the_conf the conference the paper is in whose grade is being retrieved
	 * @param the_paper the paper whose grade is being retrieved
	 * @return the numerical grade of the paper
	 */
	public int getPaperRecommendationNumericalValuefinal(final Conference the_conf, final String the_paper){
		
		//		the GUI needs to be able to retrieve the SubPC's recommendation for a paper
		//		return the numerical value associated with the 5 JRadioButtons for the recommendation level. (Jacob)
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
	 * @param the_conf the conference the paper whose recommendation is being retrieved
	 * @param the_paper the paper whose recommendation is being retrieved
	 * @return the SubProgramChair's username
	 */
	public String getPaperRecommendationSubPCName(final Conference the_conf, final String the_paper){
		//		the GUI needs to be able to retrieve the SubPC's recommendation for a paper
		//		return the username of the SubPC that made the recommendation.
		//		maybe this "getter" already exists in some form?  if so, just let me know and I'll adjust (Jacob)
		
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
	
	/*
	 * This method returns all of the userNames of the reviewers that are assigned to a particular
	 * paper. The length of the array indicates how many are assigned (i.e. 0 = no reviewers). This
	 * method also prints to the console if the paper passed in has no association to the conference
	 * passed in.
	 */
	/**
	 * This method returns all of the userNames of the reviewers that are assigned to a particular
	 * paper. The length of the array indicates how many are assigned (i.e. 0 = no reviewers). This
	 * method also prints to the console if the paper passed in has no association to the conference
	 * passed in.
	 * 
	 * @param the_conference the conference the paper belongs to
	 * @param current_paper the paper whose reviewers are being retrieved
	 * @return an array of reviewers of the current paper
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
	 * @param the_conference the conference the returned PC is from
	 * @param current_paper a paper from the conference the PC is from
	 * @return the username of the PC
	 */
	//paper does not need to be passed in unless you for some reason need to match the 
	//paper conf to the conf that was passed in
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
	
	/**
	 * Retrieves the SubProgramChair of the paper matching the paperid passed in for the conference passed in.
	 * 
	 * @param the_conference the conference being examined
	 * @param current_paper the paper being examined
	 * @return the SubProgramChair's username
	 */
	public String getUserAssignedAsSubPC(final Conference the_conference, final int current_paper){
		//	 return the username of the person assigned as Sub PC for a particular paper
		
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
			System.out.println("Get subpc grade failed!");
		}
		return subPC;
	}
	
	
	/**
	 * Method that returns all of the available reviewers for a given paper.
	 * This method follows the business rules:
	 * 			- the reviewer can't be the author of the paper.
	 *			- the reviewer can't be the person assigning the review, unless they're the PC.
	 *			- the reviewer in this list can't be one of the reviewers already assigned to this paper (they can't be assigned twice)
	 *			- TODO: May need to insert more business rules
	 * 
	 * @param the_conference the conference the paper being looked at belongs to
	 * @param the_paper the paper being checked for available reviewers
	 * @param the_person_assigning the subpc/pc assigning the reviewers to the paper
	 * @return an array of available users who can review this paper following th business rules
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
	
	
	/**
	 * Adds the reviewers to the paper.
	 * 
	 * @param the_conference The conference the paper is in.
	 * @param the_paper The paper the reviewers are reviewing.
	 * @param the_reviewers The users who are reviewing the paper.
	 * @author Aaron
	 */
	public void addReviewers(final Conference the_conference, final String the_paper, final String[] the_reviewers){
		//Gets the correct paperId and paperAuthor for the paper.
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
	/**
	 * @author David
	 * @param current_conf
	 * @param current_paper
	 * @param the_pc
	 * @return
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
			  
		    	for (int i = 1; i <= numberOfColumns; i++) {
		    		if (i > 1) System.out.print(",  ");
		    		String columnName = rsmd.getColumnName(i);
		    		System.out.print(columnName);
		    	}
		    	System.out.println("");
		    	
		    	while (resultSet.next()) {
	                ArrayList<String> record = new ArrayList<String>();
		            for (int i = 1; i <= numberOfColumns; i++) {
		            	if (i > 1) System.out.print(",  ");
		            	String columnValue = resultSet.getString(i);
                        record.add(columnValue);
		            	System.out.print(columnValue);
                }
                String value = infoForAUser(record);
                al.add(value); 
		            System.out.println("");
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
//	private String getUserIdForPC(Conference current_conf) {
//		String pc = "";
//		try {
//			
//			PreparedStatement statement = connect.prepareStatement(
//					"SELECT PROGCHAIR FROM conferences WHERE name=" +"'" + current_conf.getConfTitle() +"'");
//			resultSet = statement.executeQuery();
//			
//			if (resultSet.next()) {
//				pc = resultSet.getString(1);
//			}
//			
//		} catch (Exception e) {
//			System.out.println("Get full name failed!");
//		}
//		return pc;
//	}
	
	/**
	 * 
	 * @param the_paper_id
	 * @return
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
	 * 
	 * @param the_conference
	 * @return
	 */
	private String infoForAUser(ArrayList<String> the_conference) {
		String conference = the_conference.get(0);
		return conference;
	}

	/**
	 * Assigned the SubProgramChair username passed in to the paper that was passed in for the conference passed in.
	 * 
	 * @param the_conference the conference the paper belongs to
	 * @param the_paper the paper beings assigned to a SubProgramChair
	 * @param the_sub_pc the username of the SubProgramChair
	 */
	
	public void addSubPC(final Conference the_conference, final String the_paper, final String the_sub_pc){
		//		add this person as the SubPC.  We've already populated the list of potential with the correct people
		//		so we shouldn't have to do any checking here.  The AssignSubPCGUI will ensure a non-null result is sent.
		//		(Jacob)
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
	 * @param the_conference the conference the paper belongs to
	 * @param the_paper the paper whose reviews are being retrieved
	 * @return An array of Review objects of the given paper.
	 */
	public Review[] getReviews(final Conference the_conference, final String the_paper){
		//		the ManagePaperGUI needs all the reviews that have been completed for a paper (if any)
		//		I created a Review object so that the controller can pass back an array of Review objects
		//		all at once.  No more than 4 are allowed to be created, so that shouldn't have to be a check here.
		int paperID = -1;
		int index = 0;
		Review[] reviews = new Review[4];
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
						the_paper, resultSet.getString(5), resultSet.getString(18), 
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
	 * Returns an array of Conference objects that the user that is passed in belongs too.
	 * 
	 * @param the_username the username of the person being looked at
	 * @return An array of conferences that the user is a part of.
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
	
	/**
	 * Gets all conferences that are upcoming but not in myConferences.
	 * 
	 * @param the_username The username of the current user.
	 * @return An array of Conferences that contains all upcoming conferences.
	 * @author Aaron
	 * @throws ParseException 
	 */
	@SuppressWarnings("deprecation")
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
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private String infoForAPaper(
			ArrayList<String> record) {
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
	 * Returns an array of Papers that are in the conference passed in and are associated in some way with the user.
	 * 
	 * @param the_conf the conference whose papers are being examined
	 * @param the_username the username of the person who is associated with the returned papers
	 * @return An array of Papers from the conference that the user takes part in
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
	
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
	    	} catch (Exception e) {

	    }
	}

	/**
	 * 
	 * @param current_paper
	 * @return
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
	
	private void createPaperObjects(List<Paper> the_paper_list, final Conference the_conference, 
			final String the_username, final ResultSet rs) {

		Paper paper = null;
		
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
			
			} while (rs.next());
			
		} catch (Exception e) {
			System.out.println("createPaperObject failed!");
		}
	}
	
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

	
public static void main(String args[]) throws ParseException, SQLException {
		
		Controller controller = new Controller();
		ManageDatabase md = new ManageDatabase();
		
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		
		/*
		int buttons[] = {9, 9, 9, 9, 9, 9, 9, 9, 9, 9};
		controller.createNewReview("warfeld", testConference, "Baking Pi", "typow", "balls", 
				buttons, "please work");*/
		
		
		
		/*
		Paper papers[] = controller.getMyPapers(testConference, "thor");
		
		for (int i = 0; i < papers.length; i++) {
			System.out.println(papers[i].getPaperTitle());
			System.out.println(papers[i].getSubPC());
		}*/
		/*
		paperRelation relation = controller.getRelationToPaper(testConference, "Baking Pi", "warfeld");
		System.out.println(relation);*/
		
		try {
			controller.deletePaper(testConference, "typow", "Baking Pi");
			controller.createNewPaper(testConference, "Ranger", "fun times", "", 
					paperStatusAuthorViewable.SUBMITTED, paperStatusAdminViewable.ACCEPTED);
			controller.createNewPaper(testConference, "Fungus", "sad times", "", 
					paperStatusAuthorViewable.SUBMITTED, paperStatusAdminViewable.ACCEPTED);
		} catch (Exception e) {
			 //Auto-generated catch block
			e.printStackTrace();
		}
		
		md.printDatabase();
		
		/*
		Controller controller = new Controller();
		Conference[] conn = controller.getUpcommingConferences("typow");
		boolean c = controller.canAddReview(conn[0],"Baking Pi","yellow");
		*/
		
//		for (int i = 0; i < c.length; i++) {
//			System.out.println(c[i].getConfTitle());
//		}
		/*
		Controller controller = new Controller();
		Conference[] conn = controller.getMyConferences("warfeld");
		
		for (int i = 0; i < conn.length; i++) {
			System.out.println(conn[i].getConfTitle());
		}*/
		
		
		//ManageDatabase md = new ManageDatabase();
		//md.printDatabase();
		/*
		ManageDatabase md = new ManageDatabase();
		md.printDatabase();;
		
		/*
		Conference testConference1 = new Conference("Small Computer conferences", "PC", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		*/
		
	
		//Conference[] c = controller.getMyConferences("typow");
		
		
		
		
		
//		int buttons[] = new int[10];
//		for (int i = 0; i < 10; i++)
//		{
//			buttons[i] = i;
//		}
//		
//		controller.createNewReview("Obama", conn[0], "Baking Pi", "Michael Phelps", "Lame", buttons, "Lamer");
	}
}
