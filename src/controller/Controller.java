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
import java.text.DateFormat;
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


/**
 * The Controller handle all business logic and act as an interface between the GUI
 * and the database.
 * 
 * @author Jacob Hall
 * @version 90 Date: 11/27/13
 */
public class Controller extends Observable{
	
	/*
	 * The connection the controller has to the Database.
	 * This connection is set up during construction.
	 */
	private Connection connect = null;
	
	/*
	 * The statement is the String that is fed to the database 
	 * to issue SQL commands.
	 */
	private Statement statement = null;
	
	/*
	 * The resultSet is what is returned from the database
	 * after the statement has been sent.
	 */
	private ResultSet resultSet = null;

	/*
	 * The current state (mode of operation) the GUI is currently in.
	 * @see StateOfGUI
	 */
	private StateOfGUI state;
	
	/*
	 * The current user logged in.
	 */
	private String current_user = "";
	
	/*
	 * The current conference (if any) that is being evaluated by the user 
	 */
	private Conference current_conference;
	
	/*
	 * The current paper (if any) that is being evaluated by the user
	 */
	private static String current_paper = "";
	
	/*
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
		      System.out.println("Success!! Lets eat Cake!!");
		      
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
		
		//TODO: add some logic:   if (!the_state.equals(state){
		//		if you're already in the GUI, you shouldn't be able to go back to the 
		//		same GUI.  The next state should not equal the current one.
		//		However, maybe a GUI should be able to "refresh" if new data is supposed
		//		to be populated to a table or something like that?   Jacob
		// UNTESTED!!!
		
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
	public Boolean checkValidUsername(final String the_username)
	{
		Boolean valid = false;
		//TODO: check the username against database to see if this username
		//		already exists in the database.  Jacob
		//TODO: partially tested
		
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
		Boolean valid = true;
		//TODO: check the username/password combo against the database to see if this 
		//		username and password combo are valid.  Jacob
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
					the_middle_name + "', '" + the_last_name + "', '" + the_specialty + "')");
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
	 * @param the_username
	 */
	public void setCurrentUsername(final String the_username){
		current_user = the_username;
	}
	
	/**
	 * Returns the username of the current person logged in.
	 * @return current_user The current user
	 */
	public String getCurrentUsername(){
		return current_user;
	}
	
