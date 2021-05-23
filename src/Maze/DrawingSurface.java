package Maze;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.PApplet;

import entities.*;
import entities.abilities.Ability;
import entities.abilities.Heal;
import entities.abilities.InvincibilityPrank;
import entities.abilities.Star;
import entities.abilities.Stealth;
import entities.enemies.Enemy;
import entities.enemies.Spike;

/**
 * The PApplet that contains all the mazes and other UI components
 * 
 * @author Shelby
 * modified by Christopher Lew, Lakshya Shrivastava, Joseph Huang
 *
 */
public class DrawingSurface extends PApplet {
	

	/**
	 * Default Width of the drawing
	 */
	public static final int DRAWING_WIDTH = 900;
	/**
	 * Default Height of the drawing
	 */
	public static final int DRAWING_HEIGHT = 675;
	/**
	 * The refresh rate of the program
	 */
	public static final int FPS = 60;
	/**
	 * The number of lives that the player has
	 */
	public static int lives = 5;
	/**
	 * mazeSelected is the index of allMazes that will be loaded on screen
	 * 
	 */
	public static int mazeSelected = 0;

	/**
	 * how many invincibility frames the player has. Player cannot take a damage for
	 */
	public static int playerDmgCooldown;
	/**
	 * The cooldown where the player does not take damage
	 */
	public static final int DMG_MAX_COOLDOWN = 120;
	/**
	 * how many abilities are to be spawned randomly in the maze
	 */
	public int abilityNum;
	/**
	 * 	Current duration of the star invincibility ability
	 * Duration of the star powerup
	 */
	public static int starDuration;
	/**
	 * Maximum duration of the star invincibility ability when first used
	 */
	public static int maxStarDuration;
	/**
	 * Duration of the stealth powerup
	 */
	public static int stealthDuration;
	/**
	 * Maximum duration of the stealth ability when first used
	 */
	public static int maxStealthDuration;
	/**
	 * Iterations variable starts at this value
	 */
	public static long startingIterations = -1;

	private static final long serialVersionUID = -3647651722594954917L;
	private boolean CAN_USE_WASD_AND_ARROW_KEYS_SIMULTANEOUSLY = false;
	private static long iterations = 0;
	private static boolean debugEnabled = false;
	private Rectangle screenRect;

	private static Player player;

	private ArrayList<Shape> obstacles;
	private ArrayList<Integer> keys;
	private ArrayList<Maze> allMazes;

	private Maze maze0, maze1, newmaze4, maze3, newmaze2, maze5, maze6, maze7, maze8, maze9, maze10;
	private int toggleDebugCooldown;
	private boolean usedCheats = false;
	
	

	/*
	 * mazeChangeCooldown is the number of draw() method calls that pass between
	 * each maze change. So at mazeChangeCooldown = 30, you can change the maze at
	 * most once per 30 frames or once per 0.5 seconds
	 */
	private int mazeChangeCooldown;
	private int abilityCooldown;
	private int otherCooldown = 0;
	private static int respawnCooldown = 0;

	private int prankedTime = 0;
	private Ability currentAbility = null;
	private boolean gameComplete = false;

	private long completeTime = -1;
	private String completeTimeStr;
	

	/*
	 * ------------------------Constructor------------------------
	 */
	/**
	 * DrawingSurface's constructor initializes the Mazes and adds them to the ArrayList<Maze> allMazes
	 * @author Christopher Lew
	 */
	public DrawingSurface() {
		super();
		keys = new ArrayList<Integer>();
		screenRect = new Rectangle(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
		obstacles = new ArrayList<Shape>();

		allMazes = new ArrayList<Maze>();

		// Go to loadMazes() to add new Mazes, use the exact same name style
		loadMazes();

		allMazes.add(maze0);
		allMazes.add(maze1);
		allMazes.add(newmaze2);

		allMazes.add(maze3);
		allMazes.add(newmaze4);

		allMazes.add(maze5);
		allMazes.add(maze6);
		allMazes.add(maze7);
		allMazes.add(maze8);
		allMazes.add(maze9);
		allMazes.add(maze10);
//		DEBUG PRINT TO BE REMOVED
//		{
//			Maze thisMaze = allMazes.get(mazeSelected);
////			System.out.println("Maze: " + (mazeSelected));
//			thisMaze.printCharArray(thisMaze.getGrid());
//		}
		spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);
	}

