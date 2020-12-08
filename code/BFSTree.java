public class BFSTree {
    private IntGraphList graph;
    private int source;

    public BFSTree(IntGraphList graph, int source) {
        this.graph = graph;
        this.source = source;

        /*
         * TODO: complete this method with the breadth-first
         * search algorithm, modifying it to be able to
         * provide information about distances and parents.
         */
    }

    public int getDistanceTo(int v) {
        // TODO: complete this method
        return 0;
    }

    public int getParent(int v) {
        // TODO: complete this method
        return 0;
    }

    public IntGraphList getGraph() {
        return graph;
    }

    public int getSource() {
        return source;
    }
}
