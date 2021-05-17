package entities;
import entities.Ability;
import processing.core.PApplet;
import processing.core.PImage;
public class Heal extends Ability{
	Creature a ;
	
	private static char hSymbol = 'h';
	
	public Heal(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses=1;
//		symbol = 'h';
		imageName = "heal.png";
	}
	/**
	 * adds health to the player
	 * @param x the ammount of healing from the item
	 */
	public void healing(int x) {
		super.health+=x; 
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
	
	public String toString() {
		String s = "Heal ability at x="+this.x + "y="+this.y;
		return s;
	}
	
	/**
	 * So a heal ability is an a Creature that deals negative damage
	 */
	public void attack(Player p) {
		if(super.getPickedUp())
		p.reduceHealthBy(-1);
		//p.healBy(1); //i think this should work the same
	}
	
	public void draw(PApplet marker) {
		marker.pushStyle();
		marker.fill(95, 252, 0);
		marker.noStroke();
		marker.rect((float)x, (float)(y+height/3), (float)width , (float)height/3);
		marker.rect((float)(x+width/3), (float)(y), (float)width/3 , (float)height);
		marker.popStyle();
		
	}
	
	public static char getSymbol() {
		return hSymbol;
	}
	
}
