package entities;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

public class Player extends Creature{
//	private int lives;
	
	public Player(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.maxHealth = 5;
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
	}
	public void act(ArrayList<Shape> obstacles) {
		if (DrawingSurface.playerDmgCooldown == 0) {
			speed= 2.0;
		}
		else {
			speed = 0.5;
		}
		super.act(obstacles);
	}
	
	
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
					moveBy(0, 10);
				}
				else {
					moveBy(0, -10);
				}
			}
			
			if (otherY <= this.y && this.y <= otherY+otherHeight) {
				if (x >= otherX) {
					moveBy(10, 0);
				}
				else {
					moveBy(-10, 0);
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
		}
		else {
			marker.fill(0);
		}
		
		marker.popStyle();
		super.draw(marker);;
		
	}
}
