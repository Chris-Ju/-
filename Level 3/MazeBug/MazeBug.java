
/**
 * @author Chris Ju
 */

package info.gridworld.maze;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private Location next;
	private Stack<Location> crossLocation = new Stack<Location>();
	private Set<Location> flowerLocations = new HashSet<Location>();
    private Integer stepCount = 0;

    //final message has been shown
	private boolean hasShown = false;

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
	}

	/**
	 * Moves to the next location of the square.
	 * If reachReadRock, show path
	 * Else, find next Location
	 */
	public void act() {
		boolean isEnd = reachRedRock();
		if (isEnd) {
		//to show step count when reach the goal		
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				//showPath();
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		}
		else {
			ArrayList<Location> temp = getUnvisitedLocation();
			if (temp.size() == 0) {
				next = crossLocation.pop();
				move();
			}
			else {
				crossLocation.push(getLocation());
				next = selectLocation(selectDirection(crossLocation), temp);
				move();
				
			}
			//increase step count when move 
			stepCount++;
		}
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
		flowerLocations.add(loc);
	}

	/** 
	 * Decide wheher reach the red Rock
	 */
	public boolean reachRedRock() {
		ArrayList<Location> locs = getValidAdjacentLocations();
		Grid<Actor> gr = getGrid();
		if (locs.size() == 0) {
			return false;
		}
		for (Location temp: locs) {
			if ((gr.get(temp) instanceof Rock) && (gr.get(temp).getColor()).equals(Color.RED)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Select the location that wasn't visited from the getValidAdjacentLocations() method
	 * @return unvisitedGrid ArrayList
	 */
	public ArrayList<Location> getUnvisitedLocation() {
		ArrayList<Location> locs = getValidAdjacentLocations();
		ArrayList<Location> unvisited = new ArrayList<Location>();
		Grid<Actor> gr = getGrid();
		if (locs.size() == 0) {
			return unvisited;
		}
		for (Location temp:locs)  {
			if (!(gr.get(temp) instanceof Rock) && !(gr.get(temp) instanceof Flower)) {
			    unvisited.add(temp);
			}
		}
		return unvisited;
	}

	/**
	 * Get all valid locations in east , west, south, north
	 * @return Valid locations from four directions
	 */
	public ArrayList<Location> getValidAdjacentLocations()
	{
	    ArrayList<Location> locs = new ArrayList<Location>();
	    Grid<Actor> gr = getGrid();
	    Location loc = getLocation();
	    int d = Location.NORTH;
	    for (int i = 0; i < Location.FULL_CIRCLE / Location.RIGHT; i++)
	    {
		    Location neighborLoc = loc.getAdjacentLocation(d);
		    if (gr.isValid(neighborLoc)) {
			   locs.add(neighborLoc);
			}
		    d = d + Location.RIGHT;
	    }
	    return locs;
	}

	/**
	 * Show the correct path
	 */
	public void showPath() {
		if (flowerLocations.size() == 0 || crossLocation.size() == 0) {
			return;
		}
		//clear all the flower
		Grid<Actor> gr = getGrid();
		for (Location temp:flowerLocations) {
			gr.get(temp).removeSelfFromGrid();
		}
		//put flower in the correct path
		for (Location temp:crossLocation) {
			gr.put(temp, new Flower(Color.GREEN));
		}	
	}

	/**
	 * Calculate how many times each direction has been chosen.
	 * @param CrossPath the crossing path
	 * @return times array of four directions
	 */
	public int[] selectDirection(Stack<Location> crossPath) {

		int[] direction = {1, 1, 1, 1};
		if (crossPath.size() == 0) {
			return direction;
		}
		else {
			int sizeOfStack = crossPath.size();
			for (int i = 0; i < sizeOfStack-1; i++) {
				int tempDir = crossPath.elementAt(i).getDirectionToward(crossPath.elementAt(i+1));
				if (tempDir == Location.NORTH) {
					direction[0]++;
				}
				if (tempDir == Location.EAST) {
					direction[1]++;
				}
				if (tempDir == Location.SOUTH) {
					direction[2]++;
				}
				if (tempDir == Location.WEST) {
					direction[3]++;
				}
			}
			int tempDir = crossPath.elementAt(sizeOfStack-1).getDirectionToward(this.getLocation());
			if (tempDir == Location.NORTH) {
				direction[0]++;
			}
			if (tempDir == Location.EAST) {
				direction[1]++;
			}
			if (tempDir == Location.SOUTH) {
				direction[2]++;
			}
			if (tempDir == Location.WEST) {
				direction[3]++;
			}
			return direction;
		}
	}


	/**
	 * Select next location by given directions times
	 * @param dirs given directions times
	 * @param locs given valid locations
	 * @return next location
	 */
	public  Location selectLocation(int[] dirs, ArrayList<Location> locs) {
		if (locs.size() == 1) {
			return locs.get(0);
		}
		Location temp = locs.get(0);
		for (Location i : locs) {
			int index1 = this.getLocation().getDirectionToward(i) / Location.EAST;
			int index2 = this.getLocation().getDirectionToward(temp) / Location.EAST;
			if (dirs[index1] > dirs[index2]) {
				temp = i;
			}
		}
		return temp;
	}

}
