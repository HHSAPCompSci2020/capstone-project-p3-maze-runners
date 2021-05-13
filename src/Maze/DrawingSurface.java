package Maze;


import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.Creature;
import processing.core.PApplet;
import processing.core.PImage;
import entities.*;

public class DrawingSurface extends PApplet {
	//
	//
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private Rectangle screenRect;

	private Creature player;
	private ArrayList<Shape> obstacles;
	private ArrayList<Creature> creatures;
	private ArrayList<Integer> keys;
	private ArrayList<Enemy> enemies;

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

	/**
	 * how many abilities are to be spawned randomly in the maze
	 */
	public int abilityNum;


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
		int w =5;
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

		spawnNewPlayer();
	}


	public void spawnNewPlayer() {
		player = new Creature(loadImage("data//player.png"), DRAWING_WIDTH/2-Creature.DEFAULT_WIDTH/2,50);
		//use forward slash for folders outside src I guess?
		//
		//
	}

	public void spawnNewEnemy(Enemy enemy) {
		//enemies.add(e);

	}

	public void spawnNewAbility() {
		int x,y;
		//x=x(Math.RANDOM); maybe i should just hard code the spots where the ability spawn
		for(int i=0; i< abilityNum; i++) {
			//Ability skills = new Ability(PImage img, x, y);
		}
		/*
		 * final int num = 0;
final JPanel pane;
Timer timer = new Timer(10, new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        num++;
        pane.repaint();
    }
});
pane = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, num, 0, null);
    }
});

timer.start();
		 */
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
		Maze thisMaze = allMazes.get(mazeSelected);
		//SETUP the walls and enemies stored in each Maze here:
		obstacles = new ArrayList<Shape>();
		for (Shape s: thisMaze.getWalls()) {
			obstacles.add(s);
		}

		//		for (Creature creature: thisMaze.getCreatures()) {
		//			creatures.add(creature);
		//			
		//			if (creature instanceof Enemy) {
		//				spawnNewEnemy((Enemy)creature); 
		//				//spawnNewEnemy() yet to be implemented
		//				//could also make this a method of the Enemy class
		//			}
		//			
		//		}


		// drawing stuff

		background(128,212,255);   

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




		player.draw(this);

		popMatrix();


		// modifying stuff

		if (isPressed(KeyEvent.VK_LEFT))
			player.moveBy(-1,0);
		//			mario.walk(-1);
		if (isPressed(KeyEvent.VK_RIGHT))
			player.moveBy(1,0);
		//			mario.walk(1);
		if (isPressed(KeyEvent.VK_UP))
			player.moveBy(0,-1);
		//			mario.jump();
		if (isPressed(KeyEvent.VK_DOWN))
			player.moveBy(0, 1);

		//Chris: you can also use WASD to move
		if (isPressed(KeyEvent.VK_A))
			player.moveBy(-1,0);
		//			mario.walk(-1);
		if (isPressed(KeyEvent.VK_D))
			player.moveBy(1,0);
		//			mario.walk(1);
		if (isPressed(KeyEvent.VK_W))
			player.moveBy(0,-1);
		//			mario.jump();
		if (isPressed(KeyEvent.VK_S))
			player.moveBy(0, 1);

		if (isPressed(KeyEvent.VK_M)) { //toggle which maze
			if (mazeChangeCooldown == 0) {
				mazeChangeCooldown = 30;
				mazeSelected++;
			}




			if (mazeSelected >= allMazes.size()) {
				mazeSelected = 0;
			}
		}

		player.act(obstacles);

		if (!screenRect.intersects(player))
			spawnNewPlayer();

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

