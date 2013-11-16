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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import view.GUIEnum.StateOfGUI;
import controller.Controller;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;

/**
* The Manage Paper User Interface JPanel
* 
* Allows the user to create access all the information relevant to a paper
* in relation to their permissions associated with that paper
* @author Jacob Hall
* @version 11/15/13
*/

@SuppressWarnings("serial")
public class ManagePaperGUI extends JPanel{

	/*
	 * The background of the main JPanel
	 */
	private static final Color BACKGROUND_COLOR = new Color(153, 204, 204);
	
	/*
	 * The background of the inner user JPanel.
	 */
	private static final Color INNER_BACKGROUND_COLOR = (new Color(204, 204, 153));
	
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
	
	private static final String TEMP_TITLE_STRING = "temp button";

	private static final String TEMP_STRING = "This button is temporary.  " +
			"Eventually, it will be one of many buttons listed in the table above, " +
			"each linking the user to the paper management GUI.  Remove this later " +
			"once those buttons are implemented.";
	
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
	
	private Action my_temp_action;

	/**
	 * Create the JPanel.
	 */
	public ManagePaperGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setupActions();
		setBounds(100, 100, 722, 520);
		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setPreferredSize(WIN_DIMENSION);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Conference Management System (CMS)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 1260, 26);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 48, 1260, 2);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(INNER_BACKGROUND_COLOR);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(235, 109, 831, 519);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//JLabel label = new JLabel(controller.getCurrentUsername().toString());
		//Windows Builder Pro edits out the above line when you use it.  Replace the label
		//declaration when needed because it inserts <dynamic> into the label text 
		JLabel label = new JLabel(controller.getCurrentUsername().toString());
		label.setBounds(20, 69, 176, 20);
		panel.add(label);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton button = new JButton(my_logout_action);
		button.setBounds(20, 100, 176, 22);
		panel.add(button);
		
		JButton button_1 = new JButton(my_back_action);
		button_1.setBounds(20, 170, 176, 22);
		panel.add(button_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(206, 69, 2, 424);
		panel.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 50, 789, 13);
		panel.add(separator_2);
		
		JLabel lblPaperTitle = new JLabel("<dynamic>");
		lblPaperTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaperTitle.setBounds(20, 11, 789, 29);
		panel.add(lblPaperTitle);
		lblPaperTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnMain = new JButton("Main");
		btnMain.setBounds(20, 135, 176, 22);
		panel.add(btnMain);
		
		JButton btnSubmitPaper = new JButton("Submit Paper");
		btnSubmitPaper.setBounds(20, 235, 176, 22);
		panel.add(btnSubmitPaper);
		
		JButton btnEditSubmission = new JButton("Edit Submission");
		btnEditSubmission.setBounds(20, 270, 176, 22);
		panel.add(btnEditSubmission);
		
		JButton btnReview = new JButton("Submit Review");
		btnReview.setBounds(20, 305, 176, 22);
		panel.add(btnReview);
		
		JButton Recommendation = new JButton("Make Recommendation");
		Recommendation.setBounds(20, 340, 176, 22);
		panel.add(Recommendation);
		
		JLabel lblUserTools = new JLabel("User Tools");
		lblUserTools.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserTools.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserTools.setBounds(20, 204, 176, 20);
		panel.add(lblUserTools);
		
		JButton btnAssignReviewer = new JButton("Assign Reviewer");
		btnAssignReviewer.setBounds(20, 375, 176, 22);
		panel.add(btnAssignReviewer);
		
		JButton btnAssignSubpc = new JButton("Assign Sub-PC");
		btnAssignSubpc.setBounds(20, 410, 176, 22);
		panel.add(btnAssignSubpc);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(228, 66, 581, 424);
		panel.add(tabbedPane);
		
		JPanel tabConferenceInfo = new JPanel();
		tabbedPane.addTab("Conference Info", null, tabConferenceInfo, null);
		tabbedPane.setEnabledAt(0, true);
		tabConferenceInfo.setLayout(null);
		
		JLabel lblConferenceTitle = new JLabel("Conference Title:");
		lblConferenceTitle.setBounds(10, 21, 166, 20);
		lblConferenceTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		tabConferenceInfo.add(lblConferenceTitle);
		
		JLabel lblConferenceDate = new JLabel("Conference Date:");
		lblConferenceDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConferenceDate.setBounds(10, 55, 166, 20);
		tabConferenceInfo.add(lblConferenceDate);
		
		JLabel lblPaperStatus = new JLabel("Paper Status:");
		lblPaperStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPaperStatus.setBounds(10, 88, 166, 20);
		tabConferenceInfo.add(lblPaperStatus);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 119, 555, 13);
		tabConferenceInfo.add(separator_3);
		
		JLabel lblImportantDates = new JLabel("Important Dates:");
		lblImportantDates.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportantDates.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblImportantDates.setBounds(10, 128, 555, 20);
		tabConferenceInfo.add(lblImportantDates);
		
		JLabel lblSubpcRecommendationDeadline = new JLabel("Sub-PC Recommendation Deadline:");
		lblSubpcRecommendationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubpcRecommendationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubpcRecommendationDeadline.setBounds(23, 220, 300, 20);
		tabConferenceInfo.add(lblSubpcRecommendationDeadline);
		
		JLabel lblSubmissionDeadline = new JLabel("Submission Deadline:");
		lblSubmissionDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubmissionDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubmissionDeadline.setBounds(20, 160, 300, 20);
		tabConferenceInfo.add(lblSubmissionDeadline);
		
		JLabel lblReviewDeadline = new JLabel("Review Deadline:");
		lblReviewDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReviewDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReviewDeadline.setBounds(20, 190, 300, 20);
		tabConferenceInfo.add(lblReviewDeadline);
		
		JLabel lblAuthorNotificationDeadline = new JLabel("Author Notification Deadline:");
		lblAuthorNotificationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthorNotificationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthorNotificationDeadline.setBounds(23, 250, 300, 20);
		tabConferenceInfo.add(lblAuthorNotificationDeadline);
		
		JPanel tabReviews = new JPanel();
		tabbedPane.addTab("Reviews", null, tabReviews, null);
		tabbedPane.setEnabledAt(1, true);
		tabReviews.setLayout(null);
		
		JPanel tabRecommendation = new JPanel();
		tabbedPane.addTab("Recommendation", null, tabRecommendation, null);
		
		JPanel tabManagement = new JPanel();
		tabbedPane.addTab("Management", null, tabManagement, null);
	}
	
	/**
	 * Getter for the HomeGUI JPanel.
	 * 
	 * @return contentPane JPanel containing the HomeGUI
	 */
	public JComponent getGUI() {
		return contentPane;
	}
	
	/**
	 * Set up the actions to associate events with outside logic
	 */
	private void setupActions(){
		/*
		 * The action associated with clicking Logout
		 */
		my_logout_action = new AbstractAction(LOGOUT_TITLE_STRING, null)
		{		
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setCurrentUsername(""); //blank because they're logging out
					//we need to reset the current user to "null".
				controller.setStateOfGUI(StateOfGUI.LOGIN);
			}
		};
		my_logout_action.putValue(Action.SHORT_DESCRIPTION, LOGOUT_STRING);
		my_logout_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
		
		/*
		 * The action associated with clicking back, in this case, the same as logging out.
		 * In the future, the back button will just take you back to the previous screen,
		 * but for the sake of continuity and flow, I included the back button here as well
		 */
		my_back_action = new AbstractAction(BACK_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setCurrentUsername("");  //blank because in this case, 
					//the user is logging out by going back one screen.
				controller.setStateOfGUI(StateOfGUI.HOME);
			}
		};
		my_back_action.putValue(Action.SHORT_DESCRIPTION, BACK_STRING);
		my_back_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_B);
		
		
		/*
		 * The action associated with clicking the Create New Conference button.
		 */
		my_temp_action = new AbstractAction(TEMP_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
			}
		};
		my_temp_action.putValue(Action.SHORT_DESCRIPTION, TEMP_STRING);
		my_temp_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
	}
}

