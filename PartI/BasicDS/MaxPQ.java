package PartI.BasicDS;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key> implements Iterable<Key> {
    // Max-ordered priority queue demo via binary heap.
    private Key[] pq;
    private int N;
    private Comparator<Key> comparator;

    public MaxPQ(int capacity) {
        // pq[0] is the dummy root
        pq = (Key []) new Object[capacity+1];
        N = 0;
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int capacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key []) new Object[capacity+1];
        N = 0;
    }

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MaxPQ(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++)
            pq[i+1] = keys[i];
        for (int k = N/2; k >= 1; k--)
            sink(k);
        assert isMaxHeap();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    private void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private boolean less(int i, int j) {
        if (comparator == null){
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
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
        if (N == pq.length - 1) resize(2 * pq.length);
        pq[++N] = v;
        swim(N);
        assert isMaxHeap();
    }

    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        // return the first item in MaxPQ
        Key max = pq[1];
        // put the the last leaf to root
        exch(1, N--);
        // and then sink to the correct place
        sink(1);
        pq[N+1] = null;
        return max;
    }

    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {
        if (k > N) return true; 
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= N && less(k, left))  return false;
        if (right <= N && less(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        // create a new pq
        private MaxPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) copy = new MaxPQ<Key>(size());
            else                    copy = new MaxPQ<Key>(size(), comparator);
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }

    // Worthwile mentioning: using immutable keys, vals that cannot be changed.

}