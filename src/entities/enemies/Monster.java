package entities.enemies;

import entities.Enemy;
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
	
	private int pickDirection()
	{
		return (int)(Math.random() * directions.length);
	}

	public void attack() {
	}

	public void reduceHealth() {
	}

}

