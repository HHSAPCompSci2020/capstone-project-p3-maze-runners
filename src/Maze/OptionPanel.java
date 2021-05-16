package Maze;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class OptionPanel extends JPanel implements ActionListener {
	
	Main w;
	
	public OptionPanel(Main w) {
		this.w = w;
		
		

		
		JButton button = new JButton("Start Game");
		JLabel instructions = new JLabel("Use arrow keys or WASD to move. Press 'm' to toggle which Maze is on screen");
		
		button.addActionListener(this);
		
		this.add(instructions);

		add(button);

		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}
	
}