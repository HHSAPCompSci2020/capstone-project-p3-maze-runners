package Maze;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import entities.enemies.TimingTrap;
import processing.core.PApplet;

import entities.*;

@SuppressWarnings("serial")
public class DrawingSurface extends PApplet {
	//
	//
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;

	private static long iterations = 0;
	public static int lives = 3;
	private static boolean debugEnabled = false;

	private Rectangle screenRect;

	private Player player;

	private ArrayList<Shape> obstacles;
	private ArrayList<Integer> keys;
	private ArrayList<Maze> allMazes;

	private Maze maze0, maze1, maze2, maze3, newMaze0, newMaze1;

	private int toggleDebugCooldown;

	/**
	 * mazeSelected is the index of allMazes that will be loaded on screen
	 * 
	 */
	public int mazeSelected = 0;

	/**
	 * mazeChangeCooldown is the number of draw() method calls that pass between
	 * each maze change. So at mazeChangeCooldown = 30, you can change the maze at
	 * most once per 30 frames or once per 0.5 seconds
	 */
	private int mazeChangeCooldown;
	private int otherCooldown = 0;
	private int respawnCooldown = 0;

	/**
	 * how many invincibility frames the player has. Player cannot take a damage for
	 */
	public static int playerDmgCooldown;
	public static final int DMG_MAX_COOLDOWN = 30;
	/**
	 * how many abilities are to be spawned randomly in the maze
	 */
	public int abilityNum;
	private boolean gameComplete = false;

	/*
	 * ------------------------Constructor------------------------
	 */

	public DrawingSurface() {
		super();
		keys = new ArrayList<Integer>();
		screenRect = new Rectangle(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		obstacles = new ArrayList<Shape>();

		String instructions = "Use arrow keys or WASD to move. \n"
				+ "Press 'm' to toggle which Maze is on screen, which only works every half second";
		System.out.println(instructions);
		allMazes = new ArrayList<Maze>();

		maze2 = new Maze(this, "data//maze2.txt", 15, 15);
//		System.out.println("3rd maze in allMazes:");
//		maze2.printCharArray(maze2.getGrid());

		maze3 = new Maze(this, "data//maze3.txt", 15, 15);
//		maze2.printCharArray(maze3.getGrid());

		newMaze0 = new Maze(this, "data//newmaze0.txt", 9, 9);
		newMaze1 = new Maze(this, "data//newmaze1.txt", 12, 12);

		// add each maze to ArrayList<Maze> allMazes
//		allMazes.add(maze0);
//		allMazes.add(maze1);

		allMazes.add(newMaze0);
		allMazes.add(newMaze1);

		allMazes.add(maze2);
		allMazes.add(maze3);

//		DEBUG PRINT TO BE REMOVED
		{
			Maze thisMaze = allMazes.get(mazeSelected);
			System.out.println("Maze: " + (mazeSelected));
			thisMaze.printCharArray(thisMaze.getGrid());
		}
		spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);
	}

	/*
	 * ------------------------PApplet Methods------------------------
	 */

	// The statements in the setup() function
	// execute once when the program begins
	public void setup() {
		// size(0,0,PApplet.P3D);
	}

