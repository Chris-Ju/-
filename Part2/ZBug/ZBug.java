/* 
 * @author Chris Ju
 */

import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a "Z". <br />
 */
public class ZBug extends Bug
{
    private int steps;         
    private int sideLength;   
    
    /*
     * times reconds in which side
     */
    private int times;      
    
    private static final int MAXTIMES = 3;

    /**
     * Constructs a Z bug that traces a Z
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;             
        times = 1;
        sideLength = length;  
    }

    /**
     * Initialize the bug turn east.
     * Because the bug turn North in the beginning, so we only use turn() twice.
     */
    public void turnEast()
    {
        turn();
        turn();
    }

    /**
     * Moves to the next location of the Z.
     * It only has three side, 
     * so the first time turn 135 degrees, 
     * and the second turn 215 degrees.
     */
    public void act()
    {
        if (steps < sideLength && canMove() && times <= MAXTIMES) 
        {                
            move();      
            steps++;    
        }
        else 
        {                
            if (times == 1)
            {
                turn();      
                turn();
                turn();
            }
            else if (times == 2)
            {
                turn();
                turn();
                turn();
                turn();
                turn();
            }     
            steps = 0;
            times++;    
        }
    }
}