	/**Loads all mazes from text files
	 * @author Christopher Lew
	 */
	private void loadMazes() {
		maze0 = new Maze(this, "data//maze0.txt", 9, 9);
		maze1 = new Maze(this, "data//maze1.txt", 12, 12);
		newmaze4 = new Maze(this, "data//newmaze4.txt", 15, 15);
		maze3 = new Maze(this, "data//maze3.txt", 15, 17);
		newmaze2 = new Maze(this, "data//newmaze2.txt", 18, 25);

		maze5 = new Maze(this, "data//maze5.txt", 21, 15);
		maze6 = new Maze(this, "data//maze6.txt", 15, 15);
		maze7 = new Maze(this, "data//maze7.txt", 16, 9);

		maze8 = new Maze(this, "data//maze8.txt", 21, 18);
		maze9 = new Maze(this, "data//maze9.txt", 22, 16);
		maze10 = new Maze(this, "data//maze10.txt", 36, 26);
	}

	/**Reloads all objects in the Maze at the index mazeIndex
	 * @pre mazeIndex is between 0 and allMazes.size()-1
	 * @param mazeIndex
	 * @author Christopher Lew
	 */
	private void reloadMaze(int mazeIndex) {
//		Maze temp = new Maze(this, "data//maze" + mazeIndex+".txt", 25, 25);
		Maze temp = new Maze(allMazes.get(mazeSelected), this);
		allMazes.remove(mazeIndex);
		allMazes.add(mazeIndex, temp);
//		System.out.println("Maze "+mazeSelected);
	}

	/**
	 * Draws the HUD at the bottom of the screen. Displays Health, Lives, Ability (if one is picked up), and the in game timer
	 */
	private void drawHUD() {
		pushStyle();
//		Text that Displays the players health
		int bannerHeight = 50;

		this.fill(0, 0, 0, 64);
		this.noStroke();
		rect(0, DRAWING_HEIGHT - bannerHeight, DRAWING_WIDTH, bannerHeight);
		this.stroke(0);
		this.fill(0, 0, 0, 200);
		String healthStr = "";
		String livesStr = "Lives: " + lives + " ";
		String levelStr = "Level " + (mazeSelected + 1) + " ";

//			healthStr += "Health: " + player.getHealth();
		healthStr += "Health: ";
		String abilityStr = "", abilityStr2 = "";
		if (currentAbility != null) {
			this.textSize(16);
			@SuppressWarnings("unused")
			String s = "";

			if (currentAbility.getUses() == 1000) {
				s = "";
			}
			if (currentAbility.getUses() != 1000) {
				s = currentAbility.getUses() + "x ";
			}

			abilityStr += "Ability (spacebar): \n";
			abilityStr2 += currentAbility.toString() + " ";
		}
		String durationStr = "";
		if (player.invincible == true) {
//				healthStr += "star";
//				System.out.println("wow");
			durationStr += "Invincibility: " + (int) (starDuration / 60) + "." + (int) (starDuration / 6 % 10) + " s";

		}
		if (stealthDuration > 0) {
			durationStr += "Stealth: " + (int) (stealthDuration / 60) + "." + (int) (stealthDuration / 6 % 10) + "s";
		}

		this.fill(0, 0, 0);
		this.textSize(24);

		this.text(levelStr, 7, DRAWING_HEIGHT - 20);
		this.text(livesStr, 0 + 355, DRAWING_HEIGHT - 20);

		this.textSize(22);
		this.text(healthStr, 118, DRAWING_HEIGHT - 20);
		this.textSize(18);
		this.text(abilityStr, 480, DRAWING_HEIGHT - 32);
		this.text(abilityStr2, 480, DRAWING_HEIGHT - 15);
		if (durationStr.length() != 0) {
			this.textSize(22);
			this.text(durationStr, 480, DRAWING_HEIGHT - 20);
		}
		// draw heart
		float xStart, yStart;
		for (int i = 1; i <= player.getHealth(); i++) {
			xStart = 120 + i * 50;
			yStart = DRAWING_HEIGHT - 50;
			smooth();
			noStroke();
			fill(255, 0, 0);
			beginShape();
			vertex(xStart + 50, yStart + 15);
			bezierVertex(xStart + 50, yStart + -5, xStart + 90, yStart + 5, xStart + 50, yStart + 40);

			vertex(xStart + 50, yStart + 15);
			bezierVertex(xStart + 50, yStart + -5, xStart + 10, yStart + 5, xStart + 50, yStart + 40);
			endShape();
		}
		noFill();
		stroke(0);
		rect(3, DRAWING_HEIGHT - 43, 103, bannerHeight * 0.6f);
//			this.text(combined, 10, DRAWING_HEIGHT - 20);
		this.fill(0);


		String totalTimeStr = framesToHHMMSS(iterations - startingIterations);
		this.fill(255, 255, 255, 192);
		this.noStroke();
		this.rect(DRAWING_WIDTH - 100, 600, 100, 24);
		this.textAlign(DrawingSurface.RIGHT);
		this.textSize(20);
		this.fill(0);
		this.text(totalTimeStr, DRAWING_WIDTH - 10, DRAWING_HEIGHT - bannerHeight - 5);

		popStyle();
	}
	/**
	 * Convert a number of frames to a String in the format HH:MM:SS.S
	 * @author Christopher Lew
	 * @param totalFrames number of frames. Assuming running at 60 frames per second
	 * @return a String in the format HH:MM:SS.S. If hours = 0, does not display hours
	 */
	private String framesToHHMMSS(long totalFrames) {
		int wholeSeconds = (int) (totalFrames / 60) % 60;
		int tenthSeconds = (int) (totalFrames / 6 % 10);
		int minutes = (int) (totalFrames / 60 / 60 % 60);
		int hours = (int) (totalFrames / 60 / 60 / 60);
		String secondsStr, minutesStr, hoursStr;
		if (wholeSeconds < 10)
			secondsStr = "0" + wholeSeconds;
		else
			secondsStr = "" + wholeSeconds;
		if (minutes < 10)
			minutesStr = "0" + minutes;
		else
			minutesStr = "" + minutes;
		if (hours > 0)
			hoursStr = "" + hours + ":";
		else
			hoursStr = "";

//		this.rect(DRAWING_WIDTH - 10, DRAWING_HEIGHT - bannerHeight - 5, c, d);
		String totalTimeStr = hoursStr + minutesStr + ":" + secondsStr + "." + tenthSeconds + "";
		return totalTimeStr;
	}

