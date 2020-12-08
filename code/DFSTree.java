import java.util.Collections;
import java.util.List;

public class DFSTree {
    private IntGraphList graph;

    public DFSTree(IntGraphList graph) {
        this.graph = graph;

        /*
         * TODO: complete this method with the depth-first
         * search algorithm, modifying it to be able to
         * provide information about parents and component
         * sizes
         */
    }

    public IntGraphList getGraph() {
        return graph;
    }

    public int getParent(int v) {
        // TODO: complete this method
        return 0;
    }

    public List<Integer> getComponentSizes() {
        // TODO: complete this method
        return Collections.emptyList();
    }
}
