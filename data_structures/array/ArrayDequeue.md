# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Array Deque
Supports `get(i)`, `set(i,x)`, `add(i,x)`, `remove(i)`.
The __ArrayDeque__ like the __ArrayStack__ allows for efficient addition to the _Queue Tail_ and efficient removal from the _Queue Head_; however Deques also allow efficient addition to the _Queue Head_ and efficient removal from the _Queue Tail_!
That is, quick access to both ends.

## Get & Set in Array Dequeue
1. Check bounds
2. Get/Set Value
```java
    T get(int i) {
        if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
        return a[(j+i)%a.length];
    }
    T set(int i, T x) {
        if (i < 0 || i > n-1) throw new IndexOutOfBoundsException();
        T y = a[(j+i)%a.length];
        a[(j+i)%a.length] = x;
        return y;
    }
```

## Add to Deque
1. Check bounds
2. Check capacity -> expand if need.
3. Determine if index is closer to beginning or end of Deque
2. Insert val into index and shift the side which have fewer elements - respecting array boundaries
```java
    void add(int i, T x) {
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        if (n+1 > a.length) resize();
        if (i < n/2) { // shift a[0],..,a[i-1] left one position
            j = (j == 0) ? a.length - 1 : j - 1; //(j-1)mod a.length
            for (int k = 0; k <= i-1; k++)
                a[(j+k)%a.length] = a[(j+k+1)%a.length];
        } else { // shift a[i],..,a[n-1] right one position
            for (int k = n; k > i; k--)
                a[(j+k)%a.length] = a[(j+k-1)%a.length];
        }
        a[(j+i)%a.length] = x;
        n++;
    }
```

## Remove from Deque
1. Check bounds
2. Determine if val is closer to beginning or end
3. Shift values in-ward from closer end
4. Decrement element count
5. Check if below capacity -> resize
6. Return deleted value
```java
    T remove(int i) {
        if (i < 0 || i > n - 1)    throw new IndexOutOfBoundsException();
        T x = a[(j+i)%a.length];
        if (i < n/2) {  // shift a[0],..,[i-1] right one position
            for (int k = i; k > 0; k--)
                a[(j+k)%a.length] = a[(j+k-1)%a.length];
            j = (j + 1) % a.length;
        } else { // shift a[i+1],..,a[n-1] left one position
            for (int k = i; k < n-1; k++)
                a[(j+k)%a.length] = a[(j+k+1)%a.length];
        }
        n--;
        if (3*n < a.length) resize();
        return x;
    }
```

## Time and Space Complexity Analysis

Time,
> `get(i)`, `set(i)` operate in `O(1)` <br>
> `add(i,x)`, `remove(i)` operate in `O(1 + min{i, n-i})`
> > a sequence of `m` add/remove calls will result in `O(m)` amortized cost


[1]: http://www.opendatastructures.org