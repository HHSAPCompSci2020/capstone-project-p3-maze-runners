package Maze;

import java.awt.Shape;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import processing.core.PApplet;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import entities.*;
import entities.enemies.TimingTrap;
import entities.*;

public class Maze {
	private ArrayList<Creature> creatures;
	private ArrayList<Shape> walls;
	
	public int playerStartX, playerStartY;
	/**
	 * grid is a 2D char array initialized from Maze's constructor, indexes are at [row #][col #]
	 */
	protected char[][] grid;
	private final int DEFAULT_MAZE_WIDTH = 20, DEFAULT_MAZE_HEIGHT = 20;
	private int cellWidth = 40, cellHeight = 40;
	
	//characters that represents objects in grid
	private final char START = 'C', WALL = '#', WALKABLE = '.';
	
	
	
	
	public Maze() {
		walls = new ArrayList<Shape>();
		creatures = new ArrayList<Creature>();
		grid = new char[DEFAULT_MAZE_HEIGHT][DEFAULT_MAZE_WIDTH];
		playerStartX = 25; 
		playerStartY = 25;
	}
	
	public Maze(PApplet marker, String filename, int gridWidth, int gridHeight) {
		walls = new ArrayList<Shape>();
		creatures = new ArrayList<Creature>();
		
		grid = new char[gridHeight][gridWidth];
		this.readData(filename, grid);
		addObjectsFromGrid(marker);
	}
	
	
	/**
	 * @return the reference to the ArrayList of Shapes representing the walls in a particular maze
	 */
	public ArrayList<Shape> getWalls(){
		return walls;
	}
	
	public ArrayList<Creature> getCreatures(){
		return creatures;
	}
	
	/**
	 * 
	 * @param shape the Shape object to add to the maze, should be a Rectangle
	 */
	public void addWall(Shape shape) {
		walls.add(shape);
	}
	
	/**
	 * adds an enemy to the maze
	 */
	public void addEnemy(Enemy enemy) {
		creatures.add(enemy);
	}
	
	private void addObjectsFromGrid(PApplet marker) {
		for (int row = 0; row < grid.length; row ++) {
			for (int col = 0; col < grid[row].length; col++) {
				char c = grid[row][col];
				int x = col*cellWidth;
				int y = row*cellHeight;
				
				
				if (c == WALL) {//wall
					//Rectangle(int x, int y, int width, int height)
					Rectangle r = new Rectangle(col*cellWidth, row * cellHeight, cellWidth, cellHeight); 
					walls.add(r);
				}
				if(c == START) {//start location
					playerStartX = col*cellWidth;
					playerStartY = row*cellHeight;
				}
				if(c == WALKABLE) {
					//add nothing
				}
				if (c == TimingTrap.symbol) {//TimingTrap
					
					this.addEnemy(new TimingTrap(marker.loadImage("data//spike.png"), x,y, cellWidth, cellHeight) );
				}
				
				
//				walls.add(e)
			}
		}
		
		
		
	}
	
	
	
	
	
	/**
	 * Copied from Recursion in 2D arrays lab: GridTemplate.java
	 * @pre char[][] gameData is initialized
	 * @post gameData will be filled with all the characters in the text file
	 * @param filename name of the file to read characters from
	 * @param gameData the 2D char array to store the characters in
	 */
	public void readData (String filename, char[][] gameData) {
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
						for(int i = 0; i < line.length(); i++)
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
			if (i!= 0)
				s0+= "\n";
			for (int j = 0; j < chars[0].length; j++) {
				
				s0+= chars[i][j] + " ";
				
			}
		}
		System.out.println(s0);
	}
	
//	public boolean removeCreature(Creature c) {
//		boolean successfullyRemoved = false;
//		if (creatures.remove(c) ) {
//			successfullyRemoved = true;
//		}
//		return successfullyRemoved;
//	}
	
		
	
}
