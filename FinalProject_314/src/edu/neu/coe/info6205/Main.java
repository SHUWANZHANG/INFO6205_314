package edu.neu.coe.info6205;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) throws IOException {
		
		readFromLocalData();

		Route initial = new Route();
		for (int i = 0; i < initial.getRoute().size(); i++) 
		{
			initial.getRoute().set(i, RouteManager.getAllhomes().get(i));
		}

		Random random = new Random();

		logger.info("Initial: ");
		for (Home h : RouteManager.getAllhomes()) {
			System.out.print(h + " ");
		}

		//logger.info();
		logger.info("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		logger.info("After random: ");
		
		// Initialize population
		Population pop = new Population(50, true);

		// calculate all of pi
		for (int i = 0; i < pop.getDelivers().length; i++) {
			logger.info(pop.getDelivers()[i]);
			logger.info(">>>>>>>>>>Distance: " + pop.getDelivers()[i].getDistance() + " || Fitness: "+ pop.getDelivers()[i].getFitness() + " || PI: " + pop.getprobability()[i]);
		}

		// Best one route
		logger.info(">>>>>>>>>The best route is: ");
		logger.info(pop.getBestRoute());
		logger.info("Distance: " + pop.getBestRoute().getDistance() + " || Fitness: " + pop.getBestRoute().getFitness());

		logger.info("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		logger.info("After evolve: ");
		
        
		for(int t=0; t< 100; t++)
		{			
			pop = GeneticAlgorithm.evolve(pop);
				
            if((t+1)% 10 == 0)
            {
			// Best one route
			logger.info(">>>>>>>>>The best route is--------" + "generation is " + (t+1));
			logger.info(pop.getBestRoute());
			logger.info( "Distance: " + pop.getBestRoute().getDistance() + " || Fitness: " + pop.getBestRoute().getFitness());
            }
		}
		
		//Start User Interface
		Interface f = new Interface(pop, initial);
		f.setVisible(true);
}
	

	    public static void readFromLocalData() throws IOException {
		// Create and add our cities
		FileReader fr = new FileReader("src//data.txt");
		BufferedReader br = new BufferedReader(fr);

		// skip first line
		String newLine = br.readLine();
		while (true) {
			newLine = br.readLine();
			if (newLine == null)
				break;
			String[] arr = newLine.split(" ");
			RouteManager.addHome(new Home(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2])));

		}
		br.close();
		fr.close();

	}

}
