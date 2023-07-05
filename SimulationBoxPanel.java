/**
 * Program Name: SimulationBoxPanel.java
 * Purpose: Displays the graphical portions of the simulation
 * Coder: Hongseok Kim
 * Date: August 1, 2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimulationBoxPanel extends JPanel
{
	private JLabel Infected_LB, dead_LB, No_immunity_LB, Single_shot_LB, Two_shot_LB, Three_shot_LB, Natural_immunity_LB,
	 							 Total_population_LB, Day_LB, Num_ifected_LB, One_vaccin_LB, Two_vaccin_LB, Three_vaccin_LB, Nat_immune_LB,
	 							 Re_Infected_LB, Dead_LB, Non_vaccin_LB, ball_1, ball_2, ball_3, ball_4, ball_5, ball_6, ball_7;

    private JTextField Total_population_TF, Day_TF, Num_infected_TF, One_vaccin_TF, Two_vaccin_TF, Three_vaccin_TF, Nat_immune_TF,
    									 Re_Infected_TF, Dead_TF, Non_vaccin_TF;
    
    private ImageIcon ball1, ball2, ball3, ball4, ball5, ball6, ball7;

    private JButton Pause_Btn, Continue_Btn, Stop_Btn;
    private Font normalFont;

	//constructor
	public SimulationBoxPanel(SimulationStatus status)
	{ 
		  this.setBackground(Color.DARK_GRAY);
		  this.setLayout(new BorderLayout() );
		  setOpaque(true);
      setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
      normalFont = new Font("SansSerif", Font.PLAIN, 15);
	    
			JPanel topPanel = new JPanel();//layout will default to FlowLayout
			//create a JLabel for the top
			ball_1 = new JLabel();
			ball1 = new ImageIcon("Picture1.png");
			ball_1.setIcon(ball1);
			Infected_LB = new JLabel( " Infected  ");
			
			ball_2 = new JLabel();
			ball2 = new ImageIcon("Picture2.png");
			ball_2.setIcon(ball2);
			dead_LB = new JLabel(" Dead  ");
			
			ball_3 = new JLabel();
			ball3 = new ImageIcon("Picture3.png");
			ball_3.setIcon(ball3);
			No_immunity_LB = new JLabel(" No immunity  ");
			
			ball_4 = new JLabel();
			ball4 = new ImageIcon("Picture4.png");
			ball_4.setIcon(ball4);
			Single_shot_LB = new JLabel(" Single shot  ");
			
			ball_5 = new JLabel();
			ball5 = new ImageIcon("Picture5.png");
			ball_5.setIcon(ball5);
			Two_shot_LB = new JLabel(" Two shot  ");
			
			ball_6 = new JLabel();
			ball6 = new ImageIcon("Picture6.png");
			ball_6.setIcon(ball6);
			Three_shot_LB = new JLabel(" Three shot  ");
			
			ball_7 = new JLabel();
			ball7 = new ImageIcon("Picture7.png");
			ball_7.setIcon(ball7);
			Natural_immunity_LB = new JLabel(" Natural immunity");
		
			topPanel.add(ball_1);
			topPanel.add(Infected_LB);
			topPanel.add(ball_2);
			topPanel.add(dead_LB);
			topPanel.add(ball_3);
			topPanel.add(No_immunity_LB);
			topPanel.add(ball_4);
			topPanel.add(Single_shot_LB);
			topPanel.add(ball_5);
			topPanel.add(Two_shot_LB);
			topPanel.add(ball_6);
			topPanel.add(Three_shot_LB);
			topPanel.add(ball_7);
			topPanel.add(Natural_immunity_LB);
			
			
			this.add(topPanel, BorderLayout.NORTH);
			
			
	    JPanel middlePanel = new JPanel();
			middlePanel.setLayout(new GridLayout(10,2,0,2) );
			middlePanel.setBackground(new Color(255,255,0));
			
			Total_population_LB = new JLabel(" Total Population");
			Total_population_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Total_population_LB.setBackground(new Color(255,255,255)); //color change? 
			
			Day_LB = new JLabel(" Day #");
			Day_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Num_ifected_LB = new JLabel(" Number of Infected");
			Num_ifected_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			One_vaccin_LB = new JLabel(" One shot vaccinated people");
			One_vaccin_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Two_vaccin_LB = new JLabel(" Two shot vaccinated people");
			Two_vaccin_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Three_vaccin_LB = new JLabel(" Three shot vaccinated people");
			Three_vaccin_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Nat_immune_LB = new JLabel(" Naturally immune(recovered)");
			Nat_immune_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Re_Infected_LB = new JLabel(" Re-infected people");
			Re_Infected_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Dead_LB= new JLabel(" Dead");
			Dead_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			Non_vaccin_LB = new JLabel(" Non-Vaccinated people");
			Non_vaccin_LB.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			Total_population_TF = new JTextField(" " + status.getTotalPopulation());
			Day_TF = new JTextField(" 1");
			Num_infected_TF = new JTextField(" " + status.getInfectedCount());
			One_vaccin_TF = new JTextField(" " + status.getOneShotCount());
			Two_vaccin_TF = new JTextField(" " + status.getTwoShotCount());
			Three_vaccin_TF = new JTextField(" " + status.getThreeShotCount());
			Nat_immune_TF = new JTextField(" " + status.getRecoveredCount());
			Re_Infected_TF = new JTextField(" 0");
			Dead_TF = new JTextField(" 0");
			Non_vaccin_TF = new JTextField(" " + status.getUnvaccinatedCount());
			
			//set font
			Total_population_TF.setFont(normalFont);
			Day_TF.setFont(normalFont);
			Num_infected_TF.setFont(normalFont);
			One_vaccin_TF.setFont(normalFont);
			Two_vaccin_TF.setFont(normalFont);
			Three_vaccin_TF.setFont(normalFont);
			Nat_immune_TF.setFont(normalFont);
			Re_Infected_TF.setFont(normalFont);
			Dead_TF.setFont(normalFont);
			Non_vaccin_TF.setFont(normalFont);
			
			middlePanel.add(Total_population_LB);
			middlePanel.add(Total_population_TF);
			middlePanel.add(Day_LB);
			middlePanel.add(Day_TF);
			middlePanel.add(Num_ifected_LB);
			middlePanel.add(Num_infected_TF);
			middlePanel.add(Non_vaccin_LB);
			middlePanel.add(Non_vaccin_TF);
			middlePanel.add(One_vaccin_LB);
			middlePanel.add(One_vaccin_TF);
			middlePanel.add(Two_vaccin_LB);
			middlePanel.add(Two_vaccin_TF);
			middlePanel.add(Three_vaccin_LB);
			middlePanel.add(Three_vaccin_TF);
			middlePanel.add(Nat_immune_LB);
			middlePanel.add(Nat_immune_TF);
			middlePanel.add(Re_Infected_LB);
			middlePanel.add(Re_Infected_TF);
			middlePanel.add(Dead_LB);
			middlePanel.add(Dead_TF);
			this.add(middlePanel, BorderLayout.WEST);
			
			JPanel bottomPanel = new JPanel();
			Pause_Btn = new JButton(" PAUSE ");
			Continue_Btn = new JButton(" CONTINUE ");
			Stop_Btn = new JButton(" STOP(Result) ");
			//bottomPanel.add(Box.createRigidArea(new Dimension(10,3) ) );
			bottomPanel.add(Pause_Btn);
			bottomPanel.add(Box.createRigidArea(new Dimension(20,3) ) );
			bottomPanel.add(Continue_Btn);
			bottomPanel.add(Box.createRigidArea(new Dimension(20,3) ) );
			bottomPanel.add(Stop_Btn);
		//	bottomPanel.add(Box.createRigidArea(new Dimension(10,3) ) );
			this.add(bottomPanel, BorderLayout.SOUTH);	
			
			JPanel middlePanel2 = new JPanel();
			//Create SimulationGraphic object
			JPanel simulationPanel = new SimulationGraphic(status, this,
																										 Day_TF,
																										 Num_infected_TF,
																										 One_vaccin_TF, 
																										 Two_vaccin_TF, 
																										 Three_vaccin_TF, 
																										 Nat_immune_TF,
																										 Re_Infected_TF,
																										 Dead_TF,
																										 Non_vaccin_TF,
																										 Pause_Btn,
																										 Continue_Btn,
																										 Stop_Btn
																										 ); 
			middlePanel2.setLayout(new BorderLayout());
			middlePanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			//add SimulationGraphic Object to sub-panel
			middlePanel2.add(simulationPanel);    
			this.add(middlePanel2, BorderLayout.CENTER);
			
	}//end constructor	
	
}
//end class