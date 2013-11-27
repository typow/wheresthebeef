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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import view.GUIEnum.paperStatusAdminViewable;

/**
* The Management JPanel used within the ManagePaperGUI to display current administrative
* fields.
* 
* Allows the Program Chair and SubPC to view paper management details in the CMS.
* @author Jacob Hall
* @version 90 Date: 11/27/13
*/
@SuppressWarnings("serial")
public class ManagementPanel extends JPanel{

	/*
	 * the JPanel component containing the management tab.
	 */
	private JPanel tabManagement;
	
	/**
	 * 
	 * 	 * <dt><b>Preconditions: The controller object has been instantiated.  The PC or SubPC 
	 * 						is the user logged in  in relation to the paper being evaluated.</b><dd>
	 * <dt><b>Postconditions: A JPanel is created to represent the most current data and status 
	 * 						 concerning a paper</b><dd>
	 * @param the_current_paper
	 * @param the_paper_author
	 * @param the_admin_status
	 * @param the_pc_username
	 * @param the_sub_pc_username
	 * @param the_reviewer_usernames
	 */
	public ManagementPanel(final String the_current_paper, final String the_paper_author, 
			final paperStatusAdminViewable the_admin_status, final String the_pc_username,
			final String the_sub_pc_username, final String[] the_reviewer_usernames){
		tabManagement = setupManagementPanel(the_current_paper, the_paper_author, the_admin_status, 
				the_pc_username, the_sub_pc_username, the_reviewer_usernames);
	}	 
	
