/**
 * this class is meant for keeping track of all the bfs runs and the relationships that each actor has
 * it creates a popularity object 
 * storing the source of a bfs and the meanDistance to all its connections and the percent of the vectors 
 * the source is connected to within its component
 * @author rashad
 *
 */
public class Popularity {
	
	private int source; 
	private double meanDistance; 
	private double avg; 
	
	
	public Popularity(int source, double meanDistance, double avg) {
		this.source = source; 
		this.meanDistance = meanDistance; 
		this.avg = avg; 
		
	}
	
	public double getMeanDistance() {
		return this.meanDistance ;
	}
	public double getAvg() {
		return this.avg ; 
	}
	public int getSource() {
		return this.source; 
	}
}
