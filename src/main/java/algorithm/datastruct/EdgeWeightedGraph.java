package algorithm.datastruct;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class EdgeWeightedGraph {

    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(File file) throws FileNotFoundException {
        Scanner fileIn = new Scanner(file);
        V = fileIn.nextInt();
        E = fileIn.nextInt();
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
        while (fileIn.hasNextLine()) {
            String[] args = fileIn.nextLine().split(" ");
            if (args.length >= 3) {
                final int v = Integer.parseInt(args[0]);
                final int w = Integer.parseInt(args[1]);
                final double weight = Double.parseDouble(args[2]);
                Edge edge = new Edge(v, w, weight);
                addEdge(edge);
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) { if (e.other(v) > v) { b.add(e); } }
        }
        return b;
    }
}
