# Trie
Tries are associative arrays with an overlay of referential structure.
The referential structure which points to the association of a key and it's value infer knowledge about said <K,V>.

The value of a Trie derives from it's construction;
whereby a __key__ is a composite. Typically the constituents of that key are ordinal in sequence, usually from one data-feature.

Trie's allow lookups based on partial keys and incomplete keys.
This sort of matching allows one manipulating the ADT to resolve various subsets and qualities betwixt those subsets - like intersection, union, and difference.

## Pros
> From [StackOverflow][1]

Basic,
- Predictable O(k) lookup time where k is the size of the key
- Lookup can take less than k time if it's not there
- Supports ordered traversal
- No need for a hash function
- Deletion is straightforward

Idiosyncratic,
- You can quickly look up prefixes of keys, enumerate all entries with a given prefix, etc.

Linked Nature promotes,
- If there are many common prefixes, the space they require is shared.
- Immutable tries can share structure. Instead of updating a trie in place, you can build a new one that's different only along one branch, elsewhere pointing into the old trie. This can be useful for concurrency, multiple simultaneous versions of a table, etc.
- An immutable trie is compressible. That is, it can share structure on the suffixes as well, by hash-consing.

## Time and Space Complexity
The complexity of this datastructure is highly dependent on it's implementation.

A more _generic_ implementation would likely perform like this,

Time,

function | best case | worst case
--- | :---: | :---:
`create()` | O(1) | __O(w*k)__
`lookup(x)` | O(1) | __O(a*k)__
`insert(x)` | O(1) | __O(a*k)__
`delete(x)` | O(1) | __O(a*k)__
> - __w__ represents the longest key
> - __k__ represents the number of keys
> - __a__ represents the length of a key

Space,

best case | worst case
:---: | :---:
O(1) | __O(w*k)__

## Considerations
Hashtables are much faster than tries, even in their worst case.
Hashing Objects/Keys __can__ be more costly.

Pros of HashTables (cons of Tries),
- well known, well-optimized implementation, faster than tries for most purposes.
- keys need not have any special structure.
- more space-efficient than the obvious linked trie structure (see comments below)

## Pictures
![Trie Diagram][0]

[0]: https://ds055uzetaobb.cloudfront.net/image_optimizer/d41743795c44c298a8917c0baa559ee51cf713e2.jpg
[1]: https://stackoverflow.com/a/245976