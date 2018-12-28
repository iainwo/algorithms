# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Rootish Array Stack
Array-based Stacks can often become very empty.
Where the number of elements versus unpopulated memory address becomes a very low ratio.
For example in the `ArrayStack` the backing array can be up 2/3rds empty to 1/2 empty - at opposite ends of the spectrum.

`RootishArrayStack` is space-efficient and will consume less memory than this.

It has these properties,
```java
    List<T[]> blocks; // list of backing arrays
    int n; // element count
```

The idea is to have a list of arrays, whereby the size of those arrays form a arithmetic series.
> = [size(arr1), size(arr2), ..., size(arrN)] <br>
> = [1, 2, 3, ..., N]

Like so,

![Rootish Array Stack in action][2]

The totality of memory consumed is the sum of the block sizes, this is actually a arithmetic sequence.
The totality described is actually a partial sum(ation) of that arithmetic sequence.
A closed form can be found for that partial sum by reversing, adding and dividing the sequence.
> BlockSequence(n) = Σ(i), where i ∈ [1,n] <br>
> BlockSequence(n) = 1 + 2 + 3 ... + n <br>
> <br>
> BlockSequence(n) + BlockSequence(n) = 1 + 2 + 3 ... + n + 1 + 2 + 3 ... + n <br>
> BlockSequence(n) + BlockSequence(n) = (1 + n) + (2 + n-1) + (3 + n-2) + ... + (n-2 + 2) + (n + 1) <br>
> BlockSequence(n) + BlockSequence(n) = (1 + n) + (1 + n) + (1 + n) + ... + (1 + n) + (1 + n) <br>
> 1/2 * [BlockSequence(n) + BlockSequence(n)] = [(1 + n) + (1 + n) + (1 + n) + ... + (1 + n) + (1 + n)]/2 <br>
> BlockSequence(n) = [(1 + n) + {(n-2)/2 terms} + (1 + n)] <br>
> BlockSequence(n) = (1 + n) * n/2 <br>
> <br>
> :. BlockSequence(n) = n(1 + n)/2 <br>

To determine which number of the series (i.e block) the i-th element falls into can be derived from the inequality,
> INEQUALITY: i + 1 <= n(1 + n)/2, since there is a zero-th index

This boils down to,
> INEQUALITY: i + 1 <= n(1 + n)/2 <br>
> i + 1 <= n(1 + n)/2 <br>
> i + 1 <= [n**2 + n]/2 <br>
> 2i + 2 <= n**2 + n <br>
> 0 <= n**2 + n - 2i - 2 <br>
> 0 <= n**2 + n - 2(i + 1) <br>

This inequality can be resolved by the quadratic equation,
> {roots} ∈ [-b +/- sqrt(b**2 - 4ac)]/2 <br>
> {roots} ∈ [-1 +/- sqrt(1 - 4*(-2i - 2))]/2 <br>
> {roots} ∈ [-1 +/- sqrt(9 + 8i)]/2 <br>

Since we are trying to solve for block counts greater than zero, the root of import is,
> [-1 + sqrt(9 + 8i)]/2

Need to take the ceiling because the quadratic formula solves for when blocksizes total (i + 1).
In real life this is not the case, (i+1) is actually some index in a block rather than end of said block - typically.
__Also__ - since blocks are stored in a List, we need to convert this number to a zero-based val by subtracting (one).
The formula we should use is thus,
> CEIL([-1 + sqrt(9 + 8i)]/2) - 1

