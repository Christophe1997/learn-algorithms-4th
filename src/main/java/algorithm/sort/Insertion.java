package algorithm.sort;

import algorithm.std.StdIn;

public class Insertion {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && Sort.less(a[j], a[j-1]); j--) {
                Sort.exch(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert Sort.isSorted(a): "not sorted";
        Sort.show(a);
    }
}
