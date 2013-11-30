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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import view.GUIEnum.StateOfGUI;
import controller.Conference;
import controller.Controller;

/**
 * The Main User Interface JPanel
 * 
 * Allows the user to create new conferences, see conferences they're associated
 * with, and see all other upcoming conferences.
 * 
 * @author Jacob Hall
 * @author Warrick Holfeld
 * @version 90 Date: 11/27/13
 */
@SuppressWarnings("serial")
public class HomeGUI extends JPanel {

	/*
	 * the icon to display the CMS logo
	 */
	private static final ImageIcon ICON = new ImageIcon("src/view/images2.jpg");

	private static final String[] COLUMN_NAMES = { "Conference",
			"Conference Date" };

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
	 * The size of the JPanel.
	 */
	private static final Dimension WIN_DIMENSION = new Dimension(1280, 720);

	/*
	 * The text to display on the logout button.
	 */
	private static final String LOGOUT_TITLE_STRING = "Logout";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String LOGOUT_STRING = "Logout of the system (ALT+L)";

	/*
	 * The text to display on the logout button.
	 */
	private static final String BACK_TITLE_STRING = "<-- Back";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String BACK_STRING = "Navigate to the last screen (ALT+B)";

	/*
	 * The text to display on the create new conference button.
	 */
	private static final String NEW_CONF_TITLE_STRING = "Create New Conference";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String NEW_CONF_STRING = "Create new conference in the CMS (ALT+N)";

	// TODO: this is temporary. It will be removed later.
	private static final String TEMP_TITLE_STRING = "temp: one of many btns in the table above linked to a specific paper and conf, user has a relation";

	// TODO: this is temporary. It will be removed later.
	private static final String TEMP_STRING = "This button is temporary.  "
			+ "Eventually, it will be one of many buttons listed in the table above, "
			+ "each linking the user to the paper management GUI.  Remove this later "
			+ "once those buttons are implemented.";
	// TODO: this is temporary. It will be removed later.
	private static final String TEMP_TITLE_STRING_TWO = "temp: on of many btns in table above, no paper in focus, user has no realtion to the conf yet";
	// TODO: this is temporary. It will be removed later.
	private static final String TEMP_STRING_TWO = "This button is temporary.  "
			+ "Eventually, it will be one of many buttons listed in the table above, "
			+ "each linking the user to the paper management GUI.  Remove this later "
			+ "once those buttons are implemented.";

	private JPanel contentPane;

	/*
	 * The CMS controller
	 */
	private Controller controller;

	/*
	 * the Action associated with the logout button
	 */
	private Action my_logout_action;

	/*
	 * the Action associated with the back button
	 */
	private Action my_back_action;

	/*
	 * the Action associated with the new conference button
	 */
	private Action my_new_conf_action;

	// TODO: this is temporary. It will be removed later.
	private Action my_temp_paper_selected_action;
	// TODO: this is temporary. It will be removed later.
	private Action my_temp_conf_selected_action;

	private JPanel inner_panel;

