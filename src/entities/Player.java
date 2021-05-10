package entities;

import processing.core.PImage;

public class Player extends Creature{

	public Player(PImage img, int x, int y) {
		super(img, x, y);
		super.maxHealth = 3;
		
	}

}
