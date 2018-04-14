package edu.neu.coe.info6205;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class GATest {

	@Test
	public void test() {
		
		ArrayList<Home> home1 = new ArrayList<>();
        Home h1 = new Home(1, 10, 20, 3);
        home1.add(h1);
        Home h2 = new Home(2, 40, 50, 3);
        home1.add(h2);
        Home h3 = new Home(3, 5, 6, 3);
        home1.add(h3);
        Home h4 = new Home(4, 100, 20, 3);
        home1.add(h4);
        Home h5 = new Home(5, 90, 5, 3);
        home1.add(h5);
        Home h6 = new Home(6, 30, 70, 3);
        home1.add(h6);
        
        ArrayList<Home> home2 = new ArrayList<>();
        Home h7 = new Home(7, 8, 90, 3);
        home2.add(h7);
        Home h8 = new Home(8, 100, 25, 3);
        home2.add(h8);
        Home h9 = new Home(9, 50, 120, 3);
        home2.add(h9);
        Home h10 = new Home(10, 45, 25, 3);
        home2.add(h10);
        Home h11 = new Home(11, 80, 60, 3);
        home2.add(h11);
        Home h12 = new Home(12, 12, 16, 3);
        home2.add(h12);
        
        
	}
	
	
	@Test
    public void testCrossOver() throws IOException {
        GA.readFromLocalData();
        TourList tourList = new TourList(2, true);

        Tour parent1 = tourList.getTour(0);
        Tour parent2 = tourList.getTour(1);

        int cut1 = 3;
        int cut2 = 5;
        GA.crossOver(parent1, parent2, cut1, cut2);

        assertEquals(45, tourList.getTour(0).getCity(0).id);
        assertEquals(0, tourList.getTour(1).getCity(0).id);
    }

}
