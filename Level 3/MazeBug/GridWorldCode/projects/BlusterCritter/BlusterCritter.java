/* 
 * @author Chris Ju
 */


import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;


/**
 * A <code>BlusterCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 */
public class BlusterCritter extends Critter
{
    private int c;

    /**
     * Construct BlusterCritter
     * @param number set c
     */
    public BlusterCritter(int number)
    {
        this.c = number;
    }


    /**
     * A Bluster gets the actors all of the neighbors within two steps of its current location.
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.NORTH, Location.NORTHEAST, Location.EAST, Location.SOUTHEAST, Location.SOUTH, 
              Location.SOUTHWEST, Location.WEST, Location.NORTHWEST };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
            {
                actors.add(a);
            }
        }

        return actors;
    }

    /**
     * Count the actors. If the number of actors is greater than c, brighter, or darker.
     * @param actors artors near
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n >= c)
        {
            this.setColor(getColor().brighter());
        }
        else
        {
            this.setColor(getColor().darker());
        }
        
    }

    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
            {
                locs.add(neighborLoc);
                Location neighborLocNext = neighborLoc.getAdjacentLocation(getDirection() + d);
                if (gr.isValid(neighborLocNext))
                {
                    locs.add(neighborLocNext);
                }
            }
        }
        return locs;
    }
}
