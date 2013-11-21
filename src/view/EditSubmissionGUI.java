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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import view.GUIEnum.StateOfGUI;
import controller.Conference;
import controller.Controller;

/**
* The Edit Submission Interface JPanel
* 
* Allows the user to edit a submitted paper in the CMS.
* @author Jacob Hall
* @version 11/17/13
*/

@SuppressWarnings("serial")
public class EditSubmissionGUI extends JPanel {
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
	 * The text to display on the back button.
	 */
	private static final String BACK_TITLE_STRING = "<-- Back";
	
	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String BACK_STRING = "Navigate to the last screen (ALT+B)";

	/*
	 * The text to display on the main button.
	 */
	private static final String MAIN_TITLE_STRING = "Main";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String MAIN_STRING = "Return to Main user interface (ALT+M)";
	
	/*
	 * The text to display on the Edit Submission button.
	 */
	private static final String EDIT_SUBMISSION_TITLE_STRING = "Edit Submission";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String EDIT_SUBMISSION_STRING = "Edit the submitted paper (ALT+E)";
	
	/*
	 * The text to display on the Delete Submission button.
	 */
	private static final String DELETE_SUBMISSION_TITLE_STRING = "Delete Submission";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String DELETE_SUMISSION_STRING = "Delete the submitted paper (ALT+D)";
	
	/*
	 * the JPanel containing the entire SubmitPaperGUI
	 */
	private JPanel contentPane;
	
	/*
	 * The CMS controller
	 */
	private Controller controller;
	
	/*
	 * the current conference in focus.
	 */
	private Conference current_conf;
	
	/*
	 * the current user logged in using the CMS
	 */
	private String username;
	
	/*
	 * the Action associated with the Main button
	 */
	private Action my_main_action;

	/*
	 * the Action associated with the logout button
	 */
	private Action my_logout_action;
	
	/*
	 * the Action associated with the back button
	 */
	private Action my_back_action;
	
	/*
	 * the Action associated with the Edit Submission button
	 */
	private Action my_edit_submission_action;
	
	/*
	 * the Action associated with the Delete Submission button
	 */
	private Action my_delete_submission_action;

	/**
	 * Create the JPanel.
	 */
	public EditSubmissionGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		current_conf = controller.getCurrentConference();
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
		panel_1.setBounds(217, 181, 840, 325);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//JLabel label = new JLabel(controller.getCurrentUsername().toString());
		//Windows Builder Pro edits out the above line when you use it.  Replace the label
		//declaration when needed because it inserts <dynamic> into the label text 
		JLabel fieldUsername = new JLabel(username);
		fieldUsername.setBounds(10, 70, 157, 20);
		panel_1.add(fieldUsername);
		
		fieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		fieldUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLogout = new JButton(my_logout_action);
		btnLogout.setBounds(20, 100, 150, 22);
		panel_1.add(btnLogout);
		
		JButton btnMain = new JButton(my_main_action);
		btnMain.setBounds(20, 135, 150, 22);
		panel_1.add(btnMain);
		btnMain.setText("Main");
		
		JButton btnBack = new JButton(my_back_action);
		btnBack.setBounds(20, 170, 150, 22);
		panel_1.add(btnBack);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(182, 69, 14, 230);
		panel_1.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblConferenceTitle = new JLabel("Conference Title:");
		lblConferenceTitle.setBounds(205, 70, 166, 20);
		panel_1.add(lblConferenceTitle);
		lblConferenceTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblUserName = new JLabel("UserName:");
		lblUserName.setBounds(180, 105, 189, 20);
		panel_1.add(lblUserName);
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblUserFullName = new JLabel("User Full Name:");
		lblUserFullName.setBounds(74, 140, 297, 20);
		panel_1.add(lblUserFullName);
		lblUserFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserFullName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnEditSubmission = new JButton(my_edit_submission_action);
		btnEditSubmission.setText(EDIT_SUBMISSION_TITLE_STRING);
		btnEditSubmission.setBounds(244, 269, 159, 29);
		panel_1.add(btnEditSubmission);
		
		JLabel lblPaperTitle = new JLabel("Paper Title:");
		lblPaperTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPaperTitle.setBounds(74, 175, 297, 20);
		panel_1.add(lblPaperTitle);
		
		JLabel fieldConfTitle = new JLabel(current_conf.getConfTitle());
		fieldConfTitle.setHorizontalAlignment(SwingConstants.LEFT);
		fieldConfTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldConfTitle.setBounds(401, 70, 397, 20);
		panel_1.add(fieldConfTitle);
		
		JLabel fieldUserName = new JLabel(controller.getCurrentUsername());
		fieldUserName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldUserName.setBounds(401, 105, 397, 20);
		panel_1.add(fieldUserName);
		
		JLabel fieldUserFullName = new JLabel(controller.getFullName(controller.getCurrentUsername()));
		fieldUserFullName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldUserFullName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldUserFullName.setBounds(401, 140, 397, 20);
		panel_1.add(fieldUserFullName);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 46, 799, 20);
		panel_1.add(separator_2);
		
		JLabel lblSubmitANew = new JLabel("Edit a Paper Submission to the Conference");
		lblSubmitANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmitANew.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubmitANew.setBounds(20, 15, 791, 20);
		panel_1.add(lblSubmitANew);
		
		JLabel fieldPaperTitle = new JLabel("<dynamic>");
		fieldPaperTitle.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperTitle.setBounds(401, 175, 397, 20);
		panel_1.add(fieldPaperTitle);
		
		JButton btnDeleteSubmission = new JButton(my_delete_submission_action);
		btnDeleteSubmission.setText(DELETE_SUBMISSION_TITLE_STRING);
		btnDeleteSubmission.setBounds(621, 269, 159, 29);
		panel_1.add(btnDeleteSubmission);
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
		 * The action associated with clicking back.
		 */
		my_back_action = new AbstractAction(BACK_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
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
		 * The action associated with clicking the Edit Submission button.
		 */
		my_edit_submission_action = new AbstractAction(EDIT_SUBMISSION_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.deletePaper(current_conf, username, controller.getCurrentPaper());
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.SUBMIT_PAPER);
			}
		};
		my_edit_submission_action.putValue(Action.SHORT_DESCRIPTION, EDIT_SUBMISSION_STRING);
		my_edit_submission_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		
		/*
		 * The action associated with clicking the Submit button.
		 */
		my_delete_submission_action = new AbstractAction(DELETE_SUBMISSION_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.deletePaper(current_conf, username, controller.getCurrentPaper());
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
			}
		};
		my_delete_submission_action.putValue(Action.SHORT_DESCRIPTION, DELETE_SUMISSION_STRING);
		my_delete_submission_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_D);
	}
}

