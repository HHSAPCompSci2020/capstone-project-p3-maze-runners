package entities;

import java.awt.Shape;
import java.util.ArrayList;

import Maze.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class represents the user controlled player on the screen
 * 
 * @author Christopher Lew
 *
 */
@SuppressWarnings("serial")
public class Player extends Entity {
//	private int lives;
	/**
	 * Walking speed of the Player
	 */
	public static double WALK_SPEED = 2;
	/**
	 * Running speed of the Player
	 */
//	public static final double RUN_SPEED = 4;
	/**
	 * Speed of the Player after taking Damage
	 */
	public static final double DAMAGED_SPEED = 0.8;
	/**
	 * Represents whether the Player is invincible(true) or not(false)
	 */
	public boolean invincible = false;
	/**
	 * Represents whether the Player is visible to enemies in the mazes
	 */
	public static boolean visibleByEnemies = true;
	private PImage flippedImage;

	
	public boolean visible=true;
	
	/**
	 * Constructs this Player at position x,y with the given image and user provided
	 * dimensions
	 * 
	 * @param img    the PImage that is drawn for this Creature
	 * @param x      the top left x coordinate
	 * @param y      the top left x coordinate
	 * @param width  the width of this Creature
	 * @param height the height of this Creature
	 */
	public Player(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.maxHealth = 3;
		health = maxHealth;
		imageName = "luigi.png";
	}

	/**
	 * Constructs this Player at position x,y with the given image and user provided
	 * dimensions
	 * 
	 * @param img     the PImage that is drawn for this Creature
	 * @param x       the top left x coordinate
	 * @param y       the top left x coordinate
	 * @param width   the width of this Creature
	 * @param height  the height of this Creature
	 * @param flipped the flipped image of the provided image
	 */
	public Player(PImage img, int x, int y, int width, int height, PImage flipped) {
		super(img, x, y, width, height);
		super.maxHealth = 3;
		health = maxHealth;
		imageName = "luigi.png";
		flippedImage = flipped;
	}

//	/**
//	 * reduces the health of the player
//	 * 
//	 * @param damage the amount of damage to be taken.
//	 */
//	public void reduceHealthBy(int damage) {
//		if (!this.visibleByEnemies)
//			return;
//		if (!invincible) {
//			if (DrawingSurface.playerDmgCooldown == 0) {
//				health -= damage;
//				DrawingSurface.playerDmgCooldown = DrawingSurface.DMG_MAX_COOLDOWN;
//				if (health <= 0) {
//					health = 0;
//					DrawingSurface.lives -= 1;
//					// do DrawingSurface.spawnNewPlayer();
//				}
//			}
//		}
//	}

	/**
	 * reduces the health of the player
	 * 
	 * @param damage      How much damage to take (int)
	 * @param otherX      x coordinate of the other Creature
	 * @param otherY      y coordinate of the other Creature
	 * @param otherWidth  width of the other Creature
	 * @param otherHeight height of the other Creature
	 */
	public void reduceHealthBy(int damage, double otherX, double otherY, double otherWidth, double otherHeight) {
		if (!Player.visibleByEnemies)
			return;
		if (!invincible) {
			if (DrawingSurface.playerDmgCooldown == 0) {
				health -= damage;
				DrawingSurface.playerDmgCooldown = DrawingSurface.DMG_MAX_COOLDOWN;
				if (health <= 0) {
					health = 0;
					DrawingSurface.lives -= 1;
				}
				receiveKnockback(otherX, otherY, otherWidth, otherHeight, 1.0);

			}
		}
	}
	/**
	 * reduces the health of the player
	 * 
	 * @param damage      How much damage to take (int)
	 * @param otherX      x coordinate of the other Creature
	 * @param otherY      y coordinate of the other Creature
	 * @param otherWidth  width of the other Creature
	 * @param otherHeight height of the other Creature
	 * @param kbMultiplier the multiplier for the knockback received by the Player
	 */
	public void reduceHealthBy(int damage, double otherX, double otherY, double otherWidth, double otherHeight, double kbMultiplier) {
		if (!Player.visibleByEnemies)
			return;
		if (!invincible) {
			if (DrawingSurface.playerDmgCooldown == 0) {
				health -= damage;
				DrawingSurface.playerDmgCooldown = DrawingSurface.DMG_MAX_COOLDOWN;
				if (health <= 0) {
					health = 0;
					DrawingSurface.lives -= 1;
				}
				receiveKnockback(otherX, otherY, otherWidth, otherHeight, kbMultiplier);

			}
		}
	}

//	/**
//	 * 
//	 * @param damage the amount of damage to be taken.
//	 * @param otherX x coordinate of the entity causing health lost
//	 * @param otherY y coordinate of the entity causing health lost
//	 */
//	public void reduceHealthBy(int damage, int otherX, int otherY) {
//		if (!this.visibleByEnemies)
//			return;
//		if (!invincible) {
//			health -= damage;
//			DrawingSurface.playerDmgCooldown = DrawingSurface.DMG_MAX_COOLDOWN;
//			if (health <= 0) {
//				health = 0;
//				DrawingSurface.lives -= 1;
//			}
//			if (DrawingSurface.playerDmgCooldown == 0) {
////				work normally
//			}
//		}
//	}
	

