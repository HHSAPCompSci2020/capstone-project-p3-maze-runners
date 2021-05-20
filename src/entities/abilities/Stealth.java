package entities.abilities;
import Maze.DrawingSurface;
import entities.Player;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * makes the player not visible 
 * @author Joseph Huang
 *
 */
public class Stealth extends Ability{

	private static char sSymbol = 's';
	private long time;

	public Stealth(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses=2;
		//		imageName = "heal.png";
	}


	/**
	 * increments usage
	 */
	public void use() {
		super.use();

		int duration = 5;
		DrawingSurface.stealthDuration = duration * DrawingSurface.FPS;

	}


	
	public void invincible() {
		time=DrawingSurface.getIterations()+120;
		while(DrawingSurface.getIterations()<=time) {

		}
	}

	public void draw(PApplet marker) {
		super.draw(marker);
	}

	public static char getSymbol() {
		return sSymbol;
	}

	public String toString() {
		return "Stealthed";
	}
}
