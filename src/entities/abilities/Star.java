package entities.abilities;

import Maze.DrawingSurface;
import entities.Player;
import processing.core.PImage;

/**
 * Gives you invincibility and makes it so you kill any Enemies you touch
 * 
 * @author Chris
 */
@SuppressWarnings("serial")
public class Star extends Ability {

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
	public Star(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		// TODO Auto-generated constructor stub
		uses = 1;
	}

	/**
	 * Uses the ability
	 * 
	 * @param p the player that is using this ability
	 */
	public void use(Player p) {
		super.use();
		int duration = 5;
		DrawingSurface.starDuration = duration * DrawingSurface.FPS;
	}

	/**
	 * Returns a String representation of this Ability
	 * 
	 * @return String representation of this Ability
	 */
	public String toString() {
		return "Star Invincibility";
	}
}