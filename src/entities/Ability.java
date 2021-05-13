package entities;

import Maze.*;
import processing.core.PImage;
public class Ability extends MovingImage{
	private int uses;
	private boolean pickedUp;
	int locX, locY;
	
	
	public Ability(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);

		uses=0;
		pickedUp=false;
		locX=x;
		locY=y;
	}
	
	public Ability(PImage img, int x, int y) {
		super(img, x, y, 40, 60);
		uses=0;
		pickedUp=false;
	}
	
	public void aquire() {
		pickedUp=true;//if player collides with an ability on the floor
	}
	/**
	 * attacks depending on the type of ability currently being used
	 * e.g. Shooting a bullet, striking once with a sword
	 */
	public void attack() {
		//code
		uses--;
	}
	
	/**
	 * drops the weapon/ability being held on player command or when ammo is out
	 */
	public void drop() {
		if(uses==0) {
			pickedUp=false;
			//more code
		}
	}
	
	/**
	 * returns whether an ability is equipped to a player or not
	 * @return true if the ablity is currently held by the player, false if not or on the floor
	 */
	public boolean getPickedUp() {
		return pickedUp;
	}
}
