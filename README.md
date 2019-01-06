# ALGORITHMS & DATA-STRUCTURES
Study of algorithms and data-structures
> _[opendatastructures.org][1] was used to produce this content. Thank you so much!_ <br>
> _[brilliant.org][2] was used to produce this content. Thank you so much!_ <br>
> _[en.wikipedia.org][4] was used to produce this content. Thank you so much!_ <br>
> _[comscigate.com][5] was used to produce this content. Thank you so much!_ <br>
> Please __see__ the full list [here](./BIBLIOGRAPHY.md).

![Complexity Chart][0]

## DATA STRUCTURES

#### GENERAL
Structure | Avg. Access | Avg. Search | Avg. Insertion | Avg. Delete | Worst Access | Worst Search | Worst Insertion | Worst Delete | Worst Space
:--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---:
[Array Stack](data_structures/array/array_stack/) | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$<sup>A</sup> | $`\colorbox{yellow} {O(n)}`$<sup>A</sup> | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$<sup>A</sup> | $`\colorbox{yellow} {O(n)}`$<sup>A</sup> | $`\colorbox{yellow} {O(n)}`$
[Rootish Array Stack](data_structures/array/rootish_array_stack/) | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {n + O(sqrt(n))}`$
[Array Queue](data_structures/array/array_queue/) | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | _$`\colorbox{yellow} {O(n)}`$_
[Array Deque](data_structures/array/array_deque/) | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | _$`\colorbox{yellow} {O(n)}`$_
[Double Array Deque](data_structures/array/double_array_deque/) | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | _$`\colorbox{yellow} {O(n)}`$_
[Singly Linked List](data_structures/linked_list/singly_linked_list/) | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{yellow} {O(n)}`$_
[Doubly Linked List](data_structures/linked_list/doubly_linked_list/) | _$`\colorbox{yellow} {O(n)}`$_ | _$`\colorbox{yellow} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{yellow} {O(n)}`$_
[Unrolled Linked List](data_structures/linked_list/unrolled_linked_list/) | $`\colorbox{yellow} {O(b)}`$<sup>A</sup> | $`\colorbox{yellow} {O(b)}`$<sup>A</sup> | $`\colorbox{yellow} {O(b)}`$<sup>A</sup> | $`\colorbox{yellow} {O(b)}`$<sup>A</sup> | $`\colorbox{yellow} {O(b)}`$<sup>A</sup> | $`\colorbox{yellow} {O(b)}`$<sup>A</sup> | $`\colorbox{yellow} {O(b)}`$<sup>A</sup> | $`\colorbox{yellow} {O(b)}`$<sup>A</sup> | $`\colorbox{yellow} {n + O(b + n/b)}`$
[Chained Hash Table](data_structures/hash_table/chained_hash_table/) | _N/A_ | $`\colorbox{lightgreen} {O(1)}`$<sup>E</sup> | $`\colorbox{lightgreen} {O(1)}`$<sup>E,A</sup> | $`\colorbox{lightgreen} {O(1)}`$<sup>E,A</sup> | N/A | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | _$`\colorbox{yellow} {O(n)}`$_
[Linear Probing Table](data_structures/hash_table/linear_probing_hash_table/) | _N/A_ | $`\colorbox{lightgreen} {O(1)}`$<sup>E</sup> | $`\colorbox{lightgreen} {O(1)}`$<sup>E,A</sup> | $`\colorbox{lightgreen} {O(1)}`$<sup>E,A</sup> | N/A | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | _$`\colorbox{yellow} {O(n)}`$_
[Object Hash Table](data_structures/hash_table/object_hash_table/) | _N/A_ | $`\colorbox{lightgreen} {O(1)}`$<sup>E</sup> | $`\colorbox{lightgreen} {O(1)}`$<sup>E,A</sup> | $`\colorbox{lightgreen} {O(1)}`$<sup>E,A</sup> | N/A | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | $`\colorbox{yellow} {O(n)}`$<sup>E,A</sup> | _$`\colorbox{yellow} {O(n)}`$_
[Trie](data_structures/trie/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Binary Heap](data_structures/heap/binary_heap/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Meldable Heap](data_structures/heap/meldable_heap/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Binary Tree](data_structures/tree/binary_tree/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Binary Search Tree](data_structures/tree/binary_search_tree/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Randomized Binary Search Tree](data_structures/tree/randomized_binary_search_tree/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Scapegoat Tree](data_structures/tree/scapegoat_tree/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Treap](data_structures/tree/treap/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
> <sup>A</sup> - means amortized time<br>
> <sup>E</sup> - means expected time

#### GRAPH
Structure | addEdge | removeEdge | hasEdge | inEdge | outEdge | space
:--- | :---: | :---: | :---: | :---: | :---: | :---:
[Adjacency Matrix](data_structures/graph/adjacency_matrix/) | $`\colorbox{lightgreen} {O(1)}`$ |  $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(V)}`$ | $`\colorbox{yellow} {O(V)}`$ |  $`\colorbox{red} {O(V**2)}`$ 
[Adjacency Lists](data_structures/graph/adjacency_lists/) | $`\colorbox{lightgreen} {O(1)}`$ |  $`\colorbox{yellow} {O(E)}`$ | $`\colorbox{yellow} {O(E)}`$ | $`\colorbox{yellow} {O(E + V)}`$ | $`\colorbox{lightgreen} {O(1)}`$ |  $`\colorbox{yellow} {O(E + V)}`$ 
> all asymptotic analysises are for worst case<br>
> `V` is the number of vertices<br>
> `E` is the number of edges

## ALGORITHMS

#### SORT

Algorithm | Style | Properties | Best Time | Avg. Time | Worst Time | Worst Space
:--- | :--- | :--- | :---: | :---: | :---: | :---:
[Bubble Sort](algorithms/sort/bubble_sort/) | | `STABLE`, `IN PLACE` | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{red} {O(n**2)}`$ | $`\colorbox{red} {O(n**2)}`$ | $`\colorbox{lightgreen} {O(1)}`$
[Insertion Sort](algorithms/sort/insertion_sort/) | | `STABLE`, `IN PLACE` | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{red} {O(n**2)}`$ | $`\colorbox{red} {O(n**2)}`$ | $`\colorbox{lightgreen} {O(1)}`$
[Merge Sort](algorithms/sort/merge_sort/) | | `STABLE` | $`\colorbox{gold} {O(nlog(n))}`$ | $`\colorbox{gold} {O(nlog(n))}`$ | $`\colorbox{gold} {O(nlog(n))}`$ | $`\colorbox{yellow} {O(n)}`$
[Quick Sort](algorithms/sort/quick_sort/) | | `IN PLACE` | $`\colorbox{gold} {O(nlog(n))}`$ | $`\colorbox{gold} {O(nlog(n))}`$ | $`\colorbox{red} {O(n**2)}`$ | $`\colorbox{lightgreen} {O(1)}`$
[Heap Sort](algorithms/sort/heap_sort/) | | `IN PLACE` | $`\colorbox{gold} {O(nlog(n))}`$ | $`\colorbox{gold} {O(nlog(n))}`$ | $`\colorbox{gold} {O(nlog(n))}`$ | $`\colorbox{lightgreen} {O(1)}`$
[Counting Sort](algorithms/sort/counting_sort/) | | `STABLE` | $`\colorbox{yellow} {O(n + k)}`$ | $`\colorbox{yellow} {O(n + k)}`$ | $`\colorbox{yellow} {O(n + k)}`$ | $`\colorbox{yellow} {O(n + k)}`$
[Radix Sort](algorithms/sort/radix_sort/) | | `STABLE` | $`\colorbox{yellow} {O(kn)}`$ | $`\colorbox{yellow} {O(kn)}`$ | $`\colorbox{yellow} {O(kn)}`$ | $`\colorbox{yellow} {O(n + k)}`$
Selection Sort | | | O(?) | O(?) | O(?) | O(?)
> `STABLE` preserves order of cardinally equivalent values <br>
> `IN PLACE` no auxiliar memory is used <br>

#### SEARCH

Algorithm | Style | Properties | Best Time | Avg. Time | Worst Time | Worst Space
:--- | :--- | :--- | :---: | :---: | :---: | :---:
[Binary Search](algorithms/search/binary_search/) | | | $`\colorbox{yellowgreen} {O(log(n))}`$ | $`\colorbox{yellowgreen} {O(log(n))}`$ | $`\colorbox{yellowgreen} {O(log(n))}`$ | $`\colorbox{lightgreen} {O(1)}`$
Linear Search | | | O(?) | O(?) | O(?) | O(?)
Jump Search | | | O(?) | O(?) | O(?) | O(?)
Interpolation Search | | | O(?) | O(?) | O(?) | O(?)
Exponential Search | | | O(?) | O(?) | O(?) | O(?)
Ternary Search | | | O(?) | O(?) | O(?) | O(?)

#### GRAPH
Algorithm | Style | Properties | Best Time | Avg. Time | Worst Time | Worst Space
:--- | :--- | :--- | :---: | :---: | :---: | :---:
[Graph Breath First Search](algorithms/traversal/graph/breath_first_search/) | [`I`](algorithms/traversal/graph/breath_first_search/Graphs.java) |  | $`\colorbox{yellow} {O(n + m)}`$ | $`\colorbox{yellow} {O(n + m)}`$ | $`\colorbox{yellow} {O(n + m)}`$ | $`\colorbox{yellow} {O(n)}`$
[Graph Depth First Search](algorithms/traversal/graph/depth_first_search/) | [`R`](algorithms/traversal/graph/depth_first_search/Graphs.java), [`I`](algorithms/traversal/graph/depth_first_search/README.md) | | $`\colorbox{yellow} {O(n + m)}`$ | $`\colorbox{yellow} {O(n + m)}`$ | $`\colorbox{yellow} {O(n + m)}`$ | $`\colorbox{yellow} {O(n)}`$
[Floyd-Warshal Shortest Path](algorithms/traversal/graph/floyd_warshall_shortest_path/) | | | $`\colorbox{red} {O(v**3)}`$ | $`\colorbox{red} {O(v**3)}`$ | $`\colorbox{red} {O(v**3)}`$ | $`\colorbox{red} {O(v**2)}`$
[Bellman-Ford Shortest Path](algorithms/traversal/graph/bellman_ford_shortest_path/) | | | $`\colorbox{yellow} {O(e)}`$ | $`\colorbox{red} {O(v*e)}`$ | $`\colorbox{red} {O(v*e)}`$ | $`\colorbox{red} {O(v**2)}`$
[Dijkstra Shortest Path](algorithms/traversal/graph/dijkstra_shortest_path/) | | | $`\colorbox{red} {O(v**2)}`$ | $`\colorbox{red} {O(v**2)}`$ | $`\colorbox{red} {O(v**2)}`$ | $`\colorbox{red} {O(v**2)}`$
> `I` for Iterative Version <br>
> `R` for Recursive Version <br>
> `v` is for number of vertices
> `e` is for number of edges

#### DYNAMIC PROGRAMMING
Algorithm | Style | Properties | Best Time | Avg. Time | Worst Time | Worst Space
:--- | :--- | :--- | :---: | :---: | :---: | :---:
[Knapsack Problem](algorithms/dynamic_programming/knapsack_problem/) |  |  | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$
[Coin Changing Problem](algorithms/dynamic_programming/coin_changing_problem/) |  |  | $`\colorbox{red} {O(nk)}`$ | $`\colorbox{red} {O(nk)}`$ | $`\colorbox{red} {O(nk)}`$ | $`\colorbox{yellow} {O(n)}`$
[Longest Increasing Subsequence (LIS)](algorithms/dynamic_programming/longest_increasing_subsequence/) |  |  | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{gold} {O(nlogn)}`$ | $`\colorbox{gold} {O(nlogn)}`$ | $`\colorbox{yellow} {O(n)}`$
[Minimum Edit Distance](algorithms/dynamic_programming/edit_distance/) | [`T`](algorithms/dynamic_programming/edit_distance/MinEditDistance.java)  |  | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$
[Longest Common Substring](algorithms/dynamic_programming/longest_common_substring/) | [`M`](algorithms/dynamic_programming/longest_common_substring/Sequence.java)  |  | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$ | $`\colorbox{red} {O(mn)}`$
> `T` for Tabular Version <br>
> `M` for Memoization Version <br>

