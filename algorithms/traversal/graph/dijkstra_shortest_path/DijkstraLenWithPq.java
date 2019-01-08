package com.iain.practice;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PriorityQueue;

/**
* Edge-weighted Adjacency Matrix Digraph.
* Uses multidimensional array to represent directed edges.
* Based on [Robert Sedgewick and Kevin Wayne’s code][https://algs4.cs.princeton.edu/44sp/AdjMatrixEdgeWeightedDigraph.java.html]
*/
public class WeightedDigraphMatrix {	

	/**
	* Used to develop evolving requirements.
	*/
	public static void main(String[] args) {
		final int v = 10, e = 100;
		WeightedDigraphMatrix G1 = new WeightedDigraphMatrix(v);
		G1.addEdge(new DirectedEdge(0,3,1));
		G1.addEdge(new DirectedEdge(1,0,3));
		G1.addEdge(new DirectedEdge(1,2,3));
		G1.addEdge(new DirectedEdge(1,2,10)); // can replace existing edge
		G1.addEdge(new DirectedEdge(2,3,10));
		G1.addEdge(new DirectedEdge(3,4,10));
		G1.addEdge(new DirectedEdge(4,5,10));
		G1.addEdge(new DirectedEdge(5,6,10));
		G1.addEdge(new DirectedEdge(1,3,2));
	    System.out.println("Vertex 1 to 3 has SP: " + G1.dijkstraLen(1, 4));
	    System.out.println("Vertex 1 to 3 has SP: " + G1.dijkstraLen(1, 3));
		
		WeightedDigraphMatrix G2 = new WeightedDigraphMatrix(v, e);
		System.out.println(G1);
		System.out.println(G2);
	    
	}
	
		/**
	* Calculate the shortest paths for a single source.
	* Uses Dijkstra’s algorithm with a PriorityQueue
	* @param i the source vertex
	* @param j the target vertex
	* @return the short path length to that vertex.
	*/
	public double dijkstraLen(int i, int j) {
		boolean[] done = new boolean[V];
		double[] absoluteDistFrom = new double[V];
		int[] witness = new int[V];
		VertexHeap approximations = new VertexHeap();

        for (int k=0; k<V; k++)
            absoluteDistFrom[k] = Double.POSITIVE_INFINITY;		
        absoluteDistFrom[i] = 0.0;
        
        approximations.insert(i, absoluteDistFrom[i]);
    		
	    while(!approximations.isEmpty()) {
			int observer = approximations.pop();
		    done[observer] = true;

    		for (DirectedEdge e : outEdges(observer)) {
				int event = e.to();
				double relativeDistFromEvent = e.weight();
        
                if (done[event]) continue;
                System.out.println(""
                    + "Comparing " 
                    + i + "->" + event + " is " + absoluteDistFrom[event] 
                    + " AND "
                    + "[" + i + "->" + observer + " is " + absoluteDistFrom[observer] + "]->" 
                    + event + " is " + relativeDistFromEvent);
                if (absoluteDistFrom[event] > absoluteDistFrom[observer] + relativeDistFromEvent) {
                	absoluteDistFrom[event] = absoluteDistFrom[observer] + relativeDistFromEvent;
                	witness[event] = observer;
                }
                
                approximations.insert(event, absoluteDistFrom[event]);
        	}
		}

		return absoluteDistFrom[j];
	}

/**
	* VertexHeap -
	* Custom PriorityQueue implementation for Vertices in Dijkstra algorithm.
	* Keeps a min-heap of vertices with the vertex’s distance from a source
	* vertex. It is more efficient to keep this Heap small/pruned.
	*/
	class VertexHeap extends PriorityQueue<Vertex> {
		/**
		* Insert Vertex with priority into Queue.
		* @param j the vertex
		* @param x the distance of the vertex
		*/
		public void insert(int j, double x) {
			Vertex v = new Vertex(j, x);
			remove(v);
			add(v);
		}
		public int pop() {
			Vertex v = poll();
			return v.getVertex();
		}
		
		
	}

