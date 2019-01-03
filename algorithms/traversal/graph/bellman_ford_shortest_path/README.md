# Bellman Ford Shortest Path

Finds shortest path between one origin vertex and and all other vertices. Can be used on weighted and unweighted graph.

- guarantee to find shortest path
- slower than `Dijkstra`, but can handle negative edge weights
    - cant handle negative cycle, bc there will be no shortest path - endless
    - bellman-ford can detect negative cycles

Uses the idea of relaxation to find shortness improvmenets. However this has two difficulties,
1. negative weight cycles -> shortest path will go forever
2. bad ordering for relaxations -> exponential relaxations

The value in this algo, is that it determines the ordering of relaxations. It relaxes all edges.
> Whereas Dijstra is greedy and only selects nearest vertex that isn't processed.

If there are `m` edges, than they are relaxed `|V| - 1` times. `V` is the set of verts.

## Relaxation
A relaxation is the correction of an estimate using other data.
> BF does thi sby shrinking the distance between two vertices by comparing against other known distances

Can do this because of triangle inequality: sum of two lengths of a triangle is greater the length of other side.
> Bc, straightline is shortest path between two points.

That is $`distance(A,C) \leq distance(A,B) + distance(B,C)`$
> that is it can't be more than a,b plus b,c - as that would defy the definition of ab - bc.

Without any negative weight cycles, the shortest paths will always be simple. There will be no repitition of edges.
Therefor the shortest path has `|V*| vertices and `|V*-1|` edges, relative to the vertex the distance is being calculated for.

```python
for v in V:
    v.distance = infinity
    v.p = None
source.distance = 0
for i from 1 to |V| - 1:
    for (u, v) in E:
        relax(u, v)
for (u, v) in E: // how to detect negative cycle
    if v.distance > u.distance + weight(u, v):
        print "A negative weight cycle exists"

relax(u, v):
    if v.distance > u.distance + weight(u, v):
        v.distance = u.distance + weight(u, v)
        v.p = u
```
> From [brilliant.org][1]

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(e) | __O(v*e)__ | _O(v*e)_
> Iterating through `v` vertices<br>
> Iterating through `e` edges<br>
> Don't need to always iterate through all vertices. sometimes only need to go through all edges.

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(v**2)__ | __O(v**2)__ | __O(v**2)__
> With `v` vertices

## Consideration
- good for routing
    - must limit number of hops in endless graph

[1]: https://brilliant.org/wiki/bellman-ford-algorithm/