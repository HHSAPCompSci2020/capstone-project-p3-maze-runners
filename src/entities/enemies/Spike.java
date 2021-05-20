package entities.enemies;

import entities.Player;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Lakshya Shrivastava A timing trap will be spikes that appear and
 *         disappear every few seconds. If the player touches the timing trap,
 *         their health will go down by 1
 */
@SuppressWarnings("serial")
public class Spike extends Enemy {
	private boolean isVisible;

	/**
	 * Constructs this Spike at position x,y with the given image and default
	 * dimensions
	 * 
	 * @param img the PImage that is drawn for this Creature
	 * @param x   the top left x coordinate
	 * @param y   the top left x coordinate
	 */

	public Spike(PImage img, int x, int y) {
		super(img, x, y);
		isVisible = true;
		attackDamage = 1;
		canDie = false;
	}

	/**
	 * Constructs this Spike at position x,y with the given image and user provided
	 * dimensions
	 * 
	 * @param img    the PImage that is drawn for this Creature
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Creature
	 * @param height the height of this Creature
	 */
	public Spike(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		isVisible = true;

	}

	/**
	 * Method is used for attacking a player
	 * 
	 * @param p the player to attack
	 */
	public void attack(Player p) {
		if (isVisible) {
			p.reduceHealthBy(1, x, y, width, height);
		}
	}

	/**
	 * This method does nothing because the spikes cannot be destroyed.
	 */
	public void reduceHealth() {
	}

	/**
	 * Returns a string representation of the Spike
	 * 
	 * @return the string representation of the Spike
	 */
	public String toString() {
		String s = "Spike at x=" + x + "y=" + y;
		return s;
	}

	/**
	 * the method that draws the TimingTrap to the provided PApplet
	 * 
	 * @param marker the PApplet to draw on
	 */
	public void draw(PApplet marker) {
		super.draw(marker);

	}

	/**
	 * Returns whether this enemy is moveable or not. Must by implemented by all
	 * subclasses
	 * 
	 * @return true if this enemy is moveable, false otherwise
	 */
	public boolean isMovable() {
		return false;
	}

	/**
	 * Returns whether this enemy can die
	 * 
	 * @return true if enemy can die, false otherwise
	 */
	public boolean canDie() {
		return false;
	}

}