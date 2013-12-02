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
import view.GUIEnum.paperStatusAdminViewable;
import view.GUIEnum.paperStatusAuthorViewable;
import controller.Conference;
import controller.Controller;
import javax.swing.ListSelectionModel;

/**
* The Assign Sub-Program Chair Interface JPanel
* 
* Allows the Program Chair to assign a Sub-Program Chair to a paper in the CMS.
* @author Jacob Hall
* @version 90 Date: 11/27/13
*/
@SuppressWarnings("serial")
public class AssignSubPCGUI extends JPanel {
	
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
	 * The list of users that can be selected from to be considered
	 * for reviewers of the paper.
	 */
	private JList<String> listOfAvailableForSubPC;
	
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
	 * Create the JPanel that contains the AssignSubPCGUI.
	 * 
	 * <dt><b>Preconditions: The controller object has been instantiated.  The PC is the user logged in
	 * 						 in relation to the paper being evaluated.</b><dd>
	 * <dt><b>Postconditions: A JPanel is created to represent the most current data and status 
	 * 						 concerning a paper</b><dd>
	 * 
	 * @param the_controller
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AssignSubPCGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		current_conf = controller.getCurrentConference();
		username = controller.getCurrentUsername();
		paper_author = controller.getFullName(controller.getPaperAuthor(current_conf, current_paper));
		current_paper = controller.getCurrentPaper();
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
		
		JButton btnSubmitSubPCChoice = new JButton(my_submit_action);
		btnSubmitSubPCChoice.setToolTipText(SUBMIT_REVIEW_STRING);
		btnSubmitSubPCChoice.setText(SUBMIT_REVIEW_TITLE_STRING);
		btnSubmitSubPCChoice.setBounds(417, 467, 159, 29);
		panel_1.add(btnSubmitSubPCChoice);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 46, 799, 20);
		panel_1.add(separator_2);
		
		JLabel lblAssignSubPC = new JLabel("Assign A Sub-Program Chair to the Paper:");
		lblAssignSubPC.setHorizontalAlignment(SwingConstants.CENTER);
		lblAssignSubPC.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAssignSubPC.setBounds(20, 15, 791, 20);
		panel_1.add(lblAssignSubPC);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(217, 70, 582, 374);
		panel_1.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {580};
		gbl_panel.rowHeights = new int[] {30, 30, 30, 30, 30, 20, 30, 150};
		gbl_panel.columnWeights = new double[]{1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0};
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
		
		JLabel fieldPCName = new JLabel(controller.getFullName(username));
		fieldPCName.setHorizontalAlignment(SwingConstants.LEFT);
		fieldPCName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldPCName.setBounds(179, 0, 391, 20);
		panel_2.add(fieldPCName);
		
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
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 5;
		panel.add(separator_3, gbc_separator_3);
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		
		JLabel lblSubPC = new JLabel("Select Sub-Program Chair:");
		GridBagConstraints gbc_lblSubPC = new GridBagConstraints();
		gbc_lblSubPC.insets = new Insets(0, 0, 5, 0);
		gbc_lblSubPC.gridx = 0;
		gbc_lblSubPC.gridy = 6;
		panel.add(lblSubPC, gbc_lblSubPC);
		lblSubPC.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubPC.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		listOfAvailableForSubPC = new JList();
		listOfAvailableForSubPC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOfAvailableForSubPC.setModel(new AbstractListModel() {
			int paperId = controller.getPaperID(current_paper);
			String[] values = controller.getAvailableForSubPC(current_conf, paperId, username);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		GridBagConstraints gbc_listOfAvailableForSubPC = new GridBagConstraints();
		gbc_listOfAvailableForSubPC.gridheight = 4;
		gbc_listOfAvailableForSubPC.fill = GridBagConstraints.VERTICAL;
		gbc_listOfAvailableForSubPC.gridx = 0;
		gbc_listOfAvailableForSubPC.gridy = 7;
		panel.add(listOfAvailableForSubPC, gbc_listOfAvailableForSubPC);
	}
	
	/**
	 * Getter for the AssignSubPCGUI JPanel.
	 * 
	 * <dt><b>Preconditions: The AssignSubPCGUI has already been instantiated.</b><dd>
	 * <dt><b>Postconditions: The AssignSubPCGUI JPanel is returned.</b><dd>
	 * @return contentPane JPanel containing the AssignSubPCGUI.
	 */
	public JComponent getGUI() {
		return contentPane;
	}
	
	/**
	 * Set up the actions to associate events with outside logic
	 * 
	 * <dt><b>Preconditions: The AssignSubPCGUI is instantiated.</b><dd>
	 * <dt><b>Postconditions: actions associated with each button will be returned.</b><dd>
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
				controller.setCurrentConference(null);
				controller.setCurrentPaper("");
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
				List<String> result = listOfAvailableForSubPC.getSelectedValuesList();		//the single result of what the user selected
				if (result.isEmpty()){
					JOptionPane.showMessageDialog(contentPane, "No Sub-Program Chair selected.  Make a selection or return to " +
							"previous screen");
				} else {
					String temp = result.get(0);
					controller.setPaperStatus(current_conf, current_paper, paperStatusAuthorViewable.UNDER_REVIEW, 
							paperStatusAdminViewable.SUBMITTED);
					controller.addSubPC(current_conf, current_paper, temp);
					controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
				}			
			}
		};
		my_submit_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_REVIEW_STRING);
		my_submit_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
	}
}

