# Quicksort
> _Notes based on [opendatastructures.org][1]._ <br>
> _Notes based on [geeksforgeeks.org][2]._ <br>

Sort elements in non-decreasing order. Do this by,

Instead of sorting - do partial sorting,
1. all elements less than the pivot are on the left
2. all elements greater than the pivot are on the right
3. all elements equal to the pivot are in the middle between (1) and (2)
> The pivot is randomly selected from the current array that is partially being sorted

Repeat this partial sorting for the left and right segments of the current partially sorted array until the segments are of size 1

For example,
```
5, 4, 3, 2, 1
2, 1, 3, 5, 4 // pivot 3
(1, 2) 3 (4, 5) // pivot 1 and pivot 5
```

Like so,
```java
    <T> void quickSort(T[] a, Comparator<T> c) {
        quickSort(a, 0, a.length, c);
    }
    <T> void quickSort(T[] a, int i, int n, Comparator<T> c) {
        if (n <= 1) return;
        T x = a[i + rand.nextInt(n)];
        int p = i-1, j = i, q = i+n;
        // a[i..p]<x,  a[p+1..q-1]??x, a[q..i+n-1]>x 
        while (j < q) {
            int comp = compare(a[j], x);
            if (comp < 0) {       // move to beginning of array
                swap(a, j++, ++p);
            } else if (comp > 0) {
                swap(a, j, --q);  // move to end of array
            } else {
                j++;              // keep in the middle
            }
        }
        // a[i..p]<x,  a[p+1..q-1]=x, a[q..i+n-1]>x 
        quickSort(a, i, p-i+1, c);
        quickSort(a, q, n-(q-i), c);
    }
```
> From [opendatastructures.org][1] <br>
> This version is sometimes known as [3-Way Quick Sort or Dutch National Flag][3] because it handles pivot points in a middle segment, form three partitions left, middle, and right.

Interesting implementation which keeps the call stack to $`\leq \log_{2}(x)`$ by always recursing on the smaller half, then trying to re-pivot on the bigger,
```java
void quickSort(int arr[], int low, int high) 
{ 
    while (low < high) 
    { 
        /* pi is partitioning index, arr[p] is now 
           at right place */
        int pi = partition(arr, low, high); 
  
        // If left part is smaller, then recur for left 
        // part and handle right part iteratively 
        if (pi - low < high - pi) 
        { 
            quickSort(arr, low, pi - 1); 
            low = pi + 1; 
        } 
  
        // Else recur for right part 
        else
        { 
            quickSort(arr, pi + 1, high); 
            high = pi - 1; 
        } 
    } 
} 
```

## Cost Analysis of Quick Sort
The number of permuations of partial sorted elements, is very high.

The number of permutations which are unfavourable/unbalanced are fewer.

The probability of choosing an unfavourable permuation is low.

The expectation of a element $`i`$ being compare to a pivot element is $`\leq H_{i+1} + H_{n-i}`$.

This is because the pivot points are analgous to probability of pivot value being on the path as a node before the node $`i`$ in a binary search tree. The expectation, that is, the expected length of the search path is equal to the sum of probabilities that the pivot/node is less than $`i`$ and on the search path, plus the sum of probabilities that the pivot/node is greater than $`i`$ and on the search path.

This is less than or equal to, and upper bound by $`2n*ln(n) + O(n)`$
> Note: that this is an probabalistic expectation and not reality.
> Therefor, a worse reality could occur. Such as picking pivot points which are the maximum or minimum value found in the collection. Which would cause the current invocation of quicksort to sort sublength-1 elements for the segment smaller or greater than the max/min pivot. This equates to $`\frac{n(n+1)}{2}`$ operations which is asymptotically recognized as $`O(n^2)`$.

Best case can also be solved by Master Theorem - Case II.,
 $`T(n) = 2T(n/2) + \theta(n)`$
 > which is nlogn

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(nlogn) | __O(n**2)__ | _[2nln(n) + O(n)]_<sup>E</sup>
><sup>E</sup> - Expected time

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(1)__ | __O(1)__ | __O(1)__
> typically not in place

Stable,

is stable
:---:
No

## Considerations
- very similar to `Randomized Binary Search Tree`
- duplicates - when selected as the pivot points are favourable, because they will never be partially sorted again, as `quickSort()` only subsequently partially sort the segments to the left and right of the pivot
- faster in practice than `Merge Sort` and `Heap Sort`
    - beause inner partial sorting loop implementation works better with hardware architecture than `Merge Sort` or `Heap Sort`.

Optimizations,
- use a random index, middle index, or median of first,mid, and last element of the partition, for the pivot -> to guard against worst case quick-sort when picking a number that is the max/min of the partition.
- use insertion sort for subarrays smaller than a certain length. insertion is faster.
> [GeeksForGeeks][2] notes that one particular library uses a magnitude of 7 or less to choose insertion sort over quicksort.
- Reduce the recursion depth by using tail recursive call.
    - Can ensure the partition is less than a smaller tolerance. To decrease the stack depth [here][6].
        - will ensure stack doesn't exceed the the partitioning schemes invariant - like $`log_{2}(x)`$
    - Also see this java article on [Tail Call Optimization][5]
- does not work for large data-sets
- better for arrays, worse for linked lists because of random access.
- good cache locality, and access to data under sort, better than auxiliar access of `Merge Sort` and thus faster.
- can be done singly and doubly linked lists but must choose pivot in constant time (like first/last element of segment)

[1]: http://www.opendatastructures.org
[2]: https://www.geeksforgeeks.org/quick-sort/
[3]: https://www.geeksforgeeks.org/3-way-quicksort-dutch-national-flag/
[4]: https://www.geeksforgeeks.org/quick-sort-vs-merge-sort/
[5]: https://blog.knoldus.com/tail-recursion-in-java-8/
[6]: http://www.cs.nthu.edu.tw/~wkhon/algo08-tutorials/tutorial2b.pdf