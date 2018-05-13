package algorithm.application;

import algorithm.datastruct.DirectedEdge;
import algorithm.datastruct.EdgeWeightedDigraph;
import algorithm.graph.AcyclicLP;

import java.util.Scanner;

public class CMP {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int N = stdIn.nextInt();
        stdIn.nextLine();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * N + 2);
        int s = 2 * N;
        int t = 2 * N + 1;
        for (int i = 0; i < N; i++) {
            String[] line = stdIn.nextLine().split("\\s+");
            double duration = Double.parseDouble(line[0]);
            G.addEdge(new DirectedEdge(i, i + N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i + N, t, 0.0));
            for (int j = 1; j < line.length; j++) {
                int successor = Integer.parseInt(line[j]);
                G.addEdge(new DirectedEdge(i + N, successor, 0.0));
            }
        }
        AcyclicLP lp = new AcyclicLP(G, s);
        System.out.println("Start times:");
        for (int i = 0; i < N; i++) { System.out.printf("%4d: %5.1f\n", i, lp.distTo(i)); }
        System.out.printf("Finish time: %5.1f\n", lp.distTo(t));
    }
}
