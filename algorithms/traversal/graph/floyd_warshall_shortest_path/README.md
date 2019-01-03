# Floyd-Warshall Shortest Path

Finds the shortest distance between every pair of vertices in a graph.
> "the optimal route from Point-A to Point-B via some path"! <br>
> "the fastest way to get to every other vertice" <br>
> > Can also be used for matrix inversions

Type of dynamic programming - based on `Optimal Structure`, where the optimal solution to subproblems can be incorporated into the optimal solution of the whole problem.
> Ex. the shortest path from A-C is the shortest path from A-to-B plus the shortest from B-to-C.

The idea is to build a table of results. Where the results comprise of the shortests path from a point to another point.

This can be built by comparing the distance from one vertext to the target vertex, then comparing that distance against all other distances involving an intermediate vertex.
> By calculating the shortest distance between two points - then repeating this process for all other pairs of points, you can determine the shortest distance between two points by going to the possibile intermediate point which is shorter - which you can you because you've already compared the shortest distance between that intermediate point and the point you are trying to get to.

This following pseudo code illustrates this idea,
```python
Create a |V| x |V| matrix, M, that will describe the distances between vertices
For each cell (i, j) in M:
    if i == j:
        M[i][j] = 0
    if (i, j) is an edge in E:
        M[i][j] = weight(i, j)
    else:
        M[i][j] = infinity
for k from 1 to |V|:
    for i from 1 to |V|:
        for j from 1 to |V|:
            if M[i][j] > M[i][k] + M[k][j]:
                M[i][j] = M[i][k] + M[k][j]
```
> From [brilliant.org][1]

To reconstruct the path, just record which intermediate/the original pair itself, has the shortest path for the edge (i,j) in an extra table.

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(v**3) | __O(v**3)__ | _O(v**3)_
> Iterating through `v` vertices thrice

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(v**2)__ | __O(v**2)__ | __O(v**2)__
> Iterating through `v` vertices

## Considerations
- Best for dense graphs, because this algo is not impacted by the number of edges - runtime is determined by number of vertices.

[1]: https://brilliant.org/wiki/floyd-warshall-algorithm/