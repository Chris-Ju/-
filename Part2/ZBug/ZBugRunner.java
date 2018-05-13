/* 
 * @author Chris Ju
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains a ZBug bug. <br />
 * The bug will go in a ZBug.
 */
public final class ZBugRunner
{
    /*
     * Define the beginning position of bugs.
     * Define the side length of bugs.
     */

    private static final int ALICEX = 3;
    private static final int ALICEY = 3;
    private static final int SIDELENGTH = 4;

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();   
        ZBug alice = new ZBug(SIDELENGTH);    
        alice.setColor(Color.ORANGE); 
        alice.turnEast();

        world.add(new Location(ALICEX, ALICEY), alice);  

        world.show();
    }

    private ZBugRunner() {}                     
}