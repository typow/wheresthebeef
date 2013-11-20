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

package GUIdesigns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * The View Conference Panel
 * 
 * Allows the user to see an overview of conference and view the attached paper objects
 * @author Warrick Holfeld
 * @version 11/19/13
 */
public class ViewConferenceGUI extends JFrame {
	

	private JPanel contentPane;
	
	private static final String[] COLUMN_NAMES = {"Paper", "Manage Button", "Author", "Status", "Program Chair", "SubPC", "Reviewers"};
	
	private static final String[] DEADLINES_PANEL = {"Submit", "Review", "Recommend", "Decision", "Conference Date"};
	
	private JPanel main_panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewConferenceGUI frame = new ViewConferenceGUI();
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
	public ViewConferenceGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		main_panel = new JPanel();
		main_panel.setBackground(new Color(153, 204, 204));
		contentPane.add(main_panel, BorderLayout.CENTER);
		main_panel.setLayout(null);
		
		JLabel lblViewConferenceGui = new JLabel("View Conference GUI");
		lblViewConferenceGui.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblViewConferenceGui.setBounds(573, 11, 142, 14);
		main_panel.add(lblViewConferenceGui);
		
		
		buildScrollPanel();
		
		buildUserDataPanel();
		
		
		JPanel conference_dates_panel = new JPanel();
		conference_dates_panel.setBounds(25, 110, 399, 100);
		conference_dates_panel.setLayout(new GridLayout(5, 3));
		JLabel deadline_label = new JLabel(" Deadline Dates:");
		deadline_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		conference_dates_panel.add(deadline_label);
		for(int i = 2; i<=15; i++){
			if((i-2)%3==0) {
				conference_dates_panel.add(new JLabel(DEADLINES_PANEL[(i-2)/3]));
				System.out.println((i-2)/3);
			}
			if(i%3 == 1) {
				conference_dates_panel.add(new JLabel(""));
				System.out.println((i-2)/3);
			}
			if(i%3 == 0) {
				conference_dates_panel.add(new JLabel("1/1/11"));
				System.out.println((i-2)/3);
			}
				
		}
		conference_dates_panel.setBackground(new Color(204, 204, 153));
		main_panel.add(conference_dates_panel);
		
		JLabel lblNewLabel = new JLabel("Trees: Our Best Friends or Worst Enemies?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(818, 28, 241, 74);
		main_panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit Paper");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(818, 127, 160, 65);
		main_panel.add(btnNewButton);	
	}
	
	private void buildUserDataPanel() {
		JPanel user_info_panel = new JPanel();
		user_info_panel.setBackground(new Color(204, 204, 153));
		user_info_panel.setBounds(23, 28, 160, 71);
		user_info_panel.setLayout(new GridLayout(3, 2));
		JLabel label_2 = new JLabel(" User:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		user_info_panel.add(label_2);
		JLabel label = new JLabel("Biff McMann");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_info_panel.add(label);
		JLabel label_3 = new JLabel(" Position:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		user_info_panel.add(label_3);
		JLabel label_1 = new JLabel("Program Chair");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_info_panel.add(label_1);
		JLabel label_4 = new JLabel(" Date:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		user_info_panel.add(label_4);
		JLabel label_5 = new JLabel("9/23/2013");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_info_panel.add(label_5);
		main_panel.add(user_info_panel);
		
	}

	/**
	 * Builds the scroll panel to view and access the papers within the selected conference.
	 */
	private void buildScrollPanel() {
		JPanel panel_for_scrollpane = new JPanel(new GridLayout(7, 7));
		for(int i = 0; i<7; i++){
			JLabel jl = new JLabel(COLUMN_NAMES[i]);
			jl.setFont(new Font("Tahoma", Font.BOLD, 14));
			Font font = jl.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			jl.setFont(font.deriveFont(attributes));
			panel_for_scrollpane.add(jl);
		}
		for(int i = 8; i<=49; i++){
			if((i-2)%7==0 && i != 2) {
				panel_for_scrollpane.add(new JButton("Manage Button " + i));
			} else {
				panel_for_scrollpane.add(new JLabel("Box" + i));
			}
		}
		panel_for_scrollpane.setBackground(new Color(204, 204, 153));
		panel_for_scrollpane.setSize(10000, 10000);
		JScrollPane scrollPane = new JScrollPane(panel_for_scrollpane, 22, 32);
		System.out.println(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		System.out.println(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 221, 1183, 397);
		main_panel.add(scrollPane);
	}
	
	
	
}
