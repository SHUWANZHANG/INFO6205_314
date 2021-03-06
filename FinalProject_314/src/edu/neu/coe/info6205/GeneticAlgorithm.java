package edu.neu.coe.info6205;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class GeneticAlgorithm {

	private static float crossoverRate = (float) 0.7;
	private static float mutationRate = (float) 0.015;

	public static Population evolve(Population population) {
		Random random = new Random();

		Population newPopulation = new Population(population.getDelivers().length, false);

		newPopulation.getDelivers()[0] = population.getBestRoute(); // put best to the first route

		// Selection(Roulette Wheel Selection)--- choose 50 route and put it into a newPopulation
		for (int i = 1; i < newPopulation.getDelivers().length; i++) {
			float randomvalue = (float) (random.nextInt(65535) % 1000 / 1000.0);
			newPopulation.getDelivers()[i] = select(population, randomvalue);
		}

		// Select 50 pairs(100 route) of parents for crossover
		Population newPopulation2 = new Population(newPopulation.getDelivers().length * 2, false);

		for (int i = 0; i < newPopulation2.getpopnumber(); i += 2) {

			Route parent1 = newPopulation.getDelivers()[random.nextInt(newPopulation.getpopnumber())];
			Route parent2 = newPopulation.getDelivers()[random.nextInt(newPopulation.getpopnumber())];

			while (parent2 == parent1) {
				parent2 = newPopulation.getDelivers()[random.nextInt(newPopulation.getpopnumber())];
			}

			if (Math.random() < crossoverRate) {
				int cut1 = random.nextInt(parent1.homeNumber()); // decide that child get which genes from parents
				int cut2 = random.nextInt(parent1.homeNumber());

				ArrayList<Route> list = crossOver(parent1, parent2, cut1, cut2);
				parent1 = list.get(0);
				parent2 = list.get(1);
			}

			newPopulation2.getDelivers()[i] = parent1;
			newPopulation2.getDelivers()[i + 1] = parent2;

		}

		// sort all of the child route---priority queue
		sortByFitness(newPopulation2);

		// select the best 50 child forming a new population
		for (int i = 1; i < newPopulation.getDelivers().length; i++) {
			newPopulation.getDelivers()[i] = newPopulation2.getDelivers()[i - 1];
		}

		population.setRoute(0, newPopulation.getRoute(0));

		// mutate it
		for (int j = 1; j < population.getpopnumber(); j++) {

			Route route = new Route();
			for (int i = 0; i < newPopulation.getRoute(j).getRoute().size(); i++) {
				route.getRoute().set(i, newPopulation.getRoute(j).getRoute().get(i));
			}
			
			population.setRoute(j, mutate(route));
		}

		return population;
	}

	// Roulette Wheel Selection function
	public static Route select(Population population, double randomvalue) {

		for (int i = 0; i < population.getDelivers().length; i++) {
			if (randomvalue <= population.getprobability()[i]) {
				return population.getDelivers()[i];
			}
		}
		return null;
	}

	// crossOver parent1 and parent2 to creates two offsprings
	public static ArrayList<Route> crossOver(Route parent1, Route parent2, int cut1, int cut2) {

		int size = parent1.getRoute().size();
		Route child1 = new Route();
		Route child2 = new Route();

		// Loop and add the sub route from parent1 to our child
		for (int i = 0; i < size; i++) {
			// If our start position is less than the end position
			if (cut1 <= cut2) {
				if (i >= cut1 && i <= cut2)
					child1.getRoute().set(i, parent1.getRoute().get(i));
				else
					child2.getRoute().set(i, parent1.getRoute().get(i));
			}
			// If our start position is larger
			else {
				if (i <= cut1 && i >= cut2)
					child2.getRoute().set(i, parent1.getRoute().get(i));
				else
					child1.getRoute().set(i, parent1.getRoute().get(i));
			}
		}

		// Loop through parent2's home
		for (int i = 0; i < size; i++) {
			// If child doesn't have the home add it
			if (child1.getCityIndex(parent2.getRoute().get(i)) == -1) {
				// Loop to find a spare position in the child's tour
				for (int ii = 0; ii < size; ii++) {
					// Spare position found, add city
					if (child1.getRoute().get(ii) == null) {
						child1.getRoute().set(ii, parent2.getRoute().get(i));
						break;
					}
				}
			}

			if (child2.getCityIndex(parent2.getRoute().get(i)) == -1) {
				// Loop to find a spare position in the child's tour
				for (int ii = 0; ii < size; ii++) {
					// Spare position found, add city
					if (child2.getRoute().get(ii) == null) {
						child2.getRoute().set(ii, parent2.getRoute().get(i));
						break;
					}
				}
			}
		}
		ArrayList<Route> list = new ArrayList<>();
		list.add(child1);
		list.add(child2);
		return list;

	}

	// Sorting function --- According to the fitness
	public static void sortByFitness(Population population) {

		PriorityQueue<Route> pq = new PriorityQueue<>(population.getDelivers().length, new Comparator<Route>() {
			@Override
			public int compare(Route o1, Route o2) {
				 if (o2.getFitness() * 1000000 - o1.getFitness() * 1000000 > 0) return 1;
				 else if (o2.getFitness() * 1000000 - o1.getFitness() * 1000000 == 0) return 0;
				 else return -1;
			}
		});

		for (int i = 0; i < population.getDelivers().length; i++) {
			pq.add(population.getDelivers()[i]);
		}

		for (int i = 0; i < population.getDelivers().length; i++) {
			population.getDelivers()[i] = pq.remove();
		}

	}
	
	// Mutation, loop all home in one route, compare the random number with the mutation rate for making the decision 
	// that if the home needs to be mutated.
	public static Route mutate(Route route) {
		Random random = new Random();
		for (int i = 0; i < route.homeNumber(); i++) {
			double r = Math.random();
			if (r < mutationRate) {
				Home temp = route.getRoute().get(i);
				int p = random.nextInt(route.homeNumber());
				while (p == i) {
					p = random.nextInt(route.homeNumber());
				}
				Home choose = route.getRoute().get(p);
				route.getRoute().set(i, choose);
				route.getRoute().set(p, temp);
			}
		}
		return route;

	}

}
