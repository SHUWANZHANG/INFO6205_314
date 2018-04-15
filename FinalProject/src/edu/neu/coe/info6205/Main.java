package edu.neu.coe.info6205;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {
		readFromLocalData();

		Route initial = new Route();
		for (int i = 0; i < initial.getRoute().size(); i++) 
		{
			initial.getRoute().set(i, RouteManager.getAllhomes().get(i));
		}

		Random random = new Random();

		System.out.println("Initial: ");
		for (Home h : RouteManager.getAllhomes()) {
			System.out.print(h + " ");
		}

		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("After random: ");
		
		// Initialize population
		Population pop = new Population(50, true);

		// calculate all of pi
		for (int i = 0; i < pop.getDelivers().length; i++) {
			System.out.println(pop.getDelivers()[i]);
			System.out.println("Distance: " + pop.getDelivers()[i].getDistance() + " || Fitness: "+ pop.getDelivers()[i].getFitness() + " || PI: " + pop.getprobability()[i]);
		}

		// Best one route
		System.out.println();
		System.out.println(">>>>>>>>>The best route is: ");
		System.out.println(pop.getBestRoute());
		System.out.println("Distance: " + pop.getBestRoute().getDistance() + " || Fitness: " + pop.getBestRoute().getFitness());
		 


		System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("After evolve: ");
		
        
		for(int t=0; t< 100; t++)
		{
			//System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			pop = GeneticAlgorithm.evolve(pop);
				
            if((t+1)% 10 == 0)
            {
			// Best one route
            	System.out.println("generation is " + (t+1));
			System.out.println(">>>>>>>>>The best route is: ");
			System.out.println(pop.getBestRoute());
			System.out.println( "Distance: " + pop.getBestRoute().getDistance() + " || Fitness: " + pop.getBestRoute().getFitness());
            }
		}
		
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
