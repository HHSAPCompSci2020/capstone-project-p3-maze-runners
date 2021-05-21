package entities.abilities;

import Maze.*;

import entities.Entity;
import processing.core.PImage;

/**
 * This class represents Abilities that the Player will use to change the way
 * they interact with the game
 * 
 * @author Joseph Huang
 *
 */
@SuppressWarnings("serial")
public class Ability extends Entity {
	/**
	 * The number of times a player can use this ability
	 */
	protected int uses;
	private boolean pickedUp;
	// private int locX, locY;
	private int coolDown = 1;

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
	public Ability(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);

		pickedUp = false;
		// locX=x;
		// locY=y;
	}

	/**
	 * Constructs this Ability at position x,y with the given image and default
	 * dimensions
	 * 
	 * @param img the PImage that is drawn for this Entity
	 * @param x   the top left x coordinate
	 * @param y   the top left x coordinate
	 */

	public Ability(PImage img, int x, int y) {
		super(img, x, y, 40, 60);
		// uses=0;
		pickedUp = false;
	}

	/**
	 * pick up an ability if the player collides with it
	 */
	public void aquire() {
		pickedUp = true;// if player collides with an ability on the floor
	}

	/**
	 * attacks depending on the type of ability currently being used e.g. Shooting a
	 * bullet, striking once with a sword
	 */
	public void use() {
		System.out.println("Use ability!");
		uses--;
	}

	/**
	 * drops the weapon/ability being held on player command or when ammo is out
	 */
	public void drop() {
		if (uses == 0) {
			pickedUp = false;
			// more code//visually drop the ability
		}
	}

	/**
	 * returns whether an ability is equipped to a player or not
	 * 
	 * @return true if the ablity is currently held by the player, false if not or
	 *         on the floor
	 */
	public boolean getPickedUp() {
		return pickedUp;
	}

	/**
	 * Removes this ability from the maze
	 * 
	 * @param m     The maze that this ability is a part of
	 * @param index the index of this ability in the mazes abilities arrayList
	 */
	public void removeSelfFromMaze(Maze m, int index) {
		m.getAbilities().remove(index);
	}

	/**
	 * Returns the Ability's cooldown in seconds
	 * 
	 * @return the Ability's cooldown in seconds
	 */
	public int getCooldown() {
		return coolDown;
	}

	/**
	 * Returns a String representation of this Ability
	 * 
	 * @return String representation of this Ability
	 */
	public String toString() {
		return "Ability";
	}

	/**
	 * Returns the number of uses left in this ability
	 * 
	 * @return number of uses left in this ability
	 */
	public int getUses() {
		return uses;
	}
}
