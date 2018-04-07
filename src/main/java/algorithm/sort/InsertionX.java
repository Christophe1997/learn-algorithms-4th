package algorithm.sort;

import algorithm.std.StdIn;

public class InsertionX {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int exchange = 0;
        for (int i = N - 1; i > 0; i--) {
            if (Sort.less(a[i], a[i - 1])) {
                Sort.exch(a, i, i - 1);
                exchange++;
            }
        }
        if (exchange == 0) return;
        for (int i = 2; i < N; i++) {
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
