# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Scapegoat Tree (Balanced BST)
Rather than maintain balanced tree structures; `Scapegoat Tree` allows for a threshold of imbalance.
Whence too imbalanced use `partial rebuilding` to return to balance.

> Logic is,
> 1. Detect imbalance
> 2. Find the subtree with the imbalance
> 3. Call `rebuild(x)` on subtree.

The rebuilt subtree will have the minimum possible height.

## Parameters
The imbalance tolerance is bounded by a _capacity_ __q__ owned by the __Scapegoat Tree__.
> _Note_: guessing using __q__ instead of __n__ is to allow for freer/unstructured tree shapes which don't exceed logarithmic costs, and due to the freedom the tree isn't always forced to restructure. Something like the height of a tree and it's width are proportional - with a higher exchange rate of width nodes per nodes at depth.
```java
int q;
```
The capacitity serves as a range between how little and how many elements can exist in the __Scapegoat Tree__.
> Where ` q/2 <= n <= q `, __n__ is the number of elements

Based on that range the Scapegoat Tree keeps a logarithmic height `log3/2(q)`.
> The height imposed by that limit is at worst less than 2 levels greater than the minimum allowed height<br>
> `T -> log3/2(q) <= log3/2(2n) < log3/2(n) + 2` <br>
> `T -> log150%(q) <= log150%(n) + log150%(2) <= log150%(n) + 1.709 < log150%(n) + 2` <br>
> - __n__ in the range __[q/2, q]__ <br>

## Find in Scapegoat Tree
Same as BST `find(x)`.

## Add to Scapegoat Tree
Adding to a Scapegoat is similar as `add(x)` in `BST`.

When the addition creates a node that exceeds the allowed depth `log150%(q)`, this mathematically guarantees that their is a hierarchical subtree rooted on the path to the new node which is imbalanced.

To fix the imbalance, determine which node is imbalanced, then restructure it's subtree into a complete balanced binary tree.
```java
    boolean add(T x) {
        // first do basic insertion keeping track of depth
        Node<T> u = newNode(x);
        int d = addWithDepth(u);
        if (d > log32(q)) {
            // depth exceeded, find scapegoat
            Node<T> w = u.parent;
            while (3*size(w) <= 2*size(w.parent))
                w = w.parent;
            rebuild(w.parent);
        }
        return d >= 0;
    }
```

## Remove from Scapegoat Tree
Removal is usually the same as `remove(x)` in `BST`.

However when the size of the tree falls below the capacity of range `q`, `rebuild(u)` and then set `q` to the current number of elements; inorder to keep `q` as a value that is capable of maintaining the logarithmic height of the tree.
```java
    boolean remove(T x) {
        if (super.remove(x)) {
            if (2*n < q) {
                rebuild(r);
                q = n;
            }
            return true;
        }
        return false;
    }
```

## Rebuilding Subtree
Many ways. Easiest is to `in-order` traverse subtree and store in array, then build a new subtree based on `post-order` interpretation of the array.
```java
    void rebuild(Node<T> u) {
        int ns = size(u);
        Node<T> p = u.parent;
        Node<T>[] a = (Node<T>[]) Array.newInstance(Node.class, ns);
        packIntoArray(u, a, 0);
        if (p == nil) {
            r = buildBalanced(a, 0, ns);
            r.parent = nil;
        } else if (p.right == u) {
            p.right = buildBalanced(a, 0, ns);
            p.right.parent = p;
        } else {
            p.left = buildBalanced(a, 0, ns);
            p.left.parent = p;
        }
    }
```

Storing subtree in array,
```java
    int packIntoArray(Node<T> u, Node<T>[] a, int i) {
        if (u == nil) {
            return i;
        }
        i = packIntoArray(u.left, a, i);
        a[i++] = u;
        return packIntoArray(u.right, a, i);
    }
```

Rebuilding subtree via `post-order` interpretation of the array,
```java
    Node<T> buildBalanced(Node<T>[] a, int i, int ns) {
        if (ns == 0)
            return nil;
        int m = ns / 2;
        a[i + m].left = buildBalanced(a, i, m);
        if (a[i + m].left != nil)
            a[i + m].left.parent = a[i + m];
        a[i + m].right = buildBalanced(a, i + m + 1, ns - m - 1);
        if (a[i + m].right != nil)
            a[i + m].right.parent = a[i + m];
        return a[i + m];
    }
```

## Existential Correctness of a Scapegoat Subtree on an Add(x) Path
Upon adding a node to Scapegoat Tree, which exceeds depth `log150%(q)`, the path to the newly added node is guaranteed to have a subtree which is imbalanced.

