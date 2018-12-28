# Unrolled Linked List (Space Efficient List - SEList)
> _Notes based on [opendatastructures.org][1]._ <br>
> _Notes based on [brilliant.org][2]._ <br>
> _Notes based on [blogs.msdn.microsoft.com][3]._ <br>
> _Notes based on [linuxjournal.com][4]._

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
`SEList` addresses the hardware related throughput problem of __cache lines__ in addition to offering better __space and access versus random insertion/deletion__ - via algorithmic tradeoffs of the blocksize and it's impact on _get(i)_, _set(i,x)_, _add(i,x)_, _remove(i,x)_.

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
    1. Find the block $`b_{k}`$ which the addition is for
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

## Remove from SEList
When removing it is possible that the removal will cause a block to fall below capacity $`\bold{blocksize \pm1}`$.

When the block falls below capacity there are these solutions
1. Case I - _Steal from Peter to pay Paul_
    1. Find the block $`b_{k}`$ which the removal is for
    2. Starting after $`b_{k}`$, search in $`(s+1 \leq b)`$ steps for block $`b_{z}`$ that has capacity where, $`\text{blocksize} \geq b`$.
    3. __Redistribute__. Globally - respective of all blocks to the left of the block $`b_{z}`$ with extra capactiy, migrate a single block element from the next block into the current block. There will be $`s`$ migrations, one per each of the $`s`$ blocks which capacity of $`| b_{k+j} | = b - 1`$ before the $`b_{s+1}`$ block of a size $`| b_{z} | > b`$ . This will maintain the density invariant in the block we want to remove from, and all of the blocks which follow, once we perform the removal.
    4. Remove the item.
2. Case II - _Veni, Vidi, Vici_
    1. Find the block $`b_{k}`$ which the removal is for
    2. Starting after $`b_{k}`$, search in $`(s+1 \leq b)`$ steps for block $`b_{z}`$ that has capacity where, $`\text{blocksize} \geq b`$ and find that there is not a block, or that there is not $`(s+1)`$ blocks.
    3. Same as __Case I__ redistribute. If the last block is under capacity that is ok; the SEList invariant allows this. If the last block is empty - then remove it.
3. Case III - _Borg: It's Time to Assimilate_
    1. Find the block $`b_{k}`$ which the removal is for
    2. Starting after $`b_{k}`$, search $`b`$ times for block $`b_{z}`$ that has capacity where, $`\text{blocksize} \geq b`$ and find that there is not a block.
        - This means that all blocks have size $`|b_{i}| = b - 1`$
    3. Take all elements and distribute $`b`$ elements per block, removing the last now-empty block
    4. Remove the item.
```java
    T remove(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Location l = getLocation(i);
        T y = l.u.d.get(l.j);
        Node u = l.u;
        int r = 0;
        while (r < b && u != dummy && u.d.size() == b-1) {
            u = u.next;
            r++;
        }
        if (r == b) {  // b blocks each with b-1 elements
            gather(l.u);
        }
        u = l.u;
        u.d.remove(l.j);
        while (u.d.size() < b-1 && u.next != dummy) {
            u.d.add(u.next.d.remove(0));
            u = u.next;
        }
        if (u.d.isEmpty()) remove(u);
        n--;
        return y;
    }
```

# Cost Analysis of Gather & Spread

Implemented like,
```java
    void spread(Node u) {
        Node w = u;
        for (int j = 0; j < b; j++) {
            w = w.next;
        }
        w = addBefore(w);
        while (w != u) {
            while (w.d.size() < b)
                w.d.add(0,w.prev.d.remove(w.prev.d.size()-1));
            w = w.prev;
        }
    }
```
```java
    void gather(Node u) {
        Node w = u;
        for (int j = 0; j < b-1; j++) {
            while (w.d.size() < b)
                w.d.add(w.next.d.remove(0));
            w = w.next;
        }
        remove(w);
    }
```

