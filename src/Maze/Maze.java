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
 * Maze holds all the Walls, Abilities, and Enemies on the screen and can be loaded from a .txt file
 * @author Chris
 *
 */
public class Maze {

	// characters that represents objects in grid
	@SuppressWarnings("unused")
	private final char START_LOCATION = 'C', WALL = '#', WALKABLE = '.', HEAL_CROSS = 'h', STEALTH = 's',
			TIMING_TRAP = 'T', EXIT = 'X', SPIKE = 'S', INVINCIBILITY_PRANK = 'I';

	public int playerStartX, playerStartY;
	/**
	 * grid is a 2D char array initialized from Maze's constructor, indexes are at
	 * [row #][col #]
	 */
	protected char[][] grid;
	private final int DEFAULT_MAZE_WIDTH = 20, DEFAULT_MAZE_HEIGHT = 20;
	private int cellWidth = 40, cellHeight = 40;

	private ArrayList<Enemy> enemies;
	private ArrayList<Ability> abilities;
	private ArrayList<Shape> walls;
	private ArrayList<Exit> exits;

	public Maze() {
		walls = new ArrayList<Shape>();
		enemies = new ArrayList<Enemy>();
		abilities = new ArrayList<Ability>();
		exits = new ArrayList<Exit>();

		grid = new char[DEFAULT_MAZE_HEIGHT][DEFAULT_MAZE_WIDTH];
		playerStartX = 25;
		playerStartY = 25;
	}

	public Maze(PApplet marker, String filename, int gridWidth, int gridHeight) {
		walls = new ArrayList<Shape>();
		enemies = new ArrayList<Enemy>();
		abilities = new ArrayList<Ability>();
		exits = new ArrayList<Exit>();

		grid = new char[gridHeight][gridWidth];
		this.readData(filename, grid);
		addObjectsFromGrid(marker);
	}

	/**
	 * @return the reference to the ArrayList of Shapes representing the walls in a
	 *         particular maze
	 */
	public ArrayList<Shape> getWalls() {
		return walls;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public ArrayList<Exit> getExits() {
		return exits;
	}

	/**
	 * 
	 * @param shape the Shape object to add to the maze, should be a Rectangle
	 */
	public void addWall(Shape shape) {
		walls.add(shape);
	}

	/**
	 * adds a Creature to the maze. allows Enemies too because of polymorphism
	 */
	public void addCreature(Entity creature) {
		enemies.add((Enemy) creature);
	}

	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	public void addAbility(Ability ability) {
		abilities.add(ability);
	}

	public void addExit(Exit e) {
		exits.add(e);
	}

	/**Loads objects stored in Maze's enemies, abilities, walls, and exits ArrayLists
	 * Traverses through the .txt file saved for this Maze and matches characters on the .txt file to corresponding Entities
	 * @author Christopher Lew, with help from Lakshya Shrivastava
	 * @param marker the PApplet object used for drawing
	 */
	private void addObjectsFromGrid(PApplet marker) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				char c = grid[row][col];
				int x = col * cellWidth;
				int y = row * cellHeight;

				if (c == HEAL_CROSS) {
					this.addAbility(new Heal(null, x, y, cellWidth, cellHeight));
				}
//
//				if (c == STEALTH) {
//					this.addAbility(new Stealth(null, x, y, cellWidth, cellHeight));
//				}
				if (c == INVINCIBILITY_PRANK) {
					InvincibilityPrank  s = new InvincibilityPrank(marker.loadImage("data//invisibility.png"), x, y, cellWidth, cellHeight);
					this.addAbility(s);
				}
				if (c == 'i') {
					Star star = new Star(marker.loadImage("data//star.png"), x, y, cellWidth, cellHeight);
					this.addAbility(star);
				}

				if (c == TIMING_TRAP) {
					TimingTrap temp = new TimingTrap(marker.loadImage("data//spike.png"), x, y, cellWidth, cellHeight);
					this.addEnemy(temp);

				}
				if (c == EXIT) {
					Exit e = new Exit(marker.loadImage("data//Exit.png"), x, y, cellWidth, cellHeight);
					this.addExit(e);
				}

				if (c == SPIKE) {
					Spike e = new Spike(marker.loadImage("data//spike2.png"), x, y, cellWidth, cellHeight);
					this.addEnemy(e);
				}

				if (c == WALL) {
					// Rectangle(int x, int y, int width, int height)
					Rectangle r = new Rectangle(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
					walls.add(r);
				}
				if (c == START_LOCATION) {
					playerStartX = col * cellWidth;
					playerStartY = row * cellHeight;
				}
				if (c == 'M') {
//					Monster M = new Monster(marker.loadImage("data//Monster.png"), x, y, cellWidth, cellHeight);
					Monster monster; 
					monster = new Monster(marker.loadImage("data//monster.png"), x, y, cellWidth, cellHeight);

					addEnemy(monster);
				}
				if (c == 'H') {
					Monster M = new HomingMonster(marker.loadImage("data//HomingMonster.png"), x, y, cellWidth, cellHeight);
					addEnemy(M);
				}
				
				if (c == WALKABLE) {
					// add nothing
				}
				
				if(c== 's') {
					Star star = new Star(marker.loadImage("data//star.png"), x, y, cellWidth, cellHeight);
					this.addAbility(star);
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
	 * @pre chars is a rectangular 2D char array that is initialized
	 * @param chars
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

	public char[][] getGrid() {
		return grid;
	}

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
	public void setNewCellWidthHeight(int width, int height) {
		cellWidth = width;
		cellHeight = height;
	}
	
	
}
