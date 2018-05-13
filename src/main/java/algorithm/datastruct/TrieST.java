package algorithm.datastruct;

public class TrieST<Value> {

    private static final int R = 256;
    private Node root;

    private static class Node {
        private int size;
        private Object val;
        private Node[] next = new Node[R];
    }

    @SuppressWarnings("unchecked")
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) { return null; }
        else { return (Value) x.val; }
    }

    private Node get(Node x, String key, int d) {
        if (x == null) { return null; }
        if (d == key.length()) { return x; }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value val) { root = put(root, key, val, 0); }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) { x = new Node(); }
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.size += 1;
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public int size() { return root.size; }

    private int size(Node x) {
        if (x == null) { return 0; }
        int sum = 0;
        if (x.val != null) { sum += 1; }
        for (char c = 0; c < R; c++) { sum += size(x.next[c]); }
        return sum;
    }

    public Iterable<String> keys() { return keysWithPrefix(""); }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> queue = new Queue<>();
        collect(get(root, pre, 0), pre, queue);
        return queue;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) { return; }
        if (x.val != null) { q.enqueue(pre); }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) { return length; }
        if (x.val != null) { length = d; }
        if (d == s.length()) { return length; }
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    public void delete(String key) { root = delete(root, key, 0); }

    private Node delete(Node x, String key, int d) {
        if (x == null) { return null; }
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
            x.size = size(x);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) { return x; }
        }
        return null;
    }

    public static void main(String[] args) {
        TrieST<Integer> trieST = new TrieST<>();
        trieST.put("asd", 0);
        trieST.put("dvds", 1);
        trieST.put("sdcs", 2);
        trieST.put("fere", 3);
        trieST.put("dsdrge", 4);
        trieST.delete("dvds");
        System.out.println(trieST.size());
    }
}
