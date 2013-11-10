/**
 * Run this.
 * 
 * Note: this is very early in development.  I'm working on integrating the switching
 * of JPanels in this frame.  This is the main class to run at startup.
 * 
 * Jacob Hall
 * 11/10/13
 */

package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import View.GUIEnum.StateOfGUI;
import View.LoginGUI;

public class MainFrame extends JFrame implements Observer {

	private static LoginGUI loginWindow;
	
	private static RegisterGUI registerWindow;
	
	private Component currentPanel;

//	private static StateOfGUI state = StateOfGUI.REGISTER;
	
	private static Controller controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final MainFrame frame = new MainFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
//					controller = new Controller();
//					controller.setStateOfGUI(StateOfGUI.LOGIN);
//					loginWindow = new LoginGUI(controller);
//					registerWindow = new RegisterGUI(controller);
//					frame = new MainFrame();
//					controller.addObserver(this);
//					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					frame.setLayout(new BorderLayout());
//					setFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Method creates a state machine that will allow discrete states to
	 * represent the current mode the GUI is in.
	 */
	public void setFrame() {
		switch (controller.getStateOfGUI()) {
		case LOGIN:
			setTitle("Login");
			currentPanel = loginWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case REGISTER:
			setTitle("Register");
			this.remove(currentPanel);
			currentPanel = registerWindow.getGUI();
			add(currentPanel);
			pack();
			setVisible(true);
			break;
		case MAIN_USER_INTERFACE:

			break;
		}
	}

	/**
	 * The update method for the Observer interface. Method updates the frame
	 * with the appropriate mode of operation the GUI should transition to.
	 * 
	 * @param the_object
	 *            The Observable that called this method.
	 * @param the_arg
	 *            The argument it passed to this method.
	 */
	@Override
	public void update(final Observable the_object, final Object the_arg) {
		System.out.println("reached....");
		setFrame();
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		controller = new Controller();
		controller.setStateOfGUI(StateOfGUI.LOGIN);
		loginWindow = new LoginGUI(controller);
		registerWindow = new RegisterGUI(controller);
		controller.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setFrame();
	}
}
