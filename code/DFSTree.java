import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack; 
import java.util.TreeMap; 

public class DFSTree {
    // TODO: consider what fields you might want
    // Hint: probably not (just) a graph and source vertex
    
    // TODO: create the DFSTree constructor
    
    // TODO: create the isConnected method
    
	/**
	 * this is a set of all the vertices that have been visited, by the end of the run
	 * it will have visited all the vertices in the graph 
	 */
    private Set<Integer> visited = new HashSet<Integer>() ; 
    /**
     * This is a set of components it houses custom dfsComonent Objects to neatly track all the components
     */
    private Set<dfsComponent> components = new HashSet<dfsComponent>() ; 
    /**
     * This is the component map it is necessary for printing the table in an orderly manner since hashsets are not 
     * ordered 
     */
    private Map<Integer, Integer> compMap = new TreeMap<Integer, Integer>() ; 
    private IntGraphList graph ; 
    private int source ; 
    //private int components; 
    
    /**
     * DFS search takes the graph and the source to search from 
     * @param graph
     * @param source
     */
    public DFSTree(IntGraphList graph , int source){
        this.graph = graph; 
        this.source = source; 
        
        // this runs the initialize and it runs the dfs on the first element which I tend to use as my source actor
        // but it could be any actor at all. 
        dfsInitialize(); 
        
        // this method then runs the subsequent dfs to cover all the elements and all the subsequent components
        allComponents() ; 
    }
 
    /**
     * @return a set of dfsComponent
     */
    public Set<dfsComponent> getComponents() {
    	return this.components; 
    }
    
    /**
     * runs the first dfs starting from the source but can be any arbitrary vertex 
     */
    public void dfsInitialize() {
    	visited.add(source); 
    	//creates the first component and then adds the source of the component and runs dfsVisit
    	dfsComponent comp = new dfsComponent() ; 
    	comp.setSource(source);
    	components.add(comp); 
    	
    	DFSVisit(source , comp); 
    
    }
    
    /**
     * This is mostly just the standard DFSVisit the differnce is that 
     * it accepts a component parameter and then adds all the vertices that it visits to the component 
     * as well as increasing the component size to be the same as the number of vertices in the component
     * @param vertex
     * @param comp
     */
    private void DFSVisit(int vertex , dfsComponent comp) {
    	comp.add(vertex);
    	
    	Stack<Integer> verticesToVisit = new Stack<Integer>() ; 
    	verticesToVisit.push(vertex) ; 
    	visited.add(vertex); 
    	while(!verticesToVisit.isEmpty()) {
    		vertex =  verticesToVisit.pop(); 
    		for (int adj : graph.getAdjacencyList(vertex)) {
    			if (!visited.contains(adj)) {
    				verticesToVisit.push(vertex); 
    				verticesToVisit.push(adj) ;
    				visited.add(adj); 
    				comp.increment(); 
    				break ; 
    				
    			}
    		}
    	}
    } 
    
   
    /** 
     * @return size of components set
     */
    public int numComponents() {
    	return this.components.size();  
    }
    
    /**
     * iterates through all the vertices, if a vertex has not been visited before,
     * it will set up a new compnent for the vertex then start 
     * the dfs using that vertex as the source and seeing how large the component is 
     */
    public void allComponents() {
    	for (int vertex : graph.getVertices()) {
    		if (!visited.contains(vertex)) {
    			dfsComponent comp = new dfsComponent() ; 
    			comp.setSource(vertex);
    			components.add(comp); 
    			DFSVisit(vertex , comp); 
    		}
    	}
    }
    
    /**
     * this method is for turning the hashSet of dfsComponents into a treemap that uses the size of the component
     * as the key and the frequency of that size as the value 
     */
    public void setupCompMap() {
    	for (dfsComponent  comp : components) {  
        	int key = comp.getSize() ; 
        	if (compMap.containsKey(key)) {
        		
        		compMap.replace(key, compMap.get(key)+1);
        		
        	}
        	else {
        		compMap.put(key , 1) ; 
        		
        	}
       }
    }
    
    /**
     * Produces the Table that displays the size of components and the number of components of the size 
     */
    public void getTable() {
    	
    	System.out.println(components.size()) ; 
        
        System.out.println("| Size of Component | Number of components of this size |");
        System.out.println("| --- | --- |");
        
        setupCompMap() ; 
        
      
        
       
       
       for(int key : compMap.keySet()) {
        	
        	System.out.println("| " + key + " | " + compMap.get(key) + " |" ) ; 
        	
       }
        	
       
        
    }

    public IntGraphList getGraph() {
        return graph;
    }
    


 

   
    
 
}
