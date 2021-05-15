package entities;
import entities.Ability;
import processing.core.PImage;
public class Heal extends Ability{
	Creature a ;
	
	public Heal(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses=1;
		symbol = 'h';
	}
	/**
	 * adds health to the player
	 * @param x the ammount of healing from the item
	 */
	public void healing(int x) {
		//super.health+=x; this method if we make ability extend creature not moving image
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
}
