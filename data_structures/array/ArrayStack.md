# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Array Stack
Implements __List__ interface. Supports `set(i,x)`, `get(i)`.
Backed by an array with more memory than necessary.

Has these attributes,
```java
    T[] a; // backing array
    int n; // track current size
```

## Set and Get the Array Stack
1. Check bounds
2. Access values
```java
    T get(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        return a[i];
    }
    T set(int i, T x) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        T y = a[i];
        a[i] = x;
        return y;
    }
```

## Adding to Array Stack
1. Check bounds
2. Check capacity -> inc. if needed
3. Shift series
4. Assign new val
5. Increase item count
```java
    void add(int i, T x) {
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        if (n + 1 > a.length) resize();
        for (int j = n; j > i; j--) 
            a[j] = a[j-1];
        a[i] = x;
        n++;
    }
```

## Remove from Array Stack
1. Check bounds
2. Copy val to delete
3. Negative shift series
4. Decrease item count
5. Check capacity -> dec. if needed
6. Return val
```java
    T remove(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        T x = a[i];
        for (int j = i; j < n-1; j++) 
            a[j] = a[j+1];
        n--;
        if (a.length >= 3*n) resize();
        return x;
    }
```

## Growing backing Array
1. Determine new length
2. Allocate mem
3. If needed copy from old struct to new
4. Assign new struct to old handle
```java
    void resize() {
        T[] b = newArray(Math.max(n*2,1));
        for (int i = 0; i < n; i++) {
            b[i] = a[i];
        }
        a = b;
    }
```

## Resize Cost
In a call to `resize()` the new array allocates twice the number of active elements.
The number of active elements can be greater or less than the last call to `resize()`.
Resize costs `O(n)` to copy elements.

There are two cases,
1. When since last resize, added elements and hit capacity (2*n == array.len)
> `Ω(n) calls to add(x)` hits capacity <br>
> :. `n(i)/2`, where n(i) is the number of call between the i-1th call to resize and ith's call to resize
2. Since last resize, removed elements and fell below capacity (3n >= array.len) 
> `Ω(array.len/2 -1)` at least in the array after the previous resize <br>
> `Ω(array.len/2 -1) - array.len/3` calls to remove(x) <br>
> <br>
> = arr.len/2-1 - arr.len/3 <br>
> = arr.len/6 - 1 <br>
> = (arr.len/3)/2 - 1 <br>
> >= `n(i)/2 - 1`, where n(i) is the number of calls between the (i-1) call to resize and ith's call to resize 

The total cost to all calls of `resize()` is,
> Σ i=0 to r (n(i)/2 - 1) <= m, where __r__ is the number of total calls to resize, __m__ is the total number of add/remove ops <br>
> 1/2 * Σ i=0 to r (n(i)) - r <= m <br>
> Σ i=0 to r (n(i)) <= 2m + 2r <br>
> Σ i=0 to r (n(i)) <= O(m + r)
> Σ i=0 to r (n(i)) <= O(m), since the number of calls to add/remove is greater than the possible number of resize calls

## Time and Space Complexity
Time,
> `get(i)`, `set(i)` is `O(1)`. <br>
> `add(i,x)`, `remove(i)` is `O(1 + n - i)` <br>
> any sequence of `add(i,x)` and `remove(i)` operations results in a total of O(m) time spent during all calls to `resize()` <br>
> > efficient way to implement a Stack. In particular, we can implement `push(x)` as `add(n,x)` and `pop()` as `remove(n-1)`, in which case these operations will run in `O(1)` amortized time

Space,
> `O(n)`

## Considerations
- Can use language features to speed up algos; java `System.arraycopy(a, 0, b, 0, n)`
```java
    void resize() {
        T[] b = newArray(Math.max(2*n,1));
        System.arraycopy(a, 0, b, 0, n);
        a = b;
    }
    void add(int i, T x) {
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        if (n + 1 > a.length) resize();
        System.arraycopy(a, i, a, i+1, n-i); 
        a[i] = x;
        n++;
    }
    T remove(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        T x = a[i];
        System.arraycopy(a, i+1, a, i, n-i-1);
        n--; 
        if (a.length >= 3*n) resize();
        return x;
    }
```

[1]: http://www.opendatastructures.org