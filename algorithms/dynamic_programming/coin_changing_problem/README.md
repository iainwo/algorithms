# Coin Changing Problem
> _Notes based on [brilliant.org][1]._ <br>
> _Notes based on [ccs.neu.edu][2]._ <br>

Coin Changing Problem is exemplary of a dynamic programming problem, it has the two qualities,
1. Optimal Substructure - the problem can be broken into simpler subproblems
2. Overlapping Subproblems - the simpler problems are repitious or overlap

The `Coin Changing Problem` is - __what is the minimum number of coins of values v1, v2, v3, ..., vn required to amount to a total V__?
1. Optimal Substructure - if we take a collection which is the minimum amount totalling to V, and we reduce it by one coin, what is the minimum number of coins of values v1, v2, v3, ..., vn required to total (V - the removed coin's value). If we were to try this for each coin value, the formula f(S): the minimum number of coins of values v1, v2, v3, ..., vn requireed to amount to the total S, would appear like f(S - v1) vs. f(S -v2) vs. f(S -v3), ..., f(S - vn); which of those smaller problems has the minimum number of coins. The problem can be further reduced like this because, these subproblems are apart of the larger solution.
2. Overlapping Subproblems - the cost function, f(S-v1), will recursively perform f(S-v1 - v1) vs. f(S-v1-v2) vs. f(S-v1-v3) vs. ... f(S-v1-vn). Therefor the most simple f(0) or f(1) will be recursively called by all n initial variants of f(S -vi). This means that the subproblems overlap!

The solution to dynamic programming problems, is to reduce the optimal substructure into it's baser cases and then cache those solutions and use them in overlapping problems and more complex versions of those simpler cases - reducing the overall computation at the expense of memory.

## Dynamic Programming - Tabulation
Solve the problem from the bottom-up starting from the base case and inductively expanding to all other cases.
```python
# V = the value we want, v=the list of available denomenations 
def coinsChange(V,v):
    dpTable = [float("inf")]*(V+1)
    dpTable[0] = 0
    for i in xrange(1,V+1):
            for vi in v:
                    if (i - vi) >= 0:
                            dpTable[i] = min(dpTable[i],1+dpTable[i-vi])
    return dpTable[V]
```
From [brilliant.org][1].

```
Change(d, k, n)
1 C[0] ← 0
2 for p ← 1 to n
3   min ← ∞
4   for i ← 1 to k
5       if d[i] ≤ p then
6           if 1 + C[p − d[i]] < min then
7               min ← 1 + C[p − d[i]]
8               coin ← i
9   C[p] ← min // record the minimum number of coins used to sum to the p value
10  S[p] ← coin // store which coin was last used to sum the p value
11 return C and S

Make-Change(S, d, n)
1 while n > 0
2   Print S[n]
3   n ← n − d[S[n]]
```
From [ccs.neu.edu][2].

## Dynamic Programming - Memoization (Recursion w- Caching)
Start from the complex case and reduce the problem into it's optimal substructure and only solve parts of the substructure you need to solve - SAVE those substructure solutions as they become computed.
```python
def coinsChange(V,v):
    memo = {}
    def Change(V):
            if V in memo:
                    return memo[V]
            if V == 0:
                    return 0
            if V < 0:
                    return float("inf")
            memo[V] = min([1+Change(V-vi) for vi in v])
            return memo[V]
    return Change(V)
```
From [brilliant.org][1].

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(nk) | __O(nk)__ | _O(nk + n)_
> have to iterate through cents 1 to n<br>
> have to iterate through each denomination 1 to k<br>
> have to print out at most n coins which make up solution

Space,

best case | worst case | practical case
:---: | :---: | :---:
__O(n)__ | __O(n)__ | __O(n + n)__
> Store at worst n coins which make the solution<br>
> Store the minimum number of coins per total (from 0 to p cents)

[1]: https://brilliant.org/wiki/problem-solving-dynamic-programming/
[2]: http://www.ccs.neu.edu/home/jaa/CS7800.12F/Information/Handouts/dyn_prog.pdf