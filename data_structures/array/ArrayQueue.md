# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Array Queue
- implement first-in-first-out FIFO queue
- supports `remove()`, `add(x)`
- `ArrayStack` is not good for this because either removing and adding from opposite ends, means that one of the add/remove calls has to op. on the head of the stack and incur `O(n)` due to copying.

## Persistence
The queue has these attributes,
```java
    T[] a; // array
    int j; // beginning of q
    int n; // number of elements
```

The Queue can be thought of as an infinite array; modulus provides this.
To support infinite indexes (although limited size) you can,
```java
    a[(j + i) % n] = SOME_VAL; // where j is the start index, and i is the offset 
```

## Add to Queue
1. Check if have capacity -> expand if needed
2. Assign value to next index - the `Queue Tail`, equal to (startIdx + offsetToNewIdx) mod arraySize
3. Increment element count
```java
    boolean add(T x) {
        if (n + 1 > a.length) resize();
        a[(j+n) % a.length] = x;
        n++;
        return true;
    }
```

## Remove from Queue
1. Check array is non-empty
2. Save value from `Queue Head`
3. Decrement element count
4. Increase the start index of the queue
5. Check if below capacity -> shrink if needed
6. Return value.
```java
    T remove() { 
        if (n == 0) throw new NoSuchElementException();
        T x = a[j];
        j = (j + 1) % a.length;
        n--;
        if (a.length >= 3*n) resize();
        return x;
    }
```

## Resize Queue
When the `Queue` must be shrunk or grown, do so by doubling of the active elements.
Since the data is persisted in an artificial infinite fashion, when copying the data from the old array size to the new, do so in an artificially infinite way - but reset the indices in the new array to begin at zero.
> Copying `[(j+0)%n, (j+1)%n, (j+2)%n, ..., (j+n-1)%n]` to `[0, 1, 2, ..., n-1]`

```java
    void resize() {
        T[] b = newArray(Math.max(1,n*2));
        for (int k = 0; k < n; k++) 
            b[k] = a[(j+k) % a.length];
        a = b;
        j = 0;
    }
```

## Time and Space Complexity

Time,
> `add(x)`, `remove()` operate in `O(1)` <br>
> sequence of __m__ `add(x)`, `remove()` calls is `O(m)` amortized

Space,
> Enough space for the elements and extra capacity is `O(n)`.
> > Technically it can be [0,300)% bigger than the number of elements

[1]: http://www.opendatastructures.org