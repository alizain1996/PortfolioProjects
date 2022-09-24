package transitapp.com.transit;
import java.util.ArrayList;
/**
 * The trip class keeps track of all the information of the trip such as start time, destination etc.
 */

public class Trip {
	
	public long startingTime;
	private Card ridersCard;
	public String startingPoint;
	public String destination;
	public boolean freetapout;
	public double fareCollected;
	
	/**
	 * Constructs a new trip for a cardholder.
	 * @param startingTime the start time of the trip.
	 * @param ridersCard the card used by the cardholder.
	 * @param startingPoint the starting point of the trip.
	 */
	
	public Trip(long startingTime, Card ridersCard, String startingPoint) {
		this.startingTime = startingTime;
		this.startingPoint = startingPoint;
		this.ridersCard = ridersCard;
		this.fareCollected = 0;
		this.freetapout = false;
		this.destination = "";
	}
	
	/**
	 * Changes the destination of the trip to the given destination.
	 * @param destination the destination of the trip.
	 */
	public void destinationReached(String destination) {
		this.destination = destination;
	}
	/**
	 * Adds the farecost to the attribute fareCollected.
	 * @param fareCost the fare cost.
	 */
	public void addExpense(double fareCost) {
		this.fareCollected = fareCost;
	}
	/**
	 * changes the attribute freetapout to true if cardholder taps in and taps out at the same location
	 */
	
	public void freeRide() {
		this.freetapout = true;
	}
	/**
	 * Prints out a statement about the starting point and destination of the whole trip.
	 */
	
	public void printTrip() {
		System.out.println("The trip started from "+ this.startingPoint + "and ended at " + this.destination);
	}
	
}
