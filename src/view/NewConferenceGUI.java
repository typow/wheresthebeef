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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.GUIEnum.StateOfGUI;
import controller.Conference;
import controller.Controller;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

/**
 * The Main User Interface JPanel
 * 
 * Allows the user to create a new conference in the CMS.
 * @author Jacob Hall
 * @version 11/13/13
 */

@SuppressWarnings("serial")
public class NewConferenceGUI extends JPanel {

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
	private static final String MAIN_STRING = "Return to Main user interface (ALT+M)";
	
	/*
	 * The text to display on the main button.
	 */
	private static final String MAIN_TITLE_STRING = "Main";
	
	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String SUBMIT_STRING = "Submit the new conference (ALT+S)";
	
	/*
	 * The text to display on the create new conference button.
	 */
	private static final String SUBMIT_TITLE_STRING = "Submit";
	
	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String BACK_STRING = "Navigate to the last screen (ALT+B)";
	
	private JPanel contentPane;
	
	/*
	 * The CMS controller
	 */
	private Controller controller;
	
	/*
	 * the current user logged in using the CMS
	 */
	private String username;
	
	/*
	 * the Action associated with the logout button
	 */
	private Action my_logout_action;
	
	/*
	 * the Action associated with the back button
	 */
	private Action my_back_action;
	
	/*
	 * the Action associated with the Main button
	 */
	private Action my_main_action;
	
	/*
	 * the Action associated with the Submit button
	 */
	private Action my_submit_action;
	
	/*
	 * The JTextField containing the conference title entry.
	 */
	private JTextField conf_title_field;
	
	/*
	 * The JTextField containing the conference date entry.
	 */
	private JTextField conf_date_field;
	
	/*
	 * The JTextField containing the conference address entry.
	 */
	private JTextField conf_address_field;
	
	/*
	 * The JTextField containing the conference city entry.
	 */
	private JTextField conf_city_field;
	
	/*
	 * The JTextField containing the conference state entry.
	 */
	private JTextField conf_state_field;
	
	/*
	 * The JTextField containing the conference zip code entry.
	 */
	private JTextField con_zip_field;
	
	/*
	 * The JTextField containing the submission deadline date entry.
	 */
	private JTextField submission_deadline_field;
	
	/*
	 * The JTextField containing the review deadline date entry.
	 */
	private JTextField review_deadline_field;
	
	/*
	 * The JTextField containing the subprogram chair recommendation deadline date entry.
	 */
	private JTextField sub_pc_recommend_deadline_field;
	
	/*
	 * The JTextField containing the author notification deadline date entry.
	 */
	private JTextField author_notification_deadline_field;

