# BINARY SEARCH

## Problem
Find an element `x`, in a sorted array `arr[]` of `n` elements.

## Solution Paradigm
`DIVIDE AND CONQUER`

## Conceptual Solution
- Search a `sorted` array through a halving approach
- Infer which half of a range the search value might be in; infer this based on the sorted order and the range's midpoint value
- If the search value is smaller or equal to the midpoint - infer the possible location is in the half with smaller values than the midpoint
- If the search value is greater than the midpoint - infer the possible location is in the half with values greater than the midpoint
- Cycle through inference and search until the range is exhausted or the value found

## Asymptotic Analysis

### Time Complexity
Is O(logn) timecomplexity - because the input amount is progressively divided/multiplied by a constant amount.

It's timecomplexity can be formalized as:
```
T(n) = T(n/2) + c
```

The recurrence can be resolved using either the `Recurrence Tree Method` or the `Master Method (Case 2)`.

#### Recurrence Tree Method
```
TODO
```
#### Master Method (Case 2)
```
TODO
```

The solution is `THETA(logn).

### Space Analysis
`Iterative:` O(1) - because it uses the space already declared for the searchable array

`Recursive:` O(logn) - because the depth of the recursion is equal to logn and the call stack will thus may exhaust logn process stacks.

## Comments
Asymptotically superior to linear_search.
