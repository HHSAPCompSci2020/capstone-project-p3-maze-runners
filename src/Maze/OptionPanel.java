package Maze;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel implements ActionListener {

	Main w;

	public OptionPanel(Main w) {
		this.w = w;

		JButton button = new JButton("Start Game");
		JLabel instructions = new JLabel("Use arrow keys or WASD to move. Press '=' to enter Debug Mode");

		button.addActionListener(this);

		this.add(instructions);

		add(button);

	}

	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}

}