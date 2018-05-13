package algorithm.sort;

import algorithm.std.StdIn;
import algorithm.std.StdRandom;

public class Quick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi < lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        if (lo == hi) return lo;
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (Sort.less(a[++i], v)) if (i >= hi) break;
            while (Sort.less(v, a[--j])) if (j <= lo) break;
            if (i >= j) break;
            Sort.exch(a, i, j);
        }
        Sort.exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert Sort.isSorted(a): "not sorted";
        Sort.show(a);
    }
}
