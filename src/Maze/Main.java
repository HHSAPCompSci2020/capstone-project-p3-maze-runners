package Maze;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

	private JPanel cardPanel;

	private OptionPanel panel1;
	private DrawingSurface panel2;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Main(String title) {
		super(title);
		setBounds(100, 100, 800, 600);
//		setBounds(0, 0, screenSize.width, screenSize.height);
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

	public static void main(String[] args) {
		Main w = new Main("Maze Of Legends");
	}

	public void changePanel() {
		((CardLayout) cardPanel.getLayout()).next(cardPanel);
		panel2.requestFocus();
	}

}