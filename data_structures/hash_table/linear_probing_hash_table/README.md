# Linear Probing Hash Tables (Open Addressing)
> _Notes based on [opendatastructures.org][1]._ <br>
> _Notes based on [en.bitcoinwiki.org][2]._ <br>
> _Notes based on [mathworld.wolfram.com][3]._ <br>


Different from Chained Hash Tables, Linear Hash Tables embed their values directly into an array - inline.
If there is collision, algo will iterate forward to a non-element value.
This iteration forward requires modulation guards to prevent exhausting the array.

Hash Table Array can have these values,
1. NULL
2. DELETED - was a value before since deleted
3. VALUE - a legitimate value

The hash table has these attributes,
```java
    T[] t;   // the table
    int n;   // the size
    int d;   // t.length = 2^d
    int q;   // number of non-null entries in t
```

__q__ eeps track of the number of elements and the number of deleted.
Need to keep a large table with many nulls so chances of collision are low.

Must maintain the invariant,
1. $`2q \leq t.length`$
2. Keep hashtable size a power of 2 - easier for hashing funcs

## Find in Linear Hash Table
1. Calc hash
2. Iterate from hash forward looking for non-del and equivalent objs
3. Quit if hit null indice
4. Rest to zero-idx if at end
```java
    T find(T x) {
        int i = hash(x);
        while (t[i] != null) {
            if (t[i] != del && x.equals(t[i])) return t[i];
            i = (i == t.length-1) ? 0 : i + 1; // increment i
        }
        return null;
    }
```

## Add in Linear Hash Table
1. Find if obj exists
2. Check capacity -> grow if needed
3. Calc hash
4. Go node and iterate while nodes are populated with real values
5. inc. q if the index holds a null val, because we are not removing an old deleted
6. set node
7. inc number of nodes n
```java
    boolean add(T x) {
        if (find(x) != null) return false;
        if (2*(q+1) > t.length) resize(); // max 50% occupancy
        int i = hash(x);
        while (t[i] != null && t[i] != del)
            i = (i == t.length-1) ? 0 : i + 1; // increment i
        if (t[i] == null) q++;
        n++;
        t[i] = x;
        return true;
    }
```

## Remove from Linear Hash Table
1. Calc hash
2. iterate to matching node or null, reset to zero-idx if at end
2. Set to delete
3. Drop n
4. Check if below capacity (n8 < t.length) -> resize
5. return value
```java
    T remove(T x) {
        int i = hash(x);
        while (t[i] != null) {
            T y = t[i];
            if (y != del && x.equals(y)) { 
                t[i] = del;
                n--;
                if (8*n < t.length) resize(); // min 12.5% occupancy
                return y;
            }
            i = (i == t.length-1) ? 0 : i + 1;  // increment i
        }
        return null;
    }
```

## Resize Hash Table
```java
    void resize() {
        d = 1;
        while ((1<<d) < 3*n) d++;
        T[] told = t;
        t = newArray(1<<d);
        q = n;
        // insert everything from told
        for (int k = 0; k < told.length; k++) {
            if (told[k] != null && told[k] != del) {
                int i = hash(told[k]);
                while (t[i] != null) 
                    i = (i == t.length-1) ? 0 : i + 1;
                t[i] = told[k];
            }
        }
    }
```

## Tabulation Hashing
Any elements $`(x_{1}, ..., x_{n})`$ the hash values $`hash(x_{1}), ..., hash(x_{n})`$ are independently and uniformly distributed over $`[0, ..., t.length - 1]`$
> _based on [opendatastructures.org][1]._

The key part is that the hashes are stochastically independent - _meaning_ the occurence of one hash does not affect the probability of occurence of the other; where one hash code does not affect the distribution of another hash code - i.e the origin values which are mapped to a hashed value surjectively from the set $`2^w`$ does not affect the mappings of another hash code and it's mappings.

When Linear Probing has a hash collision, due to the collision resolution scheme elements are placed in the hash locations of other hash codes. This has a cascading effect and property that a collision will engender many more collisions. The number of collisions degrades performance.

To decrease the number of collisions and keep performance optimal high-fidelity hash functions are required. These high quality functions reduce the distributive mistakes - that is, the frequency of nonuniformities. __Usually these functions have the property of being uniformly distribute guaranteed across some length of input keys; where k-tuples of distinct keys are equally likely to be mapped to any unique k-tuple of hash indices__. 

