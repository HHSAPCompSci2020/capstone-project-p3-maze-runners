package entities;
import processing.core.PImage;

public class FireAbility extends Ability{
	private double xVelocity, yVelocity; 
	protected double speed; //higher speeds mean you move faster

public FireAbility(PImage img, int x, int y, int width, int height) {
	super(img, x, y, width, height);
	xVelocity = 0;
	yVelocity = 0;
	speed=2.5;
	super.uses=10;
}

public void use() {
super.use();
if(super.uses>0) {
	//moveBy();

}
}

public void moveBy(int x, int y) {
	if (xVelocity <= 10 && xVelocity >= -10) {
		xVelocity += speed*x;
	}
	if (yVelocity <= 10 && yVelocity >= -10) {
		yVelocity += speed*y;
	}
}

}
