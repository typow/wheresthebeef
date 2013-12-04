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
import java.util.HashMap;

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
import view.GUIEnum.paperRelation;
import view.GUIEnum.paperStatusAdminViewable;
import controller.Conference;
import controller.Controller;
import controller.Review;

/**
* The Manage Paper User Interface JPanel
* 
* Allows the user to create access all the information relevant to a conference
* and a paper in relation to their permissions associated with that paper.  If 
* the user doens't have a relationship with a paper in that conference, only
* the relevant conference data will be populated.
* 
* @author Jacob Hall
* @version 98 Date: 11/27/13
*/

@SuppressWarnings("serial")
public class ManagePaperGUI extends JPanel{

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
	private static final String ASSIGN_REVIEWER_TITLE_STRING = "Assign Reviewers";

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
	 * The text to display on the assign sub-pc button.
	 */
	private static final String ACCEPT_REJECT_TITLE_STRING = "Accept/Reject Paper";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String ACCEPT_REJECT_STRING = "Accept or reject this paper (ALT+C)";
	
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
	 * the current relationship the user has with the paper
	 */
	private paperRelation current_paper_relation;
	
	/*
	 * the current user
	 */
	private String current_user;
	
	private HashMap<String, Boolean> reviews_complete_map = new HashMap<String, Boolean>();
	
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
	
	/*
	 * the Action associated with the accept/reject paper button
	 */
	private Action my_accept_reject_action;
	
