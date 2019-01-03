# Dijkstra Shortest Path
> _Notes based on [brilliant.org][1]._ <br>

Find shortest path from source node to target node in weighted graph.
By creating tree of shortests paths from source vertex to all other points in graph.
> can be used on directed or undirected graph.<br>
> can NOT have negative weights

DIfferent from `Bellman-Ford` in that it iterates through all vertices.
Different from `Bellman-Ford` in that it relaxes for the next node with the shortest distance
Different from `Bellman-Ford` in that it doesn't relax all edges - just the outEdges of the current greedy vertex!

Sample pseudo code,
```
function Dijkstra(Graph, source):
       dist[source]  := 0                     // Distance from source to source is set to 0
       for each vertex v in Graph:            // Initializations
           if v ≠ source
               dist[v]  := infinity           // Unknown distance function from source to each node set to infinity
           add v to Q                         // All nodes initially in Q

      while Q is not empty:                  // The main loop
          v := vertex in Q with min dist[v]  // In the first run-through, this vertex is the source node
          remove v from Q 

          for each neighbor u of v:           // where neighbor u has not yet been removed from Q.
              alt := dist[v] + length(v, u)
              if alt < dist[u]:               // A shorter path to u has been found
                  dist[u]  := alt            // Update distance of u 

      return dist[]
  end function
```
> from [brilliant.org][1]

```c++
// Dijkstra shortest distance
vector<long> dijkstraPriorityQueue(vector<vector<int>> &graph,
                                   int source) {

    // Queue of pairs of distance to node number
    priority_queue<pair<int, int>, vector<pair<int, int>>,
                   greater<pair<int, int>>>
            q;
    vector<long> d(graph.size(), INT_MAX);
    d[source] = 0;

    q.push(make_pair(0, source));

    while (!q.empty()) {
        // Find minimum
        int u = q.top().second;
        q.pop();

        // Relax distances
        // Rather than decreasing the values in the queue,
        // we just add the updated distance to the queue again.
        for (int j = 0; j < graph.size(); j++) {
            if (d[j] > d[u] + graph[u][j]) {
                d[j] = d[u] + graph[u][j];
                q.push(make_pair(d[j], j));
            }
        }
    }
    return d;
```
> From [hackernoon.com][2]

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(v**2) | __O(v**2)__ | _O(v**2)_
> Iterating through `v` vertices<br>
> Iterating through `e` edges<br>
> Can use min-heap to make `O((m+n)log(n))`<br>
> Can use Fibonacci heap `O(m + nlogn)`

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(v**2)__ | __O(v**2)__ | __O(v**2)__
> With `v` vertices

[1]: https://brilliant.org/wiki/dijkstras-short-path-finder/
[2]: https://hackernoon.com/shortest-and-longest-path-algorithms-job-interview-cheatsheet-2adc8e18869