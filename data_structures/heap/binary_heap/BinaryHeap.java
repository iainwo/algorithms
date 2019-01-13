package com.iain.practice;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

// NOTE: based on Pat Morin's BinaryHeap from opendatastructures.org (ODS).

/**
 * DefaultComparator - implementation for primitive and boxed/wrapped classes
 */
class DefaultComparator < T > implements Comparator < T > {
    /**
     * compare - compare to generics to one another for boxed/primitives
     * @param a the first element of the pair to compare
     * @param b the second element of the pair to compare
     * @return -1 if a is smaller, 1 if b is smaller, zero if equal
     */
    public int compare(T a, T b) {
        if (null == a) return -1;
        if (null == b) return 1;
        return ((Comparable < T > ) a).compareTo(b);
    }
}

/**
 * Binary Heap - implementation of a min - heap using a backing binary BFS-recorded array.
 */
public class BinaryHeap < T > {
    /**
     * Main - for development and expanding requirements.
     * @params args String array of parsable usable arguments
     */
    public static void main(String[] args) {
        BinaryHeap < String > h = new BinaryHeap < > (String.class);
        h.add(null);
        h.add("");
        h.add("a");
        h.add("b");
        h.add("c");
        h.add("d");
        h.add("ef");
        h.add("abc");
        System.out.println("remove: " + h.remove());
        System.out.println("remove: " + h.remove());
        System.out.println("remove: " + h.remove());
        System.out.println("remove: " + h.remove());
        System.out.println("remove: " + h.remove());
        System.out.println("remove: " + h.remove());
        System.out.println("remove: " + h.remove());
        
        h.clear();
        
        String[] arr = {"g", "f", "guardian", "googly", "def", "jam", "records"};
        System.out.println("presort: " + Arrays.toString(arr));
        BinaryHeap.heapSort(arr);
        System.out.println("sorted: " + Arrays.toString(arr));
    }

    /**
     * DO NOT USE.
     * Privatized Constructor - BinaryHeap needs to be create with a class type
     * in order to function. See `BinaryHeap(Class<T>)`.
     */
    private BinaryHeap() {throw new UnsupportedOperationException();}

    /**
     * Constructor - used to obtain resources for factory creation of new backing arrays.
     * @param c the class type of the backing heap array
     */
    public BinaryHeap(Class < T > c) {
        this.clazz = c;
        this.a = (T[]) Array.newInstance(this.clazz, 1);
        this.c = new DefaultComparator < T > ();
    }

    private Comparator < T > c; // comparator used for trickleDown/bubbleUp
    private T[] a; // backing heap array
    private int n; // number of elements
    private Class < T > clazz; // array-backing class

    /**
     * Constructor - used for `HeapSort`
     * @param a the array of elements to sort
     * @param c the comparator to used in sorting
     */
    public BinaryHeap(T[] a, Comparator < T > c) {
        this.a = a;
        this.c = c;
        this.n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            trickleDown(i);
    }

    /**
     * add - add an element to the heap
     * @param x the element to add
     * @return true if successfully added
     */
    public boolean add(T x) {
        if (a.length < n + 1) resize();
        a[n++] = x;
        bubbleUp(n - 1);
        return true;
    }

    /**
     * bubbleUp - elevate the heap node to it’s appropriate height in the binary heap
     * @param i the bfs array location of the heap node
     */
    private void bubbleUp(int i) {
        int p = parent(i);
        while (0 < i && 0 > c.compare(a[i], a[p])) {
            swap(i, p);
            i = p;
            p = parent(i);
        }
    }


    /**
     * parent - get the BFS ordered array parent index of the given index
     * @param i the child BFS index
     * @return the parent’s index
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * remove - remove the min from the heap
     * @return the minimum element in the heap
     */
    public T remove() {
        if (0 >= n) throw new NoSuchElementException();
        T y = a[0];
        a[0] = a[--n];
        trickleDown(0);
        if (a.length >= 3 * n) resize();
        return y;
    }

    /**
     * trickleDown - relegate the heap node to it’s proper rank in the tree
     * @param i the BFS index to relegate down the heap
     */
    private void trickleDown(int i) {
        int minChild;
        while ((n > i*2 + 2) && 0 < c.compare(a[i], a[(minChild = minChild(i))])) {
                swap(i, minChild);
                i = minChild;
        }
    }

    /**
     * minChild - resolve the smallest child of a BFS index in the Heap
     * @param i the parent BFS index
     * @return the smaller binary child’s BFS index
     */
    private int minChild(int i) {
        int min = right(i);
        if (0 > c.compare(a[left(i)], a[right(i)]))
            min = left(i);
        return min;
    }

    /**
     * left - calculate left child BFS index
     * Does not guard against IndexOutOfBoundsException.
     * @param p the parent BFS Index
     * @return the left child’s BFS index
     */
    private int left(int p) {
        return p * 2 + 1;
    }

    /**
     * right - calculate right child BFS index
     * Does not guard against IndexOutOfBoundsException.
     * @param p the parent BFS Index
     * @return the right child’s BFS index
     */
    private int right(int p) {
        return p * 2 + 2;
    }

    /**
     * heapSort - sort the elements of an array via a heap datastructure
     * @param a an array
     * @param c a comparator
     */
    public static < T > void heapSort(T[] a, Comparator < T > c) {
        BinaryHeap < T > h = new BinaryHeap(a, c);
        while (1 < h.size()) {
            h.swap(0, --h.n);
            h.trickleDown(0);
        }
        Collections.reverse(Arrays.asList(a));
    }

    /**
     * heapSort - sort elemetns of an array via heap ds when underlying element
     * is comparable
     * @param a an array
     */
    public static < T extends Comparable < T >> void heapSort(T[] a) {
        heapSort(a, new DefaultComparator < T > ());
    }

    /**
     * clear - drop the backing array and reset the metadata of the heap
     */
    public void clear() {
        a = (T[]) Array.newInstance(clazz, 1);
        n = 0;
    }

    /**
     * size -
     * @return the number of elements in the heap
     */
    public int size() {
        return n;
    }

    /**
     * swap - the heap indexs so that the first element becomes the second and vice versa.
     * @param i the first index
     * @param j the second index
     */
    private void swap(int i, int j) {
        T x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    /**
     * resize - the underlying array to accommodate twice the number of current elements
     */
    private void resize() {
        int newLen = Math.max(1, 2 * n);
        T[] b = (T[]) Array.newInstance(clazz, newLen); // create constructor
        System.arraycopy(a, 0, b, 0, n);
        a = b;
    }
}