package entities.abilities;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * Play the game to figure out what this is :)
 */
@SuppressWarnings("serial")
public class InvincibilityPrank extends Ability {

	/**
	 * Constructs this Ability at position x,y with the given image and user
	 * provided dimensions
	 * 
	 * @param img    the PImage that is drawn for this Ability
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Ability
	 * @param height the height of this Ability
	 */
	public InvincibilityPrank(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses = 1;
	}

	/**
	 * increments usage
	 */
	public void use(PApplet marker) {
		super.use();
	}

	/**
	 * Returns a String representation of this Ability
	 * 
	 * @return String representation of this Ability
	 */
	public String toString() {
		String s = "Instant Win :)";
		return s;
	}

	/**
	 * Draws this question mark image of this powerup
	 * 
	 * @author Christopher Lew
	 * @param marker the PApplet object used for drawing
	 */
	public void draw(PApplet marker) {
		marker.pushStyle();
		marker.fill(200, 200, 200, 160);
		marker.ellipse((float) this.getCenterX(), (float) this.getCenterY(), (float) width * 0.75f,
				(float) height * 0.75f);

		marker.fill(0, 0, 0);
		marker.noStroke();
		marker.textAlign(PConstants.CENTER);
		marker.textSize(20);
		marker.text('?', (float) (x + width / 2), (float) (y + height / 2 + 5));
		marker.popStyle();
	}
}