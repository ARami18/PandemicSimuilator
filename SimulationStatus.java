/**
 * Program Name : SimulationStatus.java
 * Purpose: Put a Description here!
 * @author: Hongseok Kim
 * Date : 2022. 7. 28.
 */

/**
 * @author Hongseok Kim
 *
 */

public class SimulationStatus
{
	private int totalPopulation;
	private int unvaccinatedCount;
	private int oneShotCount;
	private int twoShotCount;
	private int threeShotCount;
	private int recoveredCount;
	private int infectedCount;
	
	public SimulationStatus() {
		this.totalPopulation = 0;
		this.unvaccinatedCount = 0;
		this.oneShotCount = 0;
		this.twoShotCount = 0;
		this.threeShotCount = 0;
		this.recoveredCount = 0;
		this.infectedCount = 0;
	}
	
	
	public SimulationStatus(int totalPopulation,
	int unvaccinatedCount,
	int oneShotCount,
	int twoShotCount,
	int threeShotCount,
	int recoveredCount,
	int infectedCount) {
		this.totalPopulation = totalPopulation;
		this.unvaccinatedCount = totalPopulation/100 * unvaccinatedCount;
		this.oneShotCount = totalPopulation/100 * oneShotCount;
		this.twoShotCount = totalPopulation/100 * twoShotCount;
		this.threeShotCount = totalPopulation/100 * threeShotCount;
		this.recoveredCount = totalPopulation/100 * recoveredCount;
		this.infectedCount = totalPopulation/100 * infectedCount;
	}
	
	public int getTotalPopulation()
	{
		return totalPopulation;
	}

	public void setTotalPopulation(int totalPopulation)
	{
		this.totalPopulation = totalPopulation;
	}

	public int getUnvaccinatedCount()
	{
		return unvaccinatedCount;
	}

	public void setUnvaccinatedCount(int unvaccinatedCounts)
	{
		this.unvaccinatedCount = unvaccinatedCounts;
	}

	public int getOneShotCount()
	{
		return oneShotCount;
	}

	public void setOneShotCount(int oneShotCount)
	{
		this.oneShotCount = oneShotCount;
	}

	public int getTwoShotCount()
	{
		return twoShotCount;
	}

	public void setTwoShotCount(int twoShotCount)
	{
		this.twoShotCount = twoShotCount;
	}

	public int getThreeShotCount()
	{
		return threeShotCount;
	}

	public void setThreeShotCount(int threeShotCount)
	{
		this.threeShotCount = threeShotCount;
	}

	public int getRecoveredCount()
	{
		return recoveredCount;
	}

	public void setRecoveredCount(int recoveredCount)
	{
		this.recoveredCount = recoveredCount;
	}

	public int getInfectedCount()
	{
		return infectedCount;
	}

	public void setInfectedCount(int infectedCount)
	{
		this.infectedCount = infectedCount;
	}

	
	@Override
	public String toString() {
		return "Total Population : " + totalPopulation +
				"\nUnvaccinated Population : " + unvaccinatedCount + 
				"\none Shot Population : " + oneShotCount + 
				"\nTwo Shot Population : " + twoShotCount + 
				"\nThree Shot Population : " + threeShotCount + 
				"\nRecovered Population : " + recoveredCount + 
				"\nInfected Population : " + infectedCount;
	}
}
