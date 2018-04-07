package algorithm.datastruct;

import algorithm.std.StdIn;
import algorithm.std.StdOut;

import java.util.Iterator;

public class Bag<A> implements Iterable<A> {
    private Node first;
    private int length;

    public Bag(){
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

    public void add(A item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        length++;
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

//    @Override
//    public boolean equals(Object obj) {
//        // a quick test to see if the objects are identical
//        if (this == obj) return true;
//
//        // must return false if the explicit parameter is null
//        if (obj == null) return false;
//
//        // they can't be equal if the classes not match
//        if (getClass() != obj.getClass()) return false;
//
//        Bag other = (Bag) obj;
//        为防止值为null, 需要使用Objects.equals(a, b), 在都为null返回true, 只有一个null时返回false
//        return Objects.equals()
//    }

    public static void main(String[] args) {
        Bag<String> s = new Bag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.add(item);
        }
        s.forEach(System.out::println);
        StdOut.println("(" + s.size() + " item in bag)");
    }
}
