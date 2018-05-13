package algorithm.graph;

import algorithm.datastruct.EdgeWeightedDigraph;

public class EdgeweightedTopological {
    private Iterable<Integer> order;

    public EdgeweightedTopological(EdgeWeightedDigraph G) {
        EdgeWeightedCycleFinder cycleFinder = new EdgeWeightedCycleFinder(G);
        if (!cycleFinder.hasCycle()) {
            EdgeweightedDepthFirthOrder dfs = new EdgeweightedDepthFirthOrder(G);
            order = dfs.reverstPost();
        } else {
            cycleFinder.cycle().forEach(System.out::println);
        }
    }

    public Iterable<Integer> order() { return order; }
    public boolean isDAG() { return order != null; }
}
