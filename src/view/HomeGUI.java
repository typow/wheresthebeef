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
	private static final String NEW_CONF_STRING = "Create new conference in the CMS (ALT+N)";
	
	/*
	 * The text to display on the create new conference button.
	 */
	private static final String NEW_CONF_TITLE_STRING = "Create New Conference";
	
	/*
	 * The message the will pop up when the user floats above the button.
	 */
	private static final String BACK_STRING = "Navigate to the last screen (ALT+B)";
	
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

	/**
	 * Create the JPanel.
	 */
	public HomeGUI(final Controller the_controller) {
		super();
		controller = the_controller;
		setupActions();
		setBounds(100, 100, 722, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(153, 66, 14, 366);
		contentPane.add(separator_1);
		
		JLabel lblUsername = new JLabel("My Conferences");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(177, 90, 143, 20);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Conference Management System (CMS)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(177, 11, 399, 26);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 48, 682, 2);
		contentPane.add(separator);
		
		JButton button = new JButton(my_logout_action);
		button.setBounds(10, 90, 137, 22);
		contentPane.add(button);
		
		JButton button_1 = new JButton(my_back_action);
		button_1.setBounds(10, 118, 137, 22);
		contentPane.add(button_1);
		JButton button_2 = new JButton(my_new_conf_action);
		button_2.setBounds(10, 146, 137, 22);
		contentPane.add(button_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(177, 115, 514, 125);
		contentPane.add(scrollPane);
		
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
		
		//JLabel label = new JLabel(controller.getCurrentUsername().toString());
		//Windows Builder Pro edits out the above line when you use it.  Replace the label
		//declaration when needed because it inserts <dynamic> into the label text 
		JLabel label = new JLabel(controller.getCurrentUsername().toString());
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(10, 66, 133, 20);
		contentPane.add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(177, 307, 514, 125);
		contentPane.add(scrollPane_1);
		
		JLabel lblUpcommingConferences = new JLabel("Upcomming Conferences");
		lblUpcommingConferences.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUpcommingConferences.setBounds(177, 277, 213, 20);
		contentPane.add(lblUpcommingConferences);
		contentPane.setPreferredSize(new Dimension(800, 500));
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
	}
}

