package algorithm.datastruct;

public class TST<Value> {
    private Node root;

    private class Node {
        private char c;
        private Node left, mid, right;
        private Value val;
    }

    public void put(String key, Value val) { root = put(root, key, val, 0); }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) { return null; }
        else { return x.val; }
    }

    private Node get(Node cur, String key, int d) {
        if (cur == null) { return null; }
        char c = key.charAt(d);
        if (c < cur.c) { return get(cur.left, key, d); }
        else if (c > cur.c) { return get(cur.right, key, d); }
        else if (d < key.length() - 1) { return get(cur.mid, key, d + 1); }
        else { return cur; }
    }

    private Node put(Node cur, String key, Value val, int d) {
        char c = key.charAt(d);
        if (cur == null) {
            cur = new Node();
            return cur;
        }
        if (c < cur.c) { cur.left = put(cur.left, key, val, d); }
        else if (c > cur.c) { cur.right = put(cur.right, key, val, d); }
        else if (d < key.length() - 1) { cur.mid = put(cur.mid, key, val, d + 1); }
        else { cur.val = val; }
        return cur;
    }
}
