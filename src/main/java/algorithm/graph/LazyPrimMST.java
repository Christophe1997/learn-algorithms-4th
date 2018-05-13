package algorithm.graph;

import algorithm.datastruct.Edge;
import algorithm.datastruct.EdgeWeightedGraph;
import algorithm.datastruct.MinPQ;
import algorithm.datastruct.Queue;

import java.io.File;
import java.io.FileNotFoundException;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<>();
        marked = new boolean[G.V()];
        mst = new Queue<>();
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!marked[v] || !marked[w]) {
                mst.enqueue(e);
                if (!marked[v]) { visit(G, v); }
                if (!marked[w]) { visit(G, w); }
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) { pq.insert(e); }
        }
    }

    public Iterable<Edge> edges() { return mst; }

    public double weight() {
        double weight = 0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(file);
        LazyPrimMST mst = new LazyPrimMST(G);
        mst.edges().forEach(System.out::println);
        System.out.println(mst.weight());
    }
}
