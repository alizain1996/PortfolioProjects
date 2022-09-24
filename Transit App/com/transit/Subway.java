package transitapp.com.transit;
import java.util.ArrayList;
import java.util.Date;

import transitapp.Main;

public class Subway extends publicTransport{
	public int totalTapOuts;
	public int totalTapIns;
	
	/**
	 * Constructs a new subway train.
	 * 
	 * @param fareCost    Each station ride cost for subway
	 * @param numOfStations  Number of stations in it's route
	 */
	public Subway(double fareCost, int numOfStations){
		super(fareCost, numOfStations);
		numOfStationsReached= 0;
	    totalTapIns= 0;
		totalTapOuts= 0;
		totalFaresCollected= 0;
	}
	
	/**
	 * Checks whether card is tapped out and balance is not in negatives. Then taps the card in.
	 * 
	 * @param ridersCard  The card that is being used.
	 * @return  returns true if successfully tapped in, false otherwise.
	 */
	public boolean tapIn(Card ridersCard, String StartingPoint) {                   
		Date currentClock = new Date();                      
		ArrayList<Trip> lastTwohrTrips = new ArrayList<Trip>();
		if (ridersCard.tripsOnCard.size()>1) {
			if (ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).freetapout) {
				ridersCard.tapStatus=true;
				totalTapIns+=1;
				System.out.println("Successfully tapped in!");
				return true;
		}
		}
		for (int count = 0; count < ridersCard.tripsOnCard.size() ; count ++) {
			if ( currentClock.getTime() - ridersCard.tripsOnCard.get(count).startingTime < 7200000) { 
				lastTwohrTrips.add(ridersCard.tripsOnCard.get(count));
			}
		}
		int totalCollectedinlastTwohourTrips = 0;
		if (lastTwohrTrips.size()>0) {
			for (int counts = 0; counts < lastTwohrTrips.size(); counts++) {
				totalCollectedinlastTwohourTrips += lastTwohrTrips.get(counts).fareCollected;
			}
		}
		if (totalCollectedinlastTwohourTrips >= 6) {
			Trip newTrip = new Trip(currentClock.getTime(), ridersCard, StartingPoint );
			newTrip.freeRide();
			ridersCard.tripsOnCard.add(newTrip);
			ridersCard.tapStatus=true;
			totalTapIns+=1;
			return true;
		}
		if (ridersCard.isNegative()==false) {
			ridersCard.totalCollectedinlastTwohrTrips = totalCollectedinlastTwohourTrips;
			Trip newTrip = new Trip(currentClock.getTime(), ridersCard, StartingPoint );
			ridersCard.tripsOnCard.add(newTrip);
			ridersCard.tapStatus=true;
			totalTapIns+=1;
			System.out.println("Successfully tapped in!");
			return true;
		}
		System.out.println("Your card balance is in negatives, please recharge your card!");
		return false;
	}
	
	/**
	 * Checks whether card balance has been enough for the trip. If less balance
	 * than prints and asks for the remaining amount. It also checks the time and recent trips
	 * made from the card. Then taps out the card.
	 * 
	 * @param ridersCard  The card that is being used.
	 * @return  True if successfully charged and tapped out, false otherwise.
	 */
	public boolean tapOut(Card ridersCard, String destination) {
		if (ridersCard.tapStatus==true) {
			if (ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).freetapout) {
				if (ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).destination=="") {
					ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).destinationReached(destination);
				}
				ridersCard.tapStatus=false;
				return true;
				
			}else {
				String startStation = ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).startingPoint;
				int stationsCrossed = Main.subwayStations.indexOf(startStation) - Main.subwayStations.indexOf(destination);
				stationsCrossed = Math.abs(stationsCrossed);
				double totalspent = (fareCost * stationsCrossed) + ridersCard.totalCollectedinlastTwohrTrips;
				if (totalspent >= 6) {
					if (ridersCard.cardBalance>12.7) {
						ridersCard.cardBalance=13;
					}else if (ridersCard.cardBalance<9) {
						ridersCard.cardBalance=7;
					}  
					totalFaresCollected+=(6-ridersCard.totalCollectedinlastTwohrTrips);      
					ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).destinationReached(destination);
					ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).addExpense(6-ridersCard.totalCollectedinlastTwohrTrips);
			    	ridersCard.tapStatus=false;
			    	totalTapOuts += stationsCrossed;
					return true;
				}
				ridersCard.deductBalance(fareCost * stationsCrossed);  
				totalFaresCollected+=(fareCost * stationsCrossed);      
				ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).destinationReached(destination);
				ridersCard.tripsOnCard.get((ridersCard.tripsOnCard.size()-1)).addExpense(fareCost * stationsCrossed);
		    	ridersCard.tapStatus=false;
		    	totalTapOuts += stationsCrossed;
				return true;
			}
	  	}
		System.out.println("The card has not been registered for the trip!");
		return false;
	}
	

}
