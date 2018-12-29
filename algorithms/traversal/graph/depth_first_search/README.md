# Graph Depth First Search (DFS)
> _Notes based on [opendatastructures.org][1]._ <br>

`Depth first search` traverses a graph by repeatedly exhausting the most immediate opportunities.
A interesting quality found in this type of action, is that if DFS does re-encounter a previous vertice on the path opportunity it is currently excersizing then this means that, this re-encounter is a path cycle.

## Recursive Implementation
For each node visit the children if they have not already been visited!
If the opportunities of a node have been exhausted mark the vertice as exhausted.
```java
    void dfs(Graph g, int r) {
        byte[] c = new byte[g.nVertices()];
        dfs(g, r, c);
    }
    void dfs(Graph g, int i, byte[] c) {
        c[i] = grey;  // currently visiting i
        for (Integer j : g.outEdges(i)) {
            if (c[j] == white) {
                c[j] = grey;
                dfs(g, j, c);
            } 
        }
        c[i] = black; // done visiting i
    }
```
> This implementation is liable to exhaust the call stack on sizable graphs. Better to use iterative implementation.

## Iterative Implementation
Similar logic,
1. Mark node as active
2. Visit Node
3. Get neighbours
4. Mark the non-active or exhausted nodes as active and push onto Stack
5. Visit those nodes.
6. Mark this vertice as exhausted.
```java
    void dfs2(Graph g, int r) {
        byte[] c = new byte[g.nVertices()];
        Stack<Integer> s = new Stack<Integer>();
        s.push(r);
        while (!s.isEmpty()) {
            int i = s.pop();
            if (c[i] == white) {
                c[i] = grey;
                for (int j : g.outEdges(i))
                    s.push(j);
            }
        }
    }
```

## DFS Illustration
![DFS Illustration][2]

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(n + m) | __O(n + m)__ | _O(n + m)_
> Iterating through `n` vertices is linear<br>
> Iterating through `m` edges, segmented per adjacency list is linear<br>

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(n)__ | __O(n)__ | __O(n)__
> in the case the queuing is occupied by the total number of nodes<br>
> the byte visited array - is okay size, would require  $`n`$ bytes

[1]: http://www.opendatastructures.org
[2]: http://opendatastructures.org/ods-java/img4699.png