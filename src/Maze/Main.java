package Maze;

import javax.swing.*;
import java.awt.*;

/*
 * Note to Shelby:
You can press '-' and '=' at the same time or 'O' and 'P' to access Debug mode. 
Debug mode lets you skip through the game but is not intended for normal play, which is why the controls are not shown in game. 


In Debug mode, you can skip levels, increase health and levels, hold shift to walk very fast, and press 'W', 'I', and 'N' simultaneously to have the game complete screen appear.
If any debug tool is used, the time at the end will be red and say that debug cheats were used to achieve that time. 
 */

/**
 * This is where the program starts executing
 * 
 * @author Shelby
 *
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

	private JPanel cardPanel;

	private OptionPanel panel1;
	private DrawingSurface panel2;

	/**
	 * Constructs a main object with the user provided title
	 * 
	 * @param title name of the application
	 */
	public Main(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);
		panel1 = new OptionPanel(this);
		panel2 = new DrawingSurface();
		panel2.init();

		cardPanel.add(panel1, "1");
		cardPanel.add(panel2, "2");

		add(cardPanel);

		setVisible(true);

	}

	/**
	 * Main method of the Application
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Main w = new Main("Maze Of Legends");
	}

	/**
	 * Changes the visible panel from the main menu to
	 */
	public void changePanel() {
		((CardLayout) cardPanel.getLayout()).next(cardPanel);
		panel2.requestFocus();
	}

}