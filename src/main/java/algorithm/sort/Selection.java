package algorithm.sort;

import algorithm.std.StdIn;

public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (Sort.less(a[j], a[min])) min = j;
                Sort.exch(a, i, min);
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
