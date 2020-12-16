import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics; 

public class Program {
    /**
     * This is the main method of your program.
     * 
     * @param args Command-line arguments
     * @throws IOException If reading from your file had an
     *                     error.
     */
	
	/**
	 * a simple counter that keeps track of how many times printPathSimpleReport has run
	 */
	private static int runs = 0; 
	
	/**
	 * this is a list of of Populatiry objects 
	 */
    private static ArrayList<Popularity> popularityContest = new ArrayList<Popularity>() ; 
    
    public static void main(String[] args)
            throws IOException {
        /* TODO: change this source to your actor ID */
        int source = 286726 ; // 102 = Kevin Bacon //286726

        /*
         * TODO: create several sample adjacency lists in
         * text files yourself to test your code before
         * running it on the large Kevin Bacon dataset
         */

        //Scanner scanner = new Scanner(new File("data/small-adj.txt"));
        Scanner scanner = new Scanner(new File("data/adj.txt"));
        IntGraphList graph = IntGraphList.read(scanner,50000);
        scanner.close();
        
        //System.out.println("Here is the vertices");
        //System.out.println(graph.getVertices()) ; 
        //System.out.println("here are the edges"); 
        //System.out.println(graph.getAdjacencyList(0)); 
        /*
         * TODO: you can also create custom graphs within
         * the code as follows, but it is probably easier to
         * just use your own test file.
         *
         * Example of creating graphs in code, rather than
         * from a file:
         */
        /*
         Graph graph = new Graph();
        
         graph.addNode(1);
         graph.addNode(2);
         graph.addNode(3);
         graph.addNode(4);
        
         graph.addEdge(1, 2);
         graph.addEdge(1, 3);
         graph.addEdge(1, 4);
         graph.addEdge(2, 1);
         graph.addEdge(3, 1);
         graph.addEdge(3, 4);
         graph.addEdge(4, 1);
         graph.addEdge(4, 3);
		*/ 
        //DFSTree dfsTree = new DFSTree(graph ,source);
        //printComponentReport(dfsTree);
        
        
        BFSTree bfsTree = new BFSTree(graph, source);
        //printPathReport(bfsTree);
        //printSimplePathReport(bfsTree); 

        System.out.println("Source,Fraction,Mean Distance");
        var allSources = new ArrayList<>(graph.getVertices());
        Collections.shuffle(allSources);
        int min = Math.min(allSources.size(), 1000);
        for (int s : allSources.subList(0, min)) {
            if (bfsTree.getDistanceTo(s) >= 1) {
                printSimplePathReport(new BFSTree(graph, s));
            }
        }
        printPathReport(bfsTree);
        special(); 
        
        /*
        System.out.println("ran: " + runs);
        System.out.println("Fraction " + (average/runs)); 
        System.out.println("MeanDistance " + (meanDistance/runs)); 
        */
        
        //System.out.println("Path Report");
        //printPathReport(bfsTree); 
         
        //System.out.println("Number of components:" +  dfsTree.numComponents()); 

    }

    public static void printComponentReport(DFSTree dfsTree) {
        // Report on components
    	dfsTree.getTable() ; 
    	
    	Statistics stats = new Statistics(dfsTree.getComponents()) ; 
    	
    }
    
    /**
     * prints information such as what percent of the total number of actors are in the same 
     * component as my source actor 286726
     * 
     * It iterates through all the vertices, and counts how many different connections are there to the source
     * of the bfsTree if by checking if the distance to all the other vertices in the graph is greater than 0 
     * that will find how many people is the source connected to. 
     * @param bfsTree
     */
    public static void printPathReport(BFSTree bfsTree) {
    	double num = 1 ; 
    	double den = bfsTree.getGraph().getNumVertices() -1; 
        for (int vertex: bfsTree.getGraph().getVertices()) {
        	int dist = bfsTree.getDistanceTo(vertex); 
        	if (dist > 0) {
        		num ++; 
        			//System.out.println("distance to " + vertex + ": " +  bfsTree.getDistanceTo(vertex)); 
        	}
        			
        }
        System.out.println("Number of connections in component: "+ num);
        System.out.println("Number of vertices in total: " + den);
        double avg = num/den; 
        System.out.println("Percent of total actors that are connected to my source actor" + avg); 
         
       
    	
    	Statistics stats  = new Statistics(bfsTree.getDoubleDistances());
    	System.out.println("Mean distance to all other actors" + stats.getMean());
    	
    }
    
