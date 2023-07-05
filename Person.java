/**
 * Name: Person.java
 * Purpose: To create objects of people to simulate 
 * 					infection when in contact with each other.
 * @author: Huigon Shin, 1030403
 * 					
 * Date: July 28, 2022
 */

import java.awt.Color;

public class Person
{
	//private int id;
	private boolean isAlive;
	private boolean isInfected;
	private boolean isReInfected;
	private ImmunityStatus immunityStatus;
	private ImmunityStatus initialImmunityStatus;
	private Color color;
	
	private int xCoordinate;
	private int yCoordinate;
	private int xIncrementValue;
	private int yIncrementValue;
	private int cycleCounter;
	
	//for diameter of Person object
	private int diameter;
	
	//constructors
	//should add or modify as needed
	public Person(int diam, ImmunityStatus InputImmunityStatus, int windowWidth, int windowHeight)
	{
		this.isAlive = true;
		this.isInfected = false;
		this.immunityStatus = InputImmunityStatus;
		this.setInitialImmunityStatus(InputImmunityStatus);
		this.diameter = diam;	
		this.cycleCounter = 0;
		setColor();	//depends on this.immunityStatus
		
		//Set these as random numbers when initiating
		setxCoordinate(windowWidth);
		setyCoordinate(windowHeight);
		setxIncrementValue();
		setyIncrementValue();		
	}//end constructor
	
	//methods
	
	/**
	* This determines how the Person object moves in each cycle.
	* When an object is infected, checks if it has moved 150 times and makes it dead or alive.
	*
	* @param  windowWidth  width of the fixed application window 
	* @param  windowHeight height of the fixed application window
	* @return      
	*/
	public void move(int windowWidth, int windowHeight)
	{
		if(!isAlive)
		{
			this.xIncrementValue = 0;
			this.yIncrementValue = 0;
		}
		else
		{
			//Check if Person has reached to the end of the window, and if so, change the direction
			if((this.xCoordinate >= windowWidth - this.diameter) || this.xCoordinate <= 0)
			{
				this.xIncrementValue *= -1; 
			}
			if((this.yCoordinate >= windowHeight - this.diameter) || this.yCoordinate <= 0)
			{
				this.yIncrementValue *= -1; 
			}
			
		}//end if(!isAlive)
		
		this.xCoordinate += this.xIncrementValue;
		this.yCoordinate += this.yIncrementValue;
		
		//Check if infected
		if (this.isInfected)
		{
			//Determine to be dead the person if reached 150 cycles
			if (this.cycleCounter == 150)
			{
				checkDied(this);
			}
			else
			{
				cycleCounter++;					
			}
		}//end if (this.isInfected)
	}//end move()
	
	/**
	* This checks if a collision between two people has occurred.
	* If a collision has occurred, set two people's movements using random numbers.
	* Also checks if people are infected
	*
	* @param  Person  an object to determine a collision and infection
	* @return      
	*/
	public void checkCollision(Person person)
	{		
		//If no, get coordinate differences between two people 
		int deltaX = this.xCoordinate - person.getxCoordinate();
		int deltaY = this.yCoordinate - person.getyCoordinate();
		
		//Check if two people have contacted by calculating the distance between them
		if (Math.sqrt(deltaX *deltaX + deltaY * deltaY) <= this.diameter)//if true, they have touched
		{
			this.setxIncrementValue();
			this.setyIncrementValue();
			
			person.setxIncrementValue();
			person.setyIncrementValue();
			
			//Check if one of them is infected, if so, check if uninfected person becomes infected
			if (this.isAlive() && person.isAlive())
			{
				if (this.isInfected)
				{
					if (!person.isInfected())
					{
						checkInfection(person);
					}
				}
				else if (person.isInfected())
				{
					if (!this.isInfected)
					{
						checkInfection(this);
					}
				}//end if (this.isInfected)
			}//end if (this.isAlive() && person.isAlive())
		}//end if (Math.sqrt(deltaX *deltaX + deltaY * deltaY)
	}//end checkCollision()
	
	/**
	* This checks if a person is died or not after reaching 150 cycles and being infected depending on immunityStatus.
	* When a person is alive, NO_IMMUNITY or ONE_SHOT person becomes as NATURALLY_IMMUNE.
	* Also, set the color of the object with the currently changed immunityStatus.
	*
	* @param  Person  an object to determine to be dead, and immunityStatus when being alive.
	* @return      
	*/
	public void checkDied(Person person)
	{
		ImmunityStatus immunityStatus = person.getImmunityStatus();
		
		switch(immunityStatus)
		{
		case NO_IMMUNITY:	//10% to be dead
		case INITIALLY_INFECTED:
			if (Math.random() <= 0.1) { person.setAlive(false); }
			else { person.setInfected(false); person.setImmunityStatus(ImmunityStatus.NATURALLY_IMMUNE); cycleCounter = 0; }
			break;
		case ONE_SHOT:	//7% to be dead
			if (Math.random() <= 0.07) { person.setAlive(false); }
			else { person.setInfected(false); person.setImmunityStatus(ImmunityStatus.NATURALLY_IMMUNE); cycleCounter = 0; }
			break;
		case TWO_SHOT:	//3% to be dead
			if (Math.random() <= 0.03) { person.setAlive(false); }
			//This person has been applied a stronger vaccine than naturally immune 
			else { person.setInfected(false); person.setImmunityStatus(ImmunityStatus.TWO_SHOT_RECOVERED); cycleCounter = 0; }
			break;
		case THREE_SHOT:	//1% to be dead
			if (Math.random() <= 0.01) { person.setAlive(false); }
			//This person has been applied a stronger vaccine than naturally immune 
			else { person.setInfected(false); person.setImmunityStatus(ImmunityStatus.THREE_SHOT_RECOVERED); cycleCounter = 0; }
			break;
		case NATURALLY_IMMUNE:	//3% to be dead
			if (Math.random() <= 0.03) { person.setAlive(false); }
			//This person already has naturally immune
			else { person.setInfected(false); cycleCounter = 0; }
			break;
		case TWO_SHOT_RECOVERED:	//3% to be dead
			if (Math.random() <= 0.03) { person.setAlive(false); }
			//This person has been applied a stronger vaccine than naturally immune 
			else { person.setInfected(false); cycleCounter = 0; }
			break;
		case THREE_SHOT_RECOVERED:	//1% to be dead
			if (Math.random() <= 0.01) { person.setAlive(false); }
			//This person has been applied a stronger vaccine than naturally immune 
			else { person.setInfected(false); cycleCounter = 0; }
			break;
		}
		person.setColor();
	}
	
