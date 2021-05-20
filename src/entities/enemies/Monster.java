package entities.enemies;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import entities.Player;
import processing.core.PImage;


public class Monster extends Enemy {
	
	private int[] directions; // -> NEWS
	private static final int NORTH = -1;
	private static final int SOUTH= 1;
	private static final int EAST= 1;
	private static final int WEST= -1;
	
	public Monster(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		directions = new int[4];
		directions[0] = NORTH;
		directions[1] = EAST;
		directions[2] = WEST; 
		directions[3] = SOUTH; 
		speed = 3 ;
//		symbol = 'M';
	}

	/**
	 * @author Christopher Lew
	 */
	public void act(ArrayList<Shape> obstacles) {
		super.act(obstacles);
//		move(); doesn't work as expected
		
		
		double rand1 = Math.random();
		double rand2 = Math.random();
		
		if (rand2*100 < 10) {
			int dx = 0, dy = 0;
			if (rand1 < 0.25) {
				dx = (int)speed;
			}
			else if (rand1<0.5) {
				dx = -(int)speed;
			}
			else if (rand1<0.75) {
				dy= (int)speed;
			}
			else {
				dy = -(int)speed;
			}
			
			moveBy(dx,dy);
		}
		
		
		
		
		
	}
	
	
	private int pickDirection()
	{
		return (int)(Math.random() * directions.length);
	}

//	public void attack() {
//	}
	

	public void reduceHealth() {
	}

	@Override
	public void attack(Player p) {
		p.reduceHealthBy(1, x, y, width, height);
	}
	public boolean isMovable() {
		return true;
	}
}

