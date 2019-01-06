package com.iain.practice;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
* Graph -
* Interface describing the minimum facilities to be provided.
*/
interface Graph {
	public int nVertices(); // return the number of vertices in the graph
	public void addEdge(int i, int j); // add the edge i → j, to the graph
	public void removeEdge(int i, int j); // remove i → j, from the graph
	public boolean hasEdge(int i, int j); // existence check of i → j
	public List<Integer> inEdges(int i); // all edges j → i, where j is a vertice in graph
	public List<Integer> outEdges(int i); // all edges i → j
	public int inDegree(int i); // | {j → i} |, number of edges of target vertex i
	public int outDegree(int i); // | {i → j} |, number of edges of source vertex i
}

/**
* AdjacencyList - 
* Represents a graph as an array of vertices which maintain lists of their respective edges.
*/
public class AdjacencyList implements Graph {
	public static void main(String[] args) {
		Graph g = new AdjacencyList(10);
	
		System.out.println("Graph has " + g.nVertices() + " number of vertices");	
		
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(9, 0);
		g.addEdge(1, 2);

		System.out.println("Graph has " + g.nVertices() + " number of vertices");

		System.out.println("Graph has (0,1) edge [" + g.hasEdge(0, 1) + "].");
        System.out.println("Graph has (9,0) edge [" + g.hasEdge(9, 0) + "].");
		System.out.println("Graph has (1,2) edge [" + g.hasEdge(1, 2) + "].");
		System.out.println("Graph has (1,1) edge [" + g.hasEdge(1, 1) + "].");

		System.out.println("Vertex 7 has inEdges " + stringify(g.inEdges(7)));
		System.out.println("Vertex 1 has inEdges " + stringify(g.inEdges(1)));
		System.out.println("Vertex 0 has inEdges " + stringify(g.inEdges(0)));
		System.out.println("Vertex 9 has inEdges " + stringify(g.inEdges(9)));

		System.out.println("Vertex 7 has outEdges " + stringify(g.outEdges(7)));
		System.out.println("Vertex 1 has outEdges " + stringify(g.outEdges(1)));
		System.out.println("Vertex 0 has outEdges " + stringify(g.outEdges(0)));
		System.out.println("Vertex 9 has outEdges " + stringify(g.outEdges(9)));

		System.out.println("Vertex 7 has inDegree of " + g.inDegree(7));
		System.out.println("Vertex 1 has inDegree of " + g.inDegree(1));
		System.out.println("Vertex 0 has inDegree of " + g.inDegree(0));
		System.out.println("Vertex 9 has inDegree of " + g.inDegree(9));

		System.out.println("Vertex 7 has outDegree of " + g.outDegree(7));
		System.out.println("Vertex 1 has outDegree of " + g.outDegree(1));
		System.out.println("Vertex 0 has outDegree of " + g.outDegree(0));
		System.out.println("Vertex 9 has outDegree of " + g.outDegree(9));

		g.removeEdge(0, 1);
		g.removeEdge(0, 2);
		g.removeEdge(0, 3);
		
		System.out.println("Vertex 0 has outDegree of " + g.outDegree(0));
	}
	
	/**
	* Transform List to String.
	* @param x the list to transform
	* @return the string representation.
	*/
	private static String stringify(List<Integer> x) {
	    if (null == x || 0 >= x.size()) return "";
	    return x.stream()
	            .map(Object::toString)
	            .collect(Collectors.joining(", "));
	}

	/**
	* The number of edges with vertex `i` as their source, i.e `| {i → j} |`
	* @param i the source vertex
	* @return the number of edges
	*/
	public int outDegree(int i) {
		if (0 > i || i > n-1) throw new IndexOutOfBoundsException();
		return adj[i].size();
	}

	/**
	* The number of edges which target vertex `i`, i.e `| {j → i} |`
	* @param i the target vertex
	* @return the magnitude of edges targeting `i`
	*/
	public int inDegree(int i) {
		if (0 > i || i > n-1) throw new IndexOutOfBoundsException();
		int d = 0;
		for (int j=0; j<n; j++) if(adj[j].contains(i)) d++;
		return d;
	}

	/**
	* Get target vertices of edges with source vertex `i`, i.e {i → j}
	* @param i the source vertex
	* @return the Immutable List of target vertices
	*/
	public List<Integer> outEdges(int i) {
		if (0 > i || i > n-1) throw new IndexOutOfBoundsException();
		return new ArrayList<>(adj[i]);
	}

	/**
	* Get vertices of edges, which target the `i` vertex, i.e j → i
	* @param the target vertex
	* @return source vertices
	*/
	public List<Integer> inEdges(int i) {
		if (0 > i || i > n-1) throw new IndexOutOfBoundsException();
		List<Integer> v = new ArrayList<>();
		for (int j=0; j<n; j++) if (adj[j].contains(i)) v.add(j);
		return v;
	}

	/**
	* Check if edge has membership in Graph.
	* @param i the source vertex
	* @param j the target vertex
	* @return the existence of edge i → j
	*/
	public boolean hasEdge(int i, int j) {
		if (0 > i || 0 > j || i > n-1 || j > n-1) throw new IndexOutOfBoundsException();
		return adj[i].contains(j);
	}

	/**
	* Remove the edge from the graph, so i → j is not in {E}
	* @param int i the source vertex
	* @param int j the target vertex
	*/
	public void removeEdge(int i, int j) {
		if (0 > i || 0 > j || i > n-1 || j > n-1) throw new IndexOutOfBoundsException();
		Iterator<Integer> iter = adj[i].iterator();
		while (iter.hasNext()) {
			if (iter.next() == j) {
				iter.remove();
				break;
			}
		}
	}

	/**
	* Add the given edge to the graph, such that i → j.
	* @param i the source vertice
	* @param j the target vertice
	*/
	public void addEdge(int i, int j) {
		if (0 > i || 0 > j || i > n - 1 || j > n - 1) throw new IndexOutOfBoundsException();
		adj[i].add(j);
	}

	/**
	* The number of vertices in this Graph.
	* @return the number of vertices
	*/
	public int nVertices() { return n; }

	List<Integer>[] adj; // vertice array backed by Lists for edges
	int n; // the number of vertices in this Graph, number is static after init

	/**
	* DO NOT USE.
	* Privatized constructor. This Graph cannot be created without specifying the
	* number of vertices. Use `AdjacencyList(int n)` instead.
	*/
	private AdjacencyList() {}

	/**
	* DEFAULT CONSTRUCTOR.
	* This constructor instantiates an AdjacencyList based on a static number of vertices.
	* Implements the Graph Interface.
	* @param nn the static number of vertices in this Graph
	*/
	@SuppressWarnings("unchecked")
	public AdjacencyList(int n0) {
		this();
		n = n0;
		adj = (List<Integer>[])new List[n];
		for (int i=0; i<n; i++)
			adj[i] = new ArrayList<Integer>(); // O(1) get & O(1 + min(i, n-i)) add/rem.
	}
}