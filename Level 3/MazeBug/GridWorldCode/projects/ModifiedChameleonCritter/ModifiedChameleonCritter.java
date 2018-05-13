/* 
 * @author Chris Ju
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
/**
 * A <code>ModifiedChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ModifiedChameleonCritter extends ChameleonCritter
{

    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, color darken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0)
        {
            setColor(getColor().darker());
            return;
        }
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
    }
}
