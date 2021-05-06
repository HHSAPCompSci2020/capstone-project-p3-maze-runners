package entities;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import processing.core.PImage;
import Maze.*;


public class Enemy extends MovingImage {

	private double health;
	private int x,y;
	
	public Enemy(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * the pattern in which the enemy moves around
	 */
	public void move() {
		
	}
	
	/**
	 * once an enemy is facing the player, it will stop moving and attack using its basic ability to take away 
	 * health from the player
	 */
	public void attack() {
		
	}
}
