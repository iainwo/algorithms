# JUMP SEARCH

## Problem
Find an element `x`, in a sorted array `arr[]` of `n` elements.

## Solution Paradigm
`LINEAR`

## Conceptual Solution
- Progress through a `sorted` array via sectioning the array into `sectors`
- Infer a previous sector has the search value; infer this based on the sorted order and the current sector's initial value
- If the search value is smaller or equal to the current sectors initial value - infer the possible location is in the previous sector (less the initial previous sector's value)
- If the search value is greater than the current sector's value - infer the search value may be in the current sector or even latter sectors
- Progressively inference until the end of all sectors or a previous sector is inferred of possibly containing the value
- Search the remainder of the sector (less the sector's first index) which might have the search value

## Asymptotic Analysis

### Optimal SectorSize
The upperbound timecomplexity is informally this:
```
T(n) = (n/m) + m - 1
```
Where `n` is the total number of elements and `m` is the sector size.
In the worst case the code will be required to iterate over each sector and then linearly search through the entirety of the last sector. Note: the sector search algorithm can be arbitrary but the upperbound may change.

The square root of `n` is highest performing time-minimizing function.
If the code must scan through entire range the square root of the ranges length, will incur the least number of comparisons (the initial comparison on visiting a sector and the final comparisons whence searching through the last sector).

### Time Complexity
Is O(sqrt(n)) timecomplexity - because the input amount is progressively increased by a constant (it's sqrt amount); this appears a more specific version of O(n).

It's timecomplexity can be formalized as:
```
T(n) = sqrt(n)
```

This lands jump_search between linear and binary_search:
```
O(linear_search) >= O(jump_search) >= O(binary_search)
```

The solution is `THETA(n)`.

### Recurrence
Recursion is ill fitted to this algorithm. The bounding checks would be a hassle.

### Space Analysis
`Iterative:` O(1) - because it uses the space already declared for the searchable array

## Comments
- Asymptotically superior to linear_search; usually inferior to binary_search.
- binary_search jump logn times (recursive/iter negative "backwards" range changes), this in context may be more expensive than a jump_search's singular regressive ("backward") range change. For example, in a single-linked list.
