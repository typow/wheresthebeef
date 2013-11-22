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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import view.GUIEnum.StateOfGUI;
import controller.Conference;
import controller.Controller;

/**
* The Assign Reviewer Interface JPanel
* 
* Allows the user to assign a reviewer(s) to a paper in the CMS.
* @author Jacob Hall
* @version 11/17/13
*/

@SuppressWarnings("serial")
public class AssignReviewerGUI extends JPanel {
	
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
	 * The text to display on the main button.
	 */
	private static final String SUBMIT_REVIEW_TITLE_STRING = "Submit";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String SUBMIT_REVIEW_STRING = "Submit this review form (ALT+S)";
	
	/*
	 * the maximum allowable number of reviewers for a single paper
	 */
	private static int MAX_NUM_REVIWERS = 4;
	
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
	 * the current paper being reviewed.
	 */
	private String current_paper;
	
	/*
	 * the name of the person who authored the paper being reviewed.
	 */
	private String paper_author = "";
	
	/*
	 * integer value used to represent the number of reviewers currently
	 * assigned to a paper.  This can't be more than 4.  It is used to 
	 * validate that business rule.
	 */
	private int num_of_reviewers = 0;
	
	/*
	 * The list of users that can be selected from to be considered
	 * for reviewers of the paper.
	 */
	private JList<String> list;
	
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
	private Action my_submit_action;

