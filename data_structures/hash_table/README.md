# Dictionary
> _Notes based on [CLRS][0]._ <br>
> _Notes based on [opendatastructures.org][1]._ <br>
> _Notes based on [Algorithms, 4th edition][2]._ <br>

Dictionaries and HashTables are dynamic sets that support,
1. `insert()`
2. `search()`
3. `delete()`

Apps use this DS for,
1. Compiler symbol table - mapping strings to language identifiers
2. Program Language Namespacing - associating variable names with in memory addresses
3. Multiway trees - quick access to internal child nodes
    - like in an [XFastTrie][3] using the highest i-bits to determine if a node is available on the i-th level of a Trie

Dictionaries implemented by HashTables perform well. In the worst case they __O(n)__ and in the average case insert/search/delete function in __O(1)__.

> [`Hash tables generalize the simpler notion of an ordinary array`][0]

Forms of Hash Tables,
1. Direct Addressing
2. Chaining
3. Open Addressing
4. Perfect Hashing

[0]: https://www.amazon.com/Introduction-Algorithms-3rd-MIT-Press/dp/0262033844
[1]: http://www.opendatastructures.org
[2]: https://algs4.cs.princeton.edu/34hash/
[3]: http://opendatastructures.org/ods-java/13_2_XFastTrie_Searching_in.html