package algorithm.datastruct;

import java.util.Optional;

public class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    private SequentialSearchST<Key, Value> [] st;
    public SeparateChainingHashST() { this(996); }

    @SuppressWarnings("unchecked")
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private void resize(int size) {
        SeparateChainingHashST<Key, Value> tep = new SeparateChainingHashST<>(size);
        for (int i = 0; i < M; i++) {
            if (st[i] != null) {
                for (Key key : st[i].keys()) {
                    st[i].get(key).ifPresent(value -> tep.put(key, value));
                }
            }
        }
        this.st = tep.st;
        this.M = tep.M;
    }

    private int hash(Key key) { return key.hashCode() & 0x7fffffff % M; }

    public Optional<Value> get(Key key) { return st[hash(key)].get(key); }


    public boolean contains(Key key) { return get(key).isPresent(); }

    public SeparateChainingHashST<Key, Value> put(Key key, Value val) {
        if (N / M > 6) { resize(2 * M); }
        if (!contains(key)) { N++; }
        st[hash(key)].put(key, val);
        return this;
    }

    public void delete(Key key) {
        if (N / M < 3) { resize(M / 2); }
        if (contains(key)) {
            st[hash(key)].delete(key);
            N--;
        }
    }
}
