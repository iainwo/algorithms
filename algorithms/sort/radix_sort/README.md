# Radix Sort
> _Notes based on [opendatastructures.org][1]._ <br>
> _Notes based on [brilliant.org][3]._ <br>
> _Notes based on [geeksforgeeks.org][4]._ <br>
> _Notes based on [medium.com/basecs/][5]._ <br>

`Counting Sort` is restriced to sorting data which is similar or greater in magnitude to the range of numbers.
`Radix Sort` uses Counting Sort to sort large ranges of numbers on smaller data-sets.

The idea is to establish a radix, and then counting sort according to orders of that radix.
Since Counting sort is __stable__ this guarantees that the latter sorts of higher orders do not cause the previous partial orderings to degenerate.

Since the algorithm sorts by a radix - the number of passes required to complete the sort is the number of orders of that radix which fit the width of that integer.

```java
    int[] radixSort(int[] a) {
        int[] b = null;
        for (int p = 0; p < w/d; p++) {
            int c[] = new int[1<<d];
            // the next three for loops implement counting-sort
            b = new int[a.length];
            for (int i = 0; i < a.length; i++)
                c[(a[i] >> d*p)&((1<<d)-1)]++;
            for (int i = 1; i < 1<<d; i++)
                c[i] += c[i-1];
            for (int i = a.length-1; i >= 0; i--)
                b[--c[(a[i] >> d*p)&((1<<d)-1)]] = a[i];
            a = b;
        }
        return b;
    }
```
> From [opendatastructures.org][1] <br>

## Cost Analysis of Radix Sort

- Since `w/d` represents the number of orders of the radix which fit into a w-bit integer, where $`2^d`$ is the radix. There are `w/d` passes which must be performed to sort the data.
- Since the radix is equals to $`\lceil log_{2}(radix) \rceil = d`$, then the number elements which have to be counted is equivalent to $`2^d`. The internal counting sort algo will run in $`O(n + 2^d)`$ time.
- therefor $`O((w/d)(n + 2^d))`$ is the cost
    - w - is the number of bits in the biggest integer [0,..., 2^w]
    - w/d - can be thought of as the number of digits in the number
    - 2^d - as the base/radix size
        - if you say the biggest integer is $`n^c`$ then $`log_{2}(n^c) = c*log_{2}n`$ is `w` bits big
        - if you say $`d = log_{2}(n)`$ then $`O((w/d)(n + 2^d)) = \frac{c*log_{2}(n)}{log_{2}n}*(n + 2^{log_{2}n})`$
            - simplifying to $`= c*(2n) = O(cn)`$
            > when! the width is equivalent to a radix that is congruent with the number range, and that radix is within some `c` order ~= b^c of the biggest number
            > does the number then have to be represented in binary by widths of it's radix?? seems like it
- Radix sorts d-digits each digit being $`\leq base`$
- Radix sort does counting sort over each digit either MST/LSD.
- each radix pass takes $`O(n + b)`$ time and their are $`d`$ passes. The total time is $`O(d(n + b))`$ 
    - when $`d`$ is a constant and $`b`$ is not to much larger than $`n`$, possible $`b = O(n)`$, [radix is linear in time][3]
- if $`b=n`$ then $`O(n logn U) = O(n log U / log n)`$ where __U__ is the biggest element in the `List` - which is asymptotically good, but in practice suffers due to locality and caching. Read [here][2].
## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(kn) | __O(kn)__ | _[kn]_

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(n + k)__ | __O(n + k)__ | __O(n + k)__
> not in place

Stable,

is stable
:---:
Yes

## Considerations
- radix sort can be better than `Quick Sort` if you have the numbers recorded in the digits of their radixes. ~= $`log_{2}n` bits for every digit
    - `Quick Sort` uses caches more efficiently
    - constant asymptotic factors are higher for `Radix Sort` over `Quick Sort`? apparently
- can change bases of radix sort to avoid clustering of numbers for particular [digit/bucket][5].
- very good link describing why 256-bit bases make `Radix Sort` quicker than `Quick Sort` [here][7].
- terrific reference implementation from [Erik Gorset][8]

[1]: http://www.opendatastructures.org
[2]: https://stackoverflow.com/a/25675583
[3]: https://brilliant.org/wiki/radix-sort/
[4]: https://www.geeksforgeeks.org/radix-sort/
[5]: https://medium.com/basecs/getting-to-the-root-of-sorting-with-radix-sort-f8e9240d4224
[6]: https://cs.stackexchange.com/a/44151
[7]: https://erik.gorset.no/2011/04/radix-sort-is-faster-than-quicksort.html
[8]: https://github.com/gorset/radix/blob/master/Radix.java