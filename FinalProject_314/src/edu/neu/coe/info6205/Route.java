package edu.neu.coe.info6205;

import java.util.ArrayList;
import java.util.Collections;

public class Route {

	private ArrayList<Home> route; 
	private double fitness = 0;
	
	
	public Route() {
		route = new ArrayList<Home>();
		
		for(int i=0; i< RouteManager.getAllhomes().size(); i++)
		{
			route.add(null);
		}
	}
	
	public void generateOneRoute() {
		
		for (int index=0; index< RouteManager.getAllhomes().size(); index++) {
			route.set(index, RouteManager.getAllhomes().get(index));
		}
		
		Collections.shuffle(route);
	
	}
	
	public double getDistance()
	{
		double distance = 0;
		
		for(int index =0; index< route.size()-1; index++)
		{
			Home from = route.get(index);
			Home to = route.get(index+1);
			double d = from.distanceTo(to);
			distance = distance + d;
		}
		distance = distance + route.get(route.size()-1).distanceTo(route.get(0)); //from the end to the initial
		
		return distance;
	}
	

	public ArrayList<Home> getRoute() {
		return route;
	}
	
	public double getFitness() {
		
		double dis = (double)getDistance();
		
        if (fitness == 0) {
            fitness = 10.0 / dis;
        }
        return fitness;
    }
	
	 public int getCityIndex(Home home) {
	        return route.indexOf(home);
	 }
	 
	 public int homeNumber()
	 {
		 return route.size();
	 }	
	
	
	@Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < route.size(); i++) {
            geneString += route.get(i) + " ";
        }
        return geneString;
    }



}
