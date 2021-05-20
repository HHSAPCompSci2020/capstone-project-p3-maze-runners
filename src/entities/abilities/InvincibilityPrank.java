package entities.abilities;
import Maze.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
public class InvincibilityPrank  extends Ability{
	
	private static char sSymbol = 's';
	
	public InvincibilityPrank(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses=1;
	}

	
	/**
	 * increments usage
	 */
	public void use(PApplet marker) {
		super.use();
		
		
	}
	
	
	
	public String toString() {
		String s = "Instant Win :)";
		return s;
	}
	
	
}
