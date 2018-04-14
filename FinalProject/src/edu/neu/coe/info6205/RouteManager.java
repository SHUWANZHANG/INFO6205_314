package edu.neu.coe.info6205;

import java.util.ArrayList;

public class RouteManager {
	
	private static ArrayList<Home> allhomes = new ArrayList<Home>();
	
//	public RouteManager()
//	{
//		this.allhomes = new ArrayList<Home>();
//	}
	
	public static void addHome (Home home)
	{
				
		allhomes.add(home);
	}
	
	public Home getHome(int index)
	{
		return allhomes.get(index);
	}

	public static ArrayList<Home> getAllhomes() {
		return allhomes;
	}

	

}
