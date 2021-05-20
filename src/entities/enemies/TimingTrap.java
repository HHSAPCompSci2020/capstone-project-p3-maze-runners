package entities.enemies;

import Maze.DrawingSurface;
import entities.Player;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * A timing trap will be spikes that appear and disappear every few seconds. If
 * the player touches the timing trap, their health will go down
 * 
 * @author Lakshya Shrivastava
 */
@SuppressWarnings("serial")
public class TimingTrap extends Spike {
	private boolean isVisible;
//	private Player pl;

	/**
	 * Constructs this TimingTrap at position x,y with the given image and default
	 * dimensions
	 * 
	 * @param img the PImage that is drawn for this Creature
	 * @param x   the top left x coordinate
	 * @param y   the top left x coordinate
	 */
	public TimingTrap(PImage img, int x, int y) {
		super(img, x, y);
		isVisible = true;
		attackDamage = 1;
		canDie = false;
	}

	/**
	 * Constructs this TimingTrap at position x,y with the given image and user
	 * provided dimensions
	 * 
	 * @param img    the PImage that is drawn for this Creature
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Creature
	 * @param height the height of this Creature
	 */
	public TimingTrap(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		isVisible = true;

	}

	/**
	 * Attacks player p and knocks them back
	 * 
	 * @author Christopher Lew
	 * @param p the Player being attacked
	 * @override
	 */
	public void attack(Player p) // Chris:I think this is unused?
	{
		if (isVisible) {
			p.reduceHealthBy(1, x, y, width, height);
		} 
	}

	/**
	 * This method will be used to toggle whether the spike will be draw or not and
	 * if they will hurt the player or not.
	 * 
	 * @param state the state to which the visibility will be changed
	 */
	private void setVisibility(boolean state) {
		isVisible = state;
	}

	/**
	 * This method does nothing because the spikes cannot be destroyed.
	 */
	public void reduceHealth() {
	}

	/**
	 * Returns a string representation of the TimingTrap
	 * 
	 * @return the string representation of the TimingTrap
	 */
	public String toString() {
		String s = "TimingTrap at x=" + x + "y=" + y;
		return s;
	}

	/**
	 * the method that draws the TimingTrap to the provided PApplet
	 * 
	 * @param marker the PApplet to draw on
	 */
	public void draw(PApplet marker) {

		

		if (DrawingSurface.getIterations() % 120 <= 60 || !Player.visibleByEnemies) {
			setVisibility(false);
			marker.pushStyle();
			marker.noStroke();
			marker.fill(180, 180, 180, 180);
			marker.rect((float) x, (float) y, (float) width, (float) height);
			marker.popStyle();
			

		} else {
			setVisibility(true);
			super.draw(marker);
		}
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