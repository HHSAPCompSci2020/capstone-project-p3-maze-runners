package entities.abilities;
import Maze.DrawingSurface;
import processing.core.PApplet;
import processing.core.PImage;
public class InvincibilityPrank  extends Ability{
	
	private static char sSymbol = 's';
	
	public InvincibilityPrank(PImage img, int x, int y, int width, int height) {
		super(img, x, y, width, height);
		super.uses=1;
	}

	
	/**
	 * increments usage
	 */
	public void use(PApplet marker) {
		super.use();
		
		
	}
	
	
	
	public String toString() {
		String s = "Instant Win :)";
		return s;
	}
	
	/**Draws two rectangles for the green cross
	 * @author Christopher Lew
	 * @param marker the PApplet object used for drawing
	 */
	public void draw(PApplet marker) {
		marker.pushStyle();
		marker.fill(200,200,200,160);
		marker.ellipse((float)this.getCenterX(), (float)this.getCenterY() , (float)width * 0.75f, (float)height* 0.75f);
		
		marker.fill(0, 0, 0);
		marker.noStroke();
		marker.textAlign(marker.CENTER);
		marker.textSize(20);
		marker.text('?', (float)(x+width/2), (float)(y+height/2 + 5));
//		marker.stroke(75, 232, 0);
//		marker.stroke(2);
//		marker.rect((float)x, (float)(y+height/3), (float)width , (float)height/3);
//		marker.rect((float)(x+width/3), (float)(y), (float)width/3 , (float)height);
		marker.popStyle();
		
	}
	
	
}
