package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;


public class PapersHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PapersHome frame = new PapersHome();
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
	public PapersHome() {
		setTitle("Submit Paper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWheresTheBeef = new JLabel("Wheres the Beef");
		lblWheresTheBeef.setBounds(196, 31, 147, 20);
		contentPane.add(lblWheresTheBeef);
		
		JButton btnPastPapers = new JButton("Past Papers");
		btnPastPapers.setBounds(28, 66, 115, 29);
		contentPane.add(btnPastPapers);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(28, 105, 115, 29);
		contentPane.add(btnLogOut);
		
		JList list = new JList();
		
		list.setBounds(75, 217, 426, 177);
		contentPane.add(list);
		
		JLabel lblSubmitPaper = new JLabel("Submit Paper");
		lblSubmitPaper.setBounds(243, 192, 115, 20);
		contentPane.add(lblSubmitPaper);
	}
}
