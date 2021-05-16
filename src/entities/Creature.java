package entities;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

import processing.core.PImage;
import Maze.*;

public class Creature extends MovingImage {

	public static final int DEFAULT_WIDTH = 50;
	public static final int DEFAULT_HEIGHT = 50;
	
	//NOTE: Positive x is right and positive y or positive yVelocity is DOWN!!!
	private double xVelocity, yVelocity; 
	private boolean onASurface;
	private double friction;//lower friction means you slow down faster, (velocity is multiplied by friction every act() call)
	private double gravity;
	private double jumpStrength;
	
	
	
	//New fields
	protected double speed; //higher speeds mean you move faster
	protected int maxHealth;
	protected int health; //to be implemented
	
	

	public Creature(PImage img, int x, int y) {
		super(img, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		xVelocity = 0;
		yVelocity = 0;
		onASurface = false;
		gravity = 0.7;
//		friction = .85;
		friction = 0.6;
		jumpStrength = 15;
		
		maxHealth = 1;
		health = maxHealth;
		speed = 4.0;
	}
	
	public Creature(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		xVelocity = 0;
		yVelocity = 0;
		onASurface = false;
		gravity = 0.7;
//		friction = .85;
		friction = 0.6;
		jumpStrength = 15;
		
		maxHealth = 1;
		health = maxHealth;
		speed = 4.0;
	}
	
	public boolean touchingPlayer(Player player) {
		boolean isTouching = false;
		
		
		return isTouching;
	}
	
	private boolean getHit() {
		health--;
		return false;
	}
	
	public void removeSelfFromMaze(Maze m) {
		
	}
	
	
	
	//New movement methods
	public void moveBy(int x, int y) {
		if (xVelocity <= 10 && xVelocity >= -10) {
			xVelocity += speed*x;
		}
		if (yVelocity <= 10 && yVelocity >= -10) {
			yVelocity += speed*y;
		}
	}
	
	
	// METHODS
	public void walk(int dir) { //UNUSED
		if (xVelocity <= 10 && xVelocity >= -10)
			xVelocity += dir;
	}

	public void jump() {//UNUSED
		if (onASurface)
			yVelocity -= jumpStrength;
	}

	public void hurt() {
		health--;
	}
	
	public void heal(int x) {
		if(health+x<=maxHealth) {
		health+=x;}else {
			health=maxHealth;
		}
	}
	

	
	public void act(ArrayList<Shape> obstacles) {
		double xCoord = getX();
		double yCoord = getY();
		double width = getWidth();
		double height = getHeight();

		// ***********Y AXIS***********
		
		
		//Chris: turned off gravity
//		yVelocity += gravity; // GRAVITY
		
		yVelocity *= friction;
		double yCoord2 = yCoord + yVelocity;

		Rectangle2D.Double strechY = new Rectangle2D.Double(xCoord,Math.min(yCoord,yCoord2),width,height+Math.abs(yVelocity));

		onASurface = false;

		if (yVelocity > 0) {
			Shape standingSurface = null;
			for (Shape s : obstacles) {
				if (s.intersects(strechY)) {
					onASurface = true;
					standingSurface = s;
					yVelocity = 0;
				}
			}
			if (standingSurface != null) {
				Rectangle r = standingSurface.getBounds();
				yCoord2 = r.getY()-height;
			}
		} else if (yVelocity < 0) {
			Shape headSurface = null;
			for (Shape s : obstacles) {
				if (s.intersects(strechY)) {
					headSurface = s;
					yVelocity = 0;
				}
			}
			if (headSurface != null) {
				Rectangle r = headSurface.getBounds();
				yCoord2 = r.getY()+r.getHeight();
			}
		}

		if (Math.abs(yVelocity) < .5)
			yVelocity = 0;

		// ***********X AXIS***********


		xVelocity *= friction;

		double xCoord2 = xCoord + xVelocity;

		Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(xCoord,xCoord2),yCoord2,width+Math.abs(xVelocity),height);

		if (xVelocity > 0) {
			Shape rightSurface = null;
			for (Shape s : obstacles) {
				if (s.intersects(strechX)) {
					rightSurface = s;
					xVelocity = 0;
				}
			}
			if (rightSurface != null) {
				Rectangle r = rightSurface.getBounds();
				xCoord2 = r.getX()-width;
			}
		} else if (xVelocity < 0) {
			Shape leftSurface = null;
			for (Shape s : obstacles) {
				if (s.intersects(strechX)) {
					leftSurface = s;
					xVelocity = 0;
				}
			}
			if (leftSurface != null) {
				Rectangle r = leftSurface.getBounds();
				xCoord2 = r.getX()+r.getWidth();
			}
		}


		if (Math.abs(xVelocity) < .5)
			xVelocity = 0;

		moveToLocation(xCoord2,yCoord2);

	}
	
	
	


}
