package algorithm.sort;

public class MergeBU {
    static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + 2 * sz -1, N - 1));
            }
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (Sort.less(a[j], a[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}