    /**
     * like printPathReport this method also does the same but because it is run many times 
     * it stores the data that is collected from running the method such as the number of other actors
     * the given source is connected to and the mean distance to all the connections the source has in a 
     * popularity object 
     * @param bfsTree
     */
    public static void printSimplePathReport(BFSTree bfsTree) {
    	double num = 1 ; 
    	double den = bfsTree.getGraph().getNumVertices() -1; 
        for (int vertex: bfsTree.getGraph().getVertices()) {
        	int dist = bfsTree.getDistanceTo(vertex); 
        	if (dist > 0) {
        		num ++; 
        			//System.out.println("distance to " + vertex + ": " +  bfsTree.getDistanceTo(vertex)); 
        	}
        			
        }
        
        double average = (num/den);
        Statistics stats =new Statistics(bfsTree.getDoubleDistances()); 
        double meanDistance = stats.getMean() ; 
        Popularity pop = new Popularity(bfsTree.getSource() , meanDistance , average); 
        runs ++ ; 
        System.out.println(runs); 
        popularityContest.add(pop); 
        
    	
    }
    /**
     * this method is for determining the percentiles for coverage and the percentiles for mean distance 
     * but it also determines things such as teh standard deviation and the varience in the data 
     * 
     * How it works: 
     * For every popularity element it takes the meanDistance and the source and the coverage 
     * and puts them into an array of doubles
     * 
     * it takes the array of doubles and calculates the percentiles(99, 95, 75, 50, 25)  and other 
     * information required to answer the Special Question 
     * 
     * It makes use of the DescriptiveStatistics Library form Apache Commons Math Stats to do this 
     */
    public static void special() {
    	double[] meanD = new double[popularityContest.size()]; 
    	double[] avg = new double[popularityContest.size()]; 
    	int[] src = new int[popularityContest.size()]; 
    	
    	int counter = 0 ; 
    	for(Popularity pop : popularityContest) {
    		  src[counter] = pop.getSource(); 
    		  avg[counter] = pop.getAvg() ; 
    		  meanD[counter] = pop.getMeanDistance() ; 
    		  counter++; 
    	}
    	
    	DescriptiveStatistics statsAvg = new DescriptiveStatistics(avg); 
    	DescriptiveStatistics statsMeanD = new DescriptiveStatistics(meanD) ; 
    	
    	System.out.println("Coverage of graph"); 
    	System.out.println("99% " + statsAvg.getPercentile(99)); 
    	System.out.println("95% " + statsAvg.getPercentile(95)); 
    	System.out.println("75% " + statsAvg.getPercentile(75)); 
    	System.out.println("50% " + statsAvg.getPercentile(50)); 
    	System.out.println("25% " + statsAvg.getPercentile(25));
    	System.out.println("Mean " + statsAvg.getMean()); 
    	System.out.println("Max " + statsAvg.getMax()); 
    	System.out.println("Min " + statsAvg.getMin()); 
    	System.out.println("Varience in the coverage " + statsAvg.getPopulationVariance()); 
    	System.out.println("Standard Deviation " + statsAvg.getStandardDeviation()); 
    	System.out.println("skewness" + statsAvg.getSkewness()); 
    	//System.out.println("set" + Arrays.toString(statsAvg.getValues())); 
    	
    	
    	
    	
    	
    	System.out.println(""); 
    	System.out.println(""); 
    	
    	System.out.println("Mean Distance to Connections"); 
    	System.out.println("99% " + statsMeanD.getPercentile(1)); 
    	System.out.println("95% " + statsMeanD.getPercentile(5)); 
    	System.out.println("3rd Quartile " + statsMeanD.getPercentile(25)); 
    	System.out.println("2nd Quartile " + statsMeanD.getPercentile(50)); 
    	System.out.println("1st Quartile " + statsMeanD.getPercentile(75));
    	System.out.println("Mean " + statsMeanD.getMean()); 
    	System.out.println("Max " + statsMeanD.getMax() ); 
    	System.out.println("Min " + statsMeanD.getMin()); 
    	System.out.println("Varience in the coverage " + statsMeanD.getPopulationVariance()); 
    	System.out.println("Standard Deviation " + statsMeanD.getStandardDeviation()); 
    	System.out.println("skewness " + statsMeanD.getSkewness()); 
    	
    	
    }
    
 
}
