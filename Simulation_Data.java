/*
 * Program Name: Simulation_Data
 * Purpose: GUI application that will show simulation data and coder's information
 * Author:
 * Date: July 28th, 2022
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Simulation_Data extends JFrame
{
  
	public Simulation_Data(SimulationStatus status)
	{
		//Set application Title
	
		try {
			    // build the JFrame here in the main 
				JFrame frame = new JFrame("Simulation Display"); //team name add?
				
				//boilerplate
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(1220,850);
				frame.setLocationRelativeTo(null);
				
				//create a JTabbedPane object
				JTabbedPane tPane = new JTabbedPane();
				
				//now call the JPanel constructor classes and add each one to the JTabbedPane.
				// First parameter is the text that appears on the tab, second parameter is
				// the component being added to the tab. In this case it is a JPanel			
				
				
				//add fifth panel showing vertical BoxLayout
				tPane.add("Simulation", new SimulationBoxPanel(status));
				//add the third panel showing GridLayout
				tPane.add("About us", new About_us() );
								
				
				//add the JTabbedPane to the JFrame
				frame.add(tPane);
				
				//last line
				frame.setVisible(true);		
			}//end try
			catch(Exception ex)
			{
				System.out.println("Some other Exception, message is: " + ex.getMessage());
				ex.printStackTrace();
			}
		
	}
	
	public final JFrame getSimFrame() {
		return this;
    }
}
//End class