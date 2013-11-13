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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import view.LoginGUI;
import view.GUIEnum.StateOfGUI;

import controller.Controller;


/**
 * The Main Frame to run the CMS sofware in.
 * 
 * @author Jacob Hall
 * @version 11/10/13
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer {

	/**
	 * The LoginGUI
	 */
	private static LoginGUI loginWindow;
	
	/**
	 * The RegisterGUI
	 */
	private static RegisterGUI registerWindow;
	
	/**
	 * The HomeGUI main user interface
	 */
	private static HomeGUI homeWindow;
	
	/**
	 * The current JPanal Component being used by the MainFrame JFrame
	 */
	private Component currentPanel;
	
	/**
	 * The Controller object that handles the business logic and acts as an interface
	 * between the GUI and the database.
	 */
	private static Controller controller;

	/**
	 * Creates and makes visible the MainFrame for the CMS software.
	 * 
	 * @param args Command line arguments, ignored.
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) 
		throws CloneNotSupportedException
		{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final MainFrame frame = new MainFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Method creates a state machine that will allow discrete states to
	 * represent the current mode the GUI is in.
	 */
	public void setFrame() {
		switch (controller.getStateOfGUI()) {
		case LOGIN:
			setTitle("Login");
			currentPanel = loginWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case REGISTER:
			setTitle("Register");
			this.remove(currentPanel);
			currentPanel = registerWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case HOME:
			setTitle("Main User Interface");
			this.remove(currentPanel);
			currentPanel = homeWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		}
	}

	/**
	 * The update method for the Observer interface. Method updates the frame
	 * with the appropriate mode of operation the GUI should transition to.
	 * 
	 * @param the_object
	 *            The Observable that called this method.
	 * @param the_arg
	 *            The argument it passed to this method.
	 */
	@Override
	public void update(final Observable the_object, final Object the_arg) {
		//TODO: Add logic according to the_arg passed in.  If it's a state change in the GUI
		//		then the setFrame() needs to be called.  If not, some other update should
		//		take place and the GUI shouldn't change states.  However, maybe the GUI
		//		will need to be refreshed if new data should be populated in a table
		//		and the GUI should now reflect this.
		setFrame();
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		controller = new Controller();
		controller.setStateOfGUI(StateOfGUI.LOGIN);
		loginWindow = new LoginGUI(controller);
		//TODO: move these to the setFrame() method?
		registerWindow = new RegisterGUI(controller);
		homeWindow = new HomeGUI(controller);
		controller.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setFrame();
	}
}