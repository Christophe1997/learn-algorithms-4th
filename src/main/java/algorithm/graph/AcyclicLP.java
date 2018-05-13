package algorithm.graph;

import algorithm.datastruct.DirectedEdge;
import algorithm.datastruct.EdgeWeightedDigraph;
import algorithm.datastruct.Stack;

public class AcyclicLP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) { distTo[v] = Double.NEGATIVE_INFINITY; }
        distTo[s] = 0;
        EdgeweightedTopological top = new EdgeweightedTopological(G);
        for (int v: top.order()) {
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            double newWeight = distTo[v] + e.weight();
            if (distTo[w] < newWeight) {
                distTo[w] = newWeight;
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v) { return distTo[v]; }

    public boolean hasPathTo(int v) { return distTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v) {
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) { path.push(e); }
        return path;
    }
}

