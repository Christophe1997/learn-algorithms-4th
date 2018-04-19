package algorithm.datastruct;

public class SET<Key> {
    private Key[] universe;
    int M;
    private LinearProbingHashST<Key, Nil> set;
    private Nil nil = new Nil();
    private class Nil {}

    public SET() {
        M = 16;
        set = new LinearProbingHashST<>(M);
    }

    public SET(Key[] universe) {
        this.universe = universe;
        this.M = universe.length;
        this.set = new LinearProbingHashST<>(this.M);
    }

    public void add(Key key) {
        set.put(key, nil);
    }

    public boolean contains(Key key) {
        return set.get(key).isPresent();
    }

    public SET<Key> complement() {
        SET<Key> newSet = new SET<>(universe);
        for (Key key : universe) {
            if (!contains(key)) { newSet.add(key); }
        }
        return newSet;
    }

    public Iterable<Key> keys() { return set.keys(); }

    public void union(SET<Key> other) {
        other.keys().forEach(key -> {
            if (!contains(key)) {
                add(key);
            }
        });
    }

    public void intersection(SET<Key> other) {
        keys().forEach(key -> {
            if (!other.contains(key)) {
                delete(key);
            }
        });
    }

    public void delete(Key key) { set.delete(key); }
}
