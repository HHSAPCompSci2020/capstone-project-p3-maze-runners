package entities.abilities;

import Maze.DrawingSurface;
import entities.Ability;
import entities.Player;
import processing.core.PImage;

/**
 * gives you invincibility and a way to kill monsters
 * @author Chris
 *
 */
public class Star extends Ability{

	public Star(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		// TODO Auto-generated constructor stub
		uses = 1;
	}
	
	
	
	public void use(Player p) {
		super.use();
		int duration = 5;
		DrawingSurface.starDuration = duration * DrawingSurface.FPS;
	}

}
