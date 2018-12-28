# Adjacency Lists
> _Notes based on [opendatastructures.org][1]._ <br>

Takes a vertex centric approach and stores all the vertices adjacent to each vertice in the set the Graph.

## Storing Adjacency Lists
```java
    int n;
    List<Integer>[] adj;
    AdjacencyLists(int n0) {
        n = n0;
        adj = (List<Integer>[])new List[n];
        for (int i = 0; i < n; i++) 
            adj[i] = new ArrayStack<Integer>(Integer.class);
    }
```

## Adding in Adjacency Matrix
```java
    void addEdge(int i, int j) {
        adj[i].add(j);
    }
```

## Remove Edge from Adjacency Lists
1. Get the list for the given call `remove(i,j)`
2. Iterate over the list and remove $`j`$ entries
```java
    void removeEdge(int i, int j) {
        Iterator<Integer> it = adj[i].iterator();
        while (it.hasNext()) {
            if (it.next() == j) {
                it.remove();
                return;
            }
        }    
    }
```

## Edge Checking in Adjacency Lists
To check if an edge exists,
1. Get list for given vertice $`i`$ in call `hasEdge(i,j)`
2. Check for existence
```java
    boolean hasEdge(int i, int j) {
        return adj[i].contains(j);
    }
```

To obtain all the edges with $`i`$ as the source vertice,
1. Return the adjacency list belonging to the source vertice in the call `outEdges(i)`
```java
   List<Integer> outEdges(int i) {
        return adj[i];
    }
```

To obtain all thedeges with the target vertice $`i`$ in the call `inEdges(i)`,
1. Iterate across all adjacency lists, checking for target vertice $`i`$
```java
    List<Integer> inEdges(int i) {
        List<Integer> edges = new ArrayStack<Integer>(Integer.class);
        for (int j = 0; j < n; j++)
            if (adj[j].contains(i))    edges.add(j);
        return edges;
    }
```

## Time and Space Complexity
Time,

function | best case | worst case | practical worst case
--- | :---: | :---: | :---:
`addEdge(i,j)` | O(1) | __O(1)__ | _O(1)_
`removeEdge(i,j)` | O(1) | __O(n)__ | _O(dgr(i))_
`hasEdge(i,j)` | O(1) | __O(n)__ | _O(dgr(i))_
`outEdges(i)` | O(1) | __O(1)__ | _O(1)_
`inEdges(i)` | O(1) | __O(m + n)__ | _O(m + n)_

Space,

best case | worst case | practical case
:---: | :---: | :---:
$`O(n^2)`$ | $`\bold{O(n^2)}`$ | $`O(m + n)`$

## Considerations
- what data-structure to use of adjacency lists
- should another adjacency list be maintained for all vertices targeting a vertice
- should there be composite references referring to source-style adjacency lists and target-style adjacency lists
- are __vertices__ first class objects, or are __edges__?

[1]: http://www.opendatastructures.org