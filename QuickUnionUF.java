package PartI.UnionFind;
// Quick Union lazy algorithm 
public class QuickUnionUF {
    private int[] idx;

    public QuickUnionUF(int N) {
        idx = new int[N];
        for (int i=0; i < N; i++) {
            idx[i] = i;
        }
    }

    private int root(int i) {
        // find the root of ith item 
        while (i != idx[i]) {
            i = idx[i];
        }
        return i; 
    }

    void union(int p, int q) {
        // union two tree structure 
        // let one be the child of the other 
        // could be slow if the final tree gets deeper
        int proot = root(p);
        int qroot = root(q);
        idx[proot] = qroot; 
    }
    
    boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    int find(int p) {
        return -1;
    }

    int count() {
        return 0;
    }
}