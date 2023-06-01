package PartI.SortAlgs;

public class Selection {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Comparable [] a, int i, int j) {
        Comparable item = a[i];
        a[i] = a[j];
        a[j] = item;
    }
    
    public static void sort(Comparable [] a) {
        int N = a.length;
        for (int i=0; i < N; i++) {
            int min = i;
            for (int j=i+1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }
}
