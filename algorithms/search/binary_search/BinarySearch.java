package com.iain.practice;

import java.util.Comparator;

/**
 * DefaultComparator - class can be used to compare objects implementing the Comparable
 * interface. Supports primitive and boxed data-types.
 */
class DefaultComparator < T > implements Comparator < T > {

    /**
     * compare - determine the relative difference between to objects
     * @param a the first object of the pair to compare
     * @param b the second object of the pair to compare
     * @return -1 if first is smaller, 1 if second is smaller, 0 if equal.
     */
    public int compare(T a, T b) {
        if (null == a) return -1;
        if (null == b) return 1;
        return ((Comparable < T > ) a).compareTo(b);
    }
}

/**
 * BinarySearch - object to return the results of a binary search
 * Assumes parameterized arrays to be searched are pre-sorted.
 */
public class BinarySearch {

    /**
     * Main - for development and expanding requirements
     * @params args String usable and parasable params by main
     */
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {
            0,
            1,
            2,
            3,
            4,
            5,
            6
        }; 
        int r = BinarySearch.rFind(arr, 2);
        System.out.println("found 2 @ idx: "+r);
        
        r = BinarySearch.rFind(arr, 22);
        System.out.println("found 22 @ idx: "+r);
        
        r = BinarySearch.rFind(arr, 5);
        System.out.println("found 5 @ idx: "+r);
        
        r = BinarySearch.rFind(arr, -10);
        System.out.println("found -10 @ idx: "+r);

        int i = BinarySearch.iFind(arr, 2);
        System.out.println("found 2 @ idx: "+i);
        
        i = BinarySearch.iFind(arr, 22);
        System.out.println("found 22 @ idx: "+i);
        
        i = BinarySearch.iFind(arr, 5);
        System.out.println("found 5 @ idx: "+ i);
        
        i = BinarySearch.iFind(arr, -10);
        System.out.println("found -10 @ idx: "+i);
    }

    /**
     * iFind - iteratively find and return the index where the search val occurs.
     * @param a the array to search
     * @param x the search value to find
     * @return index if found; else return -1.
     */
    public static < T extends Comparable < T >> int iFind(T[] a, T x) {
        return iFind(a, x, new DefaultComparator < T > ());
    }

    /**
     * iFind - iteratively find and return the index where the search val occurs.
     * @param a the array to search
     * @param x the search value to find
     * @param c the comparator to use
     * @return index if found; else return -1.
     */
    public static < T > int iFind(T[] a, T x, Comparator < T > c) {
        return iSearch(a, x, c, 0, a.length - 1); // TODO impl method
    }

    /**
     * iSearch - recursive binary search
     * @param a the array to search
     * @param x the search value to find
     * @param c the comparator to use
     * @param l the start index
     * @param r the end index
     * @return index if found; else return -1.
     */
    public static < T > int iSearch(T[] a, T x, Comparator < T > c, int l, int r) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            int cmp = c.compare(x, a[m]);
            if (0 == cmp) return m;
            else if (0 > cmp) r = m - 1;
            else l = m + 1;
        }
        return -1;
    }

    /**
     * rFind - recursively find and return the index where the search val occurs.
     * @param a the array to search
     * @param x the search value to find
     * @return index if found; else return -1.
     */
    public static < T extends Comparable < T >> int rFind(T[] a, T x) {
        return rFind(a, x, new DefaultComparator < T > ());
    }

    /**
     * rFind - recursively find and return the index where the search val occurs.
     * @param a the array to search
     * @param x the search value to find
     * @param c the comparator to use
     * @return index if found; else return -1.
     */
    public static < T > int rFind(T[] a, T x, Comparator < T > c) {
        return rSearch(a, x, c, 0, a.length - 1);
    }

    /**
     * rSearch - recursive binary search
     * @param a the array to search
     * @param x the search value to find
     * @param c the comparator to use
     * @param l the start index
     * @param r the end index
     * @return index if found; else return -1.
     */
    public static < T > int rSearch(T[] a, T x, Comparator < T > c, int l, int r) {
        if (l > r) return -1;
        int mid = l + (r - l) / 2;
        int cmp = c.compare(x, a[mid]);
        if (0 == cmp) return mid;
        else if (0 < cmp) return rSearch(a, x, c, mid + 1, r);
        return rSearch(a, x, c, l, mid - 1);
    }
}