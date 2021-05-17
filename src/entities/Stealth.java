package entities;
import entities.Ability;
import processing.core.PApplet;
import processing.core.PImage;
public class Stealth extends Ability{
	
	private static char sSymbol = 's';
	
	public Stealth(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses=3;
//		imageName = "heal.png";
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
	
	
	public void draw(PApplet marker) {
		marker.pushStyle();
		marker.fill(248, 44, 0);
		marker.noStroke();
		marker.rect((float)x, (float)(y+height/3), (float)width , (float)height/3);
		marker.rect((float)(x+width/3), (float)(y), (float)width/3 , (float)height);
		marker.popStyle();
		
	}
	
	public static char getSymbol() {
		return sSymbol;
	}
	
}
