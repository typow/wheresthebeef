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
import view.GUIEnum.paperRelation;
import controller.Conference;
import controller.Controller;
import controller.Review;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;

/**
* The Manage Paper User Interface JPanel
* 
* Allows the user to create access all the information relevant to a conference
* and a paper in relation to their permissions associated with that paper.  If 
* the user doens't have a relationship with a paper in that conference, only
* the relevant conference data will be populated.
* 
* @author Jacob Hall
* @version SVN version 71.   11/25/13
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
	 * The constructor for the ManagePaperGUi.  Creates the JPanel that holds the
	 * GUI itself.  The controller is passed in so the current context can be 
	 * retrieved according to who is viewing the GUI and what state the GUI
	 * is in relative to their association with the given paper.
	 * 
	 * @param the_controller  The controller object that acts as a facilitator
	 * 							between GUI elements and the database.
	 */
	public ManagePaperGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		if (controller.getCurrentPaper() == ""){
			current_paper = "No Paper Submitted";
		}
		else {
			current_paper = controller.getCurrentPaper();
		}
		current_user = controller.getCurrentUsername();
		current_paper_relation = controller.getRelationToPaper(current_conf, current_paper, current_user);
		JPanel mainPanel = setUpMainPanel();
		mainPanel.add(setupTabbedPane());
		
		
		//TODO: set up logic to only display certain tabs
	}
	
	
	/**
	 * sets up the initial parameters and general look and feel for the Main JPanel.
	 * 
	 * @return JPanel containing the MainPanel
	 */
	private JPanel setUpMainPanel(){
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
		//		paper title and add it to the label.
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
		
		JButton btnSubmitPaper = new JButton(my_submit_paper_action);
		btnSubmitPaper.setBounds(20, 235, 176, 22);
		mainPanel.add(btnSubmitPaper);
		
		JButton btnEditSubmission = new JButton(my_edit_submission_action);
		btnEditSubmission.setBounds(20, 270, 176, 22);
		mainPanel.add(btnEditSubmission);
		
		JButton btnReview = new JButton(my_submit_review_action);
		btnReview.setBounds(20, 305, 176, 22);
		mainPanel.add(btnReview);
		
		JButton Recommendation = new JButton(my_make_recommendation_action);
		Recommendation.setBounds(20, 340, 176, 22);
		mainPanel.add(Recommendation);
		
		JLabel lblUserTools = new JLabel("User Tools");
		lblUserTools.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserTools.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserTools.setBounds(20, 204, 176, 20);
		mainPanel.add(lblUserTools);
		
		JButton btnAssignReviewers = new JButton(my_assign_reviewer_action);
		btnAssignReviewers.setText("Assign Reviewers");
		btnAssignReviewers.setBounds(20, 375, 176, 22);
		mainPanel.add(btnAssignReviewers);
		
		JButton btnAssignSubpc = new JButton(my_assign_subpc_action);
		btnAssignSubpc.setBounds(20, 410, 176, 22);
		mainPanel.add(btnAssignSubpc);
		
		JButton btnAcceptReject = new JButton(my_accept_reject_action);
		btnAcceptReject.setBounds(20, 445, 176, 22);
		mainPanel.add(btnAcceptReject);
		
		return mainPanel;
	}
	
	/**
	 * Sets up the main JTabbedPane that will display the appropriate sub-tabs according to the permissions
	 * of the user logged in.
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
		if ((current_paper_relation == paperRelation.PC)||(current_paper_relation == paperRelation.SUBPC)){
			RecommendPanel recommendationPanel = new RecommendPanel(controller.getPaperRecommendationSubPCName(current_conf, current_paper), 
					current_conf.getConfTitle(), current_paper, controller.getPaperAuthor(current_conf, current_paper), 
					controller.getPaperRecommendationNumericalValuefinal(current_conf, current_paper),
					controller.getPaperRecommendationRationale(current_conf, current_paper));
			JPanel tabRecommendation = (JPanel) recommendationPanel.getGUI();
			tabbedPane.addTab("Recommendation", null, tabRecommendation, null);
		}
		
		if ((current_paper_relation == paperRelation.PC)||(current_paper_relation == paperRelation.SUBPC)){
			ManagementPanel managePanel = new ManagementPanel(current_paper, 
					controller.getPaperAuthor(current_conf, current_paper), controller.getAdminPaperStatus(current_conf, current_paper), 
					controller.getUserAssignedAsPC(current_conf, current_paper),
					controller.getUserAssignedAsSubPC(current_conf, current_paper),
					controller.getUsersAssignedAsReviewers(current_conf, current_paper));
			JPanel tabManagement = (JPanel) managePanel.getGUI();
			tabbedPane.addTab("Management", null, tabManagement, null);
		}

		
		//**************************************************************************************************************
//		JPanel tabManagement = new JPanel();
//		tabManagement.setBackground(Color.WHITE);
//		tabbedPane.addTab("Management", null, tabManagement, null);
//		tabManagement.setLayout(null);
//		
//		JPanel managementPanel = new JPanel();
//		managementPanel.setBounds(0, 0, 576, 396);
//		tabManagement.add(managementPanel);
//		managementPanel.setLayout(null);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(0, 0, 576, 396);
//		managementPanel.add(scrollPane);
//		
//		JPanel panel = new JPanel();
//		scrollPane.setViewportView(panel);
//		panel.setBackground(Color.WHITE);
//		GridBagLayout gbl_panel = new GridBagLayout();
//		gbl_panel.columnWidths = new int[]{580, 0};
//		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
//		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
//		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0};
//		panel.setLayout(gbl_panel);
//		
//		JPanel panel_0 = new JPanel();
//		panel_0.setLayout(null);
//		panel_0.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_0 = new GridBagConstraints();
//		gbc_panel_0.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_0.fill = GridBagConstraints.BOTH;
//		gbc_panel_0.gridx = 0;
//		gbc_panel_0.gridy = 0;
//		panel.add(panel_0, gbc_panel_0);
//		
//		JLabel lblPaperDetails = new JLabel("Paper Details:");
//		lblPaperDetails.setHorizontalAlignment(SwingConstants.LEFT);
//		lblPaperDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
//		lblPaperDetails.setBounds(10, 0, 483, 20);
//		panel_0.add(lblPaperDetails);
//		
//		JPanel panel_1 = new JPanel();
//		panel_1.setLayout(null);
//		panel_1.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
//		gbc_panel_1.fill = GridBagConstraints.BOTH;
//		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_1.gridx = 0;
//		gbc_panel_1.gridy = 1;
//		panel.add(panel_1, gbc_panel_1);
//		
//		JLabel lblPaperName = new JLabel("Paper Name:");
//		lblPaperName.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblPaperName.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblPaperName.setBounds(28, 0, 141, 20);
//		panel_1.add(lblPaperName);
//		
//		JLabel fieldPaperName = new JLabel("<dynamic>");
//		fieldPaperName.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldPaperName.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldPaperName.setBounds(179, 0, 391, 20);
//		panel_1.add(fieldPaperName);
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setLayout(null);
//		panel_2.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
//		gbc_panel_2.fill = GridBagConstraints.BOTH;
//		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_2.gridx = 0;
//		gbc_panel_2.gridy = 2;
//		panel.add(panel_2, gbc_panel_2);
//		
//		JLabel lblPaperAuthor = new JLabel("Paper Author:");
//		lblPaperAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblPaperAuthor.setBounds(28, 0, 141, 20);
//		panel_2.add(lblPaperAuthor);
//		
//		JLabel fieldPaperAuthor = new JLabel("<dynamic>");
//		fieldPaperAuthor.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldPaperAuthor.setBounds(179, 0, 391, 20);
//		panel_2.add(fieldPaperAuthor);
//		
//		JPanel panel_3 = new JPanel();
//		panel_3.setLayout(null);
//		panel_3.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
//		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_3.fill = GridBagConstraints.BOTH;
//		gbc_panel_3.gridx = 0;
//		gbc_panel_3.gridy = 3;
//		panel.add(panel_3, gbc_panel_3);
//		
//		JLabel lblPaperAdminStatus = new JLabel("Paper Status:");
//		lblPaperAdminStatus.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblPaperAdminStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblPaperAdminStatus.setBounds(28, 0, 141, 20);
//		panel_3.add(lblPaperAdminStatus);
//		
//		JLabel fieldPaperAdminStatus = new JLabel("<dynamic>");
//		fieldPaperAdminStatus.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldPaperAdminStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldPaperAdminStatus.setBounds(179, 0, 391, 20);
//		panel_3.add(fieldPaperAdminStatus);
//		
//		JPanel panel_4 = new JPanel();
//		panel_4.setLayout(null);
//		panel_4.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
//		gbc_panel_4.fill = GridBagConstraints.BOTH;
//		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_4.gridx = 0;
//		gbc_panel_4.gridy = 4;
//		panel.add(panel_4, gbc_panel_4);
//		
//		JLabel lblAdministrativeTeam = new JLabel("Administrative Team:");
//		lblAdministrativeTeam.setHorizontalAlignment(SwingConstants.LEFT);
//		lblAdministrativeTeam.setFont(new Font("Tahoma", Font.BOLD, 15));
//		lblAdministrativeTeam.setBounds(10, 0, 483, 20);
//		panel_4.add(lblAdministrativeTeam);
//		
//		JPanel panel_5 = new JPanel();
//		panel_5.setLayout(null);
//		panel_5.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
//		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_5.fill = GridBagConstraints.BOTH;
//		gbc_panel_5.gridx = 0;
//		gbc_panel_5.gridy = 5;
//		panel.add(panel_5, gbc_panel_5);
//		
//		JLabel lblManageProgramChair = new JLabel("Program Chair:");
//		lblManageProgramChair.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblManageProgramChair.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblManageProgramChair.setBounds(28, 0, 141, 20);
//		panel_5.add(lblManageProgramChair);
//		
//		JLabel fieldPCName = new JLabel("<dynamic>");
//		fieldPCName.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldPCName.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldPCName.setBounds(179, 0, 391, 20);
//		panel_5.add(fieldPCName);
//		
//		JPanel panel_6 = new JPanel();
//		panel_6.setLayout(null);
//		panel_6.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
//		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_6.fill = GridBagConstraints.BOTH;
//		gbc_panel_6.gridx = 0;
//		gbc_panel_6.gridy = 6;
//		panel.add(panel_6, gbc_panel_6);
//		
//		JLabel lblFinalNoticeSubmitted = new JLabel("Final Notice Submitted?");
//		lblFinalNoticeSubmitted.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblFinalNoticeSubmitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblFinalNoticeSubmitted.setBounds(155, -1, 209, 20);
//		panel_6.add(lblFinalNoticeSubmitted);
//		
//		JLabel fieldFinalNoticeSubmittedStatus = new JLabel("<dynamic>");
//		fieldFinalNoticeSubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldFinalNoticeSubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldFinalNoticeSubmittedStatus.setBounds(389, 0, 181, 20);
//		panel_6.add(fieldFinalNoticeSubmittedStatus);
//		
//		JPanel panel_7 = new JPanel();
//		panel_7.setLayout(null);
//		panel_7.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
//		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_7.fill = GridBagConstraints.BOTH;
//		gbc_panel_7.gridx = 0;
//		gbc_panel_7.gridy = 7;
//		panel.add(panel_7, gbc_panel_7);
//		
//		JLabel lblSubPC = new JLabel("Sub-Program Chair:");
//		lblSubPC.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblSubPC.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblSubPC.setBounds(28, 0, 141, 20);
//		panel_7.add(lblSubPC);
//		
//		JLabel fieldSubPCName = new JLabel("<dynamic>");
//		fieldSubPCName.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldSubPCName.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldSubPCName.setBounds(179, 0, 391, 20);
//		panel_7.add(fieldSubPCName);
//		
//		JPanel panel_8 = new JPanel();
//		panel_8.setLayout(null);
//		panel_8.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
//		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_8.fill = GridBagConstraints.BOTH;
//		gbc_panel_8.gridx = 0;
//		gbc_panel_8.gridy = 8;
//		panel.add(panel_8, gbc_panel_8);
//		
//		JLabel lblRecommendationSubmitted = new JLabel("Recommendation Submitted?");
//		lblRecommendationSubmitted.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblRecommendationSubmitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblRecommendationSubmitted.setBounds(156, -1, 209, 20);
//		panel_8.add(lblRecommendationSubmitted);
//		
//		JLabel fieldRecommendationSubmittedStatus = new JLabel("<dynamic>");
//		fieldRecommendationSubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldRecommendationSubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldRecommendationSubmittedStatus.setBounds(389, 0, 180, 20);
//		panel_8.add(fieldRecommendationSubmittedStatus);
//		
//		JPanel panel_9 = new JPanel();
//		panel_9.setLayout(null);
//		panel_9.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
//		gbc_panel_9.fill = GridBagConstraints.BOTH;
//		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_9.gridx = 0;
//		gbc_panel_9.gridy = 9;
//		panel.add(panel_9, gbc_panel_9);
//		
//		JLabel lblProgramChair = new JLabel("Reviewers:");
//		lblProgramChair.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblProgramChair.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblProgramChair.setBounds(28, 0, 141, 20);
//		panel_9.add(lblProgramChair);
//		
//		JLabel fieldReviewer1 = new JLabel("<dynamic>");
//		fieldReviewer1.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReviewer1.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReviewer1.setBounds(179, 0, 391, 20);
//		panel_9.add(fieldReviewer1);
//		
//		JPanel panel_10 = new JPanel();
//		panel_10.setLayout(null);
//		panel_10.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
//		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_10.fill = GridBagConstraints.BOTH;
//		gbc_panel_10.gridx = 0;
//		gbc_panel_10.gridy = 10;
//		panel.add(panel_10, gbc_panel_10);
//		
//		JLabel lblReview1Submitted = new JLabel("Review Submitted?");
//		lblReview1Submitted.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblReview1Submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblReview1Submitted.setBounds(156, -1, 209, 20);
//		panel_10.add(lblReview1Submitted);
//		
//		JLabel fieldReview1SubmittedStatus = new JLabel("<dynamic>");
//		fieldReview1SubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReview1SubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReview1SubmittedStatus.setBounds(389, 0, 180, 20);
//		panel_10.add(fieldReview1SubmittedStatus);
//		
//		JPanel panel_11 = new JPanel();
//		panel_11.setLayout(null);
//		panel_11.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
//		gbc_panel_11.fill = GridBagConstraints.BOTH;
//		gbc_panel_11.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_11.gridx = 0;
//		gbc_panel_11.gridy = 11;
//		panel.add(panel_11, gbc_panel_11);
//		
//		JLabel fieldReviewer2 = new JLabel("<dynamic>");
//		fieldReviewer2.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReviewer2.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReviewer2.setBounds(179, 0, 391, 20);
//		panel_11.add(fieldReviewer2);
//		
//		JPanel panel_12 = new JPanel();
//		panel_12.setLayout(null);
//		panel_12.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
//		gbc_panel_12.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_12.fill = GridBagConstraints.BOTH;
//		gbc_panel_12.gridx = 0;
//		gbc_panel_12.gridy = 12;
//		panel.add(panel_12, gbc_panel_12);
//		
//		JLabel lblReview2Submitted = new JLabel("Review Submitted?");
//		lblReview2Submitted.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblReview2Submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblReview2Submitted.setBounds(156, -1, 209, 20);
//		panel_12.add(lblReview2Submitted);
//		
//		JLabel fieldReview2SubmittedStatus = new JLabel("<dynamic>");
//		fieldReview2SubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReview2SubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReview2SubmittedStatus.setBounds(389, 0, 180, 20);
//		panel_12.add(fieldReview2SubmittedStatus);
//		
//		JPanel panel_13 = new JPanel();
//		panel_13.setLayout(null);
//		panel_13.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
//		gbc_panel_13.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_13.fill = GridBagConstraints.BOTH;
//		gbc_panel_13.gridx = 0;
//		gbc_panel_13.gridy = 13;
//		panel.add(panel_13, gbc_panel_13);
//		
//		JLabel fieldReviewer3 = new JLabel("<dynamic>");
//		fieldReviewer3.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReviewer3.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReviewer3.setBounds(179, 0, 391, 20);
//		panel_13.add(fieldReviewer3);
//		
//		JPanel panel_14 = new JPanel();
//		panel_14.setLayout(null);
//		panel_14.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
//		gbc_panel_14.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_14.fill = GridBagConstraints.BOTH;
//		gbc_panel_14.gridx = 0;
//		gbc_panel_14.gridy = 14;
//		panel.add(panel_14, gbc_panel_14);
//		
//		JLabel lblReview3Submitted = new JLabel("Review Submitted?");
//		lblReview3Submitted.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblReview3Submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblReview3Submitted.setBounds(156, -1, 209, 20);
//		panel_14.add(lblReview3Submitted);
//		
//		JLabel fieldReview3SubmittedStatus = new JLabel("<dynamic>");
//		fieldReview3SubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReview3SubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReview3SubmittedStatus.setBounds(389, 0, 180, 20);
//		panel_14.add(fieldReview3SubmittedStatus);
//		
//		JPanel panel_15 = new JPanel();
//		panel_15.setLayout(null);
//		panel_15.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_15 = new GridBagConstraints();
//		gbc_panel_15.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_15.fill = GridBagConstraints.BOTH;
//		gbc_panel_15.gridx = 0;
//		gbc_panel_15.gridy = 15;
//		panel.add(panel_15, gbc_panel_15);
//		
//		JLabel fieldReviewer4 = new JLabel("<dynamic>");
//		fieldReviewer4.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReviewer4.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReviewer4.setBounds(179, 0, 391, 20);
//		panel_15.add(fieldReviewer4);
//		
//		JPanel panel_16 = new JPanel();
//		panel_16.setLayout(null);
//		panel_16.setBackground(Color.WHITE);
//		GridBagConstraints gbc_panel_16 = new GridBagConstraints();
//		gbc_panel_16.insets = new Insets(0, 0, 5, 0);
//		gbc_panel_16.fill = GridBagConstraints.BOTH;
//		gbc_panel_16.gridx = 0;
//		gbc_panel_16.gridy = 16;
//		panel.add(panel_16, gbc_panel_16);
//		
//		JLabel lblReview4Submitted = new JLabel("Review Submitted?");
//		lblReview4Submitted.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblReview4Submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblReview4Submitted.setBounds(156, -1, 209, 20);
//		panel_16.add(lblReview4Submitted);
//		
//		JLabel fieldReview4SubmittedStatus = new JLabel("<dynamic>");
//		fieldReview4SubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReview4SubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReview4SubmittedStatus.setBounds(389, 0, 180, 20);
//		panel_16.add(fieldReview4SubmittedStatus);
		
		//**************************************************************************************************************
		
		tabbedPane.setEnabledAt(1, true);
		return tabbedPane;
	}
	
	/**
	 * creates the tab to display the conference information
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
			fieldPaperStatus = new JLabel(controller.getAdminPaperStatus(current_conf, current_paper).toString());
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
	
	
	private JPanel setupReviewTab(){
		JPanel tabReviews = new JPanel();
		tabReviews.setBackground(Color.WHITE);
		
		tabReviews.setLayout(null);
		
		JTabbedPane reviewPanel = new JTabbedPane(JTabbedPane.TOP);
		reviewPanel.setBounds(10, 11, 556, 374);
		tabReviews.add(reviewPanel);
		
		Review[] the_reviews = controller.getReviews(current_conf, current_paper);
		int num_reviews = the_reviews.length;
		
		
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
				ReviewPanel tabReview1 = new ReviewPanel(the_reviews[0], 1, true, true, true);
				JPanel review_panel_1 = (JPanel) tabReview1.getGUI();
				reviewPanel.addTab("Review #1", null, review_panel_1, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				ReviewPanel tabReview2 = new ReviewPanel(the_reviews[1], 2, true, true, true);
				JPanel review_panel_2 = (JPanel) tabReview2.getGUI();
				reviewPanel.addTab("Review #2", null, review_panel_2, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				ReviewPanel tabReview3 = new ReviewPanel(the_reviews[2], 3, true, true, true);
				JPanel review_panel_3 = (JPanel) tabReview3.getGUI();
				reviewPanel.addTab("Review #3", null, review_panel_3, null);
				num_reviews--;
			}
			if (num_reviews > 0) {
				ReviewPanel tabReview4 = new ReviewPanel(the_reviews[3], 4, true, true, true);
				JPanel review_panel_4 = (JPanel) tabReview4.getGUI();
				reviewPanel.addTab("Review #4", null, review_panel_4, null);
				num_reviews--;
			}
		}
		return tabReviews;
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
				controller.setStateOfGUI(StateOfGUI.ASSIGN_SUB_PC);
				System.out.println("assign a subprogram chair....");
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
				System.out.println("accept/reject a paper...");
			}
		};
		my_accept_reject_action.putValue(Action.SHORT_DESCRIPTION, ACCEPT_REJECT_STRING);
		my_accept_reject_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
	}
}





























//
///*
// * TCES360 Conference Management System
// * Warrick Holfeld
// * Jacob Hall
// * Aarron Merril
// * Tyler Powers
// * Seth Kramer
// * David Swanson
// * 11/10/13
// */
//
//package view;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;
//import java.util.Date;
//
//import javax.swing.AbstractAction;
//import javax.swing.Action;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComponent;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JSeparator;
//import javax.swing.JTabbedPane;
//import javax.swing.SwingConstants;
//import javax.swing.border.BevelBorder;
//import javax.swing.border.EmptyBorder;
//
//import view.GUIEnum.StateOfGUI;
//import controller.Conference;
//import controller.Controller;
//
///**
//* The Manage Paper User Interface JPanel
//* 
//* Allows the user to create access all the information relevant to a conference
//* and a paper in relation to their permissions associated with that paper.  If 
//* the user doens't have a relationship with a paper in that conference, only
//* the relevant conference data will be populated.
//* @author Jacob Hall
//* @version 11/15/13
//*/
//
//@SuppressWarnings("serial")
//public class ManagePaperGUI extends JPanel{
//
//	/*
//	 * the icon to display the CMS logo
//	 */
//	private static final ImageIcon ICON = new ImageIcon("src/view/images2.jpg");
//	
//	/*
//	 * The background of the main JPanel
//	 */
//	private static final Color BACKGROUND_COLOR = new Color(153, 204, 204);
//	
//	/*
//	 * The background of the inner user JPanel.
//	 */
//	private static final Color INNER_BACKGROUND_COLOR = (new Color(204, 204, 153));
//	
//	/*
//	 * The size of the JPanel.
//	 */
//	private static final Dimension WIN_DIMENSION = new Dimension(1280, 720);
//
//	/*
//	 * The text to display on the logout button.
//	 */
//	private static final String LOGOUT_TITLE_STRING = "Logout";
//	
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String LOGOUT_STRING = "Logout of the system (ALT+L)";
//	
//	/*
//	 * The text to display on the logout button.
//	 */
//	private static final String BACK_TITLE_STRING = "<-- Back";
//	
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String BACK_STRING = "Navigate to the last screen (ALT+B)";
//	
//	/*
//	 * The text to display on the main button.
//	 */
//	private static final String MAIN_TITLE_STRING = "Main";
//
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String MAIN_STRING = "Return to Main user interface (ALT+M)";
//	
//	/*
//	 * The text to display on the submit paper button.
//	 */
//	private static final String SUBMIT_PAPER_TITLE_STRING = "Submit Paper";
//
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String SUBMIT_PAPER_STRING = "Submit a new paper to the conference (ALT+S)";
//	
//	/*
//	 * The text to display on the edit submission button.
//	 */
//	private static final String EDIT_SUBMISSION_TITLE_STRING = "Edit Submission";
//
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String EDIT_SUBMISSION_STRING = "Edit a submitted paper (ALT+E)";
//	
//	/*
//	 * The text to display on the submit review button.
//	 */
//	private static final String SUBMIT_REVIEW_TITLE_STRING = "Submit Review";
//
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String SUBMIT_REVIEW_STRING = "Submit a new review (ALT+R)";
//	
//	/*
//	 * The text to display on the make recommendation button.
//	 */
//	private static final String MAKE_RECOMMENDATION_TITLE_STRING = "Make Recommendation";
//
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String MAKE_RECOMMENDATION_STRING = "Make a recommendation on this paper (ALT+K)";
//	
//	/*
//	 * The text to display on the assign reviewer button.
//	 */
//	private static final String ASSIGN_REVIEWER_TITLE_STRING = "Assign Reviewer";
//
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String ASSIGN_REVIEWER_STRING = "Assign a reviewer to this paper (ALT+A)";
//	
//	/*
//	 * The text to display on the assign sub-pc button.
//	 */
//	private static final String ASSIGN_SUBPC_TITLE_STRING = "Assign Sub-PC";
//
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String ASSIGN_SUBPC_STRING = "Assign a reviewer to this paper (ALT+P)";
//	
//	/*
//	 * The text to display on the assign sub-pc button.
//	 */
//	private static final String ACCEPT_REJECT_TITLE_STRING = "Accept/Reject Paper";
//
//	/*
//	 * The message the will pop up when the user floats above the button.
//	 */
//	private static final String ACCEPT_REJECT_STRING = "Accept or reject this paper (ALT+C)";
//	
//	/*
//	 * the JPanel containing the entire ManagePaperGUI
//	 */
//	private JPanel contentPane;
//	
//	/*
//	 * The CMS controller
//	 */
//	private Controller controller;
//	
//	/*
//	 * The CMS paper in focus (if there is one)
//	 */
//	private String current_paper;
//	
//	/*
//	 * the current conference in focus.
//	 */
//	private Conference current_conf;
//	
//	/*
//	 * the Action associated with the logout button
//	 */
//	private Action my_logout_action;
//	
//	/*
//	 * the Action associated with the Main button
//	 */
//	private Action my_main_action;
//
//	/*
//	 * the Action associated with the back button
//	 */
//	private Action my_back_action;
//	
//	/*
//	 * the Action associated with the submit paper button
//	 */
//	private Action my_submit_paper_action;
//	
//	/*
//	 * the Action associated with the edit submission button
//	 */
//	private Action my_edit_submission_action;
//	
//	/*
//	 * the Action associated with the submit review button
//	 */
//	private Action my_submit_review_action;
//	
//	/*
//	 * the Action associated with the make recommendation button
//	 */
//	private Action my_make_recommendation_action;
//	
//	/*
//	 * the Action associated with the assign reviewer button
//	 */
//	private Action my_assign_reviewer_action;
//	
//	/*
//	 * the Action associated with the assign sub-pc button
//	 */
//	private Action my_assign_subpc_action;
//	
//	/*
//	 * the Action associated with the accept/reject paper button
//	 */
//	private Action my_accept_reject_action;
//	
//	/**
//	 * Create the JPanel.
//	 */
//	public ManagePaperGUI(final Controller the_controller) {
//		super();
//		controller = the_controller;
//		current_conf = controller.getCurrentConference();
//		setupActions();
//		setBounds(100, 100, 722, 520);
//		contentPane = new JPanel();
//		contentPane.setBackground(BACKGROUND_COLOR);
//		contentPane.setPreferredSize(WIN_DIMENSION);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(null);
//		
//		//creates the title icon at the top of the panel
//		JButton btnIcon = new JButton("");
//		btnIcon.setForeground(BACKGROUND_COLOR);
//		btnIcon.setBorder(null);
//		btnIcon.setIcon(ICON);
//		btnIcon.setBounds(430, 11, 404, 116);
//		contentPane.add(btnIcon);		
//		JSeparator separator = new JSeparator();
//		separator.setBounds(20, 127, 1250, 12);
//		contentPane.add(separator);
//		
//		JPanel mainPanel = new JPanel();
//		mainPanel.setBackground(INNER_BACKGROUND_COLOR);
//		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		mainPanel.setBounds(232, 161, 831, 519);
//		contentPane.add(mainPanel);
//		mainPanel.setLayout(null);
//		
//		//JLabel label = new JLabel(controller.getCurrentUsername().toString());
//		//Windows Builder Pro edits out the above line when you use it.  Replace the label
//		//declaration when needed because it inserts <dynamic> into the label text 
//		JLabel label = new JLabel(controller.getCurrentUsername().toString());
//		label.setBounds(20, 69, 176, 20);
//		mainPanel.add(label);
//		
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		label.setFont(new Font("Tahoma", Font.BOLD, 16));
//		
//		JButton btnLogout = new JButton(my_logout_action);
//		btnLogout.setBounds(20, 100, 176, 22);
//		mainPanel.add(btnLogout);
//		
//		JButton btnBack = new JButton(my_back_action);
//		btnBack.setBounds(20, 170, 176, 22);
//		mainPanel.add(btnBack);
//		
//		JSeparator separator_1 = new JSeparator();
//		separator_1.setBounds(206, 69, 2, 424);
//		mainPanel.add(separator_1);
//		separator_1.setOrientation(SwingConstants.VERTICAL);
//		
//		JSeparator separator_2 = new JSeparator();
//		separator_2.setBounds(20, 50, 789, 13);
//		mainPanel.add(separator_2);
//		
//		
//		//If the paper hasn't been created yet, the Jlabel should be blank.  Otherwise, retrieve the 
//		//		paper title and add it to the label.
//		JLabel lblPaperTitle;
//		if (controller.getCurrentPaper() == ""){
//			lblPaperTitle = new JLabel("No Paper Selected");
//		}
//		else {
//			lblPaperTitle = new JLabel("Manage Paper: " + controller.getCurrentPaper());
//		}
//
//		lblPaperTitle.setHorizontalAlignment(SwingConstants.CENTER);
//		lblPaperTitle.setBounds(20, 11, 789, 29);
//		mainPanel.add(lblPaperTitle);
//		lblPaperTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
//		
//		JButton btnMain = new JButton(my_main_action);
//		btnMain.setBounds(20, 135, 176, 22);
//		mainPanel.add(btnMain);
//		
//		JButton btnSubmitPaper = new JButton(my_submit_paper_action);
//		btnSubmitPaper.setBounds(20, 235, 176, 22);
//		mainPanel.add(btnSubmitPaper);
//		
//		JButton btnEditSubmission = new JButton(my_edit_submission_action);
//		btnEditSubmission.setBounds(20, 270, 176, 22);
//		mainPanel.add(btnEditSubmission);
//		
//		JButton btnReview = new JButton(my_submit_review_action);
//		btnReview.setBounds(20, 305, 176, 22);
//		mainPanel.add(btnReview);
//		
//		JButton Recommendation = new JButton(my_make_recommendation_action);
//		Recommendation.setBounds(20, 340, 176, 22);
//		mainPanel.add(Recommendation);
//		
//		JLabel lblUserTools = new JLabel("User Tools");
//		lblUserTools.setHorizontalAlignment(SwingConstants.CENTER);
//		lblUserTools.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblUserTools.setBounds(20, 204, 176, 20);
//		mainPanel.add(lblUserTools);
//		
//		JButton btnAssignReviewers = new JButton(my_assign_reviewer_action);
//		btnAssignReviewers.setText("Assign Reviewers");
//		btnAssignReviewers.setBounds(20, 375, 176, 22);
//		mainPanel.add(btnAssignReviewers);
//		
//		JButton btnAssignSubpc = new JButton(my_assign_subpc_action);
//		btnAssignSubpc.setBounds(20, 410, 176, 22);
//		mainPanel.add(btnAssignSubpc);
//		
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setBounds(228, 66, 581, 424);
//		mainPanel.add(tabbedPane);
//		
//		JPanel tabConferenceInfo = new JPanel();
//		tabbedPane.addTab("Conference Info", null, tabConferenceInfo, null);
//		tabConferenceInfo.setLayout(null);
//		
//		JPanel tabReviews = new JPanel();
//		tabbedPane.addTab("Reviews", null, tabReviews, null);
//		tabbedPane.setEnabledAt(1, true);
//		tabReviews.setLayout(null);
//		
//		JLabel lblConferenceTitle = new JLabel("Conference Title:");
//		lblConferenceTitle.setBounds(10, 20, 166, 20);
//		lblConferenceTitle.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblConferenceTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
//		tabConferenceInfo.add(lblConferenceTitle);
//		
//		JLabel lblConferenceDate = new JLabel("Conference Date:");
//		lblConferenceDate.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblConferenceDate.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblConferenceDate.setBounds(10, 55, 166, 20);
//		tabConferenceInfo.add(lblConferenceDate);
//		
//		JLabel lblPaperStatus = new JLabel("Paper Status:");
//		lblPaperStatus.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblPaperStatus.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblPaperStatus.setBounds(10, 90, 166, 20);
//		tabConferenceInfo.add(lblPaperStatus);
//		
//		JSeparator separator_3 = new JSeparator();
//		separator_3.setBounds(10, 119, 555, 13);
//		tabConferenceInfo.add(separator_3);
//		
//		JLabel lblImportantDates = new JLabel("Important Dates:");
//		lblImportantDates.setHorizontalAlignment(SwingConstants.CENTER);
//		lblImportantDates.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblImportantDates.setBounds(10, 128, 555, 20);
//		tabConferenceInfo.add(lblImportantDates);
//		
//		JLabel lblSubpcRecommendationDeadline = new JLabel("Sub-PC Recommendation Deadline:");
//		lblSubpcRecommendationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblSubpcRecommendationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblSubpcRecommendationDeadline.setBounds(23, 220, 300, 20);
//		tabConferenceInfo.add(lblSubpcRecommendationDeadline);
//		
//		JLabel lblSubmissionDeadline = new JLabel("Submission Deadline:");
//		lblSubmissionDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblSubmissionDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblSubmissionDeadline.setBounds(20, 160, 300, 20);
//		tabConferenceInfo.add(lblSubmissionDeadline);
//		
//		JLabel lblReviewDeadline = new JLabel("Review Deadline:");
//		lblReviewDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblReviewDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblReviewDeadline.setBounds(20, 190, 300, 20);
//		tabConferenceInfo.add(lblReviewDeadline);
//		
//		JLabel lblAuthorNotificationDeadline = new JLabel("Author Notification Deadline:");
//		lblAuthorNotificationDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblAuthorNotificationDeadline.setFont(new Font("Tahoma", Font.BOLD, 16));
//		lblAuthorNotificationDeadline.setBounds(23, 250, 300, 20);
//		tabConferenceInfo.add(lblAuthorNotificationDeadline);
//		
//		JLabel fieldConfTitle = new JLabel(current_conf.getConfTitle());
//		fieldConfTitle.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldConfTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldConfTitle.setBounds(186, 20, 379, 20);
//		tabConferenceInfo.add(fieldConfTitle);
//		
//		JLabel fieldConfDate = new JLabel(current_conf.getConfDate().toString());
//		fieldConfDate.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldConfDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldConfDate.setBounds(186, 55, 379, 20);
//		tabConferenceInfo.add(fieldConfDate);
//		
//		JLabel fieldPaperStatus = new JLabel(controller.getAdminPaperStatus(current_conf, current_paper).toString());
//		fieldPaperStatus.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldPaperStatus.setFont(new Font("Tahoma", Font.BOLD, 13));
//		fieldPaperStatus.setBounds(186, 90, 379, 20);
//		tabConferenceInfo.add(fieldPaperStatus);
//		
//		JLabel fieldSubmissionDead = new JLabel(current_conf.getSubmissionDead().toString());
//		fieldSubmissionDead.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldSubmissionDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldSubmissionDead.setBounds(335, 160, 231, 20);
//		tabConferenceInfo.add(fieldSubmissionDead);
//		
//		JLabel fieldReviewDead = new JLabel(current_conf.getReviewDead().toString());
//		fieldReviewDead.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldReviewDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldReviewDead.setBounds(335, 190, 231, 20);
//		tabConferenceInfo.add(fieldReviewDead);
//		
//		JLabel fieldSubPCDead = new JLabel(current_conf.getSubPCReccomendDead().toString());
//		fieldSubPCDead.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldSubPCDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldSubPCDead.setBounds(335, 220, 231, 20);
//		tabConferenceInfo.add(fieldSubPCDead);
//		
//		JLabel fieldAuthorNotificationDead = new JLabel(current_conf.getAuthorNotificationDead().toString());
//		fieldAuthorNotificationDead.setHorizontalAlignment(SwingConstants.LEFT);
//		fieldAuthorNotificationDead.setFont(new Font("Tahoma", Font.PLAIN, 13));
//		fieldAuthorNotificationDead.setBounds(335, 250, 231, 20);
//		tabConferenceInfo.add(fieldAuthorNotificationDead);
//		
//		JPanel tabRecommendation = new JPanel();
//		tabbedPane.addTab("Recommendation", null, tabRecommendation, null);
//		
//		JPanel tabManagement = new JPanel();
//		tabbedPane.addTab("Management", null, tabManagement, null);
//		
//		JButton btnAcceptReject = new JButton(my_accept_reject_action);
//		btnAcceptReject.setBounds(20, 445, 176, 22);
//		mainPanel.add(btnAcceptReject);
//		
//		//TODO: The following logic can remove tabs from the tabbedPane if they aren't supposed to be visible
//		
//		//tabbedPane.remove(tabConferenceInfo);
//	}
//	
//	/**
//	 * Getter for the HomeGUI JPanel.
//	 * 
//	 * @return contentPane JPanel containing the HomeGUI
//	 */
//	public JComponent getGUI() {
//		return contentPane;
//	}
//	
//	/**
//	 * Set up the actions to associate events with outside logic
//	 */
//	private void setupActions(){
//		/*
//		 * The action associated with clicking Logout
//		 */
//		my_logout_action = new AbstractAction(LOGOUT_TITLE_STRING, null)
//		{		
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				controller.setCurrentUsername(""); //blank because they're logging out
//					//we need to reset the current user to "null".
//				controller.setCurrentConference(null);
//				controller.setCurrentPaper("");
//				controller.setStateOfGUI(StateOfGUI.LOGIN);
//			}
//		};
//		my_logout_action.putValue(Action.SHORT_DESCRIPTION, LOGOUT_STRING);
//		my_logout_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
//		
//		/*
//		 * The action associated with clicking back.
//		 */
//		my_back_action = new AbstractAction(BACK_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				controller.setCurrentPaper("");
//				controller.setStateOfGUI(StateOfGUI.HOME);
//			}
//		};
//		my_back_action.putValue(Action.SHORT_DESCRIPTION, BACK_STRING);
//		my_back_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_B);
//		
//		/*
//		 * The action associated with clicking the Main button.
//		 */
//		my_main_action = new AbstractAction(MAIN_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				controller.setStateOfGUI(StateOfGUI.HOME);
//			}
//		};
//		my_main_action.putValue(Action.SHORT_DESCRIPTION, MAIN_STRING);
//		my_main_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
//			
//		/*
//		 * The action associated with clicking the submit paper button.
//		 */
//		my_submit_paper_action = new AbstractAction(SUBMIT_PAPER_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				//TODO: add the switching state reference.
//				controller.setStateOfGUI(StateOfGUI.SUBMIT_PAPER);
//				System.out.println("submit paper....");
//			}
//		};
//		my_submit_paper_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_PAPER_STRING);
//		my_submit_paper_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
//		
//		/*
//		 * The action associated with clicking the edit submission button.
//		 */
//		
//		//TODO: this button should not be populated if there is no paper in focus.  Eventually,
//		//		the JOptionPane should be removed, because that condition should never be met
//		//		where the user is trying to edit a paper that hasn't been submitted.
//		
//		//TODO: furthermore, if the user isn't the author of the paper in focus, the button 
//		//		should not be populated.  They should be able to see the relevant data according
//		//		to their permissions, but they shouldn't be able to delete a paper.
//		
//		//TODO: The only other thing to consider is if the PC should have the ability to delete
//		//		a paper because they have "ultimate" authority.  If someone submits an
//		//		inflamatory or irrelevant paper, they should be able to delete it as trash.
//		my_edit_submission_action = new AbstractAction(EDIT_SUBMISSION_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				//TODO: add the switching state reference.
//				if (controller.getCurrentPaper() == "")  //if there is no paper in focus, don't try to edit it.
//				{
//					JOptionPane.showMessageDialog(contentPane, "There is no paper to edit.");
//				}
//				else {
//					controller.setStateOfGUI(StateOfGUI.EDIT_SUBMISSION);
//					System.out.println("edit submission....");
//				}
//			}
//		};
//		my_edit_submission_action.putValue(Action.SHORT_DESCRIPTION, EDIT_SUBMISSION_STRING);
//		my_edit_submission_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
//		
//		/*
//		 * The action associated with clicking the submit review button.
//		 */
//		my_submit_review_action = new AbstractAction(SUBMIT_REVIEW_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				//TODO: make sure that the user can't submit a second review, and that there aren't already 
//				//		4 reviews in the system for a paper.
//				if (controller.canAddReview(current_conf, current_paper, controller.getCurrentUsername())){
//					controller.setStateOfGUI(StateOfGUI.SUBMIT_REVIEW);
//					System.out.println("submit review....");
//				}
//			}
//		};
//		my_submit_review_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_REVIEW_STRING);
//		my_submit_review_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
//		
//		/*
//		 * The action associated with clicking the make recommendation button.
//		 */
//		my_make_recommendation_action = new AbstractAction(MAKE_RECOMMENDATION_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				//TODO: add the switching state reference
//				controller.setStateOfGUI(StateOfGUI.SUBMIT_RECOMMENDATION);
//				System.out.println("make a recommendation....");
//			}
//		};
//		my_make_recommendation_action.putValue(Action.SHORT_DESCRIPTION, MAKE_RECOMMENDATION_STRING);
//		my_make_recommendation_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_K);
//		
//		/*
//		 * The action associated with clicking the assign reviewer button.
//		 */
//		my_assign_reviewer_action = new AbstractAction(ASSIGN_REVIEWER_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				//TODO: add the switching state reference.
//				controller.setStateOfGUI(StateOfGUI.ASSIGN_REVIEWER);
//				System.out.println("assign a reviewer....");
//			}
//		};
//		my_assign_reviewer_action.putValue(Action.SHORT_DESCRIPTION, ASSIGN_REVIEWER_STRING);
//		my_assign_reviewer_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
//		
//		/*
//		 * The action associated with clicking the assign sub-pc button.
//		 */
//		my_assign_subpc_action = new AbstractAction(ASSIGN_SUBPC_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				//TODO: add the switching state reference.
//				controller.setStateOfGUI(StateOfGUI.ASSIGN_SUB_PC);
//				System.out.println("assign a subprogram chair....");
//			}
//		};
//		my_assign_subpc_action.putValue(Action.SHORT_DESCRIPTION, ASSIGN_SUBPC_STRING);
//		my_assign_subpc_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
//		
//		/*
//		 * The action associated with clicking the accept/reject paper button.
//		 */
//		my_accept_reject_action = new AbstractAction(ACCEPT_REJECT_TITLE_STRING, null)
//		{
//			@Override
//			public void actionPerformed(ActionEvent the_event) {
//				//TODO: add the switching state reference.
//				controller.setStateOfGUI(StateOfGUI.ACCEPT_REJECT);
//				System.out.println("accept/reject a paper...");
//			}
//		};
//		my_accept_reject_action.putValue(Action.SHORT_DESCRIPTION, ACCEPT_REJECT_STRING);
//		my_accept_reject_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
//	}
//}
//
//
