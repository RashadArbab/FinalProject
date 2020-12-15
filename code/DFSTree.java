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
    

    private Set<Integer> visited = new HashSet<Integer>() ; 
    
    private Set<dfsComponent> components = new HashSet<dfsComponent>() ; 
    private Map<Integer, Integer> compMap = new TreeMap<Integer, Integer>() ; 
    private IntGraphList graph ; 
    private int source ; 
    //private int components; 
    
    public DFSTree(IntGraphList graph , int source){
        this.graph = graph; 
        this.source = source; 
        
        dfsInitialize(); 
        allComponents() ; 
    }
    /*
    private void dfsInitialize() {
    	visited.add(source) ; 
        parents.put(source, null) ;
        
        DFSVisit(source); 
    }
    
    private void DFSVisit(int u) {
    	visited.add(u);
    	for(int vertex : graph.getAdjacencyList(u))
    	if (!visited.contains(vertex)) {
    		parents.put(vertex, u); 
    		DFSVisit(vertex); 
    	}
    }
    */ 
    
    public Set<dfsComponent> getComponents() {
    	return this.components; 
    }
    
    public void dfsInitialize() {
    	visited.add(source); 
    	dfsComponent comp = new dfsComponent() ; 
    	comp.setSource(source);
    	components.add(comp); 
    	
    	DFSVisit(source , comp); 
    
    }
    
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
    
    
    public boolean isConnected(int vertex) {
    	return visited.contains(vertex); 
    }
    
    public int numComponents() {
    	return this.components.size();  
    }
    
    
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
