package entities;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

public class Player extends Creature{
//	private int lives;
	public static final double MAX_SPEED = 2;
	public static final double DAMAGED_SPEED = 0.3;
	private static final int knockback = 9;
	
	public Player(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.maxHealth = 3;
		health = maxHealth;
		imageName = "player.png";
		
	}
	
	public void reduceHealthBy(int damage) {
		
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
	
	/**
	 * 
	 * @param damage How much damage to take (int)
	 * @param otherX x coordinate of the other Creature
	 * @param otherY y coordinate of the other Creature
	 * @param otherWidth width of the other Creature
	 * @param otherHeight height of the other Creature
	 */
	public void reduceHealthBy(int damage, double otherX, double otherY, double otherWidth, double otherHeight) {
		
		if (DrawingSurface.playerDmgCooldown== 0) {
			health -= damage;
			DrawingSurface.playerDmgCooldown = 60;
			if (health <= 0) {
				health = 0;
				DrawingSurface.lives -= 1;
			}
			
			
			
			//get Knocked back
			if (otherX <= this.x && this.x <= otherX+otherWidth) {
				if (y >= otherY) {
					moveBy(0, knockback);
				}
				else {
					moveBy(0, -knockback);
				}
			}
			
			if (otherY <= this.y && this.y <= otherY+otherHeight) {
				if (x >= otherX) {
					moveBy(knockback, 0);
				}
				else {
					moveBy(-knockback, 0);
				}
			}
			
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
		
		marker.popStyle();
		super.draw(marker);;
		
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return speed;
	}
}
