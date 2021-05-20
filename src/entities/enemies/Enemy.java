package entities.enemies;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import processing.core.PImage;
import Maze.*;
import entities.Creature;
import entities.Player;


/**
 * @author Lakshya Shrivastava
 *	General super class for all the enemies that will be in the maze
 */
public abstract class Enemy extends Creature {

//	protected  double health;
//	protected int x;
//	protected int y;
	protected int attackDamage;
	protected boolean canDie = true;
	
	public Enemy(PImage img, int x, int y) 
	{
		super(img,x,y);
		super.maxHealth = 3;
	}
	
	public Enemy(PImage img, int x, int y, int width, int height) 
	{
		super(img, x, y, width, height);
		super.maxHealth = 3;
	}
	
	public void act(ArrayList<Shape> obstacles, Player p, Maze m) {
		if (touchingCreature(p)) {
			
		}
		else {
			super.act(obstacles);
		}
		
	}
	
	public void attack(Player p) {
		p.reduceHealthBy(1, x, y, width, height);
	}
	
	/**
	 * the pattern in which the enemy moves around
	 */
	 abstract public void move();

//	 abstract public void attack();
	 
	 abstract public void reduceHealth();
	 
	 abstract public boolean isMovable();
		 
	 public boolean canDie() {
		 return canDie;
	 }
	 
}
