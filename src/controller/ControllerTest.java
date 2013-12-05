/**
 * 
 */
package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import view.GUIEnum.StateOfGUI;
import view.GUIEnum.paperStatusAdminViewable;
import view.GUIEnum.paperStatusAuthorViewable;
import database.ManageDatabase;


/**
 * @author Where's the Beef
 *
 */
public class ControllerTest  {
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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {     
		      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();	      	
		      connect = DriverManager.getConnection("jdbc:derby://localhost:1527/CMSDB;");		   
				assert(true);
		} catch(Exception e) {
			assert(false);
		}

	}
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}
	
	/**
	 * Resets all the tables in the database to predetermined
	 * reset values.
	 */
	public void resetDatabase() {
		ManageDatabase md = new ManageDatabase();
		md.fullReset(md);
	}

	/**
	 * Tests the construction of the controller.
	 */
	@Test
	public void testController() {
		Controller controller = new Controller();
		assertEquals(StateOfGUI.LOGIN, controller.getStateOfGUI());
	}

	/**
	 * Tests if GUI state can be set.
	 */
	@Test
	public void testSetStateOfGUI() {
		Controller controller = new Controller();
		controller.setStateOfGUI(StateOfGUI.ACCEPT_REJECT);
		assertEquals(StateOfGUI.ACCEPT_REJECT, controller.getStateOfGUI());
	}

	/**
	 * Tests if the getter for state of GUI works by retrieving the initial state.
	 */
	@Test
	public void testGetStateOfGUI() {
		Controller controller = new Controller();
		assertEquals(StateOfGUI.LOGIN, controller.getStateOfGUI());
	}

	/**
	 * Test for checkValidUsername()
	 */
	@Test
	public void testCheckValidUsername() {
		Controller controller = new Controller();
		resetDatabase();
		
		String username = "typow";  //Already in the DB we know its true
		boolean result = false;	
		result = controller.checkValidUsername(username);
		assertEquals(true, result);
		
		username = "hairyguy";  //Not in the DB we know it's false
		result = controller.checkValidUsername(username);
		assertEquals(false, result);
	}

	/**
	 * Test for checkValidUsernamePassword()
	 */
	@Test
	public void testCheckValidUsernamePassword() {
		Controller controller = new Controller();
		resetDatabase();
		
		String username = "typow";  //Already in the DB we know its true
		String password = "password";
		
		boolean result = false;
		result = controller.checkValidUsernamePassword(username, password);
		assertEquals(true, result);
		
		username = "hairyguy";  //Not in the DB we know it's false
		password = "hairyPassword";
		result = controller.checkValidUsername(username);
		assertEquals(false, result);
	}

	/**
	 * Test method for addNewUser()
	 */
	@Test
	public void testAddNewUser() {
		Controller controller = new Controller();
		resetDatabase();
		
		String username = "hairyguy";	// Not in the DB
		boolean result = true;
		result = controller.checkValidUsername(username);
		assertEquals(false, result);
		
		// Now Add user
		controller.addNewUser(username, "password", "Hairy", "E", "Guy", "");
		result = controller.checkValidUsername(username);
		assertEquals(true, result);
		
		// Clear the user from the DB
		resetDatabase();
	}

	/**
	 * Test method for setCurrentUsername()
	 */
	@Test
	public void testSetCurrentUsername() {
		Controller controller = new Controller();
		String username = "funkyChicken";
		
		// Original Username;
		assertEquals("", controller.getCurrentUsername());
		
		// After setting
		controller.setCurrentUsername(username);
		assertEquals("funkyChicken", controller.getCurrentUsername());
	}

	/**
	 * Test method for getCurrentUsername().
	 * Basically the same test as the setter.
	 */
	@Test
	public void testGetCurrentUsername() {
		Controller controller = new Controller();
		String username = "funkyChicken";
		
		// Original Username;
		assertEquals("", controller.getCurrentUsername());
		
		// After setting
		controller.setCurrentUsername(username);
		assertEquals("funkyChicken", controller.getCurrentUsername());
	}

	/**
	 * Test method for retrieving a user's full name from the 
	 * database given a valid username.
	 * TODO: Might need to change with the resetDatabase()
	 */
	@Test
	public void testGetFullName() {
		Controller controller = new Controller();
		String user = "sethk2";
		String result;
		
		if (!controller.checkValidUsername(user)) {
			controller.addNewUser
			(user, "pumpkins", "Seth", "D", "Kramer", "Testing");
		} 
		
		result = controller.getFullName(user);
		assertEquals("Seth D Kramer", result);
	}
	

	/**
	 * Tests to see if the conference already exists, database has conference a but not b. 
	 * Asserts that a is in database and b is not.
	 * Test method for {@link controller.Controller#checkConferenceExists(java.lang.String)}.
	 */
	@Test
	public void testCheckConferenceExists() {
		Controller controller = new Controller();

		Boolean valid1 = false;
		Boolean valid2 = false;

		//TODO: check the conference title against database to see if this conference title
		//		already exists in the database.  Jacob
		
		valid1 = controller.checkConferenceExists("a");
		valid2 = controller.checkConferenceExists("b");
		assertTrue(valid1 && !valid2);
	}

	/**
	 * tests to see if conference zz can be added to the database. 
	 * Test method for {@link controller.Controller#createNewConference(controller.Conference)}.
	 * @throws Exception
	 */
	@Test
	public void testCreateNewConference() throws Exception {
		Controller controller = new Controller();
		Conference the_conference = new Conference("zz", "Biff", new Date(), "3535 Wise street", 
				"Bremerton", "WA", "98311",
				new Date(), new Date(), new Date(), new Date(), "Junit Testing");
		try {
			controller.createNewConference(the_conference);
			assert true;
		} catch (Exception e) {
			assert false;
		}
	}

	/**
	 * Test method for setCurrentConference()
	 */
	@Test
	public void testSetCurrentConference() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getCurrentConference()}.
	 */
	@Test
	public void testGetCurrentConference() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#createNewPaper(controller.Conference, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public void testCreateNewPaper() throws Exception {
		Controller controller = new Controller();
		Conference the_conference = new Conference("zz", "Biff", new Date(), "3535 Wise street", 
				"Bremerton", "WA", "98311",
				new Date(), new Date(), new Date(), new Date(), "Junit Testing");
		 paperStatusAuthorViewable the_user_viewable_status = null;
		 paperStatusAdminViewable  the_admin_viewable_status = null;
		 
	    try {
	    	controller.createNewPaper(the_conference, "D-man", "JunitTest", "JunitTest home ", the_user_viewable_status, the_admin_viewable_status);
	    	assert true;
        } catch (Exception e) {
        	assert false;
        }
	}
	/**
	 * Test method for {@link controller.Controller#getRelationToPaper(controller.Conference, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetRelationToPaper() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#setPaperRelation(controller.Conference, java.lang.String, java.lang.String, view.GUIEnum.paperRelation)}.
	 */
	@Test
	public void testSetPaperRelation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#setPaperStatus(controller.Conference, java.lang.String, view.GUIEnum.paperStatusAuthorViewable, view.GUIEnum.paperStatusAdminViewable)}.
	 */
	@Test
	public void testSetPaperStatus() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getAdminPaperStatus(controller.Conference, java.lang.String)}.
	 */
	@Test
	public void testGetAdminPaperStatus() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getStatusAuthorView(controller.Conference, java.lang.String)}.
	 */
	@Test
	public void testGetStatusAuthorView() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#setCurrentPaper(java.lang.String)}.
	 */
	@Test
	public void testSetCurrentPaper() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getCurrentPaper()}.
	 */
	@Test
	public void testGetCurrentPaper() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link controller.Controller#getPaperFilePath(controller.Conference, java.lang.String)}.
	 */
	@Test
	public void testGetPaperFilePath() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link controller.Controller#deletePaper(controller.Conference, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testDeletePaper() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#createNewReview(java.lang.String, controller.Conference, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int[], java.lang.String)}.
	 */
	@Test
	public void testCreateNewReview() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#canAddReview(controller.Conference, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCanAddReview() {
		Controller controller = new Controller();
		Conference the_conference = new Conference("zz", "Biff", new Date(), "3535 Wise street", 
				"Bremerton", "WA", "98311",
				new Date(), new Date(), new Date(), new Date(), "Junit Testing");
		assertTrue(controller.canAddReview(the_conference, "junitTest", "USERNAME"));
	}

	/**
	 * Test method for {@link controller.Controller#getPaperAuthor(controller.Conference, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetPaperAuthor() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		if (!controller.checkConferenceExists("Small Computer Conference")) {
			controller.createNewConference(testConference);
		}
		String paper = "Baking Pi";
		String expectedAuthor = "Tyler Powers";
		String testedAuthor = "";
		try {
			testedAuthor = controller.getPaperAuthor(testConference, paper);
		} catch(Exception e) {
			fail("did not connect to database");
			e.getStackTrace();
		}
		assertEquals(expectedAuthor, testedAuthor);
		
		try {
			testedAuthor = controller.getPaperAuthor(testConference, "wrong paper title");
		} catch(Exception e) {
			fail("did not connect to database");
			e.getStackTrace();
		}
		assertEquals("", testedAuthor);
	}

	/**
	 * Test method for
	 */
	@Test
	public void testAddPaperRecommendation() {
		Controller controller = new Controller();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetPaperRecommendationRationale() {
		Controller controller = new Controller();
		Conference testConference = new Conference("TestTest", "PC", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		
		
		if (!controller.checkConferenceExists("TestTest")) {
			controller.createNewConference(testConference);
		} 
		
		try {
			controller.createNewPaper(testConference, "Test username", "Test PaperTitle", 
					"Test FileSubmittted", paperStatusAuthorViewable.SUBMITTED, 
					paperStatusAdminViewable.ACCEPTED);
		} catch (Exception e) {
			// Paper already exists
		}
		
		controller.addPaperRecommendation("Sub PC", testConference, 
				"Test PaperTitle", "Test Author", 5, "Test Recommendation");
		
		assertEquals("Test Recommendation", controller.getPaperRecommendationRationale(
				testConference, "Test PaperTitle"));
	}
	
	
	
	/**
	 * Test method for {@link controller.Controller#getPaperRecommendationNumericalValuefinal(controller.Conference, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetPaperRecommendationNumericalValuefinal() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		if (!controller.checkConferenceExists("Small Computer Conference")) {
			controller.createNewConference(testConference);
		}
		int expectedVal = 5;
	}
	
	/**
	 * Test method for {@link controller.Controller#getPaperRecommendationSubPCName(controller.Conference, java.lang.String)}.
	 */
	@Test
	public void testGetPaperRecommendationSubPCName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getUsersAssignedAsReviewers(controller.Conference, java.lang.String)}.
	 */
	@Test
	public void testGetUsersAssignedAsReviewers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getUserAssignedAsPC(controller.Conference)}.
	 */
	@Test
	public void testGetUserAssignedAsPC() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getUserAssignedAsSubPC(controller.Conference, int)}.
	 */
	@Test
	public void testGetUserAssignedAsSubPC() {
		Controller controller = new Controller();
		@SuppressWarnings("deprecation")
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		try {
			controller.getUserAssignedAsSubPC(testConference, 2);
			assert true;
		} catch (Exception e) {
			assert false;

		}
	}

	/**
	 * Test method for {@link controller.Controller#getAvailableReviewers(controller.Conference, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetAvailableReviewers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#addReviewers(controller.Conference, java.lang.String, java.lang.String[])}.
	 */
	@Test
	public void testAddReviewers() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getAvailableForSubPC(controller.Conference, int, java.lang.String)}.
	 */
	@Test
	public void testGetAvailableForSubPC() {
		Controller controller = new Controller();
		@SuppressWarnings("deprecation")
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		try {
			controller.getAvailableForSubPC(testConference, 2, "d-man");
			assert true;
		} catch (Exception e) {
			assert false;
		}
	}

	/**
	 * Test method for {@link controller.Controller#addSubPC(controller.Conference, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddSubPC() {
		Controller controller = new Controller();
		@SuppressWarnings("deprecation")
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		try {
			controller.addSubPC(testConference, "PAPER", "d-man");
			assert true;
		} catch (Exception e) {
			assert false;
		}
	}

	/**
	 * Test method for {@link controller.Controller#getReviews(controller.Conference, java.lang.String)}.
	 */
	@Test
	public void testGetReviews() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getMyConferences(java.lang.String)}.
	 */
	@Test
	public void testGetMyConferences() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getUpcommingConferences(java.lang.String)}.
	 */
	@Test
	public void testGetUpcommingConferences() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getMyPapers(controller.Conference, java.lang.String)}.
	 */
	@Test
	public void testGetMyPapers() {
		Controller controller = new Controller();
		@SuppressWarnings("deprecation")
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		try {
			controller.getMyPapers(testConference, "d-man");
			assert true;
		} catch (Exception e) {
			assert false;
		}
	}

	/**
	 * Test method for {@link controller.Controller#getPaperID(java.lang.String)}.
	 */
	@Test
	public void testGetPaperID() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#main(java.lang.String[])}.
	 */
	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
