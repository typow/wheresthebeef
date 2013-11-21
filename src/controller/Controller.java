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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import view.GUIEnum.StateOfGUI;


/**
 * The Controller handle all business logic and act as an interface between the GUI
 * and the database.
 * 
 * @author Jacob Hall
 * @version 11/10/13
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
	private String current_paper = "";
	
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
	 * @param the_conference_title the title of conference
	 * @return Returns True if the conference title exists in database
	 * 			Returns False if the title is not in the database
	 */
	public Boolean checkConferenceExists(final String the_conference_title) {
		Boolean valid = false;
		//TODO: check the conference title against database to see if this conference title
		//		already exists in the database.  Jacob
		
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
	 * @param the_conference the current conference object that is being created
	 */
	public void createNewConference(final Conference the_conference){
		listOfAllConferences = new ArrayList<Conference>();
		try {
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO conference VALUES ('" + the_conference.getConfTitle() + "', '" + the_conference.getProgramChair() + "', '" +
							the_conference.getConfDate() + "', '" + the_conference.getSubmissionDead() + "', '" + the_conference.getReviewDead() + "', '" + 
							the_conference.getSubPCReccomendDead() + "', '" + the_conference.getAuthorNotificationDead()+ "', '" + the_conference.getConfSummary() + "')");
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
		
	public void createNewPaper(final Conference the_conference, final String the_username, final String the_paper_title, 
			final String the_file_submitted) throws Exception{
		//TODO: add the paper and info related to it to the database.  Note:  this is called in the GUI in a try-catch statement
		//		Return Exception "Author cannot submit more than 4 papers to a single conference." 
		//		implement the Logic to prevent that here.  The GUI will print out the Exception string in a JDialog box.
		try {			
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO paper VALUES ('" + 1 + "', '" + the_username + "', '" +
							the_paper_title + "', '" + the_file_submitted + "')");
			statement.execute();
			System.out.println(the_paper_title + " Successfully added paper");
		} catch (SQLException e) {
			System.out.println("Check for valid paper failed");
			e.printStackTrace();
		}
//		if (number of papers in the conference == 4){
//			throw new Exception("Author cannot submit more than 4 papers to a single conference.");
//		}
//		else{
//			add the paper to the database.
//		}
		setCurrentPaper(the_paper_title);
	}
	
	public void setCurrentPaper(final String the_paper){
		current_paper = the_paper;
	}
	
	public String getCurrentPaper(){
		return current_paper;
	}
	
	public void deletePaper(final Conference the_conference, final String the_username, final String the_paper_title){
		//TODO: Remove this paper from the database
	}
	
	public void createNewReview(final String the_reviewer_name, final Conference the_conf, 
			final String the_username, final String the_paper, final String the_paper_author, final String the_comments_to_subpc, 
			final int[] the_answersRadioBtn, final String the_summary_comments){
		//TODO: add these elements to the database as one single review item
	}
	
	public boolean canAddReview(final Conference the_conf, final String the_paper, final String the_username){
		boolean permission_to_add = true;
		//TODO: the GUI will call this method to verify that there aren't already 4 reviews and that the user
		//		attempting to submit the review hasn't already submitted one.  If there are already 4 reviews,
		//		or the user has already submitted one, return false.
		return permission_to_add;
	}
	
	public String getPaperAuthor(final Conference the_conf, final String the_paper){
		String temp = "";
		//TODO: the GUI will call this method when another user (administrator of some sort) needs to review
		//		or recommend a paper.  They will be the current user and will need to retrieve the username
		//		of the person who Authored the paper.  (Jacob)
		return temp;
	}
	
	public void addPaperRecommendation(final String the_sub_pc_username, final Conference current_conf, 
			final String current_paper_being_recommended, final String the_paper_author, final int the_recommend_value,
			final String the_rational_for_recommendation){
		//TODO: the MakeRecommendationGUI will call this when it needs to add a single recommendation to a paper.  There should
		//		only be one recommendation per paper.  Right now, I'm going on the assumption that if a Sub-PC goes back in
		//		to submit a recommendation again, they will just be re-writing over a previous recommendation.  If you want to
		//		handle this differently, let me know because I'll have to insert a try/catch and print out the exception message
		//		to let them know they can only make a recommendation once. Add this info to the database linked to the paper
		//		it's associated with. (Jacob)			
	}
	
	public String[] getUsersAssignedAsReviewers(final Conference the_conference, final String current_paper){
		//TODO: the AssignReviewerGUI (and propably the ManagePaperGUI) will use this method to retrieve an
		//		array of usernames of the Reviewers assigned to a paper.  If there are none, return an empty array.
		//		the GUI will use that datastruct to make sure that no more than 4 reviewers are assigned to a paper
		//		already.  i.e. if 3 reviewers are returned here, 1 more can be added, no more.
		
				//temporary:
				String[] reviewers = new String[]{"chippy", "hippy", "bippy", "sippy"};
			
		
		return reviewers;
	}
	
	public String[] getAvailableReviewers(final String the_paper, final String the_person_assigning){
		//TODO: theAssignReviewerGUI needs an array of usernames of people that are capable of being a reviewer
		//		Note: - the reviewer can't be the author of the paper.
		//			  - the reviewer can't be the person assigning the review, unless they're the PC.
		//			  - the reviewer in this list can't be one of the reviewers already assigned to this paper (they can't be assigned twice)
		//			  - insert any other business rules here I'm missing
		//		return a string array and they'll be listed in the GUI to be picked from.
		
					//temporary:
					String[] reviewers = new String[]{"hank", "biff", "sally", "Mr Who Flung Dung", "I.P. Freely"};
		return reviewers;
	}
	
	public void addReviewers(final Conference the_conference, final String the_paper, final String[] the_reviewers){
		//TODO: add these reviewers to the database as assigned to the_paper in the_conference.
		//		The AssignReviewerGUI is checking to make sure how many reviewers are already assigned to the paper
		//		it is also checking (along with your help in the getAvailableReviewers method) to make sure that the
		//		reviewers assigned here are valid; not violating business rules.  Therefore, you should be able to just
		//		add these names in without checking, unless I'm missing something.  Let me know if I need to change how
		//		this is being handled.  (Jacob)
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

	/*
	public static void main(String args[]) {
		Controller controller = new Controller();
	}
	*/
}
