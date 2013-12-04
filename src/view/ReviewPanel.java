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
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import controller.Review;

/**
* The Review Panel creates a sub-panel of Reviews submitted
* to be displayed in the ManagePaperGUI
* 
* @author Jacob Hall
* @version 204 Date: 12/4/13
*/
@SuppressWarnings("serial")
public class ReviewPanel extends JPanel{

	/*
	 * the panel containing the review data.
	 */
	private Review review;
	
	/*
	 * the index of the question currently in focus
	 */
	private int index;
	
	/*
	 * the JPanel containing the review tab.
	 */
	private JPanel tabReview;
	
	/*
	 * the JScrollPane containing all of the review panels.
	 */
	private JScrollPane scrollPane;
	
	/**
	 * Constructor sets up a single review panel.  Four possible per paper.
	 * 
	 * <dt><b>Preconditions: The controller object has been instantiated.</b><dd>
	 * <dt><b>Postconditions: A JPanel is created to represent the most current data 
	 * 							and status concerning a paper</b><dd>
	 * @param the_review
	 * @param the_index
	 * @param reviewer_name_viewable
	 * @param comments_to_subpc_viewable
	 * @param authors_name_viewable
	 */
	public ReviewPanel(final Review the_review, final int the_index, 
			final boolean reviewer_name_viewable, 
			final boolean comments_to_subpc_viewable, 
			final boolean authors_name_viewable){
		review = the_review;
		index = the_index;
		setupReviewPanel(reviewer_name_viewable, comments_to_subpc_viewable, 
				authors_name_viewable);
	}	 
	
	private void setupReviewPanel(final boolean reviewer_name_viewable, 
			final boolean comments_to_subpc_viewable, 
			final boolean authors_name_viewable){
		
		tabReview = new JPanel();
		tabReview.setBackground(Color.WHITE);
		tabReview.setLayout(null);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 550, 346);
		tabReview.add(scrollPane);
		
