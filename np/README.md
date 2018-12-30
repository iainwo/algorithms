# Nondeterministic Polynomial (NP)
> _Notes based on [en.wikipedia.org][1]._ <br>
> _Notes based on [stackoverflow.com][3]._ <br>

#### ["I know an NP-Complete joke, but once you've heard one you've heard them all."](https://stackoverflow.com/questions/1857244/what-are-the-differences-between-np-np-complete-and-np-hard#comment25566954_1857244)

`Computational Complexity Theory` aims to categorize and classify computationally solvable problems according to their instrinsic difficulties which contribute to their time/computation cost.

Formally `Nondeterministic Polynomial` is made up of these members,
1. __Polynomial Time__ (Can the problem be solved in polynomial time (Y/n))
2. __Nondeterministic Polynomial Time__ (Have solutions which can be verified in Polynomial Time (Y/n))
3. __Nondeterministic Polynomial Complete__ Time (Is this Polynomial-time verifiable problem a means of computing another another NP Problem in polynomial time)
4. __Nondeterministic Polynomial Hard Time__ (is this problem solvable in polynomial time by using the solution to a NP-Complete problem)
> basically a polynomial behaving function which is composed (f o g) of solutions to other problems which may also be polynomial solutions - that involve solutions which may or may not be polynomially solvable.

It is under debate whether these members are concentric/overlapping subproblems of one another, or, just polynomial ubiquituous and homeogenous.

![NP Constituency][2]

## Nondeterministic Polynomial Complete (NPC)
`Nondeterministic Polynomial Complete` class problems are class which are both __NP__ and __NP Hard__.

The correctness of a solution to these problems can be validated in Polynomial time, however the solutions to these problems are currently only computable with asymptotic behaviours which are more ineffecient than polynomially classes.

Currently __NP__ problems can be solved in superpolynomial time.

## Simplifying NPC
__NP__ problems can be oversimplified that is correct or mostly correct. These are some techniques which can be done so,
1. Approximation: compute a solution to a similar problem which is within a factor of the optimal/original problem
2. Randomization: employ probability to shorten the computation times; at the cost of probably incorrect solutions.
3. Restrictions: Reduce the dimensionality of the problem; to a sub-problem which asymptotic cost is less and incongruent to the original problem
4. Parameterization: Solve a particular case/meta-problem of problem, by cementing a number of the independent and dependent variables contributing to variant solutions
5. Heuristic: found a solution on an assumption that may not produce the correct result nor run efficiently, but is correct or mostly correct within an tolerance level



[1]: https://en.wikipedia.org/wiki/NP-completeness
[2]: https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/P_np_np-complete_np-hard.svg/600px-P_np_np-complete_np-hard.svg.png
[3]: https://stackoverflow.com/a/1857342