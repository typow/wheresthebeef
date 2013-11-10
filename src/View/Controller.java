package View;

import java.util.Observable;

import javax.swing.JFrame;

import View.GUIEnum.StateOfGUI;

public class Controller extends Observable{

	private StateOfGUI state = StateOfGUI.LOGIN;
	
	/**
	 * @param args
	 */
	public void setStateOfGUI(StateOfGUI the_state){
		state = the_state;
		//TODO remove this println
		System.out.println("setting state to: " + the_state);
		
		setChanged();
		notifyObservers();
	}
	
	public StateOfGUI getStateOfGUI(){
		//TODO remove this println
		System.out.println("Getting state: " + state);
		return state;
	}


}
