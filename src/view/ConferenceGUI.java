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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;

import view.GUIEnum.StateOfGUI;
import controller.Controller;
import controller.Paper;

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
	private static final Color INNER_BACKGROUND_COLOR = (new Color(204, 204,
			153));

	/*
	 * The CMS controller
	 */
	private Controller controller;

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String BACK_STRING = "Navigate to the last screen (ALT+B)";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String SUBMIT_STRING = "Navigate to the submit paper screen (ALT+S)";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String LOGOUT_STRING = "Logout of the system (ALT+L)";

	/*
	 * the Action associated with the logout button
	 */
	private Action my_logout_action;

	/*
	 * the Action associated with the submit button
	 */
	private Action my_submit_action;

	/*
	 * the Action associated with the back button
	 */
	private Action my_back_action;

	/*
	 * The size of the JPanel.
	 */
	private static final Dimension WIN_DIMENSION = new Dimension(1280, 720);

	/*
	 * The text to display on the logout button.
	 */
	private static final String LOGOUT_TITLE_STRING = "Logout";

	/*
	 * The text to display on the logout button.
	 */
	private static final String SUBMIT_TITLE_STRING = "Submit Paper";

	/*
	 * The text to display on the logout button.
	 */
	private static final String BACK_TITLE_STRING = "<-- Back";

	/**
	 * Create the frame.
	 */
	public ConferenceGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		contentPane = new JPanel();
		contentPane.setPreferredSize(WIN_DIMENSION);
		contentPane.setLayout(new BorderLayout(0, 0));

		JButton btnIcon = new JButton("");
		btnIcon.setForeground(BACKGROUND_COLOR);
		btnIcon.setBorder(null);
		btnIcon.setIcon(ICON);
		btnIcon.setBounds(430, 11, 404, 116);
		contentPane.add(btnIcon);

		main_panel = new JPanel();
		main_panel.setBackground(BACKGROUND_COLOR);
		contentPane.add(main_panel, BorderLayout.CENTER);
		main_panel.setLayout(null);

		setupActions();

		buildButtonPanel();

		buildScrollPanel();

		buildUserDataPanel();

		submitPapersetup();

		createConferenceDateArray();

		JPanel conference_panel = new JPanel();
		conference_panel.setBounds(10, 137, 1235, 91);// (848, 234, 397, 100);
		conference_panel.setBackground(INNER_BACKGROUND_COLOR);
		conference_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		conference_panel.setLayout(new BorderLayout(0, 0));

		main_panel.add(conference_panel);
		JLabel conference_title_string = new JLabel(controller
				.getCurrentConference().getConfTitle(), JLabel.CENTER);
		conference_panel.add(conference_title_string, BorderLayout.CENTER);
		conference_title_string.setFont(new Font("Tahoma", Font.BOLD, 15));

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 127, 1250, 12);
		main_panel.add(separator_2);

	}

	private void createConferenceDateArray() {
		JPanel conference_dates_panel = new JPanel();
		String[] conference_date_array = new String[5];
		conference_date_array[0] = controller.getCurrentConference()
				.getSubmissionDead().toString();
		conference_date_array[1] = controller.getCurrentConference()
				.getReviewDead().toString();
		conference_date_array[2] = controller.getCurrentConference()
				.getSubPCReccomendDead().toString();
		conference_date_array[3] = controller.getCurrentConference()
				.getAuthorNotificationDead().toString();
		conference_date_array[4] = controller.getCurrentConference()
				.getConfDate().toString();
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
				conference_dates_panel.add(new JLabel(
						conference_date_array[((i / 3) - 1)]));
			}

		}
		conference_dates_panel.setBackground(INNER_BACKGROUND_COLOR);
		main_panel.add(conference_dates_panel);

	}

	private void submitPapersetup() {
		JButton submit_button = new JButton(my_submit_action);
		submit_button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		submit_button.setBounds(429, 234, 404, 101);
		main_panel.add(submit_button);

	}

	private void buildButtonPanel() {
		JPanel button_panel = new JPanel();
		button_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		button_panel.setBackground(INNER_BACKGROUND_COLOR);
		button_panel.setBounds(10, 234, 182, 100);

		JButton logout_button = new JButton(my_logout_action);
		logout_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_panel.setLayout(null);
		logout_button.setBounds(15, 16, 150, 22);
		button_panel.add(logout_button);

		JButton back_button = new JButton(my_back_action);
		back_button.setBounds(15, 54, 150, 22);
		button_panel.add(back_button);

		main_panel.add(button_panel);

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
		user_info_panel.setLayout(new GridLayout(2, 2));
		JLabel label_2 = new JLabel(" User:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		user_info_panel.add(label_2);
		JLabel label = new JLabel(controller.getCurrentUsername());
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		user_info_panel.add(label);
		JLabel label_4 = new JLabel(" Date:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		user_info_panel.add(label_4);
		String timestamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar
				.getInstance().getTime());
		JLabel label_5 = new JLabel(timestamp);
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

		// use get my author status

		//Seth look here to test this for loop for paper objects
		int number_of_papers = 0;

		Paper[] my_paper_array = controller.getMyPapers(
				controller.getCurrentConference(),
				controller.getCurrentUsername());

		if (my_paper_array != null) {
			number_of_papers = my_paper_array.length;
		}	
	
		System.out.println("number of papers in array = " + number_of_papers);
		for (int i = 0; i < number_of_papers; i++) {
			System.out.println("paper object title = "
					+ my_paper_array[i].getPaperTitle());
			System.out.println("paper object author = "
					+ my_paper_array[i].getAuthor());
			System.out.println("paper object author status viewable = "
					+ my_paper_array[i].getStatusAuthorViewable());
			System.out.println("Conference object Program Chair = "
					+ controller.getCurrentConference().getProgramChair());
			System.out.println("Paper object SubPC = "
					+ my_paper_array[i].getSubPC());
			String[] reviewer_array = my_paper_array[i].getReviewers();
			if (reviewer_array != null) {
				System.out.println("Reviewer array lenthth = "
						+ reviewer_array.length);
				for (int j = 0; j < reviewer_array.length; j++) {
					System.out.println("reviewer[" + j + "] is "
							+ reviewer_array[j]);
				}
			}
		}

		JPanel panel_for_scrollpane = new JPanel(new GridLayout(
				(number_of_papers + 1), COLUMN_NAMES.length));
		panel_for_scrollpane.setBorder(new BevelBorder(BevelBorder.LOWERED,
				null, null, null, null));

		// Create column headers
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
		// populate columns with paper data
//		for (int i = COLUMN_NAMES.length + 1; i <= ((number_of_papers + 1) * COLUMN_NAMES.length); i++) {
//
//			if ((i - 1) % COLUMN_NAMES.length == 0) {
//
//				final Paper curr_paper = my_paper_array[(i / COLUMN_NAMES.length) - 1];
//				final JLabel jl = new JLabel(curr_paper.getPaperTitle(),
//						JLabel.CENTER);
//				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//				jl.setForeground(Color.BLUE);
//				jl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//				final String box_clicked = "clicked box " + i;
//				final Font entered = new Font("Tahoma", Font.BOLD, 17);
//				final Font exited = new Font("Tahoma", Font.BOLD, 12);
//				jl.addMouseListener(new MouseListener() {
//					public void mouseClicked(MouseEvent arg0) {
//
//					}
//
//					public void mouseEntered(MouseEvent arg0) {
//						jl.setFont(entered);
//					}
//
//					public void mouseExited(MouseEvent arg0) {
//						jl.setFont(exited);
//					}
//
//					public void mousePressed(MouseEvent arg0) {
//						controller.setCurrentPaper(curr_paper.getPaperTitle());
//						controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
//					}
//
//					public void mouseReleased(MouseEvent arg0) {
//					}
//				});
//				panel_for_scrollpane.add(jl);
//			} else {
//				JLabel jl = new JLabel("Box" + i, JLabel.CENTER);
//				jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//				panel_for_scrollpane.add(jl);
//			}
//
//		}

		panel_for_scrollpane.setBackground(INNER_BACKGROUND_COLOR);
		panel_for_scrollpane.setSize(10000, 10000);
		JScrollPane scrollPane = new JScrollPane(panel_for_scrollpane, 22, 32);
		scrollPane.setBounds(10, 350, 1235, 300);
		main_panel.add(scrollPane);

	}

	private void setupActions() {
		/*
		 * The action associated with clicking Logout
		 */
		my_logout_action = new AbstractAction(LOGOUT_TITLE_STRING, null) {
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setCurrentUsername(""); // blank because they're
													// logging out
				// we need to reset the current user to "null".
				controller.setCurrentConference(null);
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.LOGIN);
			}
		};
		my_logout_action.putValue(Action.SHORT_DESCRIPTION, LOGOUT_STRING);
		my_logout_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);

		/*
		 * The action associated with clicking back, in this case the homeGUI
		 * screen.
		 */
		my_back_action = new AbstractAction(BACK_TITLE_STRING, null) {
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setCurrentConference(null);
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.HOME);
			}
		};
		my_back_action.putValue(Action.SHORT_DESCRIPTION, BACK_STRING);
		my_back_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_B);

		/*
		 * The action associated with clicking submit paper
		 */
		my_submit_action = new AbstractAction(SUBMIT_TITLE_STRING, null) {
			@Override
			public void actionPerformed(ActionEvent the_event) {
				// the user is logging out by going back one screen.
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.SUBMIT_PAPER);
			}
		};
		my_submit_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_STRING);
		my_submit_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);

	}
}
