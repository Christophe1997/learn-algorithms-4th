package algorithm.graph;

import algorithm.datastruct.Graph;
import algorithm.datastruct.Stack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DepthFirthPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirthPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) { return null; }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Graph G = new Graph(new Scanner(file));
        int s = Integer.parseInt(args[1]);
        DepthFirthPaths search = new DepthFirthPaths(G, s);
        for (int v = 0; v < G.V(); v++) {
            System.out.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)) {
                for (int x : search.pathTo(v)) {
                    if (x == s) { System.out.print(x); }
                    else { System.out.print("-" + x); }
                }
            }
            System.out.println();
        }
    }
}
