# SELECTION SORT

## Problem
Order an array `arr[]` of `n` elements, ascending.

## Conceptual Solution
1. Divide the array into sorted and unsorted halves
2. Find the smallest value in the unsorted half
3. Move the smallest value to the sorted halve
4. Repeat until unsorted half is empty

## Asymptotic Analysis

#### Time Complexity
Is &Omicron;(n<sup>2</sup>) - because the timecomplexity increases/decreases by a constant exponent in proportion to the input; this is due to the two nested loops.

#### Space Complexity
&Omicron;(1) and doesn't make more than &Omicron;(n) swaps.

## Comments
Usually this search algo is not used. Many other are asymptotically superior.
