```java
// @file: info/gridworld/actor/Critter.java
// @line: 38 56 71 88 104 125
public void act()
ArrayList<Actor> getActors();
void processActors(ArrayList<Actor> actors);
ArrayList<Location> getMoveLocations();
Location selectMoveLocation(ArrayList<Location> locs);
void makeMove(Location loc);
```


### getActor() processActors() seletMoveLocation() movelocs() makemove()
```java
// @file: info/gridworld/actor/Critter.java
// @line: 38-47
public void act()
{
    if (getGrid() == null)
        return;
    ArrayList<Actor> actors = getActors();
    processActors(actors);
    ArrayList<Location> moveLocs = getMoveLocations();
    Location loc = selectMoveLocation(moveLocs);
    makeMove(loc);
}
```

### It should not be. Because getActor() invoke the foundamental function and can get what you want.
```java
// @file: info/gridworld/actor/Critter.java
// @line: 56-59
public ArrayList<Actor> getActors()
{
    return getGrid().getNeighbors(getLocation());
}
```

### If the actor is not rock or Critter, it will be removed like being eaten.
```java
// @file: info/gridworld/actor/Critter.java
// @line: 71-78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```

### getMoveLocations selectMoveLocation and makeMove
```java
// @file: info/gridworld/actor/Critter.java
// @line: 44-46
ArrayList<Location> moveLocs = getMoveLocations();
Location loc = selectMoveLocation(moveLocs);
makeMove(loc);
```
- getMoveLocations is to find empty girds.
- selectMoveLocation is to get a location randomly.
- makeMove is to invoke moveTo method.

### Because it need not to pass param or initialize.


## Because act method invokes the processActors method, and processActors is overwritten.
```java
// @file: project/critters/ChameleonCritter.java
// @line: 36-45
public void processActors(ArrayList<Actor> actors)
{
    int n = actors.size();
    if (n == 0)
        return;
    int r = (int) (Math.random() * n);

    Actor other = actors.get(r);
    setColor(other.getColor());
}
```

### Because father class has defined a move method, param is position, when it gives a position, it need not to write method, just invoke father makeMove.
```java
// @file: project/critters/ChameleonCritter.java
// @line: 50-54
public void makeMove(Location loc)
{
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
}
```

### Write in makeMove method.
```java
public void makeMove(Location loc)
{
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, getlocation());
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
}
```

### Because its needed actors are the same as Critter class, not need to overwrite, the neighbors.
```java
// @file: info/gridworld/actor/Critter.java
// @line: 56-59
public ArrayList<Actor> getActors()
{
    return getGrid().getNeighbors(getLocation());
}
```

### Actor
```java
// @file: info/gridworld/actor/Actor.java
// @line: 102-105
public Location getLocation()
{
    return location;
}
```

### Invoke getGrid method
```java
// @file: info/gridworld/actor/Actor.java
// @line: 92-95
public Grid<Actor> getGrid()
{
    return grid;
}
```

### Because in the Critter class, processActor is to eat actor.
```java
// @file: info/gridworld/actor/Critter.java
// @line: 71-78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```

### No, it only can eat the actor immediately in front, to the right-front, or to the left-front of it.
```java
// @file: project/critters/CrabCritter.java
// @line: 44-57
public ArrayList<Actor> getActors()
{
    ArrayList<Actor> actors = new ArrayList<Actor>();
    int[] dirs =
        { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
    for (Location loc : getLocationsInDirections(dirs))
    {
        Actor a = getGrid().get(loc);
        if (a != null)
            actors.add(a);
    }

    return actors;
}
```

### Because the CrabCritter can only move in left and right.
```java
// @file: project/critters/CrabCritter.java
// @line: 62-72
public ArrayList<Location> getMoveLocations()
{
    ArrayList<Location> locs = new ArrayList<Location>();
    int[] dirs =
        { Location.LEFT, Location.RIGHT };
    for (Location loc : getLocationsInDirections(dirs))
        if (getGrid().get(loc) == null)
            locs.add(loc);

    return locs;
}
```

### A bug actor.

### All positions are neighbors. All positions are selected randomly. But CrabCritter only can move in left and right.

### Turn 90 degrees to right or left
```java
// @file: project/critters/CrabCritter.java
// @line: 77-91
public void makeMove(Location loc)
{
    if (loc.equals(getLocation()))
    {
        double r = Math.random();
        int angle;
        if (r < 0.5)
            angle = Location.LEFT;
        else
            angle = Location.RIGHT;
        setDirection(getDirection() + angle);
    }
    else
        super.makeMove(loc);
}
```

### Eat rock? Teeth are ache... 
```java
// @file: info/gridworld/actor/Critter.java
// @line: 71-78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```
