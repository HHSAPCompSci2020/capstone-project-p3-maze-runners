package entities.enemies;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import entities.Enemy;
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

	public void move() {
		int direction = pickDirection();
		if(direction == NORTH)
				x+=NORTH;
		else if(direction == EAST)
				y+= EAST;
		else if(direction == SOUTH)
				x+= SOUTH;
		else
			y+= WEST;
	}
	
	public void act(ArrayList<Shape> obstacles) {
		super.act(obstacles);
//		move(); doesn't work as expected
		
		
		double rand1 = Math.random();
		double rand2 = Math.random();
		
		if (rand2*100 < 10) {
			int x0 = 0, y0 = 0;
			if (rand1 < 0.25) {
				x0 = (int)speed;
			}
			else if (rand1<0.5) {
				x0 = -(int)speed;
			}
			else if (rand1<0.75) {
				y0= (int)speed;
			}
			else {
				y0 = -(int)speed;
			}
			
			moveBy(x0,y0);
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