	/**
	* This checks if a person is infected depending on immunityStatus
	* Also, set the color of the object with the currently changed immunityStatus.
	*
	* @param  Person  an object to determine to be infected.
	* @return      
	*/
	public void checkInfection(Person person)
	{
		ImmunityStatus immunityStatus = person.getImmunityStatus();
		
		switch(immunityStatus)
		{
		case NO_IMMUNITY:	//80% to be infected
			if (Math.random() <= 0.8) { person.setInfected(true); }
			break;
		case ONE_SHOT:	//60% to be infected
			if (Math.random() <= 0.6) { person.setInfected(true); }
			break;
		case TWO_SHOT:	//30% to be infected
			if (Math.random() <= 0.3) { person.setInfected(true); }
			break;
		case THREE_SHOT:	//10% to be infected
			if (Math.random() <= 0.1) { person.setInfected(true); }
			break;
		case NATURALLY_IMMUNE:	//40% to be infected
			if (Math.random() <= 0.4) { person.setInfected(true); person.setReInfected(true); }
			break;
		case TWO_SHOT_RECOVERED:	//30% to be infected
			if (Math.random() <= 0.3) { person.setInfected(true); person.setReInfected(true); }
			break;
		case THREE_SHOT_RECOVERED:	//10% to be infected
			if (Math.random() <= 0.1) { person.setInfected(true); person.setReInfected(true); }
			break;
		default:
			break;
		}
		person.setColor();
	}
	
	//getters and setters
	public boolean isAlive()
	{
		return isAlive;
	}

	public void setAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}

	public boolean isInfected()
	{
		return isInfected;
	}

	public void setInfected(boolean isInfected)
	{
		this.isInfected = isInfected;
	}

	public ImmunityStatus getImmunityStatus()
	{
		return immunityStatus;
	}

	public void setImmunityStatus(ImmunityStatus immunityStatus)
	{
		this.immunityStatus = immunityStatus;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor()
	{
		if (!isAlive)
		{
			color = Color.black;
		}
		else
		{
			if (isInfected)
			{
				color = Color.red;
			}
			else
			{
				switch(immunityStatus)
				{
					case NO_IMMUNITY:						color = Color.blue; 		break;
					case ONE_SHOT: 							color = Color.cyan; 		break;
					case TWO_SHOT:
					case TWO_SHOT_RECOVERED:		color = Color.yellow; 	break;
					case THREE_SHOT: 
					case THREE_SHOT_RECOVERED:	color = Color.magenta; 	break;
					case NATURALLY_IMMUNE: 			color = Color.green;		break;
					default: break;
				}
			}
		}
	}

	public int getxCoordinate()
	{
		return xCoordinate;
	}

	public void setxCoordinate(int windowWidth)
	{
		boolean isRunning = true;
		
		while(isRunning)
		{
			int x = (int)(Math.random() * windowWidth);
			if(x >= 0 && x <= windowWidth - this.diameter)
			{
				this.xCoordinate = x;
				isRunning = false;
			}
		}//end while
	}

	public int getyCoordinate()
	{
		return yCoordinate;
	}

	public void setyCoordinate(int windowHeight)
	{
		boolean isRunning = true;
		
		while(isRunning)
		{
			int y = (int)(Math.random() * windowHeight);
			if(y >= 0 && y <= windowHeight - this.diameter)
			{
				this.yCoordinate = y;
				isRunning = false;
			}
		}//end while
	}

	public int getxIncrementValue()
	{
		return xIncrementValue;
	}

	public void setxIncrementValue()
	{
		this.xIncrementValue = (int)(Math.random()*10 - 5);
	}

	public int getyIncrementValue()
	{
		return yIncrementValue;
	}

	public void setyIncrementValue()
	{
		this.yIncrementValue = (int)(Math.random()*10 - 5);
	}

	public int getCycleCounter()
	{
		return cycleCounter;
	}

	public void setCycleCounter(int cycleCounter)
	{
		this.cycleCounter = cycleCounter;
	}

	public int getDiameter()
	{
		return diameter;
	}

	public void setDiameter(int diameter)
	{
		this.diameter = diameter;
	}

	public ImmunityStatus getInitialImmunityStatus()
	{
		return initialImmunityStatus;
	}

	public void setInitialImmunityStatus(ImmunityStatus initialImmunityStatus)
	{
		this.initialImmunityStatus = initialImmunityStatus;
	}

	public boolean isReInfected()
	{
		return isReInfected;
	}

	public void setReInfected(boolean isReInfected)
	{
		this.isReInfected = isReInfected;
	}

}//End class