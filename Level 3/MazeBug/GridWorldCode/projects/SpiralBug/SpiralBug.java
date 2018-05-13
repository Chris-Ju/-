/* 
 * @author Chris Ju
 */

import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> traces out a spiral. <br />
 */
public class SpiralBug extends Bug
{
    private int steps;         
    private int sideLength;    

    /**
     * Constructs a spiral bug that traces a spiral
     */
    public SpiralBug()
    {
        steps = 0;             
        sideLength = 2;  
    }

    /**
     * Moves to the next location of the Spiral.
     * The sidelength should be add 1 every time turn in order to get a spiral.
     */
    public void act()
    {
        if (steps < sideLength && canMove()) 
        {                
            move();      
            steps++;    
        }
        else
        {                
            turn();      
            turn();       
            steps = 0;    
            sideLength += 1;
        }
    }
}