	/**
	 * @author Joseph
	 * @param amount amount of health recovered
	 */
	public void healBy(int amount) {
		health += amount;
		if (health > maxHealth)
			health = maxHealth;
	}

	/**
	 * The specifc way the Player acts, calls super classes act method
	 * 
	 * @param obstacles list of obstacles
	 */
	public void act(ArrayList<Shape> obstacles) {
		if (DrawingSurface.playerDmgCooldown == 0) {
			speed = WALK_SPEED;
		} else {
//			speed = 0.5;
		}
		super.act(obstacles);
	}

	/**
	 * Move the Entity by an x and y amount (given integers)
	 * 
	 * @param x amount to move in x direction (right positive)
	 * @param y amount to move in y direction (down positive)
	 */
	public void moveBy(int x, int y) {
		if (DrawingSurface.getRespawnTimer() != 0) {
			return;
		} else {
			super.moveBy(x, y);
		}
	}

	public char getSymbol() {
		return 'C';
	}

	/**
	 *Can be used in multiplayer, NOT USED IN THIS PROJECT
	 * @param p the other player
	 */
	public void attack(Player p) {
		System.out.println("Player attacked player");
		p.reduceHealthBy(1, x, y, width, height);
	}

	/**
	 * Returns the player's health
	 * @return player health
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 *@param marker the PApplet to which the player will be drawn
	 */
	public void draw(PApplet marker) {
		marker.pushStyle();
		if (DrawingSurface.playerDmgCooldown > 0) {
//			marker.fill(248, 44, 0);
//			marker.text("Ow!", (float) (x - width / 4), (float) (y - width / 8));

		} else {
			marker.fill(0);
			speed = WALK_SPEED;
		}
		if (DrawingSurface.playerDmgCooldown > 0) {
			speed = DAMAGED_SPEED;
			float w = (float)(width * DrawingSurface.playerDmgCooldown/DrawingSurface.DMG_MAX_COOLDOWN);
			marker.noStroke();
			marker.fill(255,80,80);
			marker.rect((float)(x), (float) (y + 1.1f*height), w, (float)height/5f);
			marker.stroke(0);
			marker.noFill();
			marker.rect((float)(x), (float) (y + 1.1f*height), (float)width, (float)height/5f);
//			System.out.println("here s = "+speed);
		} else {
			speed = WALK_SPEED;
			
		}
		if (DrawingSurface.starDuration > 0) {
			float w = (float)(width * DrawingSurface.starDuration/ (5*60));
			marker.noStroke();
			marker.fill(240,244,147);
			marker.rect((float)(x), (float) (y - height/5f), w, (float)height/5f);
			marker.stroke(0);
			marker.noFill();
			marker.rect((float)(x), (float) (y - height/5f), (float)width, (float)height/5f);
		}
		if (DrawingSurface.stealthDuration > 0) {
			float w = (float)(width * DrawingSurface.stealthDuration/ (5*60));
			marker.noStroke();
			marker.fill(240,244,147);
			marker.rect((float)(x), (float) (y - height/5f), w, (float)height/5f);
			marker.stroke(0);
			marker.noFill();
			marker.rect((float)(x), (float) (y - height/5f), (float)width, (float)height/5f);
		}
		if (DrawingSurface.playerDmgCooldown > 1) {
			Player.visibleByEnemies = false;
		}
		else {
			Player.visibleByEnemies = true;
		}

		if (!facingRight) {
			marker.image(flippedImage, (int) x, (int) y, (int) width, (int) height);
		} else {
			marker.image(image, (int) x, (int) y, (int) width, (int) height);
		}

		if (invincible) {
			marker.strokeWeight(3);
			marker.stroke(255, 255, 0);
			marker.noFill();
			marker.ellipse((float) this.getCenterX(), (float) this.getCenterY(), (float) (1.4f * width),
					(float) (1.4f * height));
		}
		marker.popStyle();

//		super.draw(marker);

	}

	/**
	 * sets the speed of the player
	 * @param speed speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Returns the current speed of the player
	 * @return the current speed of the player
	 */
	public double getSpeed() {
		return speed;
	}
}