For example,
> __BlockLocation(i), when i=0,__ <br>
> BlockLocation(i) = CEIL(1/2 * [-1 + sqrt(9)]) - 1 <br>
> BlockLocation(i) = CEIL(1/2 * 2) - 1<br>
> BlockLocation(i) = CEIL(1) - 1<br>
> BlockLocation(i) = 0<br>
> <br>
> __BlockLocation(i), when i=1,__ <br>
> BlockLocation(i) = CEIL(1/2 * [-1 + sqrt(9+8)]) - 1 <br>
> BlockLocation(i) = CEIL(1/2 * 3.12310562562) - 1<br>
> BlockLocation(i) = CEIL(1.56155281281) - 1<br>
> BlockLocation(i) = 1<br>
> <br>
> __BlockLocation(i), when i=2,__ <br>
> BlockLocation(i) = CEIL(1/2 * [-1 + sqrt(9+16)]) - 1 <br>
> BlockLocation(i) = CEIL(2) - 1<br>
> BlockLocation(i) = 1<br>
> <br>
> __BlockLocation(i), when i=3,__ <br>
> BlockLocation(i) = CEIL(1/2 * [-1 + sqrt(9+24)]) - 1 <br>
> BlockLocation(i) = CEIL(2.37228132327) - 1<br>
> BlockLocation(i) = CEIL(3) - 1<br>
> BlockLocation(i) = 2<br>
> <br>

The block location is yielded by,
```java
     int i2b(int i) {
        double db = (1.0 + Math.sqrt(9 + 8*i)) / 2.0;
        int b = (int)Math.ceil(db) - 1; // add 1, since zero based array
        return b; 
    }
```

## Get and Set in Rootish Array Stack
```java
    T get(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        int b = i2b(i);
        int j = i - b*(b+1)/2;
        return blocks.get(b)[j];
    }
    T set(int i, T x) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        int b = i2b(i);
        int j = i - b*(b+1)/2;
        T y = blocks.get(b)[j];
        blocks.get(b)[j] = x;
        return y;
    }
```

## Add to Rootish Array Stack
1. Check index bounds
2. Check capacity -> grow if needed
3. Move elements over
4. Set element
5. Increase element count
```java
    void add(int i, T x) {
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        int r = blocks.size();
        if (r*(r+1)/2 < n + 1) grow();
        n++;
        for (int j = n-1; j > i; j--)
            set(j, get(j-1));
        set(i, x);
    }
```

## Grow Rootish Array Stack
Simple because additional memory means new block.
```java
    void grow() {
        blocks.add(newArray(blocks.size()+1));
    }
```

## Remove from Rootish Array Stack
1. Check index bounds
2. Shift elements over
3. Decrement element counter
4. Check below capacity -> shrink if needed
5. return val
```java
    T remove(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        T x = get(i);
        for (int j = i; j < n-1; j++)
            set(j, get(j+1));
        n--;
        int r = blocks.size();
        if ((r-2)*(r-1)/2 >= n)    shrink();
        return x;
    }
```

## Shrink Rootish Array Stack
Simple means sqrt(n) space is free; sqrt(n) is equal to the last blocksize, because y(y+1)/2 = total capacity, and the y-th block has |y| elements, meaning root of total size because size is O(y**2).
```java
    void shrink() {
        int r = blocks.size();
        while (r > 0 && (r-2)*(r-1)/2 >= n) {
            blocks.remove(blocks.size()-1);
            r--;
        }
    }
```

## Cost Analysis of Grow/Shrink
These actions mean de/allocating memory the size of sqrt(n).

In some system this is O(1); however worst case is O(sqrt(n)).
After a grow/shrink, there are at (sqrt(n)-1) removals or (sqrt(n)-1) additions which will then trigger a shrink/grow.
Therefor the O(sqrt(n)) grow/shrink cost is amortized over the proportionate number of of adds/removes.

## Time and Space Analysis

Time,

function | best case | worst case
--- | :---: | :---:
`get(i)` | O(1) | O(1)
`set(i)` | O(1) | O(1)
`add(i,x)` | O(1) | O(n)
`remove(i)` | O(1) | O(n)

- where `push(x)`, `pop()` are __O(1)__
- any sequence of __m__ calls to `add(i, x)`, `remove(i)` cause __O(m)__ amortized cost ~= __O(1)__ per call

Space,

Rootish array stack stores __n__ elements with worst case: __n + O(sqrt(n))__

## Considerations of Sqrt and word-RAM Model
TODO! Reference section _2.6.4_ http://opendatastructures.org/ods-java/2_6_RootishArrayStack_Space.html 

[1]: http://www.opendatastructures.org
[2]: http://opendatastructures.org/ods-java/img893.png