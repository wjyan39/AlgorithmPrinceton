package PartI.SortAlgs;
public class Shell {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Comparable [] a, int i, int j){
        Comparable item = a[i];
        a[i] = a[j];
        a[j] = item;
    }

    public static void sort(Comparable[] a) {
        int N = a.length;
        int offset = 1;
        // generate step series 
        while (offset < N/3) {
            offset = 3*offset + 1;
        }
        while(offset >= 1) {
            for (int i=offset; i < N; i++) {
                for (int j=i; j >= offset && less(a[j], a[j-offset]); j -= offset) 
                {  
                    swap(a, j, j-offset);  
                }
                offset /= 3;
            }
        }

    }
}