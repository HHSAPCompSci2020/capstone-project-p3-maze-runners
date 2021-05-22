package entities.enemies;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import entities.Player;
import processing.core.PImage;

/**
 * Represents a monster that moves around randomly in the maze
 * 
 * @author Lakshya Shrivastava (most of the code was improved/replaced by Chris
 *         though)
 *
 */
@SuppressWarnings("serial")
public class Monster extends Enemy {

	/**
	 * Constructs this Monster at position x,y with the given image and user
	 * provided dimensions
	 * 
	 * @param img    the PImage that is drawn for this Creature
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Creature
	 * @param height the height of this Creature
	 */
	public Monster(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		speed = 2;
	}

	/**
	 * @author Christopher Lew
	 */
	public void act(ArrayList<Shape> obstacles) {
		super.act(obstacles);

		double rand1 = Math.random();
		double rand2 = Math.random();

//		if (rand2 * 100 < 10) {
		if (DrawingSurface.getIterations() % 5 == 0) {

			int dx = 0, dy = 0;
			if (rand1 < 0.25) {
				dx = (int) speed;
			} else if (rand1 < 0.5) {
				dx = -(int) speed;
			} else if (rand1 < 0.75) {
				dy = (int) speed;
			} else {
				dy = -(int) speed;
			}

			moveBy(dx, dy);
		}
	}

	/**
	 * Does nothing, this enemy cannot be damaged, is only killable by the star
	 * powerup
	 */
	public void reduceHealth() {
	}

	/**
	 * Method is used for attacking a player
	 * 
	 * @param p the player to attack
	 */
	public void attack(Player p) {
		p.reduceHealthBy(1, x, y, width, height);
	}

	/**
	 * Returns whether this enemy is moveable or not. Must by implemented by all
	 * subclasses
	 * 
	 * @return true if this enemy is moveable, false otherwise
	 */
	public boolean isMovable() {
		return true;
	}
}
