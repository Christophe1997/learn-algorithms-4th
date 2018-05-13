package algorithm.graph;

import algorithm.datastruct.DirectedEdge;
import algorithm.datastruct.EdgeWeightedDigraph;
import algorithm.datastruct.Queue;
import algorithm.datastruct.Stack;

public class EdgeweightedDepthFirthOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reverstPost;

    public EdgeweightedDepthFirthOrder(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reverstPost = new Stack<>();
        for (int v = 0; v < G.V(); v++) { if (!marked[v]) { dfs(G, v); } }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (DirectedEdge e : G.adj(v)) { if (!marked[e.to()]) { dfs(G, e.to()); } }
        post.enqueue(v);
        reverstPost.push(v);
    }

    public Iterable<Integer> pre() { return pre; }
    public Iterable<Integer> post() { return post; }
    public Iterable<Integer> reverstPost() { return reverstPost; }

}
