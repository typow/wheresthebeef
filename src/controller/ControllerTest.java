/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Soluna
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
	 * Test method for {@link controller.Controller#Controller()}.
	 */
	@Test
	public void testController() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#setStateOfGUI(view.GUIEnum.StateOfGUI)}.
	 */
	@Test
	public void testSetStateOfGUI() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getStateOfGUI()}.
	 */
	@Test
	public void testGetStateOfGUI() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#checkValidUsername(java.lang.String)}.
	 */
	@Test
	public void testCheckValidUsername() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#checkValidUsernamePassword(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCheckValidUsernamePassword() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#addNewUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddNewUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#setCurrentUsername(java.lang.String)}.
	 */
	@Test
	public void testSetCurrentUsername() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getCurrentUsername()}.
	 */
	@Test
	public void testGetCurrentUsername() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#getFullName(java.lang.String)}.
	 */
	@Test
	public void testGetFullName() {
		fail("Not yet implemented");
	}

	/**
	 * Tests to see if the conference already exists, database has conference a but not b. 
	 * Asserts that a is in database and b is not.
	 * Test method for {@link controller.Controller#checkConferenceExists(java.lang.String)}.
	 */
	@Test
	public void testCheckConferenceExists() {
		Boolean valid1 = false;
		Boolean valid2 = false;
		String the_conference_title = "a";
		String the_conference_title2 = "b";

		//TODO: check the conference title against database to see if this conference title
		//		already exists in the database.  Jacob
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM conference WHERE name='" + the_conference_title + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				valid1 = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Check for valid conference name failed");
			e.printStackTrace();
		}
		try {
			
			PreparedStatement statement = connect.prepareStatement(
					"SELECT * FROM conference WHERE name='" + the_conference_title2 + "'");
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				valid2 = true;
			}
			
		} catch (SQLException e) {
			System.out.println("Check for valid conference name failed");
			e.printStackTrace();
		}
		assertTrue(valid1 && !valid2);
	}

	/**
	 * tests to see if conference zz can be added to the database. 
	 * Test method for {@link controller.Controller#createNewConference(controller.Conference)}.
	 */
	@Test
	public void testCreateNewConference() {
		boolean valid = true; 
		try {			
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO conference VALUES ('" + "zz" + "', '" + "David" + "', '" +
							"11/19/2013" + "', '" + "11/19/2013" + "', '" + "11/19/2013" + "', '" + 
							"11/19/2013" + "', '" + "11/19/2013"+ "', '" + "JUnit Testing" + "')");
			statement.execute();
			valid = true;
			assertTrue(valid);
		} catch (SQLException e) {
			valid = false;
			assertTrue(valid);
		}
	}

	/**
	 * Test method for {@link controller.Controller#setCurrentConference(controller.Conference)}.
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
	 */
	@Test
	public void testCreateNewPaper() {
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
		fail("Not yet implemented");
	}

}
