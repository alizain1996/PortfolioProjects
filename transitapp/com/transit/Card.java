package transitapp.com.transit;

import java.util.ArrayList;
import java.util.Random;

public class Card  {
	
    public int cardNumber;
    public double cardBalance;
    public ArrayList<Trip> tripsOnCard = new ArrayList<>();
    //public Trip [] tripsOnCard;
    public boolean tapStatus;
    public double totalCollectedinlastTwohrTrips;
    /**
	 *Makes a new card to be used on the subway or the bus with starting balance of $19 and a cardnumber
	 */
   
    public Card() {
	    Random random = new Random();
	    this.cardNumber = random.nextInt(1000000000); 
	    this.cardBalance = 19;
	    tapStatus=false;
	    this.totalCollectedinlastTwohrTrips = 0;
	    
    }
   
    /**
	 * Checks and returns whether a card's balance is negative or not
	 * @return True is card balance in negative, False otherwise.
	 */
    public boolean isNegative() {
	    if (this.cardBalance < 0) {
		    return true;
	    }
	    return false;
    }
    /**
	 * Deduct's cost of trip from the card's balance
	 * 
	 * @param fareCost	cost of the trip that should be deducted from the card's balance
	 */
   
    public void deductBalance(double fareCost) {
	    this.cardBalance -= fareCost;
	    System.out.println("An amount of " + fareCost + " has been deducted from your card");
    }
    
    /**
	 * Suspends the Card by setting card balance to $0
	 */
   
    public void suspendCard() {
	    this.cardBalance = 0;
    }
    
    /**
	 * Adds balance to the card  
	 * 
	 * @param cardnum	the card that the balance is supposed to be added to
	 * @return True is cardbalance was adde, false otherwise
	 */
    public Boolean addBalance(int amount) {
    	if (amount == 10 || amount == 20 || amount == 50) {
        		this.cardBalance += amount;
        		System.out.println("Amount successfully deposited, your balance is " + this.cardBalance);
        		return true;
        } else {
        	return false;
        }
    }
    
    
}
