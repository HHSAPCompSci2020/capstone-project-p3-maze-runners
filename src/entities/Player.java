package entities;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import processing.core.PImage;

public class Player extends Creature{

	public Player(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.maxHealth = 5;
		health = maxHealth;
		imageName = "player.png";
	}
	
	public void reduceHealthBy(int damage) {
		
		if (DrawingSurface.playerDmgCooldown== 0) {
			health -= damage;
			DrawingSurface.playerDmgCooldown = 60;
			if (health <= 0) {
				health = 0;
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
				//do DrawingSurface.spawnNewPlayer();
			}
			
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
	

	@Override
	public void attack(Player p) {
		// TODO Auto-generated method stub
		System.out.println("Player attacked player");
		p.reduceHealthBy(1);
	}
	public int getHealth() {
		return this.health;
	}
	
}
