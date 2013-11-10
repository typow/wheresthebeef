package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;


public class Submit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Submit frame = new Submit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Submit() {
		setTitle("Submit Paper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBobWSmith = new JLabel("Bob W. Smith");
		lblBobWSmith.setBounds(29, 26, 110, 20);
		contentPane.add(lblBobWSmith);
		
		JButton btnNewButton = new JButton("<-- Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(24, 101, 115, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Past Papers");
		btnNewButton_1.setBounds(24, 131, 115, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(24, 160, 115, 29);
		contentPane.add(btnLogOut);
		
		textField = new JTextField();
		textField.setBounds(255, 102, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