	// The statements in draw() are executed until the
	// program is stopped. Each statement is executed in
	// sequence and after the last line is read, the first
	// line is executed again.
	public void draw() {
		iterations++;

		if (mazeChangeCooldown > 0)
			mazeChangeCooldown--;
		if (playerDmgCooldown > 0)
			playerDmgCooldown--;
		if (toggleDebugCooldown > 0)
			toggleDebugCooldown--;
		if (otherCooldown > 0)
			otherCooldown--;

		if (respawnCooldown > 0) {
			respawnCooldown--;
			player.setSpeed(0.25);
		} else {
			player.setSpeed(Player.MAX_SPEED);
		}

		// Loading walls
		Maze thisMaze = allMazes.get(mazeSelected);

		// SETUP the walls and enemies stored in each Maze here:
		obstacles = new ArrayList<Shape>();
		spawnWalls(obstacles, thisMaze);
		// drawing stuff

		background(128, 212, 255);

		pushMatrix();

		int width = getWidth();
		int height = getHeight();

		float ratioX = (float) width / DRAWING_WIDTH;
		float ratioY = (float) height / DRAWING_HEIGHT;

		scale(ratioX, ratioY);

		fill(100);

		for (Shape s : obstacles) {
			if (s instanceof Rectangle) {
				Rectangle r = (Rectangle) s;
				rect(r.x, r.y, r.width, r.height);
			}
		}

		checkEnemyCollisions(thisMaze);
		checkAbilityCollisions(thisMaze);
		checkExitCollision(thisMaze);

		// If player's health is less then 1, player respawns
		if (player.getHealth() <= 0 && lives > 0) {
			spawnNewPlayer(thisMaze.playerStartX, thisMaze.playerStartY);
		}

		player.draw(this);

//	Text that Displays the players health
		this.fill(0);
		String healthStr = "Lives: " + lives;
		healthStr += "\nHealth: " + player.getHealth();
		this.textSize(20);
		this.text(healthStr, DRAWING_WIDTH - 100, DRAWING_HEIGHT - 50);
		this.fill(0);

//   End of game scren
		if (gameComplete) {

			pushStyle();
			textAlign(CENTER);
			fill(255);
			this.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
			fill(0);
			textSize(40);
			String s = "Thanks For Playing!\n" + "Developed By:\n" + "Christopher Lew\n" + "Joseph Huang\n"
					+ "Lakshya Shrivatava\n";
			this.text(s, DRAWING_WIDTH / 2, DRAWING_HEIGHT / 3);
			popStyle();
		}
		popMatrix();

//		debug mode: to be deleted or commented in final version
		if (debugEnabled && !gameComplete) {
			pushStyle();
			String debugStr = "Debug On\n" + "= Toggle debug\n" + "m Go to next maze\n" + "k Increase Health\n"
					+ "l Increase Lives";
			fill(32);
			textSize(16);
			this.text(debugStr, DRAWING_WIDTH - 200, 20);

		}

		if (debugEnabled) {
			if (isPressed(KeyEvent.VK_M)) { // TOGGLE MAZE

				String s = "" + toggleMaze();
				System.out.println("Maze Selected: " + s);
			}
			if (isPressed(KeyEvent.VK_K) && otherCooldown == 0) {
				player.healBy(1);
				otherCooldown = 20;
			}
			if (isPressed(KeyEvent.VK_L) && lives < 100 && otherCooldown == 0) {
				lives++;
				otherCooldown = 30;

			}
		}

		// Player movement modification

		if (isPressed(KeyEvent.VK_LEFT))
			player.moveBy(-1, 0);
		// mario.walk(-1);
		if (isPressed(KeyEvent.VK_RIGHT))
			player.moveBy(1, 0);
		// mario.walk(1);
		if (isPressed(KeyEvent.VK_UP))
			player.moveBy(0, -1);
		// mario.jump();
		if (isPressed(KeyEvent.VK_DOWN))
			player.moveBy(0, 1);

		// Chris: you can also use WASD to move
		if (isPressed(KeyEvent.VK_A))
			player.moveBy(-1, 0);
		// mario.walk(-1);
		if (isPressed(KeyEvent.VK_D))
			player.moveBy(1, 0);
		// mario.walk(1);
		if (isPressed(KeyEvent.VK_W))
			player.moveBy(0, -1);
		// mario.jump();
		if (isPressed(KeyEvent.VK_S))
			player.moveBy(0, 1);
		if (isPressed(KeyEvent.VK_EQUALS)) {
			if (toggleDebugCooldown == 0) {
				debugEnabled = !debugEnabled;
				toggleDebugCooldown = 60;
			}
		}

		// make Creatures act
		player.act(obstacles);

		if (!screenRect.intersects(player))
			spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);

		if (lives <= 0) {
			fill(0);
			textSize(48);

			this.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
			fill(255, 0, 0);
			textAlign(CENTER);
			this.text("YOU DIED", DRAWING_WIDTH / 2, DRAWING_HEIGHT / 2);
		}
	}

	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while (keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	/*
	 * ------------------------Our Methods------------------------
	 */

	public void spawnNewPlayer(int x, int y) {
		respawnCooldown = 60;
		player = new Player(loadImage("data//player.png"), x, y, 25, 25);
	}

	private void spawnWalls(ArrayList<Shape> obstacles, Maze thisMaze) {
		for (Shape s : thisMaze.getWalls()) {
			obstacles.add(s);
		}
	}

	private void checkExitCollision(Maze thisMaze) {
		for (int i = 0; i < thisMaze.getExits().size(); i++) {
			Exit e = thisMaze.getExits().get(i);
			e.draw(this);
			if (e.touchingCreature(player)) {
				toggleMaze();
			}
		}
	}

	private void checkEnemyCollisions(Maze thisMaze) {
		// Check if any creature is touching Player. If yes, make player take damage
		for (int i = 0; i < thisMaze.getEnemies().size(); i++) {
			Enemy e = thisMaze.getEnemies().get(i);
			e.draw(this);
			if (e.touchingCreature(player)) {
				if (e instanceof Enemy) {
					e.attack(player);

				}
				// ADD NEW ENEMIES ATTACK METHODS HERE OR MODIFY attack() METHOD

			}

		}
	}

	private void checkAbilityCollisions(Maze thisMaze) {
		for (int i = 0; i < thisMaze.getAbilities().size(); i++) {
			Ability ab = thisMaze.getAbilities().get(i);
			ab.draw(this);
			if (ab.touchingCreature(player)) {
				if (ab instanceof Heal) {
					player.healBy(1);
					System.out.println("Should be healing");
					ab.removeSelfFromMaze(thisMaze, i);
				}
			}

		}
	}

	/**
	 * Change the current maze on the screen from allMazes.get(i) to
	 * allMazes.get(i+1) (or allMazes.get(0) if i+1 > allMazes.size() )
	 * 
	 * @return the index of the new Maze selected that will be shown on the screen
	 */
	public int toggleMaze() {
		System.out.println("maze change CD" + mazeChangeCooldown);
		if (mazeChangeCooldown == 0) {
			mazeChangeCooldown = 30;
			mazeSelected++;
			if (mazeSelected < allMazes.size()) {
				gameComplete = false;
			} else {
				gameComplete = true;

			}

			if (mazeSelected < allMazes.size())
				spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);
			if (mazeSelected >= allMazes.size()) {
				gameComplete = true;
				mazeSelected = 0;
			}
		}
		return mazeSelected;
	}

	public static long getIterations() {
		return iterations;
	}
}