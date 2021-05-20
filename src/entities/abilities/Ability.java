package entities.abilities;

import Maze.*;


import entities.Entity;
import processing.core.PImage;

/**
 * 
 * @author Joseph Huang
 *
 */
public class Ability extends Entity{
	protected int uses;
	private boolean pickedUp;
	//private int locX, locY;
	private int coolDown =1;
	
	
	public Ability(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);

		pickedUp=false;
		//locX=x;
	//	locY=y;
	}
	
	public Ability(PImage img, int x, int y) {
		super(img, x, y, 40, 60);
		//uses=0;
		pickedUp=false;
	}
	/**
	 * pick up an ability if the player collides with it
	 */
	public void aquire() {
		pickedUp=true;//if player collides with an ability on the floor
	}
	/**
	 * attacks depending on the type of ability currently being used
	 * e.g. Shooting a bullet, striking once with a sword
	 */
	public void use() {
		System.out.println("Use ability!");
		uses--;
	}
	
	/**
	 * drops the weapon/ability being held on player command or when ammo is out
	 */
	public void drop() {
		if(uses==0) {
			pickedUp=false;
			//more code//visually drop the ability
		}
	}
	
	/**
	 * returns whether an ability is equipped to a player or not
	 * @return true if the ablity is currently held by the player, false if not or on the floor
	 */
	public boolean getPickedUp() {
		return pickedUp;
	}
	
	public void removeSelfFromMaze(Maze m, int index) {
		m.getAbilities().remove(index);
	}
	
	/**
	 * 
	 * @return the Ability's cooldown in seconds
	 */
	public int getCooldown() {
		return coolDown;
	}
	
	public String toString() {
		return "Ability";
	}
	
	public int getUses() {
		return uses;
	}
}
