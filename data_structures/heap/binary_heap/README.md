# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Binary Heap
Maintains partial order. Order indicates which elements are greater/smaller than other elements in the hierarchy.
Primarily used as a Priority Queue. Called `Binary Heap`.

The typical invariant is that, all children nodes are greater than their direct parents.

## Persistence
Represent the binary tree as an array - in level-order (Eytzinger).
Maintains a _complete_ binary tree property - every level but last full and left-most oriented.
> For the i-th node `u` array holds entities at, <br>
> parent = (i-1)/2 <br>
> left = i*2 + 1 <br>
> right = i*2 + 2

## Add to Heap
1. resize array if needed
2. add element to end of array, at __n__
3. bubble __n__ to the an elevation where it is no longer less than it's parents
```java
    boolean add(T x) {
        if (n + 1 > a.length) resize();
        a[n++] = x;
        bubbleUp(n-1);
        return true;
    }
    void bubbleUp(int i) {
        int p = parent(i);
        while (i > 0 && compare(a[i], a[p]) < 0) {
            swap(i,p);
            i = p;
            p = parent(i);
        }
    }
```

## Remove from Heap
1. Replace root with farmost-right value
2. Delete farmost-right val
3. Trickle down new root so that it is less than it's children, in a way that promotes the smallest of it's current children
4. Resize the array if needed upon removing the element
```java
    T remove() {
        T x = a[0];
        a[0] = a[--n];
        trickleDown(0);
        if (3*n < a.length) resize();
        return x;
    }
    void trickleDown(int i) {
        do {
            int j = -1;
            int r = right(i);
            if (r < n && compare(a[r], a[i]) < 0) {
                int l = left(i);
                if (compare(a[l], a[r]) < 0) {
                    j = l;
                } else {
                    j = r;
                }
            } else {
                int l = left(i);
                if (l < n && compare(a[l], a[i]) < 0) {
                    j = l;
                }
            }
            if (j >= 0)    swap(i, j);
            i = j;
        } while (i >= 0);
    }
```

## Time and Space Complexity

Time,
> Priority Queue: `add(x)`, `remove()` is `O(logn)`
> > Taking into account array structure and ammortized resize costs with `m` ops is `O(m)`.

Space,
> Uses `O(n)`

## Time and Space Complexity

Time,

function | best case | worst case
--- | :---: | :---:
`add(x)` | O(1) | __O(logn)__
`remove(x)` | O(1) | __O(logn)__

Space,

best case | worst case
:---: | :---:
O(n) | __O(n)__

## Considerations
- good for `Prim's MST`
- good for `Dijkstra's Shortest Path`

[1]: http://www.opendatastructures.org