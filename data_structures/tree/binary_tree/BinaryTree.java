package com.iain.practice;

import java.util.Objects;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Binary Tree -
 * Implement the structure of the Binary Tree without ops for add/remove or get/set.
 * Create creational methods
 * Based on Pat Morin's opendatastructures.org (ODS).
 */
public class BinaryTree < Node extends BinaryTree.BTNode < Node >> {
    /**
     * Main meth. for local development and expanding requirements.
     * @params args String 1-dim array parsed by main
     */
    public static void main(String[] args) {
        BinaryTree.BTNode template = new BinaryTree.BTNode();
        BinaryTree b2Tree = new BinaryTree(template);
        System.out.println("size: " + b2Tree.iSize());
        
        BinaryTree.BTNode r = b2Tree.createNode();
        b2Tree.r = r;
        r.left = b2Tree.createNode();
        r.left.parent = r;
        r.left.left = b2Tree.createNode();
        r.left.left.parent = r.left;
        System.out.println("size: " + b2Tree.iSize());
        
        BinaryTree.BTNode inorderR = b2Tree.inorderRoot();
        System.out.println("inorderfirst: " + inorderR);
        System.out.println("inorderNext: " + b2Tree.nextInorder(inorderR));
        b2Tree.bfs();
        
        System.out.println("rHeight: " + b2Tree.rHeight());
    }

    static class BTNode < Node extends BTNode < Node >> {
        Node parent,
        left,
        right;
    }

    Node r; // root node of Tree
    Node templateNode; // node used to create new nodes
    Node nil; // node used to terminate leaves

    /**
     * DO NOT USE.
     * Privatized Constructor. The binary tree class requires a sample node and nil
     * node in order to function. Consult `BinaryTree(Node, Node)` for more info.
     */
    private BinaryTree() {}

    /**
     * Constructor
     * Requires a sample Node to use for future node instantiation when add/remove from
     * the Tree.
     * @param template the node which will be used for instantiation-by-reflection
     */
    public BinaryTree(Node template) {
        this.templateNode = template;
    }

    /**
     * Constructor
     * Requires a sample and nil Node so that the Tree can construct more Nodes in the
     * future.
     * @param sample A sample Node to use in `createNode` factory method
     * @param nil A a node which will be used to terminate leaf nodes
     */
    public BinaryTree(Node sample, Node nil) {
        this.templateNode = sample;
        this.nil = nil;
    }

    /**
     * rHeight - recursively calculate the height of a tree
     * @return the height
     */
    public int rHeight() {
        return rHeight(r);
    }
    
    /**
     * rHeight - recursively calculate of the subtree rooted at the given
     * parameter. Root has height of 0.
     * @return the height
     */
    public int rHeight(Node w) {
        if (nil == w) return -1;
        return 1 + Math.max(rHeight(w.left), rHeight(w.right));
    }
    
    /**
     * createNode -
     * Instantiate a new Node using reflection, the constructor provisioned sample class
     * and the constructor provisioned nil Object.
     * @return a new node
     */
    public Node createNode() {
        Node u = null;
        try {
            u = (Node)templateNode.getClass().newInstance();
            u.parent = u.left = u.right = nil;
        } catch (Exception e) {
            /* DO NOTHING */
        }
        return u;
    }

    /**
     * rSize -
     * @return the size of the Binary Tree
     */
    public int rSize() {
        if (Objects.equals(nil, r)) return 0;
        return 1 + rSize(r.left) + rSize(r.right);
    }

    /**
     * rSize - calculate the size of the subtree rooted at the given node
     * @param w the root node of the subtree
     * @return the size of the subtree
     */
    public int rSize(Node w) {
        if (Objects.equals(nil, w)) return 0;
        return 1 + rSize(w.left) + rSize(w.right);
    }
    
    /**
     * iSize - calculate the size of the tree iteratively
     * @return the size of the tree
     */
    public int iSize() {
        if (nil == r) return 0;

        int size = 0;
        Node prev, curr, next;
        curr = r;
        prev = next = r;
        while (next != r.parent) {
            if (nil != curr.left &&
                prev != curr.left &&
                prev != curr.right) {
                next = curr.left;
            } else if (nil != curr.right &&
                prev != curr.right) {
                next = curr.right;
            } else {
                size++;
                next = curr.parent;
            }
            prev = curr;
            curr = next;
        }
        return size;
    }

    /**
     * inorderRoot - get the root node of an inorder traversal
     * @return the first node of an inorder traversal
     */
    public Node inorderRoot() {
        Node w = r;
        for (; nil != w.left; w = w.left);
        return w;
    }

    /**
     * nextInorder - get the next node of an inorder traversal
     * @param w the current node of the inorder traversal
     * @return the next node of the inorder traversal
     */
    public Node nextInorder(Node w) {
        if (nil != w.right) { // go right by default
            w = w.right;
            for (; nil != w.left; w = w.left);
        } else { // otherwise escalate to the root node of this left subtree
            for (; nil != w.parent && w.parent.left != w; w = w.parent);
            w = w.parent;
        }
        return w;
    }

    /**
     * bfs - sample
     */
    public void bfs() {
        System.out.println("bfs --");
        Queue < Node > q = new LinkedList < > ();
        if (!isEmpty()) q.offer(r);
        while (!q.isEmpty()) {
            Node u = q.poll();
            System.out.println("u: -> " + u);
            if (null != u && nil != u.left) q.offer(u.left);
            if (null != u && nil != u.right) q.offer(u.right);
        }
    }

    /**
     * Clear - delete the underlying collection and reset the tree.
     */
    public void clear() {
        r = nil;
    }

    /**
     * isEmpty - determine if the tree has any content
     * @return true if the tree has a collection
     */
    public boolean isEmpty() {
        return (nil == r);
    }
}