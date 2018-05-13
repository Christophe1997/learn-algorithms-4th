package algorithm.datastruct;

import java.util.Optional;

public class RBST<Key extends Comparable<Key>, Value> {
    private final Node nil = new Node(null, null, BLACK);
    private Node root = nil;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Node left, right, parent;
        private Key key;
        private Value val;
        private boolean color;
//        private int N;

        Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
//            this.N = N;
            this.color = color;
        }

        boolean nonLeft() {
            return left == nil;
        }

        boolean nonRight() { return right == nil; }

        boolean isNil() { return this == nil; }

        void flipColors() { color = !color; }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("<key: ").append(key);
            if (parent != null) result.append(", parent: ").append(parent.key);
            result.append(">");
            return result.toString();
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        keys(root, queue);
        return queue;
    }

    private void keys(Node node, Queue<Key> queue) {
        if (node.isNil()) return;
        keys(node.left, queue);
        queue.enqueue(node.key);
        keys(node.right, queue);
    }

    private boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color == RED;
    }


    private void rotateLeft(Node node) {
        Node newNode = node.right;
        node.right = newNode.left;
        if (!newNode.left.isNil()) newNode.left.parent = node;
        newNode.parent = node.parent;
        if (node.parent.isNil()) this.root = newNode;
        else if (node == node.parent.left) node.parent.left = newNode;
        else node.parent.right = newNode;
        newNode.left = node;
        node.parent = newNode;
    }

    private void rotateRight(Node node) {
        Node newNode = node.left;
        node.left = newNode.right;
        if (!newNode.right.isNil()) newNode.right.parent = node;
        newNode.parent = node.parent;
        if (node.parent.isNil()) this.root = newNode;
        else if (node == node.parent.left) node.parent.left = newNode;
        else node.parent.right = newNode;
        newNode.right = node;
        node.parent = newNode;
    }

    private void flipColors(Node node) {
        node.flipColors();
        node.left.flipColors();
        node.right.flipColors();
    }

    public void insert(Key key, Value val) {
        Node parent = nil;
        Node current = root;
        Node insertNode = new Node(key, val, RED);
        while (!current.isNil()) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                current.val = val;
                return;
            }
        }
        insertNode.parent = parent;
        if (parent.isNil()) this.root = insertNode;
        else if (key.compareTo(parent.key) < 0) parent.left = insertNode;
        else parent.right = insertNode;

        insertNode.left = nil;
        insertNode.right = nil;

        insertFixUp(insertNode);
    }

    private void insertFixUp(Node current) {
        Node parent = current.parent;
        Node grandparent;
        while (isRed(parent)) {
            parent = current.parent;
            grandparent = parent.parent;
            if (parent == grandparent.left) {
                Node uncle = grandparent.right;
                if (isRed(uncle)) {
                    flipColors(grandparent);
                    current = grandparent;
                } else {
                    if (current == parent.right) {
                        current = parent;
                        rotateLeft(current);
                    }
                    parent.color = BLACK;
                    grandparent.color = RED;
                    rotateRight(grandparent);
                }
            } else {
                Node uncle = grandparent.left;
                if (isRed(uncle)) {
                    flipColors(grandparent);
                    current = grandparent;
                } else {
                    if (current == parent.left) {
                        current = parent;
                        rotateRight(current);
                    }
                    parent.color = BLACK;
                    grandparent.color = RED;
                    rotateLeft(grandparent);
                }
            }
        }
        root.color = BLACK;
    }

    public Optional<Value> get(Key key) {
        return get(root, key).map(x -> x.val);
    }

    private Optional<Node> get(Node root, Key key) {
        Node current = root;
        int cmp;
        while (!current.isNil()) {
            cmp = key.compareTo(current.key);
            if (cmp > 0) current = current.right;
            else if (cmp < 0) current = current.left;
            else{
//                System.out.println("hit");
                return Optional.of(current);
            }
        }
        return Optional.empty();
    }

    public Optional<Key> min() {
        if (root.isNil()) return Optional.empty();
        return min(root).map(x -> x.key);
    }

    private Optional<Node> min(Node root) {
        if (root == nil) return Optional.empty();
        Node current = root;
        while (!current.left.isNil()) current = current.left;
        return Optional.of(current);
    }

    public Optional<Key> deleteMin() {
        return deleteMin(root).map(x -> x.key);
    }

    private Optional<Node> deleteMin(Node root) {
        Optional<Node> optionalNode = min(root);
        if (optionalNode.isPresent()) {
            Node minNode = optionalNode.get();
            Node current;
            if (minNode.nonRight()) current = nil;
            else current = minNode.right;
            current.parent = minNode.parent;
            if (minNode == minNode.parent.left) { minNode.parent.left = current; }
            else { minNode.parent.right = current; }
            if (this.root == minNode) { this.root = current; }
            deleteFixUp(current);
            return Optional.of(minNode);
        }
        return optionalNode;
    }

    private void deleteFixUp(Node current) {
        while (current != this.root && !isRed(current)) {
            if (current == current.parent.left) {
                Node sibling = current.parent.right;
                if (isRed(sibling)) {
                    sibling.color = BLACK;
                    current.parent.color = RED;
                    rotateLeft(current.parent);
                    sibling = current.parent.right;
                }
                if (!isRed(sibling.left) && !isRed(sibling.right)) {
                    sibling.color = RED;
                    current = current.parent;
                } else {
                    if (!isRed(sibling.right)) {
                        sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = current.parent.right;
                    }
                    sibling.color = current.parent.color;
                    current.parent.color = BLACK;
                    sibling.right.color = BLACK;
                    rotateLeft(current.parent);
                    current = this.root;
                }
            } else {
                Node sibling = current.parent.left;
                if (isRed(sibling)) {
                    sibling.color = BLACK;
                    current.parent.color = RED;
                    rotateRight(current.parent);
                    sibling = current.parent.left;
                }
                if (!isRed(sibling.left) && !isRed(sibling.right)) {
                    sibling.color = RED;
                    current = current.parent;
                } else {
                    if (!isRed(sibling.left)) {
                        sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = current.parent.left;
                    }
                    sibling.color = current.parent.color;
                    current.parent.color = BLACK;
                    sibling.left.color = BLACK;
                    rotateRight(current.parent);
                    current = this.root;
                }
            }
        }
        current.color = BLACK;
        nil.parent = null;
    }

    public void delete(Key key) {
        Optional<Node> optionalNode = get(root, key);
        if (optionalNode.isPresent()) {
            Node deleteNode = optionalNode.get();
            Node parent = deleteNode.parent;
            Optional<Node> optionalTempNode = min(deleteNode.right);
            if (optionalTempNode.isPresent()) {
                Node tempNode = optionalTempNode.get();
                if (this.root == deleteNode) { this.root = tempNode; }
                flipNode(deleteNode, tempNode);
                deleteMin(tempNode.right);
            } else {
                Node current = deleteNode.left;
                if (this.root == deleteNode) { this.root = current; }
                current.parent = parent;
                if (deleteNode == parent.left) parent.left = current;
                else parent.right = current;
                deleteFixUp(current);
            }
        }
    }

    private void resetNil() {
        nil.parent = null;
        nil.left = null;
        nil.right = null;
    }

    private void flipNode(Node lnode, Node rnode) {
        Node tep;
        if (lnode == lnode.parent.left) { lnode.parent.left = rnode; }
        else { lnode.parent.right = rnode; }
        if (rnode == rnode.parent.left) { rnode.parent.left = lnode; }
        else { rnode.parent.right = lnode; }
        tep = lnode.parent;
        lnode.parent = rnode.parent;
        rnode.parent = tep;

        tep = lnode.left;
        lnode.left = rnode.left;
        rnode.left.parent = lnode;
        rnode.left = tep;
        tep.parent = rnode;

        tep = lnode.right;
        lnode.right = rnode.right;
        rnode.right.parent = lnode;
        rnode.right = tep;
        tep.parent = rnode;

        resetNil();
    }


    public static void main(String[] args) {
        RBST<String, String> RBMap = new RBST<>();
        RBMap.insert("a", "b");
        RBMap.insert("b", "e");
        RBMap.insert("c", "d");
        RBMap.insert("d", "h");
        System.out.println("\n" + RBMap.get("a"));
        RBMap.delete("b");
        RBMap.keys().forEach(System.out::print);
    }
}
