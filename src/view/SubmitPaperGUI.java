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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import view.GUIEnum.StateOfGUI;
import view.GUIEnum.paperStatusAdminViewable;
import view.GUIEnum.paperStatusAuthorViewable;
import controller.Conference;
import controller.Controller;

/**
* The Submit Paper Interface JPanel
* 
* Allows the user to submit a paper in the CMS.
* @author Jacob Hall
* @version 11/13/13
*/

@SuppressWarnings("serial")
public class SubmitPaperGUI extends JPanel {
	
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
	private static final String MAIN_STRING = "Return to Main user interface (ALT+M)";
	
	/*
	 * The text to display on the main button.
	 */
	private static final String MAIN_TITLE_STRING = "Main";
	
	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String SUBMIT_STRING = "Submit the new conference (ALT+S)";
	
	/*
	 * The text to display on the create new conference button.
	 */
	private static final String SUBMIT_TITLE_STRING = "Submit";
	
	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String BACK_STRING = "Navigate to the last screen (ALT+B)";
	
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
	 * the Action associated with the logout button
	 */
	private Action my_logout_action;
	
	/*
	 * the Action associated with the back button
	 */
	private Action my_back_action;
	
	/*
	 * the Action associated with the Main button
	 */
	private Action my_main_action;
	
	/*
	 * the Action associated with the Submit button
	 */
	private Action my_submit_action;
	
	/*
	 * The JTextField containing the conference title entry.
	 */
	private JTextField fieldPaperTitle;
	
	/*
	 * The JTextField containing the conference date entry.
	 */
	private JTextField fieldFileToSubmit;
	/**
	 * Create the JPanel.
	 */
	public SubmitPaperGUI(final Controller the_controller) {
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
		
		JButton btnIcon = new JButton("");
		btnIcon.setForeground(BACKGROUND_COLOR);
		btnIcon.setBorder(null);
		btnIcon.setIcon(ICON);
		btnIcon.setBounds(430, 11, 404, 116);
		contentPane.add(btnIcon);		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 127, 1250, 12);
		contentPane.add(separator);
		
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
		
		fieldPaperTitle = new JTextField();
		fieldPaperTitle.setBounds(401, 175, 418, 20);
		panel_1.add(fieldPaperTitle);
		fieldPaperTitle.setColumns(10);
		
		fieldFileToSubmit = new JTextField();
		fieldFileToSubmit.setBounds(401, 210, 418, 20);
		panel_1.add(fieldFileToSubmit);
		fieldFileToSubmit.setColumns(10);
		
		JButton btnSubmit = new JButton(my_submit_action);
		btnSubmit.setBounds(409, 255, 159, 29);
		panel_1.add(btnSubmit);
		
		JLabel lblDenotesRequired = new JLabel("* Required Field");
		lblDenotesRequired.setHorizontalAlignment(SwingConstants.LEFT);
		lblDenotesRequired.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDenotesRequired.setBounds(663, 255, 120, 20);
		panel_1.add(lblDenotesRequired);
		
		JLabel lblPaperTitle = new JLabel("* Paper Title:");
		lblPaperTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaperTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPaperTitle.setBounds(74, 175, 297, 20);
		panel_1.add(lblPaperTitle);
		
		JLabel lblFileTo = new JLabel("* File to Submit:");
		lblFileTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFileTo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFileTo.setBounds(74, 210, 297, 20);
		panel_1.add(lblFileTo);
		
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
		
		JLabel lblSubmitANew = new JLabel("Submit a New Paper to the Conference");
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
		 * The action associated with clicking the Submit button.
		 */
		my_submit_action = new AbstractAction(SUBMIT_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				String conf_title = fieldPaperTitle.getText();
				String conf_date = fieldFileToSubmit.getText();
				if (conf_title.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a conference title.");
				}
				else if (conf_date.equals("")){
					JOptionPane.showMessageDialog(contentPane, "Please enter a conference date.");
				}
				else{
					//TODO: maybe once the conference is created, a new screen should be populated with the fields entered
					//		with a "success your paper was submitted" message?
					
					//TODO: change this from a text field to a file navigation option to actually identify the file.
					//		right now, for speed of development, it's just a text field.

					String paper_title = fieldPaperTitle.getText();
					String fileSubmited = fieldFileToSubmit.getText();
					if (paper_title.equals("")){
						JOptionPane.showMessageDialog(contentPane, "Please enter a paper title.");
					}
					else if (fileSubmited.equals("")){
						JOptionPane.showMessageDialog(contentPane, "Please enter a file name.");
					}
					else {
						try{
							controller.createNewPaper(current_conf, username, paper_title, fileSubmited, 
									paperStatusAuthorViewable.SUBMITTED, paperStatusAdminViewable.SUBMITTED);
						}
						catch (Exception e){
							JOptionPane.showMessageDialog(contentPane, e);
						}
						controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
					}
				}
				
			}
		};
		my_submit_action.putValue(Action.SHORT_DESCRIPTION, SUBMIT_STRING);
		my_submit_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
	}
}

