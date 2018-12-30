# Insertion Sort

Sort the data ordinally.
Start at the first index and maintain order at the beginning of the list, with shifting and a tempory register.
Iterate over the data and for the current index shift the value through the sorted array, until the sorted quality is restored.

For example,
```
3, 2, 5, -1, 10, 12 // iter no. 1, @idx1
2, 3, 5, -1, 10, 12

2, 3, 5, -1, 10, 12 // iter no. 2, @idx2

2, 3, -1, 5, 10, 12 // iter no. 3, @idx3
2, -1, 3, 5, 10, 12
-1, 2, 3, 5, 10, 12

-1, 2, 3, 5, 10, 12 // iter no. 4 @ idx4

-1, 2, 3, 5, 10, 12 // iter no. 5 @ idx5

Sifted all elements - stop.
``` 

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(n) | __O(n**2)__ | _O(n**2)_

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(1)__ | __O(1)__ | __O(1)__
> in place

Stable,

is stable
:---:
Yes

## Considerations
- Performance highly dependent on the underlying data-structure.
    - Arrays mean have to shift all elements on insertion
    - Could use a different structure to speed up the insertions
        - linkedlist for quick insertions, and a hashmap to quickly access linked list
        - linkedlist and cache head node, tail node, and size of elements
        - `Unrolled Linked List` if there is alignment/partial sorting
            - would quickly iterate to correct block, use the blocks underlying deque to insert
            - the `b` value - the size of each block, and the number of empty spots in the block could increase to allow for more cost effective insertion
            - performance okay because of `cache lines`
        - simple deque if some of the arrays is in decreasing/opposite order allows for O(1) insertion to begining/end of list
- can be used for sorted arrays with a couple elements out of order would be O(c*n) where c is the number of elements out of order
