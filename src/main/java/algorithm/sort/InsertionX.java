package algorithm.sort;

import algorithm.std.StdIn;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

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

    private static int charAt(String s, int d) {
        if (d >= s.length()) { return -1; }
        else { return s.charAt(d); }
    }

    public static void sort(String[] str, int lo, int hi, int d) {
        int exchange = 0;
        BiPredicate<String, String> less = (a, b) -> a.substring(d).compareTo(b.substring(d)) < 0;
        for (int i = hi; i > lo; i--) {
            if (less.test(str[i], str[i - 1])) {
                Sort.exch(str, i, i - 1);
                exchange++;
            }
        }
        if (exchange == 0) return;

        for (int i = lo + 1; i <= hi; i++) {
            String temp = str[i];
            int y = i;
            while (less.test(temp, str[y - 1])) {
                str[y] = str[y - 1];
                y--;
            }
            str[y] = temp;
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert Sort.isSorted(a) : "not sorted";
        Sort.show(a);
    }
}
