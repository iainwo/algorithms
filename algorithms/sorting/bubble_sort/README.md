# Bubble Sort

Sort the data ordinally.
Do so by sifting the ordinally highest number first into position.
Repeat for all indices by decreasing order.
Stop when sifting over the array does not move any elements

Because the highest number is moved to the highest position on the first sift, __do not__ have to return to this position on next sift!

Example
```
1, 5, 10, 2, 4, 7
1, 5, 2, 10, 4, 7
1, 5, 2, 4, 10, 7
1, 5, 2, 4, 7, 10

1, 2, 5, 4, 7, 10
1, 2, 4, 5, 7, 10

No more sifts. End.
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
- Simple
- Great for fixing small errors in $`\approx O(c*n)`$ where $`c`$ is the number of required iterations through the array. 