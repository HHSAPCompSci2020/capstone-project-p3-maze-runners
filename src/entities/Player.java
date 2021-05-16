package entities;

import Maze.DrawingSurface;
import processing.core.PImage;

public class Player extends Creature{

	public Player(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.maxHealth = 3;
		health = maxHealth;
		imageName = "player.png";
	}
	
	public void takeDamage(int damage) {
		
		if (DrawingSurface.playerDmgCooldown== 0) {
			health--;
			DrawingSurface.playerDmgCooldown = 60;
			if (health <= 0) {
				health = 0;
				//do DrawingSurface.spawnNewPlayer();
			}
		}
	}

	@Override
	public void attack(Player p) {
		// TODO Auto-generated method stub
		System.out.println("Player attacked player");
		p.takeDamage(1);
	}
	public int getHealth() {
		return this.health;
	}
	
}
