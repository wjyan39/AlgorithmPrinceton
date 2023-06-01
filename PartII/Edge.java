package PartII;

public class Edge implements Comparable<Edge> {
    private final int v; 
    private final int w;
    private final double weight; 

    /**
     * Initialize an edge between vertices {@code v} and {@code w} of weight {@code weight} for undirected Graph.
     * 
     * @param v      one vertex 
     * @param w      the other vertex
     * @param weight weight of the Edge
     */

    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be non-negative");
        if (w < 0) throw new IllegalArgumentException("vertex index must be non-negative");
        if(Double.isNaN(weight)) throw new IllegalArgumentException("input weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v; 
    }

    public int other(int vertex) {
        if      (vertex == v) return w; 
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal vertex index");
    }

    // Compare two edges by weight.
    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

}