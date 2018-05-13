/* 
 * @author Chris Ju
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains a Spiral bug. <br />
 * The bug will go in a spiral.
 */
public final class SpiralBugRunner
{
    /*
     * Define the beginning position of bugs.
     */

    private static final int ALICEX = 0;
    private static final int ALICEY = 0;


    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();   
        SpiralBug alice = new SpiralBug();    
        alice.setColor(Color.ORANGE); 

        world.add(new Location(ALICEX, ALICEY), alice);  

        world.show();
    }

    private SpiralBugRunner() {}                     
}