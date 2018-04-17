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

    private int hash(Key key) { return key.hashCode() & 0x7fffffff % M; }

    public Optional<Value> get(Key key) { return st[hash(key)].get(key); }

    public boolean contains(Key key) { return get(key).isPresent(); }

    public SeparateChainingHashST<Key, Value> put(Key key, Value val) {
        st[hash(key)].put(key, val);
        return this;
    }

    public void delete(Key key) {
        st[hash(key)].delete(key);
    }
}
