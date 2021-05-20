package entities;

import processing.core.PImage;

/**
 * This class represents the Exit to the maze 
 * When the player intersects with the Exit, the game will end
 * @author Lakshya Shrivastava
 *
 */
public class Exit extends Entity{
	
	private static final char SYMBOL = 'X';
	
	public Exit(PImage img, int x, int y, int width, int height)
	{
		super(img, x, y, width, height);
	}
	
	
}