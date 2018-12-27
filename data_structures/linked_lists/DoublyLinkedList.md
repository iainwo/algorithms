# Doubly Linked List (DLList)
> _Notes based on [opendatastructures.org][1]._

A `DLList` is a sequence of nodes with bijective references to their neighbouring nodes.
Commonly the list looks like,

![DLList Diagram][2]

The node structure looks like this,
```java
    class Node {
        T x;
        Node prev, next;
    }
```

The complexity is found in keeping tack of the head and tail; this is magnified by the bidirectional pointers.
However, this can be eased by the use of a dummy node.

Attributes in a __DLList__,
```java
    int n;
    Node dummy;
    DLList() {
        dummy = new Node();
        dummy.next = dummy;
        dummy.prev = dummy;
        n = 0;
    }
```

## Searching in DLList
1. Boundary check
2. Determine if the index is in the beginning or end of list
3. Iterate forward or backward respectively
```java
    Node getNode(int i) {
        Node p = null;
        if (i < n / 2) {
            p = dummy.next;
            for (int j = 0; j < i; j++)
                p = p.next;
        } else {
            p = dummy;
            for (int j = n; j > i; j--)
                p = p.prev;
        }
        return p;
    }
```

## Get & Set in DLList
Find then return or set val.
```java
    T get(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        return getNode(i).x;
    }
    T set(int i, T x) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Node u = getNode(i);
        T y = u.x;
        u.x = x;
        return y;
    }
```

## Adding to DLList
To interleave a node into the list requires restructuring the references of the node being replaced, and the node that comes before it. Restructuring means to point the previous node at the new node and the replaced node pointed-back at the new node; then pointing the new node with references back to the previous node, and the new node's reference to the new node.

```java
    void add(int i, T x) {
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        addBefore(getNode(i), x);
    }
    Node addBefore(Node w, T x) {
        Node u = new Node();
        u.x = x;
        u.prev = w.prev;
        u.next = w;
        u.next.prev = u;
        u.prev.next = u;
        n++;
        return u;
    }
```

Illustrated as,

![DLList Insertion][3]

## Remove from DLList
```java
    void remove(Node w) {
        w.prev.next = w.next;
        w.next.prev = w.prev;
        n--;
    }
    T remove(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Node w = getNode(i);
        remove(w);
        return w.x;
    }
```

## Time and Space Complexity
Time,

function | best case | worst case | practical worst case
--- | :---: | :---: | :---:
`push(x)` | O(1) | __O(1)__ | _O(1)_
`pop()` | O(1) | __O(1)__ | _O(1)_
`add(x)` | O(1) | __O(1)__ | _O(1)_
`remove()` | O(1) | __O(1)__ | _O(1)_
`get(i)` | O(1) | __O(n)__ | _O(1 + min{i, n-i})_
`set(i,x)` | O(1) | __O(n)__ | _O(1 + min{i, n-i})_
`add(i,x)` | O(1) | __O(n)__ | _O(1 + min{i, n-i})_
`remove(i)` | O(1) | __O(n)__ | _O(1 + min{i, n-i})_
`find(x)` | O(1) | __O(n)__ | _O(1 + min{i, n-i})_

Space,

best case | worst case
:---: | :---:
__O(n)__ | __O(n)__

## Considerations
- expensive searching. Having refrence means add, remove, or access is __O(1)__
- LinkedLists are good when you can get node references externally. Like LinkedHashSet in Java.
- LinkedHashSet in java has DDList of set items, nodes are stored in a hash table. Table provides __O(1)__ access to node, then the node can be managed with __O(1)__ operations to add, remove, or access.

[1]: http://www.opendatastructures.org
[2]: http://opendatastructures.org/ods-java/img1251.png
[3]: http://opendatastructures.org/ods-java/img1271.png