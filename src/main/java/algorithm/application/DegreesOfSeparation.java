package algorithm.application;

import algorithm.datastruct.Graph;
import algorithm.graph.BreadthFirstPaths;
import algorithm.graph.SymbolGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DegreesOfSeparation {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        String sp = args[1];
        SymbolGraph sg = new SymbolGraph(file, sp);
        Graph G = sg.G();
        String source = args[2];
        sg.index(source).ifPresent(idx -> {
            BreadthFirstPaths bfs = new BreadthFirstPaths(G, idx);
            Scanner stdIn = new Scanner(System.in);
            System.out.println("what are you qureying for?");
            while (stdIn.hasNextLine()) {
                String query = stdIn.nextLine();
                sg.index(query).ifPresent(v -> {
                    if (bfs.hasPathTo(v)) {
                        for (int w : bfs.pathTo(v)) { System.out.println("   " + sg.name(w)); }
                        System.out.println("anything else?");
                    } else {
                        System.out.println("not connected");
                    }
                });
            }
        });
    }
}