This technique is part of several other functions which begin with an intial seed val and have the property that any k-tuple of distinct keys will map to a k-tuple of indexes. In the case of tabulation, $`k=3`$ so that 3-tuple of distinct keys are equally like to map to any 3-tuple of hash indices. Linear probing however requires 5-independence hashing - however tabulation is an exceptional case and will suitable distribute the values to hash.
> 3-independent means, for every 3-tuple of keys and its possible 3-tuple values, the probability that those keys are mapped to a given vallue is $`\frac{1}{m^k}`$ or $`\prod^k\frac{1}{m}`$

Note that tabulation hashing is restricted to a fixed number of bits, and is unsuitable for data-types of undefined lengths like strings or arrays. Undetermined length data-types require universal hashing techniques, where each key comprising the composite value to hash is, is mapped to an intermediate val, and then those intermediate vals a high quality hash function (5-independent or tabulation) is used to map those intermediates to a hash indice.
> Universal hashing guarantees due to the randomness of the selected hashing function, that $`hash(x) = hash(y)`$ has a probability of $`1/m`$ when $`m`$ is the number of buckets in the redistribution of preimage hash values.

Idea here is treat the key as a vector of $`r`$-bit elements. Multiply this vector against it's linear transformation - as represented by the values in the tabular $`t * 2^rbit`$ dimensional array.

breaks down for four keys because there are sets of keys w, x, y, and z where none of the four has a byte value that it does not share with at least one of the other keys
w, x, y, and z are the four keys that have either zero or one as their byte values, then each byte value in each position is shared by exactly two of the four keys. For these four keys, the hash values computed by tabulation hashing will always satisfy the equation , whereas for a 4-independent hashing scheme the same equation would only be satisfied with probability 1/m

The other part is the uniform or equal distribution of $`2^w`$ elements accross all the possible hash values.
```java
    int hash(T x) {
        int h = x.hashCode();
        return (tab[0][h&0xff] 
                 ^ tab[1][(h>>>8)&0xff]
                 ^ tab[2][(h>>>16)&0xff] 
                 ^ tab[3][(h>>>24)&0xff])
                  >>> (w-d);
    }
```

## Cost Analysis of Linear Probing
> For other proof can use Chernoff bound

- cost of linear probing starts at the hash index of the obj, and finalizes at the nearest null val - __known as a run__
- previously, loosely, idea was incr. the number of null vals in the hash table will decrease the likelyhood of hashes and their non-uniform distributions of cascading into one another - because there is buffer/give between the distribution
> this isn't rigorous, although intuitively seems sound
- __Assume uniform and independent distribution of the preimage values of the hashing func__
- invariant of linear probing is that num. of non-null elements __q__, __2q__ < t.length
- __q__ elements have been inserted since last rebuild

imagine that you are trying to `find(x)`, there is the possibility i's hash could land on pre-existing run of length __k__. This means that the cost to find __x__ is equal to $`O(1 + \frac{t.length \choose 1}{t.length}\displaystyle\sum_{k=0}^{\infin}(k*\Pr \{ P_{k} \}))`$

This simplifies to $`O(1 + 1*\displaystyle\sum_{k=0}^{\infin}(k*k*\Pr \{ P_{k} \}))`$, $`k*k*\Pr \{ P_{k} \}`$ symbolizes the probability that find's hash value is one of the indices on the run of k-len

This simplifies to $`O(1 + \displaystyle\sum_{k=0}^{\infin}(k^2*\Pr \{ P_{k} \}))`$

The question is what in the heck is $`\Pr \{ P_{k} \}`$!!



Where __c__ is a constant,
```math
\Pr \{run_{k}\} = O(c^k)\text{, where } 0 \lt c \lt 1
```

Well,
- $`{q \choose k}`$ is the number of combinations of __k__ elements
- $`\frac{k}{t.length}^k`$ is the probability that a k-length combination could be contiguous
- the probability that a hash falls in a subrange of the image is $`length(k)/t.length`$ where __k__ is the length of the subrange
- the probability that a hash falls in the subrange of the image outside __k__'s subrange is $`\frac{t.length-k}{t.length}`$, because there $`t.length-k`$ positions which that image could fall, and that number of positions composes a fraction of the total buckets in the image
- the probability that two elements fall in the subrange of __k__ are $`\frac{k}{t.length}*\frac{k}{t.length}`$
- therefor the total probability of a __k__-length run is,
```math
\Pr \{k-length run\} = \Pr \{ P_{k} \}= {q \choose k}\frac{k}{t.length}^k\frac{t.length-k}{t.length}^{q-k}
```

