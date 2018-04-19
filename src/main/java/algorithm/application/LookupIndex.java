package algorithm.application;

import algorithm.datastruct.LinearProbingHashST;
import algorithm.datastruct.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;

public class LookupIndex {
    public static void main(String[] args) throws FileNotFoundException {
        File data = new File(args[0]);
        Scanner in = new Scanner(data);
        String sp = args[1];
        LinearProbingHashST<String, Queue<String>> st = new LinearProbingHashST<>(16);
        LinearProbingHashST<String, Queue<String>> ts = new LinearProbingHashST<>(16);
        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(sp);
            String key = a[0];
            for (int i = 1; i < a.length; i++) {
                String val = a[i];
                if (!st.contains(key)) { st.put(key, new Queue<>()); }
                if (!ts.contains(val)) { ts.put(val, new Queue<>()); }
                st.get(key).ifPresent(queue -> queue.enqueue(val));
                ts.get(val).ifPresent(queue -> queue.enqueue(key));
            }
        }
        Scanner stdIn = new Scanner(System.in);
        System.out.println("What are you querying for?");
        while (stdIn.hasNext()) {
            Consumer<? super String> print = x -> System.out.println(" " + x);
            String queue = stdIn.next();
            st.get(queue).ifPresent(q -> q.forEach(print));
            ts.get(queue).ifPresent(q -> q.forEach(print));
            System.out.println("anything else?");
        }
    }
}
