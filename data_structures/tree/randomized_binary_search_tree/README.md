# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Randomized Binary Search Tree (Randomized BST)
![Unbalanced BSTs containing [0,14]][0]
![Balanced BSTs containing [0,14]][1]

## Worst Case length of Search Path
The worst case can be proven combinatorially or stochasticlly.

### Cominatorial Search Path Proof
There are numerically small numbers of BST insertion sequences which will create an unbalanced binary of `Set {S}`.
> T(n): is the number of subtrees where there is a __run__ which is $`\gt \log{n}`$ in length. Where __n__ is the size of the BST<br>
> T(n) = $`\displaystyle\sum_{i=\log{n}}^n i! {n \choose i}`$, equates to the number of unbalanced combinations

The number of balanced BST insertion sequences of `Set {S}` are many,
> F(n): is the number of subtrees where there is a run which is <= log(n) in length. Where n is the size of the BST<br>
> F(n) = `Summation of i=n down to logn of i! * n choose i`<br>
> F(n) equates to the number of Balanced BSTs under the threshold of logn

Following,
> `T(n) <= F(n)` since a series composed of perumtations multiplied by combinations for a smaller number range is combinatorially smaller than the same of a higher number range. There is always more combinations when there is more variety.

### Stochastic Search Path Proof
There are `n!` permutations of a `n` length sequence. The set of elements inserted into a Randomized BST has `n!` possible sequences of insertion.

The probability of selecting a variant of those permuations is `1/n!`.

Likewise the probability of selecting a variant of a subsequence found in a variant of a sequence `n` is equivalent to `1/i!` where is the length of the subsequence.

There are two cases which influence the path construction of a node with `x`.
1. if a number `z < x`
2. a num `z > x` 

If `z < x`, if any number > ___z___ has already been inserted into the tree, then ___z___ will be placed left of the value and consequently not be on the search path of ___x___. Assumes ___x___ is not in the tree. Means that the possibility ___z___ is not on the search path is all the permuations which a number bigger than ___z___ occurs before ___z___ in the range <b>[z,x-1]</b>. ___z___ will be on the search path of ___x___ if there are no elements which are greater than ___z___ and less than ___x___.
> Pr{z is on search path x} = `1/[floor(x)-z+1]`, if `z < x`

If `z > x`, and is in the tree, then ___z___ will only be on the search path of ___x___ if there are no other elements occuring before ___z___ which are less than ___z___ and greater than ___x___. 
> Pr{z is on search path x} = `1/[z-ceil(x)+1]`, if `z > x`

Formalized,<br>
![Stochastic probability of the rank of z occuring on the search path][probability_eq_0]
![Stochastic probability of the rank of z occuring on the search path][probability_eq_1]

The application of these probability measures is done in a permuation sort of calculation. Whereby probability that __z__ occurs on the search path before __x__ is calculated for all __z__ values in the range <b>[0,x-1]</b> and <b>[x+1,n-1]</b> where ___n___ is the total number of elements in the BST.

![Expectation of {z} between the root and element x][stochastic_0]
![Expectation of {z} between the root and element x][stochastic_1]

![Expectation of {z} between the root and element x][stochastic_2]

![Expectation of {z} between the root and element x][stochastic_3]

![Expectation of {z} between the root and element x][stochastic_4]

![Expectation of {z} between the root and element x][stochastic_5]

![Expectation of {z} between the root and element x][stochastic_6]

The Harmonic number is series which is made by,
> H(k) = 1 + 1/2 + 1/3 + ... + 1/k

The k-th Harmonic number is always found between the range of [`ln(k)`, `ln(k)+1`]. Because ln(x) is equal to the integral from 1 to k of f(x): 1/x.
> itgrl(1/x)  <=   H(k)  <=   itgrl(1/x) + 1, from 1 to k.

The Harmonic number and the probability that `{z}` elements occur on `x`'s path is,
![The integral of 1/x][ln_0]
![The integral of 1/x + 1][ln_1]

The Probability that `{z}` elements occuring on the path to `x` is,<br>
![The probability distribution of {z} being in the path of x][probability_dist]

This distribution yields a probability that the depth of `x` is,
> $`E[\text {search path length}] = H(x+1) + H(n-x) - O(1)`$, when $`x \in \{S\}`$<br>
> $`E[\text {search path length}] = H(\lceil x \rceil) + H(n- \lceil x \rceil)`$, when $`x \notin \{S\}`$
> > $`\therefore \text{ }\leq 2ln(n) + O(1)`$

## Time and Space Complexity

`find(x)` in a Randomized BST require resolving a path between the root and node of a tree. A Randomized BST is stochastically balanced.

Time,
> `Randomized BST` - probability tree height is worst case _expected_ time `E[O(logn)]`.

> > Path from __root__ to __x__ is a balanced path. This is true for every possible search value.

Space,
> `Randomized BST` - space is worst case `O(n)`.

[0]: http://opendatastructures.org/ods-java/img2843.png
[1]: http://opendatastructures.org/ods-java/img2844.png
[probability_eq_0]: http://opendatastructures.org/ods-java/img2941.png
[probability_eq_1]: http://opendatastructures.org/ods-java/img2942.png
[stochastic_0]: http://opendatastructures.org/ods-java/img2949.png
[stochastic_1]: http://opendatastructures.org/ods-java/img2950.png
[stochastic_2]: http://opendatastructures.org/ods-java/img2951.png
[stochastic_3]: http://opendatastructures.org/ods-java/img2952.png
[stochastic_4]: http://opendatastructures.org/ods-java/img2953.png
[stochastic_5]: http://opendatastructures.org/ods-java/img2954.png
[stochastic_6]: http://opendatastructures.org/ods-java/img2955.png

[ln_0]: http://opendatastructures.org/ods-java/img2878.png
[ln_1]: http://opendatastructures.org/ods-java/img2879.png

[probability_dist]: http://opendatastructures.org/ods-java/img2957.png