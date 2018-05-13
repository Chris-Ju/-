### Define in Grid.java and Implement in BoundedGrid.java
```java
// @file: info/gridworld/grid/Grid.java
// @line: 50
    boolean isValid(Location loc);

// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60-64
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```

### BoudedGrid, UnboundedGrid has no position which is invalid.
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60-64
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```

### getOccupiedAdjacentLocations.  In AbstractGrid
```java
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 62-71
public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
{
    ArrayList<Location> locs = new ArrayList<Location>();
    for (Location neighborLoc : getValidAdjacentLocations(loc))
    {
        if (get(neighborLoc) != null)
            locs.add(neighborLoc);
    }
    return locs;
}
```

### To decide whether the position ie empty. If empty, add the position.
```java
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 51-60
public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
{
    ArrayList<Location> locs = new ArrayList<Location>();
    for (Location neighborLoc : getValidAdjacentLocations(loc))
    {
        if (get(neighborLoc) == null)
            locs.add(neighborLoc);
    }
    return locs;
}
```


### 45 90
```java
// @file: info/gridworld/grid/Location.java
// @line: 40 48
public static final int RIGHT = 90;
public static final int HALF_RIGHT = 45;
```

### The construct method
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 39-46
public BoundedGrid(int rows, int cols)
{
    if (rows <= 0)
        throw new IllegalArgumentException("rows <= 0");
    if (cols <= 0)
        throw new IllegalArgumentException("cols <= 0");
    occupantArray = new Object[rows][cols];
}
```

### By use array length method. Use two-dimensional array.
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 53-58
public int getNumCols()
{
    // Note: according to the constructor precondition, numRows() > 0, so
    // theGrid[0] is non-null.
    return occupantArray[0].length;
}
```

### Row and Column Position
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60-64
public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```

### ArrayList<Location> O(r * c)
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

### Object. r and c are needed. O(1)
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 85-91
public E get(Location loc)
{
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
}
```

### The position is invalid or the object is null. O(1)
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 93-105
public E put(Location loc, E obj)
{
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    if (obj == null)
        throw new NullPointerException("obj == null");

    // Add the object to the grid.
    E oldOccupant = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = obj;
    return oldOccupant;
} 
```


### Object. It will return null. O(1)
```java
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 107-117
public E remove(Location loc)
{
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    // Remove the object from the grid.
    E r = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = null;
    return r;
}
```

### Yes. Because the O is 1.


### getRow() and getColumn(). The sequence is needed. But by row and column it can be obtained. Yes.


### Because it stores occupants in a Map<Location, E>. If without check, it will be error. Because BoundedGrid uses two-dimensional array to store.

### O(1). If instead, it will be O(log n)

### I think there is no difference.

### Yes, it can. Two-dimensional array is stable and efficient.



|Methods | SparseGridNode version | LinkedList<OccupantInCol> version | HashMap version | TreeMap version
|-|-|-|-|-|
|getNeighbors | O(c) | O(c) | O(1) | O(log n) |
|getEmptyAdjacentLocations | O(c) | O(c) |  O(1) | O(log n) |
|getOccupiedAdjacentLocations| O(c) | O(c) |  O(1) | O(log n) |
|getOccupiedLocations| O(c+n) | O(c+n) |  O(n) | O(n) |
|get| O(c) | O(c) |  O(1) | O(log n) |
|put| O(c) | O(c) |  O(1) | O(log n) |
|remove| O(c) | O(c) |  O(1) | O(log n) |


- get method: O(1)
- put method: O(1)
- resize    : O(newSize ^ 2)