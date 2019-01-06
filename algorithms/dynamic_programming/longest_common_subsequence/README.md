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



[1]: https://www.quora.com/What-are-the-top-10-most-popular-dynamic-programming-problems-among-interviewers