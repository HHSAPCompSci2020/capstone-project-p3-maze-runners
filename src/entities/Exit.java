package entities;

import processing.core.PImage;

/**
 * This class represents the Exit to the maze When the player intersects with
 * the Exit, the game will end
 * 
 * @author Lakshya Shrivastava
 *
 */
@SuppressWarnings("serial")
public class Exit extends Entity {

	/**
	 * Constructs this Creature at position x,y with the given image and user
	 * provided dimensions
	 * 
	 * @param img    the PImage that is drawn for this Creature
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Creature
	 * @param height the height of this Creature
	 */
	public Exit(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
	}

}