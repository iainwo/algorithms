# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Binary Search Tree (BST)
The Binary Search Tree is a datastructure which maintains an ordering of it's elements.
The recursive maxim which evokes this ordering is,
> ___RULE:___ `{u.left.data}` < `u.data` < `{u.right.data}`<br> 
> <br>
> where,<br>
> 1 `{u.left.data}` are all the data values in the left subtree<br>
> 2 `u.data` is the data value in the parent node of the subtrees `{u.left}` and `{u.right}`<br>
> 3 `{u.right.data}` are all the data values in the right subtree

### Searching in BST
There are three cases in searching,
1. `x < u.x`, go __u.left__
2. `x > u.x`, go __u.right__
3. `x = u.x`, found node __u__

Search can be achieved while also returning ___the smallest value that is >= to x___ if not found.
```java
T find(T x) {
        Node w = r, z = nil;
        while (w != nil) {
            int comp = compare(x, w.x);
            if (comp < 0) {
                z = w;
                w = w.left;
            } else if (comp > 0) {
                w = w.right;
            } else {
                return w.x;
            }
        }
        return z == nil ? null : z.x;
    }
```

### Adding to BST
There are three cases,
1. `x` exists, __do nothing__.
2. `x` DNE and < than last leaf, __insert to left__
3. `x` DNE and > than last leaf, __insert to right__

The steps are this,
```java
 boolean add(T x) {
        Node p = findLast(x);
        return addChild(p, newNode(x));        
    }
```
Where returning `x` or the the terminal element < or > comes from,
```java
Node findLast(T x) {
        Node w = r, prev = nil;
        while (w != nil) {
            prev = w;
            int comp = compare(x, w.x);
            if (comp < 0) {
                w = w.left;
            } else if (comp > 0) {
                w = w.right;
            } else {
                return w;
            }
        }
        return prev;
    }
```
Adding the element `x` with reference to the neighbor terminal element is,
```java
boolean addChild(Node p, Node u) {
        if (p == nil) {
            r = u;              // inserting into empty tree
        } else {
            int comp = compare(u.x, p.x);
            if (comp < 0) {
                p.left = u;
            } else if (comp > 0) {
                p.right = u;
            } else {
                return false;   // u.x is already in the tree
            }
            u.parent = p;
        }
        n++;
        return true;        
    }
```

### Removing from BST
#### Removal from Leaf and 1-ary Nodes
Can be done by splicing,
```java
void splice(Node u) {
        Node s, p;
        if (u.left != nil) {
            s = u.left;
        } else {
            s = u.right;
        }
        if (u == r) {
            r = s;
            p = nil;
        } else {
            p = u.parent;
            if (p.left == u) {
                p.left = s;
            } else {
                p.right = s; 
            }
        }
        if (s != nil) {
            s.parent = p;
        }
        n--;
    }
```
### Removal from Binary Node
The difficulty comes from having to maintain the ordering of the BST. This can be kept by removing Node `u` and replacing it with a value `w.data` that is greater than all the values in `{u.left.data}` which also satisfies `w.data` < `{u.right.data}`.

Since `u` is a node with two children, it is guaranteed to have `u.right`, subsequently a value greater than `u.data`. Thus, the smallest value in `{u.right.data}` will suffice both being greater than `{u.left.data}` and being smaller than `{u.right.data}`.
```java
void remove(Node u) {
        if (u.left == nil || u.right == nil) {
            splice(u);
        } else {
            Node w = u.right;
            while (w.left != nil) 
                w = w.left;
            u.x = w.x;
            splice(w);
        }
    }
```

## Time and Space Complexity
`find(x)`, `add(x)`, `remove(x)` in a BST require resolving a path between the root and node of a tree. A BST does not keep balanced and can degenerate into a `LinkedList`.

Time,
> `BST` - find(x), add(x), remove(x) operate in worst case `O(n)`.<br>
> `BST` - find(x), add(x), remove(x) operate in avg. case `O(logn)`.<br>
> `Skiplist` - find(x), add(x), remove(x) operate in `E[O(logn)]`.

Space,
> `BST` - `O(n)`<br>
> `Skiplist` - `O(n*logn)`

## Considerations
- Does the dataset require hierarchies -> `BST` might apply
- Should BST store parent pointer nodes? If Searching is only being done - dont' need (wastes space); however makes it hard to traverse without a stack or recursion, insertion/deletion becomes complex in `Balanced BST`s.
- storing node references can be done with explicit Node pointers or an Array. 

[1]: http://www.opendatastructures.org