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

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import view.GUIEnum.StateOfGUI;

import controller.Controller;

/**
 * The Login user interface JPanel
 * 
 * @author Warrick Holfeld
 * @author Jacob Hall (revised on 11/10/13 to add into project and began adding
 * 			Action associations with the buttons.)
 * @see setupActions for example on how I began linking buttons with the Controller
 * 			and the state machine that will control which GUI is currently displayed.
 * @version 11/10/13
 */
@SuppressWarnings("serial")
public class LoginGUI extends JPanel {

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String REGISTER_STRING = "Register as new user (ALT+R)";
	
	/*
	 * The text to display on the Register button.
	 */
	private static final String REGISTER_TITLE_STRING = "Register";
	
	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String LOGIN_STRING = "Login to the system (ALT+L)";
	
	/*
	 * The text to display on the login button.
	 */
	private static final String LOGIN_TITLE_STRING = "Login";
	
	/*
	 * The JPanel that will contain the LoginGui
	 */
	private JPanel contentPane;
	
	/*
	 * 
	 */
	private JTextField textField;
	
	/*
	 * 
	 */
	private JTextField textField_1;
	
	/*
	 * The CMS controller
	 */
	private Controller controller;
	
	/*
	 * the Action associated with the register button
	 */
	private Action my_register_action;
	
	/*
	 * the Action associated with the login button
	 */
	private Action my_login_action;

	/**
	 * Create the frame.
	 */
	public LoginGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setupActions();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewUser = new JLabel("New User?");
		lblNewUser.setBounds(393, 54, 107, 20);
		contentPane.add(lblNewUser);
		
		JButton btnRegister = new JButton(my_register_action);
		btnRegister.setBounds(475, 50, 115, 29);
		contentPane.add(btnRegister);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(111, 182, 107, 20);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(233, 179, 199, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(111, 218, 107, 20);
		contentPane.add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(231, 215, 199, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnLogin = new JButton(my_login_action);
		btnLogin.setBounds(258, 257, 159, 29);
		contentPane.add(btnLogin);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.setBounds(258, 287, 159, 29);
		contentPane.add(btnForgotPassword);
		contentPane.setPreferredSize(new Dimension(700, 400));
	}
	
	/**
	 * Getter for the LoginGUI JPanel.
	 * 
	 * @return contentPane JPanel containing the LoginGUI
	 */
	public JComponent getGUI() {
		return contentPane;
	}
	
	/**
	 * Set up the actions to associate events with outside logic
	 */
	private void setupActions(){
		my_register_action = new AbstractAction(REGISTER_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setStateOfGUI(StateOfGUI.REGISTER);
			}
		};
		my_register_action.putValue(Action.SHORT_DESCRIPTION, REGISTER_STRING);
		my_register_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
		
		my_login_action = new AbstractAction(LOGIN_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setStateOfGUI(StateOfGUI.HOME);
			}
		};
		my_login_action.putValue(Action.SHORT_DESCRIPTION, LOGIN_STRING);
		my_login_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
	}
}

