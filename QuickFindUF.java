package PartI.UnionFind;
/* 
  Naive Union Find, use a list for recording index. 
  Elements in the same set share same index.
*/

public class QuickFindUF {
    private int[] idx;

    public QuickFindUF(int N) {
        idx = new int[N];
        for (int i=0; i < N; i++) {
            idx[i] = i;
        }
    }

    void union(int p, int q) {
        int pid = idx[p];
        int qid = idx[q];
        // this operation takes O(N) scaling 
        for (int i=0; i < idx.length; i++) {
            if (idx[i] == pid) idx[i] = qid;
        }
    }
    
    boolean isConnected(int p, int q) {
        return idx[p] == idx[q];
    }

    int find(int p) {
        return -1;
    }

    int count() {
        return 0;
    }
}