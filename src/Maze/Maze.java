package Maze;

import java.awt.Shape;
import java.util.ArrayList;
import entities.*;

public class Maze {
	private ArrayList<Creature> creatures;
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
	
	public ArrayList<Creature> getCreatures(){
		return creatures;
	}
	
	/**
	 * 
	 * @param shape the Shape object to add to the maze, should be a Rectangle
	 */
	public void add(Shape shape) {
		walls.add(shape);
	}
	
	
}
