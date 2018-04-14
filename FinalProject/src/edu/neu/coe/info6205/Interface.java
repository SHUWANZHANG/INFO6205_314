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
   
   private Graphics g;
   private int count =0;
   
   private Population population;
   
   public Interface(Population pop, Route route){   
        super();
        
        this.population = pop;
        
        getContentPane().setLayout(null);
        getContentPane().add(button1);
        getContentPane().add(button2);
        getContentPane().add(label1);
        getContentPane().add(label2);
        
        setBounds(0, 100, 1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
        panel.setLayout(null);
        panel.setBounds(50,100, 900, 800);
        panel.setBackground(Color.gray);
        getContentPane().add(panel);
        
        button1.setText("Initial Diagram");
        button1.setBounds(100,10,150,80);
        
        button2.setText("Evolve");
        button2.setBounds(250,10,150,80);
        
        label1.setBounds(430, 10, 200, 80);       
        label2.setBounds(650, 10, 250, 80);
        
               
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
            	    panel.update(g);
            	    
            	    population = GeneticAlgorithm.evolve(population);
            	    
            	    Route route = population.getBestRoute();
            	    
            	    System.out.println("di---------" + route.getDistance());
            	    
            	    draw(route);
            	    
            	    label1.setText("Diatance: " + String.valueOf((Double)route.getDistance()));
        			label2.setText("Fitness: " + String.valueOf((Double)route.getFitness()));
            	    
            	    
            }
            
        });
                
    }
   
    public void draw(Route route)
    {  	
    	     g = panel.getGraphics();
    	     g.setColor(Color.green);
     	
     	for(int i=0; i< route.getRoute().size()-1; i++)
     	{
     		g.drawLine(10*route.getRoute().get(i).getX(), 10*route.getRoute().get(i).getY(), 10*route.getRoute().get(i+1).getX(), 10*route.getRoute().get(i+1).getY());
    
     		g.fillRect(10*route.getRoute().get(i).getX(), 10*route.getRoute().get(i).getY(), 5, 5);
     		String x = String.valueOf(route.getRoute().get(i).getX());
     		String y = String.valueOf(route.getRoute().get(i).getY());
            String id = String.valueOf(i);
     		g.drawString(id + "("+ x + "," + y + ")", 10*route.getRoute().get(i).getX(), 10*route.getRoute().get(i).getY());
     	}

     	int size = route.getRoute().size();

     	g.fillRect(10*route.getRoute().get(size-1).getX(), 10*route.getRoute().get(size-1).getY(), 5, 5);
     	String x = String.valueOf(route.getRoute().get(size-1).getX());
     	String y = String.valueOf(route.getRoute().get(size-1).getY());
     	String id = String.valueOf(size-1);
     	g.drawString(id + "("+ x + "," + y + ")", 10*route.getRoute().get(size-1).getX(), 10*route.getRoute().get(size-1).getY());
     	g.drawLine(10*route.getRoute().get(size-1).getX(), 10*route.getRoute().get(size-1).getY(), 10*route.getRoute().get(0).getX(), 10*route.getRoute().get(0).getY());
    	    
    }
    
  
}