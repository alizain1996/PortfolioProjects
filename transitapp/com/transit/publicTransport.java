package transitapp.com.transit;

import java.util.Date;

public class publicTransport {
	
	public double fareCost;
	private int numOfStops;
	private int numOfStations;
	protected int numOfStopsReached;
	protected int numOfStationsReached;
	public int totalTapIns;
	protected int totalTapOuts;
	protected int totalFaresCollected;
	
	/**
	 * Constructs a new public transport vehicle
	 * 
	 * @param fareCost    Each ride cost
	 * @param numOfStops  Number of stops in it's route
	 */
	public publicTransport(double fareCost, int numOfStops) {
		this.fareCost= fareCost;
		this.numOfStops= numOfStops;
		numOfStopsReached= 0;
	    totalTapIns= 0;
		totalTapOuts= 0;
		totalFaresCollected= 0;
	}
	
	
	/**
	 * Checks and returns the total amount of fares collected by the vehicle.
	 * 
	 * @return  returns the total amount of collected fares.
	 */
	public int totalFaresCollected() {
		return totalFaresCollected;
	}
	
	/**
	 * Checks and returns the total number of stops reached by the vehicle.
	 * 
	 * @return  returns the total number of stops reached by the vehicle.
	 */
	public int numOfStopsReached() {       //complete this when transit map has been made
		return numOfStopsReached;
	}
	
	
	
}