	/*
	 * ------------------------PApplet Methods------------------------
	 */

	// The statements in the setup() function
	// execute once when the program begins
	/**
	 * The statements in the setup() function execute once when the program begins
	 */
	public void setup() {
		// size(0,0,PApplet.P3D);
	}

	// The statements in draw() are executed until the
	// program is stopped. Each statement is executed in
	// sequence and after the last line is read, the first
	// line is executed again.
	/**
	 * The statements in draw() are executed until the program is stopped. This is
	 * the method that draws all the mazes
	 */
	public void draw() {
		iterations++;
//		if (iterations == temp1+1) {
//			toggleMaze();
//		}
		if (mazeChangeCooldown > 0)
			mazeChangeCooldown--;
		if (playerDmgCooldown > 0)
			playerDmgCooldown--;
		if (toggleDebugCooldown > 0)
			toggleDebugCooldown--;
		if (otherCooldown > 0)
			otherCooldown--;
		if (abilityCooldown > 0)
			abilityCooldown--;
		if (prankedTime > 0)
			prankedTime--;

		if (respawnCooldown > 0) {
			respawnCooldown--;
			player.setSpeed(0.0);
		} else {
			player.setSpeed(Player.WALK_SPEED);
		}
		if (starDuration > 0) {
			player.invincible = true;

//			System.out.println("invincible for "+ (starDuration/60) + "more seconds");
			starDuration--;
		} else {
			player.invincible = false;
		}
		if (stealthDuration > 0) {
			Player.visibleByEnemies = false;
			stealthDuration--;
		} else {
			Player.visibleByEnemies = true;
		}

		// Loading walls
		Maze thisMaze = allMazes.get(mazeSelected);

		// SETUP the walls and enemies stored in each Maze here:
		obstacles = new ArrayList<Shape>();
		spawnWalls(obstacles, thisMaze);
		// drawing stuff

		background(162, 218, 247);

		pushMatrix();

		int width = getWidth();
		int height = getHeight();

		float ratioX = (float) width / DRAWING_WIDTH;
		float ratioY = (float) height / DRAWING_HEIGHT;

		// doesn't scale correctly
//		float ratioX2, ratioY2;
//		if (mazeSelected == 10) {
//			ratioX2 = 25f/40;
//			ratioY2 = 25f/40;
////			ratioX2 = 0.01f;
////			ratioY2 = 0.01f;
//			if (iterations %10 == 0) {
//				System.out.println("should be scaling even more");
//			}
//			scale(ratioX2, ratioY2);
//		}

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

		// Print the health, lives, ability, debug information
		drawHUD();

		// author Joseph Huang
		if (lives <= 0) {
			pushStyle();
			textAlign(CENTER);
			fill(0);
			this.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);

			fill(255, 0, 0);
			textSize(40);
			this.text("YOU DIED", DRAWING_WIDTH / 2, DRAWING_HEIGHT / 3);
			popStyle();
//			toggleBackMaze();
//			temp1 = DrawingSurface.iterations;
		}

//   End of game scren
		if (gameComplete) {
			if (completeTime == -1) {
				completeTime = iterations / 60;
				completeTimeStr = framesToHHMMSS(iterations - startingIterations);
			}

			pushStyle();
			textAlign(CENTER);
			fill(255);
			this.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
			fill(0);
			textSize(40);
			String s = "Thanks For Playing!\n" + "Developed By:\n" + "Christopher Lew\n" + "Joseph Huang\n"
					+ "Lakshya Shrivastava\n";
			this.text(s, DRAWING_WIDTH / 2, DRAWING_HEIGHT / 3);
			String cheatStr = "";
			if (usedCheats) {
				cheatStr = " (using debug cheats)";
				fill(255,0,0);
			}
			String timeStr = "Your time" + cheatStr +":\n"  + completeTimeStr ;
			this.text(timeStr, DRAWING_WIDTH / 2, 4 * DRAWING_HEIGHT / 5);

			popStyle();
		}

//		debug mode: to be deleted or commented in final version
		if (debugEnabled && !gameComplete) {
			pushStyle();
			noStroke();
			fill(0, 0, 0, 16);
			rect(DRAWING_WIDTH - 200, 0, 220, 200);

			String debugStr = "DEBUG MODE\n" + "Debug controls:\n" + "[m] Go to next maze\n" + "[k] Increase Health\n"
					+ "[l] Increase Lives \n" + "[Shift] Run fast";

			fill(32);
			textSize(16);
			this.text(debugStr, DRAWING_WIDTH - 190, 20);
			popStyle();

		}

