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
        System.out.println("iBfs -- ");
        Graphs.iBfs(g, 0);
    }

	/**
	* Iterative BFS traversal of the given Graph beginning at the given root vertex.
	* @param g the Graph
	* @param r the root vertex
	*/
	public static void iBfs(Graph g, int r) {
		validate(g, r);
		boolean[] seen = new boolean[g.nVertices()];
		Queue<Integer> q = new LinkedList<>();
		q.add(r);
		while (!q.isEmpty()) {
			int i = q.remove();
			seen[i] = true;
			for (int j : g.outEdges(i)) 
            if (!seen[j]) {
            	System.out.println(i + " → " + j);
	            seen[j] = true; // guard against same-level multi refs to child
                q.add(j);
            }
		}
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
