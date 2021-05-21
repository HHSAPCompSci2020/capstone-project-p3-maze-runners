package entities.abilities;

import entities.Entity;

import entities.Player;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents the Heal Ability
 * 
 * @author Joseph Huang
 *
 */
@SuppressWarnings("serial")
public class Heal extends Ability {
	Entity a;

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
	public Heal(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses = 1;
//		symbol = 'h';
		imageName = "heal.png";
	}

	/**
	 * adds health to the playser
	 * 
	 * @param x the ammount of healing from the item
	 */
	public void healing(int x) {
		super.health += x;
	}

	/**
	 * heals by adding 1 health to the player
	 */
	public void healing3() {
		super.health++;
	}

	/**
	 * heals by adding 1 health to the player
	 */
	public void healing2() {
		a.heal(1);
	}

	/**
	 * increments usage
	 */
	public void use() {
		super.use();
	}

	/**
	 * Returns a String representation of this Ability
	 * 
	 * @return String representation of this Ability
	 */
	public String toString() {
		String s = "Instant Health";
		return s;
	}

	/**
	 * Attacks by dealind -1 damage, do not use
	 * 
	 * @author Christopher Lew
	 * @param p  the player to be attack
	 */
	public void attack(Player p) {
		if (super.getPickedUp()) {

		}
//		p.reduceHealthBy(-1);
		// p.healBy(1); //i think this should work the same
	}

	/**
	 * Draws two rectangles for the green cross
	 * 
	 * @author Christopher Lew
	 * @param marker the PApplet object used for drawing
	 */
	public void draw(PApplet marker) {
		marker.pushStyle();
		marker.fill(95, 252, 0);
		marker.noStroke();
//		marker.stroke(75, 232, 0);
//		marker.stroke(2);
		marker.rect((float) x, (float) (y + height / 3), (float) width, (float) height / 3);
		marker.rect((float) (x + width / 3), (float) (y), (float) width / 3, (float) height);
		marker.popStyle();

	}
}
