# Hash Codes for various data-types
> _Notes based on [opendatastructures.org][1]._ <br>

There are two invariants required to hash objects,
1. if $`x = y`$, then $`hash(x) == hash(y)`$
> So can access same object later
2. if $`x \neq y`$, then $`\Pr \{hash(x) == hash(y)\} \leq 3/2^{2w}`$
> Ensure object hashes are reflective of their values

## Primitive-Type Hash Codes
Can just use bit-representation if the width is $`\lt 2^w`$.

Examples
- byte
- char
- float is 32bit

For primitives larger than $`w`$, usually they are an integer multiple of $`w`$, where that integer is equal to $`i = \log_{2}(2^{c*w})\mod w`$.
For these types treat them like `Compound Object Hash Codes`.

## Compound Object Hash Codes
Hashes made by combining the recursive hashes of an Objects members.

Combining hash codes with bitwise xor-operator is hacky and don't produce quality hashing.

There are simple, better-quality, hashing functions that are available if you use $`2^{2w}`$ bits of precision.

- Let a Compound Object be represented by it's many parts $`P = \{ P_{0}, P_{1}, ..., P_{n} \}`$.
- Let a Compound Objects hash codes be $`\{x_{0}, x_{1}, ..., x_{n} \}`$.
- Let a __mutually__ random $`w` bit odd integers be $`\{z_{0}, z_{1}, ..., z_{n} \}`$.
- Let a random $`2w`$ bit odd integer be $`z`$

To calculate a compound object hashcode do,

```math
h(x_{0}, x_{1}, ..., x_{n}) = ((z\sum_{i=0}^n(z_{i}x_{i} \mod 2^{2w})) \div 2^w
```

In code,
```java
    int hashCode() {
        // random numbers from rand.org
        long[] z = {0x2058cc50L, 0xcb19137eL, 0x2cb6b6fdL}; 
        long zz = 0xbea0107e5067d19dL;

        // convert (unsigned) hashcodes to long
        long h0 = x0.hashCode() & ((1L<<32)-1);
        long h1 = x1.hashCode() & ((1L<<32)-1);
        long h2 = x2.hashCode() & ((1L<<32)-1);
        
        return (int)(((z[0]*h0 + z[1]*h1 + z[2]*h2)*zz)
                     >>> 32);
    }
```

### Collision Probability - Compound Object Hash Codes

- Let this be the first objects bit fields $`\{ x_{0}, x_{1}, ..., x_{n} \}`$.
- Let this be the second objects bit fields $`\{ y_{0}, y_{1}, ..., y_{n} \}`$.
- Let $`x_{i} \neq y_{i}`$ for $`\geq 1`$ index in $`i \in \{0, 1, ..., n \}`$

- Posit $`a^{\prime}(x_{0}, ..., x_{n}) = a^{\prime}(y_{0}, ..., y_{n})`$
- Rewrite as $`t = (x_{i} - y_{i}) \mod 2^{2w}`$
- Expanded as $`t = (\sum_{i=0}^{i-1}(z_{i}(x_{i} - y_{i})) + \sum_{j=i+1}^{n}(z_{j}(x_{j} - y_{j})))\mod 2^{2w})`$
- Assume $`x_{i} \gt y_{i}`$, then $`t = z_{i}(x_{i} - y_{i})`$
- Both $`z_{i}`$ and $`(x_{i} - y_{i})`$ is $`\leq 2^w-1`$
- Therefor product of $`z_{i}(x_{i} - y_{i}) \leq (2^w - 1)^2 = 2^{2w} - (2)2^w + 1 = 2^{2w} - 2^{w+1} + 1 \leq 2^{w2}-1`$
- Thus the products must be positive and in a bit range of $`2w`$ to $`w + 1`$
- Since the product is positive if $`a^{\prime}(x_{0}, ..., x_{n}) = a^{\prime}(y_{0}, ..., y_{n})`$, then $`0 = product - z_{i}(x_{i} - y_{i})`$ for only one value.
- Since $`z_{i}`$ is a odd $`2^w`$ bit integer that is mutually independent of the other __z__ values and the independent of the hash input values the probability that $`z_{i}`$ acts as the correct root (solves the equation) is $`\Pr {0 = product - z_{i}(x_{i} - y_{i})} = 1/(2^w)`$

