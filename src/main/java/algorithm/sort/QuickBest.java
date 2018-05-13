package algorithm.sort;

import algorithm.std.StdIn;
import algorithm.std.StdRandom;

/**
 *  A three way quick sort using insertion sorting with small array.
 *  But the algorithm perform worse than three way quick sorting( ·_·).
 */
public class QuickBest {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    @SuppressWarnings("unchecked")
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo > hi - 8) insertionSort(a, lo, hi);
        else {
            int lt = lo, i = lo + 1, gt = hi;
            Comparable v = a[lo];
            while (i <= gt) {
                int cmp = a[i].compareTo(v);
                if (cmp < 0) Sort.exch(a, lt++, i++);
                else if (cmp > 0) Sort.exch(a, gt--, i);
                else i++;
            }
            sort(a, lo, lt - 1);
            sort(a, gt + 1, hi);
        }
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int exchange = lo;
        for (int i = hi; i > lo; i--) {
            if (Sort.less(a[i], a[i - 1])) {
                Sort.exch(a, i, i - 1);
                exchange++;
            }
        }
        if (exchange == lo) return;
        for (int i = lo + 1; i <= hi; i++) {
            Comparable v = a[i];
            int j = i;
            while (Sort.less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert Sort.isSorted(a): "not sorted";
        Sort.show(a);
    }
}
