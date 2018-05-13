package algorithm.graph;

import algorithm.datastruct.Digraph;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * only topological while graph is DAG
 */
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycle = new DirectedCycle(G);
        if (!cycle.hasCycle()) {
            DepthFirthOrder dfs = new DepthFirthOrder(G);
            order = dfs.reverstPost();
        }
    }

    public Iterable<Integer> order() { return order; }
    public boolean isDAG() { return order != null; }

    public static void main(String[] args) throws FileNotFoundException {
        String filename = args[0];
        String sp = args[1];
        SymbolDigraph sg = new SymbolDigraph(new File(filename), sp);
        Topological top = new Topological(sg.G());
        top.order.forEach(v -> System.out.println(sg.name(v)));
    }
}
