# INTERPOLATION SEARCH

## Problem
Find an element `x`, in a sorted and uniformly distributed array `arr[]` of `n` elements.

## Solution Paradigm
`DIVIDE AND CONQUER`

## Conceptual Solution
- data is `sorted`
- data is `uniformly distributed`; meaning each outcome shares equal chance
- Search data through a `halving` approach
- Infer which half of a range the search value might be in; infer this based on the sorted order and the range's `interpolated midpoint value`
- If the search value is smaller or equal to the interpolated midpoint - infer the possible location is in the half with smaller values than the midpoint
- If the search value is greater than the interpolated midpoint - infer the possible location is in the half with values greater than the midpoint
- `Cycle through inference and search` until the range is exhausted or the value found

## Asymptotic Analysis

### Time-Complexity
When the data *is* uniformly distributed the timecomplexity is:
```
O(loglogn)
```
However, if the data is but uniform, the timecomplexity is `O(n)`.

#### The Impact of Interpolation on Time-Complexity
The halving is proceeded by a interpolation calculation; a popular variant is as follows:
```
INTERPOLATE_PIVOT_POINT(X) = lo + ((hi-lo)/(arr[hi]-arr[lo])*(x - arr[lo]));
```

The calculation's components can be regarded as contributing the possible effects:
``` java
int pos = lo + ((hi-lo)/(arr[hi]-arr[lo])*(x - arr[lo]));

/* Two ways of looking at it:
 * 1) First,
 * 		1.	(hi-lo) 			- this represents the magnitude of indices difference
 * 		2.	(arr[hi]-arr[lo]) 	- represents the total discontinuity between hi and lo values
 *
 * 		3.  #1/#2				- a ratio of how congruent the indexed value is to it's index.
 * 								E.g are they 1:1 - where arr[1]:1, arr[1000] ~= 1000
 * 		4.	*(x - arr[lo])		- multiply the degree of congruence by the degree of discontinuity of the
 * 								searched item and the lower-bound
 * -OR-
 * 2) Second,
 * 		1.	(x - arr[lo])		- relative search to lower-bound difference
 * 		2. 	(arr[hi] - arr[lo])	- absolute value difference
 *
 * 		3.	#1/#2				- the ratio of relative search difference to the entire range
 *
 * 		4.	*(hi-lo)			- multiply the relative search diff. - relative to the absolute range by
 * 								the total indices difference to interpolate the approximate search values
 * 								location between the lower-bound (lo) and upper-bound (hi)
 */
```
When this calculation functions, the halving exponential reduces the input size - literally, in exponential fashion; which is the reason this algorithm can operate in `O(loglogn)`.

### Space Analysis
`Iterative:` O(1) - because it uses the space already declared for the searchable array

`Recursive:` O(loglogn) - because the recursive stack would occupy memory equivalent to the number of recursive calls (i.e which is also equivalent to the Time-Complexity).

## Comments
- Asymptotically superior to linear_search, jump_search and binary_search.
- Performance gains are dependent on the uniformity of the data's distribution.
