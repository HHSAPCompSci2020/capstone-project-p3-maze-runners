package Maze;


import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.Player;
import processing.core.PApplet;

import entities.*;

public class DrawingSurface extends PApplet {

	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private Rectangle screenRect;

	private Player mario;
	private ArrayList<Shape> obstacles;

	private ArrayList<Integer> keys;
	
	//Our fields
	private Maze maze1;//to be implemented
	private static long iterations = 0;
	
	

	public DrawingSurface() {
		super();
		keys = new ArrayList<Integer>();
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Shape>();
		obstacles.add(new Rectangle(200,400,400,50));
		obstacles.add(new Rectangle(0,250,100,50));
		obstacles.add(new Rectangle(700,250,100,50));
		obstacles.add(new Rectangle(375,300,50,100));
		obstacles.add(new Rectangle(300,250,200,50));
		
		maze1 = new Maze();
		maze1.add(new Rectangle(200,400,400,50));
		
		maze1.add(new Rectangle(0,250,100,50));
		maze1.add(new Rectangle(700,250,100,50));
		maze1.add(new Rectangle(375,300,50,100));
		maze1.add(new Rectangle(300,250,200,50));
		
		
		spawnNewMario();
	}


	public void spawnNewMario() {
		mario = new Player(loadImage("notmario.png"), DRAWING_WIDTH/2-Player.MARIO_WIDTH/2,50);
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		//size(0,0,PApplet.P3D);
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		if (iterations%10 == 0)
//			System.out.println("iteration: "+iterations);
		iterations++;
		
		//Loading walls 
		
		
		
		
		// drawing stuff
		
		background(0,255,255);   

		pushMatrix();

		int width = getWidth();
		int height = getHeight();

		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;

		scale(ratioX, ratioY);

		fill(100);
		for (Shape s : obstacles) {
			if (s instanceof Rectangle) {
				Rectangle r = (Rectangle)s;
				rect(r.x,r.y,r.width,r.height);
			}
		}


		mario.draw(this);

		popMatrix();

		
		// modifying stuff

		if (isPressed(KeyEvent.VK_LEFT))
			mario.moveBy(-1,0);
//			mario.walk(-1);
		if (isPressed(KeyEvent.VK_RIGHT))
			mario.moveBy(1,0);
//			mario.walk(1);
		if (isPressed(KeyEvent.VK_UP))
			mario.moveBy(0,-1);
//			mario.jump();
		if (isPressed(KeyEvent.VK_DOWN))
			mario.moveBy(0, 1);
		
		//Chris: you can also use WASD to move
		if (isPressed(KeyEvent.VK_A))
			mario.moveBy(-1,0);
//			mario.walk(-1);
		if (isPressed(KeyEvent.VK_D))
			mario.moveBy(1,0);
//			mario.walk(1);
		if (isPressed(KeyEvent.VK_W))
			mario.moveBy(0,-1);
//			mario.jump();
		if (isPressed(KeyEvent.VK_S))
			mario.moveBy(0, 1);
		

		mario.act(obstacles);

		if (!screenRect.intersects(mario))
			spawnNewMario();

//		//draw gridLines but they dont scale with window
//
//		for (int i = 1; i <= width/100; i++) {
//			pushStyle();
//			this.color(0,0,0, 16);
//			//horizontal lines
//			line( 0, 100*i, DRAWING_WIDTH, 100*i);
//			//vertical lines
//			popStyle();
//		}
//		for (int i = 1; i <= DRAWING_WIDTH/100; i++) {
//			pushStyle();
//			this.color(0,0,0, 200);
//			//horizontal lines
//			line( 100*i, 0, 100*i , DRAWING_HEIGHT);
//			//vertical lines
//			popStyle();
//
//		}
	}


	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}


}

