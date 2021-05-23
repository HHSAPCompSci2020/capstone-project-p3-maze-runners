package Maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 
 * The first screen that shows up at launch of the app
 * 
 * @author John_Shelby, modified by Lakshya Shrivastava
 *
 */
@SuppressWarnings("serial")
public class OptionPanel extends JPanel implements ActionListener {

	Main w;
	private BufferedImage image;

	/**
	 * Creates a main menu panel for this application
	 * 
	 * @param w the main object that this panel is a part of
	 */
	public OptionPanel(Main w) {
		this.w = w;

		try {
			image = ImageIO.read(new File("data//MainMenuImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		TitledBorder tb = new TitledBorder("Maze Of Legends");
		tb.setTitlePosition(TitledBorder.CENTER);
		tb.setTitleJustification(TitledBorder.CENTER);
		tb.setTitleColor(Color.WHITE);
		tb.setTitleFont(new Font(Font.MONOSPACED, Font.ITALIC, 80));
		this.setBorder(tb);

		JLabel instructions = new JLabel("Instructions: Use arrow keys or WASD to move."); // Press '=' to enter Debug
																							// Mode
		instructions.setBounds(w.getWidth() / 2 - 350, 120, 800, 100);
		instructions.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		instructions.setForeground(Color.WHITE);
		this.add(instructions);

		JLabel instructions2 = new JLabel("Use spacebar to use a picked up ability"); // Press '=' to enter Debug Mode
		instructions2.setBounds(w.getWidth() / 2 - 320, 150, 800, 100);
		instructions2.setFont(new Font(Font.SERIF, Font.PLAIN, 35));
		instructions2.setForeground(Color.WHITE);
		this.add(instructions2);

		JButton button = new JButton("Click To Start Game");
		button.setBounds(w.getWidth() / 2 - 390, w.getHeight() / 2 - 50, 760, 100);
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

	/**
	 * When space is pressed, moves on to next panel
	 */
	public void actionPerformed(ActionEvent e) {
		w.changePanel();
		if (DrawingSurface.startingIterations == -1) {
			DrawingSurface.startingIterations = DrawingSurface.getIterations();
		}
	}

	/**
	 * Paints the screen with the background image
	 * 
	 * @param g a Graphics object to which the image is drawn
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = g2.getTransform();
		float ratioX = (float) getWidth() / 800;
		float ratioY = (float) getHeight() / 600;

		g2.scale(ratioX, ratioY);
		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters

		g2.setTransform(at);
	}
}