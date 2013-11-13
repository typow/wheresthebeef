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
	 * The current state (mode of operation) the GUI is currently in.
	 * @see StateOfGUI
	 */
	private StateOfGUI state = StateOfGUI.LOGIN;
	
	/*
	 * The current user logged in.
	 */
	private String current_user;
	
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
		//		to be populated to a table or something like that?
		state = the_state;	
		setChanged();
		notifyObservers();
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
	public Boolean checkValidUsername(final String the_username)
	{
		Boolean valid = false;
		//TODO: check the username against database to see if this username
		//		already exists in the database.
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
		//		username and password combo are valid.
		return valid;
	}
	
	public void addNewUser(final String the_username, final String the_password, 
							final String the_first_name, final String the_middle_name, 
							final String the_last_name, final String the_specialty)
	{
		//TODO: add this as a new user to the database.  GUI already verified that the
		//		username doesn't already exist and that the fields are not empty.
	}
	
	/**
	 * Sets the current user to the username of the person currently logged in.
	 * 
	 * @param the_username
	 */
	public void setCurrentUsername(final String the_username){
		current_user = the_username;
	}

}