		if (prankedTime > 0) {
			pushStyle();
			fill(0, 64, 0, 220);
			rect(0, 0, DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
			textAlign(CENTER);
			fill(255, 255, 255);
			textSize(25);
			text("Did you honestly think we would give you that?", DrawingSurface.DRAWING_WIDTH / 2,
					DrawingSurface.DRAWING_HEIGHT / 2);
			popStyle();
		}

		popMatrix();// this should be after any drawing to scale properly
		
		//Cheats you can use while in Debug mode
		if (debugEnabled) {
			if (isPressed(KeyEvent.VK_M)) { // TOGGLE MAZE
				usedCheats = true;

				@SuppressWarnings("unused")
				String s = "" + toggleMaze();
//				System.out.println("Maze Selected: " + s);
			}
			if (isPressed(KeyEvent.VK_K) && otherCooldown == 0) {
				player.healBy(1);
				otherCooldown = 20;
				usedCheats = true;

			}
			if (isPressed(KeyEvent.VK_L) && lives < 100 && otherCooldown == 0) {
				lives++;
				otherCooldown = 15;
				usedCheats = true;

			}
			if (isPressed(KeyEvent.VK_SHIFT) || isPressed(KeyEvent.SHIFT_DOWN_MASK)) {
				// System.out.println("run");

				Player.WALK_SPEED = 6;
				usedCheats = true;
			} else {
				Player.WALK_SPEED = 2;
			}
		}

		// Player movement modification

		

		if (CAN_USE_WASD_AND_ARROW_KEYS_SIMULTANEOUSLY) {
			if (isPressed(KeyEvent.VK_LEFT)) 
				player.moveBy(-1, 0);
			if (isPressed(KeyEvent.VK_A))
				player.moveBy(-1, 0);
			if (isPressed(KeyEvent.VK_RIGHT))
				player.moveBy(1, 0);
			if (isPressed(KeyEvent.VK_D))
				player.moveBy(1, 0);
			if (isPressed(KeyEvent.VK_UP))
				player.moveBy(0, -1);
			if (isPressed(KeyEvent.VK_W))
				player.moveBy(0, -1);
			if (isPressed(KeyEvent.VK_DOWN))
				player.moveBy(0, 1);
			if (isPressed(KeyEvent.VK_S))
				player.moveBy(0, 1);
		}
		else {
			if (isPressed(KeyEvent.VK_LEFT) || isPressed(KeyEvent.VK_A)) 
				player.moveBy(-1, 0);
			if (isPressed(KeyEvent.VK_RIGHT) || isPressed(KeyEvent.VK_D))
				player.moveBy(1, 0);
			if (isPressed(KeyEvent.VK_UP) || isPressed(KeyEvent.VK_W))
				player.moveBy(0, -1);
			if (isPressed(KeyEvent.VK_DOWN) || isPressed(KeyEvent.VK_S))
				player.moveBy(0, 1);
		}
		
		if (player.getXVelocity() < 0) {
			player.facingRight = false;
		} else if (player.getXVelocity() > 0) {
			player.facingRight = true;
		}

		if (currentAbility != null) {
			if (isPressed(KeyEvent.VK_SPACE)) {
				int cd = currentAbility.getCooldown();
				if (abilityCooldown == 0) {
					if (currentAbility instanceof InvincibilityPrank) {
//						System.out.println("pranked");
						InvincibilityPrank p = (InvincibilityPrank) currentAbility;
						prankedTime = 200;
						p.use();// decrements uses only
					} else if (currentAbility instanceof Star) {
						Star tempAb = (Star) currentAbility;
						tempAb.use(player);
					} else {
						currentAbility.use();
					}
					if (currentAbility.getUses() <= 0) {
						currentAbility = null;
					}

				}
				abilityCooldown = 30 * cd;
			}
		}

		if (isPressed(KeyEvent.VK_EQUALS)) {
			if (toggleDebugCooldown == 0) {
				debugEnabled = !debugEnabled;
				toggleDebugCooldown = 60;
			}
		}

		if (isPressed(KeyEvent.VK_O) && isPressed(KeyEvent.VK_P)) {
			if (toggleDebugCooldown == 0) {
				debugEnabled = !debugEnabled;
				toggleDebugCooldown = 60;
			}
		}

		// make Creatures act
		player.act(obstacles);
		for (Enemy e : thisMaze.getEnemies()) {
			e.act(obstacles);
		}
		for (Ability ab : thisMaze.getAbilities()) {
			ab.act(obstacles);
		}
		for (Exit exit : thisMaze.getExits()) {
			exit.act(obstacles);
		}

		if (!screenRect.intersects(player))
			spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);

	}

	/**
	 * Look at Processing Library Website for a detailed description.
	 */
	public void keyPressed() {
		keys.add(keyCode);
	}

	/**
	 * Look at Processing Library Website for a detailed description.
	 */
	public void keyReleased() {
		while (keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	/**
	 * Returns whether the key represented by code is pressed
	 * @param code the integer representing a keyboard key
	 * @return true if the key represented by code is pressed, false otherwise
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

	/*
	 * ------------------------Our Methods------------------------
	 */

	/**
	 * Spawns a new player
	 * @author Christopher Lew
	 * @param x the x position of the top left of the new Player
	 * @param y the y position of the top left of the new Player
	 */
	public void spawnNewPlayer(int x, int y) {
		respawnCooldown = 30;
		currentAbility = null;
//		loadMazes();	
		reloadMaze(mazeSelected);
		starDuration = 0;
		stealthDuration = 0;
		float f = 25f / 40;
		Maze thisMaze = allMazes.get(mazeSelected);
		player = new Player(loadImage("data//luigi.png"), x, y, (int) (thisMaze.getCellWidth() * f),
				(int) (thisMaze.getCellWidth() * f), loadImage("data//luigiLeft.png"));
	}

	/**
	 * Spawns the walls of the obstacles ArrayList and adds them to the current Maze
	 * @author Christopher Lew
	 * @param obstacles list of o
	 * @param thisMaze
	 */
	private void spawnWalls(ArrayList<Shape> obstacles, Maze thisMaze) {
		for (Shape s : thisMaze.getWalls()) {
			obstacles.add(s);
		}
	}

	/**
	 * Check whether the player is at the exit
	 * 
	 * @author Lakshya
	 * @param thisMaze the current Maze that holds the exit
	 */
	private void checkExitCollision(Maze thisMaze) {
		for (int i = 0; i < thisMaze.getExits().size(); i++) {
			Exit e = thisMaze.getExits().get(i);
			e.draw(this);
			if (e.touchingCreature(player)) {
				toggleMaze();
			}
		}
	}

	/**
	 * Checks collision between the Player and every Enemy, also each Enemy with
	 * every other Enemy
	 * 
	 * @author Christopher Lew
	 * @param thisMaze
	 */
	private void checkEnemyCollisions(Maze thisMaze) {
		// Check if any creature is touching Player. If yes, make player take damage
		for (int i = 0; i < thisMaze.getEnemies().size(); i++) {
			Enemy e = thisMaze.getEnemies().get(i);
			e.draw(this);
			if (e.touchingCreature(player)) {
				if (e instanceof Enemy) {
//					e.attack(player);
					e.attack(player, 1 / getCellRatio());
				}
				if (player.invincible) {
					if (e.canDie()) {
						e.removeSelfFromMaze(thisMaze, i);
					}

				}

				// MODIFY Enemies' attack() METHOD

			}

			// Make sure enemies don't run into each other
			for (int j = 0; j < thisMaze.getEnemies().size(); j++) {
				Enemy e2 = thisMaze.getEnemies().get(j);
				if (i != j) {
					if (!(e instanceof Spike && e2 instanceof Spike)) {
						if (e.touchingCreature(e2)) {
							if (e.isMovable())
								e.receiveKnockback(e2.x, e2.y, e2.width, e2.height, 0.1);
							if (e2.isMovable())
								e2.receiveKnockback(e.x, e.y, e.width, e.height, 0.1);

						}
					}
				}
			}

		}
	}

	/**
	 * Checks collision between the Player and all Abilities
	 * 
	 * @author Christopher Lew
	 * @param thisMaze the current Maze
	 */
	private void checkAbilityCollisions(Maze thisMaze) {
		for (int i = 0; i < thisMaze.getAbilities().size(); i++) {
			Ability ab = thisMaze.getAbilities().get(i);
			ab.draw(this);
			if (ab.touchingCreature(player)) {
				if (ab instanceof Heal) {
					player.healBy(1);
//					System.out.println("Should be healing");
					ab.removeSelfFromMaze(thisMaze, i);
				} else if (ab instanceof Star) {
//					starDuration = 5 * FPS;
					currentAbility = ab;
					ab.removeSelfFromMaze(thisMaze, i);
				} else if (ab instanceof Stealth) {
					currentAbility = ab;
					ab.removeSelfFromMaze(thisMaze, i);
				} else {
					currentAbility = ab;
					System.out.println("Picked up!");
					ab.removeSelfFromMaze(thisMaze, i);
				}
			}
		}
	}

	/**
	 * Change the current maze on the screen from allMazes.get(i) to
	 * allMazes.get(i+1) (or allMazes.get(0) if i+1 is greater than allMazes.size()
	 * )
	 * 
	 * @author Christopher Lew
	 * @return the index of the new Maze selected that will be shown on the screen
	 */
	public int toggleMaze() {
		if (mazeChangeCooldown == 0) {
			mazeChangeCooldown = 7;
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

	/**
	 * Returns the number of draw() iterations that have occured so far
	 * @author Christopher Lew
	 * @return number of draw() iterations
	 */
	public static long getIterations() {
		return iterations;
	}

	/**
	 * Returns how many frames on the respawn cooldown there's left
	 * @author Christopher Lew
	 * @return how many frames on the respawn cooldown there's left
	 */
	public static int getRespawnTimer() {
		return respawnCooldown;
	}

	/**
	 * Returns the player that the user controls
	 * @author Christopher Lew
	 * @post This method modified the one and only Player on screen so use carefully
	 * @return the Player that the user controls
	 */
	public static Player getPlayer() {
		return player;
	}

	/**
	 * Returns the ratio of cells
	 * @author Christopher Lew
	 * @return ratio of cells
	 */
	public double getCellRatio() {
		double d = 1.0;
		d = allMazes.get(mazeSelected).getCellWidth() / 40.0;
		return d;
	}
}