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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import view.GUIEnum.StateOfGUI;
import controller.Controller;

/**
 * The Main User Interface JPanel
 * 
 * Allows the user to create new conferences, see conferences they're associated with,
 * and see all other upcoming conferences.
 * @author Jacob Hall
 * @version 11/13/13
 */
@SuppressWarnings("serial")
public class HomeGUI extends JPanel {
	
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
	 * The text to display on the create new conference button.
	 */
	private static final String NEW_CONF_TITLE_STRING = "Create New Conference";

	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String NEW_CONF_STRING = "Create new conference in the CMS (ALT+N)";
	
	private static final String TEMP_TITLE_STRING = "temp: one of many btns in the table above linked to a specific paper and conf, user has a relation";

	private static final String TEMP_STRING = "This button is temporary.  " +
			"Eventually, it will be one of many buttons listed in the table above, " +
			"each linking the user to the paper management GUI.  Remove this later " +
			"once those buttons are implemented.";
	
	private static final String TEMP_TITLE_STRING_TWO = "temp: on of many btns in table above, no paper in focus, user has no realtion to the conf yet";

	private static final String TEMP_STRING_TWO = "This button is temporary.  " +
			"Eventually, it will be one of many buttons listed in the table above, " +
			"each linking the user to the paper management GUI.  Remove this later " +
			"once those buttons are implemented.";
	
	private JPanel contentPane;
	private JTable table;
	
	/*
	 * The CMS controller
	 */
	private Controller controller;
	
	/*
	 * the Action associated with the logout button
	 */
	private Action my_logout_action;
	
	/*
	 * the Action associated with the back button
	 */
	private Action my_back_action;
	
	/*
	 * the Action associated with the new conference button
	 */
	private Action my_new_conf_action;
	
	private Action my_temp_paper_selected_action;
	
	private Action my_temp_conf_selected_action;

	/**
	 * Create the JPanel.
	 */
	public HomeGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setupActions();
		setBounds(100, 100, 722, 520);
		contentPane = new JPanel();
		contentPane.setBackground(BACKGROUND_COLOR);
		contentPane.setPreferredSize(WIN_DIMENSION);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Conference Management System (CMS)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 11, 1260, 26);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 48, 1260, 2);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(INNER_BACKGROUND_COLOR);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(283, 109, 755, 442);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//JLabel label = new JLabel(controller.getCurrentUsername().toString());
		//Windows Builder Pro edits out the above line when you use it.  Replace the label
		//declaration when needed because it inserts <dynamic> into the label text 
		JLabel label = new JLabel(controller.getCurrentUsername().toString());
		label.setBounds(10, 39, 150, 20);
		panel.add(label);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton button = new JButton(my_logout_action);
		button.setBounds(20, 70, 150, 22);
		panel.add(button);
		
		JButton button_1 = new JButton(my_back_action);
		button_1.setBounds(20, 103, 152, 22);
		panel.add(button_1);
		JButton button_2 = new JButton(my_new_conf_action);
		button_2.setBounds(22, 136, 150, 22);
		panel.add(button_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(182, 43, 2, 352);
		panel.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblUsername = new JLabel("My Conferences");
		lblUsername.setBounds(204, 40, 143, 20);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(204, 71, 514, 125);
		panel.add(scrollPane);
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Conference", "   Author", "    Paper", "     Date", "   Status", "    Yada....", "    Yada...."
			}
		));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(table);
		
		JLabel lblUpcommingConferences = new JLabel("Upcomming Conferences");
		lblUpcommingConferences.setBounds(204, 239, 213, 20);
		panel.add(lblUpcommingConferences);
		lblUpcommingConferences.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(204, 270, 514, 125);
		panel.add(scrollPane_1);
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnTempPaperRelation = new JButton(my_temp_paper_selected_action);
		btnTempPaperRelation.setText(TEMP_TITLE_STRING);
		btnTempPaperRelation.setBounds(204, 207, 514, 23);
		panel.add(btnTempPaperRelation);
		
		JButton btnTempGeneralConf = new JButton(my_temp_conf_selected_action);
		btnTempGeneralConf.setText(TEMP_TITLE_STRING_TWO);
		btnTempGeneralConf.setBounds(204, 408, 514, 23);
		panel.add(btnTempGeneralConf);
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
				controller.setStateOfGUI(StateOfGUI.LOGIN);
			}
		};
		my_logout_action.putValue(Action.SHORT_DESCRIPTION, LOGOUT_STRING);
		my_logout_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
		
		/*
		 * The action associated with clicking back, in this case, the same as logging out.
		 * In the future, the back button will just take you back to the previous screen,
		 * but for the sake of continuity and flow, I included the back button here as well
		 */
		my_back_action = new AbstractAction(BACK_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				controller.setCurrentUsername("");  //blank because in this case, 
					//the user is logging out by going back one screen.
				controller.setCurrentConference(null);
				controller.setStateOfGUI(StateOfGUI.LOGIN);
			}
		};
		my_back_action.putValue(Action.SHORT_DESCRIPTION, BACK_STRING);
		my_back_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_B);
		
		/*
		 * The action associated with clicking the Create New Conference button.
		 */
		my_new_conf_action = new AbstractAction(NEW_CONF_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				System.out.println("Creating new conference");
				controller.setStateOfGUI(StateOfGUI.NEW_CONFERENCE);
			}
		};
		my_new_conf_action.putValue(Action.SHORT_DESCRIPTION, NEW_CONF_STRING);
		my_new_conf_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		
		/*
		 * The action associated with clicking the button that is associated with a paper and
		 * conference.  This will eventually be a table of buttons each linked to a paper and conference.
		 */
		my_temp_paper_selected_action = new AbstractAction(TEMP_TITLE_STRING, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: the button needs to be associated with a paper and conference title.  When
				//		the user selects the button, need to set the paper title in the controller
				//		so that it knows what paper is in focus if any.
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
			}
		};
		my_temp_paper_selected_action.putValue(Action.SHORT_DESCRIPTION, TEMP_STRING);
		my_temp_paper_selected_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		
		/*
		 * The action associated with clicking the button that is just a link to a conference.
		 * There is no paper in focus yet.  This is one of many buttons in the table each linking
		 * to a conference, but the user has no relation to the conference yet.
		 */
		my_temp_conf_selected_action = new AbstractAction(TEMP_TITLE_STRING_TWO, null)
		{
			@Override
			public void actionPerformed(ActionEvent the_event) {
				//TODO: the button needs to be associated with a paper and conference title.  When
				//		the user selects the button, need to set the paper title in the controller
				//		so that it knows what paper is in focus if any.
				
				//TODO: this button also needs to set the current_conf in the controller so that
				//		it knows what conference is in focus.  The next GUI can then retrieve
				//		the relevant data about that conference.  As of right now, because the table
				//		isn't set up, we don't know what conference to set as the one in focus
				//		However, since the user won't have a relation to any of these conferences,
				//		meaning they aren't an Author, PC, Subpc,....., there is no paper in focus,
				//		so set it to "". Blank.
				
//				controller.setCurrentConference(the_conference_name);
				controller.setCurrentPaper("");
				controller.setStateOfGUI(StateOfGUI.MANAGE_PAPER);
			}
		};
		my_temp_conf_selected_action.putValue(Action.SHORT_DESCRIPTION, TEMP_STRING_TWO);
		my_temp_conf_selected_action.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_T);
	}
}

