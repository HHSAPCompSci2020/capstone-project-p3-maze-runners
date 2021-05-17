package entities.enemies;

import Maze.DrawingSurface;
import entities.Enemy;
import entities.Player;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Lakshya Shrivastava
 * A timing trap will be spikes that appear and disappear every few seconds. 
 * If the player touches the timing trap, their health will go down by 1 
 */
public class Spike extends Enemy {
	private boolean isVisible;
	
	private static char tSymbol = 'T';
	
	public Spike(PImage img, int x, int y)
	{
		super(img, x, y);
		isVisible = true;
//		symbol = 'T';
//		imageName = "spike.png";
		attackDamage = 1;
	}
	
	public Spike(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		isVisible = true;
//		symbol = 'T';
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
	public void attack(Player p) //Chris:I think this is unused?
	{
		if (isVisible) {
			p.reduceHealthBy(1, x, y, width, height);
		}
		else {
//			System.out.println("safe to pass");
		}
	}
	
	/**
	 * This method will be used to toggle whether the spike will be draw or not and if they will hurt the player or not. 
	 * @param state the state to which the visibility will be changed
	 */
	private void setVisibility(boolean state)
	{
		isVisible = true;
	}
	
	/**
	 * This method does nothing because the spikes cannot be destroyed.
	 */
	public void reduceHealth()
	{
	}
	
	public String toString() {
		String s = "Spike at x="+x + "y="+y;
		return s;
	}

//	public static char getSymbol() {
//		return tSymbol;
//	}

	public void draw(PApplet marker) {


		super.draw(marker);

	}
	
}