This can be concluded by applying the invariant that no subtree can compose > than 2/3rds of it's parents hierarchy.
> `MAXIM ->   2/3 >= size(u) / size(u.parent)`

The root tree has `n` nodes. __root.left__ has < 2/3 * n. The __root.left.left__ has less than (2/3)(2/3) * n.

The form above can be generalized to any given subtree, meaning that subtree must have,
> `size(i-th u) <= Pow(2/3, i) * n`

Applying this count method to a subtrees of these depths indicates that it is impossible to have only subtrees which are 2/3rds of their parents compositions on the path to the new node - there must be a subtree which has more than 2/3rds of it's parents composition.
> size(u) <= 1, where __u__ is the newly added node with depth of __h__
> size(u) <= Pow(2/3, h)*n
> size(u) <= Pow(2/3, h)*n <= Pow(2/3, log3/2(q))*n
> size(u) <= Pow(2/3, h)*n <= Pow(2/3, log3/2(q))*n <= Pow(2/3, log3/2(n))*n
> size(u) <= Pow(2/3, h)*n <= Pow(2/3, log3/2(q))*n <= 1/n*n, since Pow(2/3,x) is the inverse of Pow(3/2, x)
> size(u) <= 1 <= ... <= size of optimal depth subtree = 1
> > It is not possible for a node to exceed the depth of the Scapegoat Tree - or have a subtree, as in doing so requires violating at least one subtree's 2/3 balance in relation to it's parent. This is because the maximum number of nodes allowable at an acceptable depth is (1).

## Cost of finding a Scapegoat
Mentioned - somewhere on the Path from __root__ to __u__ there is a singular scapegoat.
That scapegoat has one child which has more than 2/3rds of it's node composition.
Every subtree between the Scapegoat and the leaf is therefor not a Scapegoat.
Thus each subtree that is not the scapegoat at worst will have 2/3rds of it's parents nodes.
> At worst case, the calls to `size(x)` are <br>
> = Sum of i=0 to k of `size((k-i)th node)`, where node(k) is the scapegoat <br>
> = size(k) + Sum of i=0 to (k-1) of `size((k-1-i)th node)` <br>
> = size(k) + Sum of i=0 to (k-1) of `Pow(2/3, i) * size(k)` <br>
> = size(k) (Sum of i=0 to (k-1) of `Pow(2/3, i)`) <br>
> <= size(k), could remove the geometric series which integral/reinman sum hits a constant

## Cost of Rebuilding
Rebuilding always builds perfect balanced and complete trees.

When adding,
- Therefor |size(u.left) - size(u.right)| <= 1
- In order to create a scapegoat, the allowable depth must be exceeded
- The allowable depth is `log3/2(q)`
- To exceed that depth you would need the subtree to have more than 2/3rds the composition of it's parent
- Meaning 1/2 * size(u.left) > size(u.right)
- size(u.left) - size(u.right) > 1/2 * size(u.left) > 1/3 * size(u)
- :. number of `add(x)`/`remove(x)` to the tree must be > `size(u)/3 - 1` 

When removing,
- The height of the subtree never increases
- Call `rebuild(x)` on remove only when q > 2n, meaning there are q-n removals > than the actual current capacity of the tree
- Every `rebuild(x)` happens only after `q - n` ops
- _Note:_ q>2n is point when tree becomes unbalanced or close too it. must be inorder to maintain logarithmic costs and maybe free-form of tree

## Time and Space Complexity

Time,
> `add(x)`, `remove(x)`, `find(x)` can all operate in `O(logn)`
> `add(x)`, `remove(x)` have an amortized runtime of `O(mlogm)` where `m` is the number of `add(x)`/`remove(x)` ops due to `rebuild(x)`

Space,
> `O(n)`!

## Considerations
- General idea behind Scapegoat is have any shape given the height is logarithmic
- `Scapegoat Tree` in practice is slow. Even though it's time complexity is fundamentally fast
> `Scapegoat Tree` is 1.709log(q) + O(1) vs. `Skiplist` is `E[O(2log(n) + O(1))]` that is worse
> `Scapegoat Tree` is 1.709log(q) + O(1) vs. `Treap` is `1.386logn + O(1)`
> > better than `Skiplist` worse than `Treap` for complexity
> > complexity due to rebuilding, counting tree sizes
> > can optimize by storing tree sizes or tree size computations
> > but fundamentally `add(x)` & `remove(x)` ops will trigger reconstruction which other structures to not have
> > other structures consume only `O(n)` to keep balance
> > > `Scapegoat` may work with operations applied to a set which can not be achieved in O(1) operation. The rebuild phase can be hijacked to serve this purpose, so as to reap economies of scale - when iterating through the data-structure






[1]: http://www.opendatastructures.org