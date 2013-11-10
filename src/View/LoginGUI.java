package View;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class LoginGUI extends JPanel{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		super();
//		setTitle("Home");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 677, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewUser = new JLabel("New User?");
		lblNewUser.setBounds(393, 54, 107, 20);
		contentPane.add(lblNewUser);
		
		JButton btnRegister = new JButton("Register");
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
}

