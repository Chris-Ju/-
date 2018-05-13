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
public class KingCrab extends CrabCritter
{
    /**
     *  A KingCrab causes each actor that it processes to 
     *  move one location further away from the KingCrab.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        Grid gr = getGrid();
        Location loc = getLocation();
        for (Actor i : actors)
        {
            Location neighborLoc = i.getLocation();
            int direction = loc.getDirectionToward(neighborLoc);
            Location nextLoc = neighborLoc.getAdjacentLocation(direction);
            if(gr.isValid(loc) && getGrid().get(nextLoc) == null)
            {
                i.moveTo(nextLoc);
            }
            else
            {
                i.removeSelfFromGrid();
            }
        }
    } 
}
