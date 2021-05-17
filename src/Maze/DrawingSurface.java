package Maze;


import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.Creature;
import entities.enemies.TimingTrap;
import processing.core.PApplet;

import entities.*;

public class DrawingSurface extends PApplet {
	//
	//
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	private static final int WALL_THICKNESS = 7;
	

	private Rectangle screenRect;

	private Player player;
	private TimingTrap timingTrap;
	private Heal heals;
	private ArrayList<Shape> obstacles;
	private ArrayList<Creature> creatures;
	private ArrayList<Integer> keys;
	private ArrayList<Enemy> enemies;

	//Our fields
	private ArrayList<Maze> allMazes;
	private Maze maze0, maze1, txtMaze0;//to be implemented
	private static long iterations = 0;

	
	
	
	/**mazeSelected is the index of allMazes that will be loaded on screen
	 * 
	 */
	public int mazeSelected = 0;

	/**
	 * mazeChangeCooldown is the number of draw() method calls that pass between each maze change.
	 * So at mazeChangeCooldown = 30, you can change the maze at most once per 30 frames or once per 0.5 seconds
	 */
	private int mazeChangeCooldown;
	
	public static int playerDmgCooldown;

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
		int w =10;
		maze1.addWall(new Rectangle(50,10, 700,w));

		maze1.addWall(new Rectangle(200,150,400,w));
		maze1.addWall(new Rectangle(600,150,w,70));
		maze1.addWall(new Rectangle(700,150,w,300));
		maze1.addWall(new Rectangle(500,300,200,w));
		maze1.addWall(new Rectangle(300,300,100,w));
		maze1.addWall(new Rectangle(400,300,w,100));
		maze1.addWall(new Rectangle(400,400,200,w));
		maze1.addCreature(new TimingTrap(loadImage("data//spike.png"), 100,50, 50, 50) );
		//maze1.addAbility


		allMazes.add(maze1);
		
//		horizontal lines
		maze0.addWall(new Rectangle(0,0,1000,WALL_THICKNESS ));
		maze0.addWall(new Rectangle(0,595,1000,WALL_THICKNESS ));
		maze0.addWall(new Rectangle(35, 35, 250, WALL_THICKNESS ));
		maze0.addWall(new Rectangle(315, 35, 200, WALL_THICKNESS ));
		maze0.addWall(new Rectangle(545, 35, 212, WALL_THICKNESS ));
		maze0.addWall(new Rectangle(580,200,220,WALL_THICKNESS ));
		maze0.addWall(new Rectangle(545, 235, 205, WALL_THICKNESS ));
		maze0.addWall(new Rectangle(545, 442, 300, WALL_THICKNESS ));
		maze0.addWall(new Rectangle(440,200,105,WALL_THICKNESS ));
		maze0.addWall(new Rectangle(440,235,105,WALL_THICKNESS ));
		maze0.addWall(new Rectangle(35, 563, 280, WALL_THICKNESS));
		maze0.addWall(new Rectangle(345, 563, 200, WALL_THICKNESS));
		maze0.addWall(new Rectangle(576, 563, 190, WALL_THICKNESS));
		maze0.addWall(new Rectangle(70, 530, 500, WALL_THICKNESS));
		maze0.addWall(new Rectangle(600, 530, 200, WALL_THICKNESS));
		maze0.addWall(new Rectangle(105, 70, 50, WALL_THICKNESS));
		maze0.addWall(new Rectangle(185, 70, 65, WALL_THICKNESS));
		maze0.addWall(new Rectangle(77, 140, 140 , WALL_THICKNESS));
		maze0.addWall(new Rectangle(140, 105, 140, WALL_THICKNESS ));
		maze0.addWall(new Rectangle(103, 175, 182, WALL_THICKNESS ));
		
//		vertical lines
		maze0.addWall(new Rectangle(0,0,WALL_THICKNESS ,1000));
		maze0.addWall(new Rectangle(795,0,WALL_THICKNESS ,200));
		maze0.addWall(new Rectangle(795,450,WALL_THICKNESS ,250));
		maze0.addWall(new Rectangle(35, 35, WALL_THICKNESS , 430));
		maze0.addWall(new Rectangle(35, 495, WALL_THICKNESS , 68));
		maze0.addWall(new Rectangle(750, 42, WALL_THICKNESS , 128));
		maze0.addWall(new Rectangle(545, 35, WALL_THICKNESS , 407));
		maze0.addWall(new Rectangle(750, 235, WALL_THICKNESS , 165));
		maze0.addWall(new Rectangle(508, 42, WALL_THICKNESS , 128));
		maze0.addWall(new Rectangle(315, 42, WALL_THICKNESS , 128));
		maze0.addWall(new Rectangle(278, 42, WALL_THICKNESS , 132));
		maze0.addWall(new Rectangle(70, 330, WALL_THICKNESS, 200));
		maze0.addWall(new Rectangle(70, 70, WALL_THICKNESS, 230));
		maze0.addWall(new Rectangle(105, 77, WALL_THICKNESS, 35));

//		Timing Traps
		maze0.addCreature(new TimingTrap(loadImage("data//spike.png"), 285,50, 30, 25));
		maze0.addCreature(new TimingTrap(loadImage("data//spike.png"), 515,50, 30, 25));
		maze0.addCreature(new TimingTrap(loadImage("data//spike.png"), 440,208, 30, 26));
		maze0.addCreature(new TimingTrap(loadImage("data//spike.png"), 205,570, 30, 25));
		maze0.addCreature(new TimingTrap(loadImage("data//spike.png"), 546,570, 30, 25));
		