    static class Vertex implements Comparable<Vertex> {
			int j; // vertex
			double w; // weight

			private Vertex() {}

			public Vertex(int j, double w) {
				this.j = j;
				this.w = w;
			}
			
			public int getVertex() { return j; }
			public double getWeight() { return w; }

			@Override
			public boolean equals(Object o) {
				if (null == o) return false;
				if (!(o instanceof Vertex)) return false;

				if (this == o) return true;
				Vertex v = (Vertex)o;
				return (getVertex() == v.getVertex());
			}

			@Override
			public int hashCode() {
				return  (int)(((31*j + ((int)(Double.doubleToRawLongBits(w) >> 32) & 0xFF)) % (1 << 31))*31
                        +(0xFF & Double.doubleToRawLongBits(w))) % (1 << 31);
                        }

            @Override
			public int compareTo(Vertex v1) {
				int retVal = 1;
                if (null != v1) {
					retVal = Double.compare(
                        v1.getWeight(), 
                        getWeight());
				}
				return retVal;
            }
		}
	private int V; // static vertice capacity. Defined on `WeightedDigraphmatrix(int)`.
	private int E; // number of edges
	private DirectedEdge[][] adj; // graph edges

	/**
	* DO NOT USE.
	* Privatized Constructor. Graph has a dependency on vertice capacity inorder
	* to create the underlying matrix datastructure to store nodes. Graph cannot
	* function without this datastructure.
	*/
	public WeightedDigraphMatrix() {throw new UnsupportedOperationException();}

	/**
	* Initialize digraph with the capacity for the stipulated number of vertices.
	* @param V the number of vertices
	*/
	public WeightedDigraphMatrix(int V) {
		if (0 > V) throw new IllegalArgumentException(""
            + "Number of vertices must be zero or greater");
		this.V = V;
		this.E = 0;
		this.adj = new DirectedEdge[this.V][this.V];
	}

	/**
	* Get the number of vertices in the Graph.
	* This number is static.
	* @return number of vertices.
	*/
	public int nVertices() { return V; }

	/**
	* Get the number of edges in the Graph.
	* @return number of edges.
	*/
	public int nEdges() { return E; }

	/**
	* Check if Graph has Edge.
	* @param i the source vertex
	* @param j the target vertex
	* @return true if the graph has the edge
	* @throws IllegalArgumentException if {@code 0 > i || 0 > j || j >= V || i >= V}
	*/
	public boolean hasEdge(int i, int j) {
		validateVertex(i);
		validateVertex(j);
		return (null != adj[i][j]) ? true : false;
	}

	/**
	* Represent the Graph as a String.
	*/
	@Override
	public String toString() {
		final String NEWLINE = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append(""
            + "--"
            + " Graph [" + hashCode() + "]" 
            + " " + V + " vertices"
            + " " + E + " edges"
            + " --"
            + NEWLINE);
        for (int i=0; i<V; i++) {
        	sb.append("Vertice " + i + ": ");
        	for (DirectedEdge e : outEdges(i))
        		sb.append(e + " ");
        	sb.append(NEWLINE);
        }
        sb.append("-- END OF GRAPH --" + NEWLINE);
        return sb.toString();
	}

