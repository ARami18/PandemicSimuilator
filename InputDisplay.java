/*
 * Program Name: InputDisplay.java
 * Purpose: Creates a JFrame which contains the BarChartPanel.java
 * Author:
 * Date: July 28th, 2022
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InputDisplay extends JFrame
{
	//The constructor takes SimulationStatus object and JFrame object
	//The JFrame object takes the JFrame object for setLocationRelativeTo(relativePosition)
	//method, so two JFrames can appear at the same location.
	public InputDisplay(SimulationStatus status, JFrame relativePosition) {
		 super("Bar Chart depending on your input");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(500, 400);
	    this.setLayout(new BorderLayout());
	    this.setVisible(true);
	    this.setLocationRelativeTo(relativePosition);
	    
	    //Creates the BarChartPanel JPanel within the JFrame.
	    BarChartPanel displayChart = new BarChartPanel(status);
	    this.add(displayChart, BorderLayout.CENTER);
	    
	    JPanel startButtonPanel = new JPanel();
	    JPanel populationPanel = new JPanel();
	    
	    //Population Panel displays the # of people with
	    //different immunity status above the bar charts
	    populationPanel.setLayout(new GridLayout(1,6));
	    
	    
	    // Place the # Label in the PopulationPanel
	    // above the bar charts	    
	    JLabel unvaccinatedCountLabel = new JLabel("("+status.getUnvaccinatedCount()+"/"+status.getTotalPopulation()+")");
	    unvaccinatedCountLabel.setHorizontalAlignment(JLabel.CENTER);
	    unvaccinatedCountLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  13));
	    
	    JLabel oneShotVaccinatedCountsLabel = new JLabel("("+status.getOneShotCount()+"/"+status.getTotalPopulation()+")");
	    oneShotVaccinatedCountsLabel.setHorizontalAlignment(JLabel.CENTER);
	    oneShotVaccinatedCountsLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  13));
	    
	    JLabel twoShotVaccinatedCountsLabel = new JLabel("("+status.getTwoShotCount()+"/"+status.getTotalPopulation()+")");
	    twoShotVaccinatedCountsLabel.setHorizontalAlignment(JLabel.CENTER);
	    twoShotVaccinatedCountsLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  13));
	    
	    JLabel threeShotVaccinatedCountsLabel = new JLabel("("+status.getThreeShotCount()+"/"+status.getTotalPopulation()+")");
	    threeShotVaccinatedCountsLabel.setHorizontalAlignment(JLabel.CENTER);
	    threeShotVaccinatedCountsLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  13));
	    
	    JLabel recoveredCountLabel = new JLabel("("+status.getRecoveredCount()+"/"+status.getTotalPopulation()+")");
	    recoveredCountLabel.setHorizontalAlignment(JLabel.CENTER);
	    recoveredCountLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  13));
	    
	    JLabel infectedCountLabel = new JLabel("("+status.getInfectedCount()+"/"+status.getTotalPopulation()+")");
	    infectedCountLabel.setHorizontalAlignment(JLabel.CENTER);
	    infectedCountLabel.setFont(new Font(Font.DIALOG, Font.BOLD,  13));
	    
	    populationPanel.add(unvaccinatedCountLabel);
	    populationPanel.add(oneShotVaccinatedCountsLabel);
	    populationPanel.add(twoShotVaccinatedCountsLabel);
	    populationPanel.add(threeShotVaccinatedCountsLabel);
	    populationPanel.add(recoveredCountLabel);
	    populationPanel.add(infectedCountLabel);
	    
	    startButtonPanel.setLayout(new FlowLayout());
	    JButton simulationStartButton = new JButton("Start the Simulation");
	    simulationStartButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e)
		{
			InputDisplay.this.setVisible(false);
		}
	   	 
	    });//end of ActionListener()
	    
	    startButtonPanel.add(simulationStartButton);
	    
	    this.add(startButtonPanel, BorderLayout.SOUTH);
	    this.add(populationPanel,BorderLayout.NORTH);
	    this.setVisible(true);
	}	
	
}