	/**
	 * Create the JPanel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AssignReviewerGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		current_conf = controller.getCurrentConference();
		username = controller.getCurrentUsername();
		paper_author = controller.getFullName(controller.getPaperAuthor(current_conf, current_paper));
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
		
		JLabel label_1 = new JLabel("Conference Management System (CMS)");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_1.setBounds(10, 11, 1260, 26);
		contentPane.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(INNER_BACKGROUND_COLOR);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(217, 160, 840, 518);
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
		separator_1.setBounds(182, 69, 14, 427);
		panel_1.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JButton btnEditSubmission = new JButton(my_submit_action);
		btnEditSubmission.setToolTipText(SUBMIT_REVIEW_STRING);
		btnEditSubmission.setText(SUBMIT_REVIEW_TITLE_STRING);
		btnEditSubmission.setBounds(417, 467, 159, 29);
		panel_1.add(btnEditSubmission);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 46, 799, 20);
		panel_1.add(separator_2);
		
		JLabel lblMakeRecommend = new JLabel("Assign A Reviewer to the Paper:");
		lblMakeRecommend.setHorizontalAlignment(SwingConstants.CENTER);
		lblMakeRecommend.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMakeRecommend.setBounds(20, 15, 791, 20);
		panel_1.add(lblMakeRecommend);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(217, 70, 582, 374);
		panel_1.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {580};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 30, 120, 10, 30, 0, 150};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		
		JLabel lblYourName = new JLabel("Your Name:");
		lblYourName.setBounds(28, 0, 141, 20);
		lblYourName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblYourName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblYourName);
		
		JLabel fieldReviewerName = new JLabel(controller.getFullName(username));
		fieldReviewerName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReviewerName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReviewerName.setBounds(179, 0, 391, 20);
		panel_2.add(fieldReviewerName);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		panel.add(panel_3, gbc_panel_3);
		
		JLabel lblConferenceName = new JLabel("Conference Name:");
		lblConferenceName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConferenceName.setBounds(28, 0, 141, 20);
		panel_3.add(lblConferenceName);
		
		JLabel fieldConfName = new JLabel(current_conf.getConfTitle());
		fieldConfName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldConfName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldConfName.setBounds(179, 0, 391, 20);
		panel_3.add(fieldConfName);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 3;
		panel.add(panel_4, gbc_panel_4);
		
		JLabel lblPaperName = new JLabel("Paper Name:");
		lblPaperName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperName.setBounds(28, 0, 141, 20);
		panel_4.add(lblPaperName);
		
		JLabel fieldPaperName = new JLabel(controller.getCurrentPaper());
		fieldPaperName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperName.setBounds(179, 0, 391, 20);
		panel_4.add(fieldPaperName);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 4;
		panel.add(panel_5, gbc_panel_5);
		
		JLabel lblPaperAuthor = new JLabel("Paper Author:");
		lblPaperAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperAuthor.setBounds(28, 0, 141, 20);
		panel_5.add(lblPaperAuthor);
		
		JLabel fieldPaperAuthor = new JLabel(paper_author);
		fieldPaperAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperAuthor.setBounds(179, 0, 391, 20);
		panel_5.add(fieldPaperAuthor);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 5;
		panel.add(panel_7, gbc_panel_7);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		panel_6.setBounds(0, 0, 580, 123);
		panel_7.add(panel_6);
		
		JLabel lblReviewersAlreadyAssigned = new JLabel("Reviewers Already Assigned:");
		lblReviewersAlreadyAssigned.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReviewersAlreadyAssigned.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReviewersAlreadyAssigned.setBounds(28, 0, 211, 20);
		panel_6.add(lblReviewersAlreadyAssigned);
		
		//TODO: get the array of users already assigned as reviewers to this paper (if any)
		String[] users_already_assigned = controller.getUsersAssignedAsReviewers(current_conf, current_paper);
		num_of_reviewers = users_already_assigned.length;
		
		int temp_num_reviewers = num_of_reviewers;
		if (temp_num_reviewers > 0){
			JLabel user_already_assigned_1 = new JLabel(controller.getFullName(users_already_assigned[0]));
			user_already_assigned_1.setHorizontalAlignment(SwingConstants.LEFT);
			user_already_assigned_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			user_already_assigned_1.setBounds(290, 1, 367, 20);
			panel_6.add(user_already_assigned_1);
			temp_num_reviewers--;
		} else {
			JLabel user_already_assigned_1 = new JLabel("UNASSIGNED");
			user_already_assigned_1.setHorizontalAlignment(SwingConstants.LEFT);
			user_already_assigned_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			user_already_assigned_1.setBounds(290, 1, 367, 20);
			panel_6.add(user_already_assigned_1);
			temp_num_reviewers--;
		}
		
		if (temp_num_reviewers > 0){
			JLabel user_already_assigned_2 = new JLabel(controller.getFullName(users_already_assigned[1]));
			user_already_assigned_2.setHorizontalAlignment(SwingConstants.LEFT);
			user_already_assigned_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			user_already_assigned_2.setBounds(290, 32, 367, 20);
			panel_6.add(user_already_assigned_2);
			temp_num_reviewers--;
		} else {
			JLabel user_already_assigned_2 = new JLabel("UNASSIGNED");
			user_already_assigned_2.setHorizontalAlignment(SwingConstants.LEFT);
			user_already_assigned_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
			user_already_assigned_2.setBounds(290, 32, 367, 20);
			panel_6.add(user_already_assigned_2);
			temp_num_reviewers--;
		}
		
		if (temp_num_reviewers > 0){
			JLabel user_already_assigned_3 = new JLabel(controller.getFullName(users_already_assigned[2]));
			user_already_assigned_3.setHorizontalAlignment(SwingConstants.LEFT);
			user_already_assigned_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
			user_already_assigned_3.setBounds(290, 63, 367, 20);
			panel_6.add(user_already_assigned_3);
			temp_num_reviewers--;
		} else {
			JLabel user_already_assigned_3 = new JLabel("UNASSIGNED");
			user_already_assigned_3.setHorizontalAlignment(SwingConstants.LEFT);
			user_already_assigned_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
			user_already_assigned_3.setBounds(290, 63, 367, 20);
			panel_6.add(user_already_assigned_3);
			temp_num_reviewers--;
		}
		
		if (temp_num_reviewers > 0){
			JLabel user_already_assigned_4 = new JLabel(controller.getFullName(users_already_assigned[3]));
			user_already_assigned_4.setHorizontalAlignment(SwingConstants.LEFT);
			user_already_assigned_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
			user_already_assigned_4.setBounds(290, 94, 367, 20);
			panel_6.add(user_already_assigned_4);
			temp_num_reviewers--;
		} else {
			JLabel user_already_assigned_4 = new JLabel("UNASSIGNED");
			user_already_assigned_4.setHorizontalAlignment(SwingConstants.LEFT);
			user_already_assigned_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
			user_already_assigned_4.setBounds(290, 94, 367, 20);
			panel_6.add(user_already_assigned_4);
			temp_num_reviewers--;
		}
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.BOTH;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 6;
		panel.add(separator_3, gbc_separator_3);
		
		JLabel lblSummaryRecommendation = new JLabel("Available Reviewers:");
		GridBagConstraints gbc_lblSummaryRecommendation = new GridBagConstraints();
		gbc_lblSummaryRecommendation.insets = new Insets(0, 0, 5, 0);
		gbc_lblSummaryRecommendation.gridx = 0;
		gbc_lblSummaryRecommendation.gridy = 7;
		panel.add(lblSummaryRecommendation, gbc_lblSummaryRecommendation);
		lblSummaryRecommendation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSummaryRecommendation.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = controller.getAvailableReviewers(current_conf, current_paper, username);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.VERTICAL;
		gbc_list.gridx = 0;
		gbc_list.gridy = 8;
		panel.add(list, gbc_list);
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
				controller.setCurrentPaper("");
				controller.setCurrentConference(null);
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
		 * The action associated with clicking the Submit button.
		 */
		my_submit_action = new AbstractAction(SUBMIT_REVIEW_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: add more to the submit action?
				List<String> results = list.getSelectedValuesList();		//the unchecked results of what the user selected
				String[] the_reviewers = new String[4];						//array to hold the results (max possible is 4)
				int num_open_review_slots = MAX_NUM_REVIWERS - num_of_reviewers; //the number of slots remaining after
																		//considering the number already assigned (if any)
				int num_reviewers_selected = results.size();			//the number of reviewers selected by the user			
				if (num_reviewers_selected > 4){
					JOptionPane.showMessageDialog(contentPane, "Maximum number of reviewers for a paper is 4.");
				}
				else if (num_open_review_slots == 0){
					JOptionPane.showMessageDialog(contentPane, "Maximum number of reviewers for a paper is 4.");
				}
				else if ((num_open_review_slots == 3) && (num_reviewers_selected > 3)){
					JOptionPane.showMessageDialog(contentPane, "One reviewer already assinged.  You selected greater than 3." +
							" Maximum number of reviewers for a paper is 4.");
				}
				else if ((num_open_review_slots == 2) && (num_reviewers_selected > 2)){
					JOptionPane.showMessageDialog(contentPane, "Two reviewers already assinged. You selected greater than 2." +
							" Maximum number of reviewers for a paper is 4.");
				}
				else if ((num_open_review_slots == 1) && (num_reviewers_selected > 1)){
					JOptionPane.showMessageDialog(contentPane, "Three reviewers already assinged. You selected greater than 1." +
							" Maximum number of reviewers for a paper is 4.");
				}
				else if (num_reviewers_selected == 0){
					JOptionPane.showMessageDialog(contentPane, "No reviewer selected.  Select a reviewer or return to " +
							"previous screen");
				}
				else {
					controller.addReviewers(current_conf, current_paper, the_reviewers);
					controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
				}
				System.out.println("number of reviewers already assigned: " + num_of_reviewers);
				System.out.println("number of open slots: " + num_open_review_slots);
				System.out.println("number of reviewers selected: " + num_reviewers_selected);
				System.out.println("selected: " + results);
			}
		};
		my_submit_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_REVIEW_STRING);
		my_submit_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
	}
}

