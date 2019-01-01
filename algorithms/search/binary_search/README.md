# Binary Search
> _Notes based on [geeksforgeeks.org][1]._ <br>

Rather than linearly progressing through an array searching for an element, if you have an ordered List, leverage the order to make binary-maximally efficient decisions.

By accessing the midpoint of the list, and assessing if the element is less than, greater, or equal to the searching value - one can determine where that value might lie in the list.

If the midpoint is the searched value, you will have found a version of the element.
If the midpoint is greater than the element, then the element if it does exist in the list lies to the left.
If the midpoint is less than an element, then the element has the possibility of being in the right segment.

By divising the List into half, probabalistically there is an equal and balanced chance of locating the searched value if it is in the list.

Repeat this process until the value has be found or determined as not a member of the list.

```java
    int binarySearch(int arr[], int x) 
    { 
        int l = 0, r = arr.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            // Check if x is present at mid 
            if (arr[m] == x) 
                return m; 
  
            // If x greater, ignore left half 
            if (arr[m] < x) 
                l = m + 1; 
  
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
  
        // if we reach here, then element was 
        // not present 
        return -1; 
    }
```
> From [geeksforgeeks.org][1]

## Asymptotic Analysis of Binary Search
The time complexity can be expressed as this recurrence $`T(n) = T(n/2) + c`$ where $`T(n)`$ is the total cost of the search and $`c`$ is some cost associated with each iteration/invocation of the process.

This recurrence is an identity recurrence, and corresponds to the second case of the `Master Theorem` - which simplifies to $`log(n)`$.

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(1) | __O(logn)__ | _[logn]_

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(1)__ | __O(1)__ | __O(1)__
> When iterative
> $`log(n)`$ is consumed by the call stack if recursive.

## Considerations
- `Interpolation Search` is better for uniformly distributed data than `Binary Search`
    - average case is $`loglog(n)`$ and worst case is $`O(n)`$

[1]: https://www.geeksforgeeks.org/binary-search/