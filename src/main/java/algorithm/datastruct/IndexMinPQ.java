package algorithm.datastruct;


import algorithm.std.StdIn;
import jdk.nashorn.internal.ir.Optimistic;

import java.util.Optional;

public class IndexMinPQ<Key extends Comparable<Key>> {

    private int[] pq;
    private int[] qp;
    private Key[] keys;
    private int N = 0;

    public IndexMinPQ(int max) {
        pq = new int[max + 1];
        qp = new int[max + 1];
        keys = cast(new Comparable[max + 1]);
        for (int elem : qp) elem = -1;
    }

    public IndexMinPQ() {
        this(4);
    }

    @SuppressWarnings("unchecked")
    private Key[] cast(Object obj) {
        return (Key[]) obj;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void resize(int size) {
        IndexMinPQ<Key> tep = new IndexMinPQ<>(size);
        System.arraycopy(pq, 0, tep.pq, 0, N);
        System.arraycopy(qp, 0, tep.qp, 0, N);
        System.arraycopy(keys, 0, tep.keys, 0, N);
        pq = tep.pq;
        qp = tep.qp;
        keys = tep.keys;
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

    public Optional<Key> keyOf(int i) {
        return Optional.ofNullable(keys[i]);
    }

    public void insert(int k, Key key) {
        if (N > pq.length - 1) { resize(pq.length * 2);}
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);
    }

    public void change(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
        if (N < pq.length / 2) { resize(pq.length / 2); }
    }

    public Key min() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    public int delMin() {
        int idxOfMin = pq[1];
        exch(1, N);
        qp[pq[N--]] = -1;
        sink(1);
        keys[idxOfMin] = null;
        return idxOfMin;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        IndexMinPQ<String> minPQ = new IndexMinPQ<>(a.length);
        for (int i = 0; i < a.length; i++) minPQ.insert(i, a[i]);
        while (!minPQ.isEmpty()) {
            System.out.print(minPQ.min() + " ");
            minPQ.delMin();
        }
    }
}
