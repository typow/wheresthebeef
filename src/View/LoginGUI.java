package View;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JButton;
import View.GUIEnum.StateOfGUI;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;


@SuppressWarnings("serial")
public class LoginGUI extends JPanel {

	private static final String REGISTER_STRING = "Register as new user (ALT+R)";
	
	private static final String REGISTER_TITLE_STRING = "Register";
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private Controller controller;
	private Action my_register_action;

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
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(258, 257, 159, 29);
		contentPane.add(btnLogin);
		
		JButton btnForgotPassword = new JButton("Forgot Password?");
		btnForgotPassword.setBounds(258, 287, 159, 29);
		contentPane.add(btnForgotPassword);
		contentPane.setPreferredSize(new Dimension(700, 400));
	}
	
	public JComponent getGUI() {
		return contentPane;
	}
	
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
	}
}

