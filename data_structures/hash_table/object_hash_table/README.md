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


[1]: http://www.opendatastructures.org