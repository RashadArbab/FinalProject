import java.util.HashSet;  

/**
 * this class creates a component object 
 * it allows for easy linking and storing of the size of a component and the vertices inside the component 
 * as well as keeping track of the source of the component 
 * 
 * It allows for simple tracking and would be useful with more functions and trying to find more data 
 * currently most the questions can be answered without building an object to store the data hashmap should be
 * able to store the component key and the size but this I found was easier to mentally keep track of 
 * @author rashad
 *
 */
public class dfsComponent {
	private int size ; 
	private HashSet<Integer> comps ; 
	private int source; 

	
	public dfsComponent() {
		this.size = 1;  
		this.comps = new HashSet<Integer>() ; 
	}
	
	public int getSize() {
		return this.size; 
		
	}
	
	public void setSource(int vertex) {
		this.source = vertex; 
	}
	
	public void increment() {
		size ++ ; 
	}
	
	public void add(int component) {
		comps.add(component);
	}
	
	public HashSet<Integer> getSet(){
		return this.comps; 
	}
	
}
