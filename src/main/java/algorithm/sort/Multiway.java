package algorithm.sort;

import algorithm.datastruct.IndexMinPQ;
import algorithm.std.In;

import java.util.ArrayList;

public class Multiway {

    public static ArrayList<String> merge(In[] streams) {
        int N = streams.length;
        ArrayList<String> result = new ArrayList<>();
        IndexMinPQ<String> minPQ = new IndexMinPQ<>(N);
        for (int i = 0; i < N; i++ ) {
            if (!streams[i].isEmpty()) minPQ.insert(i, streams[i].readString());
        }
        while (!minPQ.isEmpty()) {
            result.add(minPQ.min());
            int i = minPQ.delMin();
            if (!streams[i].isEmpty()) minPQ.insert(i, streams[i].readString());
        }
        return result;
    }

    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++) streams[i] = new In(args[i]);
        ArrayList<String> result = merge(streams);
        System.out.println(result.toString());
    }
}
