# Knapsack (Backpack) Problem
> _Notes based on [brilliant.org][1]._ <br>

Basically a combinatorial optimization problem. Given so many combinations which is the best.

THe knapsack problems asks one to get the most value from a selection of items with a weight restriction.

To get this value, calculate the maximum possible value for all the weights less than or equal to the weight restriction using one element. Then introduce a second element, and do the same; you can asses the value of that second item by combining the second value with the maximum value so-far discovered for the current weight restriction less this second items weight. 

Fortunately the table stores maximum values in the order of ascending combinations of weights.
The table holds sums tiered by weight, by accessing the previous elements total sum for this weight tier less this items weight, will yield the max sum we have encountered so far for the subset of elements we have tried to combine.

---

- Establish the invariant - always maintain the maximum value.
- Iterate through each item.
    - Iterate through each weight category
        - If the current item can fit in this weight category, try and place it in that weight category, by evicting any previous items which do occupy the capacity of this current item. These evictions can be made because the table records the max sum of the previous elements which are under the capacity required by this current element. In this sense the table can be continued to be built and new items can confidently evict older items because there is tabulated record of the running sums which will not exceed capacity in conjunction with the current item. 

```python
# n:number of items
# v[i]:value of the i-th item, w[i]: weight of the i-th item
# W = backpack capacity

knapsack(n,v,w,W)
    for j=0 to j=W
       t[0, j]=0 
    for i=1 to n
        for j=0 to W
            if w[i]>j 
                t[i, j]=t[i - 1, j]
            else 
                t[i, j] = max(t[i - 1, j], t[i - 1, j - w[i]]+v[i])
     return t[n][W]
```

[1]: https://brilliant.org/wiki/backpack-problem/