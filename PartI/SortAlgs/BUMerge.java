package PartI.SortAlgs;

public class BUMerge {
/* Bottom-up Merge Sort */	
	private static Comparable [] aux;
	
	private static void merge(Comparable [] a, int lo, int hi, int mid) {
		if (less(a[mid], a[mid+1])) return;
		for (int k=lo; k < hi; k++) {
			aux[k] = a[k];
		}
		int i=lo, j=mid+1;
		for (int k=lo; k < hi; k++) {
			if (i > mid) 
				a[k] = aux[j++];
			else if (j > hi) 
				a[k] = aux[i++];
			else if (less(aux[i], aux[j]))
				a[k] = aux[i++];
			else 
				a[k] = aux[j++];
		}
	}
	
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

	public static void sort(Comparable [] a) {
		int N = a.length;
		aux = new Comparable[N];
		// real sorting process happens in merge
		// during each iteration of sz, sub-arrays get sorted
		for (int sz=1; sz < N; sz = sz+sz) {
			for (int lo=0; lo < N-sz; lo += sz+sz) {
				merge(a, lo, Math.min(lo+sz+sz-1, N-1), lo+sz-1);
			}
		}
	}


}
