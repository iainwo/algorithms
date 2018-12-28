# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Meldable Randomized Binary Heap
Used to implement Priority Queue. Called `Meldable Heap`.
Ops. are based on melding.

## Persistence
Binary Tree structure is free-form and does not have to be __complete__ like trad. `Binary Heap`.

## Heap Melding
1. Melding happens between Nodes of a Tree.
2. The smallest node is promoted higher/to-top of the heap, the other node becomes a child.
3. The orientation (left/right) of the node becoming the child node is stochastically based. 50-50.
4. This election process is applied for the remaining order of the subtree's nodesets which are being melded.
```java
    Node<T> merge(Node<T> h1, Node<T> h2) {
        if (h1 == nil) return h2;
        if (h2 == nil) return h1;
        if (compare(h2.x, h1.x) < 0) return merge(h2, h1);
        // now we know h1.x <= h2.x
        if (rand.nextBoolean()) {
            h1.left = merge(h1.left, h2);
            h1.left.parent = h1;
        } else {
            h1.right = merge(h1.right, h2);
            h1.right.parent = h1;
        }
        return h1;
    }
```

## Add to Meldable Heap
Create node, meld with root.
```java
    boolean add(T x) {
        Node<T> u = newNode();
        u.x = x;
        r = merge(u, r);
        r.parent = nil;
        n++;
        return true;
    }
```

## Remove Root from Meldable Heap
Merge subchildren, assign new root to root.
```java
    T remove() {
        T x = r.x;
        r = merge(r.left, r.right);
        if (r != nil) r.parent = nil;
        n--;
        return x;
    }
```

## Probabalistic Proof of Melding Cost
Melding makes use of _Random Walks_.
Walks start from the root and exit the boundary of the tree.
The left right direction of a walk is directed by even coin toss. 50-50.

Inductive Proof,
> __Base Case: walk of len 0 = log(n + 1), when n=0__ <br>
> -> Assume true for all non-negative integers from (0,n] <br>
> -> size(r.left) = n1, size(r.right) = n2, where n = 1 + n1 + n2 <br>
> <br>
> E[W] = 1 + log(n1 + 1)/2 + log(n2 + 1)/2 <br>
>      = 1 + log((n - 1 + 2)/2) <br>
>      = 1 + log((n + 1) - log(2) <br>
>      = log(n+1) <br>
> :. E[W] = O(logn)

The merge cost is based on two random walks between the first subtree and the second.
The merging ends when one of the subtrees is expended.

The cost of this can be measured,
> E[W] = log(n1 + 1) + log(n2 + 1) <br>
>      <= 2logn <br>
>      = O(logn)

## Time and Space Complexity
Time,
> Priority Queue: `add(x)`, `remove()` is `E[O(logn)]`

Space,
> Uses `O(n)`

## Considerations
- other meldable heaps: leftist, binomial, fibonacci, Pairing, Skew; none simple like `Meldable Heap`
- some meldables support `decreaseKey(u,y)` of node `u` to val-y usually in O(logn)
- some structures can decrease in `O(1)` Fibonacci or `O(loglogn)` amortized in special Pairing heaps
- `decreaseKey(u,y)` good for graph algos, like Dijkstra's __Shortest Path__

[1]: http://www.opendatastructures.org