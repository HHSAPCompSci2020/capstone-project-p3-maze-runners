package entities;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

public class Player extends Creature{
//	private int lives;
	public static final double MAX_SPEED = 2;
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
	 * 
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
	
	public void healBy(int amount) {
		health+= amount;
		if(health>maxHealth)
			health=maxHealth;
	}
	
	public void act(ArrayList<Shape> obstacles) {
		if (DrawingSurface.playerDmgCooldown == 0) {
			speed= MAX_SPEED;
		}
		else {
//			speed = 0.5;
		}
		super.act(obstacles);
	}
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
			speed = DAMAGED_SPEED;
			
		}
		else {
			marker.fill(0);
			speed = MAX_SPEED;
		}
		
		
		int a = 1;
		
		
		
		
		if (!facingRight) {
			marker.image(flippedImage, (int) x, (int) y, (int) width, (int) height);
		}
		else {
			marker.image(image, (int) x, (int) y, (int) width, (int) height);
		}
		
		if (invincible) {
			long t = DrawingSurface.getIterations() % 300;
			float r,g,b;
			
			
			double B = 2* Math.PI / 30;
			
//			
//			r = (float)(128 * (Math.sin( B * (t - 0) +0.5) ));
//			g = (float)(128 * (Math.sin( B * (t - Math.PI/3) +0.5) ));
//			b = (float)(128 * (Math.sin( B * (t - 2*Math.PI/3) +0.5) ));
			if ( 0 <= t && t < 10) {
				r = -t;
			}
			
			r =  Math.max(0,  Math.abs(t) );
			g =  Math.max(0,  Math.abs(t) );
			
			
			 r= g = b = 255;
			marker.noFill();
			marker.strokeWeight(2);
			marker.stroke(r,g,b);
			marker.ellipse((float)x, (float)y, (float)width, (float)height);
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
	
	
	
	
	
	
}
