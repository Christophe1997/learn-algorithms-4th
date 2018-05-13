package algorithm.string;

public class BruteForceSearch {

    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i < N; i++) {
            int j = 0;
            while (txt.charAt(i + j) == pat.charAt(j)) {
                j++;
                if (j == M) { return i; }
            }
        }
        return N;
    }

    public static void main(String[] args) {
        System.out.println(search("asd", "dasdg"));
    }
}
