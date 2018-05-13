package algorithm.graph;

import algorithm.datastruct.Digraph;
import algorithm.datastruct.Queue;
import algorithm.datastruct.Stack;

public class DepthFirthOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reverstPost;

    public DepthFirthOrder(Digraph G) {
        marked = new boolean[G.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reverstPost = new Stack<>();
        for (int v = 0; v < G.V(); v++) { if (!marked[v]) { dfs(G, v); } }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (int w : G.adj(v)) { if (!marked[w]) { dfs(G, w); } }
        post.enqueue(v);
        reverstPost.push(v);
    }

    public Iterable<Integer> pre() { return pre; }
    public Iterable<Integer> post() { return post; }
    public Iterable<Integer> reverstPost() { return reverstPost; }
}
