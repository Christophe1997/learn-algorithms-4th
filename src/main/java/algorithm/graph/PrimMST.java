package algorithm.graph;

import algorithm.datastruct.*;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) { distTo[v] = Double.POSITIVE_INFINITY; }
        pq = new IndexMinPQ<>();

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) { visit(G, pq.delMin()); }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (!marked[w] && e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) { pq.change(w, distTo[w]); }
                else { pq.insert(w, distTo[w]); }
            }
        }
    }

    public Iterable<Edge> edges() {
        Bag<Edge> edges = new Bag<>();
        for (int v = 1; v < edgeTo.length; v++) { edges.add(edgeTo[v]); }
        return edges;
    }

    public double weight() {
        double weight = 0;
        for (Edge e : edges()) { weight += e.weight(); }
        return weight;
    }
}
