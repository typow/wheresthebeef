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
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import controller.Controller;



public class RegisterGUI extends JPanel {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	private Controller controller;

	/**
	 * Create the frame.
	 */
	public RegisterGUI(final Controller the_controller) {
		super();
		controller = the_controller;
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
		
		textField = new JTextField();
		textField.setBounds(254, 45, 208, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(254, 90, 208, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("First");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(189, 188, 69, 20);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(161, 207, 127, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblMi = new JLabel("MI");
		lblMi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMi.setBounds(324, 187, 69, 20);
		contentPane.add(lblMi);
		
		textField_3 = new JTextField();
		textField_3.setBounds(301, 207, 69, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblLast = new JLabel("Last");
		lblLast.setHorizontalAlignment(SwingConstants.CENTER);
		lblLast.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLast.setBounds(427, 188, 69, 20);
		contentPane.add(lblLast);
		
		textField_4 = new JTextField();
		textField_4.setBounds(385, 207, 146, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(254, 143, 208, 26);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblSpecialization = new JLabel("Specialization");
		lblSpecialization.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSpecialization.setBounds(49, 273, 141, 20);
		contentPane.add(lblSpecialization);
		
		 DefaultComboBoxModel choice = new  DefaultComboBoxModel();
		 choice.addElement("--Other--");
		 choice.addElement("Engineering Bio");
		 choice.addElement("Engineering Chemical");
		 choice.addElement("Engineering Computer");
		 choice.addElement("Engineering Electronic");
		 JComboBox cb = new JComboBox(choice);
		cb.setBounds(221, 272, 160, 25);
		contentPane.add(cb);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(255, 313, 115, 29);
		contentPane.add(btnSubmit);
		contentPane.setPreferredSize(new Dimension(650, 400));
	}

	public JComponent getGUI() {
		return contentPane;
	}
	
}
