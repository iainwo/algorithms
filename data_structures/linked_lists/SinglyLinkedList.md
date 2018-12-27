# Singly Linked List (SLList)
> _Notes based on [opendatastructures.org][1]._

`SLList` is constructed of nodes, which have links in ordinal direction.

Can be used for,
- `Queue`
- `Stack`

Typically has attributes,
```java
    Node head;
    Node tail; // maintained for quick access to the caboose
    int n;
```

## Node Structure
Nodes store data and maintain reference.
```java
    class Node {
        T x;
        Node next;
    }
```

## Node Chains
The internal memory looks like this,

![SLList in memory][2]

## SLList Stack

Push,
1. Create new node
2. Set node to point to head
3. Set head as new node
4. If the stack is empty set the tail
5. Increase the count
```java
    T push(T x) {
        Node u = new Node();
        u.x = x;
        u.next = head;
        head = u;
        if (n == 0)
            tail = u;
        n++;
        return x;
    }
```

Pop,
1. Check bounds
2. save element
3. set second element to head
4. If list is empty unset tail
5. return element
```java
    T pop() {
        if (n == 0)    return null;
        T x = head.x;
        head = head.next;
        if (--n == 0) tail = null;
        return x;
    }
```

## SLList Queue
Add,
1. Create new node
2. Set as head if empty, otherwise append to tail
3. Reset tail to new node
4. Increment the count
```java
    boolean add(T x) {
        Node u = new Node();
        u.x = x;
        if (n == 0) {
            head = u;
        } else {
            tail.next = u;
        }
        tail = u;
        n++;
        return true;
    }
```

Remove (same as Stack pop),
1. Check bounds
2. Save head to tmp
3. Set head to second element
4. decrement count
5. if empty set tail to null
6. return previous head
```java
    T remove() {
        if (n == 0)    return null;
        T x = head.x;
        head = head.next;
        if (--n == 0) tail = null;
        return x;
    }
```

## Time and Space Complexity
Time,

function | best case | worst case
--- | :---: | :---:
`push(x)` | __O(1)__ | __O(1)__
`pop()` | __O(1)__ | __O(1)__
`add(x)` | __O(1)__ | __O(1)__
`remove()` | __O(1)__ | __O(1)__
`find(x)` | O(1) | __O(n)__

Space,

best case | worst case
:---: | :---:
__O(n)__ | __O(n)__

## Considerations
- An SLList nearly implements the full set of Deque operations. The only missing operation is removing from the tail of an SLList.

[1]: http://www.opendatastructures.org
[2]: http://opendatastructures.org/ods-java/img1195.png