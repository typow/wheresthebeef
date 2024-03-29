/**
 * 
 */
package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import view.GUIEnum.StateOfGUI;
import view.GUIEnum.paperRelation;
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
	 * The resultSet is what is returned from the database
	 * after the statement has been sent.
	 */
	private ResultSet resultSet = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		resetDatabase();
	}
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		resetDatabase();
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
		
		String username = "hairyguy";	// Not in the DB
		boolean result = true;
		result = controller.checkValidUsername(username);
		assertEquals(false, result);
		
		// Now Add user
		controller.addNewUser(username, "password", "Hairy", "E", "Guy", "");
		result = controller.checkValidUsername(username);
		assertEquals(true, result);

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

		Boolean valid = false;
		String conference = "Trees Have Feelings";  //Already in the DB we know its true

		//TODO: check the conference title against database to see if this conference title
		//		already exists in the database.  Jacob
		
		valid = controller.checkConferenceExists(conference);
		assertEquals(true, valid);
		conference = "b"; //Not in the DB we know it's false
		valid = controller.checkConferenceExists(conference);
		assertEquals(false, valid);
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
				new Date(), new Date(), new Date(), new Date(), "Junit Testing"); // Not in the DB
		boolean result = true;
		result = controller.checkConferenceExists("zz");
		assertEquals(false, result);
		
		// Now Add user
		controller.createNewConference(the_conference);
		result = controller.checkConferenceExists("zz");
		assertEquals(true, result);
	}

	/**
	 * Test method for setCurrentConference()
	 */
	@Test
	public void testSetCurrentConference() {
		Controller controller = new Controller();
		Conference test_conference = new Conference("zz", "Biff", new Date(), "3535 Wise street", 
				"Bremerton", "WA", "98311",
				new Date(), new Date(), new Date(), new Date(), "Junit Testing");
		controller.setCurrentConference(test_conference);
		assertEquals(test_conference, controller.getCurrentConference());
	}

	/**
	 * Test method for getCurrentConference()
	 * Basically the same as the setter.
	 */
	@Test
	public void testGetCurrentConference() {
		Controller controller = new Controller();
		Conference test_conference = new Conference("zz", "Biff", new Date(), "3535 Wise street", 
				"Bremerton", "WA", "98311",
				new Date(), new Date(), new Date(), new Date(), "Junit Testing");
		controller.setCurrentConference(test_conference);
		assertEquals(test_conference, controller.getCurrentConference());
	}

	/**
	 * Test method for createNewPaper()
	 * @throws Exception 
	 */
	@Test
	public void testCreateNewPaper() throws Exception {
		Controller controller = new Controller();		
		 paperStatusAuthorViewable the_user_viewable_status = null;
		 paperStatusAdminViewable  the_admin_viewable_status = null;
		Conference the_conference = new Conference("zz", "Biff", new Date(), "3535 Wise street", 
				"Bremerton", "WA", "98311",
				new Date(), new Date(), new Date(), new Date(), "Junit Testing"); // Not in the DB
		boolean result = true;
		
		// Now Add user
	    try { //successfully add the paper
	    	controller.createNewPaper(the_conference, "D-man", "JunitTest", "JunitTest home ", the_user_viewable_status, the_admin_viewable_status);
	    	result =  true;
        } catch (Exception e) {
        	result =  false;
        }
	    assertTrue(result);
	    try {//Show that the database now contains the paper
	    	controller.createNewPaper(the_conference, "D-man", "JunitTest", "JunitTest home ", the_user_viewable_status, the_admin_viewable_status);
	    	result =  false;
        } catch (Exception e) {
        	result = true;
        }
	    assertFalse(result);
	}
	/**
	 * Test method for getRelationToPaper()
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetRelationToPaper() {
		Controller controller = new Controller();
		
		String username1 = "typow";
		String username2 = "thor";
		String username3 = "warfeld";
		String username4 = "sethk2";
		
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Baking Pi";
		
		paperRelation testRelation = controller.getRelationToPaper(testConference, paperTitle, username1);
		assertEquals(paperRelation.PC, testRelation);
		
		testRelation = controller.getRelationToPaper(testConference, paperTitle, username2);
		assertEquals(paperRelation.SUBPC, testRelation);
		
		testRelation = controller.getRelationToPaper(testConference, paperTitle, username3);
		assertEquals(paperRelation.REVIEWER, testRelation);
		
		paperTitle = "Wooden Computers";
		testRelation = controller.getRelationToPaper(testConference, paperTitle, username4);
		assertEquals(paperRelation.AUTHOR, testRelation);
	}

	/**
	 * Test method for setPaperStatus()
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testSetPaperStatus() {
		Controller controller = new Controller();		
		
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Baking Pi";
		
		// Two status' that the paper didn't have before
		controller.setPaperStatus(testConference, paperTitle, 
				paperStatusAuthorViewable.UNDER_REVIEW, paperStatusAdminViewable.OVERDUE_FOR_REVIEW);
		
		assertEquals(paperStatusAuthorViewable.UNDER_REVIEW, controller.getStatusAuthorView(testConference, paperTitle));
		assertEquals(paperStatusAdminViewable.OVERDUE_FOR_REVIEW, 
				controller.getAdminPaperStatus(testConference, paperTitle));

	}

	/**
	 * Test method for getAdminPaperStatus()
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetAdminPaperStatus() {
		Controller controller = new Controller();
			
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Baking Pi";
		
		// Default for paper
		assertEquals(paperStatusAdminViewable.SUBMITTED,
				controller.getAdminPaperStatus(testConference, paperTitle));
	}

	/**
	 * Test method for getStatusAuthorView()
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetStatusAuthorView() {
		Controller controller = new Controller();

		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Baking Pi";
		
		// Default for paper
		assertEquals(paperStatusAuthorViewable.ACCEPTED,
				controller.getStatusAuthorView(testConference, paperTitle));
	}

	/**
	 * Test method for setCurrentPaper()
	 */
	@Test
	public void testSetCurrentPaper() {
		Controller controller = new Controller();
		String testPaper = "The Wonders of Beeswax";
		
		// Default ""
		controller.setCurrentPaper(testPaper);
		assertEquals(testPaper, controller.getCurrentPaper());
	}

	/**
	 * Test method for getCurrentPaper()
	 * Basically the same as the setter.
	 */
	@Test
	public void testGetCurrentPaper() {
		Controller controller = new Controller();
		String testPaper = "The Wonders of Beeswax";
		
		// Default ""
		controller.setCurrentPaper(testPaper);
		assertEquals(testPaper, controller.getCurrentPaper());
	}
	
	/**
	 * Test method for {@link controller.Controller#getPaperFilePath(controller.Conference, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetPaperFilePath() {
		Controller controller = new Controller();
		
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Baking Pi";

		assertEquals("text", controller.getPaperFilePath(testConference, paperTitle));
	}
	
	/**
	 * Test method for {@link controller.Controller#deletePaper(controller.Conference, java.lang.String, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testDeletePaper() {
		Controller controller = new Controller();
		
		boolean testResult = false;
		
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Baking Pi";
		String username = "typow";
		
		// Check that the paper is in the Database
		Paper[] testPapers = controller.getMyPapers(testConference, username);
		
		for (int i = 0; i < testPapers.length; i++) {
			if (testPapers[i].getPaperTitle().equals(paperTitle)) {
				testResult = true;
			}
		}
		
		assertEquals(true, testResult);
		
		// Now to delete the paper
		testResult = false;
		controller.deletePaper(testConference, username, paperTitle);
		testPapers = controller.getMyPapers(testConference, username);
		
		for (int i = 0; i < testPapers.length; i++) {
			if (testPapers[i].getPaperTitle().equals(paperTitle)) {
				testResult = true;
			}
		}
		
		assertEquals(false, testResult);
	}

	/**
	 * Test method for {@link controller.Controller#createNewReview(java.lang.String, controller.Conference, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int[], java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testCreateNewReview() {
		Controller controller = new Controller();
		
		boolean testResult = false;
			
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Wooden Computers";
		String username = "typow";
		String paperAuthor = "sethk2";
		int testButtons[] = new int[10];
		
		// Review not in Default Database
		controller.createNewReview(username, testConference, paperTitle, paperAuthor, 
				"", testButtons, "");
		
		Review testReviews[] = controller.getReviews(testConference, paperTitle);
		
		for (int i = 0; i < testReviews.length; i++) {
			if (testReviews[i].getPaperAuthor().equals(paperAuthor) &&
					testReviews[i].getReviewerName().equals(username)) {
				testResult = true;
			}
		}
		
		assertEquals(true, testResult);
		
		// Review IS in Default Database
		testResult = false;
		controller.createNewReview("warfeld", testConference, "Baking Pi",
				"typow", "", testButtons, "");

		Review testReviews2[] = controller.getReviews(testConference, paperTitle);

		for (int i = 0; i < testReviews.length; i++) {
			if (testReviews2[i].getPaperAuthor().equals(paperAuthor)
					&& testReviews2[i].getReviewerName().equals(username)) {
				testResult = true;
			}
		}

		assertEquals(true, testResult);

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
		assertTrue(controller.canAddReview(the_conference, "junitTest", "ripped"));
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
		String paper = "Baking Pi";//already has a paper of this name
		String expectedAuthor = "typow";//authored by this person in the DB
		String testedAuthor = "";
		testedAuthor = controller.getPaperAuthor(testConference, paper);
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
	 * Test method addPaperRecommendation()
	 */
	@Test
	public void testAddPaperRecommendation() {
		Controller controller = new Controller();
		
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15),
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Baking Pi";
		String subPC = "da_man";
		controller.addPaperRecommendation(subPC, testConference, paperTitle, "typow", 5, "It bakes pi!");
		
		// Recommendation from this user isn't in Default Database
		String result = controller.getPaperRecommendationSubPCName(testConference, paperTitle);
		
		assertEquals(subPC, result);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	/**
	 * Test method for getPaperRecommendationRationale()
	 */
	public void testGetPaperRecommendationRationale() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		
		
		if (!controller.checkConferenceExists("TestTest")) {
			controller.createNewConference(testConference);
		} 
		/*
		try {
			controller.createNewPaper(testConference, "Test username", "Test PaperTitle", 
					"Test FileSubmittted", paperStatusAuthorViewable.SUBMITTED, 
					paperStatusAdminViewable.ACCEPTED);
		} catch (Exception e) {
			// Paper already exists
		}
		
		controller.addPaperRecommendation("Sub PC", testConference, 
				"Test PaperTitle", "Test Author", 5, "Test Recommendation");
		*/
		
		//Recommendation already exists written by thor for paper "Baking Pi" by typow 
		//with recommendation rationale of "Helped me gain a six-pack"
		assertEquals("Helped me gain a six-pack", controller.getPaperRecommendationRationale(
				testConference, "Baking Pi"));
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
		int expectedVal = 2;//In database already as 2 from SubPC thor
		int testedVal = controller.getPaperRecommendationNumericalValuefinal(testConference, "Baking Pi");
		assertEquals(expectedVal, testedVal);
	}
	
	/**
	 * Test method for {@link controller.Controller#getPaperRecommendationSubPCName(controller.Conference, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetPaperRecommendationSubPCName() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15),
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		controller.addPaperRecommendation("da-man", testConference, "Baking Pi", "typow", 5, "It bakes pi!");
		String subpc = "da-man";
		
		String result = controller.getPaperRecommendationSubPCName(testConference, "Baking Pi");
		
		assertEquals(subpc, result);
		
	}

	/**
	 * Test method for {@link controller.Controller#getUsersAssignedAsReviewers(controller.Conference, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetUsersAssignedAsReviewers() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15),
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String[] reviewers = new String[2];
		
		String[] result = controller.getUsersAssignedAsReviewers(testConference, "Baking Pi");
		
		Boolean equal = false;
		for (int i = 0; i < result.length; i++)
		{
			if (result[i].equals("noise") || result[i].equals("warfeld")) {
				equal = true;
			} else {
				equal = false;
			}
				
		}
		
		assertEquals(true, equal);
	}

	/**
	 * Test method for {@link controller.Controller#getUserAssignedAsPC(controller.Conference)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetUserAssignedAsPC() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15),
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String pc ="typow";
		
		String result = controller.getUserAssignedAsPC(testConference);
		
		assertEquals(pc, result);
	}

	/**
	 * Test method for {@link controller.Controller#getUserAssignedAsSubPC(controller.Conference, int)}.
	 */
	@Test
	public void testGetUserAssignedAsSubPC() {
		
		Controller controller = new Controller();
		String subprogChair = "thor"; //
		@SuppressWarnings("deprecation")
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		assertEquals(controller.getUserAssignedAsSubPC(testConference, 2), subprogChair);
	}

	/**
	 * Test method for {@link controller.Controller#getAvailableReviewers(controller.Conference, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetAvailableReviewers() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15),
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paper = "Baking Pi";//15users - author(typow) - currentReviewers(warfeld, noise)
		String subpc = "thor";// - subpc(thor) = ajm1, sethk2, d-man, Halmus, idol, yellow, solo, da-man, bounty,
								//enterprise, ripped
		String reviewers[] = controller.getAvailableReviewers(testConference, paper, subpc);
		String expectedReviewers[] = {"ajm1", "sethk2", "d-man", "Halmus", "idol", "yellow",
				"solo", "da-man", "bounty", "enterprise", "ripped"};
		Arrays.sort(reviewers);//sort the tested reviewers
		Arrays.sort(expectedReviewers);//sort the expected
		boolean check = Arrays.equals(reviewers, expectedReviewers);//compare the expected
		assertTrue(check);

	}

	/**
	 * Test method for {@link controller.Controller#addReviewers(controller.Conference, java.lang.String, java.lang.String[])}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAddReviewers() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15),
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		
		String[]reviewers = new String[2];
		reviewers[0] = "noise";
		reviewers[1] = "bounty";
		
		controller.addReviewers(testConference, "Baking Pi", reviewers);
	 
		String[] result = controller.getUsersAssignedAsReviewers(testConference, "Baking Pi");
		
		Boolean equal = false;
		//Test noise was added
		for (int i = 0; i < result.length; i++)
		{
			if (result[i].equals("noise"))//noise was added
				equal = true;
		}
		assertTrue(equal);
		
		//Test bounty was added
		equal = false;
		for (int i = 0; i < result.length; i++)
		{
			if (result[i].equals("bounty"))//noise was added
				equal = true;
		}
		assertTrue(equal);
	}

	/**
	 * Test method for {@link controller.Controller#getAvailableForSubPC(controller.Conference, int, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetAvailableForSubPC() {
		Controller controller = new Controller();
		resetDatabase();
		String[] subprogChair = {"ajm1", "warfeld", "d-man", "Halmus", "idol", "thor", "yellow", "solo", "da-man", "bounty", "enterprise", "noise", "ripped"};
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		assertEquals(subprogChair, controller.getAvailableForSubPC(testConference, 5, "typow"));

	}

	/**
	 * Test method for {@link controller.Controller#addSubPC(controller.Conference, java.lang.String, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAddSubPC() {
		Controller controller = new Controller();
		
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		boolean result = true;
		String subPC = controller.getUserAssignedAsSubPC(testConference, 5);
		assertNull(subPC);
		
		// Now Add user
		controller.addSubPC(testConference, "Wooden Computers", "Halmus");
		subPC = controller.getUserAssignedAsSubPC(testConference, 5);
		assertNotNull(subPC);
	}

	/**
	 * Test method for {@link controller.Controller#getReviews(controller.Conference, java.lang.String)}.
	 */
	@Test
	public void testGetReviews() {
		Controller controller = new Controller();

		boolean testResult = false;
			
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1), "Test Address", 
				"Test City", "Test State", "Test Zip", new Date(2000, 1, 15), new Date(2000, 1, 20), 
				new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		String paperTitle = "Baking Pi";
	
		// Review in Default Database	
		Review testReviews[] = controller.getReviews(testConference, paperTitle);
		
		assertEquals("warfeld", testReviews[0].getReviewerName());
		assertEquals("noise", testReviews[1].getReviewerName());
	}

	/**
	 * Test method for {@link controller.Controller#getMyConferences(java.lang.String)}.
	 */
	@Test
	public void testGetMyConferences() {
		Controller controller = new Controller();

		String username = "warfeld";
		
		Conference testConferences[] = controller.getMyConferences(username);
		
		// From the Default Database
		assertEquals("Small Computer Conference", testConferences[0].getConfTitle());
		assertEquals("Everything Math", testConferences[1].getConfTitle());
	}

	/**
	 * Test method for {@link controller.Controller#getUpcommingConferences(java.lang.String)}.
	 */
	@Test
	public void testGetUpcommingConferences() {
		Controller controller = new Controller();

		String username = "typow";
		
		Conference testConferences[] = controller.getUpcommingConferences(username);
		
		for (int i = 0; i < testConferences.length; i++) {
			System.out.println(testConferences[i].getConfTitle());
		}

		// From the Default Database
		assertEquals("Trees Have Feelings", testConferences[0].getConfTitle());
		assertEquals("Everything Math", testConferences[1].getConfTitle());
	}

	/**
	 * Test method for {@link controller.Controller#getMyPapers(controller.Conference, java.lang.String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testGetMyPapers() {
		Controller controller = new Controller();
		Conference testConference = new Conference("Small Computer Conference", "typow", new Date(2000, 1, 1),
				"Test Address", "Test City", "Test State", "Test Zip", new Date(2000, 1, 15), 
				new Date(2000, 1, 20), new Date(2000, 1, 25), new Date(2000, 1, 27), "Test Summary");
		paperStatusAuthorViewable author = controller.getStatusAuthorView(testConference, "Wooden Computers");
		paperStatusAdminViewable admin = controller.getAdminPaperStatus(testConference, "Wooden Computers");
		
		String subChair = controller.getUserAssignedAsSubPC(testConference, 8);
		String progrChair = controller.getUserAssignedAsPC(testConference);
		String[] reviewers = controller.getUsersAssignedAsReviewers(testConference, "Sequoias- A Love Story");
		Paper[] the_paper = {new Paper("Small Computer Conference", "Wooden Computers",  "sethk2",  author, admin, subChair, progrChair, reviewers, 5)};

		assertEquals(the_paper[0].getID(), controller.getMyPapers(testConference, "sethk2")[0].getID());
	}

	/**
	 * Test method for {@link controller.Controller#getPaperID(java.lang.String)}.
	 */
	@Test
	public void testGetPaperID() {
		Controller controller = new Controller();
		
		String paperTitle = "Baking Pi";
		
		assertEquals(2, controller.getPaperID(paperTitle));
	}
}
