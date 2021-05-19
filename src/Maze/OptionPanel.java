package Maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel implements ActionListener {

	Main w;
	private BufferedImage image;

	public OptionPanel(Main w) {
		this.w = w;
		
		try {
			image = ImageIO.read(new File("data//MainMenuImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		TitledBorder tb = new TitledBorder("Maze Of Legends");
		tb.setTitlePosition(TitledBorder.BELOW_TOP);
		tb.setTitleColor(Color.WHITE);
		tb.setTitleFont(new Font(Font.MONOSPACED, Font.ITALIC, 80));
		this.setBorder(tb);

		JLabel instructions = new JLabel("Instructions: Use arrow keys or WASD to move."); // Press '=' to enter Debug Mode
		instructions.setBounds(10, 120, 800, 100);
		instructions.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		instructions.setForeground(Color.WHITE);
		this.add(instructions);

		JButton button = new JButton("Click To Start Game");
		button.setBounds(10, 230, 760, 100);
		button.addActionListener(this);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(button);

		this.setLayout(null);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
}