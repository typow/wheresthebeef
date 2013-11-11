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

package view;

public class GUIEnum {

	/**
	 * The enumeration class to reference what state the GUI is in.
	 * 
	 * More states need to be added to correspond to the various GUI
	 * windows needed to drive this program.
	 * 
	 * @author Jacob Hall
	 * @version 10 Nov 2013
	 */
	public enum StateOfGUI{
		/**
		 * Make the login screen is visible.
		 */
		LOGIN,
		/**
		 * Make the registration screen visible.
		 */
		REGISTER,
		/**
		 * Make the main user interface visible.
		 */
		HOME;
	}

}
