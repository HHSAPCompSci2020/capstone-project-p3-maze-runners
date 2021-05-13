package entities;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import processing.core.PImage;
import Maze.*;


/**
 * @author Lakshya Shrivastava
 *	General super class for all the enemies that will be in the maze
 */
public abstract class Enemy extends Creature {

	protected  double health;
	private int x,y;
	
	public Enemy(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.maxHealth = 3;
	}
	
	/**
	 * the pattern in which the enemy moves around
	 */
	 abstract private void move();

	 abstract private void attack();
	 
	 abstract public void reduceHealth();
}
