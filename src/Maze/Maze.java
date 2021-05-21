package Maze;

import java.awt.Shape;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import java.awt.Rectangle;
import entities.*;
import entities.abilities.Ability;
import entities.enemies.*;
import entities.abilities.*;

/**
 * Maze holds all the Walls, Abilities, and Enemies on the screen and can be
 * loaded from a .txt file
 * 
 * @author Chris
 *
 */
public class Maze {

	// characters that represents objects in grid
	@SuppressWarnings("unused")
	private final char START_LOCATION = 'C', WALL = '#', WALKABLE = '.', HEAL_CROSS = 'h', STEALTH = 's',
			TIMING_TRAP = 'T', EXIT = 'X', SPIKE = 'S', INVINCIBILITY_PRANK = 'I', STAR = 'i', FENCE = 'F',
			MONSTER = 'M', HOMING_MONSTER = 'H';

	public int playerStartX, playerStartY;
	/**
	 * grid is a 2D char array initialized from Maze's constructor, indexes are at
	 * [row #][col #]
	 */
	protected char[][] grid;
	private final int DEFAULT_MAZE_WIDTH = 20, DEFAULT_MAZE_HEIGHT = 20;
	private int cellWidth, cellHeight;

	private ArrayList<Enemy> enemies;
	private ArrayList<Ability> abilities;
	private ArrayList<Shape> walls;
	private ArrayList<Exit> exits;

	private String fileName;

	/**
	 * Creates a maze with default arguments
	 */
	public Maze() {
		walls = new ArrayList<Shape>();
		enemies = new ArrayList<Enemy>();
		abilities = new ArrayList<Ability>();
		exits = new ArrayList<Exit>();

		fileName = "none";
		grid = new char[DEFAULT_MAZE_HEIGHT][DEFAULT_MAZE_WIDTH];
		playerStartX = 25;
		playerStartY = 25;
		cellWidth = 40;
		cellHeight = 40;
	}

	/**
	 * Creates a maze with user specified arguments
	 * 
	 * @param marker     The PApplet to which the maze will be drawn
	 * @param filename   The file from which to get the maze
	 * @param gridWidth  width of the grid / number of columns
	 * @param gridHeight height of the grid / number of rows
	 */
	public Maze(PApplet marker, String filename, int gridWidth, int gridHeight) {
		walls = new ArrayList<Shape>();
		enemies = new ArrayList<Enemy>();
		abilities = new ArrayList<Ability>();
		exits = new ArrayList<Exit>();

		this.fileName = filename;
		grid = new char[gridHeight][gridWidth];
		cellWidth = 40;
		cellHeight = 40;

		if (gridWidth >= 36) {
			cellWidth = 25;
			cellHeight = 25;
		}

		this.readData(filename, grid);
		addObjectsFromGrid(marker);
	}

	/**
	 * Construct a maze from another Maze. Skips reading the text file
	 * 
	 * @param old the old maze
	 * @param marker The PApplet to which the maze will be drawn
	 */
	public Maze(Maze old, PApplet marker) {
		cellWidth = old.cellWidth;
		cellHeight = old.cellHeight;

		this.fileName = old.getFileName();
		grid = old.grid;

		walls = new ArrayList<Shape>();
		enemies = new ArrayList<Enemy>();
		abilities = new ArrayList<Ability>();
		exits = new ArrayList<Exit>();
		addObjectsFromGrid(marker); // Add objects to the ArrayLists from the char array
	}

	/**
	 * Returns the name of the .txt file used to initialize this Maze's ArrayLists
	 * 
	 * @return the name of the .txt file used to initialize this Maze's ArrayLists
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Returns the reference to the ArrayList of Shapes representing the walls in a
	 * particular maze
	 * 
	 * @return the reference to the ArrayList of Shapes representing the walls in a
	 *         particular maze
	 */
	public ArrayList<Shape> getWalls() {
		return walls;
	}

	/**
	 * Returns the reference to the ArrayList of Shapes representing the enemies in
	 * a particular maze
	 * 
	 * @return the reference to the ArrayList of Shapes representing the enemies in
	 *         a particular maze
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * Returns the reference to the ArrayList of Shapes representing the abilites in
	 * a particular maze
	 * 
	 * @return the reference to the ArrayList of Shapes representing the abilites in
	 *         a particular maze
	 */
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	/**
	 * Returns the reference to the ArrayList of Shapes representing the exits in a
	 * particular maze
	 * 
	 * @return the reference to the ArrayList of Shapes representing the exits in a
	 *         particular maze
	 */
	public ArrayList<Exit> getExits() {
		return exits;
	}

	/**
	 * Rectangle representing a wall
	 * @param shape the Shape object to add to the maze, should be a Rectangle
	 */
	public void addWall(Shape shape) {
		walls.add(shape);
	}

	/**
	 * adds an Entity to the maze(includes enemies, ability, exits, etc.)
	 * 
	 * @param entity the entity to add
	 */
	public void addEntity(Entity entity) {
		enemies.add((Enemy) entity);
	}

