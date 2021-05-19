package entities;
import Maze.DrawingSurface;
import entities.Ability;
import processing.core.PApplet;
import processing.core.PImage;
public class InvincibilityPrank  extends Ability{
	
	private static char sSymbol = 's';
	
	public InvincibilityPrank(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses=1;
//		imageName = "heal.png";
	}

	
	/**
	 * increments usage
	 */
	public void use(PApplet marker) {
		super.use();
		
		
	}
	
	
	
	public String toString() {
		String s = "Invincibility";
		return s;
	}
	
//	
//	public void draw(PApplet marker) {
//		marker.pushStyle();
//		marker.fill(248, 44, 0);
//		marker.noStroke();
//		marker.rect((float)x, (float)(y+height/3), (float)width , (float)height/3);
//		marker.rect((float)(x+width/3), (float)(y), (float)width/3 , (float)height);
//		marker.popStyle();
//		
//	}
	
	
	
	//Inheriting static methods is very weird, just put 's'
//	public static char getSymbol() {
//		return sSymbol;
//	}
	
}
