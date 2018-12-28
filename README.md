# ALGORITHMS & DATA-STRUCTURES
Study of algorithms and data-structures

![Complexity Chart][0]

## DATA STRUCTURES

Structure | Avg. Access | Avg. Search | Avg. Insertion | Avg. Delete | Worst Access | Worst Search | Worst Insertion | Worst Delete | Worst Space
:--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---:
[Array Stack](data_structures/array/) | $`\colorbox{lightgreen} {O(1)}`$ | O(n0) | $`\colorbox{gold} {O(n)}`$<sup>A</sup> | $`\colorbox{gold} {O(n)}`$$<sup>A</sup> | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$<sup>A</sup> | $`\colorbox{gold} {O(n)}`$<sup>A</sup> | $`\colorbox{gold} {O(n)}`$
[Rootish Array Stack](data_structures/array/rootish_array_stack/) | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | __O(n + sqrt(n))__
[Array Queue](data_structures/array/array_queue/) | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | _$`\colorbox{gold} {O(n)}`$_
[Array Deque](data_structures/array/array_deque/) | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | _$`\colorbox{gold} {O(n)}`$_
[Double Array Deque](data_structures/array/double_array_deque/) | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | _$`\colorbox{gold} {O(n)}`$_
[Singly Linked List](data_structures/linked_list/singly_linked_list/) | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{gold} {O(n)}`$_
[Doubly Linked List](data_structures/linked_list/doubly_linked_list/) | _$`\colorbox{gold} {O(n)}`$_ | _$`\colorbox{gold} {O(n)}`$_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | _$`\colorbox{gold} {O(n)}`$_
[Unrolled Linked List](data_structures/linked_list/unrolled_linked_list/) | $`\colorbox{gold} {O(b)}`$<sup>A</sup> | $`\colorbox{gold} {O(b)}`$<sup>A</sup> | $`\colorbox{gold} {O(b)}`$<sup>A</sup> | $`\colorbox{gold} {O(b)}`$<sup>A</sup> | $`\colorbox{gold} {O(b)}`$<sup>A</sup> | $`\colorbox{gold} {O(b)}`$<sup>A</sup> | $`\colorbox{gold} {O(b)}`$<sup>A</sup> | $`\colorbox{gold} {O(b)}`$<sup>A</sup> | $`\colorbox{gold} {n + O(b + n/b)}`$
[Chained Hash Table](data_structures/hash_table/chained_hash_table/) | _N/A_ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | $`\colorbox{lightgreen} {O(1)}`$ | N/A | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | $`\colorbox{gold} {O(n)}`$ | _$`\colorbox{gold} {O(n)}`$_
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