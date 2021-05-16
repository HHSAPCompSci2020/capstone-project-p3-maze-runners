package entities;

import processing.core.PImage;

public class Player extends Creature{

	public Player(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.maxHealth = 3;
		
	}
	
	public void takeDamage(int damage) {
		health--;
		if (health <= 0) {
			//do DrawingSurface.spawnNewPlayer();
		}
	}
}
