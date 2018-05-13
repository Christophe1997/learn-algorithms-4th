package algorithm.graph;

import algorithm.application.WeightedQuickUnionUF;
import algorithm.datastruct.Edge;
import algorithm.datastruct.EdgeWeightedGraph;
import algorithm.datastruct.MinPQ;
import algorithm.datastruct.Queue;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) { pq.insert(e); }
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(w, v);
                mst.enqueue(e);
            }
        }
    }

    public Iterable<Edge> edges() { return mst; }

    public double weight() {
        double weight = 0;
        for (Edge e : edges()) { weight += e.weight(); }
        return weight;
    }
}
