/* 
 * @author Chris Ju
 */

import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
   

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> result = new ArrayList<Location>();
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        Grid gr = getGrid();
        Location loc = getLocation();

        /**
         * First decide whether neighbor is valid or null.
         * If true
         *    decide whether neighbor's neighbor is valid or null,
         *    If true add neighbor's neighbor
         *       false add neighbor
         */
        
        for (int d : dirs)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc) && getGrid().get(neighborLoc) == null)
            {
                Location neighborLocNext = neighborLoc.getAdjacentLocation(getDirection() + d);
                if (gr.isValid(neighborLocNext) && getGrid().get(neighborLocNext) == null)
                {
                    locs.add(neighborLocNext);
                }
                else
                {
                    locs.add(neighborLoc);
                }
            }
        }
        for (Location a : locs)
        {
            if (getGrid().get(a) == null)
            {
                result.add(a);
            }
        }
        return result;
    }
}