Thus we can express the Probability but we still don't know what it means. How it behaves. Goal is to reduce the Probability formula we have to something we can use for Asymptotic ANalysis of $`O(1 + \displaystyle\sum_{k=0}^{\infin}(k^2*\Pr \{ P_{k} \}))`$

So let's start with,

```math
\Pr \{k-length run\} = \Pr \{ P_{k} \}= {q \choose k}\frac{k}{t.length}^k\frac{t.length-k}{t.length}^{q-k}
```

Sub in __q__ bc, 2q < t.length
```math
\Pr \{k-length run\} <= {q \choose k}\frac{k}{2q}^k\frac{2q-k}{2q}^{q-k}
```

Explicitly state the combinations,
Sub in __q__ bc, 2q < t.length

$`\Pr \{k-length run\} <=  \frac{q!}{(q-k)!k!}\frac{k}{2q}^k\frac{2q-k}{2q}^{q-k}`$

Now simplify the factorial, we can do that by Stirling's Approximation.
> $`n! = n*(n-1)*...*1`$ <br>
> $`ln(n!) = ln(n*(n-1)*...*1)`$ <br>
> $`ln(n!) = ln(n)+ln(n-1)+...+ln(1)`$ <br>
> $`ln(n!) = \displaystyle\sum_{k=1}^n(lnk)`$ <br>
> $`ln(n!) \thickapprox \int_{1}^nln(x) dx`$ // stirlings approx. replace reinman sum with integral and change __k__ for continuous __x__ <br>
> $`ln(n!) \thickapprox [xln(x) - x]_{1}^n`$ <br>
> $`ln(n!) \thickapprox (1ln(1) - 1) + ... + (2ln(2) - 2) + ... + (nln(n) - n)`$ // solve series using <multiply,shift, subtract> <br>
> // TODO finish solving series ☹ <br>
> $`ln(n! \thickapprox nln(n) - n`$

$`\Pr \{k-length run\} <=  \frac{q^q}{(q-k)^{q-k}k^k}\frac{k}{2q}^k\frac{2q-k}{2q}^{q-k}`$

$`\Pr \{k-length run\} <=  \frac{q^{k}q^{q-k}}{(q-k)^{q-k}k^k}\frac{k}{2q}^k\frac{2q-k}{2q}^{q-k}`$

$`\Pr \{k-length run\} <=  \frac{1}{2}^k\frac{q(2q-k)}{2q(q-k)}^{q-k}`$

$`\Pr \{k-length run\} <=  \frac{1}{2}^k\sqrt(e)^k`$

This plugs into our cost func,
> $`O(1 + \displaystyle\sum_{k=0}^{\infin}(k^2*\Pr \{ P_{k} \}))`$ <br>
> $`O(1 + \displaystyle\sum_{k=0}^{\infin}(k^2*O(\frac{1}{2}^k\sqrt(e)^k)))`$ <br>
> $`O(1 + \displaystyle\sum_{k=0}^{\infin}(k^2*O(c^k)))`$ where $`c^k`$ appoaces zero as $`x \to \infin`$ <br>
> $`O(1)`$

## Time and Space Complexity
Time,

function | best case | worst case | practical worst case
--- | :---: | :---: | :---:
`add(i,x)` | O(1)<sup>E,A</sup> | __O(n)__<sup>E,A</sup> | _O(n)_<sup>E,A</sup>
`remove(i)` | O(1)<sup>E,A</sup> | __O(n)__<sup>E,A</sup> | _O(n)_<sup>E,A</sup>
`find(x)` | O(1)<sup>E</sup> | __O(n)__<sup>E</sup> | _O(n)_<sup>E</sup>
> - __O(x)__<sup>A</sup> - means amortized <br>
> - If there are $`1 <= m`$ calls to `add(i,x)` or `remove(i)` there is at most $`\Omicron(m)`$ comuptations aggregated across the $`\{ add(i,x), remove(i) \}`$ operations.

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(n)__ | __O(n)__ | __O(n)__

[1]: http://www.opendatastructures.org
[2]: https://en.bitcoinwiki.org/wiki/Tabulation_hashing
[3]: http://mathworld.wolfram.com/StirlingsApproximation.html