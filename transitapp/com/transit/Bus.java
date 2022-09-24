package transitapp.com.transit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bus extends publicTransport{
	public int totalTapOuts;
	
	/**
	 * Constructs a new bus
	 * 
	 * @param fareCost    Each bus ride cost
	 * @param numOfStops  Number of stops in it's route
	 */
	public Bus(int fareCost, int numOfStops){
		super(fareCost, numOfStops);
		numOfStopsReached= 0;
	    totalTapIns= 0;
		totalTapOuts= 0;
		totalFaresCollected= 0;
	}
	
	/**
	 * Checks whether card balance is enough for the trip and then taps in the card. If less balance
	 * than prints and asks for the remaining amount. It also checks the time and recent trips
	 * made from the card.
	 * 
	 * @param ridersCard  The card which is going to be used
	 * @return  True if successfully charged and tapped in, False otherwise.
	 */
	public boolean tapIn(Card ridersCard , String startingPoint) {             
		Date currentClock=new Date();                  
		long miliSecIn2Hr = 7200000;
		ArrayList<Trip> lastTwohrTrips = new ArrayList<Trip>();
		if (ridersCard.tripsOnCard.size()>0) {
			for (int count = 0; count < ridersCard.tripsOnCard.size() ; count ++) {
				if ( currentClock.getTime() - ridersCard.tripsOnCard.get(count).startingTime < miliSecIn2Hr) { 
					lastTwohrTrips.add(ridersCard.tripsOnCard.get(count));
				}
			}
		}
		int totalCollectedinlastTwohrTrips = 0;
		if (ridersCard.tripsOnCard.size()>0) {
			for (int counts = 0; counts < lastTwohrTrips.size(); counts++) {
				totalCollectedinlastTwohrTrips += lastTwohrTrips.get(counts).fareCollected;
			}
		}
		if (totalCollectedinlastTwohrTrips >=6 ) {
			totalTapIns+=1;
			ridersCard.tapStatus=true;
			Trip newTrip = new Trip(currentClock.getTime(), ridersCard, startingPoint );
			ridersCard.tripsOnCard.add(newTrip);
			return true;
		}if (totalCollectedinlastTwohrTrips > 4.0 && totalCollectedinlastTwohrTrips < 6.5) {
			ridersCard.deductBalance(Math.abs(6.0-totalCollectedinlastTwohrTrips));
			if (ridersCard.cardBalance>12.5) {
				ridersCard.cardBalance=13;
			}else if (ridersCard.cardBalance<9) {
				ridersCard.cardBalance=7;
			}
			totalTapIns+=1;
			totalFaresCollected+=Math.abs(6.0-totalCollectedinlastTwohrTrips);
			ridersCard.tapStatus=true;
			Trip newTrip = new Trip(currentClock.getTime(), ridersCard, startingPoint );
			newTrip.addExpense(Math.abs(6.0-totalCollectedinlastTwohrTrips));
			ridersCard.tripsOnCard.add(newTrip);
			return true;
		}
		if (ridersCard.cardBalance >= 2) {                      
				ridersCard.deductBalance(fareCost);
				totalTapIns+=1;
				totalFaresCollected+=fareCost;
				ridersCard.tapStatus=true;
				Trip newTrip = new Trip(currentClock.getTime(), ridersCard, startingPoint );
				newTrip.addExpense(fareCost);
				ridersCard.tripsOnCard.add(newTrip);
				return true;	
			}
		
		System.out.println("Your card balance is not enough for the ride, please recharge card!");
		return false;
	}
	
	/**
	 * If the card was previously tapped in, it taps it out, otherwise prints and points out that 
	 * the card has not been registered for the specific trip. It also adds destination in the current trip
	 * 
	 * @param ridersCard  The card which is going to be used
	 * @return  True if successfully tapped out, False otherwise.
	 */
	public boolean tapout(Card ridersCard, String destination) {
		if (ridersCard.tapStatus==true) {
			int size = ridersCard.tripsOnCard.size();
			ridersCard.tripsOnCard.get(size-1).destinationReached(destination);
			ridersCard.tapStatus=false;
			totalTapOuts+=1;
			System.out.println(totalFaresCollected);
			return true;
		}
		System.out.println("The card was not tapped in!");
		return false; 
	}	
	
}
