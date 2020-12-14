import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFSTree {
    private IntGraphList graph;
    private int source;
    private HashMap<Integer , Integer> distances = new HashMap<Integer , Integer>() ; 
    private HashMap<Integer, Integer> parents = new HashMap<Integer , Integer>() ;  
    

    public BFSTree(IntGraphList graph, int source) {
        this.graph = graph;
        this.source = source;
        
        initialize() ; 

        /*
         * TODO: complete this method with the breadth-first
         * search algorithm, modifying it to be able to
         * provide information about distances and parents.
         */
    }
    

  
    private void initialize() {
    	
    	for(Integer node : graph.getVertices()) {
    		distances.put(node, -1); 
    		parents.put(node, null); 
    		
    	}
    	
        Queue<Integer> q = new LinkedList<Integer>();
        distances.put(source, 0); 
        q.add(source) ; 
        
        
        while (!q.isEmpty()) {
        	//set source to u, 
        	//remove u from the queue 
        	//go through every connection that u has
        	int u = q.remove(); 
        	for(int v : graph.getAdjacencyList(u)) {
        		if(distances.get(v) == -1) {
        			distances.put(v , distances.get(u) +1) ; 
        			parents.put(v, u); 
        			q.add(v); 
        		}
        		
        		
        	}
        	
        }
        
    }
    
    

    
    

    public int getDistanceTo(int v) {
        // TODO: complete this method
        return distances.get(v);
    }

    public int getParent(int v) {
        // TODO: complete this method
        return parents.get(v); 
        
    }

    public IntGraphList getGraph() {
        return graph;
    }

    public int getSource() {
        return source;
    }
}
