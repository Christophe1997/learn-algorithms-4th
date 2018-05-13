package algorithm.datastruct;

import algorithm.std.StdIn;
import algorithm.std.StdOut;

import java.util.Iterator;

public class Stack<A> implements Iterable<A> {
    private Node first;
    private int length;

    public Stack(){
        first = null;
        length = 0;
    }

    private class Node {
        A item;
        Node next;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public void push(A item) {
        Node oldTop = first;
        first = new Node();
        first.item = item;
        first.next = oldTop;
        length++;
    }

    public A pop() {
        A item = first.item;
        first = first.next;
        length--;
        return item;
    }

    public Iterator<A> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<A> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
        }

        @Override
        public A next() {
            A item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