		JPanel primary_panel = new JPanel();
		scrollPane.setViewportView(primary_panel);
		primary_panel.setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {550};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 150, 30, 30, 50, 
				150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 50, 150, 
				50, 150, 50, 150, 50, 150};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 
				1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		primary_panel.setLayout(gbl_panel);
		
		JLabel lblReviewer = new JLabel("Reviewer #" + index);
		lblReviewer.setHorizontalAlignment(SwingConstants.LEFT);
		lblReviewer.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblReviewer = new GridBagConstraints();
		gbc_lblReviewer.anchor = GridBagConstraints.WEST;
		gbc_lblReviewer.insets = new Insets(0, 3, 5, 0);
		gbc_lblReviewer.gridx = 0;
		gbc_lblReviewer.gridy = 0;
		primary_panel.add(lblReviewer, gbc_lblReviewer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		primary_panel.add(panel_1, gbc_panel_1);
		
		JLabel fieldReviewer = new JLabel("Reviewer Name:");
		fieldReviewer.setHorizontalAlignment(SwingConstants.RIGHT);
		fieldReviewer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fieldReviewer.setBounds(28, 0, 141, 20);
		panel_1.add(fieldReviewer);
		
		JLabel fieldReviewerName;
		if (reviewer_name_viewable){
			fieldReviewerName = new JLabel(review.getUsername());
		} else {
			fieldReviewerName = new JLabel("*********");
		}
		fieldReviewerName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReviewerName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReviewerName.setBounds(179, 0, 391, 20);
		panel_1.add(fieldReviewerName);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		primary_panel.add(panel_2, gbc_panel_2);
		
		JLabel lblPaperName = new JLabel("Paper Name:");
		lblPaperName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperName.setBounds(28, 0, 141, 20);
		panel_2.add(lblPaperName);
		
		JLabel fieldPaperName = new JLabel(review.getPaper());
		fieldPaperName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperName.setBounds(179, 0, 391, 20);
		panel_2.add(fieldPaperName);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		primary_panel.add(panel_3, gbc_panel_3);
		

		JLabel	lblPaperAuthor = new JLabel("Paper Author:");
		lblPaperAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperAuthor.setBounds(28, 0, 141, 20);
		panel_3.add(lblPaperAuthor);
		
		JLabel fieldPaperAuthor;
		if (authors_name_viewable){
			fieldPaperAuthor = new JLabel(review.getPaperAuthor());
		} else {
			fieldPaperAuthor = new JLabel("*********");
		}
		fieldPaperAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperAuthor.setBounds(179, 0, 391, 20);
		panel_3.add(fieldPaperAuthor);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		primary_panel.add(separator_1, gbc_separator_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 5;
		primary_panel.add(panel_4, gbc_panel_4);
		
		JLabel lblCommentsToSubPC = new JLabel("Confidential Comments to the Subprogram Chair");
		lblCommentsToSubPC.setHorizontalAlignment(SwingConstants.LEFT);
		lblCommentsToSubPC.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCommentsToSubPC.setBounds(10, 0, 483, 20);
		panel_4.add(lblCommentsToSubPC);
		
		JTextArea fieldCommentsToSubPC;
		if (comments_to_subpc_viewable){
			fieldCommentsToSubPC = new JTextArea(review.getCommentsToSubPC());
		} else {
			fieldCommentsToSubPC = new JTextArea();
		}
		fieldCommentsToSubPC.setWrapStyleWord(true);
		fieldCommentsToSubPC.setLineWrap(true);
		fieldCommentsToSubPC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldCommentsToSubPC.setWrapStyleWord(true);
		fieldCommentsToSubPC.setEditable(false);
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.insets = new Insets(5, 10, 5, 15);
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 6;
		primary_panel.add(fieldCommentsToSubPC, gbc_textArea_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 7;
		primary_panel.add(separator_2, gbc_separator_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 8;
		primary_panel.add(panel_5, gbc_panel_5);
		
		JLabel lblCommentsToAuthor = new JLabel("Comments to the Author");
		lblCommentsToAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		lblCommentsToAuthor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCommentsToAuthor.setBounds(10, 0, 483, 20);
		panel_5.add(lblCommentsToAuthor);
		
		primary_panel = setupQuestionFields(primary_panel);
		
		JTextArea textArea_12 = new JTextArea();
		textArea_12.setText("    Summary Comments:");
		textArea_12.setLineWrap(true);
		textArea_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		textArea_12.setEditable(false);
		GridBagConstraints gbc_textArea_12 = new GridBagConstraints();
		gbc_textArea_12.fill = GridBagConstraints.BOTH;
		gbc_textArea_12.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_12.gridx = 0;
		gbc_textArea_12.gridy = 29;
		primary_panel.add(textArea_12, gbc_textArea_12);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_16.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_16 = new GridBagConstraints();
		gbc_panel_16.fill = GridBagConstraints.BOTH;
		gbc_panel_16.gridx = 0;
		gbc_panel_16.gridy = 30;
		primary_panel.add(panel_16, gbc_panel_16);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[]{550, 0};
		gbl_panel_16.rowHeights = new int[]{200, 0};
		gbl_panel_16.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_16.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_16.setLayout(gbl_panel_16);
		
		JTextArea fieldSummaryComments = new JTextArea(review.getSummaryComments());
		fieldSummaryComments.setWrapStyleWord(true);
		fieldSummaryComments.setLineWrap(true);
		fieldSummaryComments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldSummaryComments.setEditable(false);
		GridBagConstraints gbc_textArea_13 = new GridBagConstraints();
		gbc_textArea_13.fill = GridBagConstraints.BOTH;
		gbc_textArea_13.insets = new Insets(5, 10, 5, 15);
		gbc_textArea_13.gridx = 0;
		gbc_textArea_13.gridy = 0;
		panel_16.add(fieldSummaryComments, gbc_textArea_13);
	}
	
	/**
	 * Method sets up the viewable only panel that will display the results of the
	 * reviews submitted relative to the paper.
	 * 
	 * <dt><b>Preconditions: A JPanel is made available to place the data in.</b><dd>
	 * <dt><b>Postconditions: The JPanel contains all of the questions and recorded
	 * 						answers relevant to the given review in the database.</b><dd>
	 * 
	 * @param primary_panel The JPanel to be packed with the questions and results recorded.
	 * @return primary_panel The JPanel now containing all of the questions and results recorded.
	 */
	private JPanel setupQuestionFields(final JPanel primary_panel){	
		JTextArea txtrQ1 = new JTextArea();
		txtrQ1.setWrapStyleWord(true);
		txtrQ1.setText("     1.  Can the content be directly applied by classroom instructors or curriculum                        designers?");
		txtrQ1.setLineWrap(true);
		txtrQ1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ1.setEditable(false);
		GridBagConstraints gbc_txtrCanThe = new GridBagConstraints();
		gbc_txtrCanThe.fill = GridBagConstraints.BOTH;
		gbc_txtrCanThe.insets = new Insets(0, 0, 5, 0);
		gbc_txtrCanThe.gridx = 0;
		gbc_txtrCanThe.gridy = 9;
		primary_panel.add(txtrQ1, gbc_txtrCanThe);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 10;
		primary_panel.add(panel_6, gbc_panel_6);
		
		JRadioButton q1_5 = new JRadioButton("[5] Directly and obviously applicable");
		q1_5.setBackground(Color.WHITE);
		q1_5.setBounds(64, 7, 470, 23);
		q1_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[0], 5, q1_5);
		panel_6.add(q1_5);
		
		JRadioButton q1_4 = new JRadioButton("[4]");
		q1_4.setBackground(Color.WHITE);
		q1_4.setBounds(64, 33, 470, 23);
		q1_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[0], 4, q1_4);
		panel_6.add(q1_4);
		
		JRadioButton q1_3 = new JRadioButton("[3]");
		q1_3.setBackground(Color.WHITE);
		q1_3.setBounds(64, 59, 470, 23);
		q1_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[0], 3, q1_3);
		panel_6.add(q1_3);
		
		JRadioButton q1_2 = new JRadioButton("[2]");
		q1_2.setBackground(Color.WHITE);
		q1_2.setBounds(64, 85, 470, 23);
		q1_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[0], 2, q1_2);
		panel_6.add(q1_2);
		
		JRadioButton q1_1 = new JRadioButton("[1] Not applicable to classroom instruction or design");
		q1_1.setBackground(Color.WHITE);
		q1_1.setBounds(64, 111, 470, 23);
		q1_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[0], 1, q1_1);
		panel_6.add(q1_1);
		
		JTextArea txtrQ2 = new JTextArea();
		txtrQ2.setWrapStyleWord(true);
		txtrQ2.setText("    2.  Does the work appeal to a broad readership interested in engineering education or            is it narrowly specialized? ");
		txtrQ2.setLineWrap(true);
		txtrQ2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ2.setEditable(false);
		GridBagConstraints gbc_txtrDoesThe = new GridBagConstraints();
		gbc_txtrDoesThe.fill = GridBagConstraints.BOTH;
		gbc_txtrDoesThe.insets = new Insets(0, 0, 5, 0);
		gbc_txtrDoesThe.gridx = 0;
		gbc_txtrDoesThe.gridy = 11;
		primary_panel.add(txtrQ2, gbc_txtrDoesThe);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 12;
		primary_panel.add(panel_7, gbc_panel_7);
		
		JRadioButton q2_5 = new JRadioButton("[5] Broad");
		q2_5.setBackground(Color.WHITE);
		q2_5.setBounds(64, 7, 470, 23);
		q2_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[1], 5, q2_5);
		panel_7.add(q2_5);
		
		JRadioButton q2_4 = new JRadioButton("[4]");
		q2_4.setBackground(Color.WHITE);
		q2_4.setBounds(64, 33, 470, 23);
		q2_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[1], 4, q2_4);
		panel_7.add(q2_4);
		
		JRadioButton q2_3 = new JRadioButton("[3]");
		q2_3.setBackground(Color.WHITE);
		q2_3.setBounds(64, 59, 470, 23);
		q2_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[1], 3, q2_3);
		panel_7.add(q2_3);
		
		JRadioButton q2_2 = new JRadioButton("[2]");
		q2_2.setBackground(Color.WHITE);
		q2_2.setBounds(64, 85, 470, 23);
		q2_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[1], 2, q2_2);
		panel_7.add(q2_2);
		
		JRadioButton q2_1 = new JRadioButton("[1] Narrow");
		q2_1.setBackground(Color.WHITE);
		q2_1.setBounds(64, 111, 470, 23);
		q2_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[1], 1, q2_1);
		panel_7.add(q2_1);
		
		JTextArea txtQ3 = new JTextArea();
		txtQ3.setWrapStyleWord(true);
		txtQ3.setText("    3.  Does the work address a significant problem?");
		txtQ3.setLineWrap(true);
		txtQ3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQ3.setEditable(false);
		GridBagConstraints gbc_textArea_4 = new GridBagConstraints();
		gbc_textArea_4.fill = GridBagConstraints.BOTH;
		gbc_textArea_4.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_4.gridx = 0;
		gbc_textArea_4.gridy = 13;
		primary_panel.add(txtQ3, gbc_textArea_4);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 14;
		primary_panel.add(panel_8, gbc_panel_8);
		
		JRadioButton q3_5 = new JRadioButton("[5] Significant");
		q3_5.setSelected(true);
		q3_5.setBackground(Color.WHITE);
		q3_5.setBounds(64, 7, 470, 23);
		q3_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[2], 5, q3_5);
		panel_8.add(q3_5);
		
		JRadioButton q3_4 = new JRadioButton("[4]");
		q3_4.setBackground(Color.WHITE);
		q3_4.setBounds(64, 33, 470, 23);
		q3_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[2], 4, q3_4);
		panel_8.add(q3_4);
		
		JRadioButton q3_3 = new JRadioButton("[3]");
		q3_3.setBackground(Color.WHITE);
		q3_3.setBounds(64, 59, 470, 23);
		q3_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[2], 3, q3_3);
		panel_8.add(q3_3);
		
		JRadioButton q3_2 = new JRadioButton("[2]");
		q3_2.setBackground(Color.WHITE);
		q3_2.setBounds(64, 85, 470, 23);
		q3_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[2], 2, q3_2);
		panel_8.add(q3_2);
		
		JRadioButton q3_1 = new JRadioButton("[1] Insignificant");
		q3_1.setBackground(Color.WHITE);
		q3_1.setBounds(64, 111, 470, 23);
		q3_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[2], 1, q3_1);
		panel_8.add(q3_1);
		
		JTextArea txtrQ4 = new JTextArea();
		txtrQ4.setWrapStyleWord(true);
		txtrQ4.setText("    4.  Does the author build upon relevant references and bodies of knowledge?");
		txtrQ4.setLineWrap(true);
		txtrQ4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ4.setEditable(false);
		GridBagConstraints gbc_textArea_5 = new GridBagConstraints();
		gbc_textArea_5.fill = GridBagConstraints.BOTH;
		gbc_textArea_5.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_5.gridx = 0;
		gbc_textArea_5.gridy = 15;
		primary_panel.add(txtrQ4, gbc_textArea_5);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 16;
		primary_panel.add(panel_9, gbc_panel_9);
		
		JRadioButton q4_5 = new JRadioButton("[5] Relevant and sufficient references to existing bodies of knowledge");
		q4_5.setSelected(true);
		q4_5.setBackground(Color.WHITE);
		q4_5.setBounds(66, 7, 470, 23);
		q4_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[3], 5, q4_5);
		panel_9.add(q4_5);
		
		JRadioButton q4_4 = new JRadioButton("[4]");
		q4_4.setBackground(Color.WHITE);
		q4_4.setBounds(66, 33, 470, 23);
		q4_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[3], 4, q4_4);
		panel_9.add(q4_4);
		
		JRadioButton q4_3 = new JRadioButton("[3]");
		q4_3.setBackground(Color.WHITE);
		q4_3.setBounds(66, 59, 470, 23);
		q4_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[3], 3, q4_3);
		panel_9.add(q4_3);
		
		JRadioButton q4_2 = new JRadioButton("[2]");
		q4_2.setBackground(Color.WHITE);
		q4_2.setBounds(66, 85, 470, 23);
		q4_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[3], 2, q4_2);
		panel_9.add(q4_2);
		
		JRadioButton q4_1 = new JRadioButton("[1] Few if any relevant references");
		q4_1.setBackground(Color.WHITE);
		q4_1.setBounds(66, 111, 470, 23);
		q4_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[3], 1, q4_1);
		panel_9.add(q4_1);
		
		JTextArea txtrQ5 = new JTextArea();
		txtrQ5.setWrapStyleWord(true);
		txtrQ5.setText("    5.  If a teaching intervention is reported, is it adequately evaluated in terms of its                     impact on learning in actual use?");
		txtrQ5.setLineWrap(true);
		txtrQ5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ5.setEditable(false);
		GridBagConstraints gbc_txtrArea_6 = new GridBagConstraints();
		gbc_txtrArea_6.fill = GridBagConstraints.BOTH;
		gbc_txtrArea_6.insets = new Insets(0, 0, 5, 0);
		gbc_txtrArea_6.gridx = 0;
		gbc_txtrArea_6.gridy = 17;
		primary_panel.add(txtrQ5, gbc_txtrArea_6);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 18;
		primary_panel.add(panel_10, gbc_panel_10);
		
		JRadioButton q5_5 = new JRadioButton("[5] Excellent evaluation ");
		q5_5.setSelected(true);
		q5_5.setBackground(Color.WHITE);
		q5_5.setBounds(66, 7, 470, 23);
		q5_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[4], 5, q5_5);
		panel_10.add(q5_5);
		
		JRadioButton q5_4 = new JRadioButton("[4]");
		q5_4.setBackground(Color.WHITE);
		q5_4.setBounds(66, 33, 470, 23);
		q5_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[4], 4, q5_4);
		panel_10.add(q5_4);
		
		JRadioButton q5_3 = new JRadioButton("[3]");
		q5_3.setBackground(Color.WHITE);
		q5_3.setBounds(66, 59, 470, 23);
		q5_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[4], 3, q5_3);
		panel_10.add(q5_3);
		
		JRadioButton q5_2 = new JRadioButton("[2]");
		q5_2.setBackground(Color.WHITE);
		q5_2.setBounds(66, 85, 470, 23);
		q5_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[4], 2, q5_2);
		panel_10.add(q5_2);
		
		JRadioButton q5_1 = new JRadioButton("[1] Inadequate evaluation");
		q5_1.setBackground(Color.WHITE);
		q5_1.setBounds(66, 111, 470, 23);
		q5_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[4], 1, q5_1);
		panel_10.add(q5_1);
		
		JTextArea txtrQ6 = new JTextArea();
		txtrQ6.setWrapStyleWord(true);
		txtrQ6.setText("    6.  Does the author use methods appropriate to the goals, both for the instructional                  intervention and the evaluation of impact on learning?");
		txtrQ6.setLineWrap(true);
		txtrQ6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ6.setEditable(false);
		GridBagConstraints gbc_textArea_7 = new GridBagConstraints();
		gbc_textArea_7.fill = GridBagConstraints.BOTH;
		gbc_textArea_7.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_7.gridx = 0;
		gbc_textArea_7.gridy = 19;
		primary_panel.add(txtrQ6, gbc_textArea_7);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.insets = new Insets(0, 0, 5, 0);
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 20;
		primary_panel.add(panel_11, gbc_panel_11);
		
		JRadioButton q6_5 = new JRadioButton("[5] Appropriate methods");
		q6_5.setSelected(true);
		q6_5.setBackground(Color.WHITE);
		q6_5.setBounds(66, 7, 470, 23);
		q6_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[5], 5, q6_5);
		panel_11.add(q6_5);
		
		JRadioButton q6_4 = new JRadioButton("[4]");
		q6_4.setBackground(Color.WHITE);
		q6_4.setBounds(66, 33, 470, 23);
		q6_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[5], 4, q6_4);
		panel_11.add(q6_4);
		
		JRadioButton q6_3 = new JRadioButton("[3]");
		q6_3.setBackground(Color.WHITE);
		q6_3.setBounds(66, 59, 470, 23);
		q6_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[5], 3, q6_3);
		panel_11.add(q6_3);
		
		JRadioButton q6_2 = new JRadioButton("[2]");
		q6_2.setBackground(Color.WHITE);
		q6_2.setBounds(66, 85, 470, 23);
		q6_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[5], 2, q6_2);
		panel_11.add(q6_2);
		
		JRadioButton q6_1 = new JRadioButton("[1] Inappropriate or unclear methods");
		q6_1.setBackground(Color.WHITE);
		q6_1.setBounds(66, 111, 470, 23);
		q6_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[5], 1, q6_1);
		panel_11.add(q6_1);
		
		JTextArea txtrQ7 = new JTextArea();
		txtrQ7.setText("    7.  Did the author provide sufficient detail to replicate and evaluate?");
		txtrQ7.setLineWrap(true);
		txtrQ7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ7.setEditable(false);
		GridBagConstraints gbc_textArea_8 = new GridBagConstraints();
		gbc_textArea_8.fill = GridBagConstraints.BOTH;
		gbc_textArea_8.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_8.gridx = 0;
		gbc_textArea_8.gridy = 21;
		primary_panel.add(txtrQ7, gbc_textArea_8);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.insets = new Insets(0, 0, 5, 0);
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 22;
		primary_panel.add(panel_12, gbc_panel_12);
		
		JRadioButton q7_5 = new JRadioButton("[5] Sufficient detail");
		q7_5.setSelected(true);
		q7_5.setBackground(Color.WHITE);
		q7_5.setBounds(66, 7, 470, 23);
		q7_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[6], 5, q7_5);
		panel_12.add(q7_5);
		
		JRadioButton q7_4 = new JRadioButton("[4]");
		q7_4.setBackground(Color.WHITE);
		q7_4.setBounds(66, 33, 470, 23);
		q7_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[6], 4, q7_4);
		panel_12.add(q7_4);
		
		JRadioButton q7_3 = new JRadioButton("[3]");
		q7_3.setBackground(Color.WHITE);
		q7_3.setBounds(66, 59, 470, 23);
		q7_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[6], 3, q7_3);
		panel_12.add(q7_3);
		
		JRadioButton q7_2 = new JRadioButton("[2]");
		q7_2.setBackground(Color.WHITE);
		q7_2.setBounds(66, 85, 470, 23);
		q7_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[6], 2, q7_2);
		panel_12.add(q7_2);
		
		JRadioButton q7_1 = new JRadioButton("[1] Insufficient detail");
		q7_1.setBackground(Color.WHITE);
		q7_1.setBounds(66, 111, 470, 23);
		q7_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[6], 1, q7_1);
		panel_12.add(q7_1);
		
		JTextArea txtrQ8 = new JTextArea();
		txtrQ8.setText("    8.  Is the paper clearly and carefully written?");
		txtrQ8.setLineWrap(true);
		txtrQ8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ8.setEditable(false);
		GridBagConstraints gbc_textArea_9 = new GridBagConstraints();
		gbc_textArea_9.fill = GridBagConstraints.BOTH;
		gbc_textArea_9.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_9.gridx = 0;
		gbc_textArea_9.gridy = 23;
		primary_panel.add(txtrQ8, gbc_textArea_9);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.insets = new Insets(0, 0, 5, 0);
		gbc_panel_13.gridx = 0;
		gbc_panel_13.gridy = 24;
		primary_panel.add(panel_13, gbc_panel_13);
		
		JRadioButton q8_5 = new JRadioButton("[5] excellent ");
		q8_5.setSelected(true);
		q8_5.setBackground(Color.WHITE);
		q8_5.setBounds(66, 7, 470, 23);
		q8_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[7], 5, q8_5);
		panel_13.add(q8_5);
		
		JRadioButton q8_4 = new JRadioButton("[4] very good ");
		q8_4.setBackground(Color.WHITE);
		q8_4.setBounds(66, 33, 470, 23);
		q8_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[7], 4, q8_4);
		panel_13.add(q8_4);
		
		JRadioButton q8_3 = new JRadioButton("[3] acceptable ");
		q8_3.setBackground(Color.WHITE);
		q8_3.setBounds(66, 59, 470, 23);
		q8_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[7], 3, q8_3);
		panel_13.add(q8_3);
		
		JRadioButton q8_2 = new JRadioButton("[2] weak ");
		q8_2.setBackground(Color.WHITE);
		q8_2.setBounds(66, 85, 470, 23);
		q8_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[7], 2, q8_2);
		panel_13.add(q8_2);
		
		JRadioButton q8_1 = new JRadioButton("[1] unacceptable");
		q8_1.setBackground(Color.WHITE);
		q8_1.setBounds(66, 111, 470, 23);
		q8_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[7], 1, q8_1);
		panel_13.add(q8_1);
		
		JTextArea txtrQ9 = new JTextArea();
		txtrQ9.setText("    9.  Does the paper adhere to accepted standards of style, usage, and composition?");
		txtrQ9.setLineWrap(true);
		txtrQ9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrQ9.setEditable(false);
		GridBagConstraints gbc_textArea_10 = new GridBagConstraints();
		gbc_textArea_10.fill = GridBagConstraints.BOTH;
		gbc_textArea_10.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_10.gridx = 0;
		gbc_textArea_10.gridy = 25;
		primary_panel.add(txtrQ9, gbc_textArea_10);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
		gbc_panel_14.fill = GridBagConstraints.BOTH;
		gbc_panel_14.insets = new Insets(0, 0, 5, 0);
		gbc_panel_14.gridx = 0;
		gbc_panel_14.gridy = 26;
		primary_panel.add(panel_14, gbc_panel_14);
		
		JRadioButton q9_5 = new JRadioButton("[5] excellent ");
		q9_5.setSelected(true);
		q9_5.setBackground(Color.WHITE);
		q9_5.setBounds(66, 7, 470, 23);
		q9_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[8], 5, q9_5);
		panel_14.add(q9_5);
		
		JRadioButton q9_4 = new JRadioButton("[4] very good ");
		q9_4.setBackground(Color.WHITE);
		q9_4.setBounds(66, 33, 470, 23);
		q9_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[8], 4, q9_4);
		panel_14.add(q9_4);
		
		JRadioButton q9_3 = new JRadioButton("[3] acceptable ");
		q9_3.setBackground(Color.WHITE);
		q9_3.setBounds(66, 59, 470, 23);
		q9_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[8], 3, q9_3);
		panel_14.add(q9_3);
		
		JRadioButton q9_2 = new JRadioButton("[2] weak ");
		q9_2.setBackground(Color.WHITE);
		q9_2.setBounds(66, 85, 470, 23);
		q9_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[8], 2, q9_2);
		panel_14.add(q9_2);
		
		JRadioButton q9_1 = new JRadioButton("[1] unacceptable");
		q9_1.setBackground(Color.WHITE);
		q9_1.setBounds(66, 111, 470, 23);
		q9_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[8], 1, q9_1);
		panel_14.add(q9_1);
		
		JTextArea txtrQ10 = new JTextArea();
		txtrQ10.setText("  Summary Rating:");
		txtrQ10.setLineWrap(true);
		txtrQ10.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtrQ10.setEditable(false);
		GridBagConstraints gbc_textArea_11 = new GridBagConstraints();
		gbc_textArea_11.fill = GridBagConstraints.BOTH;
		gbc_textArea_11.insets = new Insets(0, 0, 5, 0);
		gbc_textArea_11.gridx = 0;
		gbc_textArea_11.gridy = 27;
		primary_panel.add(txtrQ10, gbc_textArea_11);
		
		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_15 = new GridBagConstraints();
		gbc_panel_15.fill = GridBagConstraints.BOTH;
		gbc_panel_15.insets = new Insets(0, 0, 5, 0);
		gbc_panel_15.gridx = 0;
		gbc_panel_15.gridy = 28;
		primary_panel.add(panel_15, gbc_panel_15);
		
		JRadioButton q10_5 = new JRadioButton("[5] strong accept ");
		q10_5.setSelected(true);
		q10_5.setBackground(Color.WHITE);
		q10_5.setBounds(66, 7, 470, 23);
		q10_5 = setupButtonAnswers(review.getAnswersToRadioBtns()[9], 5, q10_5);
		panel_15.add(q10_5);
		
		JRadioButton q10_4 = new JRadioButton("[4] accept ");
		q10_4.setBackground(Color.WHITE);
		q10_4.setBounds(66, 33, 470, 23);
		q10_4 = setupButtonAnswers(review.getAnswersToRadioBtns()[9], 4, q10_4);
		panel_15.add(q10_4);
		
		JRadioButton q10_3 = new JRadioButton("[3] neutral ");
		q10_3.setBackground(Color.WHITE);
		q10_3.setBounds(66, 59, 470, 23);
		q10_3 = setupButtonAnswers(review.getAnswersToRadioBtns()[9], 3, q10_3);
		panel_15.add(q10_3);
		
		JRadioButton q10_2 = new JRadioButton("[2] reject ");
		q10_2.setBackground(Color.WHITE);
		q10_2.setBounds(66, 85, 470, 23);
		q10_2 = setupButtonAnswers(review.getAnswersToRadioBtns()[9], 2, q10_2);
		panel_15.add(q10_2);
		
		JRadioButton q10_1 = new JRadioButton("[1] strong reject");
		q10_1.setBackground(Color.WHITE);
		q10_1.setBounds(66, 111, 470, 23);
		q10_1 = setupButtonAnswers(review.getAnswersToRadioBtns()[9], 1, q10_1);
		panel_15.add(q10_1);
		
		return primary_panel;
	}
	
	/**
	 * Method is used to determine which button is selected based on an index recorded.
	 * 
	 * <dt><b>Preconditions: A button is selected.</b><dd>
	 * <dt><b>Postconditions: The button is returned.</b><dd>
	 * 
	 * @param answer  The answer recorded in the database as selected for the given question
	 * @param the_index
	 * @param the_button
	 * @return the_button.  The button that is selected based on the Index passed in.
	 */
	private JRadioButton setupButtonAnswers(final int answer, final int the_index, final JRadioButton the_button){
		if (the_index == answer){
			the_button.setSelected(true);
		} else {
			the_button.setSelected(false);
		}
		the_button.setEnabled(false);
		return the_button;
	}

	/**
	 * Getter for the ReviewPanel JPanel.
	 * 
	 * <dt><b>Preconditions: The ReviewPanel has already been instantiated.</b><dd>
	 * <dt><b>Postconditions: The ReviewPanel JPanel is returned.</b><dd>
	 * @return contentPane JPanel containing the ReviewPanel.
	 */
	public JComponent getGUI() {
		return tabReview;
	}
}
