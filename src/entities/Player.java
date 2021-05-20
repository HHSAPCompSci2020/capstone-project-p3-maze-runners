package entities;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Christopher Lew
 *
 */
public class Player extends Entity{
//	private int lives;
	public static final double WALK_SPEED = 2;
	public static final double RUN_SPEED = 4;
	public static final double DAMAGED_SPEED = 0.6;
	private PImage flippedImage;
	public boolean invincible = false;
	public boolean visibleByEnemies = true;
	
	
	public Player(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.maxHealth = 3;
		health = maxHealth;
		imageName = "luigi.png";
	} 
	
	public Player(PImage img, int x, int y, int width, int height, PImage flipped) {
		super(img, x, y, width, height);
		super.maxHealth = 3;
		health = maxHealth;
		imageName = "luigi.png";
		flippedImage = flipped;
	} 
	
	public void reduceHealthBy(int damage) {
		if (!invincible) {
			if (DrawingSurface.playerDmgCooldown== 0) {
				health -= damage;
				DrawingSurface.playerDmgCooldown = DrawingSurface.DMG_MAX_COOLDOWN;
				if (health <= 0) {
					health = 0;
					DrawingSurface.lives -= 1;
					//do DrawingSurface.spawnNewPlayer();
				}
			}
		}
	}
	
	/**
	 * reduces the health of the player
	 * @param damage How much damage to take (int)
	 * @param otherX x coordinate of the other Creature
	 * @param otherY y coordinate of the other Creature
	 * @param otherWidth width of the other Creature
	 * @param otherHeight height of the other Creature
	 */
	public void reduceHealthBy(int damage, double otherX, double otherY, double otherWidth, double otherHeight) {
		if (!invincible) {
			if (DrawingSurface.playerDmgCooldown== 0) {
				health -= damage;
				DrawingSurface.playerDmgCooldown = 60;
				if (health <= 0) {
					health = 0;
					DrawingSurface.lives -= 1;
				}
				receiveKnockback(otherX, otherY, otherWidth, otherHeight, 1.0);

			}
		}
	}
	
	/**
	 * 
	 * @param damage how much health is lost
	 * @param otherX x coordinate of the entity causing health lost
	 * @param otherY y coordinate of the entity causing health lost
	 */
	public void reduceHealthBy(int damage, int otherX, int otherY) {
		if (!invincible) {
			health -= damage;
			DrawingSurface.playerDmgCooldown = 60;
			if (health <= 0) {
				health = 0;
				DrawingSurface.lives -= 1;
			}
			if (DrawingSurface.playerDmgCooldown== 0) {
				//take knockback
				
				double direction;
			}
		}
	}
	
	/**
	 * 
	 * @param amount amount of health recovered
	 */
	public void healBy(int amount) {
		health+= amount;
		if(health>maxHealth)
			health=maxHealth;
	}
	
	/**
	 * @param obstacles list of obstacles 
	 */
	public void act(ArrayList<Shape> obstacles) {
		if (DrawingSurface.playerDmgCooldown == 0) {
			speed= WALK_SPEED;
		}
		else {
//			speed = 0.5;
		}
		super.act(obstacles);
	}
	
	/**
	 * @param x, y the x and y corrdinates of the move location
	 */
	public void moveBy(int x, int y) {
		if (DrawingSurface.getRespawnTimer() != 0) {
			return;
		}
		else {
			super.moveBy(x, y);
		}
	}
	
	
	
	
	
	public char getSymbol() {
		return 'C';
	}
	

	public void attack(Player p) {
		// TODO Auto-generated method stub
		System.out.println("Player attacked player");
		p.reduceHealthBy(1);
	}
	
	public int getHealth() {
		return this.health;
	}
	
	
	public void draw(PApplet marker) {
		marker.pushStyle();
		if (DrawingSurface.playerDmgCooldown > 0) {
			marker.fill(248, 44, 0);
			marker.text("Ow!", (float)(x - width/4), (float)(y - width/8));
			
		}
		else {
			marker.fill(0);
			speed = WALK_SPEED;
		}
		if (DrawingSurface.playerDmgCooldown > 30) {
			speed = DAMAGED_SPEED;
		}
		else {
			speed = WALK_SPEED;
		}
		
		
		
		
		
		if (!facingRight) {
			marker.image(flippedImage, (int) x, (int) y, (int) width, (int) height);
		}
		else {
			marker.image(image, (int) x, (int) y, (int) width, (int) height);
		}
		
		if (invincible) {
			marker.strokeWeight(3);
			marker.stroke(255, 255, 0);
			marker.noFill();
			marker.ellipse((float)this.getCenterX(), (float)this.getCenterY(), (float)(1.4f*width), (float)(1.4f*height));
		}
		marker.popStyle();

//		super.draw(marker);
		
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return speed;
	}
	
	/*
	public void rgbColorStuff(PApplet marker) {
		float p = 255;
		float amp = 255;
		float t = DrawingSurface.getIterations() % p;
		float r = 255, g = 255, b = 255;
		float m = amp / (p/3);
				
//		
//		r = (float)(128 * (Math.sin( B * (t - 0) +0.5) ));
//		g = (float)(128 * (Math.sin( B * (t - Math.PI/3) +0.5) ));
//		b = (float)(128 * (Math.sin( B * (t - 2*Math.PI/3) +0.5) ));
		if ( 0 <= t && t < p/3) { 
			r = - t * m + amp;
			g = m*t;
			b = 0;
			System.out.println("1");
		}
		else if (p/3 <= t && t < 2*p/3 ) {
			r = 0;
			g = -m * t + 2*amp;
			b = m* (t - p/3);
			System.out.println("2");

		}
		else if (t >= p/3) {
			r= m * (t-2*p/3);
			g = 0;
			b = -m*t + 3*amp;
			System.out.println("3");

		}
		
		
//		r = 255 * r/p;
//		g = 255 * g/p;
//		b = 255 * b/p;
//		if (t == 10) 
			System.out.println(r+","+g+","+b);

		marker.noFill();
		marker.strokeWeight(2);
		marker.stroke(r,g,b);
	}*/
	
	
	
}
