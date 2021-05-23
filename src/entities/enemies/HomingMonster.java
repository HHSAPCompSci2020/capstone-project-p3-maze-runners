package entities.enemies;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import entities.Player;
import processing.core.PImage;

/**
 * A monster but it moves towards the player
 * 
 * @author Christopher Lew
 *
 */
@SuppressWarnings("serial")
public class HomingMonster extends Monster {

	/**
	 * Constructs this HomingMonster at position x,y with the given image and user
	 * provided dimensions
	 * 
	 * @param img    the PImage that is drawn for this Creature
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Creature
	 * @param height the height of this Creature
	 */
	public HomingMonster(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 2;
	}

	/**
	 * Make Monster slowly go towards player (See superclass for more details)
	 */
	public void act(ArrayList<Shape> obstacles) {
		super.act(obstacles);
		// Make Monster slowly go towards player
		if (Player.visibleByEnemies || DrawingSurface.stealthDuration >0) {

			if (DrawingSurface.getIterations() % 10 == 0) {
				double dx = 0, dy = 0;
				if (DrawingSurface.getPlayer().getCenterX() < this.getCenterX()) {
					dx = -speed;
				} else {
					dx = speed;
				}
				if (DrawingSurface.getPlayer().getCenterY() < this.getCenterY()) {
					dy = -speed;
				} else {
					dy = speed;
				}
				moveBy(dx, dy);

			}
		}
		else if (!Player.visibleByEnemies){
			if (DrawingSurface.getIterations() % 10 == 0) {
				double dx = 0, dy = 0;
				if (DrawingSurface.getPlayer().getCenterX() < this.getCenterX()) {
					dx = 0.5*speed;
				} else {
					dx = -0.5*speed;
				}
				if (DrawingSurface.getPlayer().getCenterY() < this.getCenterY()) {
					dy = 0.5*speed;
				} else {
					dy = -0.5*speed;
				}
				moveBy(dx, dy);

			}
		}
	}

}
