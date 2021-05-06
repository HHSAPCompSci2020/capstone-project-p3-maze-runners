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
	private ArrayList<Maze> allMazes;
	private Maze maze0, maze1;//to be implemented
	private static long iterations = 0;
	
	/**mazeSelected is the index of allMazes that will be loaded on screen
	 * 
	 */
	public int mazeSelected = 0;
	
	/**
	 * mazeChangeCooldown is the number of draw() method calls that pass between each maze change.
	 * So at mazeChangeCooldown = 30, you can change the maze at most once per 30 frames or once per 0.5 seconds
	 */
	public int mazeChangeCooldown;
	
	

	public DrawingSurface() {
		super();
		keys = new ArrayList<Integer>();
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		obstacles = new ArrayList<Shape>();
//		obstacles.add(new Rectangle(200,400,400,50));
//		obstacles.add(new Rectangle(0,250,100,50));
//		obstacles.add(new Rectangle(700,250,100,50));
//		obstacles.add(new Rectangle(375,300,50,100));
//		obstacles.add(new Rectangle(300,250,200,50));
		
		
		String instructions = "Use arrow keys or WASD to move. \n"
				+ "Press 'm' to toggle which Maze is on screen, which only works every half second";
		System.out.println(instructions);
		
		maze1 = new Maze();
		maze0 = new Maze();
		allMazes = new ArrayList<Maze>();
		int w =10;
		maze1.add(new Rectangle(50,10, 700,w));

		maze1.add(new Rectangle(200,150,400,w));
		maze1.add(new Rectangle(600,150,w,70));
		maze1.add(new Rectangle(700,150,w,300));
		maze1.add(new Rectangle(500,300,200,w));
		maze1.add(new Rectangle(300,300,100,w));
		maze1.add(new Rectangle(400,300,w,100));
		maze1.add(new Rectangle(400,400,200,w));
		
		allMazes.add(maze1);
		maze0.add(new Rectangle(200,400,400,50));
		maze0.add(new Rectangle(0,250,100,50));
		maze0.add(new Rectangle(700,250,100,50));
		maze0.add(new Rectangle(375,300,50,100));
		maze0.add(new Rectangle(300,250,200,50));
		
		allMazes.add(maze0);
		
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
		if (mazeChangeCooldown > 0) {
			mazeChangeCooldown--;
		}
		//Loading walls 
		
		obstacles = new ArrayList<Shape>();
		for (Shape s: allMazes.get(mazeSelected).getWalls()) {
			obstacles.add(s);
		}
		
		
		
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
		
		if (isPressed(KeyEvent.VK_M)) { //toggle which maze
			if (mazeChangeCooldown == 0) {
				mazeChangeCooldown = 30;
				mazeSelected++;
			}
				
			
			
			
			if (mazeSelected >= allMazes.size()) {
				mazeSelected = 0;
			}
		}
		
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

