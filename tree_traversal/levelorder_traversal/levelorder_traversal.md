# Levelorder Traversal
Notes from - https://www.geeksforgeeks.org/level-order-tree-traversal/

- Similar to decontructing a layered cake from the top
- Top level, the the level beneath the top level, etc.

## Algorithm
1. Visit the root
2. Get all the nodes on a level - max 2**h
3. Do stuff
4. Get all th enodes on the next level from the previous level's state

## Notes
- Good for finding nodes closer to the root
- Consumes less memory when the tree is skewed
- Consumes more memory when the tree is balanced
