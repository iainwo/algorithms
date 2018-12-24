# TREE DATASTRUCTURES

Tree structures are the formation and relation of Nodes.
Nodes is data which has references to a parent Node and Children Nodes.

Tree Nodes have one parent and can have many Children Nodes.

This creates a hierarchical structure formed by (1) Parent Node.
This Parent Node is the `Root` Node.
The Children Nodes which have Parent Nodes but do not have any Children Nodes
of their own, are each known as a `Leaf` and collectively as `Leaves` of the
Tree.

A `Subtree` refers to a Parent Node and it's Children Nodes - and their Children
Nodes. A Subtree is rooted at this Parent Node; a relative root.

## Purpose
Trees are idyllic of hierarchical arrangements.
In addition to their referential structure, that structure provides reasonable
access and search costs, as well as reasonable insertion and deletion costs.

The typical tree has an Average search, access, insert, delete time of O(log(n)).
The typical tree has a Worst search, access, insert, delete time of O(n).
The typical tree has Best and Worst Space cost of O(n).

## Use-Cases
1. Manipulate hierarchical data
2. Easy search
3. Manipulate lists of sorted data
4. Multi-stage decision making

## Variants
1. `Binary Tree` - allows each Node to have [0 - 2] children Nodes.

## Types of Binary Trees
1. `Full Binary Tree` - every node has (0) or (2) children. All nodes except for leaves have children.
2. `Complete Binary Tree` - every level is full, except for last. All nodes on last are to the leftmost position.
3. `Perfect Binary Tree` - every node has two children.
4. `Balanced Binary Tree` - the height is at most log(n) or log(n) + 1.
5. `Degenerate Tree` - every internal node has (1) child.

## Properties of A Binary Tree
Root Node is considered having a height of (0).
1. `l = 2**h` - possible # of Nodes on a level is equal to 2**(level height).
2. `N <= 2**(h + 1) - 1` - total nodes is at most one less the # of Nodes on the next level.
3. `l >= ceil(log(N+1))-1` - a balanced tree has a min height of log(n), therefor at min. the height of the tree must be equal or greater.
4. `h >= ceil(log(L))` - the number of leaves infers the minimum possible height of the tree.
5. In a `Full Binary Tree` - `L = 1 + I` - the number of leaves is greater by one than the number of internal nodes.

### Degree Sum Formula
This formula relates the degrees of a Undirected Graph's Vertices to it's number of Edges.
```
  E dgr(v) = 2|E|
```

### Number of Vertices Formula
This formula relates the number of edges to the number of vertices,
```
  |E| = L + I - 1
```

### Handshaking Lemma
The `Handshaking Lemma` says that when Finite Undirected Graph's have a number
of odd-degree vertices - their number are even.

### Tree Properties Proofed by the Handshaking Lemma,

### Leaves are a product of Internal Nodes
```
In a k-ary Full Binary Tree,
L = (k - 1)I + 1

This infers that the Tree's Leaves (L) are subsequent to the number of fully
populated Internal k-ary nodes.

When root is a Leaf,
1 = (k - 1)0 + 1
1 = 1

:. L = (k - 1)I + 1

When root is an internal node,
E dgr(v) = 2|E|
L + (I - 1)(k + 1) + k = 2|E|
L + (I - 1)(k + 1) + k = 2(L + I - 1)
(I - 1)(k + 1) + k = L + 2(I - 1)
Ik + I -k -1 + k = L + 2(I - 1)
Ik -k - 1 + k = L + I - 2
Ik - 1 = L + I - 2
Ik - I = L - 1
L = Ik - I + 1
L = I(k - 1) + 1

:. L = (k - 1)I + 1
```

### Binary Tree Leaves are (1) Greater than T
L = T + 1

Read - the number of nodes with k-ary children plus one is equal to the number
of Leaves.

When the root is a Leaf,
1 = 0 + 1
1 = 1

:. L = T + 1

When root is an internal node with (2) Children,
E dgr(v) = 2|E|
L + (T - 1)(k + 1) + k = 2|E|
L + (T - 1)(k + 1) + k = 2(L + T - 1)
(T - 1)(k + 1) + k = L + 2(T - 1)
(T - 1)(k + 1) + k = L + 2(T - 1)
L = (T - 1)(k + 1) + k - 2(T - 1)
L = Tk + T - k - 1 + k - 2T + 2
L = Tk - 1 - T + 2
L = Tk - T + 1
L = T(k - 1) + 1
L = T(k - 1) + 1
L = T(2 - 1) + 1 // if k=2
L = T + 1 // if k=2

- OR -

E dgr(v) = 2|E|
L + (T-1)*3 + (S-1)*2 + 2 = 2|E|
L + (T-1)*3 + (S-1)*2 + 2 = 2(L + T + S - 1)
L + T - 3 + (S-1)*2 + 2 = 2(L + S - 1)
L + T - 3 + -2 + 2 = 2(L - 1)
L + T - 3 = 2(L - 1)
T - 3 = L - 2
T = L + 1

:. L = T + 1

When root has one Child,
E drg(v) = 2|E|
L + T*3 + S*2 + 1 = 2|E|
L + T*3 + S*2 + 1 = 2(L + S + T + 1 - 1)
L + T*3 + S*2 + 1 = 2(L + S + T)
L + T + S*2 + 1 = 2(L + S)
T + S*2 + 1 = L + 2(S)
T + 1 = L

:. L = T + 1

## Sample Questions

https://www.geeksforgeeks.org/gate-gate-cs-2015-set-3-question-35/
Consider a binary tree T that has 200 leaf nodes. Then, the number of nodes in T that have exactly two children are _________.
(A) 199
(B) 200
(C) Any number between 0 and 199
(D) Any number between 100 and 200

Answer: (A)

https://www.geeksforgeeks.org/gate-gate-cs-2015-set-2-question-20/
A binary tree T has 20 leaves. The number of nodes in T having two children is
(A) 18
(B) 19
(C) 17
(D) Any number between 10 and 20

Answer: (B) 

https://www.geeksforgeeks.org/gate-gate-cs-2005-question-36/
In a complete k-ary tree, every internal node has exactly k children. The number of leaves in such a tree with n internal nodes is
(A) nk
(B) (n – 1)k + 1
(C) n(k – 1) + 1
(D) n(k – 1)


Answer: (C)

https://www.geeksforgeeks.org/gate-gate-cs-2010-question-12/
In a binary tree with n nodes, every node has an odd number of descendants. Every node is considered to be its own descendant. What is the number of nodes in the tree that have exactly one child?
(A) 0
(B) 1
(C) (n-1)/2
(D) n-1


Answer: (A)

https://www.geeksforgeeks.org/gate-gate-cs-2007-question-43/
A complete n-ary tree is a tree in which each node has n children or no children. Let I be the number of internal nodes and L be the number of leaves in a complete n-ary tree. If L = 41, and I = 10, what is the value of n?
(A) 3
(B) 4
(C) 5
(D) 6

Answer: (C) 
