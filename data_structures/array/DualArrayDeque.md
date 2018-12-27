# Study Notes
> _Notes based on [opendatastructures.org][1]._

## Dual Array Deque
A `Deque`.

Performs `get(i)`, `set(i,x)`, `add(i,x)`, `remove(i)` oeprations.

## Persistence
Uses two stacks to act as efficient LIFO FIFO Queue,
```java
    List<T> front;
    List<T> back;
```

## Getting and Setting Dual Array Deque
```java
    T get(int i) {
        if (i < front.size()) {
            return front.get(front.size()-i-1);
        } else {
            return back.get(i-front.size());
        }
    }
    T set(int i, T x) {
        if (i < front.size()) {
            return front.set(front.size()-i-1, x);
            
        } else {
            return back.set(i-front.size(), x);
        }
    }
```

## Adding to Dual Array Deque
1. Evaluate bounds
2. Determine which half to place
3. Place
4. Rebalance
```java
    void add(int i, T x) {
        if (i < front.size()) { 
            front.add(front.size()-i, x);
        } else {
            back.add(i-front.size(), x);
        }
        balance();
    }
```

## Remove from Dual Array Deque
1. Evaluate bounds
2. Determine half
3. Remove
4. Rebalance
5. Return Value
```java
    T remove(int i) {
        T x;
        if (i < front.size()) {
            x = front.remove(front.size()-i-1);
        } else {
            x = back.remove(i-front.size());
        }
        balance();
        return x;
    }
```

## Balance Dual Array Deque
Ensure that neither half has less than 1/4 of the elements.

```java
    void balance() {
        int n = size();
        if (3*front.size() < back.size()) {
            int s = n/2 - front.size();
            List<T> l1 = newStack();
            List<T> l2 = newStack();
            l1.addAll(back.subList(0,s));
            Collections.reverse(l1);
            l1.addAll(front);
            l2.addAll(back.subList(s, back.size()));
            front = l1;
            back = l2;
        } else if (3*back.size() < front.size()) {
            int s = front.size() - n/2;
            List<T> l1 = newStack();
            List<T> l2 = newStack();
            l1.addAll(front.subList(s, front.size()));
            l2.addAll(front.subList(0, s));
            Collections.reverse(l2);
            l2.addAll(back);
            front = l1;
            back = l2;
        }
    }
```
## Cost Analysis of Balancing
> The potential difference in balance is,
> phi(x) = | front.size() - back.size() | <br>
> <br>
> After balancing the potential difference's inequality is, <br>
> phi(x) = | front.size() - back.size() | <br>
> phi(x) = | ceil(n/2) - floor(n/2) | <br>
> phi(x) <= 1 <br>
> <br>
> On rebalance, one half is < 1/4 of the elements, where `3*secondHalf > oneHalf` <br>
> phi(x) = | back.size() - front.size() | <br>
> phi(x) > | back.size() - back.size()/3 | <br>
> phi(x) > | 2*back.size()/3 | <br>
> phi(x) > | 2*(3n/4)/3 | <br>
> phi(x) > | n/2 | <br>
> <br>
> Thus the difference in potential from the current rebalance compared to the last, <br>
> = n/2 - 1, where there may be even zero difference in the previous state <br>
> <br>
> This is means that number of elements to change have been half since the last recall - meaning n/2 calls to add/remove. <br>
> 1. In the case of __add(x)__ the number of elements would have doubled, meaning moving the previous n/2 elements is amortized accross the last n/2 adds <br>
> 2. In the case of __remove()__ the number of elmenets would be halved, meaning there are only n/2 of the previous elements to copy, and there were already n/2 removes <br>

## Time and Space Analysis

Time,

function | best case | worst case
--- | :---: | :---:
`get(i)` | O(1) | O(1)
`set(i)` | O(1) | O(1)
`add(i,x)` | O(1) | O(n)
`remove(i)` | O(1) | O(n)

> `get(i)`, `set(i)` are __O(1)__ <br>
> `add(i,x)`, `remove(i)` are __O(1 + min{n - i, i})__ <br>
> - `m` calls to add/remove will results in __O(m)__ amortized cost of `balance()` operation.

Space,
- Space is `O(n)`

[1]: http://www.opendatastructures.org