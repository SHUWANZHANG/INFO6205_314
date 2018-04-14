package edu.neu.coe.info6205;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Population {
	
	private Route[] delivers; 

	public Population(int numofpop, boolean initialize) {
		
		delivers = new Route[numofpop];
		
		if (initialize) 
		{
            for (int i = 0; i < numofpop; i++) {
                Route newRoute = new Route();
                newRoute.generateOneRoute();
                delivers[i] = newRoute;
            }
        }
	}
	
	public Route[] getDelivers() {
		return delivers;
	}
	
	public Route getBestRoute()
	{
		Route best = delivers[0];
		for(int i=1; i< delivers.length; i++)
		{
			if(best.getFitness() <= delivers[i].getFitness())
			{
				best = delivers[i];
			}
		}
		
		return best;
	}
	
	public float[] getprobability()  //cumulative probability
	{		
		double sum = 0;
		
		float[] pi = new float[delivers.length];
		
		for(int i=0; i< delivers.length; i++)
		{
			sum = sum + delivers[i].getFitness();			
		}
		
		pi[0] = (float) (delivers[0].getFitness() / sum);
		
		for(int i=1; i< pi.length; i++)
		{
			pi[i] = (float) (delivers[i].getFitness()/sum + pi[i-1]);
		}
		
		return pi;
		
	}
	
	
	public void sort(Route[] delivers) {

        PriorityQueue<Route> pq = new PriorityQueue<>(delivers.length, new Comparator<Route>() 
        
        {
            @Override
            public int compare(Route o1, Route o2) {
                return (int)(o2.getFitness()*1000000 - o1.getFitness()*1000000);
            }

        });

        for (int i = 0; i < delivers.length; i++) {
            pq.add(delivers[i]);
        }
        
        
        for (int i = 0; i < delivers.length; i++) {
        	    delivers[i] = pq.remove();
        }      
        
    }

	public static boolean checkIfAccessbleByOneWay(Route route) {
		
		for (int i = route.getRoute().size() - 1; i >= 1; i--) {
			if(route.getRoute().get(i).getIsOneWayTo() == route.getRoute().get(i-1).getId())
			{
				return false;
			}
        }
        return true;
	}

	public int getpopnumber()
	{
		return delivers.length;
	}

}





