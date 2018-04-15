package edu.neu.coe.info6205;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Interface extends JFrame { 
    
   final JButton button1 = new JButton();
   final JButton button2 = new JButton();
   final JPanel panel = new JPanel();
   JLabel label1 = new JLabel();
   JLabel label2 = new JLabel();
   JLabel label3 = new JLabel();
   
   private Graphics g;
   private int count =2;
   
   private Population population;
   private int sumgeneration = 100;
   
   public Interface(Population pop, Route route){   
        super();
        
        
        this.population = pop;
        
        getContentPane().setLayout(null);
        getContentPane().add(button1);
        getContentPane().add(button2);
        getContentPane().add(label1);
        getContentPane().add(label2);
        getContentPane().add(label3);
        
        setBounds(0, 100, 1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
        panel.setLayout(null);
        panel.setBounds(50,100, 1100, 900);
        panel.setBackground(Color.gray);
        getContentPane().add(panel);
        
        button1.setText("Initial Diagram");
        button1.setBounds(100,10,150,80);
        
        button2.setText("Evolve");
        button2.setBounds(250,10,150,80);
        
        label1.setBounds(600, 10, 200, 80);       
        label2.setBounds(830, 10, 250, 80);
        label3.setBounds(430, 10, 150, 80);
               
        button1.addActionListener(new ActionListener(){
   
        		public void actionPerformed(final ActionEvent e)
        		{
        			//panel.update(g);
        			draw(route);  
        			label1.setText("Diatance: " + String.valueOf((Double)route.getDistance()));
        			label2.setText("Fitness: " + String.valueOf((Double)route.getFitness()));
        		}
              
        });        
        
        button2.addActionListener(new ActionListener(){
   
            public void actionPerformed(final ActionEvent e)
            {
            	    count++;
            	    panel.update(g);
            	    
            	    for(int i =0 ; i< 50; i++)
            	    {
            	    	     population = GeneticAlgorithm.evolve(population);
            	    	     sumgeneration = sumgeneration + i;
            	    	     label3.setText("Generation: " + sumgeneration );
            	    }
            	    
            	    Route route = population.getBestRoute();
            	    
            	    draw(route);
            	    
            	    label1.setText("Diatance: " + String.valueOf((Double)route.getDistance()));
        			label2.setText("Fitness: " + String.valueOf((Double)route.getFitness()));
        			label3.setText("Generation: "  + count*50);
            	    
            }
            
        });
                
    }
   
    public void draw(Route route)
    {  	
    	     g = panel.getGraphics();
    	     g.setColor(Color.green);
     	
     	for(int i=0; i< route.getRoute().size()-1; i++)
     	{
     		g.drawLine(route.getRoute().get(i).getX()/10, route.getRoute().get(i).getY()/10, route.getRoute().get(i+1).getX()/10, route.getRoute().get(i+1).getY()/10);
    
     		g.fillRect(route.getRoute().get(i).getX()/10, route.getRoute().get(i).getY()/10, 5, 5);
     		String x = String.valueOf(route.getRoute().get(i).getX());
     		String y = String.valueOf(route.getRoute().get(i).getY());
            String id = String.valueOf(i);
     		g.drawString(id + "("+ x + "," + y + ")", route.getRoute().get(i).getX()/10, route.getRoute().get(i).getY()/10);
     	}

     	int size = route.getRoute().size();

     	g.fillRect(route.getRoute().get(size-1).getX()/10, route.getRoute().get(size-1).getY()/10, 5, 5);
     	String x = String.valueOf(route.getRoute().get(size-1).getX());
     	String y = String.valueOf(route.getRoute().get(size-1).getY());
     	String id = String.valueOf(size-1);
     	g.drawString(id + "("+ x + "," + y + ")", route.getRoute().get(size-1).getX()/10, route.getRoute().get(size-1).getY()/10);
     	g.drawLine(route.getRoute().get(size-1).getX()/10, route.getRoute().get(size-1).getY()/10, route.getRoute().get(0).getX()/10, route.getRoute().get(0).getY()/10);
    	    
    }
    
  
}