import static org.junit.Assert.*;

import info.gridworld.actor.*;
import info.gridworld.grid.Location;

import org.junit.Before;
import org.junit.Test;

public class JumperTest {

    private static final int ONE = 1;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int NINTY = 90;
    private static final int ZERO = 0;

    private ActorWorld world;
    private Jumper test;
    private Rock rock;
    private Flower flower;
    private Bug bug;

    @Before
    public void setUp() throws Exception {
        world = new ActorWorld();
        test = new Jumper();
        rock = new Rock();
        flower = new Flower();
        bug = new Bug();
    }

    @Test
    //when there is nothing in front of a jumper
    public void noBoundary() {
        world.add(new Location(FIVE, FIVE), test);
        test.act();
        assertEquals(test.getLocation(), new Location(THREE, FIVE));
        assertEquals(test.getDirection(), ZERO);
    }

    @Test
    //when there is a grid between a jumper to a rock
    public void rockInfront() {
        world.add(new Location(FIVE, FIVE), test);
        world.add(new Location(THREE, FIVE), rock);
        test.act();
        assertEquals(test.getLocation(), new Location(FOUR, FIVE));
        assertEquals(test.getDirection(), ZERO);
    }

    @Test
    //when a jumper facing a rock
    public void facingRock() {
        world.add(new Location(FIVE, FIVE), test);
        world.add(new Location(FOUR, FIVE), rock);
        test.act();
        assertEquals(test.getLocation(), new Location(THREE, FIVE));
        assertEquals(test.getDirection(), ZERO);
    }

    @Test
    //when there is a grid between a jumper to a flower
    public void flowerInfront() {
        world.add(new Location(FIVE, FIVE), test);
        world.add(new Location(THREE, FIVE), flower);
        test.act();
        assertEquals(test.getLocation(), new Location(THREE, FIVE));
        assertEquals(test.getDirection(), ZERO);
    }

    @Test
    //when a jumper facing a flower
    public void facingFlower() {
        world.add(new Location(FIVE, FIVE), test);
        world.add(new Location(FOUR, FIVE), flower);
        test.act();
        assertEquals(test.getLocation(), new Location(THREE, FIVE));
        assertEquals(test.getDirection(), ZERO);
    }

    @Test
    //when there is a grid between a jumper to a bug
    public void bugInfront() {
        world.add(new Location(FIVE, FIVE), test);
        world.add(new Location(THREE, FIVE), bug);
        test.act();
        assertEquals(test.getLocation(), new Location(FOUR, FIVE));
        assertEquals(test.getDirection(), ZERO);
    }

    @Test
    //when a jumper facing a bug
    public void facingBug() {
        world.add(new Location(FIVE, FIVE), test);
        world.add(new Location(FOUR, FIVE), bug);
        test.act();
        assertEquals(test.getLocation(), new Location(THREE, FIVE));
        assertEquals(test.getDirection(), ZERO);
    }

    @Test
    //when there is a grid between a jumper to the wall
    public void wallInfront() {
        world.add(new Location(ONE, ZERO), test);
        test.act();
        assertEquals(test.getLocation(), new Location(ZERO, ZERO));
        assertEquals(test.getDirection(), ZERO);
    }

    @Test
    //when a jumper facing the wall
    public void facingWall() {
        world.add(new Location(ZERO, ZERO), test);
        test.act();
        assertEquals(test.getLocation(), new Location(ZERO, ZERO));
        assertEquals(test.getDirection(), NINTY);
    }
}