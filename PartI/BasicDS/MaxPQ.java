package PartI.BasicDS;

public class MaxPQ<Key extends Comparable<Key>> 
{
    // Max-ordered priority queue demo via binary heap.
    private Key[] pq;
    private int N;

    public MaxPQ(int capacity) {
        // pq[0] is the dummy root
        pq = (Key []) new Comparable[capacity+1];
    }

    public boolean isEmpty() {
        return N==0;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    private void swim(int k) {
        while(k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            // choose the larger child 
            if(j < N && less(j, j+1)) j++;
            // find the right place 
            if(!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        // return the first item in MaxPQ
        Key max = pq[1];
        // put the the last leaf to root
        exch(1, N--);
        // and then sink to the correct place
        sink(1);
        pq[N+1] = null;
        return max;
    }

    // Worthwile mentioning: using immutable keys, vals that cannot be changed.

}