	/**
	 * Method creates and returns the panel.
	 * 
	 * @param the_current_paper
	 * @param the_paper_author
	 * @param the_admin_status
	 * @param the_pc_username
	 * @param the_sub_pc_username
	 * @param the_reviewer_usernames
	 * @return localTabManagement The tab containing the management JPanel
	 */
	private JPanel setupManagementPanel(final String the_current_paper, final String the_paper_author, 
			final paperStatusAdminViewable the_admin_status, final String the_pc_username,
			final String the_sub_pc_username, final String[] the_reviewer_usernames){
		
		tabManagement = new JPanel();
		tabManagement.setBackground(Color.WHITE);
		tabManagement.setLayout(null);
		
		JPanel localTabManagement = new JPanel();
		localTabManagement.setBackground(Color.WHITE);
		localTabManagement.setLayout(null);
		
		JPanel managementPanel = new JPanel();
		managementPanel.setBounds(0, 0, 576, 396);
		localTabManagement.add(managementPanel);
		managementPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 576, 396);
		managementPanel.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{580, 0};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0};
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
		
		JLabel lblPaperDetails = new JLabel("Paper Details:");
		lblPaperDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaperDetails.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPaperDetails.setBounds(10, 0, 483, 20);
		panel_0.add(lblPaperDetails);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		panel.add(panel_1, gbc_panel_1);
		
		JLabel lblPaperName = new JLabel("Paper Name:");
		lblPaperName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperName.setBounds(28, 0, 141, 20);
		panel_1.add(lblPaperName);
		
		JLabel fieldPaperName = new JLabel(the_current_paper);
		fieldPaperName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperName.setBounds(179, 0, 391, 20);
		panel_1.add(fieldPaperName);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		panel.add(panel_2, gbc_panel_2);
		
		JLabel lblPaperAuthor = new JLabel("Paper Author:");
		lblPaperAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperAuthor.setBounds(28, 0, 141, 20);
		panel_2.add(lblPaperAuthor);
		
		JLabel fieldPaperAuthor = new JLabel(the_paper_author);
		fieldPaperAuthor.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperAuthor.setBounds(179, 0, 391, 20);
		panel_2.add(fieldPaperAuthor);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 3;
		panel.add(panel_3, gbc_panel_3);
		
		JLabel lblPaperAdminStatus = new JLabel("Paper Status:");
		lblPaperAdminStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperAdminStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPaperAdminStatus.setBounds(28, 0, 141, 20);
		panel_3.add(lblPaperAdminStatus);
		
		JLabel fieldPaperAdminStatus = new JLabel(the_admin_status.toString());
		fieldPaperAdminStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPaperAdminStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPaperAdminStatus.setBounds(179, 0, 391, 20);
		panel_3.add(fieldPaperAdminStatus);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 4;
		panel.add(panel_4, gbc_panel_4);
		
		JLabel lblAdministrativeTeam = new JLabel("Administrative Team:");
		lblAdministrativeTeam.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdministrativeTeam.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdministrativeTeam.setBounds(10, 0, 483, 20);
		panel_4.add(lblAdministrativeTeam);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 5;
		panel.add(panel_5, gbc_panel_5);
		
		JLabel lblManageProgramChair = new JLabel("Program Chair:");
		lblManageProgramChair.setHorizontalAlignment(SwingConstants.RIGHT);
		lblManageProgramChair.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblManageProgramChair.setBounds(28, 0, 141, 20);
		panel_5.add(lblManageProgramChair);
		
		JLabel fieldPCName = new JLabel(the_pc_username);
		fieldPCName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPCName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPCName.setBounds(179, 0, 391, 20);
		panel_5.add(fieldPCName);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 6;
		panel.add(panel_6, gbc_panel_6);
		
		JLabel lblFinalNoticeSubmitted = new JLabel("Final Notice Submitted?");
		lblFinalNoticeSubmitted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFinalNoticeSubmitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFinalNoticeSubmitted.setBounds(155, -1, 209, 20);
		panel_6.add(lblFinalNoticeSubmitted);
		
		JLabel fieldFinalNoticeSubmittedStatus = new JLabel("<dynamic>");
		fieldFinalNoticeSubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldFinalNoticeSubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldFinalNoticeSubmittedStatus.setBounds(389, 0, 181, 20);
		panel_6.add(fieldFinalNoticeSubmittedStatus);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 7;
		panel.add(panel_7, gbc_panel_7);
		
		JLabel lblSubPC = new JLabel("Sub-Program Chair:");
		lblSubPC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubPC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSubPC.setBounds(28, 0, 141, 20);
		panel_7.add(lblSubPC);
		
		JLabel fieldSubPCName = new JLabel(the_sub_pc_username);
		fieldSubPCName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldSubPCName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldSubPCName.setBounds(179, 0, 391, 20);
		panel_7.add(fieldSubPCName);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 8;
		panel.add(panel_8, gbc_panel_8);
		
		JLabel lblRecommendationSubmitted = new JLabel("Recommendation Submitted?");
		lblRecommendationSubmitted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRecommendationSubmitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRecommendationSubmitted.setBounds(156, -1, 209, 20);
		panel_8.add(lblRecommendationSubmitted);
		
		JLabel fieldRecommendationSubmittedStatus = new JLabel("<dynamic>");
		fieldRecommendationSubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldRecommendationSubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldRecommendationSubmittedStatus.setBounds(389, 0, 180, 20);
		panel_8.add(fieldRecommendationSubmittedStatus);
		
		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 9;
		panel.add(panel_9, gbc_panel_9);
		
		JLabel lblProgramChair = new JLabel("Reviewers:");
		lblProgramChair.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProgramChair.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProgramChair.setBounds(28, 0, 141, 20);
		panel_9.add(lblProgramChair);
		
		int num_reviews = the_reviewer_usernames.length;
		String reviewer_1_username = "Not assigned yet.";
		if (num_reviews >= 1){
			reviewer_1_username = the_reviewer_usernames[0];
		}
		JLabel fieldReviewer1 = new JLabel(reviewer_1_username);
		fieldReviewer1.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReviewer1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReviewer1.setBounds(179, 0, 391, 20);
		panel_9.add(fieldReviewer1);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 10;
		panel.add(panel_10, gbc_panel_10);
		
		JLabel lblReview1Submitted = new JLabel("Review Submitted?");
		lblReview1Submitted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReview1Submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReview1Submitted.setBounds(156, -1, 209, 20);
		panel_10.add(lblReview1Submitted);
		
		JLabel fieldReview1SubmittedStatus = new JLabel("<dynamic>");
		fieldReview1SubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReview1SubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReview1SubmittedStatus.setBounds(389, 0, 180, 20);
		panel_10.add(fieldReview1SubmittedStatus);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.insets = new Insets(0, 0, 5, 0);
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 11;
		panel.add(panel_11, gbc_panel_11);
		
		String reviewer_2_username = "Not assigned yet.";
		if (num_reviews >= 2){
			reviewer_2_username = the_reviewer_usernames[1];
		}
		JLabel fieldReviewer2 = new JLabel(reviewer_2_username);
		fieldReviewer2.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReviewer2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReviewer2.setBounds(179, 0, 391, 20);
		panel_11.add(fieldReviewer2);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.insets = new Insets(0, 0, 5, 0);
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 12;
		panel.add(panel_12, gbc_panel_12);
		
		JLabel lblReview2Submitted = new JLabel("Review Submitted?");
		lblReview2Submitted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReview2Submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReview2Submitted.setBounds(156, -1, 209, 20);
		panel_12.add(lblReview2Submitted);
		
		JLabel fieldReview2SubmittedStatus = new JLabel("<dynamic>");
		fieldReview2SubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReview2SubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReview2SubmittedStatus.setBounds(389, 0, 180, 20);
		panel_12.add(fieldReview2SubmittedStatus);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.insets = new Insets(0, 0, 5, 0);
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.gridx = 0;
		gbc_panel_13.gridy = 13;
		panel.add(panel_13, gbc_panel_13);
		
		String reviewer_3_username = "Not assigned yet.";
		if (num_reviews >= 3){
			reviewer_3_username = the_reviewer_usernames[2];
		}
		JLabel fieldReviewer3 = new JLabel(reviewer_3_username);
		fieldReviewer3.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReviewer3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReviewer3.setBounds(179, 0, 391, 20);
		panel_13.add(fieldReviewer3);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_14 = new GridBagConstraints();
		gbc_panel_14.insets = new Insets(0, 0, 5, 0);
		gbc_panel_14.fill = GridBagConstraints.BOTH;
		gbc_panel_14.gridx = 0;
		gbc_panel_14.gridy = 14;
		panel.add(panel_14, gbc_panel_14);
		
		JLabel lblReview3Submitted = new JLabel("Review Submitted?");
		lblReview3Submitted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReview3Submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReview3Submitted.setBounds(156, -1, 209, 20);
		panel_14.add(lblReview3Submitted);
		
		JLabel fieldReview3SubmittedStatus = new JLabel("<dynamic>");
		fieldReview3SubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReview3SubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReview3SubmittedStatus.setBounds(389, 0, 180, 20);
		panel_14.add(fieldReview3SubmittedStatus);
		
		JPanel panel_15 = new JPanel();
		panel_15.setLayout(null);
		panel_15.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_15 = new GridBagConstraints();
		gbc_panel_15.insets = new Insets(0, 0, 5, 0);
		gbc_panel_15.fill = GridBagConstraints.BOTH;
		gbc_panel_15.gridx = 0;
		gbc_panel_15.gridy = 15;
		panel.add(panel_15, gbc_panel_15);
		
		String reviewer_4_username = "Not assigned yet.";
		if (num_reviews >= 4){
			reviewer_4_username = the_reviewer_usernames[3];
		}
		JLabel fieldReviewer4 = new JLabel(reviewer_4_username);
		fieldReviewer4.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReviewer4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReviewer4.setBounds(179, 0, 391, 20);
		panel_15.add(fieldReviewer4);
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_16 = new GridBagConstraints();
		gbc_panel_16.insets = new Insets(0, 0, 5, 0);
		gbc_panel_16.fill = GridBagConstraints.BOTH;
		gbc_panel_16.gridx = 0;
		gbc_panel_16.gridy = 16;
		panel.add(panel_16, gbc_panel_16);
		
		JLabel lblReview4Submitted = new JLabel("Review Submitted?");
		lblReview4Submitted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReview4Submitted.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReview4Submitted.setBounds(156, -1, 209, 20);
		panel_16.add(lblReview4Submitted);
		
		JLabel fieldReview4SubmittedStatus = new JLabel("<dynamic>");
		fieldReview4SubmittedStatus.setHorizontalAlignment(SwingConstants.LEFT);
		fieldReview4SubmittedStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldReview4SubmittedStatus.setBounds(389, 0, 180, 20);
		panel_16.add(fieldReview4SubmittedStatus);

		return localTabManagement;
	}
	
	/**
	 * Getter for the ManagementPanel JPanel.
	 * 
	 * <dt><b>Preconditions: The ManagementPanel has already been instantiated.</b><dd>
	 * <dt><b>Postconditions: The ManagementPanel JPanel is returned.</b><dd>
	 * @return contentPane JPanel containing the ManagementPanel.
	 */
	public JComponent getGUI() {
		return tabManagement;
	}
}

