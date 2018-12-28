# Unrolled Linked List (Space Efficient List - SEList)
> _Notes based on [opendatastructures.org][1]._ <br>
> _Notes based on [brilliant.org][2]._ <br>
> _Notes based on [blogs.msdn.microsoft.com][3]._

A `Space Efficient List` differs from a `DLList` or `SLList` in that it has more than one data value per node.
Often each node stores a __block__ of data; this can still be thought of as a list of nodes.
It is also valid to picture the __SEList__ as a the fragmentation of a large array - and that those fragments still have some connection - and that connection is via a pointer.

![Illustration of an SEList][4]

Typically a __Node__ will have these qualities,
```java
    class Node {
        BDeque d;
        Node prev, next;
    }
```

Where the Array segments mentioned earlier are __Deques__ - for the benefit of speed efficient manipulation.
```java
    class BDeque extends ArrayDeque<T> {
        BDeque() {
            super(SEList.this.type());
            a = newArray(b+1);
        }
        void resize() { }
    }
```
> Note that the size of these Deques are static values. This will contribute later to the management and performance of the SEList.

> By selecting a __blocksize__ that is equal to a factor of the $`\bold{\sqrt{n}}`$ of the total elements and by maintaing near-complete usage of each those blocks, the __SEList__ will have distributed about $`\bold{\sqrt{n}}`$ elements per array segment (i.e Node).

> Usually to maintain the density of $`\bold{\sqrt{n}}`$ elements per Node, array segments are required to have a number of elements which are within $`\bold{blocksize \pm1}`$.

Invariants of an SEList,
1. blocksize __b__ must be $`\bold{1 <= blocksize <= n}`$
2. all blocks must have $`\bold{blocksize \pm1}`$, except the last block

## Contents of an SEList
With the concepts mentioned before an __SEList__ has these attributes,
```java
    int n;
    Node dummy;
```

## Benefits of an SEList
`SEList` addresses the hardware related problem of __cache lines__ in addition to offering better __space or time__ - via algorithmic tradeoffs of the blocksize and it's impace on _search(x)_, _add(i,x)_, _remove(i,x)_.

### Cache Lines

> _Modern PCs have multi-level cache hierarchies that make traversing an array (visiting the elements in order) very fast. Cache hits are so fast that in cache-sensitive analysis they are considered "free"; we only count cache misses. If a cache line has size B, then the number of cache misses is about n/B. A linked list, on the other hand, requires a cache miss for every node access in the worst case. Even in the best case, when the nodes are allocated consecutively in order, because linked list nodes are larger, it can require several times more cache misses to traverse the list._ <br>
> > _... I constructed a linked list of 60 million integers and created an array of the same 60 million integers. I compiled with full optimization. Traversing the linked list required 0.48 seconds, while traversing the array required 0.04 seconds, 12 times faster. Moreover, when I introduced code to fragment the memory pool, the advantage increased dramatically to 50 times or more. The linked list also required 4 times as much memory - twice as much for next pointers, and twice as much again for allocation metadata._ <br><br>
> \- [blogs.msdn.microsoft.com][3].

### Space vs Time
Unrolled Linked Lists also support a trade off between memory compactness and operation time.

That trade off is determined by the __blocksize__ in relation to the total number of elements __n__.
> The blocksize can be $`\bold{1 <= blocksize <= n}`$.
That blocksize __b__ specifies the number of blocks in an SEList and in addition, specifies the number of possible elements with those blocks.

```math
\bold{T(n)}: \text{the number of blocks in an SEList}
```
```math
\bold{T(n)} =  \lfloor n / blocksize \rfloor + 1
```

The preponderance of elements in a block, cause these algorithmic qualities emerge between `Arrays` versus `LinkedLists`. Thus concerting a trade off between __space and time__.

Pro/Con | Quality | LinkedList | Array
:---: | :---: | :---: | :---:
\+ | _Access_ | | __x__ 
\+ | _Insertion_ | __x__ | 
\+ | _Contiguity_ | | __x__
\- | _Extra References_ | __x__ | 
> - When the blocksize __b__ is smaller `insertion` and `deletion` is quicker but `searching` is slower
> - When the blocksize __b__ is large `searching` is quicker but `insertion` and `deletion` are slower
> > - This is because an __Array__ is quicker to access and slower to manipulate (not at their extremities)
> > - This is because a __LinkedList__ is quicker to manipulate but slower to access
> > - A smaller __b__ will increase the number of LinkedLists; and a large __b__ will increase the number of Arrays
> - Compact data-types like chars and bits reduce memory footprint even further
> - The SEList can be configure to require a higher threshold of populated data; this will increase the density of the stored data at the cost of more frequent node splits and merges. <br>
> - Also the smaller size of __b__ will reduce the number of densities thresholds and will and increase management overhead of maintaining $`\bold{blocksize \pm1}`$ due to the blocksize __b__ being more fractious.

