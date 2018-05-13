package algorithm.string;

public class Quick3string {

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static int charAt(String s, int d) {
        if (d >= s.length()) { return -1; }
        else { return s.charAt(d); }
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) { return; }
        int lt = lo, gt = hi;
        int i = lo + 1;
        int v = charAt(a[lo], d);
        while (i < gt) {
            int t = charAt(a[i], d);
            if (t > v) { exch(a, i, gt); gt--; }
            else if (t < v) { exch(a, i, lt); lt++; i++; }
            else { i++; }
        }
        sort(a, lo, lt - 1, d);
        if (v > 0) { sort(a, lt, gt, d + 1); }
        sort(a, gt + 1, hi, d);
    }
}
