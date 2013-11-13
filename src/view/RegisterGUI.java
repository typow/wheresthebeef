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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import view.GUIEnum.StateOfGUI;
import controller.Controller;

/**
 * The RegisterGUI JPanel.
 * 
 * @author Warrick Holfeld Jacob Hall
 * 		edited to add the event listeners to the class and interface with the Controller
 * @version 11/13/13
 */

public class RegisterGUI extends JPanel {
	
	/*
	 * The JPanel that the RegisterGUI will be contained in .
	 */
	private JPanel contentPane;
	
	/*
	 * The JTextField containing the username entry.
	 */
	private JTextField username_field;
	
	/*
	 * The JTextField containing the password entry.
	 */
	private JTextField password_field;
	
	/*
	 * The JTextField containing the first name entry.
	 */
	private JTextField f_name_field;
	
	/*
	 * The JTextField containing the middle initial entry.
	 */
	private JTextField mi_name_field;
	
	/*
	 * The JTextField containing the last name entry.
	 */
	private JTextField l_name_field;
	
	/*
	 * The JTextField containing the password entry.
	 */
	private JTextField confirm_password_field;
	
	/*
	 * The engineering field specialty chosen to best describe user
	 */
	private JComboBox<String> cb;
	
	/*
	 * The text to display on the Register button.
	 */
	private static final String SUBMIT_TITLE_STRING = "Register";
	
	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String SUBMIT_STRING = "Login to the system (ALT+S)";
	
	/*
	 * the Action associated with the register button.
	 */
	private Action my_submit_action;
	
	/*
	 * The controller object that the entire program will access.
	 */
	private Controller controller;

	/**
	 * Create the frame.
	 * 
	 * @param the_controller The Controller is passed to this class at instantiation
	 * 			to allow the JPanel to notify the controller when data is ready to be 
	 * 			passed and a new user is to be created.
	 */
	public RegisterGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setupActions();
		setBounds(100, 100, 643, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(77, 48, 113, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(77, 93, 113, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(77, 139, 172, 33);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(77, 209, 69, 20);
		contentPane.add(lblName);
		
		username_field = new JTextField();
		username_field.setBounds(254, 45, 208, 26);
		contentPane.add(username_field);
		username_field.setColumns(10);
		
		password_field = new JTextField();
		password_field.setBounds(254, 90, 208, 26);
		contentPane.add(password_field);
		password_field.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("First");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(189, 188, 69, 20);
		contentPane.add(lblNewLabel_3);
		
		f_name_field = new JTextField();
		f_name_field.setBounds(161, 207, 127, 26);
		contentPane.add(f_name_field);
		f_name_field.setColumns(10);
		
		JLabel lblMi = new JLabel("MI");
		lblMi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMi.setBounds(324, 187, 69, 20);
		contentPane.add(lblMi);
		
		mi_name_field = new JTextField();
		mi_name_field.setBounds(301, 207, 69, 26);
		contentPane.add(mi_name_field);
		mi_name_field.setColumns(10);
		
		JLabel lblLast = new JLabel("Last");
		lblLast.setHorizontalAlignment(SwingConstants.CENTER);
		lblLast.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLast.setBounds(427, 188, 69, 20);
		contentPane.add(lblLast);
		
		l_name_field = new JTextField();
		l_name_field.setBounds(385, 207, 146, 26);
		contentPane.add(l_name_field);
		l_name_field.setColumns(10);
		
		confirm_password_field = new JTextField();
		confirm_password_field.setBounds(254, 143, 208, 26);
		contentPane.add(confirm_password_field);
		confirm_password_field.setColumns(10);
		
		JLabel lblSpecialization = new JLabel("Specialization");
		lblSpecialization.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSpecialization.setBounds(49, 273, 141, 20);
		contentPane.add(lblSpecialization);
		
		DefaultComboBoxModel<String> choice = new  DefaultComboBoxModel<String>();
		choice.addElement("--Other--");
		choice.addElement("Engineering Bio");
		choice.addElement("Engineering Chemical");
		choice.addElement("Engineering Computer");
		choice.addElement("Engineering Electronic");
		cb = new JComboBox<String>(choice);
		cb.setBounds(221, 272, 160, 25);
		contentPane.add(cb);
		
		JButton btnSubmit = new JButton(my_submit_action);
		btnSubmit.setBounds(255, 313, 115, 29);
		contentPane.add(btnSubmit);
		contentPane.setPreferredSize(new Dimension(650, 400));
	}

	public JComponent getGUI() {
		return contentPane;
	}
	
	/**
	 * Set up the actions to associate events with outside logic
	 */
	@SuppressWarnings("serial")
	private void setupActions(){
		my_submit_action = new AbstractAction(SUBMIT_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				String username = username_field.getText();
				String password_1 = password_field.getText();
				String password_2 = confirm_password_field.getText();
				String first = f_name_field.getText();
				String middle = mi_name_field.getText();
				String last = l_name_field.getText();
				String specialty;
				if (username.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a username.");
				}
				else if (controller.checkValidUsername(username))
				{
					JOptionPane.showMessageDialog(contentPane, "Username already in use, enter different username.");
				}
				else if (password_1.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a password.");
				}
				else if (password_2.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please confirm password.");
				}
				else if (!password_1.equals(password_2)){
					JOptionPane.showMessageDialog(contentPane, "Passwords entered don't match.");
				}
				else if (first.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter first name.");
				}
				else if (middle.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter middle initial.");
				}
				else if (last.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter last name.");
				}
				else
				{
					if (cb.getSelectedItem().equals("--Other--")){
						specialty = (String) cb.getSelectedItem();
					}
					else if (cb.getSelectedItem().equals("Engineering Bio")){
						specialty = (String) cb.getSelectedItem();
					}
					else if (cb.getSelectedItem().equals("Engineering Chemical")){
						specialty = (String) cb.getSelectedItem();
					}
					else if (cb.getSelectedItem().equals("Engineering Computer")){
						specialty = (String) cb.getSelectedItem();
					}
					else {
						specialty = (String) cb.getSelectedItem();
					}						
					controller.addNewUser(username, password_1, first, middle, last, specialty);
					controller.setStateOfGUI(StateOfGUI.HOME);
				}
			}
		};
		my_submit_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_STRING);
		my_submit_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
	}
	
}
