# MERGE SORT

## Problem
Take an array `arr[]` and sort the stored values ascending; so that for all `x` in `arr[]`, the evaluation x<sub>m</sub> <= x<sub>m+1</sub> holds true.

## Paradigm
`DIVIDE AND CONQUER`

## Concept
- Recursively halve the halves of the given array until the elements reach an atomic level.
- Once at an atomic granularity, combine the combination of atomic halves in sorted order

## Time-Complexity
The division component of this algorithm operates via the halving of the initial array. The number of halves which can be produced, can be expressed given the forumula:
```
n = total number of elements
nhalves = total possible number of halves

nhalves = n + (n%1)
```

The actuall number of divisions it takes to get those halves can be expressed thus,
```
n = total number of elements
ndivisions = total number of divisions necessary to produce halves equal in size to an elements atomic size

ndivisions = logn
```

Disregarding the comparison necessary to recombine the divisions into sorted amalgamations, the recombination phases requires an identical number of steps as the division phase - that is, `logn`.

The number of possible combinations of merging one array with another is:
```
leftArrLen = the length of the one array
rightArrLen = the length of the other array

total # of possible combinations = (2)**n/2
```
Since both arrays are sorted the only viable elements for comparison are the foremost members of each array. This means, logically, choice is restricted to array one's first element or array two's first element. This presents a total of (2) options. It is also given that each array is of relatively similar length; since the merge sort algorithm produces halves; it is possible that one array is longer than the other, but this difference is only restricted to a length of one. This can be attributed to the originating array being of odd length and the resultant division is an array of even length and another of an odd size.

Regardless of all that, the former equation does hint at the total number of operations required to merge two halves. There is at least `n/2` comparisons which need to be made; these comparisons determine how the smaller-size arrays elements can be interwoven with the other arrays elements to produce a sorted sequence. However after this sorting there may still be elements, which were larger than one of the arrays. These remaining elements must also be placed into the new sorted array.

In either of these cases, the total complexity cost of this sorting equates to the total size of the two halves:
```
leftArrLen = the size of the left array
rightArrLen = the size of the right array

total op count to create merge arr =
= O(leftArrLen + rightArrLen)
= O(n)

:. Where n is the original size of the array before the fractionation into "left" and "right" arrays.
```

**THEREFORE**, the total time complexity can be expressed as:
```
O(nlogn)
```

### Recurrence Calculations
```
T(n) = T(n/2) + THETA(n)
```

This can be solved by either the `recurrence tree method` or the `Master` method.

#### Recurrence Tree Method
```
TODO
```

#### Master Method (Case 2)
```
TODO
```


## Spatial-Complexity
Depending on the efficiency of the implementation this can either be `O(nlogn)` or `THETA(n)`. Usually `O(n)` and not in-place.

## Comment
- merge_sort is particularly good for `linked lists`.
  - merging can occur in `O(1)` space, since the linked list can be re-referenced
  - merge_sort operates over contiguous ranges, where linked lists suit sequential iteration
  - merge_sort is less suited to arrays because it frequently uses insert operations and arrays have an expensive insert cost

- on the flipside `quick_sort` is complimentary to arrays; as it favours random access; and linked lists conversely, do not.
