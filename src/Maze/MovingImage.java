package Maze;

import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a moving image.
 *
 * by: Shelby
 * on: 5/3/13
 */

@SuppressWarnings("serial")
public class MovingImage extends Rectangle2D.Double {

	// FIELDS
	/**
	 * The image to be drawn
	 */
	protected PImage image;
	/**
	 * 	Whether the image should be facing right or not
	 */
	protected boolean facingRight;

	// CONSTRUCTORS
	public MovingImage(PImage img, int x, int y, int w, int h) {
		super(x, y, w, h);
		image = img;
		facingRight = true;
	}

	// METHODS
	/**
	 * moves the image to a new location
	 * @param x new x coordinate of location
	 * @param y new y coordiante of location
	 */
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	/**
	 * moves image by a certain amount
	 * @param x how much image moves in x direction
	 * @param y how much image moves in y direction
	 */
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}

	/**
	 * set the x and y to the min and max dimensions of the window size
	 * @param windowWidth the width of the window
	 * @param windowHeight the height of the window
	 */
	public void applyWindowLimits(int windowWidth, int windowHeight) { 
		x = Math.min(x, windowWidth - width);
		y = Math.min(y, windowHeight - height);
		x = Math.max(0, x);
		y = Math.max(0, y);
	}

	/**
	 *  draws the image
	 * @param g PApplet object that allows us to draw onto the surface
	 */
	public void draw(PApplet g) {
		g.image(image, (int) x, (int) y, (int) width, (int) height);
	}
	
	/**
	 * sets the direction to either true or false
	 * @param faceRight true if image is facing right, false if not
	 */
	public void setDirection(boolean faceRight) {
		facingRight = faceRight;
	}
}