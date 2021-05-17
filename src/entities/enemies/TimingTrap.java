package entities.enemies;

import entities.Enemy;
import entities.Player;
import processing.core.PImage;

/**
 * @author Lakshya Shrivastava
 * A timing trap will be spikes that appear and disappear every few seconds. 
 * If the player touches the timing trap, their health will go down by 1 
 */
public class TimingTrap extends Enemy {
	private boolean isVisible;
	
	
	
	public TimingTrap(PImage img, int x, int y)
	{
		super(img, x, y);
		isVisible = true;
		symbol = 'T';
//		imageName = "spike.png";
		attackDamage = 1;
	}
	
	public TimingTrap(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		isVisible = true;
		symbol = 'T';
//		imageName = "spike.png";

	}
	
	/**
	 * spikes dont move, they are toggled at a fixed location
	 */
	public void move() 
	{
		
	}

	/**
	 * 
	 * @override
	 */
	public void attack(Player p) 
	{
		if (System.nanoTime() % 30 <= 15) {
			p.reduceHealthBy(1, x, y, width, height);
		}
	}
	
	/**
	 * This method will be used to toggle whether the spike will be draw or not and if they will hurt the player or not. 
	 * @param state the state to which the visibility will be changed
	 */
	private void setVisibility(boolean state)
	{
		isVisible = state;
	}
	
	/**
	 * This method does nothing because the spikes cannot be destroyed.
	 */
	public void reduceHealth()
	{
	}
	
	public String toString() {
		String s = "TimingTrap at x="+x + "y="+y;
		return s;
	}

	

	
	
}