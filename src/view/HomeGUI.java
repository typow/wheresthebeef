package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;


public class HomeGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	/*
	 * The CMS controller
	 */
	private Controller controller;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HomeGUI frame = new HomeGUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public HomeGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(153, 66, 14, 366);
		contentPane.add(separator_1);
		
		JLabel lblUsername = new JLabel("My Conferences");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(177, 90, 143, 20);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Conference Management System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(170, 11, 330, 26);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 48, 631, 2);
		contentPane.add(separator);
		
		Button button = new Button("Logout");
		button.setBounds(10, 90, 137, 22);
		contentPane.add(button);
		
		Button button_1 = new Button("<-- Back");
		button_1.setBounds(10, 118, 137, 22);
		contentPane.add(button_1);
		
		Button button_2 = new Button("Create New Conference");
		button_2.setBounds(10, 146, 137, 22);
		contentPane.add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(177, 115, 514, 125);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Conference", "   Author", "    Paper", "     Date", "   Status", "    Yada....", "    Yada...."
			}
		));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("UserName");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(60, 69, 107, 20);
		contentPane.add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(177, 307, 514, 125);
		contentPane.add(scrollPane_1);
		
		JLabel lblUpcommingConferences = new JLabel("Upcomming Conferences");
		lblUpcommingConferences.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUpcommingConferences.setBounds(177, 277, 213, 20);
		contentPane.add(lblUpcommingConferences);
		contentPane.setPreferredSize(new Dimension(800, 500));
	}
	
	/**
	 * Getter for the HomeGUI JPanel.
	 * 
	 * @return contentPane JPanel containing the HomeGUI
	 */
	public JComponent getGUI() {
		return contentPane;
	}
}

