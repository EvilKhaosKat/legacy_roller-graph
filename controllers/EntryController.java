/**
 * 
 */
package controllers;

import models.MainContainer;
import views.MainFrame;

/**
 * @author LAP
 *
 */
public class EntryController {
	private static MainFrame test;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test = new MainFrame("Roller graph. Режим реального времени.");
		test.setVisible(true);
		MainContainer.setMainFrame(test);
	}

}
