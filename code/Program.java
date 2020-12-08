import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Program {
    /**
     * This is the main method of your program.
     * 
     * @param args Command-line arguments
     * @throws IOException If reading from your file had an
     *                     error.
     */
    public static void main(String[] args)
            throws IOException {
        /* TODO: change this source to your actor ID */
        int source = 102; // 102 = Kevin Bacon

        /*
         * TODO: create several sample adjacency lists in
         * text files yourself to test your code before
         * running it on the large Kevin Bacon dataset
         */

//        Scanner scanner = new Scanner(new File("data/adj.txt"));
        Scanner scanner = new Scanner(new File("data/small-adj.txt"));
        IntGraphList graph = IntGraphList.read(scanner,50000);
        scanner.close();

        /*
         * TODO: you can also create custom graphs within
         * the code as follows, but it is probably easier to
         * just use your own test file.
         *
         * Example of creating graphs in code, rather than
         * from a file:
         */
        // Graph graph = new Graph();
        //
        // graph.addNode(1);
        // graph.addNode(2);
        // graph.addNode(3);
        // graph.addNode(4);
        //
        // graph.addEdge(1, 2);
        // graph.addEdge(1, 3);
        // graph.addEdge(1, 4);
        // graph.addEdge(2, 1);
        // graph.addEdge(3, 1);
        // graph.addEdge(3, 4);
        // graph.addEdge(4, 1);
        // graph.addEdge(4, 3);

        DFSTree dfsTree = new DFSTree(graph);
        printComponentReport(dfsTree);

        BFSTree bfsTree = new BFSTree(graph, source);
        printPathReport(bfsTree);

        System.out.println("Source,Fraction,Mean Distance");
        var allSources = new ArrayList<>(graph.getVertices());
        Collections.shuffle(allSources);
        int min = Math.min(allSources.size(), 1000);
        for (int s : allSources.subList(0,min)) {
            if (bfsTree.getDistanceTo(s) >= 1) {
                printSimplePathReport(new BFSTree(graph, s));
            }
        }
    }

    public static void printComponentReport(DFSTree dfsTree) {
        // Report on components
    }

    public static void printPathReport(BFSTree bfsTree) {
        // Report on shortest path from source
    }

    public static void printSimplePathReport(BFSTree bfsTree) {
        // Report on shortest path from source
    }
}
