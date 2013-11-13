package GUIdesigns;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;

public class ViewConferenceGUI extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 649, 437);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		//GridBagLayout gbl = new GridBagLayout();
		JPanel panel_for_scrollpane = new JPanel(new GridLayout(7, 7));
		for(int i = 1; i<=49; i++){
			if((i-2)%7==0 && i != 2) {
				panel_for_scrollpane.add(new JButton("Mange Button " + i));
			} else {
				panel_for_scrollpane.add(new JLabel("Box" + i));
			}
		}
			
		panel_for_scrollpane.setBackground(new Color(253, 192, 160));
		panel_for_scrollpane.setSize(10000, 10000);
		JScrollPane scrollPane = new JScrollPane(panel_for_scrollpane, 22, 32);
		System.out.println(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		System.out.println(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 221, 613, 168);
		panel.add(scrollPane);
		
		
		JLabel lblViewConferenceGui = new JLabel("View Conference GUI");
		lblViewConferenceGui.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblViewConferenceGui.setBounds(248, 0, 142, 14);
		panel.add(lblViewConferenceGui);
		
		JPanel user_info_panel = new JPanel();
		user_info_panel.setBackground(new Color(255, 255, 204));
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
		panel.add(user_info_panel);
		
		
		JPanel conference_dates_panel = new JPanel();
		conference_dates_panel.setBounds(23, 110, 265, 100);
		user_info_panel.setLayout(new GridLayout(3, 2));
		conference_dates_panel.setBackground(new Color(253, 192, 160));
		panel.add(conference_dates_panel);
		
		JLabel lblNewLabel = new JLabel("Trees: Our Best Friends or Worst Enemies?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(336, 25, 241, 74);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Submit Paper");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(389, 124, 160, 65);
		panel.add(btnNewButton);
		
	}
}
