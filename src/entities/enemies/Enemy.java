package entities.enemies;

import java.awt.*;
import java.util.*;
import processing.core.PImage;
import Maze.*;
import entities.Entity;
import entities.Player;

/**
 * General super class for all the enemies that will be in the maze
 * 
 * @author Lakshya Shrivastava
 */
@SuppressWarnings("serial")
public abstract class Enemy extends Entity {

	/**
	 * The amount of damage that an enemy does to the player
	 */
	protected int attackDamage;
	/**
	 * Whether this enemy can die or not
	 */
	protected boolean canDie;

	/**
	 * Constructs this Enemy at position x,y with the given image and default
	 * dimensions
	 * 
	 * @param img the PImage that is drawn for this Creature
	 * @param x   the top left x coordinate
	 * @param y   the top left x coordinate
	 */
	public Enemy(PImage img, int x, int y) {
		super(img, x, y);
		maxHealth = 3;
		canDie = true;
	}

	/**
	 * Constructs this Enemy at position x,y with the given image and user provided
	 * dimensions
	 * 
	 * @param img    the PImage that is drawn for this Creature
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Creature
	 * @param height the height of this Creature
	 */
	public Enemy(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		maxHealth = 3;
		canDie = true;
	}

	/**
	 * Taken from APCS animation demo Handles the basic behavior of all Entities.
	 * This includes move speed.
	 * 
	 * @param obstacles
	 */
	public void act(ArrayList<Shape> obstacles, Player p, Maze m) {
		if (touchingCreature(p)) {

		} else {
			super.act(obstacles);
		}

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
	 * Method is used for attacking a player
	 * 
	 * @param p the player to attack
	 */
	public void attack(Player p, double kbMultiplier) {
		if (Player.visibleByEnemies) {
			p.reduceHealthBy(1, x, y, width, height, kbMultiplier);
		}
	}

	/**
	 * subclasses must implement this method
	 */
	abstract public void reduceHealth();

	/**
	 * Returns whether this enemy can die
	 * 
	 * @return true if enemy can die, false otherwise
	 */
	public boolean canDie() {
		return canDie;
	}

	/**
	 * Returns whether this enemy is moveable or not. Must by implemented by all
	 * subclasses
	 * 
	 * @return true if this enemy is moveable, false otherwise
	 */
	abstract public boolean isMovable();

}
