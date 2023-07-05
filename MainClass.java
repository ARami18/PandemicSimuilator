import java.awt.*;
import javax.swing.*;

/**
 * Program Name : MainClass.java
 * Purpose: Put a Description here!
 * @author: Hongseok Kim, Alejandro Ramirez, Huigon Shin, Juyeong Kim
 * Date : 2022. 7. 28.
 */
public class MainClass
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		InputPrompt prompt = new InputPrompt();
		
		//There could be a better way to transfer from a GUI to another, 
		//but this is the only way I could think of for now :(
		while(prompt.isVisible()) {
			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		//the InputPrompt's visibility will be set to false 
		//when everything is set and the next JFrame window will open
		SimulationStatus status = prompt.getStatus();		
		System.out.println(status.toString());
		
		InputDisplay display = new InputDisplay(status, prompt);
		
		while(display.isVisible()) {
			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		Simulation_Data simulation = new Simulation_Data(status);
		while(simulation.isVisible()) {
			try
			{
				Thread.sleep(1);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
//		new ResultDisplay(status);
		;
	}

}
