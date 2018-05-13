package algorithm.datastruct;

import algorithm.std.StdIn;


public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    @SuppressWarnings("unchecked")
    private Key[] cast(Object obj) {
        return (Key[]) obj;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void resize(int newSize) {
        Key[] t = cast(new Comparable[newSize]);
        System.arraycopy(pq, 1, t, 1, N);
        pq = t;
    }

    public MaxPQ() {
        pq = cast(new Comparable[1]);
    }

    public MaxPQ(int max) {
        pq = cast(new Comparable[max + 1]);
    }

    public void insert(Key v) {
        if (N >= pq.length - 1) resize(pq.length * 2);
        pq[++N] = v;
        swim(N);
    }

    public Key max() {
        return pq[1];
    }

    public Key delMax() {
        if (N < (pq.length - 1) / 2) resize(pq.length / 2);
        Key max = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MaxPQ<String> maxPQ = new MaxPQ<>();
        for (String elem : a) maxPQ.insert(elem);
        while (!maxPQ.isEmpty()) {
            System.out.print(maxPQ.max() + " ");
            maxPQ.delMax();
        }
        System.out.println();
    }
}
