package Maze;

import java.awt.Shape;
import java.util.ArrayList;
import entities.*;

public class Maze {
	private ArrayList<Enemy> enemies;
	private ArrayList<Shape> walls;
	
	public Maze() {
		walls = new ArrayList<Shape>();
	}
	
	/**
	 * @return the reference to the ArrayList of Shapes representing the walls in a particular maze
	 */
	public ArrayList<Shape> getWalls(){
		return walls;
	}
	
	public void add(Shape shape) {
		walls.add(shape);
	}
	
	
}
