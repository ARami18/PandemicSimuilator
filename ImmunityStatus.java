/**
 * Name: ImmunityStatus.java
 * Purpose: To set enum for immunity status of Person object
 * @author: Huigon Shin, 1030403
 *  					PLEASE INSERT NAME AND STUDENT NUMBER HERE
 * Date: July 28, 2022
 */

public enum ImmunityStatus
{
	NO_IMMUNITY,			
	ONE_SHOT,					
	TWO_SHOT,					
	THREE_SHOT,				
	NATURALLY_IMMUNE,	
	TWO_SHOT_RECOVERED,		//this is recovered person who took two shot
	THREE_SHOT_RECOVERED,	//this is recovered person who took three shot
	INITIALLY_INFECTED		//assume as NO_IMMUNITY
}
//End class