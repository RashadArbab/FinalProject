import java.util.HashSet;  
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
