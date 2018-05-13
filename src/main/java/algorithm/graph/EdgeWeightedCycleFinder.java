package algorithm.graph;

import algorithm.datastruct.DirectedEdge;
import algorithm.datastruct.EdgeWeightedDigraph;
import algorithm.datastruct.Stack;

public class EdgeWeightedCycleFinder {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) { if (!marked[v]) { dfs(G, v); } }
    }

    public void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            if (this.hasCycle()) {
                return;
            } else if (!marked[e.to()]) {
                edgeTo[e.to()] = v;
                dfs(G, e.to());
            } else if (onStack[e.to()]) {
                cycle = new Stack<>();
                for (int x = v; x !=e.to(); x = edgeTo[x]) { cycle.push(x); }
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() { return cycle != null; }

    public Iterable<Integer> cycle() { return cycle; }
}
