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
	private Maze maze;
	
	

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
			mario.walk(-1);
		if (isPressed(KeyEvent.VK_RIGHT))
			mario.walk(1);
		if (isPressed(KeyEvent.VK_UP))
			mario.jump();

		mario.act(obstacles);

		if (!screenRect.intersects(mario))
			spawnNewMario();

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
