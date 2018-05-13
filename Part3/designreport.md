# Inception: clarify the details of the problem:

- a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?
  - Jump a step.
- b. What will a jumper do if the location two cells in front of the jumper is out of the grid?
  - Jump a step. 
- c. What will a jumper do if it is facing an edge of the grid?
  - Invoke turn method twice.
- d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?
  - Jump above it.
- e. What will a jumper do if it encounters another jumper in its path?
  - Jump above it.
- f. Are there any other tests the jumper needs to make?
  - I have no idea.