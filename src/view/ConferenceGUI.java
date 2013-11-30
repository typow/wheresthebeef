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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

import controller.Controller;

/**
 * The View Conference Panel
 * 
 * Allows the user to see an overview of conference and view the attached paper
 * objects
 * 
 * @author Warrick Holfeld
 * @version 11/29/13
 */
@SuppressWarnings("serial")
public class ConferenceGUI extends JPanel {

	private JPanel contentPane;
	
	/*
	 * the icon to display the CMS logo
	 */
	private static final ImageIcon ICON = new ImageIcon("src/view/images2.jpg");

	private static final String[] COLUMN_NAMES = { "Paper", "Author", "Status",
			"Program Chair", "SubPC", "Reviewers" };

	private static final String[] DEADLINES_PANEL = { "Submit", "Review",
			"Recommend", "Decision", "Conference Date" };

	private JPanel main_panel;

	
	/*
	 * The background of the main JPanel
	 */
	private static final Color BACKGROUND_COLOR = new Color(153, 204, 204);
	
	/*
	 * The background of the inner user JPanel.
	 */
	private static final Color INNER_BACKGROUND_COLOR = (new Color(204, 204, 153));
	
	/*
	 * The CMS controller
	 */
	private Controller controller;

	/**
	 * Create the frame.
	 */
	public ConferenceGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));

		
		JButton btnIcon = new JButton("");
		btnIcon.setForeground(BACKGROUND_COLOR);
		btnIcon.setBorder(null);
		btnIcon.setIcon(ICON);
		btnIcon.setBounds(430, 11, 404,116);
		contentPane.add(btnIcon);

		main_panel = new JPanel();
		main_panel.setBackground(BACKGROUND_COLOR);
		contentPane.add(main_panel, BorderLayout.CENTER);
		main_panel.setLayout(null);

		buildScrollPanel();

		buildUserDataPanel();

		JPanel conference_dates_panel = new JPanel();
		conference_dates_panel.setBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		conference_dates_panel.setBounds(848, 234, 397, 100);
		conference_dates_panel.setLayout(new GridLayout(5, 3));
		JLabel deadline_label = new JLabel(" Deadline Dates:");
		deadline_label.setFont(new Font("Tahoma", Font.BOLD, 11));
		conference_dates_panel.add(deadline_label);
		for (int i = 2; i <= 15; i++) {
			if ((i - 2) % 3 == 0) {
				conference_dates_panel.add(new JLabel(
						DEADLINES_PANEL[(i - 2) / 3]));
			}
			if (i % 3 == 1) {
				conference_dates_panel.add(new JLabel(""));
			}
			if (i % 3 == 0) {
				conference_dates_panel.add(new JLabel("1/1/11"));
			}

		}
		conference_dates_panel.setBackground(INNER_BACKGROUND_COLOR);
		main_panel.add(conference_dates_panel);

		JButton btnNewButton = new JButton("Submit Paper");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(429, 234, 404, 101);
		main_panel.add(btnNewButton);
		
		JPanel conference_panel = new JPanel();
		conference_panel.setBounds(201, 137, 1044, 91);//(848, 234, 397, 100);
		conference_panel.setBackground(INNER_BACKGROUND_COLOR);
		conference_panel.setBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		conference_panel.setLayout(new BorderLayout(0, 0));
		
		main_panel.add(conference_panel);
		JLabel conference_title_string = new JLabel(
				"Trees: Our Best Friends or Worst Enemies?", JLabel.CENTER);
		conference_panel.add(conference_title_string, BorderLayout.CENTER);
		conference_title_string.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 127, 1250, 12);
		main_panel.add(separator_2);
		
				
	}

	/**
	 * Builds the the panel that shows the users info.
	 */
	private void buildUserDataPanel() {
		JPanel user_info_panel = new JPanel();
		user_info_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		user_info_panel.setBackground(INNER_BACKGROUND_COLOR);
		user_info_panel.setBounds(201, 234, 213, 100);
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
	 * Launch the application.
	 */
	public JComponent getGUI() {
		return contentPane;
	}

	/**
	 * Builds the scroll panel to view and access the papers within the selected
	 * conference.
	 */
	private void buildScrollPanel() {
		JPanel panel_for_scrollpane = new JPanel(new GridLayout(7, COLUMN_NAMES.length));
		panel_for_scrollpane.setBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));
		for (int i = 0; i < COLUMN_NAMES.length; i++) {
			JLabel jl = new JLabel(COLUMN_NAMES[i], JLabel.CENTER);
			jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jl.setFont(new Font("Tahoma", Font.BOLD, 14));
			Font font = jl.getFont();
			Map attributes = font.getAttributes();
			attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
			jl.setFont(font.deriveFont(attributes));
			panel_for_scrollpane.add(jl);
		}
		for (int i = COLUMN_NAMES.length+1; i <= 42; i++) {

			if ((i - 1) % COLUMN_NAMES.length == 0) {
				final JLabel jl = new JLabel("Box " + i, JLabel.CENTER);
				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jl.setForeground(Color.BLUE);
				jl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
				final String box_clicked = "clicked box " + i;
				final Font entered = new Font("Tahoma", Font.BOLD, 17);
				final Font exited = new Font("Tahoma", Font.BOLD, 12);
				jl.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {

					}

					public void mouseEntered(MouseEvent arg0) {
						jl.setFont(entered);
					}

					public void mouseExited(MouseEvent arg0) {
						jl.setFont(exited);
					}

					public void mousePressed(MouseEvent arg0) {
						System.out.println(box_clicked);
					}

					public void mouseReleased(MouseEvent arg0) {
					}
				});
				panel_for_scrollpane.add(jl);
			} else {
				JLabel jl = new JLabel("Box" + i, JLabel.CENTER);
				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel_for_scrollpane.add(jl);
			}

		}

		panel_for_scrollpane.setBackground(INNER_BACKGROUND_COLOR);
		panel_for_scrollpane.setSize(10000, 10000);
		JScrollPane scrollPane = new JScrollPane(panel_for_scrollpane, 22, 32);
		scrollPane.setBounds(10, 350, 1235, 300);
		main_panel.add(scrollPane);

	}
}
