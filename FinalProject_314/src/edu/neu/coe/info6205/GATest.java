package edu.neu.coe.info6205;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class GATest {


    @Test
    public void testGetDistance() {


        Route route = new Route();
        route.getRoute().add(new Home(0, 1, 2));
        route.getRoute().add(new Home(1, 2, 3));
        route.getRoute().add(new Home(2, 3, 4));

        //System.out.println(route.getRoute().size());


        assertEquals(5, (int)route.getDistance());


    }

//    @Test
//    public void testMutate() throws IOException {
//        Main.readFromLocalData();
//        Population population = new Population(1, true);
//
//        double oldFitness = population.getRoute(0).getFitness();
//
//        GeneticAlgorithm.mutate(population.getRoute(0));
//
//        double newFitness = population.getRoute(0).getFitness();
//
//        Assert.assertFalse(oldFitness != newFitness);
//
//    }

    @Test
    public void testCrossOver() throws IOException {

        Main.readFromLocalData();

        Route parent1 = new Route();
        Route parent2 = new Route();

        for (int i = 0; i < 48; i++) {
            parent1.getRoute().set(i, new Home(i, 1, 2));
            parent2.getRoute().set(i, new Home(47 - i, 1, 2));
        }
        
        int cut1 = 3;
        int cut2 = 5;
        List<Route> list = GeneticAlgorithm.crossOver(parent1, parent2, cut1, cut2);

        parent1 = list.get(0);
        parent2 = list.get(1);

        assertEquals(47, parent1.getRoute().get(0).getId());
        assertEquals(44, parent1.getRoute().get(6).getId());
        assertEquals(47, parent2.getRoute().get(3).getId());
        assertEquals(6, parent2.getRoute().get(6).getId());
    }

    @Test
    public void testSortByFitness() throws IOException {

        Main.readFromLocalData();

        Population population = new Population(10, true);

        GeneticAlgorithm.sortByFitness(population);

        //System.out.println(population.getDelivers().length);

        for (int i = 0; i < population.getDelivers().length - 1; i++) {
            //System.out.print(population.getDelivers()[i].getFitness() + " ");
            Assert.assertTrue(population.getDelivers()[i].getFitness() >= population.getDelivers()[i + 1].getFitness());
        }
    }





}