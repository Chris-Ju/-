
/**
 * @author Chris Ju
 */

import info.gridworld.grid.*;
import java.util.ArrayList;

/**
 * A <code>UnboundedGrid2</code> is a rectangular grid with a infinite number of rows and columns. <br />
 * Store data like cpp STL stack, if full, enlarge
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
    private Object[][] occupantArray;
    private int dimension;

    private static final int SIXTEEN = 16;
    /**
     * Constructs an empty unbounded grid with the dimension of 16
     */
    public UnboundedGrid2() {
        dimension = SIXTEEN;
        occupantArray = new Object[dimension][dimension];
    }

    public int getNumRows() {
        return -1;
    }

    public int getNumCols() {
        return -1;
    }

    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }


    /**
     * get all occupied locations
     * @return ArrayList contains occupied position
     */
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> occupied = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    occupied.add(loc);
                }
            }
        }

        return occupied;
    }

    /**
     * get object by given location
     * @param loc given location
     * @return target object
     */
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        // return null if a location is valid, but not in the array
        if (loc.getRow() >= dimension || loc.getCol() >= dimension) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }


    /**
     * put object to the grid
     * @param loc the position to be set
     * @param obj the object to be put
     * @return oldOccupant, which is the pre object in the same line. Empty is null.
     */
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        if (loc.getRow() >= dimension || loc.getCol() >= dimension) {
            resize(loc);
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }


    /**
     * Remove object by given location
     * @param loc the target position
     * @return if loc is empty return null, else return remove object
     */
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        // if location is valid and not in array, return null
        if (loc.getRow() >= dimension || loc.getCol() >= dimension) {
            return null;
        }
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }


    /**
     * Enlarge the grid
     */
    private void resize(Location loc) {
        // double the size until it is greater than needed
        int size = dimension;
        while (loc.getRow() >= size || loc.getCol() >= size) {
            size *= 2;
        }

        // create a new array
        Object[][] temp = new Object[size][size];

        // copy over the old contents
        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                temp[r][c] = occupantArray[r][c];
            }
        }

        // assign occupantArray the new array and update dimension
        occupantArray = temp;
        dimension = size;
    }
}