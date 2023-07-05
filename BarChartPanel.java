/*
 * Program Name: BarChartPanel.java
 * Purpose: Creates a JPanel of Bar Chart, based on the values received by the 
 *          SimulationStatus object created by user at InputPrompt.java.
 * Author:
 * Date: July 28th, 2022
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 

public class BarChartPanel extends JPanel {
 
  private int totalPopulation;
  private double[] values;
  private String[] labels;
  private Color[] colors;
  private String title;
 
  //Constructor takes a SimulationStatus object as an argument
  public BarChartPanel(SimulationStatus status) {
	  
	  //The total population
	  totalPopulation = status.getTotalPopulation();
	  
	  //The counts of people with different immunity status
	  values = new double[]{
	   		 status.getUnvaccinatedCount(),
	   		 status.getOneShotCount(),
	   		 status.getTwoShotCount(),
	   		 status.getThreeShotCount(),
	   		 status.getRecoveredCount(),
	   		 status.getInfectedCount()};
	    
	  //Labels to be placed under each bar chart
	  labels = new String[]{"Unvaccinated",
	   		 "Single Shot",
	   		 "Two Shot",
	   		 "Three Shot",
	   		 "Natural Imm.",
	   		 "Infected"};
	  //Each color represents the color of the bar chart
	  colors = new Color[]{
	          Color.WHITE,
	          Color.CYAN,
	          Color.YELLOW,
	          Color.MAGENTA,
	          Color.GREEN,
	          Color.RED
	      };
	  
	  title = "";
	  
  }
 
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    //The paintComponent method does not begin if the inputs are empty
    if (values == null || values.length == 0) {
      return;
    }
 
    double minValue = 0;
    double maxValue = 0;
    
    //finding the minimum and maximum values
    for (int i = 0; i < values.length; i++) {
      if (minValue > values[i]) {
        minValue = values[i];
      }
      if (maxValue < values[i]) {
        maxValue = values[i];
      }
    }
 
    //Dimension class supports drawing of rectangular shaped objects
    //getSize() returns the size of the JPanel in type of Dimension class
    Dimension dim = getSize();
    int panelWidth = dim.width;
    int panelHeight = dim.height;
    
    //The width of each bar depends on the # of the bar charts
    int barWidth = panelWidth / values.length;
 
    Font titleFont = new Font(Font.DIALOG, Font.BOLD,  13);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
 
    Font labelFont = new Font(Font.DIALOG, Font.PLAIN,  13);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
 
    //Writing the title at the top of the JPanel (The title string is currently
    //empty, so nothing will appear at this moment)
    int titleWidth = titleFontMetrics.stringWidth(title);
    int stringHeight = titleFontMetrics.getAscent();
    int stringWidth = (panelWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, stringWidth, stringHeight);
 
    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue) {
      return;
    }    
    
    double scale = (panelHeight - top - bottom) / (maxValue - minValue);
    stringHeight = panelHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);
    
    //drawing the bar charts with corresponding labels
    for (int j = 0; j < values.length; j++) {
   	 
      int valueP = j * barWidth + 1;
      int valueQ = top;
      int height = (int) (values[j] * scale);
      if (values[j] >= 0) {
        valueQ += (int) ((maxValue - values[j]) * scale);
      } else {
        valueQ += (int) (maxValue * scale);
        height = -height;
      }
 
      g.setColor(colors[j]);
      g.fillRect(valueP, valueQ, barWidth - 2, height);
      g.setColor(Color.black);
      g.drawRect(valueP, valueQ, barWidth - 2, height);
 
      int labelWidth = labelFontMetrics.stringWidth(labels[j]);
      stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
      g.drawString(labels[j], stringWidth, stringHeight);
    }
  } 

}