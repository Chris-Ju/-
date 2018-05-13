/* 
 * @author Chris Ju
 */

 
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.*;

import java.awt.Color;

/**
 * This class runs a world that contains Jumper. <br />
 */
public final class JumperRunner
{
    
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        alice.setColor(Color.ORANGE);

        world.add(alice);
        world.add(new Rock());
        world.add(new Bug());

        world.show();
    }

    private JumperRunner() {}
}