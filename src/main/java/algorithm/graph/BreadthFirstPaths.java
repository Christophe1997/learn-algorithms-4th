package algorithm.graph;

import algorithm.datastruct.Graph;
import algorithm.datastruct.Queue;
import algorithm.datastruct.Stack;

public class BreadthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int v) {
        Queue<Integer> queue = new Queue<>();
        marked[v] = true;
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            int x = queue.dequeue();
            for (int w : G.adj(x)) {
                if (!marked[w]) {
                    edgeTo[w] = x;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) { return marked[v]; }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) { return null; }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
