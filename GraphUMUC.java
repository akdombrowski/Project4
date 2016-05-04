/*
 * Name: Anthony Dombrowski
 * Date: April 25, 2016
 * File: GraphUMUC.java
 * Assignment: Project 4
 * Purpose: Implementation of a graph.
 */


public class GraphUMUC<T> {
	private int v;
	LinkedListUMUC<Vertex>[] adj;
	
	// number of vertices constructor
	// unchecked warning from creating a generic array of linked lists
	@SuppressWarnings("unchecked") 
	public GraphUMUC(int v) {
		// make sure a nonnegative number of vertices is entered
		if(v < 0) {
			throw new IllegalArgumentException(
					"Number of vertices can't be negative.");
		}
		
		adj = (LinkedListUMUC<Vertex>[]) new LinkedListUMUC[v];
		for(int i = 0; i < v; i++) {
			adj[i] = new LinkedListUMUC<Vertex>();
		} // end for
	} // end int constructor
	
	// adds a vertex to the head of the list at i
	public void addVertex(T t, int i) {
		// throw exception if there's already a vertex at i
		// an array out of bounds exception will be thrown if i >= adj's size
		if(adj[i].peekHead() != null) {
			throw new IllegalArgumentException("A vertex is already at " + i);
		}
		
		adj[i].insertHead(new Vertex(t));
		v++;
	} // end addVertex method
	
	// removes the vertex from the head of the list at i and all of its edges
	public void removeVertex(int i) {
		// throw exception if there's already a vertex at i
		// an exception will also be thrown if i is out of bounds of adj
		if(adj[i].peekHead() == null) {
			throw new IllegalArgumentException("There isn't a vertex at " + i);
		}

		adj[i] = new LinkedListUMUC<Vertex>();
		v--;
	}
	
	// adds an edge to the adjacency list at index i 
	// by adding a new the T vertex to i's list
	public void addEdge(T t, int i){
		Vertex node = new Vertex(t, i);
		adj[i].insertTail(node);
	} // end addEdge method
	
	// removes the vertex at j in the list of edges of the vertex at i
	public void removeEdge(int i, T t){
		for(int j = 0; j < adj[i].size(); j++) {
			// create variable for vertex at j in i's list
			Vertex node = adj[i].peekElementAt(j);
			// removes the element from the list of i if t is found
			if(node.getData() == t) {
				adj[i].removeElementAt(j);
			} // end if
		} // end for;
	} // end removeEdge method
	
	// tells whether or not a vertex at index i is adjacent a particular T
	public boolean hasEdge(int i, T t){
		for(int j = 0; j < adj[i].size(); j++) {
			// create variable for node at j in i's list
			Vertex node = adj[i].peekElementAt(j);
			// return true if t matches any item in i's list
			if(node.getData() == t) {
				return true;
			} // end if
		} // end hasEdge method
		
		// return false if t wasn't found in i's list
		return false;
	} // end hasEdge method
	
	// returns a list of the edges coming out from the vertex at i
	public LinkedListUMUC<Vertex> outEdges(int i){
		return adj[i];
	} // end outEdges method
	
	// returns a list of the edges going into the vertex with t
	@SuppressWarnings("unchecked")
	public LinkedListUMUC<Vertex> inEdges(T t){
		LinkedListUMUC<Vertex> edges = new LinkedListUMUC<Vertex>();
		
		for(int i = 0; i < v; i++) {
			for(int j = 0; j < adj[i].size(); j++) {
				Vertex node = adj[i].peekElementAt(j);
				if(node == t) {
					edges.insertTail(node);
				}
			}
		}
		
		return edges;
	}
	
	// breadth-first search
	public void bfs() {
		
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < v; i++) {
			sb.append(adj[i].peekHead() + "\n");
		}
		return sb.toString();
	}
	
	public int getV() {
		return v;
	}
	
	public Vertex getVertex(int i) {
		return adj[i].peekHead();
	}
	
	public class Vertex {
		private T data;
		private int index;
		
		public Vertex() {
			data = null;
			index = 0;
		}
		
		public Vertex(T t) {
			data = t;
		}
		
		public Vertex(T t, int i) {
			data = t;
			index = i;
		}
		
		public T getData(){
			return data;
		}
		
		public int getIndex() {
			return index;
		}
		
		public String toString() {
			return data.toString();
		}
	}
	
	public static void main(String[] args) {
		int size = 10;
		GraphUMUC<Integer> graph = new GraphUMUC(size);
		
		for(int i = 0; i < 10; i++) {
			graph.addVertex(i, i);
		}
		
		System.out.println(graph.toString());
		
	}
}
