package algorithm.datastruct;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Digraph(Scanner scanner) throws FileNotFoundException {
        this(scanner.nextInt());
        int E = scanner.nextInt();
        for (int i = 0; i < E; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            addEdge(v, w);
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(V + " vertices" + E + " edges\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj[v]) { s.append(w).append(" "); }
            s.append("\n");
        }
        return s.toString();
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) { for (int w: adj(v)) { R.addEdge(w, v); } }
        return R;
    }
}
