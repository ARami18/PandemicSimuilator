/**
 * Program Name : InputPrompt.java
 * Purpose: Put a Description here!
 * @author: Hongseok Kim
 * Date : 2022. 7. 28.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class InputPrompt extends JFrame
{
	private static SimulationStatus status_;
	
	int currentPopulation = 0;
	JPanel argumentInput = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	String[] numberList = {"0 %","5 %","10 %","15 %","20 %","25 %","30 %","35 %","40 %","45 %","50 %","55 %","60 %","65 %","70 %","75 %","80 %","85 %","90 %","95 %","100 %"};
	
	//a slider whose minimum value is 100, maximum value is 1000 and initial value is 100
	JSlider populationSlider = new JSlider(JSlider.HORIZONTAL, 100, 1000, 100);
	
	//creates ComboBox with element from 0% ~~ 100%
	JComboBox<String> unvaccinated = new JComboBox<String>(numberList);
	JComboBox<String> oneShotVaccinated = new JComboBox<String>(numberList);
	JComboBox<String> twoShotVaccinated = new JComboBox<String>(numberList);
	JComboBox<String> threeShotVaccinated = new JComboBox<String>(numberList);
	JComboBox<String> recovered = new JComboBox<String>(numberList);
	JComboBox<String> infected = new JComboBox<String>(numberList);
	
	
	String totalPopulationLabelString = "Total Population : ";
	JLabel totalPopulationLabel = new JLabel(totalPopulationLabelString);
	JLabel unvaccinatedLabel = new JLabel("Percentage of Unvaccinated");
	JLabel oneShotVaccinatedLabel = new JLabel("Percentage of Single Shot Vaccinated");
	JLabel twoShotVaccinatedLabel = new JLabel("Percentage of Two Shot Vaccinated");
	JLabel threeShotVaccinatedLabel = new JLabel("Percentage of Three Shot Vaccinated");
	JLabel recoveredLabel = new JLabel("Percentage of Recovered");
	JLabel infectedLabel = new JLabel("Percentage of Infected");
	
	JButton proceedButton = new JButton("Proceed");
	
	//constructor
	public InputPrompt() {
		super("Argument Inputs for Simulation");
		Font font  = new Font(Font.DIALOG, Font.PLAIN,  13);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setVisible(true);	
		
		//adding design to the Slider
		populationSlider.setMajorTickSpacing(100);	
		populationSlider.setPaintTicks(true);		
				
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(proceedButton);		
		argumentInput.setLayout(new GridLayout(7,2,15,15));
		
		//setting the background color of ComboBox to White
		unvaccinated.setBackground(Color.WHITE);
		oneShotVaccinated.setBackground(Color.WHITE);
		twoShotVaccinated.setBackground(Color.WHITE);
		threeShotVaccinated.setBackground(Color.WHITE);
		recovered.setBackground(Color.WHITE);
		infected.setBackground(Color.WHITE);
		
		totalPopulationLabel.setFont(font);
		unvaccinatedLabel.setFont(font);
		oneShotVaccinatedLabel.setFont(font);
		twoShotVaccinatedLabel.setFont(font);
		threeShotVaccinatedLabel.setFont(font);
		recoveredLabel.setFont(font);
		infectedLabel.setFont(font);
		
		//Adding the JLabels and JComboBoxes to
		//JPanel
		argumentInput.add(totalPopulationLabel);
		argumentInput.add(populationSlider);		
		argumentInput.add(unvaccinatedLabel);
		argumentInput.add(unvaccinated);
		argumentInput.add(oneShotVaccinatedLabel);
		argumentInput.add(oneShotVaccinated);
		argumentInput.add(twoShotVaccinatedLabel);
		argumentInput.add(twoShotVaccinated);
		argumentInput.add(threeShotVaccinatedLabel);
		argumentInput.add(threeShotVaccinated);
		argumentInput.add(recoveredLabel);
		argumentInput.add(recovered);
		argumentInput.add(infectedLabel);
		argumentInput.add(infected);
		
		//getting the current population from Slider, and set the value to JLabel
		//totalPopulationLabel
		currentPopulation = populationSlider.getValue();
		totalPopulationLabel.setText(totalPopulationLabelString + currentPopulation);
		this.add(argumentInput, BorderLayout.CENTER);		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		//add actionListener to the Proceed JButton
		proceedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {				
			try {				
				//discard " %" from elements of JComboBox to get the integer value for percentage
				int infectedPercentage=Integer.parseInt((infected.getSelectedItem().toString().substring(0, infected.getSelectedItem().toString().length()-2)));
				
				//# of Infected cannot be 0, validation
				if(infectedPercentage <=0)
				{
					JOptionPane.showMessageDialog(null,
							"The counts of infected must be higher than 0.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				//getting the total population and immunity status percentage
				//from comboboxes and slider
				//example : "15 %" becomes "15" and converted to int value by Integer.parseInt(String) method
				int totalPopulation = (int)Math.round(populationSlider.getValue()/100.0) * 100;
				int unvaccinatedPercentage =  Integer.parseInt((unvaccinated.getSelectedItem().toString().substring(0, unvaccinated.getSelectedItem().toString().length()-2)));
				int singleShotVaccinatedPercentage =  Integer.parseInt((oneShotVaccinated.getSelectedItem().toString().substring(0, oneShotVaccinated.getSelectedItem().toString().length()-2)));
				int doubleShotVaccinatedPercentage =  Integer.parseInt((twoShotVaccinated.getSelectedItem().toString().substring(0, twoShotVaccinated.getSelectedItem().toString().length()-2)));
				int tripleShotVaccinatedPercentage =  Integer.parseInt((threeShotVaccinated.getSelectedItem().toString().substring(0, threeShotVaccinated.getSelectedItem().toString().length()-2)));
				int recoveredPercentage =  Integer.parseInt((recovered.getSelectedItem().toString().substring(0, recovered.getSelectedItem().toString().length()-2)));
				
				//the sum of all Percentage values must be 100 to start
				int sumOfPercentageValues = unvaccinatedPercentage + singleShotVaccinatedPercentage
						+doubleShotVaccinatedPercentage + tripleShotVaccinatedPercentage + recoveredPercentage+infectedPercentage;
				if(sumOfPercentageValues != 100) {
					JOptionPane.showMessageDialog(null,
							"The sum of the percentage values must be 100.\n"  + 
							"Current sum of the percentage values is " + sumOfPercentageValues+".", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				//validation complete, initialize a SimulationStatus object that will be
				//used throughout the project
				status_ = new SimulationStatus(totalPopulation,
						unvaccinatedPercentage,
						singleShotVaccinatedPercentage,
						doubleShotVaccinatedPercentage,
						tripleShotVaccinatedPercentage,
						recoveredPercentage,
						infectedPercentage);
				
				//if status is set, set this JFrame visibility to false, and open
				//the next JFrame GUI window.
				if(status_ != null)
					InputPrompt.this.setVisible(false);
				
				//when user inputs non-numeric values, NumberFormatException will be thrown
				//and the user will be notified with JOptionPane Dialog box
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "Enter numeric characters only please", "Message", JOptionPane.INFORMATION_MESSAGE);				
				return;
			}
			
			
			}
		});
		
		//every time there is a change to the slider,
		//the text set in JLabel will change
		populationSlider.addChangeListener(new ChangeListener() {
	      public void stateChanged(ChangeEvent event) {
	        
	      	currentPopulation = (int)Math.round(populationSlider.getValue()/100.0) * 100;
	      	  
	      	totalPopulationLabel.setText(totalPopulationLabelString + currentPopulation);
	      }
		});
	}	 

	public SimulationStatus getStatus() {
		return status_;
	}
}