	/**
	* Iterator navigating edges which have the given source vertex
	* @param i the source vertex
	*/
	class OutEdgesIterator 
        implements Iterator<DirectedEdge>, Iterable<DirectedEdge> {
		private int i; // source vertex
		private int j; // target vertex
		
		/**
		* DO NOT USE.
		* Privatized Constructor. This iterator requires a vertex from which to
		* iterate. Used {@code OutEdgesIterator(int)} instead.
		*/
		private OutEdgesIterator() {}

		/**
		* Construct iterator for the given source vertex.
		* @param i the source vertex
		*/
		public OutEdgesIterator(int i) { this.i = i; }
		
		/**
		* Get iterable iterator.
		* @return iterator for foreach statements
		*/
		public Iterator<DirectedEdge> iterator() { return this; }


		/**
		* UNSUPPORTED.
		* Remove an element from the iteration’s collection.
		*/
		public void remove() { throw new UnsupportedOperationException(); }

		/**
		* Check there is another element in the collection.
		* @return true if an element exists
		*/
		public boolean hasNext() {
			for(; j<V; j++) if (hasEdge(i, j)) return true;
			return false;
        }

        /**
        * Get the next element.
        */
        public DirectedEdge next() {
        	if (!hasNext()) throw new NoSuchElementException();
        	return adj[i][j++];
        }
	}

	/**
	* Get iterator to edges with the given source vertex.
	* @param i the source vertex
	* @return iterator to edges with source vertex
	*/
	public OutEdgesIterator outEdges(int i) {
		validateVertex(i);
		return new OutEdgesIterator(i);
    }

	/**
	* Add directed edge to Graph.
	* @param e the edge
	*/
	public void addEdge(DirectedEdge e) {
		int i = e.from();
		int j = e.to();
		validateVertex(i);
        validateVertex(j);
        if (null == adj[i][j]) {
        	E++;
        	adj[i][j] = e;
        } else if (!Objects.equals(adj[i][j].weight(), e.weight())) {
            adj[i][j] = e;
        }
	}

	/**
	* Validate that the vertex is in this Graph.
	* @param v the vertex
	*/
	private void validateVertex(int v) {
		if (0 > v || V <= v) throw new IndexOutOfBoundsException();
	}

    /**
	* Initialize digraph with capacity for vertices and a number of random-weighted edges.
	* @param V the vertice capacity of the graph
	* @param E the number of edges to create
	*/
	public WeightedDigraphMatrix(int V, int E) {
		this(V);
		if (0 > E) throw new IllegalArgumentException(""
            + "number of edges must greater than negative one");
        if (V*V < E) throw new IllegalArgumentException(""
        	+ "too many edges");
		while (E != this.E) {
			int i = (int)(Math.random()*V);
			int j = (int)(Math.random()*V);
			double w = Math.random()*200 + 100; // [-100, 100] range
			addEdge(new DirectedEdge(i, j, w));
		}
	}

	/**
	* Directed Edge class
	* Represents a relationship from a source vertex to a target vertex.
	*/
	static class DirectedEdge {
		
		/**
		* Get source vertex
		*/
		public int from() { return i; }

		/**
		* Get target vertex
		*/
		public int to() { return j; }
		
		/**
		* Get weight.
		* @return the edge weight
		*/
		public double weight() { return w; }

		/**
		* Represent the edge as a String
		* @return string representation.
		*/
		@Override
		public String toString() {
			return ""
			    +"[" + i + " → " + j + "]"
			    + "{w = " + String.format("%.2f", w) + "}";
		}

		private  int i; // source vertex
		private  int j; // target vertex
		private  double w; // edge weight

		/**
		* DO NOT USE.
		* Privatized Constructor. Edges must be created with a source vertex,
		* a target vertex, and a weight.
		*/
		private DirectedEdge() {throw new UnsupportedOperationException();}

		/**
		* Create edge between source and target vertices with a given weight.
		* @param i the source vertex
		* @param j the target vertex
		* @param w the weight
		*/
		public DirectedEdge(int i, int j, double w) {
			if (0 > i) throw new IllegalArgumentException(""
				+ "Vertex" + i + " must be non-negative");
			if (0 > j) throw new IllegalArgumentException(""
				+ "Vertex" + j + " must be non-negative");
			if (Double.isNaN(w)) throw new IllegalArgumentException(""
				+ "Weight is NaN");
			this.i = i;
			this.j = j;
			this.w = w;
		}
	}
}