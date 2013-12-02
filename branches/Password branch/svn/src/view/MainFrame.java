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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import controller.Controller;

/**
 * The Main Frame to run the CMS software in.
 * 
 * @author Jacob Hall
 * @author warrick Holfeld
 * @version 112 Date: 11/28/13
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer {

	/*
	 * The current JPanal Component being used by the MainFrame JFrame
	 */
	private Component currentPanel;

	/*
	 * The Controller object that handles the business logic and acts as an
	 * interface between the GUI and the database.
	 */
	private static Controller controller;

	/**
	 * Creates and makes visible the MainFrame for the CMS software.
	 * 
	 * @param args
	 *            Command line arguments, ignored.
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final MainFrame frame = new MainFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Method creates a state machine that will allow discrete states to
	 * represent the current mode the GUI is in.
	 * 
	 * <dt><b>Preconditions: the controller has been instantiated</b>
	 * <dd>
	 * <dt><b>Postconditions: The currentPanel is set to the state that is
	 * current within the controller which maintains the context of what GUI
	 * should be displayed.</b>
	 * <dd>
	 */
	public void setFrame() {
		switch (controller.getStateOfGUI()) {
		case LOGIN:
			LoginGUI loginWindow = new LoginGUI(controller);
			this.remove(currentPanel);
			currentPanel = loginWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			loginWindow.setDefaultCurserPosition();
			break;
		case REGISTER:
			RegisterGUI registerWindow = new RegisterGUI(controller);
			this.remove(currentPanel);
			currentPanel = registerWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case HOME:
			HomeGUI homeWindow = new HomeGUI(controller);
			this.remove(currentPanel);
			currentPanel = homeWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case CONFERENCE:
			ConferenceGUI ConferenceWindow = new ConferenceGUI(controller);
			setTitle("Conferences");
			this.remove(currentPanel);
			currentPanel = ConferenceWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case NEW_CONFERENCE:
			NewConferenceGUI newConferenceWindow = new NewConferenceGUI(
					controller);
			setTitle("Create a New Conference");
			this.remove(currentPanel);
			currentPanel = newConferenceWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case MANAGE_PAPER:
			ManagePaperGUI managePaperWindow = new ManagePaperGUI(controller);
			this.remove(currentPanel);
			currentPanel = managePaperWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case SUBMIT_PAPER:
			SubmitPaperGUI submitPaperWindow = new SubmitPaperGUI(controller);
			this.remove(currentPanel);
			currentPanel = submitPaperWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case EDIT_SUBMISSION:
			EditSubmissionGUI editSubmissionWindow = new EditSubmissionGUI(
					controller);
			this.remove(currentPanel);
			currentPanel = editSubmissionWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case SUBMIT_REVIEW:
			SubmitReviewGUI submitReviewWindow = new SubmitReviewGUI(controller);
			this.remove(currentPanel);
			currentPanel = submitReviewWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
//			submitReviewWindow.setScrollPaneToTop();
			break;
		case SUBMIT_RECOMMENDATION:
			MakeRecommendationGUI submitRecommendationWindow = new MakeRecommendationGUI(
					controller);
			this.remove(currentPanel);
			currentPanel = submitRecommendationWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case ASSIGN_REVIEWER:
			AssignReviewerGUI assignReviewerWindow = new AssignReviewerGUI(
					controller);
			this.remove(currentPanel);
			currentPanel = assignReviewerWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case ASSIGN_SUB_PC:
			AssignSubPCGUI assignSubPCWindow = new AssignSubPCGUI(controller);
			this.remove(currentPanel);
			currentPanel = assignSubPCWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case ACCEPT_REJECT:
			AcceptRejectGUI acceptRejectWindow = new AcceptRejectGUI(controller);
			this.remove(currentPanel);
			currentPanel = acceptRejectWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		}
	}

	/**
	 * The update method for the Observer interface. Method updates the frame
	 * with the appropriate mode of operation the GUI should transition to.
	 * 
	 * <dt><b>Preconditions: the controller is instantiated as Observable</b>
	 * <dd>
	 * <dt><b>Postconditions: when update is called, the next frame will be
	 * updated with the new context.</b>
	 * <dd>
	 * 
	 * @param the_object
	 *            The Observable that called this method.
	 * @param the_arg
	 *            The argument it passed to this method.
	 */
	@Override
	public void update(final Observable the_object, final Object the_arg) {
		setFrame();
	}

	/**
	 * Create the frame.
	 * 
	 * <dt><b>Preconditions: none</b>
	 * <dd>
	 * <dt><b>Postconditions: a frame is created and the CMS software is
	 * initiated.</b>
	 * <dd>
	 */
	public MainFrame() {
		setUpFrame();
		controller.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	/**
	 * Method sets up the frame initially, instantiates the single Controller
	 * Object used by the CMS software and initializes the first JPanel viewable
	 * to be the login window.
	 * 
	 * <dt><b>Preconditions: none</b>
	 * <dd>
	 * <dt><b>Postconditions: Controller is instantiated, and the login window
	 * is loaded.</b>
	 * <dd>
	 */
	private void setUpFrame() {
		controller = new Controller();
		LoginGUI loginWindow = new LoginGUI(controller);
		this.setResizable(false);
		setTitle("Login");
		currentPanel = loginWindow.getGUI();
		add(currentPanel);
		pack();
		setVisible(true);
		loginWindow.setDefaultCurserPosition();
	}
}

/*
 * TODO:
 * 
 * controller.getReviews()
 * 
 */
