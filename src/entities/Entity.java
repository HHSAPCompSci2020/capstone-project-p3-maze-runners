package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

import processing.core.PImage;
import Maze.*;

@SuppressWarnings("serial")
public abstract class Entity extends MovingImage {

	/**
	 * Default Width of the Creature
	 */
	public static final int DEFAULT_WIDTH = 50;
	/**
	 * Default Height of the Creature
	 */
	public static final int DEFAULT_HEIGHT = 50;

	// NOTE: Positive x is right and positive y or positive yVelocity is DOWN!!!
	private double xVelocity, yVelocity;
	private double friction;// lower friction means you slow down faster, (velocity is multiplied by
							// friction every act() call)
//	private double gravity;
//	private double jumpStrength;
//	private boolean onASurface;

	/**
	 * how far the entity gets knocked back
	 */
	protected int knockback = 9;
//	public static char symbol;

	/**
	 * name of the image that this entity is represented by
	 */
	protected static String imageName;

	// New fields
	/**
	 * Speed of the entity
	 */
	protected double speed; // higher speeds mean you move faster
	/**
	 * Maximum health of the entity
	 */
	protected int maxHealth;
	/**
	 * current health of the entity
	 */
	protected int health; // to be implemented

	/**
	 * Constructs this Entity at position x,y with the given image and default
	 * dimensions
	 * 
	 * @param img the PImage that is drawn for this Entity
	 * @param x   the top left x coordinate
	 * @param y   the top left x coordinate
	 */
	public Entity(PImage img, int x, int y) {
		super(img, x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		xVelocity = 0;
		yVelocity = 0;
//		onASurface = false;
//		gravity = 0.7;
//		friction = .85;
		friction = 0.6;
//		jumpStrength = 15;

		maxHealth = 1;
		health = maxHealth;
		speed = 4.0;
	}

	/**
	 * Constructs this Entity at position x,y with the given image and user provided
	 * dimensions
	 * 
	 * @param img    the PImage that is drawn for this Entity
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Entity
	 * @param height the height of this Entity
	 */
	public Entity(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		xVelocity = 0;
		yVelocity = 0;
//		onASurface = false;
//		gravity = 0.7;
//		friction = .85;
		friction = 0.6;
//		jumpStrength = 15;

		maxHealth = 1;
		health = maxHealth;
		speed = 4.0;
	}

	/**
	 * Check collision between this Entity and the other
	 * 
	 * @author Christopher Lew
	 * @param other the other Creature to check collision with
	 * @return true if Creatures touching, false if not touching
	 */
	public boolean touchingCreature(Entity other) {
		boolean isTouching = false;

		isTouching = other.intersects(this);

		if (isTouching && System.nanoTime() % 30 == 0) {
//			System.out.println("INTERSECTING! " + this);
		}
		return isTouching;
	}

	protected boolean getHit() {
		health--;
		return false;
	}

	/**
	 * Removes itself from a given Maze
	 * 
	 * @pre this is already added to the enemies arraylist in Maze m
	 * @author Christopher Lew
	 * @param m
	 * @param index
	 */
	public void removeSelfFromMaze(Maze m, int index) {
		m.getEnemies().remove(index);
	}

	/**
	 * Returns a string representation of the entity
	 * 
	 * @return the string representation of the entity
	 */
	public String toString() {
		String s = "";
		s += "Creature at x=" + this.x + "y=" + this.y;
		return s;
	}

	// New movement methods
	/**
	 * Move the Entity by an x and y amount (given integers)
	 * 
	 * @param x amount to move in x direction (right positive)
	 * @param y amount to move in y direction (down positive)
	 */
	public void moveBy(int x, int y) {
		if (xVelocity <= 10 && xVelocity >= -10) {
			xVelocity += speed * x;
		}
		if (yVelocity <= 10 && yVelocity >= -10) {
			yVelocity += speed * y;
		}
	}

	/**
	 * Move the Entity by an x and y amount (given doubles)
	 * 
	 * @param x amount to move in x direction (right positive)
	 * @param y amount to move in y direction (down positive)
	 */
	public void moveBy(double x, double y) {
		if (xVelocity <= 10 && xVelocity >= -10) {
			xVelocity += speed * x;
		}
		if (yVelocity <= 10 && yVelocity >= -10) {
			yVelocity += speed * y;
		}
	}

	// METHODS
//	public void walk(int dir) { //UNUSED
//		if (xVelocity <= 10 && xVelocity >= -10)
//			xVelocity += dir;
//	}
//
//	public void jump() {//UNUSED
//		if (onASurface)
//			yVelocity -= jumpStrength;
//	}
//
//	public void hurt() {
//		health--;
//	}

	public void heal(int x) {
		if (health + x <= maxHealth) {
			health += x;
		} else {
			health = maxHealth;
		}
	}

	/**
	 * Taken from APCS animation demo Handles the basic behavior of all Entities.
	 * This includes move speed.
	 * 
	 * @param obstacles
	 */
	public void act(ArrayList<Shape> obstacles) {
		double xCoord = getX();
		double yCoord = getY();
		double width = getWidth();
		double height = getHeight();

		// ***********Y AXIS***********

		// Chris: turned off gravity
//		yVelocity += gravity; // GRAVITY

		yVelocity *= friction;
		double yCoord2 = yCoord + yVelocity;

		Rectangle2D.Double strechY = new Rectangle2D.Double(xCoord, Math.min(yCoord, yCoord2), width,
				height + Math.abs(yVelocity));

//		onASurface = false;

		if (yVelocity > 0) {
			Shape standingSurface = null;
			for (Shape s : obstacles) {
				if (s.intersects(strechY)) {
//					onASurface = true;
					standingSurface = s;
					yVelocity = 0;
				}
			}
			if (standingSurface != null) {
				Rectangle r = standingSurface.getBounds();
				yCoord2 = r.getY() - height;
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
				yCoord2 = r.getY() + r.getHeight();
			}
		}

		if (Math.abs(yVelocity) < .5)
			yVelocity = 0;

		// ***********X AXIS***********

		xVelocity *= friction;

		double xCoord2 = xCoord + xVelocity;

		Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(xCoord, xCoord2), yCoord2,
				width + Math.abs(xVelocity), height);

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
				xCoord2 = r.getX() - width;
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
				xCoord2 = r.getX() + r.getWidth();
			}
		}

		if (Math.abs(xVelocity) < .5)
			xVelocity = 0;

		moveToLocation(xCoord2, yCoord2);

	}

