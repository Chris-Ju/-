/* 
 * @author Chris Ju
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.awt.Color;


/**
 * This class runs a world that contains QuickCrab critters. <br />
 */
public final class QuickCrabRunner
{
    private static final int SEVEN = 7;
    private static final int THREE = 3;
    private static final int EIGHT = 8;
    private static final int TWO = 2;
    private static final int ONE = 1;
    private static final int FOUR = 4;
    private static final int FIVE = 5;

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN, EIGHT), new Bug());
        world.add(new Location(THREE, THREE), new Bug());
        world.add(new Location(TWO, EIGHT), new Bug(Color.BLUE));
        world.add(new Location(FIVE, FIVE), new Bug(Color.PINK));
        world.add(new Location(ONE, FIVE), new Bug(Color.RED));
        world.add(new Location(SEVEN, TWO), new Bug(Color.YELLOW));
        world.add(new Location(FOUR, FOUR), new QuickCrab());
        world.show();
    }

    private QuickCrabRunner() {}
}