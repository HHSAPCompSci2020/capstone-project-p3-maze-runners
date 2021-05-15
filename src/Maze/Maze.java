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
	private final int CELL_WIDTH = 50, CELL_HEIGHT = 50;
	
	//characters that represents objects in grid
	
	
	
	
	
	public Maze() {
		walls = new ArrayList<Shape>();
		creatures = new ArrayList<Creature>();
		grid = new char[DEFAULT_MAZE_HEIGHT][DEFAULT_MAZE_WIDTH];
		playerStartX = 100; 
		playerStartY = 100;
	}
	
	public Maze(String filename, int gridWidth, int gridHeight) {
		walls = new ArrayList<Shape>();
		creatures = new ArrayList<Creature>();
		
		grid = new char[gridHeight][gridWidth];
		this.readData(filename, grid);
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
	
	private void createMazeFromGrid() {
		for (int i = 0; i < grid.length; i ++) {
			for (int j = 0; j < grid[i].length; j++) {
				char c = grid[i][j];
				if (c == '#') {
					//Rectangle(int x, int y, int width, int height)
					Rectangle r = new Rectangle(j*CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT); 
					
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
	
	
	
	
}
