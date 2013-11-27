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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
* The RecommendPanel JPanel creates a panel containing recommendations already submitted.
* 
* @author Jacob Hall
* @version 98 Date: 11/27/13
*/
@SuppressWarnings("serial")
public class RecommendPanel extends JPanel{

	private JPanel tabRecommendation;
	
	public RecommendPanel(final String the_sub_pc_username, final String the_conf_title, final String the_paper_name, 
			final String the_paper_author, final int answer_index, final String the_rational){
		tabRecommendation = setupRecommendationPanel(the_sub_pc_username, the_conf_title, the_paper_name, 
				the_paper_author, answer_index, the_rational);
	}	 
	
	private JPanel setupRecommendationPanel(final String the_sub_pc_username, final String the_conf_title, 
			final String the_paper_name, final String the_paper_author, final int answer_index, final String the_rational){
		
		JPanel tabRecommendation = new JPanel();
		tabRecommendation.setLayout(null);
		
		JPanel recommendationPanel = new JPanel();
		recommendationPanel.setBackground(Color.WHITE);
		recommendationPanel.setBounds(0, 0, 576, 396);
		tabRecommendation.add(recommendationPanel);
		recommendationPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 576, 396);
		recommendationPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {580, 0};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 150, 30, 200};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_0 = new JPanel();
		panel_0.setLayout(null);
		panel_0.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_0 = new GridBagConstraints();
		gbc_panel_0.insets = new Insets(0, 0, 5, 0);
		gbc_panel_0.fill = GridBagConstraints.BOTH;
		gbc_panel_0.gridx = 0;
		gbc_panel_0.gridy = 0;
		panel.add(panel_0, gbc_panel_0);
		
		JLabel lblRecommendationOfSub = new JLabel("Recommendation of Sub Program Chair:");
		lblRecommendationOfSub.setHorizontalAlignment(SwingConstants.LEFT);
		lblRecommendationOfSub.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRecommendationOfSub.setBounds(10, 0, 483, 20);
		panel_0.add(lblRecommendationOfSub);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		
		JLabel lblSubpcName = new JLabel("SubPC Name:");
		lblSubpcName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubpcName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSubpcName.setBounds(28, 0, 141, 20);
		panel_1.add(lblSubpcName);
		
		JLabel fieldSubPCUsername = new JLabel(the_sub_pc_username);
		fieldSubPCUsername.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSubPCUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldSubPCUsername.setBounds(179, 0, 391, 20);
		panel_1.add(fieldSubPCUsername);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		panel.add(panel_2, gbc_panel_2);
		
		JLabel label_2 = new JLabel("Conference Name:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(28, 0, 141, 20);
		panel_2.add(label_2);
		
		JLabel fieldConfTitle = new JLabel(the_conf_title);
		fieldConfTitle.setHorizontalAlignment(SwingConstants.LEFT);
		fieldConfTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldConfTitle.setBounds(179, 0, 391, 20);
		panel_2.add(fieldConfTitle);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		panel.add(panel_3, gbc_panel_3);
		
		JLabel label_4 = new JLabel("Paper Name:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(28, 0, 141, 20);
		panel_3.add(label_4);
		
		JLabel fieldPaperName = new JLabel(the_paper_name);
		fieldPaperName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperName.setBounds(179, 0, 391, 20);
		panel_3.add(fieldPaperName);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		panel.add(panel_4, gbc_panel_4);
		
		JLabel label_6 = new JLabel("Paper Author:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBounds(28, 0, 141, 20);
		panel_4.add(label_6);
		
		JLabel fieldPaperAuthor = new JLabel(the_paper_author);
		fieldPaperAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperAuthor.setBounds(179, 0, 391, 20);
		panel_4.add(fieldPaperAuthor);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 5;
		panel.add(panel_5, gbc_panel_5);
		
		JLabel label_8 = new JLabel("Summary Recommendation:");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_8.setBounds(10, 0, 483, 20);
		panel_5.add(label_8);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 6;
		panel.add(panel_6, gbc_panel_6);
		
		JRadioButton q5 = new JRadioButton("[5] Strongly accept");
		q5.setSelected(true);
		q5.setBackground(Color.WHITE);
		q5.setBounds(64, 7, 470, 23);
		q5 = setupButtonAnswers(answer_index, 5, q5);
		panel_6.add(q5);
		
		JRadioButton q4 = new JRadioButton("[4] accept");
		q4.setBackground(Color.WHITE);
		q4.setBounds(64, 33, 470, 23);
		q4 = setupButtonAnswers(answer_index, 4, q4);
		panel_6.add(q4);
		
		JRadioButton q3 = new JRadioButton("[3] neutral");
		q3.setBackground(Color.WHITE);
		q3.setBounds(64, 59, 470, 23);
		q3 = setupButtonAnswers(answer_index, 3, q3);
		panel_6.add(q3);
		
		JRadioButton q2 = new JRadioButton("[2] reject");
		q2.setBackground(Color.WHITE);
		q2.setBounds(64, 85, 470, 23);
		q2 = setupButtonAnswers(answer_index, 2, q2);
		panel_6.add(q2);
		
		JRadioButton q1 = new JRadioButton("[1] strong reject");
		q1.setBackground(Color.WHITE);
		q1.setBounds(64, 111, 470, 23);
		q1 = setupButtonAnswers(answer_index, 1, q1);
		panel_6.add(q1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 7;
		panel.add(panel_7, gbc_panel_7);
		
		JLabel label_9 = new JLabel("Rationale for Recommendation:");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_9.setBounds(10, 0, 483, 20);
		panel_7.add(label_9);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_8.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 8;
		panel.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{580, 0};
		gbl_panel_8.rowHeights = new int[]{200, 0};
		gbl_panel_8.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		JTextArea fieldRecommendationRationale = new JTextArea(the_rational);
		fieldRecommendationRationale.setWrapStyleWord(true);
		fieldRecommendationRationale.setLineWrap(true);
		fieldRecommendationRationale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldRecommendationRationale.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		gbc_textArea.insets = new Insets(5, 10, 5, 15);
		panel_8.add(fieldRecommendationRationale, gbc_textArea);

		return tabRecommendation;
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
	 * Getter for the RecommendPanel JPanel.
	 * 
	 * <dt><b>Preconditions: The RecommendPanel has already been instantiated.</b><dd>
	 * <dt><b>Postconditions: The RecommendPanel JPanel is returned.</b><dd>
	 * @return contentPane JPanel containing the RecommendPanel.
	 */
	public JComponent getGUI() {
		return tabRecommendation;
	}
}