	/**
	 * Create the JPanel that contains the HomeGUI.
	 * 
	 * <dt><b>Preconditions: The controller object has been instantiated.</b>
	 * <dd>
	 * <dt><b>Postconditions: A JPanel is created to represent the most current
	 * data and status concerning a paper</b>
	 * <dd>
	 * 
	 * @param the_controller
	 */
	public HomeGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setupActions();
		setBounds(100, 100, 722, 520);
		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setPreferredSize(WIN_DIMENSION);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		// creates the title icon at the top of the panel
		JButton btnIcon = new JButton("");
		btnIcon.setForeground(BACKGROUND_COLOR);
		btnIcon.setBorder(null);
		btnIcon.setIcon(ICON);
		btnIcon.setBounds(430, 11, 404, 116);
		contentPane.add(btnIcon);
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 127, 1250, 12);
		contentPane.add(separator);

		inner_panel = new JPanel();
		inner_panel.setBackground(INNER_BACKGROUND_COLOR);
		inner_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		inner_panel.setBounds(281, 182, 756, 469);
		contentPane.add(inner_panel);
		inner_panel.setLayout(null);

		JLabel username_label = new JLabel(controller.getCurrentUsername()
				.toString());
		username_label.setBounds(20, 66, 150, 20);
		inner_panel.add(username_label);

		username_label.setHorizontalAlignment(SwingConstants.CENTER);
		username_label.setFont(new Font("Tahoma", Font.BOLD, 16));

		JButton logout_button = new JButton(my_logout_action);
		logout_button.setBounds(20, 97, 150, 22);
		inner_panel.add(logout_button);

		JButton back_button = new JButton(my_back_action);
		back_button.setBounds(20, 130, 152, 22);
		inner_panel.add(back_button);
		JButton new_conf_button = new JButton(my_new_conf_action);
		new_conf_button.setBounds(22, 163, 150, 22);
		inner_panel.add(new_conf_button);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(182, 70, 2, 352);
		inner_panel.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);

		JLabel lblUsername = new JLabel("My Conferences");
		lblUsername.setBounds(204, 67, 143, 20);
		inner_panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));

		createMyConferencesScrollPane();
		
		createUpcomingConferencesScrollPane();

		JLabel lblUpcomingConferences = new JLabel("Upcoming Conferences");
		lblUpcomingConferences.setBounds(204, 266, 213, 20);
		inner_panel.add(lblUpcomingConferences);
		lblUpcomingConferences.setFont(new Font("Tahoma", Font.BOLD, 16));

		

		JLabel lblUserHomePage = new JLabel("User Home Page");
		lblUserHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserHomePage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserHomePage.setBounds(21, 21, 711, 20);
		inner_panel.add(lblUserHomePage);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 52, 712, 20);
		inner_panel.add(separator_2);

		JButton btnNewButton = new JButton(my_temp_paper_selected_action);
		btnNewButton.setBounds(38, 358, 89, 23);
		inner_panel.add(btnNewButton);
	}

	/**
	 * 
	 * 
	 * <dt><b>Preconditions: </b>
	 * <dd>
	 * <dt><b>Postconditions: The scroll pane for the upcoming conferences is created</b>
	 * <dd>
	 * 
	 */
	private void createUpcomingConferencesScrollPane() {
		
		//needs to be changed to getUpcommingConferences
		Conference[] upcoming_conference_array = controller
				.getMyConferences(controller.getCurrentUsername().toString());
		
		int total_upcoming_conf = 0;
		

		if(upcoming_conference_array != null) {
			total_upcoming_conf = upcoming_conference_array.length;
		}
		

		JPanel panel_for_scrollpane = new JPanel(new GridLayout(
				(total_upcoming_conf + 1), COLUMN_NAMES.length));

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
		

		if (upcoming_conference_array != null) {
			for (Conference element : upcoming_conference_array) {
				final String conference_title = element.getConfTitle();
				final Conference upcoming_conference = element;
				final JLabel conference_name_jl = new JLabel(conference_title,
						JLabel.CENTER);
				conference_name_jl.setBorder(BorderFactory
						.createLineBorder(Color.BLACK));
				conference_name_jl.setForeground(Color.BLUE);
				conference_name_jl.setCursor(new java.awt.Cursor(
						java.awt.Cursor.HAND_CURSOR));
				final String box_clicked = conference_title + "box clicked";
				final Font entered = new Font("Tahoma", Font.BOLD, 17);
				final Font exited = new Font("Tahoma", Font.BOLD, 12);
				conference_name_jl.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {

					}

					public void mouseEntered(MouseEvent arg0) {
						conference_name_jl.setFont(entered);
					}

					public void mouseExited(MouseEvent arg0) {
						conference_name_jl.setFont(exited);
					}

					public void mousePressed(MouseEvent arg0) {
						System.out.println(box_clicked);
						controller.setCurrentConference(upcoming_conference);
						controller.setStateOfGUI(StateOfGUI.CONFERENCE);
					}

					public void mouseReleased(MouseEvent arg0) {
					}
				});
				panel_for_scrollpane.add(conference_name_jl);
				JLabel date_jl = new JLabel(element.getConfDate().toString(),
						JLabel.CENTER);
				date_jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel_for_scrollpane.add(date_jl);
			}
			
		}
		

		panel_for_scrollpane.setSize(10000, 10000);
		JScrollPane upcoming_conferences_scrollpane = new JScrollPane(panel_for_scrollpane, 22, 32);
		upcoming_conferences_scrollpane.setBounds(204, 297, 514, 125);
		upcoming_conferences_scrollpane.setViewportBorder(new BevelBorder(
				BevelBorder.LOWERED, null, null, null, null));
		inner_panel.add(upcoming_conferences_scrollpane);
		

		
	}

	/**
	 * 
	 * 
	 * <dt><b>Preconditions: </b>
	 * <dd>
	 * <dt><b>Postconditions: the scroll pane for the users confernces is created</b>
	 * <dd>
	 * 
	 * @return contentPane JPanel containing the AcceptRejectGUI.
	 */
	private void createMyConferencesScrollPane() {
		
		Conference[] curr_conference_array = controller
				.getMyConferences(controller.getCurrentUsername().toString());
		int total_current_conf = 0;
		
		if(curr_conference_array != null) {
			total_current_conf = curr_conference_array.length;
		}
		

		JPanel panel_for_scrollpane = new JPanel(new GridLayout(
				(total_current_conf + 1), COLUMN_NAMES.length));

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
		
		

		if (curr_conference_array != null) {
			for (Conference element : curr_conference_array) {
				final String conference_title = element.getConfTitle();
				final Conference curr_conference = element;
				final JLabel conference_name_jl = new JLabel(conference_title,
						JLabel.CENTER);
				conference_name_jl.setBorder(BorderFactory
						.createLineBorder(Color.BLACK));
				conference_name_jl.setForeground(Color.BLUE);
				conference_name_jl.setCursor(new java.awt.Cursor(
						java.awt.Cursor.HAND_CURSOR));
				final String box_clicked = conference_title + "box clicked";
				final Font entered = new Font("Tahoma", Font.BOLD, 17);
				final Font exited = new Font("Tahoma", Font.BOLD, 12);
				conference_name_jl.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent arg0) {

					}

					public void mouseEntered(MouseEvent arg0) {
						conference_name_jl.setFont(entered);
					}

					public void mouseExited(MouseEvent arg0) {
						conference_name_jl.setFont(exited);
					}

					public void mousePressed(MouseEvent arg0) {
						System.out.println(box_clicked);
						controller.setCurrentConference(curr_conference);
						controller.setStateOfGUI(StateOfGUI.CONFERENCE);
					}

					public void mouseReleased(MouseEvent arg0) {
					}
				});
				panel_for_scrollpane.add(conference_name_jl);
				JLabel date_jl = new JLabel(element.getConfDate().toString(),
						JLabel.CENTER);
				date_jl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel_for_scrollpane.add(date_jl);
			}
			
		}
		
