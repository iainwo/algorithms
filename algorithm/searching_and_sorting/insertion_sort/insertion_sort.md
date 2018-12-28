# INSERTION SORT
Insertion sort is traditionally related to how many people would sort their hand of cards. Grab from the right and order on the left.

If a value is found to be smaller than some values on the left, then greater values are shifted to the right and the current value is put in place of the smallest of the greater elements.

A full sorting of a reverse-sorted range requires the full traversal of the range - to access each element to sort, and then a full iteration over the current sorted elements to insert the new val.

## Time-complexity
This algorithm due to possibilities of full range and nested iteration has a time-complexity of `O(n*n)`.

## Space Complexity
This algorithm performs sorting in place - and can be upper-bounded by `O(1)`. It does however make many many writes and similarly mirrors the time-complexity bound `O(n*n)`.

## Modified Binary Search
`Insertion Sort` can be improved with use of a more efficient search algorithm. This algorithm is responsible for determining the smallest index which holds a value greater than the search value `x`.

This applies to very large sorting ranges, where a linear search would take `O(n)` time-complexity and efficiencies of `O(logn)` or better are feasible.

#### Binary Search Endgame
##### Endgame #1
In this case the binary search range is of an `ODD` length. Due to this, a mid point will exist in the middle, there will be a left index and a rightmost index.
```
-[LEFT_IDX]-[MIDPOINT]-[RIGHT_IDX]
```

When binary search operates over this odd 3-node length range it will either realize the searched value - `x` (as the midpoint), or it will return either the leftmost or rightmost index.

At this point, when binary search goes to operate over the single length node; the midpoint, left index and right index will be of equivalent value. This is because the node is of length (1) - therefor the left and right index can only be of that 1-lenght index and therefor the average of two equivalent values is the same equivalent value.

That is,
```
IF leftIdx = 5,
IF rightIdx =5,

AVG({leftIdx, rightIdx}) =
= (5 + 5)/SIZE_OF({leftIdx, rightIdx})
= (10)/2
= 5

:. The average of equivalent values is one occurrence of the equivalent values.
```

Given the equivalence of left index, right index and midpoint - if the searched value does not match these shared indices then the search value `x` either occurs in one of these four cases:

| Case No.  | State                    | BSearch Idx to Return  |
| ---       | ---                      | ---                    |
| 1         | `x < arr[0]`             | `return 0;`            |
| 2         | `x > arr[arr.length-1]`  | `return arr.length-1`  |
| 3         | `x < arr[l/r/mid]`       | `return l/r/mid;`      |
| 4         | `x > arr[l/r/mid]`       | `return 1 + (l/r/mid);`|

##### Endgame #2
In this case the binary search ranges is of an `EVEN` length. In `JAVA` the traditional calculation of the midpoint will take the floor any averaged value:
```java
int midIdx = (rightIdx - leftIdx)/2;
```

When the midpoint of an 2-node even range is calculated, this will return the first node (the leftmost index) as the midpoint:
```
-[LEFT_IDX/MIDPOINT]-[RIGHT_IDX]
```
In the case the calculation takes the `ceil(midpoint)` the logic which follows can be switched.

There are three extra if checks to create:

| Case No.  | State                    | BSearch Idx to Return     |
| ---       | ---                      | ---                       |
| 1         | `x < arr[l/mid]`         | `return l/mid;`           |
| 2         | `x > arr[l/mid]`         | `return 1 + (l/mid);`     |
| 2         | `x > arr[l/mid]`         | `return r;`               |
| 2         | `x > arr[l/mid]`         | `return binary_search();` |

##### The Corollary Returns of the Endgame's 1 & 2

| Case No.  | State                    | BSearch Idx to Return     |
| ---       | ---                      | ---                       |
| 1         | `x < arr[0]`             | `return 0;`               |
| 2         | `x > arr[arr.length-1]`  | `return arr.length-1`     |
| 3         | `x < arr[l/mid]`         | `return l/mid;`           |
| 4         | `x > arr[l/mid]`         | `return 1 + (l/mid);`     |
| 4         | `x > arr[l/mid]`         | `return binary_serach();` |
