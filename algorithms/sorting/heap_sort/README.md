# Heap Sort
> _Notes based on [opendatastructures.org][1]._ <br>

Sort elements in non-descending order.
Use a heap data stracture - backed by the array to sort.

Heapify the backed array. Do this by calling `trickleDown()`.

Swap the top of the heap with the last index of the heap. Decrease the size of the heap. TrickleDown the new root.

Repeat to get descending sorted array.

Reverse the array in O(n) time.

Code looks like this,
```java
    <T> void sort(T[] a, Comparator<T> c) {
        BinaryHeap<T> h = new BinaryHeap<T>(a, c);
        while (h.n > 1) {
            h.swap(--h.n, 0);
            h.trickleDown(0);
        }
        Collections.reverse(Arrays.asList(a));
    }
    BinaryHeap(T[] a, Comparator<T> c) {
        this.c = c;
        this.a = a;
        n = a.length;
        for (int i = n/2-1; i >= 0; i--) {
            trickleDown(i);
        }
    }
```

## Cost Analysis of Heap Sort

There are three parts,
1. heapify the backing array
    - $`\displaystyle\sum_{i=1}^n\frac{(i-1)n}{2^i} \leq \displaystyle\sum_{i=1}^n\frac{in}{2^i} \leq O(n)\displaystyle\sum_{i=1}^n\frac{i}{2^i} = O(2n) = O(n)`$
        - (i-1) is the height of the subtree
        - $`\displaystyle\sum_{i=1}^n\frac{i}{2^i} \approx 2`$
            - $`\displaystyle\sum_{i=1}^n\frac{i}{2^i} = \frac{1}{2} + \frac{2}{4} + \frac{3}{8} + ... +  + \frac{n}{2^n}`$
                - geometric with coefficients, two parts
                    - $`\displaystyle\sum_{i=1}^n\frac{1}{2^i}`$ // geometric
                    - $`\displaystyle\sum_{i=1}^ni`$ // coefficients
                    - $`\displaystyle\sum_{i=1}^n\frac{i}{2^i} = \displaystyle\sum_{i=1}^ni*t^i`$ where $`t = \frac{1}{2}`$
                    - $`\text{n rows} = \begin{cases} r & r^2 & r^3 & r^4 & ... & r^n  \\ 0 & r^2 & r^3 & r^4 & ... & r^n \\ 0 & 0 & r^3 & r^4 & ... & r^n \\ 0 & 0 & 0 & r^4 & ... & r^n \\ ... & ... & ... & ... & ... & r^n \\ 0 & 0 & 0 & 0 & ... & r^n\end{cases}`$
                    - $`\text{n rows} = r + 2r^2 + 3r^3 + 4r^4 + ... + nr^n`$
                    - $`r^i + r^{i+1} + ... + r^n = \frac{r^i - r^{n+1}}{1 - r}`$
                    - The sum of n rows equals $`\displaystyle\sum_{i=1}^n\frac{r^i - r^{n+1}}{1 - r}`$
                    - $`= \frac{1}{1 - r}\displaystyle\sum_{i=1}^n(r^i - r^{n+1})`$
                    - $`= \frac{1}{1 - r}(\displaystyle\sum_{i=1}^nr^i - \displaystyle\sum_{i=1}^nr^{n+1})`$
                    - $`= \frac{1}{1 - r}(\frac{r^i - r^{n+1}}{1 - r} - \displaystyle\sum_{i=1}^nr^{n+1})`$
                    - $`= \frac{1}{1 - r}(\frac{r^i - r^{n+1}}{1 - r} - nr^{n+1})`$
                    - $`= \frac{1}{1 - r}(\frac{r - r^{n+1}}{1 - r} - \frac{(1-r)nr^{n+1}}{1-r})`$
                    - $`= \frac{r - r^{n+1} - (1-r)nr^{n+1}}{(1 - r)^2}`$
                    - $`= \frac{r - r^{n+1} - nr^{n+1} - r*nr^{n+1}}{(1 - r)^2}`$
                    - $`= \frac{r - r^{n+1}(1 + n - nr)}{(1 - r)^2}`$
                    - $`= \frac{\frac{1}{2} - \frac{1}{2}^{n+1}(1 + n - n\frac{1}{2})}{(1 - \frac{1}{2})^2}`$
                    - $`= \frac{\frac{1}{2} - \frac{1}{2}^{n+1}(1 + \frac{n}{2})}{(\frac{1}{4})}`$
                    - $`= 4(\frac{1}{2} - \frac{1}{2}^{n+1}(1 + \frac{n}{2}))`$
                    - $`= \frac{4}{2} - 4*\frac{1}{2}^{n+1}(1 + \frac{n}{2})`$
                    - $`= \frac{4}{2} - \frac{1}{2^{n-1}}(1 + \frac{n}{2})`$
                    - $`= 2 - \frac{1}{2^{n-1}} + \frac{n}{2^{n}}`$
                    - $`\approx 2`$ as $`\lim_{n -> \infty}`$ 
2. extract min/max __n__ times
    - removal is logn
    - trickleDown requires pushing an element down the height of the heap subtree
        - the summation of this is $`\displaystyle\sum_{i=0}^{n-i}(2log(n-i)) \leq \displaystyle\sum_{i=0}^{n-i}(2log(n)) = 2nlogn`$
3. reverse array
    - O(n) time to reverse array

The cost is thus O(2n) + O(2nlogn) + O(n).
> That is O(nlogn)

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(nlogn) | __O(nlogn)__ | _[O(2n) + O(2nlogn) + O(n)]_

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
- can be used to figure out k-largest/k-smallest element (order statistics)
    - could also use a randomized binary search tree
- not really used because `Merge Sort` and `Quick Sort` operate quicker in production.
[1]: http://www.opendatastructures.org