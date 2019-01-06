# Counting Sort
> _Notes based on [opendatastructures.org][1]._ <br>

The mechanic which holds comparison-based sorting back is the number of operations which can be conducted in constant time.
If you have a way of performing multiple operations in constant time, the typical $`log_{x}(n!)`$ can be beaten.

For example,
```java
a[b[i]] = 0xC001; //operates in constant O(1) time
```
> Since `a` is an array and `b[i]` is a dynamic value, multiple operations to any of a's indices can happen. This mechanic also happens in O(1) time. Thus this can be leveraged to sort faster.

`Counting Sort` can be used to sort __n__ elements in a range of $`[0,k-1]`$.

```java
    int[] countingSort(int[] a, int k) {
        int c[] = new int[k];
        for (int i = 0; i < a.length; i++)
            c[a[i]]++;
        for (int i = 1; i < k; i++)
            c[i] += c[i-1];
        int b[] = new int[a.length];
        for (int i = a.length-1; i >= 0; i--)
            b[--c[a[i]]] = a[i];
        return b;
    }
```
> From [opendatastructures.org][1] <br>

## Time and Space Complexity
Time,

best case | worst case | practical worst case
:---: | :---: | :---:
O(n + k) | __O(n + k)__ | _[n + k]_

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
- better if there is ratio in favour of more elements than range possibilities
    - this is because most of the time will be spent calculating running sums for the `c` counting array

[1]: http://www.opendatastructures.org