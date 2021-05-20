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

@SuppressWarnings("serial")
/**
 * 
 * @author Christopher Lew, Lakshya Shrivastava, Joseph Huang
 *
 */
public class DrawingSurface extends PApplet {
	public static final int DRAWING_WIDTH = 900;
	public static final int DRAWING_HEIGHT = 675;
	public static final int FPS = 60;
	
	private static long iterations = 0;
	public static int lives = 3;
	private static boolean debugEnabled = false;

	private Rectangle screenRect;

	private static Player player;

	private ArrayList<Shape> obstacles;
	private ArrayList<Integer> keys;
	private ArrayList<Maze> allMazes;


	private Maze maze0, maze1, maze2, maze3, maze4, maze5, maze6, maze7, maze8, maze9;

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
	private int abilityCooldown;
	private int otherCooldown = 0;
	private static int respawnCooldown = 0;
	
	private int prankedTime = 0;

	/**
	 * how many invincibility frames the player has. Player cannot take a damage for
	 */
	public static int playerDmgCooldown;
	public static final int DMG_MAX_COOLDOWN = 60;
	/**
	 * how many abilities are to be spawned randomly in the maze
	 */
	public int abilityNum;
	
	public static int starDuration;
	public static int stealthDuration;
	private Ability currentAbility = null;
	
	private boolean gameComplete = false;

	/*
	 * ------------------------Constructor------------------------
	 */
	/**
	 * @author Christopher Lew
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
		
		//Go to loadMazes() to add new Mazes, use the exact same name style
		loadMazes();
		allMazes.add(maze0);
		allMazes.add(maze1);

		allMazes.add(maze2);
		allMazes.add(maze3);
		allMazes.add(maze4);
		allMazes.add(maze5);
		allMazes.add(maze6);
		allMazes.add(maze7);
		allMazes.add(maze8);
		allMazes.add(maze9);
//		maze9.setNewCellWidthHeight(35, 35);
//		DEBUG PRINT TO BE REMOVED
		{
			Maze thisMaze = allMazes.get(mazeSelected);
			System.out.println("Maze: " + (mazeSelected));
			thisMaze.printCharArray(thisMaze.getGrid());
		}
		spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);
	}
	/**
	 * @author Christopher Lew
	 */
	private void loadMazes() {
		maze0 = new Maze(this, "data//maze0.txt", 9, 9);
		maze1 = new Maze(this, "data//maze1.txt", 12, 12);
		maze2 = new Maze(this, "data//maze2.txt", 15, 15);
		maze3 = new Maze(this, "data//maze3.txt", 15, 15);
		maze4 = new Maze(this, "data//maze4.txt", 18, 25);

		maze5 = new Maze(this, "data//maze5.txt", 20, 15);
		maze6 = new Maze(this, "data//maze6.txt", 15, 15);
		maze7 = new Maze(this, "data//maze7.txt", 16, 9);
		
		maze8 = new Maze(this, "data//maze8.txt", 25, 18);
		maze9 = new Maze(this, "data//maze9.txt", 20, 15);
		

	}
	