	/**
	 * Returns the full name of the individual when the username is passed in
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
		//TODO: check the conference title against database to see if this conference title
		//		already exists in the database.  Jacob
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//NOTE..........................
		//TODO: this method prints out the stack trace no matter what.  If the title exists or doesn't.
		//(TYLER) i changed conferences to conference in the statement this should fix it if it does fix spacing
		try {
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM conference WHERE name='" + the_conference_title + "'");
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
					"INSERT INTO conferences VALUES ('" + the_conference.getConfTitle() + "', '" + the_conference.getProgramChair() + "', '" +
							the_conference.getConfDate().toLocaleString() + "', '" + the_conference.getSubmissionDead().toLocaleString() + "', '" + the_conference.getReviewDead().toLocaleString() + "', '" + 
							the_conference.getSubPCReccomendDead().toLocaleString() + "', '" + the_conference.getAuthorNotificationDead().toLocaleString() + "', '" + the_conference.getConfSummary() + "')");
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
		//TODO: The GUI will need to be able to update the current conference being looked at by the user
		//		The controller will need to update the current_conference in this class to reflect that so
		//		that the GUI can call and retrieve the conference information for display.  Jacob
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
			
			PreparedStatement statement = connect.prepareStatement("SELECT ID FROM papers");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				total+=1;
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
		//TODO: add the user viewable status and the admin viewable status.  These are already set up in the GUIEnum class.
		//		We need two because the user should only see a general indication of the progress and the admin needs to see
		//		a detailed status update according to deadlines and where it's at in the whole review process. (Jacob)
		if(i<4) {
			try {			
				PreparedStatement statement = connect.prepareStatement("INSERT INTO papers (ID,  AUTHOR,  " +
						"NAME,  TEXT,  CONFNAME) VALUES (" + total + ", '" + the_username + "', '" +
								the_paper_title + "', '" + the_file_submitted + "', '" +
								the_conference.getConfTitle() + "')");
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
	 * 
	 * @param the_conference The conference 
	 * @param the_paper_title
	 * @param the_username
	 * @return
	 */
	public paperRelation getRelationToPaper(final Conference the_conference, final String the_paper_title, 
			final String the_username){
		//TODO: I'm not sure how the database is tracking this, but the GUI's need to be able to retrieve
		//		the user's relationship to a paper.  If they are the AUTHOR, REVIEWER, .....  (Jacob)
		//		Tracking this right now is clumsy but doable go through the the conf and check if username
		//		the same as the PC if so hes the PC if not check if hes author of the paper if not check if
		//		they are a reviewer from the review table if not check if they are subprog from rec table

		
			//temporary:
			paperRelation relation = paperRelation.AUTHOR;
		return relation;
	}
	 
	
	public void setPaperRelation(final Conference the_conference, final String the_paper_title, 
			final String the_username, final paperRelation the_relation){
		//TODO: The GUI needs to be able to set a person's relation to a paper to be later queried.  (Jacob)
		//kind of the same idea as getRelationToPaper()
		
		
		
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
		
		//TODO: this is a generic update of the Paper status for both author viewable and admin viewable called at different
		//		points in the program when reviews are submitted and so on.
		
		try {
			PreparedStatement statement = connect.prepareStatement("UPDATE papers SET status='" + the_author_viewable_status + "', " +
					"adminstat='" + the_admin_viewable_status + "' WHERE name='" + the_paper_title + "'");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error setting paper status." + e.getMessage());
		}
		
		
	}
	
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
			System.out.println("paperStatusAdminViewable failed!");
		}
		
		if (adminStatus.equals(null)) {
			System.out.println("paperStatusAdminViewable failed!");
		}
		
		return adminStatus;
	}
	
	/*
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
		
		if (authorStatus.equals(null)) {
			System.out.println("paperStatusAuthorViewable failed!");
		}
		
		return authorStatus;
	}
	
	public void setCurrentPaper(final String the_paper){
		current_paper = the_paper;
	}
	
	public String getCurrentPaper(){
		return current_paper;
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
		//TODO: Remove this paper from the database
		
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DELETE FROM papers WHERE name='" + the_paper_title + "'");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error deleting paper." + e.getMessage());
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
		//TODO: add these elements to the database as one single review item
		
		int paperId = 0;
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT id FROM papers WHERE name='" + the_paper + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				paperId = Integer.parseInt(resultSet.getString(1));
			}
			System.out.println(paperId);
			
		} catch (SQLException e) {
			System.out.println("Error creating new review." + e.getMessage());
		}
		
		try {
			PreparedStatement statement = connect.prepareStatement("INSERT INTO reviews VALUES (1, " + paperId + ", " +
					"'" + the_reviewer_username + "', '" + the_conf.getConfTitle() + "', '" + the_paper + "', " +
				    "'" + the_paper_author + "', " + the_answersRadioBtn[0] + ", " + the_answersRadioBtn[1] + ", " +
				    the_answersRadioBtn[2] + ", " + the_answersRadioBtn[3] + ", " + the_answersRadioBtn[4] + ", " +
				    the_answersRadioBtn[5] + ", " + the_answersRadioBtn[6] + ", " + the_answersRadioBtn[7] + ", " +
				    the_answersRadioBtn[8] + ", " + the_answersRadioBtn[9] + ", '" + the_summary_comments + "')");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error creating new review part 2." + e.getMessage());
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
		//TODO: the GUI will call this method to verify that there aren't already 4 reviews and that the user
		//		attempting to submit the review hasn't already submitted one.  If there are already 4 reviews,
		//		or the user has already submitted one, return false.
		return permission_to_add;
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
				username += resultSet.getString(2);
			}
			
		} catch (Exception e) {
			System.out.println("Get full name failed!");
		}
		
		return username;
	}
	
	/**
	 * 
	 * @param the_sub_pc_username
	 * @param current_conf
	 * @param current_paper_being_recommended
	 * @param the_paper_author
	 * @param the_numerical_value
	 * @param the_rational_for_recommendation
	 */
	public void addPaperRecommendation(final String the_sub_pc_username, final Conference current_conf, 
			final String current_paper_being_recommended, final String the_paper_author, final int the_numerical_value,
			final String the_rational_for_recommendation){
		//TODO: the MakeRecommendationGUI will call this when it needs to add a single recommendation to a paper.  There should
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
			} else {
				statement = connect.prepareStatement("INSERT INTO recommendations VALUES (" + paperID + ", " +
						paperID + ", '" + the_sub_pc_username + "', '" + current_conf.getConfTitle() +
						"', '" + current_paper_being_recommended + "', '" + the_paper_author + "', " +
						the_numerical_value + ", '" + the_rational_for_recommendation + "')");
			}
		} catch(Exception e) {
			e.getStackTrace();
			System.out.println("Failed to just addREC");
		}
	}
	
	/*
	 * Retrieves the SubPC's recommendation for a paper from the database
	 * given a conference and a paper within that conference. If the paper does
	 * not exist in that conference the sentinel value paperID = -1 and it returns
	 * a blank string for the rationale.
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
				statement = connect.prepareStatement(
						"SELECT * FROM recommendations WHERE paperid= " + paperID);
				resultSet = statement.executeQuery();
				result = resultSet.getString(8);			// Location of the rationale in the Database
			} else {
				System.out.println("Requested Paper doesn't exist in that conference");
			}
			
		} catch (Exception e) {
			System.out.println("getPaperRecommendationRationale failed!");
		}
		
		return result;
	}

	
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
	
	public String getPaperRecommendationSubPCName(final Conference the_conf, final String the_paper){
		//TODO: the GUI needs to be able to retrieve the SubPC's recommendation for a paper
		//		return the username of the SubPC that made the recommendation.
		//		maybe this "getter" already exists in some form?  if so, just let me know and I'll adjust (Jacob)
		
		//temporary
		String temp = "Clayton";
		return temp;
	}
	
	/*
	 * This method returns all of the userNames of the reviewers that are assigned to a particular
	 * paper. The length of the array indicates how many are assigned (i.e. 0 = no reviewers). This
	 * method also prints to the console if the paper passed in has no association to the conference
	 * passed in.
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
	
	/*
	 * Method that returns the PC for a given conference.
	 * This method returns null if the conference can't be found in the database.
	 */
	//TODO: Check paperID
	public String getUserAssignedAsPC(final Conference the_conference, final int current_paper){
		
		String result = null;
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT progchair FROM conference WHERE name=" +"'" + the_conference.getConfTitle() +"'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				result = resultSet.getString(1);		
			}	
			
			
		} catch (Exception e) {
			System.out.println("getUserAssignedAsPC failed!");
		}
		
		return result;
	}
	
	public String getUserAssignedAsSubPC(final Conference the_conference, final int current_paper){
		//TODO: return the username of the person assigned as Sub PC for a particular paper
		String subPC = null;
		try {
			PreparedStatement statement = connect.prepareStatement(
					"SELECT subchair FROM recommendations WHERE conference=" +"'" + the_conference.getConfTitle() + 
					"' AND paperid='" + current_paper + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				subPC = resultSet.getString(1);
			}
			
		} catch (Exception e) {
			System.out.println("Get subpc grade failed!");
		}
		return subPC;
	}
	
	/*
	 * Method that returns all of the available reviewers for a given paper.
	 * This method follows the business rules:
	 * 			- the reviewer can't be the author of the paper.
			  	- the reviewer can't be the person assigning the review, unless they're the PC.
				- the reviewer in this list can't be one of the reviewers already assigned to this paper (they can't be assigned twice)
				- TODO: May need to insert more business rules
	 * 
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
					
		return (String[]) result.toArray();
	}
	
	public void addReviewers(final Conference the_conference, final String the_paper, final String[] the_reviewers){
		//TODO: add these reviewers to the database as assigned to the_paper in the_conference.
		//		The AssignReviewerGUI is checking to make sure how many reviewers are already assigned to the paper
		//		it is also checking (along with your help in the getAvailableReviewers method) to make sure that the
		//		reviewers assigned here are valid; not violating business rules.  Therefore, you should be able to just
		//		add these names in without checking, unless I'm missing something.  Let me know if I need to change how
		//		this is being handled.  (Jacob)
	}
	/**
	 * @author David
	 * @param current_conf
	 * @param current_paper
	 * @param the_pc
	 * @return
	 */
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
	
	private String infoForAUser(ArrayList<String> the_conference) {
		String conference = the_conference.get(0);
		return conference;
	}


	public void addSubPC(final Conference the_conference, final String the_paper, final String the_sub_pc){
		//TODO: add this person as the SubPC.  We've already populated the list of potential with the correct people
		//		so we shouldn't have to do any checking here.  The AssignSubPCGUI will ensure a non-null result is sent.
		//		(Jacob)
		int total = 0;
		try {
			
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM recommendations");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				total+=1;
			}
			
		} catch (SQLException e) {
			System.out.println("Check for valid Username failed");
			e.printStackTrace();
		}
		int id = getPaperID(the_paper);
		String author = getPaperAuthor(the_conference, the_paper);
		try {
			
			PreparedStatement statement = connect.prepareStatement("INSERT INTO recommendations(id, paperid, subchair, conference, papername, paperauthor)" +
					" VALUE("+total+id+"'"+the_sub_pc+"'"+"'"+the_conference.getConfTitle()+"'"+the_paper+"'"+author+"')");
			resultSet = statement.executeQuery();
			
		} catch (Exception e) {
			System.out.println("Get full name failed!");
		}
		
	}
	
	public Review[] getReviews(final Conference the_conference, final String the_paper){
		//TODO: the ManagePaperGUI needs all the reviews that have been completed for a paper (if any)
		//		I created a Review object so that the controller can pass back an array of Review objects
		//		all at once.  No more than 4 are allowed to be created, so that shouldn't have to be a check here.
		
		
						//temporary:
						Review[] temp = new Review[4];
						temp[0] = new Review("Halmus", current_conference, "the_username", "the_paper", "the_paper_author", "the_comments_to_subpc", new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5}, "the_summary_comments");
						temp[1] = new Review("Bob", current_conference, "trees", "why?", "joe", "i ate the whole thing", new int[]{2, 3, 4, 5, 1, 2, 3, 4, 5, 1}, "no");
						temp[2] = new Review("Hank", current_conference, "grass", "why not?", "sally", "you did not", new int[]{4, 2, 5, 1, 2, 3, 3, 2, 5, 5}, "yes");
						temp[3] = new Review("Bob", current_conference, "leaves", "Beause?", "paula", "I did to", new int[]{3, 4, 4, 5, 4, 3, 4, 5, 5, 5}, "What General Weygand called the Battle of France is over. I expect that the Battle of Britain is about to begin. Upon this battle depends the survival of Christian civilization. Upon it depends our own British life, and the long continuity of our institutions and our Empire. The whole fury and might of the enemy must very soon be turned on us. Hitler knows that he will have to break us in this Island or lose the war. If we can stand up to him, all Europe may be free and the life of the world may move forward into broad, sunlit uplands. But if we fail, then the whole world, including the United States, including all that we have known and cared for, will sink into the abyss of a new Dark Age made more sinister, and perhaps more protracted, by the lights of perverted science. Let us therefore brace ourselves to our duties, and so bear ourselves that if the British Empire and its Commonwealth last for a thousand years, men will still say, 'This was their finest hour.'");
		return temp;
	}
	
	public Conference[] getMyConferences(final String the_username){
	//TODO: do this....
		//temporary
		Conference[] the_conf_array = new Conference[1];
		return the_conf_array;
	}
	
	public Conference[] getUpcommingConferences() throws ParseException{
	//TODO: 
    	ArrayList<Conference> al = new ArrayList<Conference>();
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM conferences WHERE NOTIFYDATE >= '"+new Date().toLocaleString()+"'");
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
	                Conference value = infoForAConference(record);
	                al.add(value); 
	        }    
	    	while (resultSet.next()) {
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	if (i > 1) System.out.print(",  ");
	            	String columnValue = resultSet.getString(i);
	            	System.out.print(columnValue);
	            }
	            System.out.println("");  
	        }
		} catch (SQLException e) {
			System.out.println("Check for valid conference name failed");
			e.printStackTrace();
		}
		Conference[] the_conf_array;
		if(al.size()>0) {
			the_conf_array = new Conference[al.size()];
			for(int i=0;i<al.size();i++){
				the_conf_array[i]=al.get(i);
			}
		} else {
			the_conf_array = new Conference[1];
			the_conf_array[0] = null;
		}

		return the_conf_array;
	}
	private Conference infoForAConference(
			ArrayList<String> record) throws ParseException {
		DateFormat formatter; 
		Date conferencedate; 
		Date paperSubmissionDate; 
		Date reviewPaperDate; 
		Date recommendationPaperDate; 
		Date notificationDate; 
		formatter = new SimpleDateFormat("yy-MM-dd");
		String conferenceName = record.get(0);
		String programChair = record.get(1);
		conferencedate = formatter.parse(record.get(2));
		paperSubmissionDate = formatter.parse(record.get(2));
		reviewPaperDate = formatter.parse(record.get(2));
		recommendationPaperDate = formatter.parse(record.get(2));
		notificationDate = formatter.parse(record.get(2));
		String summary = record.get(7);

		
		Conference conference = new Conference(conferenceName, programChair, conferencedate, current_user, current_user, current_user, current_user, paperSubmissionDate, reviewPaperDate, recommendationPaperDate, notificationDate, summary);
		return conference;
	}
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

	public String[] getMyPapers(final String the_conf, final String the_username){
	//TODO: A GUI is going to need to get a string of paper titles that they are associated
	//		with given a specific conference;
		ArrayList<String> al = new ArrayList<String>();
		try {			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM papers WHERE confname=" +"'" + the_conf +"' AND author='" +
							the_username + "'");
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
	                String value = infoForAPaper(record);
	                al.add(value);
	        }    
	    	while (resultSet.next()) {
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	if (i > 1) System.out.print(",  ");
	            	String columnValue = resultSet.getString(i);
	            	System.out.print(columnValue);
	            }
	            System.out.println("");  
	        }
		} catch (Exception e) {
			System.out.println("Get full name failed!");
		}
		
		String[] papers = new String[al.size()];
		if(al.size()>0) {
			papers = new String[al.size()];
			for(int i=0;i<al.size();i++){
				papers[i]=al.get(i);
			}
		} else {
			papers = new String[1];
			papers[0] = null;
		}
		return papers;
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

	public int getPaperID(String current_paper) {
		int paper = 0;
		try {
			
			PreparedStatement statement = connect.prepareStatement("SELECT id FROM papers where NAME='"+current_paper+"'");
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
	
	public static void main(String args[]) throws ParseException {
		Controller controller = new Controller();
		Conference[] conn = controller.getUpcommingConferences();
		controller.getAvailableForSubPC(conn[1], 2, "Tyler Powers");
		controller.getMyPapers("TestTest", "Test username");
		controller.getPaperID("Packing on Abs");
		controller.setPaperStatus(conn[0], "Packing on Abs", paperStatusAuthorViewable.ACCEPTED, paperStatusAdminViewable.OVERDUE_FOR_RECOMMEND);
//		int buttons[] = new int[10];
//		for (int i = 0; i < 10; i++)
//		{
//			buttons[i] = i;
//		}
//		
//		controller.createNewReview("Obama", conn[0], "Baking Pi", "Michael Phelps", "Lame", buttons, "Lamer");
	}


	
}
