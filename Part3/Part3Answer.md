### loc1.getRow()
```java
// @file: info/gridworld/grid/Location.java
// @line: 110-113
public int getRow()
{
    return row;
}
```

### flase
```java
// @file: info/gridworld/grid/Location.java
// @line: 205-212
public boolean equals(Object other)
{
    if (!(other instanceof Location))
        return false;

    Location otherLoc = (Location) other;
    return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
}
```

### (3, 5)
```java
// @file: info/gridworld/grid/Location.java
// @line: 130-139 147-148 168
public Location getAdjacentLocation(int direction)
{
    // reduce mod 360 and round to closest multiple of 45
    int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
    if (adjustedDirection < 0)
        adjustedDirection += FULL_CIRCLE;

    adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
    int dc = 0;
    int dr = 0;
    if (adjustedDirection == SOUTH)
        dr = 1;
    return new Location(getRow() + dr, getCol() + dc);
}
```

### 135
```java
// @file: info/gridworld/grid/Location.java
// @line: 178-195
public int getDirectionToward(Location target)
{
    int dx = target.getCol() - getCol();
    int dy = target.getRow() - getRow();
    // y axis points opposite to mathematical orientation
    int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

    // mathematical angle is counterclockwise from x-axis,
    // compass angle is clockwise from y-axis
    int compassAngle = RIGHT - angle;
    // prepare for truncating division by 45 degrees
    compassAngle += HALF_RIGHT / 2;
    // wrap negative angles
    if (compassAngle < 0)
        compassAngle += FULL_CIRCLE;
    // round to nearest multiple of 45
    return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
}
```

### Because the grid consists of coordinates, and the direction is determined by numbers.

### For the BoundedGrid, we can use getOccupiedLocations method and then get the return array count.
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 66-83
public ArrayList<Location> getOccupiedLocations()
{
    ArrayList<Location> theLocations = new ArrayList<Location>();

    // Look at all grid locations.
    for (int r = 0; r < getNumRows(); r++)
    {
        for (int c = 0; c < getNumCols(); c++)
        {
            // If there's an object at this location, put it in the array.
            Location loc = new Location(r, c);
            if (get(loc) != null)
                theLocations.add(loc);
        }
    }

    return theLocations;
}
```
### For the UnboundedGrid, we also can use getOccupiedLocations method and then get the return array count.
```java
// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 58-64
public ArrayList<Location> getOccupiedLocations()
{
    ArrayList<Location> a = new ArrayList<Location>();
    for (Location loc : occupantMap.keySet())
        a.add(loc);
    return a;
}
```
### Use all position number substracts the occupied location.


### Use isValid method, if return true, it is value. Or not value. 
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60-64
public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```

### In his child class AbstractGrid and grandchild class BoundedGrid and UnboundedGrid, Grid class is an interface and AbstractGrid is an abstract class, they can not be instantiation. And all the method must be implemented in child class.
```java
// @file: info/gridworld/grid/AbstractGrid.java
// @file: info/gridworld/grid/BoundedGrid.java
// @file: info/gridworld/grid/UnboundedGrid.java
```

### Yes, I think so. Java's ArrayList is like cpp's Vector. It overcomes the disadvantage that can not lengthen the size. The object we can not decide the number of, so I think ArrayList is better.

### location direction color
```java
// @file: info/gridworld/actor/Actor.java
// @line: 32-34
private Location location;
private int direction;
private Color color;
```

### Color is blue and direction is north
```java
// @file: info/gridworld/actor/Actor.java
// @line: 40-45
public Actor()
{
    color = Color.BLUE;
    direction = Location.NORTH;
    grid = null;
    location = null;
}
```

### Because I think Actor class can be instantiation, and some method should be implemented.

### An actor can not put itself into a grid twice without first removing itself
```java
// @file: info/gridworld/actor/Actor.java
// @line: 117-119
if (grid != null)
    throw new IllegalStateException(
            "This actor is already contained in a grid.");
```
### An actor can not remove itself from a grid twice
```java
// @file: info/gridworld/actor/Actor.java
// @line: 135-137
if (grid == null)
    throw new IllegalStateException(
            "This actor is not contained in a grid.");
```
### An actor can be placed into a grid, remove itself, and then put itself back.

### Use setDirection method
```java
// @file: info/gridworld/actor/Actor.java
// @line: 80-85
public void setDirection(int newDirection)
{
    direction = newDirection % Location.FULL_CIRCLE;
    if (direction < 0)
        direction += Location.FULL_CIRCLE;
}
```


### Below code
```java
// @file: info/gridworld/actor/Bug.java
// @line: 98-99
if (!gr.isValid(next))
    return false;
```

### Below code
```java
// @file: info/gridworld/actor/Bug.java
// @line: 100-101
Actor neighbor = gr.get(next);
    return (neighbor == null) || (neighbor instanceof Flower);
```

### isValid and get! In order to get grid information and decide.
```java
// @file: info/gridworld/actor/Bug.java
// @line: 96-99
Location loc = getLocation();
if (!gr.isValid(next))
    return false;
```

### getAdjacentLocation! In order to get the position in this direction.
```java
// @file: info/gridworld/actor/Bug.java
// @line: 97
Location next = loc.getAdjacentLocation(getDirection());
```

### getLocation!
```java
// @file: info/gridworld/actor/Actor.java
// @line: 102-105
public Location getLocation()
{
    return location;
}
```

### removeSelfFromGrid
```java
// @file: info/gridworld/actor/Bug.java
// @line: 78-81
if (gr.isValid(next))
    moveTo(next);
else
    removeSelfFromGrid();
```

### I do not think so. Because if so, you should change it after every time move.

### Because the constuct function
```java
// @file: info/gridworld/actor/Bug.java
// @line: 82
Flower flower = new Flower(getColor());
```

### If it removes itself because of out of grid, it will place a flower. If not, it will not.
```java
// @file: info/gridworld/actor/Bug.java
// @line: 78-83
if (gr.isValid(next))
    moveTo(next);
else
    removeSelfFromGrid();
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```

### Below code
```java
// @file: info/gridworld/actor/Bug.java
// @line: 82-83
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```

### Four times, because every time turn 45 degrees.
```java
// @file: info/gridworld/actor/Bug.java
// @line: 62-64
public void turn()
{
    setDirection(getDirection() + Location.HALF_RIGHT);
} 
```
