/*
 * RUN THIS!
 * 
 * Note: this is very early in development.  I'm working on integrating the switching
 * of JPanels in this frame.  This is the main class to run at startup.
 * 
 * Jacob Hall
 * 11/10/13
 */

package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;

import View.LoginGUI;

@SuppressWarnings("serial")
public class MainFrame{

	private static LoginGUI loginWindow = new LoginGUI();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new BorderLayout());
					frame.setTitle("Home");
//					frame.setSize(100, 520);
					frame.add(loginWindow.getGUI());
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {


	}
}
