# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Treap (A Randomized BST)
A `Treap` is a Randomized BST additionally supporting `add(x)` and `remove(x)` ops with reasonable complexity - as compared to `BST`.

Treaps do this with properties,
- BST Property
- Heap Property

`BST Property` - all vals in <b>{u.left.data}</b> < u.data < <b>{u.right.data}</b>.

`Heap Property` - <b>u.parent.p < u.p</b> for all nodes except root
> i.e all nodes have priority less than their children

Both these properties mean that the Treap maintians order of it's elements making the data look inserted in a particular order.

That order is made by the `Heap Property` and the priority of the node.

![Treap with priority (val,priorty)][treap_example]

Treap Node Priority is recorde like,
```java
    class BTNode<Node extends BTNode<Node>> {
        Node left;
        Node right;
        Node parent;
        int p;    
    }
```

A sufficiently random priority will probabilistically assume a balanced tree.

A set __{S}__ is,
```
(0,1,2,3,4,5,...,n)
```

A prioritized set __{S}__ is,
```
(<0,5>,<1,0>,<2,100>,...<n,24>)
```
## Find in Treap
This prioritized set in a Treap has a Search Path length equal to a `BST`'s; which is based on the random possibility of inserting __{z}__ elements before the key __x__.
> x ∈ __{S}__, expect search length is `H(rank(x)+1) + H(n-rank(x)) - O(1)

> x ∉ __{S}__, expect search length is `H(rank(x)) + H(n - rank(x))`

## Adding to Treap
In order to add to a Treap viably over time, the Treap must maintain it's BST and Heap properties.

Adding an element is easiest when added as a leaf, via BST insertion. Then the Tree has to be retructured in relation to the new node, based on priority of node, and the Heap property.

The restructuring is done by rotations.

![Illustration of Treap Rotations][treap_rotations]

Code for rotation is,
```java
    void rotateLeft(Node u) {
        Node w = u.right;
        w.parent = u.parent;
        if (w.parent != nil) {
            if (w.parent.left == u) {
                w.parent.left = w;
            } else {
                w.parent.right = w;
            }
        }
        u.right = w.left;
        if (u.right != nil) {
            u.right.parent = u;
        }
        u.parent = w;
        w.left = u;
        if (u == r) { r = w; r.parent = nil; }
    } 
```

Rotations move an element by a depth of (1) up or down.

After node is added, rotate the node up to proper priority.
```java
boolean add(T x) {
        Node<T> u = newNode();
        u.x = x;
        u.p = rand.nextInt();
        if (super.add(u)) {
            bubbleUp(u);
            return true;
        }
        return false;
    }
```

The element can be rotated up in a direction opposite to it subtree location. A node which is the left child of it's parent can be rotated right into the position of it's parent and vice versa.
```java
    void bubbleUp(Node<T> u) {
        while (u.parent != nil && u.parent.p > u.p) {
            if (u.parent.right == u) {
                rotateLeft(u.parent);
            } else {
                rotateRight(u.parent);
            }
        }
        if (u.parent == nil) {
            r = u;
        }
    }
```

The runtime equals search path length plus number of rotations. Search is <b>O(logn)</b> and num of rotations is smaller than or equal to length of search path. Meaning <b>O(logn)</b> is worst case for `add(x)`.

## Remove from Treap
`remove(x)` is opposite of `add(x)`. Search for node then rotate node to leaf and splice.
```java
boolean remove(T x) {
        Node<T> u = findLast(x);
        if (u != nil && compare(u.x, x) == 0) {
            trickleDown(u);
            splice(u);
            return true;
        }
        return false;
    }
```

Rotation choice based on,
1. `u.left & u.right == null`, no rotation necessary
2. `u.left is null`, right rotation
3. `u.right is null`, left rotation
4. `u.left.p < u.right.p` right rotation on __u__
5. `u.left.p > u.right.p` left rotation on __u__
```java
    void trickleDown(Node<T> u) {
        while (u.left != nil || u.right != nil) {
            if (u.left == nil) {
                rotateLeft(u);
            } else if (u.right == nil) {
                rotateRight(u);
            } else if (u.left.p < u.right.p) {
                rotateRight(u);
            } else {
                rotateLeft(u);
            }
            if (r == u) {
                r = u.parent;
            }
        }
    }
```

Removal is opposite mechanics to add. If you were to re-add the same removed node it would cost the Search Path plus __y__ rotations up. __y__ rotations is equivalent to the number of rotations in the remove operations and the search path is based on one less element.

## Time and Space Complexity
`find(x)` in Treap require resolving a path between the root and node of a tree.

`add(x)`, `remove(x)` in Treap requires resolving path between root and node of tree, then performing several rotations less than or equal to the length of the Search Path.

Time,
> `Treap` - find(x), add(x), remove(x) operate in worst case `E[O(logn)]`

> > `Skiplist` operates in `[O(logn)]`
> > `Skiplist` actually `E[O(2log(n) + O(1))]
> > `Treap` actually is `2ln(n) + O(1)` or `1.386logn + O(1)` smaller than Skiplist
> > `Skiplist` can optimize using biased cointoss to get `eln(n) + O(1)` similar to `1.884log(n) + O(1)`
> Randomized BST must make random choices and must maintain the recorded sizes of their subtrees; but can provide rank access in `E[O(logn)]`. Whereas Treap node priorities only keep priority for balancing and do not support rank access in that time.

Space,
> `Treap` - uses `O(n)` space.

## Considerations
- basic structure of Treap was originally named as `Cartesian Tree`
- Treap space optimization can be done by hashing a `u`'s in-memory address, hash must be randomized and min-wise independent. Tabulation hashing is good
- Can construct treap based on subtree size instead
> Adding based on
> > 1. With probability 1/[size(u) + 1], the value <b>x</b> is added the usual way, as a leaf, and rotations are then done to bring <b>x</b> up to the root of this subtree.
> > 2. Otherwise (with probability 1-1/[size(u) + 1], the value <b>x</b> is recursively added into one of the two subtrees rooted at <b>u.left</b> or <b>u.right</b>, as appropriate.
> REmove based on
> > 1. With probability <b>u.left.size/(u.size-1)</br>, we perform a right rotation at <b>u</b>, making <b>u.left</b> the root of the subtree that was formerly rooted at <b>u</b>.
> > 2. With probability <b>u.righit.size/(u.size-1)</b>, we perform a left rotation at <b>u</b>, making <b>u.right</b> the root of the subtree that was formerly rooted at <b>u</b>.

[treap_example]: http://opendatastructures.org/ods-java/img2973.png
[treap_rotations]: http://opendatastructures.org/ods-java/img3012.png