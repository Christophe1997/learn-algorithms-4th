package algorithm.string;

import algorithm.sort.InsertionX;

public class MSD {
    private static final int R = 256;
    private static final int M = 15;
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d >= s.length()) { return -1; }
        else { return s.charAt(d); }
    }

    public static void sort(String[] a) {
        aux = new String[a.length];
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            InsertionX.sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) { count[charAt(a[i], d) + 2]++; }
        for (int i = 0; i < R + 1; i++) { count[i + 1] += count[i]; }
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]] = a[i];
            count[charAt(a[i], d) + 1]++;
        }
        System.arraycopy(aux, lo, a, lo, hi - lo + 1);
        for (int i = 0; i < R; i++) { sort(a, lo + count[i], lo + count[i + 1] - 1, d + 1); }
    }

    public static void main(String[] args) {
        String[] a = new String[4];
        a[0] = "adfseg";
        a[1] = "dfddfbg";
        a[2] = "vfddsa";
        a[3] = "dfadca";
        sort(a);
        for (String str : a) { System.out.println(str); }
    }

}