#### TREE
Algorithm | Style | Properties | Best Time | Avg. Time | Worst Time | Worst Space
:--- | :--- | :--- | :---: | :---: | :---: | :---:
Level Order Traversal | `I`, `R` | | O(?) | O(?) | O(?) | O(?)
Inorder Traversal | `I`, `R`, `NRNS` | | O(?) | O(?) | O(?) | O(?)
Postorder Traversal | `I`, `R` | | O(?) | O(?) | O(?) | O(?)
Preorder Traversal | `I`, `R` | | O(?) | O(?) | O(?) | O(?)
> `I` for Iterative Version <br>
> `R` for Recursive Version <br>
> `NRNS` for Non-Recursive Non-Stack Version <br>

## MEMORY

Power of 2 | Exact Value (x) | Approx. Value | XBytes into MB, GB, etc.
:---: | :---: | :---: | :---:
7 | 128 | | |
8 | 256 | | |
10 | 1024 | 1 Thousand | 1KB |
16 | 65,536 |  | 64KB |
20 | 1,048,576 | 1 Million | 1MB |
30 | 1,073,741,824 | 1 Billion | 1GB |
32 | 4,294,967,296 | | 4GB
40 | 1,099,511,627,776 | 1 Trillion | 1TB

## NONDETERMINISTIC POLYNOMIAL