Both the inner and outer loops have a number of operations which are $`<= b + 1`$.
This means that nesting will produce a runtime of $`\Omicron(b^2)`$.

Every block can have a capacity of $`\{ (b - 1), b, (b + 1) \}`$ elements - except for the last block which is free.

Define $`\Phi = \sum_{\mathclap{i=0}}^b size(b - b_{i})`$

Posit `add(i, x)` or `remove(i)` decreases $`\Phi`$ by $`1`$.
Posit that `spread(u)` and `gather(u)` happen when $`\Phi = \sum^b size(b-1)`$ or $`\Phi = \sum^b size(b+1)`$
Posit after `spread(u)` and `gather(u)` has $`\Phi = \sum^b size(b)`$

If `spread(u)` or `gather(u)` was just called, then in order to be recalled the current $`\Phi_{i}`$ must change to $`\Phi_{i-1}`$ or $`\Phi_{i+1}`$

The number of operations __q__ to get to any of $`\{ \Phi_{i-i}, \Phi_{i+1}`$ from $`\Phi_{i}`$ is either,
1. $`q = \Phi_{i+1} - \Phi_{i}`$, meaning $`q = (\Phi_{i+1} - \Phi_{i}) / \Phi_{add(i, x)}`$
2. $`q = \Phi_{i} - \Phi_{i-1}`$, meaning $`q = (\Phi_{i} - \Phi_{i-1}) / \Phi_{remove(i)}`$

Since $`1 = \Phi_{add(i, x)} = \Phi_{remove(i)}`$, __q__ is equal to $`(\Phi_{i+1} - \Phi_{i})/1`$
Meaning there are $`\pm b`$ operations between `gather(u)` and `spread(u)` calls.

This means that $`\Omicron(b^2)`$ is amortized with $`b`$ operations per `add(i,x)` or `remove(i)` call.

Therefor if there are $`1 <= m`$ calls to `add(i,x)` or `remove(i)` there is at most $`\Omicron(bm)`$ comuptations aggregated across the $`\{ add(i,x), remove(i), spread(u), gather(u) \}`$ operations.
$`\Box`$

## Time and Space Complexity
Time,

function | best case | worst case | practical worst case
--- | :---: | :---: | :---:
`get(i)` | O(1) | __O(b)__<sup>A</sup> | _O(1 + min{i, n-i}/b)_<sup>A</sup>
`set(i,x)` | O(1) | __O(b)__<sup>A</sup> | _O(1 + min{i, n-i}/b)_<sup>A</sup>
`add(i,x)` | O(1) | __O(b)__<sup>A</sup> | _O(b + min{i, n-i}/b)_<sup>A</sup>
`remove(i)` | O(1) | __O(b)__<sup>A</sup> | _O(b + min{i, n-i}/b)_<sup>A</sup>
`find(x)` | O(1) | __O(b)__<sup>A</sup> | _O(1 + min{i, n-i}/b)_<sup>A</sup>
> - __O(x)__<sup>A</sup> - means amortized <br>
> - If there are $`1 <= m`$ calls to `add(i,x)` or `remove(i)` there is at most $`\Omicron(bm)`$ comuptations aggregated across the $`\{ add(i,x), remove(i), spread(u), gather(u) \}`$ operations.

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(n)__ | __O(n)__ | __n + O(b + n/b)__

## Considerations
- What do you need quicker add/remove times or quicker access times
- Have a low __b__ for quicker add/remove times
- Have a high __b__ for quicker access times
- Consider __cache lines__ and if they bottleneck your Random Access
- In languages which do not have garbage collectors can used XOR-Node Pointers to keep one Node pointer instead of three [XOR-LList][4].

[1]: http://www.opendatastructures.org
[2]: https://brilliant.org/wiki/unrolled-linked-list/
[3]: https://blogs.msdn.microsoft.com/devdev/2005/08/22/unrolled-linked-lists/
[4]: http://opendatastructures.org/ods-java/img1346.png
[5]: https://www.linuxjournal.com/article/6828