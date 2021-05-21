package entities.enemies;

import entities.Player;
import processing.core.PApplet;
import processing.core.PImage;

@SuppressWarnings("serial")
public class Fence extends Spike{

	public Fence(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		// TODO Auto-generated constructor stub
		isVisible = false;
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
		//dont attack
	}
	public void attack(Player p, double x) // Chris:I think this is unused?
	{
		//dont attack
	}

	/**
	 * This method will be used to toggle whether the spike will be draw or not and
	 * if they will hurt the player or not.
	 * 
	 * @param state the state to which the visibility will be changed
	 */
	private void setVisibility(boolean state) {
//		isVisible = state;
		isVisible = false;
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

		setVisibility(false);
		marker.pushStyle();
		marker.noStroke();
		marker.fill(128, 230, 128, 180);
		marker.rect((float) x, (float) y, (float) width, (float) height);
		marker.popStyle();


		setVisibility(true);

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
