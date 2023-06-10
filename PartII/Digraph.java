package PartII;

import java.util.NoSuchElementException;
import PartI.BasicDS.Bag;
import PartI.BasicDS.Stack;
import Utility.In;

// Directed Graph 
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;        // number of vertices 
    private int E;              // number of Edges 
    private Bag<Integer>[] adj; // adj[v] adjacency list for vertex v
    private int[] indegree;     // indegree[v] indegree of vertex v

    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices V should be non-negative.");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v=0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    // initialize via input text 
    public Digraph(In in) {
        if (in == null) throw new IllegalArgumentException();
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("Number of vertices V should be non-negative.");
            E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of edges E should be non-negative.");
            indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v=0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            for (int i=0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor.", e);
        }
    }

    public Digraph(Digraph G) {
        if (G == null) throw new IllegalArgumentException("argument is null");
        this.V = G.V();
        this.E = G.E();
        indegree = new int[V];
        for (int v=0; v < V; v++)
            this.indegree[v] = G.indegree(v);
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v=0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    // out degree of vertex v, i.e. size of adj[v]
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) 
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w); 
        adj[v].add(w);
        indegree[w]++;
        this.E++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // reverse of the digraph
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}
