package com.iain.practice;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
* Graph Interface -
* Defines the minimum operations supported by a Graph.
* Based on [ODS][http://opendatastructures.org/ods-java/12_1_AdjacencyMatrix_Repres.html] by Pat Morin.
*/
interface Graph {
	public int nVertices(); // return the number of vertices in Graph
	public void addEdge(int i, int j); // add the directed edge i → j to the graph
	public void removeEdge(int i, int j); // remove the directed edge i → j from the graph
	public boolean hasEdge(int i, int j); // determines if edge i → j is a member in Graph
	public List<Integer> inEdges(int i); // vertices of edges which target `i`, i.e {j → i}: {j}
	public List<Integer> outEdges(int i); // vertices of edges which source is `i`, i.e {i → j}: {i}
	public int inDegree(int i); // number of edges with `i` as target, i.e `| {j → i} |`
	public int outDegree(int i); // number of edges with `i` as source, i.e `| {i → j} |`
}

/**
* AdjacencyMatrix -
* Matrix based implementation of a Graph. Each index corresponds to an edge.
* Each row/rank holds edges for a source vertex.
* Each column/file holds edges of target vertex.
*
* Based on [ODS][http://opendatastructures.org/ods-java/12_1_AdjacencyMatrix_Repres.html] by Pat Morin.
*/
public class AdjacencyMatrix implements Graph {
	public static void main(String[] args) {
		Graph g = new AdjacencyMatrix(10);
		printMetadata(g);

		g.addEdge(0, 9);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(0, 4);
		g.addEdge(9, 0);
		g.addEdge(1, 2);
		printMetadata(g);

		g.removeEdge(0, 9);
		g.removeEdge(9, 0);
		g.removeEdge(1, 1);
		printMetadata(g);
	}



	/**
	* Get the number of edges with the given source vertex, i.e `| {i → j} |`.
	* @param i the source vertex
	* @return the number of edges
	*/
    public int outDegree(int i) {
    	if (0 > i || i > n-1) throw new IndexOutOfBoundsException();
    	int d = 0;
    	for (int j=0; j<n; j++) if(adj[i][j]) d++;
    	return d;
    }	

    /**
	* Get the number of edges which target the given vertex, i.e `| {j → i} |`.
	* @param i the target vertex
	* @return the number of edges
	*/
	public int inDegree(int i) {
		if (0 > i || i > n-1) throw new IndexOutOfBoundsException();
		int d = 0;
		for (int j=0; j<n; j++) if (adj[j][i]) d++;
		return d;
	}

    /**
	* Get target vertices of edges with the given source vertex, i.e {i → j}
	* @param i the source vertex
	* @return List of target vertices
	*/
	public List<Integer> outEdges(int i) {
		if (0 > i || i > n-1) throw new IndexOutOfBoundsException();
		List<Integer> v = new ArrayList<>();
		for (int j=0; j<n; j++) if (adj[i][j]) v.add(j);
		return v;
	}

    /**
	* Get the source vertices are in edges targeting the given vertex.
	* @param i the target vertex
	* @return List of vertices
	*/
	public List<Integer> inEdges(int i) {
		if (0 > i || i > n-1) throw new IndexOutOfBoundsException();
		List<Integer> v = new ArrayList<>();
		for (int j=0; j<n; j++) if (adj[j][i]) v.add(j);
		return v;
	}

	/**
	* Check if edge is in Graph.
	* @param i the source vertex
	* @param j the target vertex
	*/
	public boolean hasEdge(int i, int j) {
		if (0 > i || 0 > j || i > n-1 || j > n-1) throw new IndexOutOfBoundsException();
		return adj[i][j];
	}

	/**
	* Remove an edge from the Graph.
	* @param i the source vertex
	* @param j the target vertex
	*/
	public void removeEdge(int i, int j) {
		if (0 > i || 0 > j || i > n-1 || j > n-1) throw new IndexOutOfBoundsException();
		adj[i][j] = false;
	}

	/**
	* Add the directed edge to the Graph.
	* @param i the source vertex
	* @param j the target vertex
	*/
	public void addEdge(int i, int j) {
		if (0 > i || 0 > j || i > n-1 || j > n-1) throw new IndexOutOfBoundsException();
		adj[i][j] = true;
	}
	
	/**
	* Get the number of vertices in this Graph.
	* @return number of vertices, i.e `| {V} |`
	*/
	public int nVertices() { return n; }

	/**
	* STATIC: Print the Graph metadata.
	* Only intended for private use.
	* @param g the Graph.
	*/
	private static void printMetadata(Graph g) {
	    
    	// Print vertex count
        System.out.println("Graph has " + g.nVertices() + " vertices.");
    		
        // Print hasEdge
        for (int i=0; i<g.nVertices(); i++)
    			for (int j=0; j<g.nVertices(); j++)
    				System.out.println(""
    					+ "Graph has edge (" + i + ", " + j + ")"
    					+ " [" + g.hasEdge(i, j) + "].");
    		
    		// Print inEdges
        for (int i=0; i<g.nVertices(); i++)
    			System.out.println(""
    				+ "Vertex " + i + " has inEdges"
    				+ " [" + stringify(g.inEdges(i)) + "].");
    
    		// Print outEdges
        for (int i=0; i<g.nVertices(); i++)
    			System.out.println(""
    				+ "Vertex " + i + " has outEdges"
    				+ " [" + stringify(g.outEdges(i)) + "].");
    
    		// Print inDegree
        for (int i=0; i<g.nVertices(); i++)
    			System.out.println(""
    				+ "Vertex " + i + " has inDegree"
    				+ " [" + g.inDegree(i) + "].");
    
    		// Print outDegree
        for (int i=0; i<g.nVertices(); i++)
    			System.out.println(""
    				+ "Vertex " + i + " has outDegree"
    				+ " [" + g.outDegree(i) + "].");
	}

	/**
	* STATIC: Transform a List of Integers into a String.
	* @param x the List of Integers
	* @return the string representation
	*/
	private static String stringify(List<Integer> x) {
		if (null == x || x.isEmpty()) return "";
		return x.stream()
			.map(Object::toString)
			.collect(Collectors.joining(", "));
	}

	int n; // number of vertices in Graph. Static value.
	boolean[][] adj; // underlying Graph representation. Stores edges.

	/**
	* DO NOT USE.
	* Privatized constructor. This Graph cannot be created with stipulating
	* the number of vertices.
	*/
	private AdjacencyMatrix() {}

	/**
	* DEFAULT CONSTRUCTOR.
	* The Graph must be instantiated with a static number of vertices.
	* @param n0 the number of vertices
	*/
	public AdjacencyMatrix(int n0) {
		this();
		n = n0;
		adj = new boolean[n][n];
	}
}