package PartI.Sort;

public class Insertion {
    // Object should have Comparable implemented 
    public static void sort(Comparable [] a, int lo, int hi) {
        for (int i=lo; i <= hi; i++) {
            for (int j=i; j > 0; j--) {
                if (less(a[j], a[j-1])) {
                    swap(a, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Comparable [] a, int i, int j){
        Comparable item = a[i];
        a[i] = a[j];
        a[j] = item;
    }
}
