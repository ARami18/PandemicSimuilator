/**
 * Program Name: About_us.java
 * Purpose: uses a GridLayout for this panel.
 * 
 * 
 * Date: 
 */
import java.awt.*;
import java.awt.event.*;


import javax.imageio.ImageIO;
import javax.swing.*;

public class About_us extends JPanel
{
	private Font normalFont;
	private Font largeFont;
	//constructor
	public About_us() 
	{
		super();
		try {	
		//set up
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new GridLayout(2,2) );
		normalFont = new Font("SansSerif", Font.PLAIN, 25);
		largeFont = new Font("Serif", Font.BOLD, 25);
		setOpaque(true);
    setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    
    //About Juyoun information ---------------------- start
    
    JPanel btnPanel1= new JPanel();
    btnPanel1.setLayout(new BorderLayout());
    btnPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    JTextArea textArea11 = new JTextArea(" Juyoun Kim ");
    JTextArea textArea12 = new JTextArea(" Korean ");
    JTextArea textArea13 = new JTextArea(" 0985724 ");
    JTextArea textArea14 = new JTextArea(" Female ");
    textArea11.setFont(normalFont);
		
    JPanel namePanel1 = new JPanel();
    namePanel1.setLayout(new FlowLayout());
    
    namePanel1.add(textArea11);
    namePanel1.add(textArea12);
    namePanel1.add(textArea13);
    namePanel1.add(textArea14);
    btnPanel1.add(namePanel1, BorderLayout.NORTH);
		
		JButton btn1 = new JButton();
		btn1.setSize(50,50);
    ImageIcon juyoun = new ImageIcon("juyoun.png");
    btn1.setIcon(juyoun);
		btnPanel1.add(btn1, BorderLayout.CENTER);
		
		//About Hongseok information ---------------------- start
		
		JPanel btnPanel2= new JPanel();
		btnPanel2.setLayout(new BorderLayout());
		btnPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		JTextArea textArea21 = new JTextArea(" Hongseok Kim ");
    JTextArea textArea22 = new JTextArea(" Korean ");
    JTextArea textArea23 = new JTextArea(" 1015897 ");
    JTextArea textArea24 = new JTextArea(" Male ");
    textArea21.setFont(normalFont);
		
    JPanel namePanel2 = new JPanel();
    namePanel2.setLayout(new FlowLayout());
    
    namePanel2.add(textArea21);
    namePanel2.add(textArea22);
    namePanel2.add(textArea23);
    namePanel2.add(textArea24);
    btnPanel2.add(namePanel2, BorderLayout.NORTH);
		
		JButton btn2 = new JButton();
		btn2.setSize(50,50);
    ImageIcon hongseok = new ImageIcon("hongseok.png");
    btn2.setIcon(hongseok);
    btnPanel2.add(btn2, BorderLayout.CENTER);
		
    //About Huigon information ---------------------- start
    
		JPanel btnPanel3= new JPanel();
		btnPanel3.setLayout(new BorderLayout());
		btnPanel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    JTextArea textArea31 = new JTextArea(" Huigon Shin ");
    JTextArea textArea32 = new JTextArea(" Korean ");
    JTextArea textArea33 = new JTextArea(" 1030403 ");
    JTextArea textArea34 = new JTextArea(" Male ");
    textArea31.setFont(normalFont);
		
    JPanel namePanel3 = new JPanel();
    namePanel3.setLayout(new FlowLayout());
		
    namePanel3.add(textArea31);
    namePanel3.add(textArea32);
    namePanel3.add(textArea33);
    namePanel3.add(textArea34);
    btnPanel3.add(namePanel3, BorderLayout.NORTH);
		
		JButton btn3 = new JButton();
		btn3.setSize(50,50);
    ImageIcon huigon = new ImageIcon("huigon.png");
    btn3.setIcon(huigon);
    btnPanel3.add(btn3, BorderLayout.CENTER);
    
    //About arckey information ---------------------- start
    
		JPanel btnPanel4= new JPanel();
		btnPanel4.setLayout(new BorderLayout());
		btnPanel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    JTextArea textArea41 = new JTextArea(" Alejandro Ramirez  ");
    JTextArea textArea42 = new JTextArea(" Colombian ");
    JTextArea textArea43 = new JTextArea(" 0971808 ");
    JTextArea textArea44 = new JTextArea(" Male ");
    textArea41.setFont(normalFont);
		
    JPanel namePanel4 = new JPanel();
    namePanel4.setLayout(new FlowLayout());
		
    namePanel4.add(textArea41);
    namePanel4.add(textArea42);
    namePanel4.add(textArea43);
    namePanel4.add(textArea44);
    btnPanel4.add(namePanel4, BorderLayout.NORTH);
		
		JButton btn4 = new JButton();
		btn4.setSize(50,50);
    ImageIcon arckey = new ImageIcon("arckey.png");
    btn4.setIcon(arckey);
		btnPanel4.add(btn4, BorderLayout.CENTER);
		
		//add them to the JPanel
		this.add(btnPanel1);
    this.add(btnPanel2);
		this.add(btnPanel3);
		this.add(btnPanel4);
		}
		catch(Exception ex)
		{
			System.out.println("Some other Exception, message is: " + ex.getMessage());
			ex.printStackTrace();
		}
	}//end constructor
	
}//end class