	/*
	 * The JTextArea containing the summary description of the conference.
	 */
	private JTextArea conference_summary_field;
	/**
	 * Create the JPanel.
	 */
	public NewConferenceGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		username = controller.getCurrentUsername().toString();
		setupActions();
		setBounds(100, 100, 722, 520);
		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setPreferredSize(WIN_DIMENSION);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 48, 1260, 2);
		contentPane.add(separator);
		
		JLabel label_1 = new JLabel("Conference Management System (CMS)");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_1.setBounds(10, 11, 1260, 26);
		contentPane.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(INNER_BACKGROUND_COLOR);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(218, 87, 840, 585);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//JLabel label = new JLabel(controller.getCurrentUsername().toString());
		//Windows Builder Pro edits out the above line when you use it.  Replace the label
		//declaration when needed because it inserts <dynamic> into the label text 
		JLabel label = new JLabel(username);
		label.setBounds(10, 46, 157, 20);
		panel_1.add(label);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLogout = new JButton(my_logout_action);
		btnLogout.setBounds(20, 77, 150, 23);
		panel_1.add(btnLogout);
		
		JButton btnMain = new JButton(my_main_action);
		btnMain.setBounds(20, 111, 150, 23);
		panel_1.add(btnMain);
		btnMain.setText("Main");
		
		JButton btnBack = new JButton(my_back_action);
		btnBack.setBounds(20, 145, 150, 23);
		panel_1.add(btnBack);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(182, 46, 14, 499);
		panel_1.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(204, 242, 597, 18);
		panel_1.add(separator_2);
		
		JLabel lblSubmissionDeadline = new JLabel("* Submission Deadline:");
		lblSubmissionDeadline.setBounds(203, 260, 317, 20);
		panel_1.add(lblSubmissionDeadline);
		lblSubmissionDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubmissionDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblReviewDeadline = new JLabel("* Review Deadline:");
		lblReviewDeadline.setBounds(201, 291, 319, 20);
		panel_1.add(lblReviewDeadline);
		lblReviewDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReviewDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblSubPcRecommendation = new JLabel("* Sub-PC Recommendation Deadline:");
		lblSubPcRecommendation.setBounds(203, 322, 317, 20);
		panel_1.add(lblSubPcRecommendation);
		lblSubPcRecommendation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubPcRecommendation.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAuthorNotificationDeadline = new JLabel("* Author Notification Deadline:");
		lblAuthorNotificationDeadline.setBounds(203, 353, 317, 20);
		panel_1.add(lblAuthorNotificationDeadline);
		lblAuthorNotificationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthorNotificationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAssignedPC = new JLabel
				(controller.getFullName(username));
		lblAssignedPC.setBounds(518, 205, 157, 20);
		panel_1.add(lblAssignedPC);
		lblAssignedPC.setHorizontalAlignment(SwingConstants.LEFT);
		lblAssignedPC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblProgramChair = new JLabel("Program Chair:");
		lblProgramChair.setBounds(201, 205, 297, 26);
		panel_1.add(lblProgramChair);
		lblProgramChair.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgramChair.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		submission_deadline_field = new JTextField();
		submission_deadline_field.setBounds(539, 262, 262, 20);
		panel_1.add(submission_deadline_field);
		submission_deadline_field.setColumns(10);
		
		review_deadline_field = new JTextField();
		review_deadline_field.setBounds(539, 293, 262, 20);
		panel_1.add(review_deadline_field);
		review_deadline_field.setColumns(10);
		
		sub_pc_recommend_deadline_field = new JTextField();
		sub_pc_recommend_deadline_field.setBounds(539, 324, 262, 20);
		panel_1.add(sub_pc_recommend_deadline_field);
		sub_pc_recommend_deadline_field.setColumns(10);
		
		author_notification_deadline_field = new JTextField();
		author_notification_deadline_field.setBounds(539, 355, 262, 20);
		panel_1.add(author_notification_deadline_field);
		author_notification_deadline_field.setColumns(10);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(201, 384, 597, 18);
		panel_1.add(separator_3);
		
		JLabel lblConferenceSummary = new JLabel("Conference Summary:");
		lblConferenceSummary.setBounds(180, 394, 621, 20);
		panel_1.add(lblConferenceSummary);
		lblConferenceSummary.setHorizontalAlignment(SwingConstants.CENTER);
		lblConferenceSummary.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		conference_summary_field = new JTextArea();
		conference_summary_field.setBounds(216, 425, 581, 66);
		panel_1.add(conference_summary_field);
		
		JLabel lblConferenceTitle = new JLabel("* Conference Title:");
		lblConferenceTitle.setBounds(203, 46, 297, 20);
		panel_1.add(lblConferenceTitle);
		lblConferenceTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblConfDate = new JLabel("* Conference Date:");
		lblConfDate.setBounds(203, 76, 297, 20);
		panel_1.add(lblConfDate);
		lblConfDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblConferenceLocationDeadline = new JLabel("* Conference Location:");
		lblConferenceLocationDeadline.setBounds(203, 114, 297, 20);
		panel_1.add(lblConferenceLocationDeadline);
		lblConferenceLocationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceLocationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		conf_title_field = new JTextField();
		conf_title_field.setBounds(517, 48, 284, 20);
		panel_1.add(conf_title_field);
		conf_title_field.setColumns(10);
		
		conf_date_field = new JTextField();
		conf_date_field.setBounds(517, 78, 284, 20);
		panel_1.add(conf_date_field);
		conf_date_field.setColumns(10);
		
		JButton btnSubmit = new JButton(my_submit_action);
		btnSubmit.setBounds(417, 513, 159, 29);
		panel_1.add(btnSubmit);
		
		conf_address_field = new JTextField();
		conf_address_field.setBounds(583, 114, 218, 20);
		panel_1.add(conf_address_field);
		conf_address_field.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(518, 114, 58, 20);
		panel_1.add(lblAddress);
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(516, 145, 37, 20);
		panel_1.add(lblCity);
		lblCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblZip = new JLabel("Zip:");
		lblZip.setBounds(518, 174, 31, 20);
		panel_1.add(lblZip);
		lblZip.setHorizontalAlignment(SwingConstants.LEFT);
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		con_zip_field = new JTextField();
		con_zip_field.setBounds(557, 175, 85, 20);
		panel_1.add(con_zip_field);
		con_zip_field.setColumns(10);
		
		conf_state_field = new JTextField();
		conf_state_field.setBounds(708, 146, 93, 20);
		panel_1.add(conf_state_field);
		conf_state_field.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(652, 145, 47, 20);
		panel_1.add(lblState);
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 425, 583, 68);
		panel_1.add(scrollPane);
		
		conf_city_field = new JTextField();
		conf_city_field.setBounds(557, 146, 85, 20);
		panel_1.add(conf_city_field);
		conf_city_field.setColumns(10);
		
		JLabel lblDenotesRequired = new JLabel("* Required Field");
		lblDenotesRequired.setHorizontalAlignment(SwingConstants.LEFT);
		lblDenotesRequired.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDenotesRequired.setBounds(690, 525, 120, 20);
		panel_1.add(lblDenotesRequired);
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
				controller.setStateOfGUI(StateOfGUI.HOME);
			}
		};
		my_back_action.putValue(Action.SHORT_DESCRIPTION, BACK_STRING);
		my_back_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_B);
		
		/*
		 * The action associated with clicking the Main button.
		 */
		my_main_action = new AbstractAction(MAIN_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setStateOfGUI(StateOfGUI.HOME);
			}
		};
		my_main_action.putValue(Action.SHORT_DESCRIPTION, MAIN_STRING);
		my_main_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		
		/*
		 * The action associated with clicking the Submit button.
		 */
		my_submit_action = new AbstractAction(SUBMIT_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				String conf_title = conf_title_field.getText();
				String conf_date = conf_date_field.getText();
				String conf_address = conf_address_field.getText();
				String conf_city = conf_city_field.getText();
				String conf_state = conf_state_field.getText();
				String con_zip = con_zip_field.getText();
				String submission_deadline = submission_deadline_field.getText();
				String review_deadline = review_deadline_field.getText();
				String sub_pc_recommend_deadline = sub_pc_recommend_deadline_field.getText();
				String author_notification_deadline = author_notification_deadline_field.getText();
				String conference_summary = conference_summary_field.getText();
				if (conf_title.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a conference title.");
				}
				else if (conf_date.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a conference date.");
				}
				else if (conf_address.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a street address for the conference.");
				}
				else if (conf_city.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter city for the conference.");
				}
				else if (conf_state.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a state for the conference.");
				}
				else if (con_zip.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a zip code for the conference.");
				}
				else if (submission_deadline.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter the deadline for Authors to submit " +
							"their papers.");
				}
				else if (review_deadline.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter the deadline that all paper reviews must " +
							"be submitted by.");
				}
				else if (sub_pc_recommend_deadline.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter the deadline that the sub-program chair " +
							"recommendation must be submitted by.");
				}
				else if (author_notification_deadline.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter the date which Authors will be notified of " +
							"the status of their paper.");
				}
				else{
					Conference new_conference = new Conference(conf_title, username, conf_date, conf_address, conf_city, conf_state, con_zip, 
							submission_deadline, review_deadline, sub_pc_recommend_deadline, author_notification_deadline, 
							conference_summary);
					//TODO: maybe once the conference is created, a new screen should be populated with the fields entered
					//		with a "success your conference was created" message?
					controller.createNewConference(new_conference);
					controller.setStateOfGUI(StateOfGUI.HOME);
				}
				
			}
		};
		my_submit_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_STRING);
		my_submit_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
	}
}
