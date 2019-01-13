package com.iain.practice;

import java.util.Comparator;
import java.util.Iterator;

/* NOTE: This implementation is incomplete and only a sketch mostly add/remove ops are supported in the BST
 * but their deps in BT.class are not implemented */

/**
* Default Comparator for primitive and boxed type BSTs
*/
class DefaultComparator < T > implements Comparator < T > {
    public int compare(T a, T b) {
        return ((Comparable < T > ) a).compareTo(b);
    }
}

/**
 * BT - implementation of a Binary Tree
 */
class BT < Node extends BT.BTNode < Node >> {

    Node r; // root node	
    Node nil; // nil node

    /**
     * BTNode - nodes which make up a Binary Tree
     */
    public static class BTNode < Node extends BTNode < Node >> {
        Node p,
        l,
        r; // parent, left, right Nodes
    }

    /**
     * newNode - instantiate a new empty node
     * @return a new node
     */
    public Node newNode() {
        return null;
    }
}

/**
 * BST - implementation of a Binary Search Tree
 */
public class BST < Node extends BST.BSTNode < T > , T > extends BT < BST.BSTNode < T >> {
    /**
     * Main meth. for development and evolving requirements.
     * @params args String parseable and usable by main
     */
    public static void main(String[] args) {
        BST < BST.BSTNode < String > , String > tree = new BST < > ();
    }

    protected int n; // number of elements
    protected Comparator < T > c;
    /**
     * Constructor
     * Requires a sample node for factory method instantiation of new nodes
     */
    public BST(Node sampleNode) {
        this.sample = sampleNode;
        this.c = new DefaultComparator < T > ();
    }

    /**
     * BSTNode - a node contain attribs for the BST Tree.
     */
    public static class BSTNode < T > extends BT.BTNode < BSTNode < T >> {
        T x;
    }

    /**
     * remove - an element from the BST
     * @param x the element to remove
     * @return true if successful
     */
    public boolean remove(T x) {
        Node w = findLast(x);
        if (nil != w && 0 == c.compare(w.x, x)) {
            remove(w);
            return true;
        }
        return false;
    }

    /**
     * remove - the given node from the BST
     * @param u the node to remove
     * @return true if successful
     */
    protected boolean remove(Node u) {
        if (nil == u.left || nil == u.right) {
            splice(u);
        } else {
            Node w = u.right;
            for (; nil != w.left; w = w.left);
            u.x = w.x;
            splice(w);
        }
        return true;
    }

    /**
     * splice - remove a node from the BST
     * Assumes the node has one or less children
     * @param u the node to splice
     */
    protected void splice(Node u) {

        Node child;
        Node parent = u.parent;
        if (nil != u.left) child = u.left;
        if (nil != u.right) child = u.right;
        if (u == r) {
            parent = nil;
            r = child;
        } else {
            if (parent.left = u) parent.left = child;
            else parent.right = child;
        }
        if (nil != child) child.parent = parent;
        n--;
    }

    /**
     * Add - the element to the underlying collection.
     * @param x the element to add
     * @return true if the operation succeeds
     */
    public boolean add(T x) {
        Node p = findLast(x);
        return addChild(p, newNode(x));
    }

    /**
     * addChild - add node `u` as child of `p`
     * @param p
     * @param u
     * @return true if child was added
     */
    protected boolean addChild(Node p, Node u) {
        int cmp = c.compare(p.x, u.x);
        if (0 == cmp) return false;
        if (0 > cmp) {
            p.left = u;
            u.parent = p;
        } else {
            p.right = u;
            u.parent = p;
        }
        return true;
    }

    /**
     * findLast - get a matching element in the BST or
     * the element that was last on the search path
     * @param x the element to find
     * @return the resolved element
     */
    protected Node findLast(T x) {
        Node w = r, prev = nil;
        while (nil != w) {
            prev = w;
            int cmp = c.compare(w.x, x);
            if (0 > cmp) w = w.r;
            else if (0 < cmp) w = w.l;
            else return w;
        }
        return w;
    }

    /**
     * newNode - instantiate a new node
     * @param x an element to associate with the new Node
     * @return another node
     */
    protected Node newNode(T x) {
        Node w = super.newNode();
        w.x = x;
        return w;
    }


    /**
     * size
     * @return the number of elements in the collection
     */
    public int size() {
        return n;
    }

    /**
     * clear -
     * drop the collection and reset the BST metadata.
     */
    public void clear() {
        super.clear();
        n = 0;
    }
}