# ALGORITHMS & DATA-STRUCTURES
Study of algorithms and data-structures
> _[opendatastructures.org][1] was used to produce this content. Thank you so much!_ <br>
> _[brilliant.org][2] was used to produce this content. Thank you so much!_ <br>
> _[blogs.msdn.microsoft.com][3] was used to produce this content. Thank you so much!_

![Complexity Chart][0]

## DATA STRUCTURES

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
[Chained Hash Table](data_structures/hash_table/chained_hash_table/) | _N/A_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | N/A | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | _$`\colorbox{yellow} {O(n)}`$_
[Linear Probing Table](data_structures/hash_table/linear_probing_hash_table/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Object Hash Table](data_structures/hash_table/object_hash_table/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Trie](data_structures/trie/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Binary Heap](data_structures/heap/binary_heap/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Meldable Heap](data_structures/heap/meldable_heap/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Binary Tree](data_structures/tree/binary_tree/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Binary Search Tree](data_structures/tree/binary_search_tree/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Randomized Binary Search Tree](data_structures/tree/randomized_binary_search_tree/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Scapegoat Tree](data_structures/tree/scapegoat_tree/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
[Treap](data_structures/tree/treap/) | _O(?)_ | _O(?)_ | _O(?)_ | _O(?)_ | O(?) | O(?) | O(?) | O(?) | O(?)
> - O(x)<sup>A</sup> - means amortized

Graph | addEdge | removeEdge | hasEdge | inEdge | outEdge | space
:--- | :---: | :---: | :---: | :---: | :---: | :---:
[Adjacency Matrix](data_structures/graph/adjacency_matrix/) | $`\colorbox{lightgreen} {O(1)}`$ |  $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{lightgreen} {O(1)}`$ |  $`\colorbox{red} {O(n**2)}`$ 
[Adjacency Lists](data_structures/graph/adjacency_lists/) | $`\colorbox{lightgreen} {O(1)}`$ |  $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n)}`$ | $`\colorbox{yellow} {O(n + m)}`$ | $`\colorbox{lightgreen} {O(1)}`$ |  $`\colorbox{yellow} {O(n + m)}`$ 
> all asymptotic analysises are for worst case<br>
> - `n` is the number of vertices<br>
> - `m` is the number of edges

## ALGORITHMS

Algorithm | Style | Best Time | Avg. Time | Worst Time | Worst Space
:--- | :--- | :---: | :---: | :---: | :---:
Linear Search | | O(?) | O(?) | O(?) | O(?)
Binary Search | | O(?) | O(?) | O(?) | O(?)
Jump Search | | O(?) | O(?) | O(?) | O(?)
Interpolation Search | | O(?) | O(?) | O(?) | O(?)
Exponential Search | | O(?) | O(?) | O(?) | O(?)
Ternary Search | | O(?) | O(?) | O(?) | O(?)
Bubble Sort | | O(?) | O(?) | O(?) | O(?)
Insertion Sort | | O(?) | O(?) | O(?) | O(?)
Selection Sort | | O(?) | O(?) | O(?) | O(?)
Merge Sort | | O(?) | O(?) | O(?) | O(?)
Heap Sort | | O(?) | O(?) | O(?) | O(?)
Level Order Traversal | `I`, `R` | O(?) | O(?) | O(?) | O(?)
Inorder Traversal | `I`, `R`, `NRNS` | O(?) | O(?) | O(?) | O(?)
Postorder Traversal | `I`, `R` | O(?) | O(?) | O(?) | O(?)
Preorder Traversal | `I`, `R` | O(?) | O(?) | O(?) | O(?)

> `I` for Iterative Version <br>
> `R` for Recursive Version <br>
> `NRNS` for Non-Recursive Non-Stack Version <br>

[0]: http://i0.wp.com/www.jessicayung.com/wp-content/uploads/2016/08/screenshot-5.png?fit=846%2C591
[1]: http://www.opendatastructures.org
[2]: https://brilliant.org/wiki/unrolled-linked-list/
[3]: https://blogs.msdn.microsoft.com/devdev/2005/08/22/unrolled-linked-lists/