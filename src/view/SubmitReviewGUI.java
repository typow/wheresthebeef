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
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.GUIEnum.StateOfGUI;
import controller.Conference;
import controller.Controller;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import java.awt.TextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
* The Submit Review Interface JPanel
* 
* Allows the user to submit a review relevant to a paper in the CMS.
* @author Jacob Hall
* @version 11/17/13
*/

@SuppressWarnings("serial")
public class SubmitReviewGUI extends JPanel {
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
	private Action my_submit_action;
	
	/*
	 * the Action associated with the Delete Submission button
	 */
	private Action my_delete_submission_action;
	private final ButtonGroup Q1_Group = new ButtonGroup();
	private final ButtonGroup Q2_Group = new ButtonGroup();
	private final ButtonGroup Q3_Group = new ButtonGroup();
	private final ButtonGroup Q4_Group = new ButtonGroup();
	private final ButtonGroup Q5_Group = new ButtonGroup();
	private final ButtonGroup Q6_Group = new ButtonGroup();
	private final ButtonGroup Q7_Group = new ButtonGroup();
	private final ButtonGroup Q8_Group = new ButtonGroup();
	private final ButtonGroup Q9_Group = new ButtonGroup();
	private final ButtonGroup Q10_Group = new ButtonGroup();

	/**
	 * Create the JPanel.
	 */
	public SubmitReviewGUI(final Controller the_controller) {
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
		contentPane.setPreferredSize(new Dimension(1280, 720));
		
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
		panel_1.setBounds(217, 82, 840, 586);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(222, 70, 582, 444);
		panel_1.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {580};
		gbl_panel.rowHeights = new int[] {30, 150, 0, 30, 30, 30, 30, 0, 30, 200, 0, 30, 30, 150, 50, 150, 30, 150, 30, 150, 50, 150, 50, 150, 30, 150, 30, 150, 30, 150, 30, 150, 30, 200};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblInstructionsToReviewers = new JLabel("Instructions to Reviewers:");
		lblInstructionsToReviewers.setHorizontalAlignment(SwingConstants.LEFT);
		lblInstructionsToReviewers.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblInstructionsToReviewers = new GridBagConstraints();
		gbc_lblInstructionsToReviewers.anchor = GridBagConstraints.WEST;
		gbc_lblInstructionsToReviewers.insets = new Insets(0, 3, 5, 0);
		gbc_lblInstructionsToReviewers.gridx = 0;
		gbc_lblInstructionsToReviewers.gridy = 0;
		panel.add(lblInstructionsToReviewers, gbc_lblInstructionsToReviewers);
		
		JTextArea txtrPleaseProvideA = new JTextArea();
		txtrPleaseProvideA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrPleaseProvideA.setText("Please provide a numeric rating on a 5-point scale for each question, along with a brief           rationale for each numeric rating.  In doing so, please discuss both the strengths and the       weaknesses of each paper so that the editors and authors can understand your reasoning.                                                                                                                                                  Please phrase your reviews politely; even 'bad' papers represent a lot of work on the part of   the authors. The review may be the basis for further revisions of the paper or the work that   the paper reports. We all know how hurtful a needlessly negative review can be, and how      helpful a positive one can be; please try to bear that in mind when you are writing yours.");
		txtrPleaseProvideA.setEditable(false);
		txtrPleaseProvideA.setLineWrap(true);
		GridBagConstraints gbc_txtrPleaseProvideA = new GridBagConstraints();
		gbc_txtrPleaseProvideA.fill = GridBagConstraints.BOTH;
		gbc_txtrPleaseProvideA.insets = new Insets(0, 3, 5, 3);
		gbc_txtrPleaseProvideA.gridx = 0;
		gbc_txtrPleaseProvideA.gridy = 1;
		panel.add(txtrPleaseProvideA, gbc_txtrPleaseProvideA);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 2;
		panel.add(separator_3, gbc_separator_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		panel.add(panel_2, gbc_panel_2);
		
		JLabel lblReviewerName = new JLabel("Reviewer Name:");
		lblReviewerName.setBounds(28, 0, 141, 20);
		lblReviewerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReviewerName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblReviewerName);
		
		JLabel fieldReviewerName = new JLabel("<dynamic>");
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
		gbc_panel_3.gridy = 4;
		panel.add(panel_3, gbc_panel_3);
		
		JLabel lblConferenceName = new JLabel("Conference Name:");
		lblConferenceName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConferenceName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConferenceName.setBounds(28, 0, 141, 20);
		panel_3.add(lblConferenceName);
		
		JLabel fieldConfName = new JLabel("<dynamic>");
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
		gbc_panel_4.gridy = 5;
		panel.add(panel_4, gbc_panel_4);
		
		JLabel lblPaperName = new JLabel("Paper Name:");
		lblPaperName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperName.setBounds(28, 0, 141, 20);
		panel_4.add(lblPaperName);
		
		JLabel fieldPaperName = new JLabel("<dynamic>");
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
		gbc_panel_5.gridy = 6;
		panel.add(panel_5, gbc_panel_5);
		
		JLabel lblPaperAuthor = new JLabel("Paper Author:");
		lblPaperAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperAuthor.setBounds(28, 0, 141, 20);
		panel_5.add(lblPaperAuthor);
		
		JLabel fieldPaperAuthor = new JLabel("<dynamic>");
		fieldPaperAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperAuthor.setBounds(179, 0, 391, 20);
		panel_5.add(fieldPaperAuthor);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_4 = new GridBagConstraints();
		gbc_separator_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_4.insets = new Insets(0, 0, 5, 0);
		gbc_separator_4.gridx = 0;
		gbc_separator_4.gridy = 7;
		panel.add(separator_4, gbc_separator_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 8;
		panel.add(panel_6, gbc_panel_6);
		
		JLabel lblConfidentialCommentsTo = new JLabel("Confidential Comments to the Subprogram Chair");
		lblConfidentialCommentsTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfidentialCommentsTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConfidentialCommentsTo.setBounds(10, 0, 483, 20);
		panel_6.add(lblConfidentialCommentsTo);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 5, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 9;
		panel.add(textArea, gbc_textArea);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_5.insets = new Insets(0, 0, 5, 0);
		gbc_separator_5.gridx = 0;
		gbc_separator_5.gridy = 10;
		panel.add(separator_5, gbc_separator_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 11;
		panel.add(panel_7, gbc_panel_7);
		
		JLabel lblCommentsToThe = new JLabel("Comments to the Author");
		lblCommentsToThe.setHorizontalAlignment(SwingConstants.LEFT);
		lblCommentsToThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCommentsToThe.setBounds(10, 0, 483, 20);
		panel_7.add(lblCommentsToThe);
		
		JTextArea txtrQ1 = new JTextArea();
		txtrQ1.setText("     1.  Can the content be directly applied by classroom instructors or curriculum designers?");
		txtrQ1.setLineWrap(true);
		txtrQ1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ1.setEditable(false);
		GridBagConstraints gbc_txtrQ1 = new GridBagConstraints();
		gbc_txtrQ1.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ1.fill = GridBagConstraints.BOTH;
		gbc_txtrQ1.gridx = 0;
		gbc_txtrQ1.gridy = 12;
		panel.add(txtrQ1, gbc_txtrQ1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_8.setLayout(null);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 13;
		panel.add(panel_8, gbc_panel_8);
		
		JRadioButton Q1_1 = new JRadioButton("[5] Directly and obviously applicable");
		Q1_Group.add(Q1_1);
		Q1_1.setBackground(Color.WHITE);
		Q1_1.setBounds(64, 7, 470, 23);
		panel_8.add(Q1_1);
		
		JRadioButton Q1_2 = new JRadioButton("[4]");
		Q1_Group.add(Q1_2);
		Q1_2.setBackground(Color.WHITE);
		Q1_2.setBounds(64, 33, 470, 23);
		panel_8.add(Q1_2);
		
		JRadioButton Q1_3 = new JRadioButton("[3]");
		Q1_Group.add(Q1_3);
		Q1_3.setBackground(Color.WHITE);
		Q1_3.setBounds(64, 59, 470, 23);
		panel_8.add(Q1_3);
		
		JRadioButton Q1_4 = new JRadioButton("[2]");
		Q1_Group.add(Q1_4);
		Q1_4.setBackground(Color.WHITE);
		Q1_4.setBounds(64, 85, 470, 23);
		panel_8.add(Q1_4);
		
		JRadioButton Q1_5 = new JRadioButton("[1] Not applicable to classroom instruction or design");
		Q1_Group.add(Q1_5);
		Q1_5.setBackground(Color.WHITE);
		Q1_5.setBounds(64, 111, 470, 23);
		panel_8.add(Q1_5);
		
		JTextArea txtrQ2 = new JTextArea();
		txtrQ2.setText("    2.  Does the work appeal to a broad readership interested in engineering education or is it                  narrowly specialized? ");
		txtrQ2.setLineWrap(true);
		txtrQ2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ2.setEditable(false);
		GridBagConstraints gbc_txtrQ2 = new GridBagConstraints();
		gbc_txtrQ2.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ2.fill = GridBagConstraints.BOTH;
		gbc_txtrQ2.gridx = 0;
		gbc_txtrQ2.gridy = 14;
		panel.add(txtrQ2, gbc_txtrQ2);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 15;
		panel.add(panel_9, gbc_panel_9);
		
		JRadioButton Q2_1 = new JRadioButton("[5] Broad");
		Q2_Group.add(Q2_1);
		Q2_1.setBackground(Color.WHITE);
		Q2_1.setBounds(64, 7, 470, 23);
		panel_9.add(Q2_1);
		
		JRadioButton Q2_2 = new JRadioButton("[4]");
		Q2_Group.add(Q2_2);
		Q2_2.setBackground(Color.WHITE);
		Q2_2.setBounds(64, 33, 470, 23);
		panel_9.add(Q2_2);
		
		JRadioButton Q2_3 = new JRadioButton("[3]");
		Q2_Group.add(Q2_3);
		Q2_3.setBackground(Color.WHITE);
		Q2_3.setBounds(64, 59, 470, 23);
		panel_9.add(Q2_3);
		
		JRadioButton Q2_4 = new JRadioButton("[2]");
		Q2_Group.add(Q2_4);
		Q2_4.setBackground(Color.WHITE);
		Q2_4.setBounds(64, 85, 470, 23);
		panel_9.add(Q2_4);
		
		JRadioButton Q2_5 = new JRadioButton("[1] Narrow");
		Q2_Group.add(Q2_5);
		Q2_5.setBackground(Color.WHITE);
		Q2_5.setBounds(64, 111, 470, 23);
		panel_9.add(Q2_5);
		
		JTextArea txtrQ3 = new JTextArea();
		txtrQ3.setText("    3.  Does the work address a significant problem?");
		txtrQ3.setLineWrap(true);
		txtrQ3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ3.setEditable(false);
		GridBagConstraints gbc_txtrQ3 = new GridBagConstraints();
		gbc_txtrQ3.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ3.fill = GridBagConstraints.BOTH;
		gbc_txtrQ3.gridx = 0;
		gbc_txtrQ3.gridy = 16;
		panel.add(txtrQ3, gbc_txtrQ3);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 17;
		panel.add(panel_10, gbc_panel_10);
		
		JRadioButton Q3_1 = new JRadioButton("[5] Significant");
		Q3_Group.add(Q3_1);
		Q3_1.setBackground(Color.WHITE);
		Q3_1.setBounds(64, 7, 470, 23);
		panel_10.add(Q3_1);
		
		JRadioButton Q3_2 = new JRadioButton("[4]");
		Q3_Group.add(Q3_2);
		Q3_2.setBackground(Color.WHITE);
		Q3_2.setBounds(64, 33, 470, 23);
		panel_10.add(Q3_2);
		
		JRadioButton Q3_3 = new JRadioButton("[3]");
		Q3_Group.add(Q3_3);
		Q3_3.setBackground(Color.WHITE);
		Q3_3.setBounds(64, 59, 470, 23);
		panel_10.add(Q3_3);
		
		JRadioButton Q3_4 = new JRadioButton("[2]");
		Q3_Group.add(Q3_4);
		Q3_4.setBackground(Color.WHITE);
		Q3_4.setBounds(64, 85, 470, 23);
		panel_10.add(Q3_4);
		
		JRadioButton Q3_5 = new JRadioButton("[1] Insignificant");
		Q3_Group.add(Q3_5);
		Q3_5.setBackground(Color.WHITE);
		Q3_5.setBounds(64, 111, 470, 23);
		panel_10.add(Q3_5);
		
		JTextArea txtrQ4 = new JTextArea();
		txtrQ4.setText("    4.  Does the author build upon relevant references and bodies of knowledge?");
		txtrQ4.setLineWrap(true);
		txtrQ4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ4.setEditable(false);
		GridBagConstraints gbc_txtrQ4 = new GridBagConstraints();
		gbc_txtrQ4.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ4.fill = GridBagConstraints.BOTH;
		gbc_txtrQ4.gridx = 0;
		gbc_txtrQ4.gridy = 18;
		panel.add(txtrQ4, gbc_txtrQ4);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(Color.WHITE);
		panel_11.setLayout(null);
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.insets = new Insets(0, 0, 5, 0);
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 19;
		panel.add(panel_11, gbc_panel_11);
		
		JRadioButton Q4_1 = new JRadioButton("[5] Relevant and sufficient references to existing bodies of knowledge");
		Q4_Group.add(Q4_1);
		Q4_1.setBackground(Color.WHITE);
		Q4_1.setBounds(66, 7, 470, 23);
		panel_11.add(Q4_1);
		
		JRadioButton Q4_2 = new JRadioButton("[4]");
		Q4_Group.add(Q4_2);
		Q4_2.setBackground(Color.WHITE);
		Q4_2.setBounds(66, 33, 470, 23);
		panel_11.add(Q4_2);
		
		JRadioButton Q4_3 = new JRadioButton("[3]");
		Q4_Group.add(Q4_3);
		Q4_3.setBackground(Color.WHITE);
		Q4_3.setBounds(66, 59, 470, 23);
		panel_11.add(Q4_3);
		
		JRadioButton Q4_4 = new JRadioButton("[2]");
		Q4_Group.add(Q4_4);
		Q4_4.setBackground(Color.WHITE);
		Q4_4.setBounds(66, 85, 470, 23);
		panel_11.add(Q4_4);
		
		JRadioButton Q4_5 = new JRadioButton("[1] Few if any relevant references");
		Q4_Group.add(Q4_5);
		Q4_5.setBackground(Color.WHITE);
		Q4_5.setBounds(66, 111, 470, 23);
		panel_11.add(Q4_5);
		
		JTextArea txtrQ5 = new JTextArea();
		txtrQ5.setWrapStyleWord(true);
		txtrQ5.setText("    5.  If a teaching intervention is reported, is it adequately evaluated in terms of its impact on                learning in actual use?");
		txtrQ5.setLineWrap(true);
		txtrQ5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ5.setEditable(false);
		GridBagConstraints gbc_txtrQ5 = new GridBagConstraints();
		gbc_txtrQ5.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ5.fill = GridBagConstraints.BOTH;
		gbc_txtrQ5.gridx = 0;
		gbc_txtrQ5.gridy = 20;
		panel.add(txtrQ5, gbc_txtrQ5);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.insets = new Insets(0, 0, 5, 0);
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 21;
		panel.add(panel_12, gbc_panel_12);
		
		JRadioButton Q5_1 = new JRadioButton("[5] Excellent evaluation ");
		Q5_Group.add(Q5_1);
		Q5_1.setBackground(Color.WHITE);
		Q5_1.setBounds(66, 7, 470, 23);
		panel_12.add(Q5_1);
		
		JRadioButton Q5_2 = new JRadioButton("[4]");
		Q5_Group.add(Q5_2);
		Q5_2.setBackground(Color.WHITE);
		Q5_2.setBounds(66, 33, 470, 23);
		panel_12.add(Q5_2);
		
		JRadioButton Q5_3 = new JRadioButton("[3]");
		Q5_Group.add(Q5_3);
		Q5_3.setBackground(Color.WHITE);
		Q5_3.setBounds(66, 59, 470, 23);
		panel_12.add(Q5_3);
		
		JRadioButton Q5_4 = new JRadioButton("[2]");
		Q5_Group.add(Q5_4);
		Q5_4.setBackground(Color.WHITE);
		Q5_4.setBounds(66, 85, 470, 23);
		panel_12.add(Q5_4);
		
		JRadioButton Q5_5 = new JRadioButton("[1] Inadequate evaluation");
		Q5_Group.add(Q5_5);
		Q5_5.setBackground(Color.WHITE);
		Q5_5.setBounds(66, 111, 470, 23);
		panel_12.add(Q5_5);
		
		JTextArea txtrQ6 = new JTextArea();
		txtrQ6.setWrapStyleWord(true);
		txtrQ6.setText("    6.  Does the author use methods appropriate to the goals, both for the instructional                           intervention and the evaluation of impact on learning?");
		txtrQ6.setLineWrap(true);
		txtrQ6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ6.setEditable(false);
		GridBagConstraints gbc_txtrQ6 = new GridBagConstraints();
		gbc_txtrQ6.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ6.fill = GridBagConstraints.BOTH;
		gbc_txtrQ6.gridx = 0;
		gbc_txtrQ6.gridy = 22;
		panel.add(txtrQ6, gbc_txtrQ6);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.insets = new Insets(0, 0, 5, 0);
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.gridx = 0;
		gbc_panel_13.gridy = 23;
		panel.add(panel_13, gbc_panel_13);
		
		JRadioButton Q6_1 = new JRadioButton("[5] Appropriate methods");
		Q6_Group.add(Q6_1);
		Q6_1.setBackground(Color.WHITE);
		Q6_1.setBounds(66, 7, 470, 23);
		panel_13.add(Q6_1);
		
		JRadioButton Q6_2 = new JRadioButton("[4]");
		Q6_Group.add(Q6_2);
		Q6_2.setBackground(Color.WHITE);
		Q6_2.setBounds(66, 33, 470, 23);
		panel_13.add(Q6_2);
		
		JRadioButton Q6_3 = new JRadioButton("[3]");
		Q6_Group.add(Q6_3);
		Q6_3.setBackground(Color.WHITE);
		Q6_3.setBounds(66, 59, 470, 23);
		panel_13.add(Q6_3);
		
		JRadioButton Q6_4 = new JRadioButton("[2]");
		Q6_Group.add(Q6_4);
		Q6_4.setBackground(Color.WHITE);
		Q6_4.setBounds(66, 85, 470, 23);
		panel_13.add(Q6_4);
		
		JRadioButton Q6_5 = new JRadioButton("[1] Inappropriate or unclear methods");
		Q6_Group.add(Q6_5);
		Q6_5.setBackground(Color.WHITE);
		Q6_5.setBounds(66, 111, 470, 23);
		panel_13.add(Q6_5);
		
		JTextArea txtrQ7 = new JTextArea();
		txtrQ7.setText("    7.  Did the author provide sufficient detail to replicate and evaluate?");
		txtrQ7.setLineWrap(true);
		txtrQ7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ7.setEditable(false);
		GridBagConstraints gbc_txtrQ7 = new GridBagConstraints();
		gbc_txtrQ7.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ7.fill = GridBagConstraints.BOTH;
		gbc_txtrQ7.gridx = 0;
		gbc_txtrQ7.gridy = 24;
		panel.add(txtrQ7, gbc_txtrQ7);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
		gbc_panel_14.insets = new Insets(0, 0, 5, 0);
		gbc_panel_14.fill = GridBagConstraints.BOTH;
		gbc_panel_14.gridx = 0;
		gbc_panel_14.gridy = 25;
		panel.add(panel_14, gbc_panel_14);
		
		JRadioButton Q7_1 = new JRadioButton("[5] Sufficient detail");
		Q7_Group.add(Q7_1);
		Q7_1.setBackground(Color.WHITE);
		Q7_1.setBounds(66, 7, 470, 23);
		panel_14.add(Q7_1);
		
		JRadioButton Q7_2 = new JRadioButton("[4]");
		Q7_Group.add(Q7_2);
		Q7_2.setBackground(Color.WHITE);
		Q7_2.setBounds(66, 33, 470, 23);
		panel_14.add(Q7_2);
		
		JRadioButton Q7_3 = new JRadioButton("[3]");
		Q7_Group.add(Q7_3);
		Q7_3.setBackground(Color.WHITE);
		Q7_3.setBounds(66, 59, 470, 23);
		panel_14.add(Q7_3);
		
		JRadioButton Q7_4 = new JRadioButton("[2]");
		Q7_Group.add(Q7_4);
		Q7_4.setBackground(Color.WHITE);
		Q7_4.setBounds(66, 85, 470, 23);
		panel_14.add(Q7_4);
		
		JRadioButton Q7_5 = new JRadioButton("[1] Insufficient detail");
		Q7_Group.add(Q7_5);
		Q7_5.setBackground(Color.WHITE);
		Q7_5.setBounds(66, 111, 470, 23);
		panel_14.add(Q7_5);
		
		JTextArea txtrQ8 = new JTextArea();
		txtrQ8.setText("    8.  Is the paper clearly and carefully written?");
		txtrQ8.setLineWrap(true);
		txtrQ8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ8.setEditable(false);
		GridBagConstraints gbc_txtrQ8 = new GridBagConstraints();
		gbc_txtrQ8.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ8.fill = GridBagConstraints.BOTH;
		gbc_txtrQ8.gridx = 0;
		gbc_txtrQ8.gridy = 26;
		panel.add(txtrQ8, gbc_txtrQ8);
		
		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_15 = new GridBagConstraints();
		gbc_panel_15.insets = new Insets(0, 0, 5, 0);
		gbc_panel_15.fill = GridBagConstraints.BOTH;
		gbc_panel_15.gridx = 0;
		gbc_panel_15.gridy = 27;
		panel.add(panel_15, gbc_panel_15);
		
		JRadioButton Q8_1 = new JRadioButton("[5] excellent ");
		Q8_Group.add(Q8_1);
		Q8_1.setBackground(Color.WHITE);
		Q8_1.setBounds(66, 7, 470, 23);
		panel_15.add(Q8_1);
		
		JRadioButton Q8_2 = new JRadioButton("[4] very good ");
		Q8_Group.add(Q8_2);
		Q8_2.setBackground(Color.WHITE);
		Q8_2.setBounds(66, 33, 470, 23);
		panel_15.add(Q8_2);
		
		JRadioButton Q8_3 = new JRadioButton("[3] acceptable ");
		Q8_Group.add(Q8_3);
		Q8_3.setBackground(Color.WHITE);
		Q8_3.setBounds(66, 59, 470, 23);
		panel_15.add(Q8_3);
		
		JRadioButton Q8_4 = new JRadioButton("[2] weak ");
		Q8_Group.add(Q8_4);
		Q8_4.setBackground(Color.WHITE);
		Q8_4.setBounds(66, 85, 470, 23);
		panel_15.add(Q8_4);
		
		JRadioButton Q8_5 = new JRadioButton("[1] unacceptable");
		Q8_Group.add(Q8_5);
		Q8_5.setBackground(Color.WHITE);
		Q8_5.setBounds(66, 111, 470, 23);
		panel_15.add(Q8_5);
		
		JTextArea txtrQ9 = new JTextArea();
		txtrQ9.setText("    9.  Does the paper adhere to accepted standards of style, usage, and composition?");
		txtrQ9.setLineWrap(true);
		txtrQ9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ9.setEditable(false);
		GridBagConstraints gbc_txtrQ9 = new GridBagConstraints();
		gbc_txtrQ9.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ9.fill = GridBagConstraints.BOTH;
		gbc_txtrQ9.gridx = 0;
		gbc_txtrQ9.gridy = 28;
		panel.add(txtrQ9, gbc_txtrQ9);
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_16 = new GridBagConstraints();
		gbc_panel_16.insets = new Insets(0, 0, 5, 0);
		gbc_panel_16.fill = GridBagConstraints.BOTH;
		gbc_panel_16.gridx = 0;
		gbc_panel_16.gridy = 29;
		panel.add(panel_16, gbc_panel_16);
		
		JRadioButton Q9_1 = new JRadioButton("[5] excellent ");
		Q9_Group.add(Q9_1);
		Q9_1.setBackground(Color.WHITE);
		Q9_1.setBounds(66, 7, 470, 23);
		panel_16.add(Q9_1);
		
		JRadioButton Q9_2 = new JRadioButton("[4] very good ");
		Q9_Group.add(Q9_2);
		Q9_2.setBackground(Color.WHITE);
		Q9_2.setBounds(66, 33, 470, 23);
		panel_16.add(Q9_2);
		
		JRadioButton Q9_4 = new JRadioButton("[2] weak ");
		Q9_Group.add(Q9_4);
		Q9_4.setBackground(Color.WHITE);
		Q9_4.setBounds(66, 85, 470, 23);
		panel_16.add(Q9_4);
		
		JRadioButton Q9_5 = new JRadioButton("[1] unacceptable");
		Q9_Group.add(Q9_5);
		Q9_5.setBackground(Color.WHITE);
		Q9_5.setBounds(66, 111, 470, 23);
		panel_16.add(Q9_5);
		
		JRadioButton Q9_3 = new JRadioButton("[3] acceptable ");
		Q9_Group.add(Q9_3);
		Q9_3.setBackground(Color.WHITE);
		Q9_3.setBounds(66, 59, 470, 23);
		panel_16.add(Q9_3);
		
		JTextArea txtrQ10 = new JTextArea();
		txtrQ10.setText("  Summary Rating:");
		txtrQ10.setLineWrap(true);
		txtrQ10.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtrQ10.setEditable(false);
		GridBagConstraints gbc_txtrQ10 = new GridBagConstraints();
		gbc_txtrQ10.insets = new Insets(0, 0, 5, 0);
		gbc_txtrQ10.fill = GridBagConstraints.BOTH;
		gbc_txtrQ10.gridx = 0;
		gbc_txtrQ10.gridy = 30;
		panel.add(txtrQ10, gbc_txtrQ10);
		
		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_17 = new GridBagConstraints();
		gbc_panel_17.insets = new Insets(0, 0, 5, 0);
		gbc_panel_17.fill = GridBagConstraints.BOTH;
		gbc_panel_17.gridx = 0;
		gbc_panel_17.gridy = 31;
		panel.add(panel_17, gbc_panel_17);
		
		JRadioButton Q10_1 = new JRadioButton("[5] strong accept ");
		Q10_Group.add(Q10_1);
		Q10_1.setBackground(Color.WHITE);
		Q10_1.setBounds(66, 7, 470, 23);
		panel_17.add(Q10_1);
		
		JRadioButton Q10_2 = new JRadioButton("[4] accept ");
		Q10_Group.add(Q10_2);
		Q10_2.setBackground(Color.WHITE);
		Q10_2.setBounds(66, 33, 470, 23);
		panel_17.add(Q10_2);
		
		JRadioButton Q10_3 = new JRadioButton("[2] neutral ");
		Q10_Group.add(Q10_3);
		Q10_3.setBackground(Color.WHITE);
		Q10_3.setBounds(66, 85, 470, 23);
		panel_17.add(Q10_3);
		
		JRadioButton Q10_4 = new JRadioButton("[1] reject ");
		Q10_Group.add(Q10_4);
		Q10_4.setBackground(Color.WHITE);
		Q10_4.setBounds(66, 111, 470, 23);
		panel_17.add(Q10_4);
		
		JRadioButton Q10_5 = new JRadioButton("[3] strong reject");
		Q10_Group.add(Q10_5);
		Q10_5.setBackground(Color.WHITE);
		Q10_5.setBounds(66, 59, 470, 23);
		panel_17.add(Q10_5);
		
		JTextArea txtrSummaryComments = new JTextArea();
		txtrSummaryComments.setText("    Summary Comments:");
		txtrSummaryComments.setLineWrap(true);
		txtrSummaryComments.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtrSummaryComments.setEditable(false);
		GridBagConstraints gbc_txtrSummaryComments = new GridBagConstraints();
		gbc_txtrSummaryComments.insets = new Insets(0, 0, 5, 0);
		gbc_txtrSummaryComments.fill = GridBagConstraints.BOTH;
		gbc_txtrSummaryComments.gridx = 0;
		gbc_txtrSummaryComments.gridy = 32;
		panel.add(txtrSummaryComments, gbc_txtrSummaryComments);
		
		JTextArea fieldSummaryComments = new JTextArea();
		fieldSummaryComments.setWrapStyleWord(true);
		fieldSummaryComments.setLineWrap(true);
		fieldSummaryComments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_fieldSummaryComments = new GridBagConstraints();
		gbc_fieldSummaryComments.insets = new Insets(5, 5, 5, 5);
		gbc_fieldSummaryComments.fill = GridBagConstraints.BOTH;
		gbc_fieldSummaryComments.gridx = 0;
		gbc_fieldSummaryComments.gridy = 33;
		panel.add(fieldSummaryComments, gbc_fieldSummaryComments);
		
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
		separator_1.setBounds(182, 69, 14, 492);
		panel_1.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JButton btnEditSubmission = new JButton(my_submit_action);
		btnEditSubmission.setToolTipText(SUBMIT_REVIEW_STRING);
		btnEditSubmission.setText(SUBMIT_REVIEW_TITLE_STRING);
		btnEditSubmission.setBounds(421, 532, 159, 29);
		panel_1.add(btnEditSubmission);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 46, 799, 20);
		panel_1.add(separator_2);
		
		JLabel lblSubmitANew = new JLabel("Submit a Review");
		lblSubmitANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubmitANew.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubmitANew.setBounds(20, 15, 791, 20);
		panel_1.add(lblSubmitANew);
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
				//TODO: add more to the submit action
				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
			}
		};
		my_submit_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_REVIEW_STRING);
		my_submit_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
	}
}

