package entities.enemies;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import processing.core.PImage;

public class HomingMonster extends Monster{

	public HomingMonster(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 2;
	}
	
	public void act(ArrayList<Shape> obstacles) {
		super.act(obstacles);
		//Make Monster slowly go towards player
				if (DrawingSurface.getIterations()%10 == 0) {
					double x0 = 0, y0 = 0;
					if (DrawingSurface.getPlayer().getCenterX() < this.getCenterX() ) {
						x0 = -speed;
					}
					else {
						x0 = speed;
					}
					if (DrawingSurface.getPlayer().getCenterY() < this.getCenterY() ) {
						y0 = -speed;
					}
					else {
						y0 = speed;
					}
					moveBy(x0,y0);

				}
	}

}
