# Longest Common Substring

## Definition of a Dynamic Programming Problem
Dynamic Programming is a set of problems with optimal substructure and overlapping subproblems.

Optimal Substructure is when the optimal answer to a global problem consists of the optimal answers to it's subproblems.

Overlapping Subproblems is when the problems comprising a larger problem have dependence on one another, they are not independent problems.

## Solving Dynamic Programming Problems
1. __Recognize a dynamic programming problem__
    - is there repeated subproblems
    - are there dependent subproblems
    - are there optimal solutions which are comprised of their subproblems optimal solutions
    - is the global problem definable in terms of it's subproblems
2. __Determine the number of changing parameters.__
    - not the number of subproblems per problem
        - rather the number of resources involved in those subproblems
    - if your problem was a function how many parameters do you have
        - which change
    - typically one (such as fib(x) or coinChange(y)) or two (editDistance(a,b))
3. __Express the recursive relation.__
    - once you know the recursive relation and the number of parameters this is just a formalization
4. __What are the base cases.__
    - At what point do the subproblems become atomic, and are no longer reducible.
    - Is there variance in the base case
        - are the solutions to subproblems distributed of (1) or (1+) base cases
5. __Decide to iterate or recurse as a solution__
    - what are the trade offs
        - recursive stack overflow
        - sparsity of computation (only need to calculate some of the base cases and subproblems)
        - cost of tabulation/vs memoization
6. __Add memoization.__
    - if recursive
        - just add caching to a subproblem lookup instead of recursing into that subproblem
7. __Time Complexity as the number of (possible states * work per state)__
    - linear if you have a single parameter
        - fib(0), fib(1), fib(2), ..., fib(n)
    - Quadratic if you have two parameters
    - Can be though of as the work you have to do to solve all subproblems!
> From [Nikola Otasevic - Cofounder, Refdash][1]

## Longest Common Substring
1. Check each substring if the substring adjacent is also a match.
    - repeated subproblems over the same indices
    - dependent subproblems indices are dependent on solving the same subproblems which use the same indices
    - global optimum is sum of the local optima (*constrained to adjacency of substrings)
2. The indices into each string change, indices into String 1, and indices into 2. Can just base of end and use one indice per String for size
3. `LS(A, B, x, y) = MAX(ROOTED_LS(A, B, x, y), LS(A, B, x-1, y), LS(A, B, x, y-1))`
4. `A[x] == B[y]` or `A[x] != B[y]`
5. Recurse is better when substrings are smaller
    - higher probability of entering "correct recursion"
    - penalty for recursing is small because the recursive depth and number of recursions is small
    - memoization combats penalty of "cache-miss" recursion subtrees
    - only have to pay for the recursions you do, tabulation pays for everything
    - only use as much memory as you need (stack & hashmap/length table)
    - code is simpler
6. Added.
7. $`O(A*B)`
    - $`\displaystyle\sum_{a=0}^A\sum_{b=0}^B (1 + a + b - | a - b |)`$
    > `a + b - | a - b |` returns the minimum of __a__ and __b__
    - $`A*B + A*B*(B-1)/2 + A(A-1)/2 + \displaystyle\sum_{a=0}^A(B(B-1)/2 -a)`$
    > `| a - b |` is always positive so can swap the summation from `| a - b |` to `| b - a |`<br>
    > basically just a sum of sums of B(B-1)/2 less the current iterations a val equaling to a(a-1)/2
    - $`A*B + A*B*(B-1)/2 + A(A-1)/2 + A*B(B-1)/2 - A(A-1)/2`$
    - $`O(A*B)`$

[1]: https://www.quora.com/What-are-the-top-10-most-popular-dynamic-programming-problems-among-interviewers