- Next map $`a^{\prime}(x) \to hash(x)`$ and also map $`a^{\prime}(y) \to hash(y)`$, the only cases which $`hash(x) = hash(y)`$ is when the modular truncation (of the deltas, x-y) produces a range of all zeroes or all 1s in it. This is because $`2^w`$ as a div value means that any retained product after the div must be $`\gt 2^w`$; consequently there are thus only $`2^{2w} - 2^w`$ bit fields and therefor the opportunity for that range to be all 1s or 0s is only two cases - whereas the total possible values are $`2^w `$. That means that the $`\Pr { hash(x) = hash(y) } = 2/2^w`$ - 2 cases divided by all the other $`2^{2w}`$ possibilities.

$`\Box`$

## Arrays and String Hash Codes
`Compound Object Hash Codes` work well with finite fields; they do not work well, with variable length data-types - due to the dependency on mutually independent-random-odd $`w` integers $`z_{y}`$.
> Can't use pseudorandom sequence to generate __z__ values because the pseudo random sequence is not mutually independent - then would have to prove that pseudorandom non-mutually independent $`z`$ numbers don't cause non-mutually independent $`t`$ relationships between objects x and y and thus cause hashing collisions.
> > Better to construct hashes with polynomials and primes<br>
> > This idea is based on theorem that a series of polynomials over prime fields behave like typical polynomials

Theorem - let $`p`$ be a prime and let $`f(z) = x_{0}z^0 + x_{1}z^1 + ... + x_{n}z^n`$ be a non-trivial (greater than zero degree) with coefficients $`x_{i} \in \{0, ..., p-1\}`$, then $`f(z)\modp = 0`$ has at most $`n`$ solutions for $`z \in \{0, ..., p-1\}`$
> In implementation the x coefficients are the values from the object to hash, they should __not__ exceed $`\lt p-2`$ so that $`(p-1)`$ can be used as a salt in the computer algorithm

- $`h(x_{0}, x_{1}, ..., x_{n}) = (x_{0}z^0 + x_{1}z^1 + ... + x_{n}z^n + (p-1)z^(n+1)) \mod p`$
> note that __(p-1)__ is different from any other value of __x__ which are the possible values of the object to hash. These values must be $`\lt p-1`$

### Polynomial over Primes Collision Proof
- let $`p \gt 2^w+1`$, assume $`x_{0}, x_{1}, ..., x_{n}`$ and $`y_{0}, y_{1}, ..., y_{n}`$ be sequences of w-bit integers and assume $`x_{i} \ne y_{i}`$ for at least one index

$`((x_{0}-y_{0})z^0 + (x_{1}-y_{1})z^1 + ... + (x_{n}-y_{n})z^n) \mod p = 0`$
> Since there is at least one index which __x__ and __y__ values do not equal, the difference and each hash's polynomial themeselves have to be non-trivial, due to this there are only __n__ possible __z__ solutions due to the theorem. due to the range of possible number up until $`p-1`$ there can be $`p-1`$ numbers. therefor the probability that the index that is unequal has the solution __z__ is $`n/p`$.
> > When the two objects are of different length the collision probability changes to $`MAX\{n1,n2\}/p)`$

### Hashing Implementation and Idiosyncracies
```java
 int hashCode() {
        long p = (1L<<32)-5;   // prime: 2^32 - 5
        long z = 0x64b6055aL;  // 32 bits from random.org
        int z2 = 0x5067d19d;   // random odd 32 bit number
        long s = 0;
        long zi = 1;
        for (int i = 0; i < x.length; i++) {
            // reduce to 31 bits
            long xi = (x[i].hashCode() * z2) >>> 1; 
            s = (s + zi * xi) % p;
            zi = (zi * z) % p;    
        }
        s = (s + zi * (p-1)) % p;
        return (int)s;
    }
```
> uses d=31 to reduce x.hashcode to a 31-bit value, this is so unsigned 63-bit arithmetic can be performed <br>
> probability therefor changes to $`2/2^31 + MAX\{n1,n2\}/(2^{32}-5)`$

[1]: http://www.opendatastructures.org