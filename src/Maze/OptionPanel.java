package Maze;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel implements ActionListener {

	Main w;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public OptionPanel(Main w) {
		this.w = w;
		JButton button = new JButton("Start Game");
		button.setBounds(200, 175, 100,75);
		button.addActionListener(this);
		this.add(button);
		
		
		JLabel instructions = new JLabel("Use arrow keys or WASD to move."); // Press '=' to enter Debug Mode
		instructions.setBounds(200, 50, 200, 200);
		this.add(instructions);

		
		this.setSize(400, 480);
		this.setLayout(null);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}

}