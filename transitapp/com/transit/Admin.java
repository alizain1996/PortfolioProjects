package transitapp.com.transit;

import java.util.ArrayList;

/**
 * The Admin class keeps track of all the buses and subways that it has in its transit network and it
 * keeps track of all the cardholders. It is used to keep track of revenue and expenses and produce
 * report on a day-to-day basis.
 */
public class Admin  {
	private ArrayList<Bus> allBuses;
	private ArrayList<Subway> allSubways;
	private ArrayList<CardHolder> allCardHolders;
	
	/**
	 * Constructs a new instance of Admin with list of buses, subways and cardholders.
	 */
	public Admin() {
		this.allBuses = new ArrayList<Bus>();
		this.allSubways = new ArrayList<Subway>();
		this.allCardHolders = new ArrayList<CardHolder>();
		
	}
	
	// Helper Function
	/**
	 * Counts the total fare collected from each bus in a list of buses or each subway in a list of
	 * subways.
	 * @return total fare collected from all buses or all subways.
	 */
	public int busFareCounter() {
		int total = 0;
		for (int count = 0; count < this.allBuses.size(); count ++) {
			total += this.allBuses.get(count).totalFaresCollected;
		}
		return total;
	}
	
	// Helper Function
		/**
		 * Counts the total fare collected from each subway
		 * 
		 * @return total total fares collected.
		 */
	public int subwayFareCounter() {
		int total = 0;
		for (int count = 0; count < this.allSubways.size(); count ++) {
			total += this.allSubways.get(count).totalFaresCollected;
		}
		return total;
	}
	
	
	// Helper Function
	/**
	 * Counts the total number of stops/stations reached by cardholders in every bus 
	 * @param transitList the list of buses 
	 * @return total stops/stations reached by all cardholders.
	 */
	
	public int stopsCount(ArrayList<Bus> transitList) {
		int total = 0;
		for (int count = 0; count < transitList.size(); count ++) {
			total += transitList.get(count).numOfStopsReached;
		}
		return total;
	}
	
	// Helper Function
		/**
		 * Counts the total number of stops/stations reached by cardholders in every subway
		 * @param transitList the list of  subways
		 * @return total stops/stations reached by all cardholders.
		 */
		
	public int stationsCount(ArrayList<Subway> transitList) {
		int total = 0;
		for (int count = 0; count < transitList.size(); count ++) {
			total += transitList.get(count).numOfStationsReached;
		}
		return total;
	}
	
   
	/**
	 * Calculates the total fare collected from all buses and subways
	 * @return the total fare collected from all buses and subways for the day. 
	 */
	public int faresCollected() {
		int busTotalFare = busFareCounter();
		int subwayTotalFare = subwayFareCounter();
		int total = busTotalFare + subwayTotalFare;
		return total;
	}
	
	
	/**
	 * Calculates total number of stops/stations reached by all cardholders in buses and subways
	 * @return total number of stops/stations reached by all cardholders.
	 */
	public int stopsandStationsReached() {
		int busStopsTotal = stopsCount(this.allBuses);
		int subwayStationTotal = stationsCount(this.allSubways);
		int total = busStopsTotal + subwayStationTotal;
		return total;
	}
	
	
	/**
	 * Compares total fares collected with number of stops/stations reached by all cardholders.
	 */
	public String dailyReport() {
		System.out.println("Total number of fares collected in a day: " + faresCollected());
		System.out.println("Total number of stops/stations reached by all cardholders: " 
		+ stopsandStationsReached());
		return "Total number of fares collected in a day: " + faresCollected() + "\nTotal number of stops/stations reached by all cardholders: " + 
		+ stopsandStationsReached();
	}
}