	/**
	 * The constructor for the ManagePaperGUI.  Creates the JPanel that holds the
	 * GUI itself.  The controller is passed in so the current context can be 
	 * retrieved according to who is viewing the GUI and what state the GUI
	 * is in relative to their association with the given paper.
	 * 
	 * <dt><b>Preconditions: The controller object has been instantiated.</b><dd>
	 * <dt><b>Postconditions: A JPanel is created to represent the most current data and status 
	 * 						 concerning a paper</b><dd>
	 * @param the_controller
	 */
	public ManagePaperGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		current_conf = controller.getCurrentConference();
		current_user = controller.getCurrentUsername();
		if (controller.getCurrentPaper() == ""){
			current_paper = "No Paper Submitted";
		}
		else {
			current_paper = controller.getCurrentPaper();
		}
		current_paper_relation = controller.getRelationToPaper(current_conf, current_paper, current_user);	
		JPanel mainPanel = setUpMainPanel();
		mainPanel.add(setupTabbedPane());
	}
	
	
	/**
	 * Method sets up the initial parameters and general look and feel for the Main JPanel.
	 * 
	 * <dt><b>Preconditions: The controller object has been instantiated.</b><dd>
	 * <dt><b>Postconditions: The main panel is created.</b><dd>
	 * 
	 * @return mainPanel JPanel containing the MainPanel
	 */
	private JPanel setUpMainPanel(){
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
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(INNER_BACKGROUND_COLOR);
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainPanel.setBounds(232, 161, 831, 519);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel label = new JLabel(controller.getCurrentUsername().toString());
		label.setBounds(20, 69, 176, 20);
		mainPanel.add(label);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnLogout = new JButton(my_logout_action);
		btnLogout.setBounds(20, 100, 176, 22);
		mainPanel.add(btnLogout);
		
		JButton btnBack = new JButton(my_back_action);
		btnBack.setBounds(20, 170, 176, 22);
		mainPanel.add(btnBack);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(206, 69, 2, 424);
		mainPanel.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 50, 789, 13);
		mainPanel.add(separator_2);
		
		//If the paper hasn't been created yet, the JLabel should be blank.  Otherwise, retrieve the 
		//paper title and add it to the label.
		JLabel lblPaperTitle;
		if (controller.getCurrentPaper() == ""){
			lblPaperTitle = new JLabel("No Paper Submitted");
		}
		else {
			lblPaperTitle = new JLabel("Manage Paper: " + controller.getCurrentPaper());
		}

		lblPaperTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaperTitle.setBounds(20, 11, 789, 29);
		mainPanel.add(lblPaperTitle);
		lblPaperTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnMain = new JButton(my_main_action);
		btnMain.setBounds(20, 135, 176, 22);
		mainPanel.add(btnMain);
		
		JLabel lblUserTools = new JLabel("User Tools");
		lblUserTools.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserTools.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserTools.setBounds(20, 204, 176, 20);
		mainPanel.add(lblUserTools);
				
		//Constants for buttons listed in this order for the absolute layout button.setbounds:
		//(20, 235, 176, 22),(20, 270, 176, 22),(20, 305, 176, 22),(20, 340, 176, 22),(20, 375, 176, 22),(20, 410, 176, 22),(20, 445, 176, 22)
		if (current_paper_relation == paperRelation.AUTHOR){
			JButton btnSubmitPaper = new JButton(my_submit_paper_action);
			btnSubmitPaper.setBounds(20, 235, 176, 22);
			mainPanel.add(btnSubmitPaper);
			
			JButton btnEditSubmission = new JButton(my_edit_submission_action);
			btnEditSubmission.setBounds(20, 270, 176, 22);
			mainPanel.add(btnEditSubmission);
		} else if (current_paper_relation == paperRelation.REVIEWER){			
			JButton btnReview = new JButton(my_submit_review_action);
			btnReview.setBounds(20, 235, 176, 22);
			mainPanel.add(btnReview);
		} else if (current_paper_relation == paperRelation.SUBPC){
			JButton btnAssignReviewers = new JButton(my_assign_reviewer_action);
			btnAssignReviewers.setBounds(20, 235, 176, 22);
			mainPanel.add(btnAssignReviewers);
			
			JButton Recommendation = new JButton(my_make_recommendation_action);
			Recommendation.setBounds(20, 270, 176, 22);
			mainPanel.add(Recommendation);
		} else if (current_paper_relation == paperRelation.PC){
			JButton btnEditSubmission = new JButton(my_edit_submission_action);
			btnEditSubmission.setBounds(20, 235, 176, 22);
			mainPanel.add(btnEditSubmission);
			
			JButton btnAssignSubpc = new JButton(my_assign_subpc_action);
			btnAssignSubpc.setBounds(20, 270, 176, 22);
			mainPanel.add(btnAssignSubpc);
			
			JButton btnAcceptReject = new JButton(my_accept_reject_action);
			btnAcceptReject.setBounds(20, 305, 176, 22);
			mainPanel.add(btnAcceptReject);
		}
		return mainPanel;
	}
	
	/**
	 * Sets up the main JTabbedPane that will display the appropriate sub-tabs according to the permissions
	 * of the user logged in.
	 * 
	 * <dt><b>Preconditions: The main panel exists to attach this tabbed pane to.</b><dd>
	 * <dt><b>Postconditions: A JPanel is created to represent the tabbed panes
	 * 						that will be viewable to various users based on their 
	 * 						relation to the paper in focus.</b><dd>
	 * 
	 * @return JTabbedPane  The main JTabbedPane containing all sub-tabs.
	 */
	private JTabbedPane setupTabbedPane(){
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(228, 66, 581, 424);
		
		JPanel tabConferenceInfo = setupConferenceTab();
		tabbedPane.addTab("Conference Info", null, tabConferenceInfo, null);
		
		JPanel tabReviews = setupReviewTab();
		tabbedPane.addTab("Reviews", null, tabReviews, null);

		//if the user is the Author or a Reviewer, they shouldn't see the recommendation made by the SubPC
		//else, the SubPC and the PC both should be able to view it.
		Boolean is_recommendation_complete = false;
		if ((current_paper_relation == paperRelation.PC)||(current_paper_relation == paperRelation.SUBPC)){
			if (controller.getPaperRecommendationNumericalValuefinal(current_conf, current_paper) != 0){
				is_recommendation_complete = true;
				System.out.println("there's a recommendation submitted!");
				RecommendPanel recommendationPanel = new RecommendPanel(controller.getPaperRecommendationSubPCName(current_conf, current_paper), 
						current_conf.getConfTitle(), current_paper, controller.getPaperAuthor(current_conf, current_paper), 
						controller.getPaperRecommendationNumericalValuefinal(current_conf, current_paper),
						controller.getPaperRecommendationRationale(current_conf, current_paper));
				JPanel tabRecommendation = (JPanel) recommendationPanel.getGUI();
				tabbedPane.addTab("Recommendation", null, tabRecommendation, null);
			}
//			RecommendPanel recommendationPanel = new RecommendPanel(controller.getPaperRecommendationSubPCName(current_conf, current_paper), 
//					current_conf.getConfTitle(), current_paper, controller.getPaperAuthor(current_conf, current_paper), 
//					controller.getPaperRecommendationNumericalValuefinal(current_conf, current_paper),
//					controller.getPaperRecommendationRationale(current_conf, current_paper));
//			JPanel tabRecommendation = (JPanel) recommendationPanel.getGUI();
//			tabbedPane.addTab("Recommendation", null, tabRecommendation, null);
		}
		int paperId = controller.getPaperID(current_paper);
		System.out.println("paper admin status" + controller.getAdminPaperStatus(current_conf, current_paper));
		if ((current_paper_relation == paperRelation.PC)||(current_paper_relation == paperRelation.SUBPC)){
			ManagementPanel managePanel = new ManagementPanel(current_paper, 
					controller.getPaperAuthor(current_conf, current_paper), controller.getAdminPaperStatus(current_conf, current_paper), 
					controller.getUserAssignedAsPC(current_conf),
					controller.getUserAssignedAsSubPC(current_conf, paperId),
					controller.getUsersAssignedAsReviewers(current_conf, current_paper),
					reviews_complete_map, is_recommendation_complete);
			JPanel tabManagement = (JPanel) managePanel.getGUI();
			tabbedPane.addTab("Management", null, tabManagement, null);
		}
		
		tabbedPane.setEnabledAt(1, true);
		return tabbedPane;
	}
	
	/**
	 * Method creates the tab to display the conference information
	 * 
	 * <dt><b>Preconditions: The tabbed pane exists to attach this particular pane to.</b><dd>
	 * <dt><b>Postconditions: A JPanel is created to represent the conference info.</b><dd>
	 * 
	 * @return JPanel containing the conference info
	 */
	private JPanel setupConferenceTab(){
		JPanel tabConferenceInfo = new JPanel();
		tabConferenceInfo.setBackground(Color.WHITE);
		tabConferenceInfo.setLayout(null);
		
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
		
		JLabel fieldConfDate = new JLabel(current_conf.getConfDate().toString());
		fieldConfDate.setHorizontalAlignment(SwingConstants.LEFT);
		fieldConfDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldConfDate.setBounds(186, 55, 379, 20);
		tabConferenceInfo.add(fieldConfDate);
		
		JLabel fieldPaperStatus;
		if (current_paper == "No Paper Submitted"){
			fieldPaperStatus = new JLabel(current_paper);
		}
		else {
			String status = controller.getAdminPaperStatus(current_conf, current_paper).toString();
			if (status == null){
				status = "fix me";
			}
			fieldPaperStatus = new JLabel(status);
		}
		fieldPaperStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperStatus.setFont(new Font("Tahoma", Font.BOLD, 13));
		fieldPaperStatus.setBounds(186, 90, 379, 20);
		tabConferenceInfo.add(fieldPaperStatus);
		
		JLabel fieldSubmissionDead = new JLabel(current_conf.getSubmissionDead().toString());
		fieldSubmissionDead.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSubmissionDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldSubmissionDead.setBounds(335, 160, 231, 20);
		tabConferenceInfo.add(fieldSubmissionDead);
		
		JLabel fieldReviewDead = new JLabel(current_conf.getReviewDead().toString());
		fieldReviewDead.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReviewDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReviewDead.setBounds(335, 190, 231, 20);
		tabConferenceInfo.add(fieldReviewDead);
		
		JLabel fieldSubPCDead = new JLabel(current_conf.getSubPCReccomendDead().toString());
		fieldSubPCDead.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSubPCDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldSubPCDead.setBounds(335, 220, 231, 20);
		tabConferenceInfo.add(fieldSubPCDead);
		
		JLabel fieldAuthorNotificationDead = new JLabel(current_conf.getAuthorNotificationDead().toString());
		fieldAuthorNotificationDead.setHorizontalAlignment(SwingConstants.LEFT);
		fieldAuthorNotificationDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldAuthorNotificationDead.setBounds(335, 250, 231, 20);
		tabConferenceInfo.add(fieldAuthorNotificationDead);
		
		return tabConferenceInfo;
	}
	
	
	/**
	 * Method creates and returns the review tab which might contain as many as four
	 * sub-tabs, each pertaining to a particular review.
	 * 
	 * <dt><b>Preconditions: The tabbed pane exists to attach this particular pane to.</b><dd>
	 * <dt><b>Postconditions: A JPanel is created to represent the review info.</b><dd>
	 * 
	 * @return tabReviews The JPanel containing the reviews. 
	 */
	private JPanel setupReviewTab(){
		JPanel tabReviews = new JPanel();
		tabReviews.setBackground(Color.WHITE);
		
		tabReviews.setLayout(null);
		
		JTabbedPane reviewPanel = new JTabbedPane(JTabbedPane.TOP);
		reviewPanel.setBounds(10, 11, 556, 374);
		tabReviews.add(reviewPanel);
		
		//following section ensures that only completed reviews are populated in the GUI
		Review[] initial_review_array = controller.getReviews(current_conf, current_paper);
		Review[] temp_review_array = new Review[4];
		int initial_num_reviews = initial_review_array.length;
		int temp_num_reviews = 0;
		int j = 0;
		for (int i = 0; i < initial_num_reviews; i++){
			if (initial_review_array[i].getAnswersToRadioBtns()[0] == 0){
				System.out.println("empty review at index: " + i);
			} else {
				temp_review_array[j] = initial_review_array[i];
				System.out.println("adding temporary review at index: " + j);
				j++;
				temp_num_reviews++;
			}
		}
		System.out.println("number of actually completed reviews: " + temp_num_reviews);
		Review[] the_reviews = new Review[temp_num_reviews];
		int num_reviews = the_reviews.length;
		for (int i = 0; i < num_reviews; i++){
			the_reviews[i] = temp_review_array[i];
			System.out.println("adding final review at index: " + i);
		}
		//end of check for empty reviews.
		
		//if the user logged in, the only review they should be able to see is their own.
		if (current_paper_relation == paperRelation.REVIEWER){
			System.out.println("i'm an author");
			Review usersOnlyViewableReview;
			for (int i = 0; i < num_reviews; i++){
				System.out.println("checking review #" + i);
				System.out.println(current_user);
				System.out.println(the_reviews[i].getReviewerName().toString());
				if (the_reviews[i].getReviewerName().equals(current_user)){
					System.out.println("the names matched");
					usersOnlyViewableReview = the_reviews[i];
					ReviewPanel tabReview = new ReviewPanel(usersOnlyViewableReview, 1, true, 
							true, false);
					JPanel review_panel = (JPanel) tabReview.getGUI();
					reviewPanel.addTab("Your Review", null, review_panel, null);
				}
			}
		}
		else if (current_paper_relation == paperRelation.AUTHOR){
			if (num_reviews > 0) {
				ReviewPanel tabReview1 = new ReviewPanel(the_reviews[0], 1, false, false, true);
				JPanel review_panel_1 = (JPanel) tabReview1.getGUI();
				reviewPanel.addTab("Review #1", null, review_panel_1, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				ReviewPanel tabReview2 = new ReviewPanel(the_reviews[1], 2, false, false, true);
				JPanel review_panel_2 = (JPanel) tabReview2.getGUI();
				reviewPanel.addTab("Review #2", null, review_panel_2, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				ReviewPanel tabReview3 = new ReviewPanel(the_reviews[2], 3, false, false, true);
				JPanel review_panel_3 = (JPanel) tabReview3.getGUI();
				reviewPanel.addTab("Review #3", null, review_panel_3, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				ReviewPanel tabReview4 = new ReviewPanel(the_reviews[3], 4, false, false, true);
				JPanel review_panel_4 = (JPanel) tabReview4.getGUI();
				reviewPanel.addTab("Review #4", null, review_panel_4, null);
				num_reviews--;
			}
		} else {
			if (num_reviews > 0) {
				reviews_complete_map.put(the_reviews[0].getReviewerName(), true);
				ReviewPanel tabReview1 = new ReviewPanel(the_reviews[0], 1, true, true, true);
				JPanel review_panel_1 = (JPanel) tabReview1.getGUI();
				reviewPanel.addTab("Review #1", null, review_panel_1, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				reviews_complete_map.put(the_reviews[1].getReviewerName(), true);
				ReviewPanel tabReview2 = new ReviewPanel(the_reviews[1], 2, true, true, true);
				JPanel review_panel_2 = (JPanel) tabReview2.getGUI();
				reviewPanel.addTab("Review #2", null, review_panel_2, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				reviews_complete_map.put(the_reviews[2].getReviewerName(), true);
				ReviewPanel tabReview3 = new ReviewPanel(the_reviews[2], 3, true, true, true);
				JPanel review_panel_3 = (JPanel) tabReview3.getGUI();
				reviewPanel.addTab("Review #3", null, review_panel_3, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				reviews_complete_map.put(the_reviews[3].getReviewerName(), true);
				ReviewPanel tabReview4 = new ReviewPanel(the_reviews[3], 4, true, true, true);
				JPanel review_panel_4 = (JPanel) tabReview4.getGUI();
				reviewPanel.addTab("Review #4", null, review_panel_4, null);
				num_reviews--;
			}
		}
		return tabReviews;
	}
	
	
	/**
	 * Getter for the ManagePaperGUI JPanel.
	 * 
	 * <dt><b>Preconditions: The ManagePaperGUI has already been instantiated.</b><dd>
	 * <dt><b>Postconditions: The ManagePaperGUI JPanel is returned.</b><dd>
	 * @return contentPane JPanel containing the ManagePaperGUI.
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
				controller.setStateOfGUI(StateOfGUI.CONFERENCE);
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
				controller.setCurrentConference(null);
				controller.setCurrentPaper("");
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
				controller.setStateOfGUI(StateOfGUI.SUBMIT_PAPER);
			}
		};
		my_submit_paper_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_PAPER_STRING);
		my_submit_paper_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		
		/*
		 * The action associated with clicking the edit submission button.
		 */
		my_edit_submission_action = new AbstractAction(EDIT_SUBMISSION_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				if (controller.getCurrentPaper() == "")  //if there is no paper in focus, don't try to edit it.
				{
					JOptionPane.showMessageDialog(contentPane, "There is no paper to edit.");
				}
				else {
					controller.setStateOfGUI(StateOfGUI.EDIT_SUBMISSION);
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
//				if (controller.canAddReview(current_conf, current_paper, controller.getCurrentUsername())){
//					System.out.println("I can add a review");
					controller.setStateOfGUI(StateOfGUI.SUBMIT_REVIEW);
//				}
//				System.out.println("I can't add a review");
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
				controller.setStateOfGUI(StateOfGUI.SUBMIT_RECOMMENDATION);
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
				controller.setStateOfGUI(StateOfGUI.ASSIGN_REVIEWER);
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
				controller.setStateOfGUI(StateOfGUI.ASSIGN_SUB_PC);
			}
		};
		my_assign_subpc_action.putValue(Action.SHORT_DESCRIPTION, ASSIGN_SUBPC_STRING);
		my_assign_subpc_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
		
		/*
		 * The action associated with clicking the accept/reject paper button.
		 */
		my_accept_reject_action = new AbstractAction(ACCEPT_REJECT_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setStateOfGUI(StateOfGUI.ACCEPT_REJECT);
			}
		};
		my_accept_reject_action.putValue(Action.SHORT_DESCRIPTION, ACCEPT_REJECT_STRING);
		my_accept_reject_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
	}
}