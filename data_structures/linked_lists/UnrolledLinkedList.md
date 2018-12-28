# Unrolled Linked List (Space Efficient List - SEList)
> _Notes based on [opendatastructures.org][1]._ <br>
> _Notes based on [brilliant.org][2]._ <br>
> _Notes based on [blogs.msdn.microsoft.com][3]._

There are good algorithmic reasons for preferring `Arrays` over `LinkedLists` and vice-versa.

Pro/Con | Quality | LinkedList | Array
:---: | :---: | :---: | :---:
\+ | _Access_ | | __x__ 
\+ | _Insertion_ | __x__ | 
\+ | _Contiguity_ | | __x__
\- | _Extra References_ | __x__ | 

However datastructures are not only focused with these type of algorithmic concerns.
`Unrolled Linked Lists` address __cache lines__ in addition to algorithmic tradeoffs of size and between __search(x) vs add(i,x)/remove(i,x)__.

## Cache Lines

> _Modern PCs have multi-level cache hierarchies that make traversing an array (visiting the elements in order) very fast. Cache hits are so fast that in cache-sensitive analysis they are considered "free"; we only count cache misses. If a cache line has size B, then the number of cache misses is about n/B. A linked list, on the other hand, requires a cache miss for every node access in the worst case. Even in the best case, when the nodes are allocated consecutively in order, because linked list nodes are larger, it can require several times more cache misses to traverse the list._ <br>
> > _... I constructed a linked list of 60 million integers and created an array of the same 60 million integers. I compiled with full optimization. Traversing the linked list required 0.48 seconds, while traversing the array required 0.04 seconds, 12 times faster. Moreover, when I introduced code to fragment the memory pool, the advantage increased dramatically to 50 times or more. The linked list also required 4 times as much memory - twice as much for next pointers, and twice as much again for allocation metadata._ <br><br>
> \- [blogs.msdn.microsoft.com][3].

## Space
Unrolled Linked Lists support a trade off between memory compactness and operation time.
Even more space is saved for data like chars and bits.
The SEList can be configure to require a higher threshold of populated data; this will increase the density of the stored data at the cost of more frequent node splits and merges.


[1]: http://www.opendatastructures.org
[2]: https://brilliant.org/wiki/unrolled-linked-list/
[3]: https://blogs.msdn.microsoft.com/devdev/2005/08/22/unrolled-linked-lists/