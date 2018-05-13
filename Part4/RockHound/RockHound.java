/* 
 * @author Chris Ju
 */


import info.gridworld.actor.*;

import java.util.ArrayList;

/**
 * A <code>RockHound</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 */
public class RockHound extends Critter
{
    /**
     * Remove all rocks in the actor List.
     * @param actors the actors seleted
     */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor i : actors)
        {
            if (i instanceof Rock)
            {
                i.removeSelfFromGrid();
            }
        }
    }
}
