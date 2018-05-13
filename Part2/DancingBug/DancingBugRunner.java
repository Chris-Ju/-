/* 
 * @author Chris Ju
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains a dancing bug. <br />
 * The bug will go in a random dancing.
 */
public final class DancingBugRunner
{
    /*
     * Define the beginning position of bugs.
     * Define the turn times array.
     */

    private static final int ALICEX = 3;
    private static final int ALICEY = 3;
    private static final int ARRAY[] = {1, 2, 3, 3, 2, 5, 7, 8, 3};

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();   
        DancingBug alice = new DancingBug(ARRAY);    
        alice.setColor(Color.ORANGE); 

        world.add(new Location(ALICEX, ALICEY), alice);  

        world.show();
    }

    private DancingBugRunner() {}                     
}