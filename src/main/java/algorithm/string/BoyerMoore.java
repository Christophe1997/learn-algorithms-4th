package algorithm.string;

public class BoyerMoore {
    private int[] right;
    private int M;
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++) { right[c] = -1; }
        for (int j = 0; j < M; j++) { right[pat.charAt(j)] = j; }
    }

    public int search(String txt) {
        int N = txt.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            int j = M - 1;
            while (j >= 0 && pat.charAt(j) == txt.charAt(i + j)) { j--; }
            if (j >= 0) {
                skip = j - right[txt.charAt(i + j)];
                if (skip < 1) {
                    skip = 1;
                }
            } else { return i; }
        }
        return N;
    }

    public static void main(String[] args) {
        BoyerMoore bm = new BoyerMoore("txt");
        System.out.println(bm.search("atxtadss"));
    }

}
