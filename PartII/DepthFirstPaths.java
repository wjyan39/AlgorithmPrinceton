package PartII;
import PartI.BasicDS.Stack;;

/**
 * compute paths from a source vertex <em>s</em> to every other vertex
 */

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;  // source vertex 

    public DepthFirstPaths(Graph G, int s) {
        this.s = s; 
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x=v; x != s; x=edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}