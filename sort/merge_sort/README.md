# Merge Sort
> _Notes based on [opendatastructures.org][1]._ <br>
> _Notes based on [geeksforgeeks.org][2]._ <br>

Sort in non-decreasing order, using comparisons.

Idea is to halve the sorting task repeatedly until only having to sort singular elements.
Then sort those singular elements and combined their sorted order with other pairs of sorted elements.
Combine halves until having combined all the elements from the original sum.

For example,
```
2, 1, 5, 4, 3, 3, 6
(2, 1, 5) (4, 3, 3, 6)
(2, 1) (5) (4, 3) (3, 6)
(1, 2) (5) (3, 4) (3, 6) // n-operations
(1, 2, 5) (3, 3, 4, 6) // n-operations
(1, 2, 3, 3, 4, 5, 6) // n-operations
```
> n-operations when merging
> log(n) operations to halve, plus log(n) more operations to rebuild

## Cost Analysis
If you were to halve __n__ until reaching (1) element, the progression would look like,
```
n, n/2, n/4, n/8, ..., n/n
```
> that is, $`n(1/1, 1/2, 1/3, ..., 1/n)`$

Each halve produces this many subdivisions,
```
if you halve n zero times, n has (1) group
if you havle n one time, n/2 has 2 groups
if you havle n two times, n/4 has 4 groups
if you havle n ... times, ... groups
if you havle n lg(n) times, n/n has n groups
```

Therefor,
```
n(1) + (n/2)*2 + (n/4)*4 + (n/8)*8 + ... + (n/n)*n = n + n + n + ... + n
```
> This happens log(n) times.

Therefor $`\displaytype\sum_{i=0}^nn = n*\displaytype\sum_{i=0}^n = n\logn`$

If the number of inputs is odd, then it's the cost of (n-1)log(n-1) + 1 + 1 + ... + 1 (log(n-1) extra 1s) <= nlogn.

```java
    <T> void mergeSort(T[] a, Comparator<T> c) {
        if (a.length <= 1) return;
        T[] a0 = Arrays.copyOfRange(a, 0, a.length/2);
        T[] a1 = Arrays.copyOfRange(a, a.length/2, a.length);
        mergeSort(a0, c);
        mergeSort(a1, c);
        merge(a0, a1, a, c);
    }
    <T> void merge(T[] a0, T[] a1, T[] a, Comparator<T> c) {
        int i0 = 0, i1 = 0;
        for (int i = 0; i < a.length; i++) {
            if (i0 == a0.length)
                a[i] = a1[i1++];
            else if (i1 == a1.length)
                a[i] = a0[i0++];
            else if (compare(a0[i0], a1[i1]) < 0)
                a[i] = a0[i0++];
            else 
                a[i] = a1[i1++];
        }
    }
```
> From [opendatastructures.org][1]

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(nlogn) | __O(nlogn)__ | _O(nlogn)_

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(n)__ | __O(n)__ | __O(n)__
> typically not in place

Stable,

is stable
:---:
Yes

## Considerations
- good sort func for linkedlists because data is accessed sequentially when merging
    - can rearrange nodes in O(1) time and O(1) space
    - although must linearly iterate to get the half way point when halving
- can count inversions in a list
    - number of inversions = number of inversions in left + number of inversions in right
    - if an element in left is greater than an element in right, bc left is sorted that means all the other elements in left greater than that element are also greater than the element from the right - so (num of el. in left - current indx) = no inversions for the element in r
        - just need to sum all the inversion counts found in the left list that are greater than the right list
- good candidate for external sorting
- comparison is generalized; data-type doesn't matter
    - compare returns negative if a<b
    - compare returns positive if a>b,
    - zero if a=b
- better than quicksort, for large externalized data.

[1]: http://www.opendatastructures.org
[2]: https://www.geeksforgeeks.org/merge-sort/