	/**
	 * @pre Maze txt file is in the exact format mazeN.txt, where N is the index of the maze in allMazes
	 * @param mazeIndex
	 * @author Christopher Lew
	 */
	private void reloadMaze(int mazeIndex) {
//		Maze temp = new Maze(this, "data//maze" + mazeIndex+".txt", 25, 25);
		Maze temp = new Maze(allMazes.get(mazeSelected), this);
		allMazes.remove(mazeIndex);
		allMazes.add(mazeIndex, temp);
	}
	private void drawBanner() {
//		Text that Displays the players health
			int bannerHeight = 50;
			
			this.fill(0, 0, 0, 64);
			this.noStroke();
			rect(0, DRAWING_HEIGHT - bannerHeight, DRAWING_WIDTH, bannerHeight);
			this.stroke(0);
			this.fill(0, 0, 0, 200);
			String healthStr = "";
			String livesStr = "Lives: " + lives;
			
			healthStr += "Health: " + player.getHealth();
			String abilityStr = "";
			if (currentAbility!= null) {
				this.textSize(16);
				String s = "";
				
				if (currentAbility.getUses() == 1000) {
					s = "";
				}
				if (currentAbility.getUses() != 1000) {
					s = currentAbility.getUses() + "x ";
				}


				abilityStr += "Ability (press spacebar): "+  s + currentAbility.toString();
			}
			if (player.invincible == true) {
//				healthStr += "star";
//				System.out.println("wow");
				abilityStr += " | " + (int)(starDuration/60) + "."+ (int)(starDuration/6 %10 ) + " s of invincibility";
			}
			if (stealthDuration >0) {
				abilityStr += " | "+(int)(stealthDuration/60) + "."+ (int)(stealthDuration/6 %10 ) + "s of stealth";
			}
			this.textSize(20);
			this.text(livesStr, 0 + 20, DRAWING_HEIGHT - 20);
			this.text(healthStr, 120, DRAWING_HEIGHT - 20);
			this.text(abilityStr, 300, DRAWING_HEIGHT - 20);
			this.fill(0);
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
		if (prankedTime> 0)
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
		}
		else {
			player.invincible = false;
		}
		if (stealthDuration > 0) {
			player.visibleByEnemies=false;			
			stealthDuration--;
		}
		else {
			player.visibleByEnemies=true;			
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

		//Print the health, lives, ability, debug information
		drawBanner();
		
		
		//author Joseph Huang
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

			pushStyle();
			textAlign(CENTER);
			fill(255);
			this.rect(0, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
			fill(0);
			textSize(40);
			String s = "Thanks For Playing!\n" + "Developed By:\n" + "Christopher Lew\n" + "Joseph Huang\n"
					+ "Lakshya Shrivastava\n";
			this.text(s, DRAWING_WIDTH / 2, DRAWING_HEIGHT / 3);
			popStyle();
		}
		
		

//		debug mode: to be deleted or commented in final version
		if (debugEnabled && !gameComplete) {
			pushStyle();
			noStroke();
			fill(0,0,0,16);
			rect(DRAWING_WIDTH - 200, 0, 220, 200);
			

			String debugStr = "[Debug On]\n" + "Debug controls:\n" + "= Toggle debug mode\n" + "m Go to next maze\n" 
			+ "k Increase Health\n" + "l Increase Lives";
			fill(32);
			textSize(16);
			this.text(debugStr, DRAWING_WIDTH - 190, 20);
			popStyle();

		}
		
		if (prankedTime >0) {
			pushStyle();
			fill(0,64,0, 220);
			rect(0, 0, DrawingSurface.DRAWING_WIDTH, DrawingSurface.DRAWING_HEIGHT);
			textAlign(CENTER);
			fill(255,255,255);
			textSize(25);
			text("Did you honestly think we would give you that?",DrawingSurface.DRAWING_WIDTH/2,DrawingSurface.DRAWING_HEIGHT/2);
			popStyle();
		}
		
		popMatrix();//this should be after any drawing to scale properly
		if (debugEnabled) {
			if (isPressed(KeyEvent.VK_M)) { // TOGGLE MAZE
				
				String s = "" + toggleMaze();
//				System.out.println("Maze Selected: " + s);
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

		if (isPressed(KeyEvent.VK_LEFT)) {
			player.moveBy(-1, 0);
		}
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
		
//		if (isPressed(KeyEvent.VK_SHIFT) ||  isPressed(KeyEvent.SHIFT_DOWN_MASK) ) {
//			System.out.println("run");
//
//			player.setSpeed(player.RUN_SPEED);
//		}
//		else {
//			player.setSpeed(player.WALK_SPEED);
//		}
		
		
//		if (isPressed(KeyEvent.VK_F)) {
//			starDuration = 3000;
//			System.out.println("star");
//		}
		
		if (player.getXVelocity() < 0) {
			player.facingRight = false;
		}
		else if (player.getXVelocity() > 0){
			player.facingRight = true;
		}
		
		
		
		if (currentAbility!= null) {
			if (isPressed(KeyEvent.VK_SPACE)) {
				int cd = currentAbility.getCooldown();
				if (abilityCooldown == 0) {
					if (currentAbility instanceof InvincibilityPrank) {
//						System.out.println("pranked");
						InvincibilityPrank p = (InvincibilityPrank)currentAbility;
						prankedTime = 200;
						p.use();//decrements uses only
					}
					else if (currentAbility instanceof Star) {
						Star tempAb = (Star)currentAbility;
						tempAb.use(player);
					}
					else {
						currentAbility.use();
					}
					if (currentAbility.getUses()<= 0) {
						currentAbility = null;
					}

				}
				abilityCooldown = 30*cd;
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
		for (Enemy e: thisMaze.getEnemies()) {
			e.act(obstacles);
		}
		for (Ability ab: thisMaze.getAbilities()) {
			ab.act(obstacles);
		}
		for (Exit exit: thisMaze.getExits()) {
			exit.act(obstacles);
		}
		

		if (!screenRect.intersects(player))
			spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);

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

	/**
	 * @author Christopher Lew
	 * @param x the x position of the top left of the new Player
	 * @param y the y position of the top left of the new Player
	 */
	public void spawnNewPlayer(int x, int y) {
		respawnCooldown = 30;
//		loadMazes();	
		reloadMaze(mazeSelected);
		starDuration = 0;
		player = new Player(loadImage("data//luigi.png"), x, y, 25, 25, loadImage("data//luigiLeft.png"));
	}

	private void spawnWalls(ArrayList<Shape> obstacles, Maze thisMaze) {
		for (Shape s : thisMaze.getWalls()) {
			obstacles.add(s);
		}
	}

/**
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

	/**Checks collision between the Player and every Enemy, also each Enemy with every other Enemy
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
					e.attack(player);

				}
				if (player.invincible) {
					if (e.canDie()) {
						e.removeSelfFromMaze(thisMaze, i);
					}
				}
				
				
				
				// MODIFY Enemies' attack() METHOD

			}
			
			//Make sure enemies don't run into each other
			for (int j = 0; j < thisMaze.getEnemies().size(); j++) {
				Enemy e2 = thisMaze.getEnemies().get(j);
				if (i != j) {
					if ( ! (e instanceof Spike && e2 instanceof Spike) ) {
						if (e.touchingCreature(e2)) {
							if (e.isMovable())
								e.receiveKnockback(e2.x, e2.y, e2.width, e2.height, 0.2);
							if (e2.isMovable())
								e2.receiveKnockback(e.x, e.y, e.width, e.height, 0.2);

						}
					}
				}
			}

		}
	}

	/**Checks collision between the Player and all Abilities
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
				}
				else if (ab instanceof Star) {
//					starDuration = 5 * FPS;
					currentAbility = ab;
					ab.removeSelfFromMaze(thisMaze, i);
				}else if (ab instanceof Stealth) {
					currentAbility = ab;
					ab.removeSelfFromMaze(thisMaze, i);
				}
				else {
					currentAbility = ab;
					System.out.println("Picked up!");
					ab.removeSelfFromMaze(thisMaze, i);
				}
			}
		}
	}

	/**
	 * Change the current maze on the screen from allMazes.get(i) to
	 * allMazes.get(i+1) (or allMazes.get(0) if i+1 > allMazes.size() )
	 * @author Christopher Lew
	 * @return the index of the new Maze selected that will be shown on the screen
	 */
	public int toggleMaze() {
//		System.out.println("maze change CD" + mazeChangeCooldown);
		if (mazeChangeCooldown == 0) {
			mazeChangeCooldown = 10;
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
	
//	public void toggleBackMaze() {
//		if (mazeChangeCooldown == 0) {
//			mazeChangeCooldown = 10;
//			mazeSelected--;
////			if (mazeSelected < allMazes.size()) {
////				gameComplete = false;
////			} else {
////				gameComplete = true;
////
////			}
//
//			if (mazeSelected < allMazes.size())
//				spawnNewPlayer(allMazes.get(mazeSelected).playerStartX, allMazes.get(mazeSelected).playerStartY);
//			if (mazeSelected >= allMazes.size()) {
//				gameComplete = true;
//				mazeSelected = 0;
//			}
//		}		
//	}

	
	/**
	 * @author Christopher Lew
	 * @return number of draw() iterations
	 */
	public static long getIterations() {
		return iterations;
	}
	
	/**
	 * @author Christopher Lew
	 * @return how many frames on the respawn cooldown there's left
	 */
	public static int getRespawnTimer() {
		return respawnCooldown;
	}
	
	
	/**@author Christopher Lew
	 * @post This method modified the one and only Player on screen so use carefully
	 * @return the Player that the user controls
	 */
	public static Player getPlayer() {
		return player;
	}
	
}