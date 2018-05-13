package algorithm.datastruct;

import algorithm.std.StdIn;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    @SuppressWarnings("unchecked")
    private Key[] cast(Object obj) {
        return (Key[]) obj;
    }

    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void resize(int newSize) {
        Key[] t = cast(new Comparable[newSize]);
        System.arraycopy(pq, 1, t, 1, N);
        pq = t;
    }

    public MinPQ() {
        pq = cast(new Comparable[4]);
    }

    public MinPQ(int max) {
        pq = cast(new Comparable[max + 1]);
    }

    public void insert(Key v) {
        if (N >= pq.length - 1) resize(pq.length * 2);
        pq[++N] = v;
        swim(N);
    }

    public Key min() {
        return pq[1];
    }

    public Key delMin() {
        if (N < (pq.length - 1) / 2) resize(pq.length / 2);
        Key min = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return min;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MinPQ<String> minPQ = new MinPQ<>();
        for (String elem : a) minPQ.insert(elem);
        while (!minPQ.isEmpty()) {
            System.out.print(minPQ.min() + " ");
            minPQ.delMin();
        }
        System.out.println();
    }
}