		allMazes.add(maze0);
		
		txtMaze0 = new Maze(this, "data//txtmaze0.txt", 15, 15);
		allMazes.add(txtMaze0);
		System.out.println("3rd maze in allMazes:");
		txtMaze0.printCharArray(txtMaze0.grid);
		
		
		
		spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);
		spawnNewAbility();
		spawnNewEnemy(timingTrap);
	}

	public void spawnNewPlayer(int x, int y) {
		player = new Player(loadImage("data//player.png"), x,y, 25, 25);
	}
	public void spawnNewPlayer() {
		player = new Player(loadImage("data//player.png"), 8,10, 25, 25);
		//use forward slash for folders outside src I guess?
		//
		//
	}

	public void spawnNewEnemy(Enemy enemy) {

		//		enemies.add(new TimingTrap(loadImage("data//player.png"), 100,50, 50, 50) );
		//enemies.add(e);


	}

	public void spawnNewAbility() {
		int x,y;
		//x=x(Math.RANDOM); maybe i should just hard code the spots where the ability spawn
		heals = new Heal(loadImage("data//heal.png"), 250, 250, 40, 60);

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
		if (playerDmgCooldown > 0) {
			playerDmgCooldown--;
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
		
		//Check if any creature is touching Player. If yes, make player take damage
		for (Creature c: thisMaze.getCreatures()) {
			c.draw(this);
			if (c.touchingCreature(player)) {
				if (c instanceof Heal) {
					c.attack(player);
					System.out.println("Should be healing");
				}
				if (c instanceof Enemy) {
					c.attack(player);
					
				}
				
				
				
			}
			
		}
		if (player.getHealth() <= 0) {
			spawnNewPlayer(thisMaze.playerStartX, thisMaze.playerStartY);
		}
		
		
		player.draw(this);
		
		
		
		//		timingTrap.draw(this);
		

		String healthStr = "Health: " + player.getHealth();
		this.textSize(20);
		this.stroke(0);
		this.text(healthStr, DRAWING_WIDTH - 100, DRAWING_HEIGHT - 24);
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
		 

		if (isPressed(KeyEvent.VK_M)) { //TOGGLE MAZE
			String s = "" + toggleMaze();
			System.out.println("Maze Selected: "+s);
		}
		//make Creatures act
		player.act(obstacles);

		if (!screenRect.intersects(player))
			spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);
		
		
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
	
	
	/**
	 * Change the current maze on the screen from allMazes.get(i) to allMazes.get(i+1) (or allMazes.get(0) if i+1 > allMazes.size() )
	 * @return the index of the new Maze selected that will be shown on the screen
	 */
	public int toggleMaze() {
		if (mazeChangeCooldown == 0) {
			mazeChangeCooldown = 30;
			mazeSelected++;
			if (mazeSelected >= allMazes.size()) {
				mazeSelected = 0;
			}
			System.out.println("Maze Selected: "+mazeSelected);
			spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY); 
		}
		return mazeSelected;
	}


}

