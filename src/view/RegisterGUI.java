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
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

/**
 * The RegisterGUI JPanel.
 * 
 * @author Warrick Holfeld Jacob Hall
 * 		edited to add the event listeners to the class and interface with the Controller
 * @version 11/13/13
 */

@SuppressWarnings("serial")
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
		
		DefaultComboBoxModel<String> choice = new  DefaultComboBoxModel<String>();
		choice.addElement("--Other--");
		choice.addElement("Engineering Bio");
		choice.addElement("Engineering Chemical");
		choice.addElement("Engineering Computer");
		choice.addElement("Engineering Electronic");
		contentPane.setPreferredSize(new Dimension(1280, 720));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 48, 1260, 2);
		contentPane.add(separator);
		
		JLabel label = new JLabel("Conference Management System (CMS)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label.setBounds(10, 11, 1260, 26);
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(348, 129, 587, 389);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setBounds(76, 50, 113, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(76, 95, 113, 20);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password");
		lblNewLabel_2.setBounds(76, 142, 172, 33);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(76, 211, 69, 20);
		panel.add(lblName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNewLabel_3 = new JLabel("First");
		lblNewLabel_3.setBounds(187, 186, 69, 20);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblMi = new JLabel("MI");
		lblMi.setBounds(321, 186, 69, 20);
		panel.add(lblMi);
		lblMi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblLast = new JLabel("Last");
		lblLast.setBounds(426, 186, 69, 20);
		panel.add(lblLast);
		lblLast.setHorizontalAlignment(SwingConstants.CENTER);
		lblLast.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		l_name_field = new JTextField();
		l_name_field.setBounds(384, 211, 146, 26);
		panel.add(l_name_field);
		l_name_field.setColumns(10);
		
		mi_name_field = new JTextField();
		mi_name_field.setBounds(300, 211, 69, 26);
		panel.add(mi_name_field);
		mi_name_field.setColumns(10);
		
		f_name_field = new JTextField();
		f_name_field.setBounds(155, 211, 127, 26);
		panel.add(f_name_field);
		f_name_field.setColumns(10);
		cb = new JComboBox<String>(choice);
		cb.setBounds(259, 274, 198, 25);
		panel.add(cb);
		
		JLabel lblSpecialization = new JLabel("Specialization");
		lblSpecialization.setBounds(76, 273, 141, 20);
		panel.add(lblSpecialization);
		lblSpecialization.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnSubmit = new JButton(my_submit_action);
		btnSubmit.setBounds(254, 315, 115, 29);
		panel.add(btnSubmit);
		
		confirm_password_field = new JTextField();
		confirm_password_field.setBounds(254, 148, 208, 26);
		panel.add(confirm_password_field);
		confirm_password_field.setColumns(10);
		
		password_field = new JTextField();
		password_field.setBounds(254, 95, 208, 26);
		panel.add(password_field);
		password_field.setColumns(10);
		
		username_field = new JTextField();
		username_field.setBounds(254, 50, 208, 26);
		panel.add(username_field);
		username_field.setColumns(10);
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
					controller.setCurrentUsername(username);
					controller.addNewUser(username, password_1, first, middle, last, specialty);
					controller.setStateOfGUI(StateOfGUI.HOME);
				}
			}
		};
		my_submit_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_STRING);
		my_submit_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
	}
}
