package entities;

import Maze.*;
public class Ability {
	private int uses;
	private boolean pickedUp;
	
	public Ability() {
		uses=0;
		pickedUp=false;
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
