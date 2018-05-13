package algorithm.datastruct;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(File file) throws FileNotFoundException {
        Scanner fileIn = new Scanner(file);
        V = fileIn.nextInt();
        E = fileIn.nextInt();
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
        while (fileIn.hasNextLine()) {
            String[] args = fileIn.nextLine().split(" ");
            if (args.length >= 3) {
                final int v = Integer.parseInt(args[0]);
                final int w = Integer.parseInt(args[1]);
                final double weight = Double.parseDouble(args[2]);
                DirectedEdge edge = new DirectedEdge(v, w, weight);
                addEdge(edge);
            }
        }
    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                edges.add(e);
            }
        }
        return edges;
    }
}
