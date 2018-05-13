package algorithm.string;

public class LSD {

    public static void sort(String[] a, int W) {
        int N = a.length;
        final int R = 256;
        int[] count = new int[R + 1];
        String[] aux = new String[N];
        for (int i = W - 1; i >= 0; i--) {
            for (String str : a) { count[str.charAt(i) + 1]++; }
            for (int k = 0; k < R; k++) { count[k + 1] += count[k]; }
            for (int k = 0; k < N; k++) {
                aux[count[a[i].charAt(i)]] = a[k];
                count[a[i].charAt(i)]++;
            }
            System.arraycopy(aux, 0, a, 0, a.length);
        }
    }
}
