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
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import view.GUIEnum.StateOfGUI;
import controller.Conference;
import controller.Controller;

/**
* The Manage Paper User Interface JPanel
* 
* Allows the user to create access all the information relevant to a conference
* and a paper in relation to their permissions associated with that paper.  If 
* the user doens't have a relationship with a paper in that conference, only
* the relevant conference data will be populated.
* @author Jacob Hall
* @version 11/15/13
*/

@SuppressWarnings("serial")
public class ManagePaperGUI extends JPanel{

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
	 * The text to display on the submit paper button.
	 */
	private static final String SUBMIT_PAPER_TITLE_STRING = "Submit Paper";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String SUBMIT_PAPER_STRING = "Submit a new paper to the conference (ALT+S)";
	
	/*
	 * The text to display on the edit submission button.
	 */
	private static final String EDIT_SUBMISSION_TITLE_STRING = "Edit Submission";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String EDIT_SUBMISSION_STRING = "Edit a submitted paper (ALT+E)";
	
	/*
	 * The text to display on the submit review button.
	 */
	private static final String SUBMIT_REVIEW_TITLE_STRING = "Submit Review";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String SUBMIT_REVIEW_STRING = "Submit a new review (ALT+R)";
	
	/*
	 * The text to display on the make recommendation button.
	 */
	private static final String MAKE_RECOMMENDATION_TITLE_STRING = "Make Recommendation";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String MAKE_RECOMMENDATION_STRING = "Make a recommendation on this paper (ALT+K)";
	
	/*
	 * The text to display on the assign reviewer button.
	 */
	private static final String ASSIGN_REVIEWER_TITLE_STRING = "Assign Reviewer";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String ASSIGN_REVIEWER_STRING = "Assign a reviewer to this paper (ALT+A)";
	
	/*
	 * The text to display on the assign sub-pc button.
	 */
	private static final String ASSIGN_SUBPC_TITLE_STRING = "Assign Sub-PC";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String ASSIGN_SUBPC_STRING = "Assign a reviewer to this paper (ALT+P)";
	
	/*
	 * the JPanel containing the entire ManagePaperGUI
	 */
	private JPanel contentPane;
	
	/*
	 * The CMS controller
	 */
	private Controller controller;
	
	/*
	 * The CMS paper in focus (if there is one)
	 */
	private String current_paper;
	
	/*
	 * the current conference in focus.
	 */
	private Conference current_conf;
	
	/*
	 * the Action associated with the logout button
	 */
	private Action my_logout_action;
	
	/*
	 * the Action associated with the Main button
	 */
	private Action my_main_action;

	/*
	 * the Action associated with the back button
	 */
	private Action my_back_action;
	
	/*
	 * the Action associated with the submit paper button
	 */
	private Action my_submit_paper_action;
	
	/*
	 * the Action associated with the edit submission button
	 */
	private Action my_edit_submission_action;
	
	/*
	 * the Action associated with the submit review button
	 */
	private Action my_submit_review_action;
	
	/*
	 * the Action associated with the make recommendation button
	 */
	private Action my_make_recommendation_action;
	
	/*
	 * the Action associated with the assign reviewer button
	 */
	private Action my_assign_reviewer_action;
	
	
	/*
	 * the Action associated with the assign sub-pc button
	 */
	private Action my_assign_subpc_action;
	