//		panel_for_scrollpane.setBackground(BACKGROUND_COLOR);
		panel_for_scrollpane.setSize(10000, 10000);
		JScrollPane my_conferences_scrollpane = new JScrollPane(panel_for_scrollpane, 22, 32);
		my_conferences_scrollpane.setBounds(204, 98, 514, 125);
		my_conferences_scrollpane.setViewportBorder(new BevelBorder(
				BevelBorder.LOWERED, null, null, null, null));
		inner_panel.add(my_conferences_scrollpane);
	}

	/**
	 * Getter for the HomeGUI JPanel.
	 * 
	 * <dt><b>Preconditions: The HomeGUI has already been instantiated.</b>
	 * <dd>
	 * <dt><b>Postconditions: The HomeGUI JPanel is returned.</b>
	 * <dd>
	 * 
	 * @return contentPane JPanel containing the HomeGUI.
	 */
	public JComponent getGUI() {
		return contentPane;
	}

	/**
	 * Set up the actions to associate events with outside logic
	 * 
	 * <dt><b>Preconditions: The HomeGUI is instantiated.</b>
	 * <dd>
	 * <dt><b>Postconditions: actions associated with each button will be
	 * returned.</b>
	 * <dd>
	 */
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
		 * The action associated with clicking back, in this case, the same as
		 * logging out. In the future, the back button will just take you back
		 * to the previous screen, but for the sake of continuity and flow, I
		 * included the back button here as well
		 */
		my_back_action = new AbstractAction(BACK_TITLE_STRING, null) {
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setCurrentUsername(""); // blank because in this
													// case,
				// the user is logging out by going back one screen.
				controller.setCurrentConference(null);
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.LOGIN);
			}
		};
		my_back_action.putValue(Action.SHORT_DESCRIPTION, BACK_STRING);
		my_back_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_B);

		/*
		 * The action associated with clicking the Create New Conference button.
		 */
		my_new_conf_action = new AbstractAction(NEW_CONF_TITLE_STRING, null) {
			@Override
			public void actionPerformed(ActionEvent the_event) {
				System.out.println("Creating new conference");
				controller.setStateOfGUI(StateOfGUI.NEW_CONFERENCE);
			}
		};
		my_new_conf_action.putValue(Action.SHORT_DESCRIPTION, NEW_CONF_STRING);
		my_new_conf_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);

		/*
		 * The action associated with clicking the button that is associated
		 * with a paper and conference. This will eventually be a table of
		 * buttons each linked to a paper and conference.
		 */
		my_temp_paper_selected_action = new AbstractAction(TEMP_TITLE_STRING,
				null) {
			@Override
			public void actionPerformed(ActionEvent the_event) {
				// TODO: the button needs to be associated with a paper and
				// conference title. When
				// the user selects the button, need to set the paper title in
				// the controller
				// so that it knows what paper is in focus if any.
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
			}
		};
		my_temp_paper_selected_action.putValue(Action.SHORT_DESCRIPTION,
				TEMP_STRING);
		my_temp_paper_selected_action.putValue(Action.MNEMONIC_KEY,
				KeyEvent.VK_N);

		/*
		 * The action associated with clicking the button that is just a link to
		 * a conference. There is no paper in focus yet. This is one of many
		 * buttons in the table each linking to a conference, but the user has
		 * no relation to the conference yet.
		 */
		my_temp_conf_selected_action = new AbstractAction(
				TEMP_TITLE_STRING_TWO, null) {
			@Override
			public void actionPerformed(ActionEvent the_event) {
				// TODO: the button needs to be associated with a paper and
				// conference title. When
				// the user selects the button, need to set the paper title in
				// the controller
				// so that it knows what paper is in focus if any.

				// TODO: this button also needs to set the current_conf in the
				// controller so that
				// it knows what conference is in focus. The next GUI can then
				// retrieve
				// the relevant data about that conference. As of right now,
				// because the table
				// isn't set up, we don't know what conference to set as the one
				// in focus
				// However, since the user won't have a relation to any of these
				// conferences,
				// meaning they aren't an Author, PC, Subpc,....., there is no
				// paper in focus,
				// so set it to "". Blank.

				// controller.setCurrentConference(the_conference_name);
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
			}
		};
		my_temp_conf_selected_action.putValue(Action.SHORT_DESCRIPTION,
				TEMP_STRING_TWO);
		my_temp_conf_selected_action.putValue(Action.MNEMONIC_KEY,
				KeyEvent.VK_T);
	}
}
