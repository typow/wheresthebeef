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

package View;

import java.util.Observable;
import View.GUIEnum.StateOfGUI;

/**
 * The Controller handle all business logic and act as an interface between the GUI
 * and the database.
 * 
 * @author Jacob Hall
 * @version 11/10/13
 */
public class Controller extends Observable{

	/**
	 * The current state (mode of operation) the GUI is currently in.
	 * @see StateOfGUI
	 */
	private StateOfGUI state = StateOfGUI.LOGIN;
	
	/**
	 * Set the next state the GUI should transition to.  Note: setting this to a new state
	 * causes the Observable Object Controller to change states and notify Observers.
	 * 
	 * @param the_state
	 */
	public void setStateOfGUI(StateOfGUI the_state){
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


}
