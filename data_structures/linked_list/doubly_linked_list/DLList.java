package com.iain.practice;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;

/**
 * DLList -
 * Doubly Linked List implementation of the AbstractSequentialList Data Type.
 * Makes use of a dummy node to simplify add/remove ops.
 */
public class DLList < T > extends AbstractSequentialList < T > {
    /**
     * Main -
     * For development and expanding requirements.
     * @param args list of string arguments to the main process
     */
    public static void main(String[] args) {
        List < String > list = new DLList < > ();
        list.size();
        list.add("abc");
        ListIterator < String > it = list.listIterator();
        while (it.hasNext()) it.next();
        System.out.println(it.previous());
        it.remove();
    }

    class Node {
        T x;
        Node prev, next;
    }

    private int n; // number of elements
    private Node dummy; // dummy node

    public DLList() {
        this.dummy = new Node();
        dummy.next = dummy;
        dummy.prev = dummy;
    }

    /**
     * set - the index with the given element
     * @param i the index to set
     * @param x the value to set
     * @return the element at the index before being set
     */
    public T set(int i, T x) {
        if (0 > i || n <= i) throw new IndexOutOfBoundsException();
        Node w = getNode(i);
        T y = w.x;
        w.x = x;
        return y;
    }

    /**
     * listIterator - return a ListIterator for this DLList.
     * This will support the abstracted AbstractSequentialList operations
     * which make use of this iterator to efficiently get(i)/set(i)/remove(x)/add(i, x) ops.
     * @param i the index for the iterator to start at
     * @return a ListIterator for this LinkedList
     */
    public ListIterator < T > listIterator(int i) {
        if (0 > i || n <= i) throw new IndexOutOfBoundsException();
        return new DLLIterator(this, i);
    }

    /**
     * ListIterator - class for efficiently iterating over a DLList.
     */
    class DLLIterator implements ListIterator < T > {

        Node c,
        p; // the current (c) and parent (p) node in the list
        int i; // the next index in the iteration
        DLList < T > list; // underlying list being iterated








        /**
         * Constructor
         * Allows for Iterator to begin at given index of a DLList.
         * @param i the index to start at
         */
        public DLLIterator(DLList list, int i) {
            this.list = list;
            this.c = list.getNode(i);
            this.i = i;
        }

        /**
         * next - retrieve the next element in the iteration.
         * @return the element next in the iteration
         */
        public T next() {
            T y = c.x;
            p = c;
            c = c.next;
            return y;
        }
        /**
         * add -
         * @param x the element to add
         */
        public void add(T x) {
            DLList.this.addBefore(c, x);
        }

        /**
         * nextIndex - return the next index
         * @return the next index
         */
        public int nextIndex() {
            return i;
        }

        /**
         * previousIndex -
         * @return the previous index of this iteration
         */
        public int previousIndex() {
            return nextIndex() - 1;
        }

        /**
         * remove - remove the previously iterated element
         */
        public void remove() {
            if (p == c) c = c.next;
            DLList.this.remove(p);
        }

        /**
         * previous -
         * @return the previous element in the iteration.
         */
        public T previous() {
            c = c.prev;
            p = c;
            return c.x;
        }
        /**
         * set - the last iterated element to the provided element
         * @param x the element to place in the DLList
         */
        public void set(T x) {
            p.x = x;
        }

        /**
         * get - the last iterated element
         * @return the last iterated element
         */
        public T get() {
            return p.x;
        }

        /**
         * hasPrevious - determine if there is a previous element to iterate
         * @return true if exists
         */
        public boolean hasPrevious() {
            return (c.prev != dummy);
        }

        /**
         * hasNext - determine if there is another element to iterate
         * @return true if exists
         */
        public boolean hasNext() {
            return (dummy != c);
        }

    }

    /**
     * get - the element at the given index
     * @param i the index to retrieve
     * @return the element at the index
     */
    public T get(int i) {
        if (0 > i || n <= i) throw new IndexOutOfBoundsException();
        return getNode(i).x;
    }
    /**
     * add - the given element at the given index
     * @param i the index to add at
     * @param x the element to add
     * @return true if the operation was a success
     */
    public void add(int i, T x) {
        if (0 > i || n <= i) throw new IndexOutOfBoundsException();
        addBefore(getNode(i), x);
    }

    /**
     * remove - remove the index from the list
     * @param i the index to obliterate
     * @return y the removed element
     */
    public T remove(int i) {
        if (0 > i || n <= i) throw new IndexOutOfBoundsException();
        Node w = getNode(i);
        splice(w);
        return w.x;
    }

    /**
     * getNode - get the node at the given index of the DLList
     * @param i the index of the list
     * @return the element value of that index
     */
    private Node getNode(int i) {
        if (0 > i || n <= i) throw new IndexOutOfBoundsException();

        Node w = dummy;
        if (n / 2 < i)
            for (int j = n; j >= i; j--, w = w.prev);
        else
            for (int j = 0; j <= i; j++, w = w.next);

        return w;
    }

    /**
     * splice - remove the node from the DLList and repair the LList
     * @param w the node to remove
     * @return the Node removed from the list
     */
    public Node splice(Node w) {
        w.prev.next = w.next;
        w.next.prev = w.prev;
        w.next = null;
        w.prev = null;
        n--;
        return w;
    }

    /**
     * addBefore - add element before the given Node
     * @param w the node which to insert the value before
     * @param x the element to insert
     * @return true if succesfully
     */
    protected boolean addBefore(Node w, T x) {
        if (null == w ||
            null == w.prev ||
            null == w.next) throw new IllegalArgumentException();
        Node u = new Node();
        u.x = x;
        u.next = w;
        u.prev = w.prev;
        w.prev.next = u;
        w.prev = u;
        n++;
        return true;
    }

    /**
     * add -
     * @param x add the element to the end of the list
     * @return true if the op is a success
     */
    public boolean add(T x) {
        return addBefore(dummy, x);
    }

    /**
     * size -
     * @return the number of elements int the list
     */
    public int size() {
        return n;
    }
}