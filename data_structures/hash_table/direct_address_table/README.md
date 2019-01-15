# Direct-Address Table
> _Notes based on [CLRS][0]._ <br>

Used to implement a Dictionary (a dynamic set with insert/search/delete).

Unique, in that each element of the Universe is bijectively mapped to an index in an Array. This mapping is done by associating the Object's key with a unique index in the Array. The array will either store a reference to a satellite object containing that element or the array will store the object itself!

![Direct-Addressing Diagram](resources/direct_addressing.png)

## Direct-Addressing Criteria
1. There is a universe $`U`$ of keys which is small
2. The universe $`U = {0, 1, ..., m-1}`$, where $`m`$ is small
3. All elements have unique keys
4. Dynamic set is implemented by an array $`T`$
5. The array $`T = T[0, 1, ..., m-1]`$ has $`m`$ indices
6. Element $`k`$ is located in slot $`T[k]`$

## Pseudo Dictionary (insert/search/del) Impl With Direct-Addressing
> based on [CLRS][0]<br>

`search(T, k)`:
```java
    return T[k];
```

`insert(T, x)`:
```java
    T[x.key] = x;
```

`delete(T, x)`:
```java
    T[x.key] = null; // or NIL-like object
```

## Time and Space Complexity

Time,

function | best case | worst case
--- | :---: | :---:
`search(x)` | O(1) | __O(1)__
`insert(x)` | O(1) | __O(1)__
`delete(x)` | O(1) | __O(1)__

Space,

best case | worst case
:---: | :---:
O(n) | __O(n)__

## Considerations
- can store key or actual element in the arraay backing direct-addressing
    - storing obj. in array/table saves space
    - must use special key to signify NIL
    - don't need to store key, bc if you can get the index you have the key
- if universe $`U`$ is too large, direct-addressing is impractical because of memory required by the array backing direct-addressing
- if the ratio of elements stored in a direct-addressing table versus the total number of elements in the universe, is low, the memory usage by the table is wasteful
    - hash table would be better option

[0]: https://www.amazon.com/Introduction-Algorithms-3rd-MIT-Press/dp/0262033844