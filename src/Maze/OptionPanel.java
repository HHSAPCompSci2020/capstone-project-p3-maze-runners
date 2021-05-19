package Maze;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel implements ActionListener {

	Main w;

	
	public OptionPanel(Main w) {
		this.w = w;
		
		TitledBorder tb = new TitledBorder("Maze Of Legends");
		tb.setTitlePosition(TitledBorder.BELOW_TOP);
		tb.setTitleFont(new Font(Font.MONOSPACED, Font.ITALIC, 80));
		this.setBorder(tb);
		
		JLabel instructions = new JLabel("Instructions: Use arrow keys or WASD to move."); // Press '=' to enter Debug Mode
		instructions.setBounds(10, 120, 800, 100);
		instructions.setFont(new Font(Font.SERIF,Font.PLAIN, 35) );
		this.add(instructions);

		JButton button = new JButton("Start Game");
		button.setBounds(10, 230, 760,100);
		button.addActionListener(this);
		this.add(button);
		
		this.setLayout(null);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}

}