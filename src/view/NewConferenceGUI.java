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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import view.GUIEnum.StateOfGUI;
import controller.Conference;
import controller.Controller;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

/**
 * The New Conference Interface JPanel
 * 
 * Allows the user to create a new conference in the CMS.
 * @author Jacob Hall
 * @version 11/13/13
 */

@SuppressWarnings("serial")
public class NewConferenceGUI extends JPanel {
	
	/*
	 * the icon to display the CMS logo
	 */
	private static final ImageIcon ICON = new ImageIcon("src/view/images2.jpg");

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
	
//TODO:
	private JSpinner date_conf_field;
	
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
	
	
	//TODO:
	private JSpinner submission_deadline_field;
	
	private JSpinner review_deadline_field;
	
	private JSpinner sub_pc_recommend_deadline_field;

	private JSpinner author_notification_deadline_field;
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
		
		JButton btnIcon = new JButton("");
		btnIcon.setForeground(BACKGROUND_COLOR);
		btnIcon.setBorder(null);
		btnIcon.setIcon(ICON);
		btnIcon.setBounds(430, 11, 404, 116);
		contentPane.add(btnIcon);		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 127, 1250, 12);
		contentPane.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(INNER_BACKGROUND_COLOR);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(217, 153, 840, 541);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//JLabel label = new JLabel(controller.getCurrentUsername().toString());
		//Windows Builder Pro edits out the above line when you use it.  Replace the label
		//declaration when needed because it inserts <dynamic> into the label text 
		JLabel label = new JLabel(username);
		label.setBounds(10, 22, 157, 20);
		panel_1.add(label);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLogout = new JButton(my_logout_action);
		btnLogout.setBounds(20, 53, 150, 22);
		panel_1.add(btnLogout);
		
		JButton btnMain = new JButton(my_main_action);
		btnMain.setBounds(20, 87, 150, 22);
		panel_1.add(btnMain);
		btnMain.setText("Main");
		
		JButton btnBack = new JButton(my_back_action);
		btnBack.setBounds(20, 121, 150, 22);
		panel_1.add(btnBack);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(182, 22, 14, 499);
		panel_1.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(204, 218, 597, 18);
		panel_1.add(separator_2);
		
		JLabel lblSubmissionDeadline = new JLabel("* Submission Deadline:");
		lblSubmissionDeadline.setBounds(203, 236, 317, 20);
		panel_1.add(lblSubmissionDeadline);
		lblSubmissionDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubmissionDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblReviewDeadline = new JLabel("* Review Deadline:");
		lblReviewDeadline.setBounds(201, 267, 319, 20);
		panel_1.add(lblReviewDeadline);
		lblReviewDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReviewDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblSubPcRecommendation = new JLabel("* Sub-PC Recommendation Deadline:");
		lblSubPcRecommendation.setBounds(203, 298, 317, 20);
		panel_1.add(lblSubPcRecommendation);
		lblSubPcRecommendation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubPcRecommendation.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAuthorNotificationDeadline = new JLabel("* Author Notification Deadline:");
		lblAuthorNotificationDeadline.setBounds(203, 329, 317, 20);
		panel_1.add(lblAuthorNotificationDeadline);
		lblAuthorNotificationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthorNotificationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAssignedPC = new JLabel
				(controller.getFullName(username));
		lblAssignedPC.setBounds(518, 181, 157, 20);
		panel_1.add(lblAssignedPC);
		lblAssignedPC.setHorizontalAlignment(SwingConstants.LEFT);
		lblAssignedPC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblProgramChair = new JLabel("Program Chair:");
		lblProgramChair.setBounds(201, 181, 297, 26);
		panel_1.add(lblProgramChair);
		lblProgramChair.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgramChair.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(201, 360, 597, 18);
		panel_1.add(separator_3);
		
		JLabel lblConferenceSummary = new JLabel("Conference Summary:");
		lblConferenceSummary.setBounds(180, 370, 621, 20);
		panel_1.add(lblConferenceSummary);
		lblConferenceSummary.setHorizontalAlignment(SwingConstants.CENTER);
		lblConferenceSummary.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		conference_summary_field = new JTextArea();
		conference_summary_field.setBounds(216, 401, 581, 66);
		panel_1.add(conference_summary_field);
		
		JLabel lblConferenceTitle = new JLabel("* Conference Title:");
		lblConferenceTitle.setBounds(203, 22, 297, 20);
		panel_1.add(lblConferenceTitle);
		lblConferenceTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblConfDate = new JLabel("* Conference Date:");
		lblConfDate.setBounds(203, 52, 297, 20);
		panel_1.add(lblConfDate);
		lblConfDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblConferenceLocationDeadline = new JLabel("* Conference Location:");
		lblConferenceLocationDeadline.setBounds(203, 90, 297, 20);
		panel_1.add(lblConferenceLocationDeadline);
		lblConferenceLocationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceLocationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		conf_title_field = new JTextField();
		conf_title_field.setBounds(517, 24, 284, 20);
		panel_1.add(conf_title_field);
		conf_title_field.setColumns(10);
		
		JButton btnSubmit = new JButton(my_submit_action);
		btnSubmit.setBounds(417, 489, 159, 29);
		panel_1.add(btnSubmit);
		
		conf_address_field = new JTextField();
		conf_address_field.setBounds(583, 90, 218, 20);
		panel_1.add(conf_address_field);
		conf_address_field.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(518, 90, 58, 20);
		panel_1.add(lblAddress);
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(516, 121, 37, 20);
		panel_1.add(lblCity);
		lblCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblZip = new JLabel("Zip:");
		lblZip.setBounds(518, 150, 31, 20);
		panel_1.add(lblZip);
		lblZip.setHorizontalAlignment(SwingConstants.LEFT);
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		con_zip_field = new JTextField();
		con_zip_field.setBounds(557, 151, 85, 20);
		panel_1.add(con_zip_field);
		con_zip_field.setColumns(10);
		
		conf_state_field = new JTextField();
		conf_state_field.setBounds(708, 122, 93, 20);
		panel_1.add(conf_state_field);
		conf_state_field.setColumns(10);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(652, 121, 47, 20);
		panel_1.add(lblState);
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 401, 583, 68);
		panel_1.add(scrollPane);
		
		conf_city_field = new JTextField();
		conf_city_field.setBounds(557, 122, 85, 20);
		panel_1.add(conf_city_field);
		conf_city_field.setColumns(10);
		
		JLabel lblDenotesRequired = new JLabel("* Required Field");
		lblDenotesRequired.setHorizontalAlignment(SwingConstants.LEFT);
		lblDenotesRequired.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDenotesRequired.setBounds(690, 501, 120, 20);
		panel_1.add(lblDenotesRequired);
		
		date_conf_field = new JSpinner();
		date_conf_field.setModel(new SpinnerDateModel());
		date_conf_field.setBounds(518, 54, 157, 20);
		panel_1.add(date_conf_field);
		
		submission_deadline_field = new JSpinner();
		submission_deadline_field.setModel(new SpinnerDateModel());
		submission_deadline_field.setBounds(539, 236, 136, 20);
		panel_1.add(submission_deadline_field);
		
		review_deadline_field = new JSpinner();
		review_deadline_field.setModel(new SpinnerDateModel());
		review_deadline_field.setBounds(539, 267, 136, 20);
		panel_1.add(review_deadline_field);
		
		sub_pc_recommend_deadline_field = new JSpinner();
		sub_pc_recommend_deadline_field.setModel(new SpinnerDateModel());
		sub_pc_recommend_deadline_field.setBounds(539, 298, 136, 20);
		panel_1.add(sub_pc_recommend_deadline_field);
		
		author_notification_deadline_field = new JSpinner();
		author_notification_deadline_field.setModel(new SpinnerDateModel());
		author_notification_deadline_field.setBounds(539, 329, 136, 20);
		panel_1.add(author_notification_deadline_field);
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
				controller.setCurrentConference(null);
				controller.setCurrentPaper("");
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
			
			
			//TODO: Need to check to verify that the conference doesn't already exist in the system.
			
			
			@Override
			public void actionPerformed(ActionEvent the_event) {
				String conf_title = conf_title_field.getText();
				Date conf_date = (Date)date_conf_field.getValue();
				String conf_address = conf_address_field.getText();
				String conf_city = conf_city_field.getText();
				String conf_state = conf_state_field.getText();
				String con_zip = con_zip_field.getText();
				Date submission_deadline = (Date)submission_deadline_field.getValue();
				Date review_deadline = (Date)review_deadline_field.getValue();
				Date sub_pc_recommend_deadline = (Date)sub_pc_recommend_deadline_field.getValue();
				Date author_notification_deadline = (Date)author_notification_deadline_field.getValue();
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

