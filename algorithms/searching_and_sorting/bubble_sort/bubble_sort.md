# BUBBLE SORT

## Problem
Order an array `arr[]` of `n` elements, ascending.

## Conceptual Solution
- Progressively sort the array
- Sequentially sort each unique element
- Sorting is achieved by sifting an element to an index where all preceding elements are of a lighter weight
- If the sifting was done in sequence - one element after the other, all elements will be in index where each subsequent element (in the array, not the process) will be of a lighter weight

## Asymptotic Analysis

#### Time-Complexity
Is &Omicron;(n<sup>2</sup>) - because the time-complexity increases/decreases by a constant exponent in proportion to the input; this is due to the two loops. With an optimization which checks for index swapping, the best time-complexity can be improved to &Omicron;(n).

#### Space Complexity
&Omicron;(1) and doesn't make more than &Omicron;(n<sup>2</sup>) swaps.

## Comments
Usually this search algo is not used. Many other are asymptotically superior; may be applicable in sorted arrays with minimal order discord.
