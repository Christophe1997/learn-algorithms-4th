package algorithm.string;

public class KeyIndexedCount {

    public static void sort(char[] chr, int R) {
        int[] count = new int[R + 1];
        char[] temp = new char[chr.length];
        for (char aChr : chr) { count[aChr + 1]++; }
        for (int i = 0; i < R; i++) { count[i + 1] += count[i]; }
        for (char aChr : chr) { temp[count[aChr]] = aChr; count[aChr]++; }
        System.arraycopy(temp, 0, chr, 0, chr.length);
    }

    public static void sort(char chr[]) { sort(chr, 256); }

    public static void main(String[] args) {
        char[] chr = "aasdfdbgtrhrf".toCharArray();
        sort(chr);
        for (char aChr : chr) { System.out.print(aChr); }
    }
}
