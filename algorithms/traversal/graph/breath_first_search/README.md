# Graph Breath First Search (BFS)
> _Notes based on [opendatastructures.org][1]._ <br>

Visit proximal vertices before vertices of another rank.

# Algorithm
Same as tree. Need to keep track of visited node.
1. Create struct to store visted nodes
2. Create q ,and enqueue the root node
3. mark the root node as visited
4. Begin BFS
5. Get all neighbours adding them to q, if they have not been visited
6. upon adding to q mark as visited
```java
    void bfs(Graph g, int r) {
        boolean[] seen = new boolean[g.nVertices()]; // keep track of nodes
        Queue<Integer> q = new SLList<Integer>();
        q.add(r);
        seen[r] = true;
        while (!q.isEmpty()) {
            int i = q.remove();
            for (Integer j : g.outEdges(i)) {
                if (!seen[j]) {
                    q.add(j);
                    seen[j] = true;
                }
            }
        }
    }
```
> Nodes visited are dependent on the order in the `Adjacency List` <br>
> BFS ensures a vertice at $`depth(u) = k`$ is never visted before vertices at $`depth(u) = k - 1`$ <br>

Sketch for Shortest Path Algo,
1. Create array z of size `m` - the number of vertices
2. When visiting new vertice, register that new vertice was last visited by the current vertice
3. Continue until find node
4. Look at the registry entries working backwards from the current node. Repeat till at root.
> A new node can only be visited once. The only time a nodes source origin can be recorded is when it is added to the queue. Therefor, this registry entry can only ever be written once - and not overwritten. Upon finding the search node, the registered data will contain all the previously recorded registry entries and their respective source vertices. BFS guarantees to visit vertices oridinally according to depth, never traversing a path that is deeper than the current depth of BFS. This means that consulting the registered edges will always have the shortest unaltered path from the searched vertice to the root. 

Here is a glimpse of how a Graph is traversed!

![Glimpse of Traversal][2]

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(n + m) | __O(n + m)__ | _O(n + m)
> Iterating through `n` vertices is linear<br>
> Iterating through `m` edges, segmented per adjacency list is linear<br>

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(n)__ | __O(n)__ | __O(n)__
> in the case the queuing is occupied by the total number of nodes<br>
> the boolean visited array - is okay size, would require  $`(n / 8) bytes`$

[1]: http://www.opendatastructures.org
[2]: http://opendatastructures.org/ods-java/img4647.png