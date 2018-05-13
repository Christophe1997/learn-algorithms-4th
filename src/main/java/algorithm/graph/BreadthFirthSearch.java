package algorithm.graph;

import algorithm.datastruct.Graph;
import algorithm.datastruct.Queue;

public class BreadthFirthSearch {
    private boolean[] marked;
    private int count;

    public BreadthFirthSearch(Graph G, int s) {
        marked = new boolean[G.V()];
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
                    queue.enqueue(w);
                    marked[w] = true;
                    count++;
                }
            }
        }
    }

    public boolean marked(int v) { return marked[v]; }

    public int count() { return count; }
}
