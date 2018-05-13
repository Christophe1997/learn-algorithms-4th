package algorithm.graph;

import algorithm.datastruct.Digraph;
import algorithm.datastruct.LinearProbingHashST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

public class SymbolDigraph {
    private LinearProbingHashST<String, Integer> st;
    private String[] keys;
    private Digraph G;

    public SymbolDigraph(File file, String sp) throws FileNotFoundException {
        st = new LinearProbingHashST<>(50);

        Scanner fileIn = new Scanner(file);
        while (fileIn.hasNextLine()) {
            String[] a = fileIn.nextLine().split(sp);
            for (String symbol : a) { if (!st.contains(symbol)) { st.put(symbol, st.size()); } }
        }
        keys = new String[st.size()];
        for (String name : st.keys()) { st.get(name).ifPresent(size -> keys[size] = name); }
        G = new Digraph(st.size());

        fileIn = new Scanner(file);
        while (fileIn.hasNextLine()) {
            String[] a = fileIn.nextLine().split(sp);
            st.get(a[0]).ifPresent(v -> {
                for (int i = 1; i < a.length; i++) { st.get(a[i]).ifPresent(w -> G.addEdge(v, w)); }
            });
        }
    }

    public boolean contains(String s) { return st.contains(s); }

    public Optional<Integer> index(String s) { return st.get(s); }

    public String name(int v) { return keys[v]; }

    public Digraph G() { return G; }
}
