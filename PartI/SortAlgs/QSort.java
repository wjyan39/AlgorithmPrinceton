package PartI.SortAlgs;

import Utility.StdRandom;

public class QSort {
    private static int partition(int [] a, int lo, int hi) {
        int cur_key = a[lo];
        int i = lo, j = hi;
        while(true) {
            while (less(a[i], cur_key)) {
                i++;
                if (i == hi) break;
            }
            while (less(cur_key, a[j])) {
                j--;
                if (j==lo) break;
            }
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private static boolean less(int v, int w) {
        return v - w < 0;
    }

    private static void swap(int [] a, int i, int j){
        int item = a[i];
        a[i] = a[j];
        a[j] = item;
    }

    private static void sort(int [] a, int lo, int hi) {
        // quick sort algorithm demo
        if (hi <= lo) return;
        // partition to a[lo, j] and a[j+1, hi]
        // satisfying left <= a[j] and right >= a[j]
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static void sort(int [] a) {
        int N = a.length;
        StdRandom.shuffle(a);
        sort(a, 0, N-1);
    }

    public static void partitionthree(int [] a, int lo, int hi) {
        // 3-way-partition demo
        int left = lo, right = hi, i = lo+1;
        int cur_key = a[lo];
        while (i < right) {
            if(less(a[i], cur_key)) {
                swap(a, i, left);
                left++;
                i++;
            } else if (less(cur_key, a[i])) {
                swap(a, i, right);
                right--;
            } else {
                i++;
            }
        }
        return;
    }

}