package entities;
import entities.Ability;
import processing.core.PImage;
public class Heal extends Ability{
	Creature a ;
	public Heal(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
	}
	public void healing(int x) {
		//super.health+=x; this method if we make ability extend creature not moving image
	}
	public void healing2() {
		a.heal(1);
	}
}
