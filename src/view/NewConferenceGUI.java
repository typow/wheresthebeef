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

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.GUIEnum.StateOfGUI;
import controller.Controller;
import javax.swing.JTextField;

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
	private static final String NEW_CONF_STRING = "Create new conference in the CMS (ALT+N)";
	
	/*
	 * The text to display on the create new conference button.
	 */
	private static final String NEW_CONF_TITLE_STRING = "Create New Conference";
	
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
	private JTextField conf_title;
	private JTextField conf_date;
	private JTextField conf_address;
	private JTextField conf_city;
	private JTextField conf_state;
	private JTextField con_zip;
	private JTextField submission_deadline;
	private JTextField review_deadline;
	private JTextField sub_pc_recommend_deadline;
	private JTextField author_notification_deadline;
	private JTextField textField;

	/**
	 * Create the JPanel.
	 */
	public NewConferenceGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setupActions();
		setBounds(100, 100, 722, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(153, 66, 14, 457);
		contentPane.add(separator_1);
		
		JLabel lblConferenceTitle = new JLabel("Conference Title:");
		lblConferenceTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConferenceTitle.setBounds(177, 76, 297, 20);
		contentPane.add(lblConferenceTitle);
		
		JLabel lblNewLabel = new JLabel("Conference Management System (CMS)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(177, 11, 399, 26);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 48, 754, 2);
		contentPane.add(separator);
		
		JButton button = new JButton(my_logout_action);
		button.setBounds(10, 90, 137, 22);
		contentPane.add(button);
		
		JButton button_1 = new JButton(my_back_action);
		button_1.setBounds(10, 118, 137, 22);
		contentPane.add(button_1);
		JButton button_2 = new JButton(my_new_conf_action);
		button_2.setBounds(10, 146, 137, 22);
		contentPane.add(button_2);
		
		//JLabel label = new JLabel(controller.getCurrentUsername().toString());
		//Windows Builder Pro edits out the above line when you use it.  Replace the label
		//declaration when needed because it inserts <dynamic> into the label text 
		JLabel label = new JLabel(controller.getCurrentUsername().toString());
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(10, 66, 133, 20);
		contentPane.add(label);
		contentPane.setPreferredSize(new Dimension(800, 555));
		
		JLabel lblConfDate = new JLabel("Conference Date:");
		lblConfDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConfDate.setBounds(177, 107, 297, 20);
		contentPane.add(lblConfDate);
		
		JLabel lblSubmissionDeadline = new JLabel("Submission Deadline:");
		lblSubmissionDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubmissionDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubmissionDeadline.setBounds(177, 291, 297, 20);
		contentPane.add(lblSubmissionDeadline);
		
		JLabel lblReviewDeadline = new JLabel("Review Deadline:");
		lblReviewDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReviewDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReviewDeadline.setBounds(177, 322, 297, 20);
		contentPane.add(lblReviewDeadline);
		
		JLabel lblSubPcRecommendation = new JLabel("Sub-PC Recommendation Deadline:");
		lblSubPcRecommendation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubPcRecommendation.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubPcRecommendation.setBounds(177, 353, 297, 20);
		contentPane.add(lblSubPcRecommendation);
		
		JLabel lblAuthorNotificationDeadline = new JLabel("Author Notification Deadline:");
		lblAuthorNotificationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthorNotificationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuthorNotificationDeadline.setBounds(177, 384, 297, 20);
		contentPane.add(lblAuthorNotificationDeadline);
		
		JLabel lblConferenceLocationDeadline = new JLabel("Conference Location:");
		lblConferenceLocationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceLocationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConferenceLocationDeadline.setBounds(177, 145, 297, 20);
		contentPane.add(lblConferenceLocationDeadline);
		
		JLabel lblProgramChair = new JLabel("Program Chair:");
		lblProgramChair.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgramChair.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProgramChair.setBounds(177, 236, 297, 26);
		contentPane.add(lblProgramChair);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(177, 273, 597, 18);
		contentPane.add(separator_2);
		
		conf_title = new JTextField();
		conf_title.setBounds(490, 78, 284, 20);
		contentPane.add(conf_title);
		conf_title.setColumns(10);
		
		conf_date = new JTextField();
		conf_date.setColumns(10);
		conf_date.setBounds(490, 109, 284, 20);
		contentPane.add(conf_date);
		
		conf_address = new JTextField();
		conf_address.setColumns(10);
		conf_address.setBounds(556, 147, 218, 20);
		contentPane.add(conf_address);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(490, 146, 58, 20);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCity.setBounds(490, 177, 37, 20);
		contentPane.add(lblCity);
		
		conf_city = new JTextField();
		conf_city.setColumns(10);
		conf_city.setBounds(529, 178, 85, 20);
		contentPane.add(conf_city);
		
		JLabel lblState = new JLabel("State:");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblState.setBounds(624, 178, 47, 20);
		contentPane.add(lblState);
		
		conf_state = new JTextField();
		conf_state.setColumns(10);
		conf_state.setBounds(681, 178, 93, 20);
		contentPane.add(conf_state);
		
		JLabel lblZip = new JLabel("Zip:");
		lblZip.setHorizontalAlignment(SwingConstants.LEFT);
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblZip.setBounds(490, 205, 31, 20);
		contentPane.add(lblZip);
		
		con_zip = new JTextField();
		con_zip.setColumns(10);
		con_zip.setBounds(529, 206, 85, 20);
		contentPane.add(con_zip);
		
		submission_deadline = new JTextField();
		submission_deadline.setColumns(10);
		submission_deadline.setBounds(490, 293, 284, 20);
		contentPane.add(submission_deadline);
		
		review_deadline = new JTextField();
		review_deadline.setColumns(10);
		review_deadline.setBounds(490, 324, 284, 20);
		contentPane.add(review_deadline);
		
		sub_pc_recommend_deadline = new JTextField();
		sub_pc_recommend_deadline.setColumns(10);
		sub_pc_recommend_deadline.setBounds(490, 355, 284, 20);
		contentPane.add(sub_pc_recommend_deadline);
		
		author_notification_deadline = new JTextField();
		author_notification_deadline.setColumns(10);
		author_notification_deadline.setBounds(490, 386, 284, 20);
		contentPane.add(author_notification_deadline);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(177, 414, 597, 18);
		contentPane.add(separator_3);
		
		JLabel lblConferenceSummary = new JLabel("Conference Summary:");
		lblConferenceSummary.setHorizontalAlignment(SwingConstants.CENTER);
		lblConferenceSummary.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConferenceSummary.setBounds(153, 425, 621, 20);
		contentPane.add(lblConferenceSummary);
		
		textField = new JTextField();
		textField.setBounds(177, 453, 597, 70);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAssignedPC = new JLabel("<dynamic>");
		lblAssignedPC.setHorizontalAlignment(SwingConstants.LEFT);
		lblAssignedPC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAssignedPC.setBounds(490, 236, 157, 20);
		contentPane.add(lblAssignedPC);
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
		 * The action associated with clicking the Create New Conference button.
		 */
		my_new_conf_action = new AbstractAction(NEW_CONF_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				System.out.println("Creating new conference");
//				controller.setStateOfGUI(StateOfGUI.LOGIN);
			}
		};
		my_new_conf_action.putValue(Action.SHORT_DESCRIPTION, NEW_CONF_STRING);
		my_new_conf_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
	}
}

