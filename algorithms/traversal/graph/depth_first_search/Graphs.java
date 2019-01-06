package com.iain.practice;

import java.util.Objects;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
* Graph Interface -
* Details the minimum operations which must be provided by an implementation.
*/
interface Graph {
	public int nVertices(); // get number of vertices in Graph
	public List<Integer> outEdges(int i); // get vertices of edges with source vertex `i`
}

/**
* Graph algorithm class to hold abstracted algorithms and reduce re-implimented code.
*/
public class Graphs {
    public static void main(String[] args) {
        Graph g = new Graph() {
                    public int nVertices() { return 2; }
                    public List<Integer> outEdges(int i) {
                        List<Integer> x = new ArrayList<>();
                        x.add(1);
                        return x;
                    }
            };
            
        System.out.println("rDfs -- ");
        Graphs.rDfs(g, 0);
    }

	private static byte GRAY = 1, WHITE = 0, BLACK = 2;
	/**
	* HELPER METHOD.
	* Recursively DFS traverse the given Graph. Client API call is `rDfs(Graph, int)`.
	* @param g the Graph
	* @param i the root vertex
	* @param seen the visit states of Graph vertices
	*/
	private static void rDfsHelper(Graph g, int i, byte[] seen) {
		seen[i] = GRAY;
		for (int j : g.outEdges(i)) 
		    if (WHITE == seen[j]) {
            	System.out.println(i + " → " + j);
		        rDfsHelper(g, j, seen);
		    }
		seen[i] = BLACK;
    }

	/**
	* Recursive DFS traversal of the given Graph starting at given vertex.
	* @param g the Graph
	* @param r the root vertex to start at
	*/
	public static void rDfs(Graph g, int r) {
		validate(g, r);
		byte[] seen = new byte[g.nVertices()];
		rDfsHelper(g, r, seen);
	}

	/**
	* Validate that a Graph and its root are accessible.
	* Throws exceptions if invalid.
	* @param g the Graph
	* @param r the root vertex
	*/
	private static void validate(Graph g, int r) {
		Objects.requireNonNull(g, "Graph cannot be NULL.");
		int n = g.nVertices();
		if (0 > r || r > n-1) throw new IndexOutOfBoundsException();
    }
}
