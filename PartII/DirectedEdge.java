package PartII;

public class DirectedEdge implements Comparable<DirectedEdge> {
    private final int v; 
    private final int w;
    private final double weight; 

    /**
     * Initialize an edge between vertices {@code v} and {@code w} of weight {@code weight} for directed Graph.
     * 
     * @param v      src vertex 
     * @param w      dst vertex
     * @param weight weight of the Edge
     */

    public DirectedEdge(int v, int w, double weight) {
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

    public int from() {
        return v; 
    }

    public int to() {
        return w;
    }

    // Compare two edges by weight.
    @Override
    public int compareTo(DirectedEdge other) {
        return Double.compare(this.weight, other.weight);
    }

    public String toString() {
        return String.format("%d -> %d %5.2f", v, w, weight);
    }

}