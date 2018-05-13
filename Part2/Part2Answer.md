###  Decide the side of square that the BoxBug move. If steps are larger than it, it will turn direction.
```java
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 37
sideLength = length;
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 45
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
}
```


### Record moving how many steps has moved in a side.
```java
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 36
steps = 0;
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 45
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
}
```



### Because call once turn 45 degrees, and it should be turn 90 degrees.
```java
// @file: info/gridworld/actor/Bug.java
// @line: 62-65
public void turn()
{
    setDirection(getDirection() + Location.HALF_RIGHT);
}
```


### Because BoxBug extend Bug class. And Bug class has move method.
```java
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 25
public class BoxBug extends Bug
// @file: info/gridworld/actor/Bug.java
// @line: 71-84
public void move()
{
    Grid<Actor> gr = getGrid();
    if (gr == null)
        return;
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    if (gr.isValid(next))
        moveTo(next);
    else
        removeSelfFromGrid();
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
}
```


### Yes, Because sidelength is private and you can not change it.
```java
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 28
private int sideLength;
```


### No, beacause the sidelength is fixed at the beginning. And the act method is fixed.
```java
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 37
sideLength = length;
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 43-56
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
    }
}
```


### After the bug turn direction.
```java
// @file: //GridWorld/project/boxBug/BoxBug.java
// @line: 52-54
turn();
turn();
steps = 0;
```
