package PartI.UnionFind;
/* Improved Quick Union Find Algorithm */
public class ImproveUF {
    private int [] idx;
    // size recording array
    private int [] sz;
    
    public ImproveUF(int N) {
        idx = new int[N];
        sz = new int[N];
        for (int i=0; i < N; i++) {
            idx[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int p, int q) {
        // weighted quick union of two tree structure 
        // let one with smaller size be the child of the other 
        int proot = root(p);
        int qroot = root(q);
        if (proot == qroot) return;
        if (sz[proot] < sz[qroot]) {
            sz[qroot] += sz[proot];
            idx[proot] = qroot;
        } else {
            sz[proot] += sz[qroot];
            idx[qroot] = proot;
        } 
    }

    private int root(int i) {
        // find the root of ith item 
        while (i != idx[i]) {
            // little trick: path compression, flatten the tree, make shallower
            // let ith element point to its grandparent.
            idx[i] = idx[idx[i]];
            i = idx[i];
        }
        return i; 
    }

    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

}
