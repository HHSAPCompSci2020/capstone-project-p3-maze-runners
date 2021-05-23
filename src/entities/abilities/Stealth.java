package entities.abilities;

import Maze.DrawingSurface;
import entities.Player;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * makes the player invisible to enemies
 * 
 * @author Joseph Huang
 *
 */
@SuppressWarnings("serial")
public class Stealth extends Ability {

	private long time;

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
	public Stealth(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses = 1;
	}

	/**
	 * increments usage
	 */
	public void use() {
		super.use();

		int duration = 3;
		DrawingSurface.maxStealthDuration =  duration * DrawingSurface.FPS;
		DrawingSurface.stealthDuration = DrawingSurface.maxStealthDuration;
		Player.visibleByEnemies = false;

	}

	/**
	 * 
	 */
	public void invincible() {
		time = DrawingSurface.getIterations() + 120;
		while (DrawingSurface.getIterations() <= time) {

		}
	}

	public void draw(PApplet marker) {
		super.draw(marker);
	}

	/**
	 * Returns a String representation of this Ability
	 * 
	 * @return String representation of this Ability
	 */
	public String toString() {
		return "Temporary Stealth";
	}
}