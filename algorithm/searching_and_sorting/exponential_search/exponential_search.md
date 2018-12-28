# EXPONENTIAL SEARCH

## Problem
Find an element `x`, in a sorted array `arr[]` of `n` elements.

## Solution Paradigm
`TODO`

## Conceptual Solution
- Search a `sorted` array through a halving approach
- The array may be unbounded (`i.e infinite`)
- The midpoint is a calculation of the former index and some constant exponent
- Infer which half of a range the search value might be in; infer this based on the sorted order and the range's midpoint value
- If the search value is smaller or equal to the midpoint - infer the possible location is in the half with smaller values than the midpoint `use binary_search to find the value`
- If the search value is greater than the midpoint - infer the possible location is in the half with values greater than the midpoint, `continue to exponentially progress through the array`
- Exponentially the algorithm with move through swaths of the array

## Asymptotic Analysis

### Time Complexity
Is O(logn) timecomplexity - because the input amount is progressively divided/multiplied by a constant amount.

| Big Omicron | Big Theta | Big Omega |
| --- | --- | --- |
| &Omicron;(logi) | &Theta;(i) | &Omega;(i) |


### Space Analysis
`Iterative:` &Omicron;(1) - if both the exponential_search component and binary_search are iterative, they will use the space already declared for the searchable array

`Recursive:` &Omicron;(logn) - if either exponential or binary search components are recursive they will occupy memory equal to the depth of their recursion tree. The memory consumption can be bounded by &Omicron;(logn).

## Comments
- Good for unbounded and infinite arrays, or when the search term can usually be
found at the beginning of an array
