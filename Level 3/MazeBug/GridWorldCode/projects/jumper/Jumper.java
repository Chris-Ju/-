/* 
 * @author Chris Ju
 */



import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.*;

import java.awt.Color;



/**
 * An <code>Jumper</code> is an entity with a color and direction that can jumper above other actor.
 * <br />
 */
public class Jumper extends Actor
{
    private static final int ONE = 1;
    private static final int TWO = 2;

    /**
     * Constructs a green Jumper that is facing north.
     */
    public Jumper()
    {
        setColor(Color.green);
        setDirection(0);
    }

    /**
     * Constructs a Jumper with a given color
     * @param color the color of jumper
     */
    public Jumper(Color color)
    {
        setColor(color);
        setDirection(0);
    }


    /**
     * Tests whether this jumper can move forward into a location that is empty or
     * contains a flower.
     * And if the position it will jump is occupied, decide wheher the position forward it.
     * If both are occupied, it can not move. 
     * @return 1 if this jumper can move 1 step.
     * @return 2 if this jumper can move 2 step.
     * @return 0 if this jumper can not move.
     */
    private int canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
        {
            return 0;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextNext = next.getAdjacentLocation(getDirection());
        if (gr.isValid(nextNext))
        {
            Actor neighbor = gr.get(nextNext);
            if ( isEmptyOrFlower(neighbor) )
            {
                return 2;
            }
        }
        if (gr.isValid(next))
        {
            Actor neighbor = gr.get(next);
            if( isEmptyOrFlower(neighbor) )
            {
                return 1;
            }
        }
        return 0;
        
        // ok to move into empty location or onto flower
        // not ok to move onto any other Actor
    }

    /**
     * Test if given position is empty or flower.
     * @param neighbor given position
     * @return true if it is empty or flower
     */
    private boolean isEmptyOrFlower(Actor neighbor)
    {
        return (neighbor == null) || (neighbor instanceof Flower);
    }


    /**
     * Moves the Jumper forward, putting a flower into the location it previously
     * occupied.
     */
    public void move(int step)
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (step == 2)
        {
            Location nextNext = next.getAdjacentLocation(getDirection());
            moveTo(nextNext);
        }
        else
        {
            moveTo(next);
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    /**
     * Moves if it can move, turns otherwise.
     * 
     */
    public void act()
    {
        if (canMove() == TWO)
        {
            move(TWO);
        }
        else if (canMove() == ONE)
        {
            move(ONE);
        }
        else
        {
            turn();
        }
    }

    /**
     * Turns the bug 90 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.RIGHT);
    }
    
}