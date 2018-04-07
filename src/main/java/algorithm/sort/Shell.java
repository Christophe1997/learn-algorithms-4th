package algorithm.sort;

import algorithm.std.StdIn;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        // 使用序列1/2 (3^k - 1)
        while (h < N/3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && Sort.less(a[j], a[j - h]); j -= h) {
                    Sort.exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert Sort.isSorted(a): "not sorted";
        Sort.show(a);
    }
}
