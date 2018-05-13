/* 
 * @author Chris Ju
 */

import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> Dancing. Turn times by given array. <br />
 */
public class DancingBug extends Bug
{     
    private int   time;
    private int[] times;

    /**
     * Constructs a dancing bug that turn times of a given times array.
     * @param times[] turn times
     */
    public DancingBug(int[] gtimes)
    {
        time = 0;
        this.times = gtimes.clone();
    }


    /**
     * Turn times by given number
     * @param number turn times
     */
    private void turnTime(int number)
    {
        for(int i = 0; i < number; i++)
        {
            turn();
        }
    }


    /**
     * Moves to the next location of the circle.
     */
    public void act()
    {
        if(time >= times.length)
        {
            time = 0;
        }
        turnTime(times[time]);
        move();
        time++;
    }
}