## Finding in SEList
1. Find the correct block
2. Modulate the index based on the block location
3. Return the block and the relative index
```java
    Location getLocation(int i) {
        if (i < n/2) {
            Node u = dummy.next;
            while (i >= u.d.size()) {
                i -= u.d.size();
                u = u.next;
            }
            return new Location(u, i);
        } else {
            Node u = dummy;
            int idx = n;
            while (i < idx) {
                u = u.prev;
                idx -= u.d.size();
            }
            return new Location(u, i-idx);
        }
    }
```

Datastructure for relative block location,
```java
    class Location {
        Node u;
        int j;
        Location(Node u, int j) {
            this.u = u;
            this.j = j;
        }
    }
```

## Get & Set SEList
1. Derive the location of the index relative to a block
2. Get or set the value
```java
    T get(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Location l = getLocation(i);
        return l.u.d.get(l.j);
    }
    T set(int i, T x) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Location l = getLocation(i);
        T y = l.u.d.get(l.j);
        l.u.d.set(l.j,x);
        return y;
    }
```

## Adding to SEList
In the case of adding to the end of a SEList and all the blocks are full - or no blocks yet exist,
1. Create new block
2. Insert element
```java
    boolean add(T x) {
        Node last = dummy.prev;
        if (last == dummy || last.d.size() == b+1) {
            last = addBefore(dummy);
        }
        last.d.add(x);
        n++;
        return true;
    }
```

In the general case, when a blocks is full,
1. Case I. - Locate another bucket
    1. Find the block $`b_{k}`$ which the addition is for
    2. Starting after $`b_{k}`$, search in $`(s+1 \leq b)`$ steps for block that is not full where, $`\text{blocksize} \leq (b + 1)`$.
    3. __Redistribute__. Globally - respective of all blocks to the right of the insertion block $`b_{k}`$, migrate a single block element from one block into the following block. There will be $`s`$ migrations, one per each of the $`b_{(s+1) - 1} `$ blocks which were full before the $`b_{s+1}`$ block which is not full. This will create space in the block we want to add to, while creating space previously and recursively in the blocks following it.
    4. Add value to $`b_{k}`$
2. Case II. - No Empties
    1. Find the block $`b_{k}`$ which the addition is for
    2. Starting after $`b_{k}`$, search in $`(s+1 \leq b)`$ steps for block that is not full where, $`\text{blocksize} < (b + 1)`$. and find that there are no empty blocks between $`b_{k}`$ and $`b_{\min (\lceil n/b \rceil, \lfloor n/b \rfloor +1)}`$.
    3. Add a new block
    4. And re-distribute.
    5. Insert.
3. Case III.
    1. 1. Find the block $`b_{k}`$ which the addition is for
    2. Starting after $`b_{k}`$, search $`(b)`$ times that all blocks following blocks have $`\text{blocksize} = (b + 1)`$. and find that there are no empty blocks.
    3. Create new block at the end of the List
    4. Redistribute every blocks contents so that each block has only $`b`$ elements.
    5. Add element
```java
    void add(int i, T x) {
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        if (i == n) {
            add(x);
            return;
        }
        Location l = getLocation(i);
        Node u = l.u;
        int r = 0;
        while (r < b && u != dummy && u.d.size() == b+1) {
            u = u.next;
            r++;
        }
        if (r == b) {      // b blocks each with b+1 elements
            spread(l.u);
            u = l.u;
        } 
        if (u == dummy) {  // ran off the end - add new node
            u = addBefore(u);
        }
        while (u != l.u) { // work backwards, shifting elements
            u.d.add(0, u.prev.d.remove(u.prev.d.size()-1));
            u = u.prev;
        }
        u.d.add(l.j, x);
        n++;
    }
```

[1]: http://www.opendatastructures.org
[2]: https://brilliant.org/wiki/unrolled-linked-list/
[3]: https://blogs.msdn.microsoft.com/devdev/2005/08/22/unrolled-linked-lists/
[4]: http://opendatastructures.org/ods-java/img1346.png