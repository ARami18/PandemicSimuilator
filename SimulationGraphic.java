/**
 * Program Name: SimulationGraphic.java
 * Purpose: a program to display the animation of the simulation based on user inputs. 
 *          Uses Person, Simulation and ImmunityStatus classes to display the graphical 
 *          representation of the pandemic simulation.
 *         
 * Author: Alejandro Ramirez
 * Date: August 5, 2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SimulationGraphic extends JPanel 
{

	//CLASS WIDE SCOPE AREA
	private final int WIDTH = 800, HEIGHT = 700; //size of JPanel
	private final int LAG_TIME = 200; // time in milliseconds between re-paints of screen
	private Timer time;               //Timer class object that will fire events every LAG_TIME interval
	private final int IMG_DIM = 10;   //size of ball to be drawn
	
	//Array to hold the number of Person objects, array of persons declared
	private ArrayList<Person> peopleArray;
	private int num_infected, num_one_shot, num_two_shot, num_three_shot, num_nat_immune, num_re_infected, num_dead, num_non_vacc;
	
	private int cycleCounter = 0;
	
	
	//Constructor for Sim graphic panel
	public SimulationGraphic(SimulationStatus status, SimulationBoxPanel boxPanel,
													 JTextField Day_TF,
													 JTextField Num_infected_TF,
													 JTextField One_vaccin_TF,
													 JTextField Two_vaccin_TF,
													 JTextField Three_vaccin_TF,
													 JTextField Nat_immune_TF,
													 JTextField Re_Infected_TF,
													 JTextField Dead_TF,
													 JTextField Non_vaccin_TF,
													 JButton Pause_Btn,
													 JButton Continue_Btn,
													 JButton Stop_Btn)
	{
		
		//Set Person array
		peopleArray = new ArrayList<Person>();
		
		//Timer with listener registered
		this.time = new Timer(LAG_TIME, new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (cycleCounter < 450)
				{
					//Calculate position for each Person object
					for (int i = 0; i < peopleArray.size(); i++)
					{
						peopleArray.get(i).move(WIDTH, HEIGHT);
					}
					 
					//Check for collisions between all pairs of Person objects
					//Outer loop for first person
					for (int i = 0; i < peopleArray.size() - 1; i++)
					{
						//Inner loop to execute checkCollision using outer loop's
						// current person object
						for (int j = i + 1; j < peopleArray.size(); j++) {

							peopleArray.get(i).checkCollision(peopleArray.get(j));
						}//end inner loop
						 
					}//end for collision check
					
					//one cycle is ended
					cycleCounter++;
					countAmount();
					
					//Set amount into the display
					Day_TF.setText(" " + getDay());
					Num_infected_TF.setText(" " + num_infected);
					One_vaccin_TF.setText(" " + num_one_shot);
					Two_vaccin_TF.setText(" " + num_two_shot);
					Three_vaccin_TF.setText(" " + num_three_shot);
					Nat_immune_TF.setText(" " + num_nat_immune);
					Re_Infected_TF.setText(" " + num_re_infected);
					Dead_TF.setText(" " + num_dead);
					Non_vaccin_TF.setText(" " + num_non_vacc);	
					
					repaint(); 
				}
			}//END ACTIONPERFORMED
		}// end anonymous inner class
		);
		
		//Loop to populate unvaccinated Person into arrayList
		for (int i = 0; i < status.getUnvaccinatedCount(); i++) {
			
			peopleArray.add(new Person(IMG_DIM, ImmunityStatus.NO_IMMUNITY, WIDTH, HEIGHT));
		}
		
		//Loop to populate one shot Person into arrayList
		for (int i = 0; i < status.getOneShotCount(); i++) {
			
			peopleArray.add(new Person(IMG_DIM, ImmunityStatus.ONE_SHOT, WIDTH, HEIGHT));
		}
		
		//Loop to populate two shot Person into arrayList
		for (int i = 0; i < status.getTwoShotCount(); i++) {
			
			peopleArray.add(new Person(IMG_DIM, ImmunityStatus.TWO_SHOT, WIDTH, HEIGHT));
		}
		
		//Loop to populate three shot Person into arrayList
		for (int i = 0; i < status.getThreeShotCount(); i++) {
			
			peopleArray.add(new Person(IMG_DIM, ImmunityStatus.THREE_SHOT, WIDTH, HEIGHT));
		}
		
		//Loop to populate recovered Person(naturally immune) into arrayList
		for (int i = 0; i < status.getRecoveredCount(); i++) {
			
			peopleArray.add(new Person(IMG_DIM, ImmunityStatus.NATURALLY_IMMUNE, WIDTH, HEIGHT));
		}
		
		//Loop to populate infected Person into arrayList
		for (int i = 0; i < status.getInfectedCount(); i++) {
			Person person = new Person(IMG_DIM, ImmunityStatus.INITIALLY_INFECTED, WIDTH, HEIGHT);
			person.setInfected(true);
			//initially infected person should be set color manually
			person.setColor();
			peopleArray.add(person);
		}
		
		//check if arrayList size is equal to getTotalPopulation
		if (peopleArray.size() != status.getTotalPopulation()) {
			System.out.println("The array has not been properly filled with the amount of people intended");
		}
		
		//set preferred size of panel using an ANONYMOUS Dimension object
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT) );
		this.setBackground(Color.WHITE);
		
		
		//set ActionLister for JButtons
		Pause_Btn.addActionListener(new ActionListener()
		{
		     public void actionPerformed(ActionEvent ev)
		     {
		    	 time.stop();
		     } 
		}		
		);
		
		Continue_Btn.addActionListener(new ActionListener()
		{
	     public void actionPerformed(ActionEvent ev)
	     {
	    	 time.restart();
	     } 
		}		
		);
		
		Stop_Btn.addActionListener(new ActionListener()
		{
		     public void actionPerformed(ActionEvent ev)
		     {
		    	 time.stop();
		    	 SimulationStatus resultStatus = new SimulationStatus();
		    	 resultStatus.setInfectedCount(num_infected);
		    	 resultStatus.setOneShotCount(num_one_shot);
		    	 resultStatus.setTwoShotCount(num_two_shot);
		    	 resultStatus.setThreeShotCount(num_three_shot);
		    	 resultStatus.setUnvaccinatedCount(num_non_vacc);
		    	 resultStatus.setTotalPopulation(status.getTotalPopulation());
		    	 resultStatus.setRecoveredCount(num_nat_immune);
		    	 
		    	 //Close the frame in which the simulation lives
           JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(boxPanel);
		    	 topFrame.dispose();
		    	 
		    	 //show result table
		    	 new ResultDisplay(peopleArray, status, resultStatus, num_re_infected, num_dead);
		     } 
		}		
		);	
		
		//start the timer so it creates ActionEvent objects 
		this.time.start();	
	}// END CONSTRUCTOR SIMGRAPHIC
	
	//paintComponent Override
	public void paintComponent(Graphics grap)
	{
		super.paintComponent(grap);
		
		//brush color
		grap.setColor(Color.PINK);
		
		//paint all Person objects onto panel
		//set color using person object's getColor()
		for(int i = 0; i < peopleArray.size(); i++) 
		{
			
			grap.setColor(peopleArray.get(i).getColor());
			grap.fillOval(peopleArray.get(i).getxCoordinate(), peopleArray.get(i).getyCoordinate(), 
					      peopleArray.get(i).getDiameter(), peopleArray.get(i).getDiameter());
			
		}
		
	}// END PAINT OVERRIDE
	
	public int getDay()
	{
		if (cycleCounter == 450)
		{
			return 21;
		}
		else
		{
			//150 cycles = 7 days
			double dayPerCycles = 7.0 / 150;	//0.0466666666666667
			return (int)(Math.floor(dayPerCycles * cycleCounter)) + 1;
		}
	}
	
	public void countAmount()
	{
		num_infected = 0;
		num_one_shot = 0;
		num_two_shot = 0;
		num_three_shot = 0;
		num_nat_immune = 0;
		num_re_infected = 0;
		num_dead = 0;
		num_non_vacc = 0;
		
		for(int i = 0; i < peopleArray.size(); i++)
		{
			//counts current people's status to display
			// if re-infected
			if (peopleArray.get(i).isReInfected())
			{
				num_re_infected++;
			}
			
			// if dead
			if (!peopleArray.get(i).isAlive())
			{
				num_dead++;
			}
			// if infected
			else if (peopleArray.get(i).isInfected())
			{
				num_infected++;
			}
			else
			{
				switch(peopleArray.get(i).getImmunityStatus())
				{
					case NO_IMMUNITY:						num_non_vacc++; 	break;
					case ONE_SHOT:							num_one_shot++;		break;
					case TWO_SHOT:							num_two_shot++; 	break;
					case THREE_SHOT:						num_three_shot++;	break;
					case NATURALLY_IMMUNE: 		
					case TWO_SHOT_RECOVERED: 	
					case THREE_SHOT_RECOVERED:	num_nat_immune++;	break;
					default: break;
				}
			}
		}
	}
}
