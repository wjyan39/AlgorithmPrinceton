package PartI.SortAlgs;

import java.util.Comparator;

public class Insertion {
    // Object should have Comparable implemented 

    private Insertion() { }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i=1; i < n; i++) {
            for (int j=i; j > 0 && less(a[j], a[j-1]); j--) {
                swap(a, j, j-1);
            }
        }
    }

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