	/**
	 * adds an enemy to the maze
	 * 
	 * @param e the enemy to add
	 */
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	/**
	 * adds an ability to the maze
	 * 
	 * @param ability the ability to add
	 */
	public void addAbility(Ability ability) {
		abilities.add(ability);
	}

	/**
	 * adds an exit to the maze
	 * 
	 * @param e the exit to add
	 */
	public void addExit(Exit e) {
		exits.add(e);
	}

	/**
	 * Loads objects stored in Maze's enemies, abilities, walls, and exits
	 * ArrayLists Traverses through the .txt file saved for this Maze and matches
	 * characters on the .txt file to corresponding Entities
	 * 
	 * @author Christopher Lew, with help from Lakshya Shrivastava
	 * @param marker the PApplet object used for drawing
	 */
	private void addObjectsFromGrid(PApplet marker) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				char c = grid[row][col];
				int x = col * cellWidth;
				int y = row * cellHeight;

				if (c == WALL) { // #
					Rectangle r = new Rectangle(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
					walls.add(r);
				} else if (c == WALKABLE) {
					// add nothing
				} else if (c == HEAL_CROSS) {// h
					this.addAbility(new Heal(null, x, y, cellWidth, cellHeight));
				} else if (c == INVINCIBILITY_PRANK) {// I
					InvincibilityPrank s = new InvincibilityPrank(null, x, y, cellWidth, cellHeight);
					this.addAbility(s);
				} else if (c == STAR) {
					Star star = new Star(marker.loadImage("data//star.png"), x, y, cellWidth, cellHeight);
					this.addAbility(star);
				} else if (c == TIMING_TRAP) {// T
					TimingTrap temp = new TimingTrap(marker.loadImage("data//spike.png"), x, y, cellWidth, cellHeight);
					this.addEnemy(temp);
				} else if (c == EXIT) {// X
					Exit e = new Exit(marker.loadImage("data//Exit.png"), x, y, cellWidth, cellHeight);
					this.addExit(e);
				} else if (c == SPIKE) {// S
					Spike e = new Spike(marker.loadImage("data//spike2.png"), x, y, cellWidth, cellHeight);
					this.addEnemy(e);
				} else if (c == START_LOCATION) {// C
					playerStartX = col * cellWidth;
					playerStartY = row * cellHeight;
				} else if (c == MONSTER) {
					Monster monster;
					monster = new Monster(marker.loadImage("data//monster.png"), x, y, cellWidth, cellHeight);
					addEnemy(monster);
				} else if (c == HOMING_MONSTER) {
					Monster M = new HomingMonster(marker.loadImage("data//HomingMonster.png"), x, y, cellWidth,
							cellHeight);
					addEnemy(M);
				} else if (c == STEALTH) {
					Stealth sneak = new Stealth(marker.loadImage("data//invisibility.png"), x, y, cellWidth,
							cellHeight);
					this.addAbility(sneak);
				} else if (c == FENCE) {
					Fence f = new Fence(null, x, y, cellWidth, cellHeight);
					this.addEnemy(f);
				}
			}
		}

	}

	/**
	 * Copied from Recursion in 2D arrays lab: GridTemplate.java
	 * 
	 * @pre char[][] gameData is initialized
	 * @post gameData will be filled with all the characters in the text file
	 * @param filename name of the file to read characters from
	 * @param gameData the 2D char array to store the characters in
	 */
	public void readData(String filename, char[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
				reader = new FileReader(dataFile);
				in = new Scanner(reader);

				while (in.hasNext()) {
					String line = in.nextLine();
					for (int i = 0; i < line.length(); i++)
						if (count < gameData.length && i < gameData[count].length) {
							if (line.charAt(i) == '\\')
								return;
							gameData[count][i] = line.charAt(i);
						}

					count++;
				}

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}

		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}

	/**
	 * prints the specified 2D array of chars
	 * @pre chars is a rectangular 2D char array that is initialized
	 * @param chars the 2d array to be printed
	 */
	public void printCharArray(char[][] chars) {
		String s0 = "";
		for (int i = 0; i < chars.length; i++) {
			if (i != 0)
				s0 += "\n";
			for (int j = 0; j < chars[0].length; j++) {

				s0 += chars[i][j] + " ";

			}
		}
		System.out.println(s0);
	}

	/**
	 * Returns the two dimensional array representing this maze
	 * 
	 * @return the two dimensional array representing this maze
	 */
	public char[][] getGrid() {
		return grid;
	}

	/**
	 * returns String representation of this maze
	 * 
	 * @return String representation of this maze
	 */
	public String toString() {
		String s0 = "";

		for (int i = 0; i < grid.length; i++) {
			if (i != 0)
				s0 += "\n";
			for (int j = 0; j < grid[0].length; j++) {

				s0 += grid[i][j] + " ";

			}
		}
		return s0;
	}

	/**
	 * Sets the dimentions of each cell in the maze
	 * 
	 * @param width  width of each cell
	 * @param height height of each cel
	 */
	public void setNewCellWidthHeight(int width, int height) {
		cellWidth = width;
		cellHeight = height;
	}

	/**
	 * Returns width of the cell
	 * 
	 * @return width of the cell
	 */
	public int getCellWidth() {
		return cellWidth;
	}

}
