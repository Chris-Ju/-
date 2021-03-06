
/**
 * @author Chris Ju
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

/**
 * This class is to test additional grid class.
 */
public final class SparseGridRunner 
{

    private static final int TWO = 2;

    public static void main(String[] args) 
    {
        ActorWorld world = new ActorWorld();
        //world.addGridClass("SparseBoundedGrid");
        //world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("UnboundedGrid2");
        world.add(new Location(TWO, TWO), new Critter());
        world.show();
    }
    private SparseGridRunner() {}
}