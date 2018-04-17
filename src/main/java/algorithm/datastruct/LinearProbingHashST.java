package algorithm.datastruct;

import java.util.Optional;

public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M;
    private Key[] keys;
    private Value[] vals;

    @SuppressWarnings("unchecked")
    public LinearProbingHashST(int size) {
        M = size;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    private int moduloPlusOne(int i) {return (i + 1) % M; }
    private void remove(int i) {
        keys[i] = null;
        vals[i] = null;
        N--;
    }

    private int hash(Key key) { return (key.hashCode() & 0x7fffffff) % M; }

    private void resize(int size) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(size);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N > M/2) { resize(2 * M); }
        int i;
        for (i = hash(key); keys[i] != null; i = moduloPlusOne(i)) {
            if (key.equals(keys[i])) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Optional<Value> get(Key key) {
        for (int i = hash(key); keys[i] != null; i = moduloPlusOne(i)) {
            if (key.equals(keys[i])) {
                return Optional.of(vals[i]);
            }
        }
        return Optional.empty();
    }

    public boolean contains(Key key) { return get(key).isPresent(); }

    public void delete(Key key) {
        get(key).ifPresent(value -> {
            int i = hash(key);
            while (!key.equals(keys[i])) { i = moduloPlusOne(i); }
            remove(i);
            i = moduloPlusOne(i);
            while (keys[i] != null) {
                Key keyRedo = keys[i];
                Value valRedo = vals[i];
                remove(i);
                put(keyRedo, valRedo);
                i = moduloPlusOne(i);
            }
            if (N > 0 && N < M/8) resize(M / 2);
        });
    }
}
