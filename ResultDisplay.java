import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;

import java.util.ArrayList;
public class ResultDisplay extends JFrame
{
//	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private DefaultTableCellRenderer cellRenderer1, cellRenderer2;
	private JLabel totalPopulation;
	private JButton restartButton, closeButton;
	private JPanel buttonPanel,upperPanel;
	
	private String[] columnStrings = {"Total", "Unvaccinated", "Single Shot", "Two Shots", "Three Shots", "Infected", "Re-infected", "Recovered"};
	private String[] initTotalColumn = new String[8];
	private String[] currTotalColumn = new String[8];
	private String[] infectedColumn = new String[8];
	private String[] deadColumn = new String[8];
	
	private int init_unvac, init_one, init_two, init_three, init_recovered, init_infected,
							current_unvac, current_one, current_two, current_three, current_recovered, current_infected,
							unvac_dead, one_dead, two_dead, three_dead, recovered_dead, infected_dead, reinfected_dead,
							total_infected, unvac_infected, one_infected, two_infected, three_infected, recovered_infected,
							total_size;
	   
	   //constructor
	   public ResultDisplay(ArrayList<Person> peopleArray, SimulationStatus status, SimulationStatus resultStatus, int num_re_infected, int num_dead)
	   {
	      setTitle("Simulation Result");
	      setLayout(new BorderLayout());
	      scrollPane = new JScrollPane();
	      JTable table = new JTable();
	      scrollPane.setViewportView(table);
	      model = (DefaultTableModel)table.getModel();
	      model.addColumn("Immunity Status");
	      model.addColumn("Input Total #");
	      model.addColumn("Result Total #");
	      model.addColumn("% of Contracted Disease");
	      model.addColumn("% of Death Toll");
	   
	      buttonPanel = new JPanel();
	      restartButton = new JButton("Restart the Simulation");
	      closeButton = new JButton("End the Simulator");	      
	      
	      restartButton.addActionListener(new ActionListener()
    		{
	      	public void actionPerformed(ActionEvent ev)
	 	    	{
	      		closeFrame();
	      		new Simulation_Data(status);
	 	    	} 
    		});
	      
	      closeButton.addActionListener(new ActionListener()
    		{
	      	public void actionPerformed(ActionEvent ev)
	 	    	{
      			System.exit(0);
	 	    	} 
    		});
	      
	      buttonPanel.setLayout(new FlowLayout());
	      buttonPanel.add(restartButton);
	      buttonPanel.add(closeButton);
	     
	      calculatePercent(peopleArray, status, resultStatus, num_re_infected, num_dead);
	      
	      for(int i = 0;i < 8; i++) {
	         model.addRow(new Object[0]);
	         model.setValueAt(columnStrings[i], i, 0);
	         model.setValueAt(initTotalColumn[i], i, 1);
	         model.setValueAt(currTotalColumn[i], i, 2);
	         model.setValueAt(infectedColumn[i], i, 3);
	         model.setValueAt(deadColumn[i], i, 4);
	         
	      }
	      // set the column width for each column
	      table.getColumnModel().getColumn(0).setPreferredWidth(130);
	      table.getColumnModel().getColumn(1).setPreferredWidth(100);
	      table.getColumnModel().getColumn(2).setPreferredWidth(100);
	      table.getColumnModel().getColumn(3).setPreferredWidth(200);
	      table.getColumnModel().getColumn(4).setPreferredWidth(150);
	      cellRenderer1 = new DefaultTableCellRenderer();
	      cellRenderer1.setHorizontalAlignment(JLabel.CENTER);
	      cellRenderer2 = new DefaultTableCellRenderer();
	      cellRenderer2.setHorizontalAlignment(JLabel.RIGHT);
	      table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer1);
	      table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer2);
	      table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer2);
	      table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer2);
	      table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer2);

	      upperPanel = new JPanel();
	      upperPanel.setSize(this.WIDTH, 50);	      
	      totalPopulation = new JLabel("Total Population : " + peopleArray.size());
	      totalPopulation.setFont(new Font(Font.DIALOG, Font.PLAIN,  13));
	      upperPanel.add(totalPopulation);
	      
	      add(scrollPane, BorderLayout.CENTER);
	      add(buttonPanel, BorderLayout.SOUTH);
	      add(upperPanel, BorderLayout.NORTH);
	      setSize(600, 500);
	      setResizable(false);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setLocationRelativeTo(this);
	      setVisible(true);
	   }
	  
	   public void calculatePercent(ArrayList<Person> peopleArray, SimulationStatus status, SimulationStatus resultStatus, int num_re_infected, int num_dead)
	   {	  	 
	  	 total_size = status.getTotalPopulation();
	  	 
	  	 init_unvac =  status.getUnvaccinatedCount();
	  	 init_one = status.getOneShotCount();
	  	 init_two = status.getTwoShotCount();
	  	 init_three = status.getThreeShotCount();
	  	 init_recovered = status.getRecoveredCount();
	  	 init_infected = status.getInfectedCount();
	  	 
	  	 current_unvac = resultStatus.getUnvaccinatedCount();
	  	 current_one = resultStatus.getOneShotCount();
	  	 current_two = resultStatus.getTwoShotCount();
	  	 current_three = resultStatus.getThreeShotCount();
	  	 current_recovered = resultStatus.getRecoveredCount();
	  	 current_infected = resultStatus.getInfectedCount();
	  	 
	  	 //input total amount
	  	 initTotalColumn[0] = "" + total_size;
	  	 initTotalColumn[1] = "" + init_unvac;
	  	 initTotalColumn[2] = "" + init_one;
	  	 initTotalColumn[3] = "" + init_two;
	  	 initTotalColumn[4] = "" + init_three;
	  	 initTotalColumn[5] = "" + init_infected;
	  	 initTotalColumn[6] = "-";
	  	 initTotalColumn[7] = "" + init_recovered;
	  	 
	  	//result total amount
	  	 currTotalColumn[0] = "" + total_size;
	  	 currTotalColumn[1] = "" + current_unvac;
	  	 currTotalColumn[2] = "" + current_one;
	  	 currTotalColumn[3] = "" + current_two;
	  	 currTotalColumn[4] = "" + current_three;
	  	 currTotalColumn[5] = "" + current_infected;
	  	 currTotalColumn[6] = "" + num_re_infected;
	  	 currTotalColumn[7] = "" + current_recovered;
	  	 
	  	 unvac_dead = 0;
	  	 one_dead = 0;
	  	 two_dead = 0;
	  	 three_dead = 0;
	  	 reinfected_dead = 0;
	  	 recovered_dead = 0;
	  	 infected_dead = 0;
	  	 
	  	 total_infected = 0;
	  	 recovered_infected = 0;
	  	 unvac_infected = 0;
	  	 one_infected = 0;
	  	 two_infected = 0;
	  	 three_infected = 0;

	  	 for(int i = 0; i < total_size; i++)
	  	 {
	  		   		 
	  		 //check dead people
	  		 if (!peopleArray.get(i).isAlive())
	  		 {
	  			 if (peopleArray.get(i).getInitialImmunityStatus() == ImmunityStatus.INITIALLY_INFECTED)
	  			 {
	  				 infected_dead++;
	  			 }
	  			 else if (peopleArray.get(i).isReInfected())
	  			 {
	  				 reinfected_dead++;
	  			 }
	  			 else
	  			 {
	  				 switch(peopleArray.get(i).getImmunityStatus())
			  		 {
							case NO_IMMUNITY:						unvac_dead++; 		break;
							case ONE_SHOT:							one_dead++;				break;
							case TWO_SHOT:							two_dead++; 			break;
							case THREE_SHOT:						three_dead++;			break;
							case NATURALLY_IMMUNE: 	
							case TWO_SHOT_RECOVERED:
							case THREE_SHOT_RECOVERED:	recovered_dead++;	break;
							default:	break;
			  		 }
	  			 }
	  		 }
	  		 //alive
	  		 else
	  		 {
	  			 //check infected people
	  			 if (peopleArray.get(i).isInfected())
	  			 {
	  				 total_infected++;
	  				 
	  				 if (peopleArray.get(i).isReInfected())
	  				 {
	  					 recovered_infected++;
	  				 }
	  				 else
	  				 {
	  					 switch(peopleArray.get(i).getImmunityStatus())
	  		  		 {
	  						case NO_IMMUNITY:	unvac_infected++; break;
	  						case ONE_SHOT:		one_infected++;		break;
	  						case TWO_SHOT:		two_infected++; 	break;
	  						case THREE_SHOT:	three_infected++;	break;
	  						default:	break;
	  		  		 }
	  				 }
	  			 }//end if (peopleArray.get(i).isInfected())
	  		 }//end if (!peopleArray.get(i).isAlive())
	  	 }//end for(int i = 0; i < peopleArray.size(); i++)  	 
	  	 
	  	 
	  	 //percentage for infected
	  	 infectedColumn[0] = String.format("%.2f", total_infected * 100.0 / total_size);
	  	 infectedColumn[1] = String.format("%.2f", unvac_infected * 100.0 / total_size);
	  	 infectedColumn[2] = String.format("%.2f", one_infected * 100.0 / total_size);
	  	 infectedColumn[3] = String.format("%.2f", two_infected * 100.0 / total_size);
	  	 infectedColumn[4] = String.format("%.2f", three_infected * 100.0 / total_size);
	  	 //it's not possible infected person is infected
	  	 infectedColumn[5] = "-";
	  	 infectedColumn[6] = String.format("%.2f", num_re_infected * 100.0 / total_size);
	  	 infectedColumn[7] = String.format("%.2f", recovered_infected * 100.0 / total_size);
	  	 
	  	 //percentage for dead
	  	 deadColumn[0] = String.format("%.2f", num_dead * 100.0 / total_size);
	  	 //prevent dividing by zero
	  	 if (init_unvac == 0)
	  		 deadColumn[1] = "-";
	  	 else
	  		 deadColumn[1] = String.format("%.2f", unvac_dead * 100.0 / init_unvac);
	  	 
	  	 if (init_one == 0)
	  		 deadColumn[2] = "-";
	  	 else
	  		 deadColumn[2] = String.format("%.2f", one_dead * 100.0 / init_one);
	  	 
	  	 if (init_two == 0)
	  		 deadColumn[3] = "-";
	  	 else
	  		 deadColumn[3] = String.format("%.2f", two_dead * 100.0 / init_two);
	  	 
	  	 if (init_three == 0)
	  		 deadColumn[4] = "-";
	  	 else
	  		 deadColumn[4] = String.format("%.2f", three_dead * 100.0 / init_three);
	  	 
	  	 if (init_infected == 0)
	  		 deadColumn[5] = "-";
	  	 else
	  		 deadColumn[5] = String.format("%.2f", infected_dead * 100.0 / init_infected);
	  	 
	  	 if (num_re_infected == 0)
	  		 deadColumn[6] = "-";
	  	 else
	  		 deadColumn[6] = String.format("%.2f", reinfected_dead * 100.0 / num_re_infected);
	  	 
	  	 if (init_recovered == 0)
	  		 deadColumn[7] = "-";
	  	 else
	  		 deadColumn[7] = String.format("%.2f", recovered_dead * 100.0 / init_recovered);
	  	 
	   }
	   
	   public void closeFrame()
	   {
	  	 this.setVisible(false);
	   }
}
