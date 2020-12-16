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
    
    /**
     * same bfs search as before 
     * @param graph
     * @param source
     */
    public BFSTree(IntGraphList graph, int source) {
        this.graph = graph;
        this.source = source;
        
        initialize() ; 
        
        //getTable(); 

      
    }
    

    /**
     * initializes the bfs and runs the search itself so when an instance of bfsTree is created 
     * it automatically searches and has the important information ready for use. 
     */
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
    
    

    /**
     * prints the table housing distance from source and the number of actors 
     * that are that distance from the source 
     * 
     */
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
    
    /**
     * for the apache commons DescriptiveStatistics to work it needs an array of doubles not a set of integers 
     * so this method takes the set of distance.values 
     * and it puts them into an array of doubles which the DescriptiveStatistics class can read
     * @return an array fo doubles from distances.values
     */
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

    /**
     * useless method 
    public int getParent(int v) {
        // TODO: complete this method
        return parents.get(v); 
        
    }
	*/ 
    public IntGraphList getGraph() {
        return graph;
    }
    
    /**
     * this method is for finding the percent of your component that is within 6 degrees of freedom from you 
     * if greater than 0.5 you are within 6 degrees of freedom to everyone else in your component 
     * @return
     */
    public double getBacon() {
    	double num = 0 ; 
    	double den = distances.keySet().size(); 
    	
    	for(int value : distances.values()) {
    		if (value >= 0 && value <=7) {
    			num ++; 
    		}
    	}
    	//System.out.println(num/den); 
    	return num/den ; 
    }
    
    /**
     * @return the distance from the source to kevin bacon 
     */
    public int distanceToBacon() {
    	return getDistanceTo(102)  ; 
    }

    public int getSource() {
        return source;
    }
}
