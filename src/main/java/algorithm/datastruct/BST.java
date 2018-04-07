package algorithm.datastruct;

import java.util.Optional;
import java.util.function.Function;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Node left, right;
        private Key key;
        private Value val;
        private int N;

        Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }

        boolean nonLeft() {
            return left == null;
        }

        boolean nonRight() {
            return right == null;
        }
    }

    private Function<Node, Key> getKey = node -> node.key;

    private <T> Optional<T> option(Node node, Function<Node, T> f) {
        if (node == null) return Optional.empty();
        return Optional.of(f.apply(node));
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.N;
    }

    private int resize(Node node) {
        return size(node.left) + size(node.right) + 1;
    }

    public Optional<Value> get(Key key) {
        return Optional.of(get(root, key));
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) get(node.right, key);
        else if (cmp < 0) get(node.left, key);
        return node.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null) return new Node(key, val, 1);
        int cmp = key.compareTo(node.key);
        if (cmp > 0) node.right = put(node.right, key, val);
        else if (cmp < 0) node.left = put(node.left, key, val);
        else node.val = val;
        node.N = resize(node);
        return node;
    }

    public Optional<Key> min() {
        return option(min(root), getKey);
    }

    private Node min(Node node) {
        if (root == null) return null;
        if (node.nonLeft()) return node;
        return min(node.left);
    }

    private Optional<Key> max() {
        return option(max(root), getKey);
    }

    private Node max(Node node) {
        if (node == null) return null;
        if (node.nonRight()) return node;
        return max(node.right);
    }

    public Optional<Key> floor(Key key) {
        return option(floor(root, key), getKey);
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);

        if (cmp < 0) floor(node.left, key);
        else if (cmp == 0) return node;

        if (node.nonRight()) return node;
        else return floor(node.right, key);
//        Node t = floor(node.right, key);
//        if (t != null) return t;
//        else return node;
    }

    public Optional<Key> ceiling(Key key) {
        return option(ceiling(root, key), getKey);
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);

        if (cmp > 0) floor(node.right, key);
        else if (cmp == 0) return node;

        if (node.nonLeft()) return node;
        else return floor(node.left, key);
    }

    public Optional<Key> select(int k) {
        return option(select(root, k), getKey);
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int t = size(node);
        if (k > t) return select(node.right, k - t - 1);
        else if (k < t) return select(node.left, k);
        return node;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    ;

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(node.left, key);
        else if (cmp > 0) return 1 + size(node.left) + rank(node.right, key);
        return size(node.left);
    }

    public void deleteMin() {
        if (root != null) root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.nonLeft()) return node.right;
        node.left = deleteMin(node.left);
        node.N = resize(node);
        return node;
    }

    public void deleteMax() {
        if (root != null) root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node.nonRight()) return node.left;
        node.right = deleteMax(node.right);
        node.N = resize(node);
        return node;
    }

    public void delete(Key key) {

    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp > 0) node.right = delete(node.right, key);
        else if (cmp < 0) node.left = delete(node.left, key);
        else {
            if (node.nonLeft()) return node.right;
            if (node.nonRight()) return node.left;
            Node t = node;
            node = min(node.right);
            assert node != null;
            node.right = deleteMin(node.right);
            node.left = t.left;
        }
        node.N = resize(node);
        return node;
    }

    public Iterable<Key> keys() {
        Optional<Key> optionalMin = min(), optionalMax = max();
        if (optionalMax.isPresent() && optionalMin.isPresent()) return keys(optionalMin.get(), optionalMax.get());
        else return new Queue<>();
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue, Key hi, Key lo) {
        if (node == null) return;
        int cmplo = lo.compareTo(node.key);
        int cmphi = hi.compareTo(node.key);
        if (cmplo < 0) keys(node.left, queue, hi, lo);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(node.key);
        if (cmphi > 0) keys(node.right, queue, hi, lo);

    }
}
