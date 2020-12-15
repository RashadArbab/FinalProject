import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.ArrayList; 

public class BFSTree {
    private IntGraphList graph;
    private int source;
    private HashMap<Integer , Integer> distances = new HashMap<Integer , Integer>() ; 
    private HashMap<Integer, Integer> parents = new HashMap<Integer , Integer>() ;  
    private TreeMap<Integer, Integer> setOfDistances  = new TreeMap<Integer, Integer>(); 
    

    public BFSTree(IntGraphList graph, int source) {
        this.graph = graph;
        this.source = source;
        
        initialize() ; 
        
        getTable(); 

      
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
    
    

    
    public void getTable() {
    	 
   
         for(Integer dist : distances.values()) {
        	 if(setOfDistances.containsKey(dist)) {
        		 int value = setOfDistances.get(dist); 
        		 setOfDistances.replace(dist, value + 1); 
        	 }
        	 else {
        		 setOfDistances.put(dist, 1); 
        	 }
         }
         System.out.println("| Distance from source | Number of Actors |");
         System.out.println("| --- | --- |");
         for(Integer key : setOfDistances.keySet()) {
        	 System.out.println("| " + key + " | " + setOfDistances.get(key) + " |");
         }
    }
    
    public double[] getDoubleDistances() {
    	ArrayList<Integer> ints  = new ArrayList<Integer>(); 
    	int counter = 0; 
    	for (Integer dist : distances.values() ) {
    		if (dist >= 0) {
    			ints.add(dist); 
    			
    		}
    	}
    	
    	double[]  doubles = new double[ints.size()]; 
    
    	for(Integer num : ints) {
    		doubles[counter] = num; 
    		counter ++;
    	}
    	
    	return doubles; 
    	
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
