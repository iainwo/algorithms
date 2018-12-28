# HEAP SORT

## Problem
Sort a given array `arr[]` so that every element `x`, x<sub>m</sub> <= x<sub>m+1</sub> holds true.

## Paradigm
`COMPARISON BASED`

## Main Concepts
- The arrangements will happen in-place
- The in-place memory will be divided into two halves: sorted and unsorted
- The unsorted half - will not maintain an ascending order of elements, but it will maintain the range's maximum value as the zero-eth index.
- The algorithm will proceed to copy the unsorted zero-eth index's value and place it in the largest unoccupied index of the sorted array; following which the algorithm will re-arrange the unsorted array - maintaining it's zero-eth column maximum, whilst removing it's current zero-eth index.
- Once the unsorted array has iteratively diminished, the sorted array will have been created in descending order from largest to smallest index; and it's values will be ascending

## The Unsorted Range's Max-Value @ the Zero-eth Index Maxim
This maxim is maintained by a Data-Structure called `Heap`. The Heap is essentially a binary tree with paired with a pair of precepts; the precepts are as follows:

1. Completeness (or Shape)
  - the binary tree, in exception of it's second last and last depth, will maintain both binary children for each of it's nodes
  - if any child nodes lay dangling from the second last depth, they must occupy the **left-most positions** of the binary tree
2. Order
  - the node values must either be **greater and equal** than all subordinate nodes, **or vice versa - less than and equal**; this precept can be simplified to the axiom - *a node must be greater and equal to it's binary children's individual values, or less than and equal*.

If the Heap is ordered, so that each node is *greater and equal* - it is called a `Max-Heap`; the converse is named `Min-Heap`.

The consequence of either of these Heap types is that the root node will either be the maximum value of the collection or the minimum.

## Maintaining the Heap
Under alteration the identity of a Heap can be undermined; the resolutions of which are contextually dependent:

### Adding a Node to the Heap
The easiest solution is to affix the newest node in a way that maintains the Completeness and Shape of the Heap; whilst potentially violating the order. When adding a node, the node should be attached at the deepest depth on the left-most available position.

The Heap can then be rebalanced, or "*heapified*" - to correct it's potentially misbegotten order; whether erroneous or otherwise, the two operations of addition and balancing, are for this discussion deemed with optimal efficiency.

### Removing a Node from the Heap
The removal of a node should likewise maintain Shape, rather than Order. To remove a node from the heap, interchange the node to be removed and the deepest and right-most node of the tree.

At this point the Heap, with disregard to the last node of the tree (the one which is deepest and most-right), the Heap has been excised of the intended node. Similar to the algorithm for adding a node, the Heap's order can be rebalanced using the "*heapify*" function.

### The Heapify Function
The heapify function will impose heap-order on a sub-heap given the single-element which is out of order. This function will not order the entirety of heap, it will only resolve one particular node in relation to it's descendants. It has a similar effect to `insertion_sort` where the smallest value is inserted directly following the largest-smaller equivalent; which in turn requires all indices greater than the insertion index to be shifted one over. This behaviour is just happening in a tree structure.

Sample code looks like:
```javascript
function heapify(arr, i, max) {
  var sIdx, lIdx, rIdx;

  while (i < max) {
    sIdx = i;
    lIdx = 2 * sIdx + 1;
    rIdx = lIdx + 1;
    if (lIdx < max && arr[lIdx] > arr[sIdx]) {
      sIdx = lIdx;
    }
    if (rIdx < max && arr[rIdx] > arr[sIdx]) {
      sIdx = rIdx;
    }
    if (i == sIdx) {
      return;
    }
    var tmp = arr[sIdx];
    arr[sIdx] = arr[i];
    arr[i] = tmp;
    i = sIdx;
  }
}
```
It seems an iterative approach makes more sense than recursion; since the Heap itself is typically in practice maintained in an `Array` - it would almost seem incongruous and contradictory to abstain from a tree structure and use an array, but then make a tree of recursive stack frames. Perhaps it would make sense to use an array for more sophisticated operations that access more than ends or beginnings of the tree.

This heapify function operates in &Omicron;(logn) time-complexity; if a node needs to be pushed further down the tree - each progressive descending step will halve the number of nodes that were in the subtree, previous to descent. This constant division of size begets the aforementioned time-complexity.

## Time-Complexity

This algorithm needs to formulate a heap of (n) nodes, with each addition incurring &Omicron;(logn) time-complexity. This alone resolves &Omicron;(nlogn) time-complexity.

The iterative removal of the max value from the Heap and it's consequential re-balancing is resultive of the time-cost:
```
# of operations ~= (n(n-1)/2)*logn
```

The time-complexity is clearly a factor of `logn` and with due inspection is found to be in the worst-case:
```
O(nlogn)
```

## Spatial-Complexity
Fortunately this algorithm operates in-space; however it does substantiate numerous writes and is classified as `unstable` since the Heap Data-Structure fails to distinguish between nodes of equivalent value.

## Comments
- Super cool Data-Structure
- Super slick algorithm