NP-Complete Problem | Description
--- | ---
Boolean Satisfiability Problem (SAT) | Whether variables of a given Boolean formula can be solved with `TRUE` and `FALSE` values evaluating to `TRUE`
Knapsack Problem | To do with combinatorial optimization; given a set of items with a weight and a value, determine the number of each item to include in a collection so that the total weight is less than or equal to a given limit and the total value is as large as possible
Hamiltonian Path or Cycle | Find a path or cycle which visits every vertice in a Graph only once
Traveling Salesman | Given a set of cities and their relative distances determine the shortest route to visit all cities and return to original city of departure
Subgraph Isomorphism | Identify if a graph or its subgraphs are isomorphic of another graph. Generalization of the `Maximum Clique` and `Hamiltonian Cycle`.
Subset Sum Problem | Given a set of integers, is there a non-empty set which sum to zero. Special case of the `Knapsack Problem` and relevant to `Partition Problem`.
Clique Problem | Find a clique - a subset of vertices, adjacent to one another, satisfying some criteria. Related to fining the `Maximum Clique`.
Vertex Cover Problem | The set of vertices where that set is surjectively incident to all edges of the graph
Independent Set Problem | Set of vertices in a graph where none are adjacent. Related to `Maximal Subset` - where the independent set is not a subset of any other independent set.
Dominating Set | A subset of vertices V which are adjacent to all the vertices in V which aren't apart of that subset
Graph Coloring | Does a Graph `G` admit a proper vertex coloring with `k` colors.
> [notes](np/README.md)


[0]: http://i0.wp.com/www.jessicayung.com/wp-content/uploads/2016/08/screenshot-5.png?fit=846%2C591
[1]: http://www.opendatastructures.org
[2]: https://brilliant.org/wiki/unrolled-linked-list/
[4]: https://en.wikipedia.org/
[5]: http://comscigate.com/Books/contests/icpc.pdf