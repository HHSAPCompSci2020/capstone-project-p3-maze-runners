package entities.enemies;

import entities.Enemy;
import entities.Player;
import processing.core.PImage;

/**
 * 
 * Can be used to block a path. Unbreakable. Image TBD (brick wall?)
 * @author Lakshya Shrivastava
 *
 */
public class Blockade extends Enemy{

	
	public Blockade(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
//		symbol = 'B';
	}

	public void move() {
	}

	public void attack() {
	}

	public void reduceHealth() {
	}

	@Override
	public void attack(Player p) {
		// TODO Auto-generated method stub
		
	}

}
