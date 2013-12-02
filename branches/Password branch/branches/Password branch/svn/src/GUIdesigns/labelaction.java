package GUIdesigns;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class labelaction extends JFrame {

public static void main(String[] args) {
labelaction frame = new labelaction();
}

JLabel click = new JLabel("Click Here");
JLabel alert = new JLabel("You Have Clicked A Label");
JPanel panel = new JPanel();
JTextField txuser = new JTextField(15);
JPasswordField pass = new JPasswordField(15);

labelaction(){
super("Login Autentification");
setSize(300,200);
setLocation(500,280);
panel.setLayout (null); 


click.setBounds(110,50,80,20);
click.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
alert.setBounds(70,100,180,20);
panel.add(click);
panel.add(alert);
alert.setVisible(false);
getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
mouseactionlabel();
}

void mouseactionlabel(){
click.addMouseListener(new MouseListener()
{
public void mouseClicked(MouseEvent arg0) {
alert.setVisible(true);
}
public void mouseEntered(MouseEvent arg0) {
}
public void mouseExited(MouseEvent arg0) {
}
public void mousePressed(MouseEvent arg0) {
}
public void mouseReleased(MouseEvent arg0) {
}
});
}
}