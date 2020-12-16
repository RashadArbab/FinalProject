import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics ;


/**
 * This class is a helper class for calculating statistics
 * based on a list of integer values. In both Question 1 and
 * Question 2, you will need to calculate the min, max,
 * mean, Q1, Q2, and Q3 of a list of integers, as well as
 * count the frequency of each integer.
 *
 * IMPORTANT: This class is not yet complete, so must be
 * completed by you.
 *
 * To accomplish this, you should fill in the missing parts
 * of this code. Project 3 builds upon Projects 1 and 2
 * here! Finding the top N foods by calorie count is similar
 * to finding the first second and third quartiles. I
 * recommend you consider using a min heap (via the
 * PriorityQueue class) to complete this code. However, you
 * may use whatever means you prefer (sorting, your solution
 * to Project 1, etc.).
 *
 * @author Mark Hancock
 * @author <your name> - you are going to author one of the
 *         methods
 *
 */
public class Statistics {
    private HashMap<Integer, Integer> counts = new HashMap<>();
    private int min;
    private int max;
    private double mean;
    private double q1;
    private double q2;
    private double q3;
    private DescriptiveStatistics stats; 

    /**
     * FOR DFS ONLY 
     * 
     * 
     * Constructs a new Statistics object based on a list of
     * components. This class doesn't store the integers, but
     * instead calculates the min, max, Q1-Q3, and counts
     * the number of occurrences of each value.
     * 
     * It takes the set of components and their sizes, 
     * turns them into an array of doubles
     * uses the array to create an  instance of DescriptiveStatistics 
     *
     * @param list the list of components to collect
     *             statistics from.
     */
    public Statistics(Set<dfsComponent> list) {
        
        double[] doubles = new double[list.size()] ; 
        int counter = 0; 
        for (dfsComponent comps : list) {
        	doubles[counter] = comps.getSize(); 
        	counter++ ; 
        }
        DescriptiveStatistics stats = new DescriptiveStatistics(doubles) ; 
        this.stats = stats ;
        
        System.out.println("Stats size " + doubles.length) ;
        calculateFromList();
    } 
    
    /**
     * FOR BFS ONLY 
     * 
     * accepts an array of doubles and creates instances of DescirptiveStatistics with them
     * 
     * @param list
     */
    public Statistics(double[] list) {
       DescriptiveStatistics stats = new DescriptiveStatistics(list); 
       this.stats = stats; 
       //calculateFromList() ;

    }

    /**
     * This method calculates the min, max, mean, Q1, Q2,
     * and Q3 values from a list of integers, and also
     * counts the frequency (number of occurrences) of each
     * repeated value in the list.
     *
     * @param list a list of unsorted integers, with
     *             possible repeated values.
     */
    private void calculateFromList() {
        /*
         * TODO: complete this method!
         * 
         * By the end of this method, you should have
         * calculated the min, max, mean, Q1, Q2, and Q3
         * values, as well as populated the counts HashMap
         * with the counts of each value.
         */
    	//System.out.println("Min: " + getMin()); 
    	//System.out.println("Max: " + getMax());
    	//System.out.println("Mean: " + getMean()); 
    	//System.out.println("Q1: " + getQ1()); 
    	//System.out.println("Q2: " + getQ2()); 
    	//System.out.println("Q3: " + getQ3()); 
    }

    /**
     * NOT USED I wouldnt dream of sorting an arraylist. way better to put it into a tree map in linearithmic time
     * than to sort in quadratic time. 
     * 
     * Returns a list of ordered unique values based on the
     * original list with duplicates.
     * 
     * 
     *
     * @return a list of ordered unique values based on the
     *         original list with duplicates
     */
    /*
    public List<Integer> getSortedUniqueKeys() {
        ArrayList<Integer> keys = new ArrayList<>(
                counts.keySet());
        Collections.sort(keys);
        return keys;
    }
    */

    /**
     * Not used 
     * 
     * Returns the number of occurrences of a specific
     * value.
     *
     * @param value the value to check for occurrences
     * @return the number of occurrences
     
    public int getCountOf(int value) {
        return counts.get(value);
    }
    */

    //These all use the DescriptiveStats object to find the given values 
    
    /**
     * @return the minimum value from the list
     */
    public int getMin() {
        return (int) stats.getMin(); 
    }

    /**
     * @return the maximum value from the list
     */
    public int getMax() {
        return (int)  stats.getMax() ;
    }

    /**
     * @return the mean value from the list
     */
    public double getMean() {
    	return  stats.getMean(); 
    }

    /**
     * @return the first quartile value from the list
     */
    public double getQ1() {
        return stats.getPercentile(25); 
    }

    /**
     * @return the first quartile value from the list
     */
    public double getQ2() {
        return stats.getPercentile(50) ; 
    }

    /**
     * @return the third quartile value from the list
     */
    public double getQ3() {
        return stats.getPercentile(75); 
    }
}
