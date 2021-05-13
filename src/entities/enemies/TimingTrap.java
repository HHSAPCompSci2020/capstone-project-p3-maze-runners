package entities.enemies;

import entities.Enemy;
import processing.core.PImage;

/**
 * @author Lakshya Shrivastava
 * A timing trap will be spikes that appear and disappear every few seconds. 
 * If the player touches the timing trap, their health will go down by 1 
 */
public class TimingTrap extends Enemy {
	private boolean isVisible;
	
	public TimingTrap(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		isVisible = true;
	}
	
	public void move() 
	{
		
	}

	public void attack() 
	{
		
	}
	
	private void setVisibility(boolean state)
	{
		isVisible = state;
	}
	
	public void reduceHealth()
	{
		health -= 1;
	}
}