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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import view.GUIEnum.StateOfGUI;
import controller.Controller;

/**
 * The Login user interface JPanel
 * 
 * @author Warrick Holfeld
 * @author Jacob Hall
 * 	
 * @version 90 Date: 11/27/13
 */
@SuppressWarnings("serial")
public class LoginGUI extends JPanel {
	
	/*
	 * the icon to display the CMS logo
	 */
	private static final ImageIcon ICON = new ImageIcon("src/view/images2.jpg");
	
	/*
	 * The background of the main JPanel
	 */
	private static final Color BACKGROUND_COLOR = new Color(153, 204, 204);
	
	/*
	 * The background of the inner user JPanel.
	 */
	private static final Color INNER_BACKGROUND_COLOR = (new Color(204, 204, 153));
	
	/*
	 * The size of the JPanel.
	 */
	private static final Dimension WIN_DIMENSION = new Dimension(1280, 720);
//	private static final Dimension WIN_DIMENSION = new Dimension(1024, 720?);

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
	private JTextField username_field;
	
	/*
	 * Warrick changed from JTextField
	 */
	private JPasswordField password_field;
	
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
	 * Create the JPanel that contains the LoginGUI.
	 * 
	 * <dt><b>Preconditions: The controller object has been instantiated.</b><dd>
	 * <dt><b>Postconditions: A JPanel is created to show the Login Screen</b><dd>
	 * @param the_controller
	 */
	public LoginGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setupActions();
		
		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setPreferredSize(WIN_DIMENSION);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
				
		JPanel panel = new JPanel();
		panel.setBackground(INNER_BACKGROUND_COLOR);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(413, 205, 469, 310);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewUser = new JLabel("New User?");
		lblNewUser.setBounds(63, 81, 107, 20);
		panel.add(lblNewUser);
		lblNewUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(63, 121, 107, 20);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(63, 157, 107, 20);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		//Warrick changed from JTextField
		password_field = new JPasswordField();
		password_field.setBounds(187, 156, 199, 26);
		
		panel.add(password_field);
		password_field.setColumns(10);
		
		username_field = new JTextField();
		username_field.setBounds(187, 120, 199, 26);
		panel.add(username_field);
		username_field.setColumns(10);
		
		JButton btnRegister = new JButton(my_register_action);
		btnRegister.setBounds(209, 79, 159, 29);
		panel.add(btnRegister);
		
		JButton btnLogin = new JButton(my_login_action);
		btnLogin.setBounds(209, 193, 159, 29);
		panel.add(btnLogin);
		
		JButton btnIcon = new JButton("");
		btnIcon.setForeground(BACKGROUND_COLOR);
		btnIcon.setBorder(null);
		btnIcon.setIcon(ICON);
		btnIcon.setBounds(430, 11, 404, 116);
		contentPane.add(btnIcon);
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 127, 1250, 12);
		contentPane.add(separator);
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
	
	/**
	 * Getter for the LoginGUI JPanel.
	 * 
	 * <dt><b>Preconditions: The LoginGUI has already been instantiated.</b><dd>
	 * <dt><b>Postconditions: The LoginGUI JPanel is returned.</b><dd>
	 * @return contentPane JPanel containing the LoginGUI.
	 */
	public JComponent getGUI() {
		return contentPane;
	}
	
	/**
	 * 
	 * Sets the initial curser position to the username field.
	 * 
	 * <dt><b>Preconditions: The LoginGUI has already been instantiated.</b><dd>
	 * <dt><b>Postconditions: The curser position is set to the username field.</b><dd>
	 */
	public void setDefaultCurserPosition(){
		username_field.requestFocusInWindow();
	}
	
	/**
	 * Set up the actions to associate events with outside logic
	 * 
	 * <dt><b>Preconditions: The LoginGUI is instantiated.</b><dd>
	 * <dt><b>Postconditions: actions associated with each button will be returned.</b><dd>
	 */
	private void setupActions(){
		my_register_action = new AbstractAction(REGISTER_TITLE_STRING, null)
		{			
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
				String username = username_field.getText();
				String password = password_field.getText();
				if (username.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a username.");
				}
				else if (password.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a password.");
				}
				else if (!controller.checkValidUsername(username))//Changed from username and password to just password
				{
					JOptionPane.showMessageDialog(contentPane, "Invalid username and password");
				}
				else {
					controller.setCurrentUsername(username);
					controller.setStateOfGUI(StateOfGUI.HOME);
				}
			}
		};
		my_login_action.putValue(Action.SHORT_DESCRIPTION, LOGIN_STRING);
		my_login_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
	}
}

