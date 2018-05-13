/* 
 * @author Chris Ju
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains circle bugs. <br />
 * The bug will go in a hexagon.
 */
public final class CircleBugRunner
{
    /*
     * Define the circle side and the beginning position of bugs.
     */
    private static final int ALICESIDE  = 6;
    private static final int BOBSIDE = 3;
    private static final int ALICEX = 7;
    private static final int ALICEY = 8;
    private static final int BOBX = 5;
    private static final int BOBY = 5;


    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();   
        CircleBug alice = new CircleBug(ALICESIDE);    
        alice.setColor(Color.ORANGE);

        CircleBug bob = new CircleBug(BOBSIDE);  

        world.add(new Location(ALICEX, ALICEY), alice);  
        world.add(new Location(BOBX, BOBY), bob);

        world.show();
    }

    private CircleBugRunner() {}                     
}