	/**
	 * Create the JPanel.
	 */
	public ManagePaperGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		current_conf = controller.getCurrentConference();
		setupActions();
		setBounds(100, 100, 722, 520);
		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setPreferredSize(WIN_DIMENSION);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		//creates the title icon at the top of the panel
		JButton btnIcon = new JButton("");
		btnIcon.setForeground(BACKGROUND_COLOR);
		btnIcon.setBorder(null);
		btnIcon.setIcon(ICON);
		btnIcon.setBounds(430, 11, 404, 116);
		contentPane.add(btnIcon);		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 127, 1250, 12);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(INNER_BACKGROUND_COLOR);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(232, 161, 831, 519);
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
		
		JButton btnLogout = new JButton(my_logout_action);
		btnLogout.setBounds(20, 100, 176, 22);
		panel.add(btnLogout);
		
		JButton btnBack = new JButton(my_back_action);
		btnBack.setBounds(20, 170, 176, 22);
		panel.add(btnBack);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(206, 69, 2, 424);
		panel.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 50, 789, 13);
		panel.add(separator_2);
		
		
		//If the paper hasn't been created yet, the Jlabel should be blank.  Otherwise, retrieve the 
		//		paper title and add it to the label.
		JLabel lblPaperTitle;
		if (controller.getCurrentPaper() == ""){
			lblPaperTitle = new JLabel("No Paper Selected");
		}
		else {
			lblPaperTitle = new JLabel("Manage Paper: " + controller.getCurrentPaper());
		}

		lblPaperTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaperTitle.setBounds(20, 11, 789, 29);
		panel.add(lblPaperTitle);
		lblPaperTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnMain = new JButton(my_main_action);
		btnMain.setBounds(20, 135, 176, 22);
		panel.add(btnMain);
		
		JButton btnSubmitPaper = new JButton(my_submit_paper_action);
		btnSubmitPaper.setBounds(20, 235, 176, 22);
		panel.add(btnSubmitPaper);
		
		JButton btnEditSubmission = new JButton(my_edit_submission_action);
		btnEditSubmission.setBounds(20, 270, 176, 22);
		panel.add(btnEditSubmission);
		
		JButton btnReview = new JButton(my_submit_review_action);
		btnReview.setBounds(20, 305, 176, 22);
		panel.add(btnReview);
		
		JButton Recommendation = new JButton(my_make_recommendation_action);
		Recommendation.setBounds(20, 340, 176, 22);
		panel.add(Recommendation);
		
		JLabel lblUserTools = new JLabel("User Tools");
		lblUserTools.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserTools.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserTools.setBounds(20, 204, 176, 20);
		panel.add(lblUserTools);
		
		JButton btnAssignReviewers = new JButton(my_assign_reviewer_action);
		btnAssignReviewers.setText("Assign Reviewers");
		btnAssignReviewers.setBounds(20, 375, 176, 22);
		panel.add(btnAssignReviewers);
		
		JButton btnAssignSubpc = new JButton(my_assign_subpc_action);
		btnAssignSubpc.setBounds(20, 410, 176, 22);
		panel.add(btnAssignSubpc);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(228, 66, 581, 424);
		panel.add(tabbedPane);
		
		JPanel tabConferenceInfo = new JPanel();
		tabbedPane.addTab("Conference Info", null, tabConferenceInfo, null);
		tabConferenceInfo.setLayout(null);
		
		JPanel tabReviews = new JPanel();
		tabbedPane.addTab("Reviews", null, tabReviews, null);
		tabbedPane.setEnabledAt(1, true);
		tabReviews.setLayout(null);
		
		JLabel lblConferenceTitle = new JLabel("Conference Title:");
		lblConferenceTitle.setBounds(10, 20, 166, 20);
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
		lblPaperStatus.setBounds(10, 90, 166, 20);
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
		
		JLabel fieldConfTitle = new JLabel(current_conf.getConfTitle());
		fieldConfTitle.setHorizontalAlignment(SwingConstants.LEFT);
		fieldConfTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldConfTitle.setBounds(186, 20, 379, 20);
		tabConferenceInfo.add(fieldConfTitle);
		
		JLabel fieldConfDate = new JLabel(current_conf.getConfDate());
		fieldConfDate.setHorizontalAlignment(SwingConstants.LEFT);
		fieldConfDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldConfDate.setBounds(186, 55, 379, 20);
		tabConferenceInfo.add(fieldConfDate);
		
		JLabel fieldPaperStatus = new JLabel("<dynamic: status>");
		fieldPaperStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperStatus.setBounds(186, 90, 379, 20);
		tabConferenceInfo.add(fieldPaperStatus);
		
		JLabel fieldSubmissionDead = new JLabel(current_conf.getSubmissionDead());
		fieldSubmissionDead.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSubmissionDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldSubmissionDead.setBounds(335, 160, 231, 20);
		tabConferenceInfo.add(fieldSubmissionDead);
		
		JLabel fieldReviewDead = new JLabel(current_conf.getReviewDead());
		fieldReviewDead.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReviewDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReviewDead.setBounds(335, 190, 231, 20);
		tabConferenceInfo.add(fieldReviewDead);
		
		JLabel fieldSubPCDead = new JLabel(current_conf.getSubPCReccomendDead());
		fieldSubPCDead.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSubPCDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldSubPCDead.setBounds(335, 220, 231, 20);
		tabConferenceInfo.add(fieldSubPCDead);
		
		JLabel fieldAuthorNotificationDead = new JLabel(current_conf.getAuthorNotificationDead());
		fieldAuthorNotificationDead.setHorizontalAlignment(SwingConstants.LEFT);
		fieldAuthorNotificationDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldAuthorNotificationDead.setBounds(335, 250, 231, 20);
		tabConferenceInfo.add(fieldAuthorNotificationDead);
		
		JPanel tabRecommendation = new JPanel();
		tabbedPane.addTab("Recommendation", null, tabRecommendation, null);
		
		JPanel tabManagement = new JPanel();
		tabbedPane.addTab("Management", null, tabManagement, null);
		
		//TODO: The following logic can remove tabs from the tabbedPane if they aren't supposed to be visible
		
		//tabbedPane.remove(tabConferenceInfo);
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
				controller.setCurrentPaper("");
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
		 * The action associated with clicking the submit paper button.
		 */
		my_submit_paper_action = new AbstractAction(SUBMIT_PAPER_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: add the switching state reference.
				controller.setStateOfGUI(StateOfGUI.SUBMIT_PAPER);
				System.out.println("submit paper....");
			}
		};
		my_submit_paper_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_PAPER_STRING);
		my_submit_paper_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		
		/*
		 * The action associated with clicking the edit submission button.
		 */
		
		//TODO: this button should not be populated if there is no paper in focus.  Eventually,
		//		the JOptionPane should be removed, because that condition should never be met
		//		where the user is trying to edit a paper that hasn't been submitted.
		
		//TODO: furthermore, if the user isn't the author of the paper in focus, the button 
		//		should not be populated.  They should be able to see the relevant data according
		//		to their permissions, but they shouldn't be able to delete a paper.
		
		//TODO: The only other thing to consider is if the PC should have the ability to delete
		//		a paper because they have "ultimate" authority.  If someone submits an
		//		inflamatory or irrelevant paper, they should be able to delete it as trash.
		my_edit_submission_action = new AbstractAction(EDIT_SUBMISSION_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: add the switching state reference.
				if (controller.getCurrentPaper() == "")  //if there is no paper in focus, don't try to edit it.
				{
					JOptionPane.showMessageDialog(contentPane, "There is no paper to edit.");
				}
				else {
					controller.setStateOfGUI(StateOfGUI.EDIT_SUBMISSION);
					System.out.println("edit submission....");
				}
			}
		};
		my_edit_submission_action.putValue(Action.SHORT_DESCRIPTION, EDIT_SUBMISSION_STRING);
		my_edit_submission_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		
		/*
		 * The action associated with clicking the submit review button.
		 */
		my_submit_review_action = new AbstractAction(SUBMIT_REVIEW_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: make sure that the user can't submit a second review, and that there aren't already 
				//		4 reviews in the system for a paper.
				if (controller.canAddReview(current_conf, current_paper, controller.getCurrentUsername())){
					controller.setStateOfGUI(StateOfGUI.SUBMIT_REVIEW);
					System.out.println("submit review....");
				}
			}
		};
		my_submit_review_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_REVIEW_STRING);
		my_submit_review_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
		
		/*
		 * The action associated with clicking the make recommendation button.
		 */
		my_make_recommendation_action = new AbstractAction(MAKE_RECOMMENDATION_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: add the switching state reference
				controller.setStateOfGUI(StateOfGUI.SUBMIT_RECOMMENDATION);
				System.out.println("make a recommendation....");
			}
		};
		my_make_recommendation_action.putValue(Action.SHORT_DESCRIPTION, MAKE_RECOMMENDATION_STRING);
		my_make_recommendation_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_K);
		
		/*
		 * The action associated with clicking the assign reviewer button.
		 */
		my_assign_reviewer_action = new AbstractAction(ASSIGN_REVIEWER_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: add the switching state reference.
				controller.setStateOfGUI(StateOfGUI.ASSIGN_REVIEWER);
				System.out.println("assign a reviewer....");
			}
		};
		my_assign_reviewer_action.putValue(Action.SHORT_DESCRIPTION, ASSIGN_REVIEWER_STRING);
		my_assign_reviewer_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
		
		/*
		 * The action associated with clicking the assign sub-pc button.
		 */
		my_assign_subpc_action = new AbstractAction(ASSIGN_SUBPC_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: add the switching state reference.
//				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
				System.out.println("assign a subprogram chair....");
			}
		};
		my_assign_subpc_action.putValue(Action.SHORT_DESCRIPTION, ASSIGN_SUBPC_STRING);
		my_assign_subpc_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
	}
}