//	public abstract char getSymbol();

	/**
	 * Make this Entity get "bumped" by the other Entity
	 * 
	 * @param otherX       x position of other Creature
	 * @param otherY       y position of other Creature
	 * @param otherWidth   width of other Creature
	 * @param otherHeight  height of other Creature
	 * @param kbMultiplier how far to be knocked back (larger value = farther push)
	 */
	public void receiveKnockback(double otherX, double otherY, double otherWidth, double otherHeight, double kbMultiplier) {
		boolean runNewVersion = true;

		if(runNewVersion) {
			double oCenterX = otherX + otherWidth/2;
			double oCenterY = otherY + otherHeight/2;
			double dx = oCenterX - this.getCenterX();
			double dy = oCenterY - this.getCenterY();
			double distance = Math.sqrt(  dx*dx + dy*dy);
			double angle; // angle from positive x axis in radians, greater values go clockwise
			double kbX, kbY, kbMagnitude;
			
//			if (dx == 0) {
//				if (dy > 0) 
//					angle = Math.PI;
//				else 
//					angle = 3 * Math.PI/2;
//			}
			angle = Math.atan(dy/dx);
			angle = Math.atan2(dy, dx);

//			Point other = new Point();
//			other.x = (int)oCenterX;
//			other.y = (int)oCenterY;
//			other.
			
			
			
			//kbMagnitude is between 0.5 and 2
//			System.out.println("dist: "+distance);
//			kbMagnitude = Math.min(10, Math.max(0.1, Math.pow( (80 - distance)/100.0, 0.5)));
			if (distance > 60) {
				kbMagnitude = 1.0;
			}
			else if (distance > 40) {
				kbMagnitude = 1.05;
			}
			else if (distance > 30) {
				kbMagnitude = 1.4;
			}
			else if (distance > 20){
				kbMagnitude = 1.8;
			}
			else {
				kbMagnitude = 2.5;
			}
			if (DrawingSurface.mazeSelected == 10) {
				kbMagnitude *= 25.0/40;
			}
			kbMagnitude *= kbMultiplier;
			kbX = -kbMagnitude * (dx/distance);
			kbY = -kbMagnitude * (dy/distance);
			
//			if (dx <0) 
//				kbX = Math.abs(kbX);
//			else
//				kbX = -Math.abs(kbX);
//			if (dy < 0)
//				kbY = Math.abs(kbY);
//			else
//				kbY = -Math.abs(kbY);
			
			moveBy( kbX*knockback, kbY * knockback );
			angle = Math.toDegrees(angle);
//			System.out.println("angle = " + angle+ " kbX = "+kbX + ", kbY = "+kbY);
//			System.out.println("dx = "+dx+ " dy = "+dy + "\n");
		}
		else {
			// get Knocked back
			if (otherX <= this.x && this.x <= otherX + otherWidth) {
				if (y >= otherY) {
					moveBy(0, kbMultiplier * knockback);
				} else {
					moveBy(0, -kbMultiplier * knockback);
				}
			}

			if (otherY <= this.y && this.y <= otherY + otherHeight) {
				if (x >= otherX) {
					moveBy(kbMultiplier * knockback, 0);
				} else {
					moveBy(-kbMultiplier * knockback, 0);
				}
			}
		}
	}

	/**
	 * Gets the velocity along the X-axis
	 * 
	 * @return velocity along the X-axis
	 */
	public double getXVelocity() {
		return xVelocity;
	}

	/**
	 * Gets the velocity along the Y-axis
	 * 
	 * @return velocity along the Y-axis
	 */
	public double getYVelocity() {
		return yVelocity;
	}

}