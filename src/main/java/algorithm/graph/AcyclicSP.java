package algorithm.graph;

import algorithm.datastruct.DirectedEdge;
import algorithm.datastruct.EdgeWeightedDigraph;
import algorithm.datastruct.Stack;

import java.io.File;
import java.io.FileNotFoundException;

public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int v = 0; v < G.V(); v++) { distTo[v] = Double.POSITIVE_INFINITY; }
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
            if (distTo[w] > newWeight) {
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

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(file);
        int s = Integer.parseInt(args[1]);
        AcyclicSP sp = new AcyclicSP(G, s);

        for (int t = 0; t < G.V(); t++) {
            System.out.print(s + " to " + t);
            System.out.printf(" (%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (DirectedEdge e : sp.pathTo(t)) { System.out.print(e + "   "); }
            }
            System.out.println();
        }
    }
}
