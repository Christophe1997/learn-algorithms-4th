package algorithm.datastruct;

import java.util.Optional;

public class SequentialSearchST<Key, Value> {
    private Node first;
    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Optional<Value> get(Key key) {
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) return Optional.of(current.val);
        }
        return Optional.empty();
    }

    public void put(Key key, Value val) {
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                current.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }
    public void delete(Key key) {
        Node parent = first;
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                if (current == first) {
                    first = current.next;
                } else {
                    parent.next = current.next;
                }
                return;
            }
            parent = current;
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node tep = first; tep != null; tep = tep.next) {
            queue.enqueue(tep.key);
        }
        return queue;
    }
}
