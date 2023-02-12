package PartI.Sort;

public class Merge {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void merge(Comparable [] a, Comparable [] aux, int hi, int lo, int mid) {
        // a[lo : mid] and a[mid+1 : hi] are sorted respectively
        // assert isSorted(a, lo, mid);
        // assert isSorted(a, mid+1, hi);
        for (int k=lo; k < hi ; k++) {
            aux[k] = a[k];
        }
        int i=lo, j=mid+1;
        for (int k=lo; k < hi ; k++) {
            if (i > mid) {
                // the i idx has reached to its end mid, copy the rest aux[j:hi]
                a[k] = aux[j++];
            } 
            else if (j > hi) {
                // the j idx has reached hi, copy the rest in aux[i:mid]
                a[k] = aux[i++];
            }
            else if (less(aux[i], aux[j])) {
                a[k] = aux[i++];
            }
            else {
                a[k] = aux[j++];
            }
        }

    }

    static int CUTOFF = 7;

    private static void sort(Comparable [] a, Comparable [] aux, int lo, int hi) {
        if (hi <= lo) return;
        if (hi <= lo + CUTOFF -1) {
            // for small subarrays, use insertion sort or other alternatives
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        // check if merge is not needed, already sorted a[lo:hi]
        if (less(a[mid], a[mid+1])) return;
        merge(a, aux, lo, hi, mid);
    }

    public static void sort(Comparable [] a) {
        Comparable [] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);

    }

    private static void improvedmerge(Comparable [] a, Comparable [] aux, int hi, int lo, int mid) {
        int i=lo, j=mid+1;
        for (int k=lo; k < hi ; k++) {
            if (i > mid)
                aux[k] = a[j++];
            else if (j > hi)
                aux[k] = a[i++];
            else if (less(aux[i], aux[j]))
                aux[k] = a[i++];
            else
                aux[k] = a[j++];
        }
    }

    private static void improvedsort(Comparable [] a, Comparable [] aux, int lo, int hi) {
        // save time for copying to aux by inverse the calling order during each recursion
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        improvedsort(aux, a, lo, mid);
        improvedsort(aux, a, mid+1, hi);
        improvedmerge(a, aux, lo, hi, mid);
    }

    public static void improvedsort(Comparable [] a) {
        Comparable [] aux = new Comparable[a.length];
        improvedsort(a, aux, 0, a.length-1);

    }

    
}
