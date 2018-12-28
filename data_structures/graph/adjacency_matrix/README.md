# Adjacency Matrix
> _Notes based on [opendatastructures.org][1]._ <br>

Basic __graph__ terminology,
- Directed graphs $`G`$ are binary composites of vertices $`V`$, and pairings of vertices $`E`$.
- Formally this relation ship is expressed as a graph $`G = (V,E)`$.
- Literature refers to pairings of vertices $`E`$ as $`Edges`$.
- $`n`$ is the number of vertices in a graph $`n = |V|`$
- $`m`$ is the number of edges in a graph $`m = |E|`$

Basic __edge__ terminology,
- A vertice $`V_{i}`$ which is aware of $`V_{j}`$ is called an $`Edge`$; and is expressed $`(V_{i},V_{j})`$. 
- $`V_{i}`$ is referred to as the source and $`V_{j}`$ is called the target. 
- $`V_{j}`$ is considered _reachable_ by $`V_{i}`$ if $`(V_{i},V_{j}) \in E`$.

Basic __path__ terminology,
- A Path is made of vertices like $`A = \{V_{i}, V_{i+1}, ..., V_{j} \}`$, where $`\forall k \in \{0, ..., (j-1) \}: (V_{i+k}, V_{i+k+1}) \in E`$
- A Cycle is a Path where the edge $`(V_{j}, V_{i}) \in E`$; and produces a progression of circular reference.

Graphs commonly appear like this,

![Graph Illustration][2]

Graph operations often include,
- `addEdge(i,j)` - add an edge to $`E`$
- `removeEdge(i,j)` - remove an edge from $`E`$
- `hasEdge(i,j)` - check if the edge exists in $`E`$
- `outEdges(i)` - all the edges in  $`E`$ where $`i`$ is the source
- `inEdges(i)` - all the edges in  $`E`$ where $`i`$ is the target

Graphs can be represented in way that best benefits there application.
The `Adjacency Matrix` represents a graph as a matrix. This is optimal for matrix-based alebraic equations; and when $`m \approx n^2`$.

## Storing a Adjacency Matrix
The adjacency matrix can be constructed out of arrays.
```java
    int n;
    boolean[][] a;
    AdjacencyMatrix(int n0) {
        n = n0;
        a = new boolean[n][n];
    }
```

A boolean value of $`a[i][j] == True`$ means that there exists an edge $`(V_{i},V_{j})`$. 

## Adding, Removing and Searching Edges in Adjacency Matrix
Requires accessing the dimensional array.
```java
    void addEdge(int i, int j) {
        a[i][j] = true;
    }
    void removeEdge(int i, int j) {
        a[i][j] = false;
    }
    boolean hasEdge(int i, int j) {
        return a[i][j];
    }
```

## Searching for Source and Target Vertices in Edges of Adjacency Matrix
Requires iteration through dimensional array.
```java
    List<Integer> outEdges(int i) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < n; j++) 
            if (a[i][j]) edges.add(j);
        return edges;
    }
    List<Integer> inEdges(int i) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int j = 0; j < n; j++)
            if (a[j][i]) edges.add(j);
        return edges;
    }
```

## Cost Analysis of Adjacency Matrix
- Dimensional arrays are memory inefficient when there is only a few number of edges
- Arrays take $`O(n^2)`$ words, this can be reduced by only using bits to $`O(n^2/w)`$

## Time and Space Complexity
Time,

function | best case | worst case | practical worst case
--- | :---: | :---: | :---:
`access` | O(1) | __O(1)__ | _O(1)_
`search` | O(1) | __O(n)__ | _O(1 + min{i, n-i})_
`insertion` | O(1) | __O(1)__ | _O(1)_
`deletion` | O(1) | __O(1)__ | _O(1)_
`find(x)` | O(1) | __O(1)__ | _O(1)_

Space,

best case | worst case | practical case
:---: | :---: | :---:
$`O(n^2)`$ | $`\bold{O(n^2)}`$ | $`O(n^2)`$

[1]: http://www.opendatastructures.org
[2]: http://opendatastructures.org/ods-java/img4500.png