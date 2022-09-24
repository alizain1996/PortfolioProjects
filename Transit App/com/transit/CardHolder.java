
package transitapp.com.transit;

import java.util.ArrayList;
import java.util.Random;

import transitapp.Main;

public class CardHolder {
	
    private String name;
    final private String email;
    private ArrayList<Card> allTravelCards;
    public ArrayList<Trip> recentTrips;
    public Card newCard;
    public int cardholder_id;
    
    
    
    /**
	 * Constructs a new cardholder and assigns a new card to the cardholder
	 * 
	 * @param name	the name of the cardholder
	 * @param email	the email of the cardholder
	 */
    public CardHolder(String name, String email) {
    	this.name = name;
    	this.email = email;
    	this.newCard = new Card();
    	ArrayList<Card> allTravelCards = new ArrayList<>();
    		allTravelCards.add(newCard);
    	Random random = new Random();
    	this.cardholder_id = random.nextInt(1000000000); 
    	;
    	
    	
    	
    }
    /**
	 * Returns the new card assigned to a cardholder
	 * 
	 * @return the new card assigned to a cardholder
	 */
    public Card showCard() {
    	return newCard;
    }
    /**
	 * Returns the card number of the card assigned to a cardholder
	 * 
	 * @return the new card number of the card assigned to a cardholder
	 */
    public int showCardNum() {
    	return newCard.cardNumber;
    }
    
    /**
	 * Returns the ID of the cardholder
	 * 
	 * @return the ID of the cardholder
	 */
    public int showCardHolderID() {
    	return cardholder_id;
    }
    
    /**
	 * Suspends the card if reported stolen or lost
	 * 
	 * @param cardNum	the cardnumber of the card that is to be suspended
	 */
    public Boolean suspendCard(String cardNum) {
    	int card = Integer.parseInt(cardNum);
    	for (int count = 0 ; count < allTravelCards.size(); count ++) {
    		if (allTravelCards.get(count).cardNumber == card) {
    			allTravelCards.get(count).suspendCard();
    			System.out.println("The following Card:" + cardNum + " has been suspended");
    			return true;
    		}
    	}return false;
    }
    /**
	 * Adds a new card to the list of cardholder's cards
	 * 
	 */
    public void addCard() {
    	Card myCard = new Card();
    	allTravelCards.add(myCard);
    }
    /**
	 * Removes a card from the list of cardholder's cards
	 * 
	 */
    public Boolean removeCard(String cardNum) {
    	int card = Integer.parseInt(cardNum);
    	for (int count = 0; count < allTravelCards.size(); count ++) {
    		if (allTravelCards.get(count).cardNumber == card) {
    			allTravelCards.remove(count);
    			return true;
    		}
    	}
    	System.out.println("We could not find a card with card number " + cardNum + "please try again");
    	return false;
    }
    /**
	 * Changes the cardholder's name 
	 * 
	 * @param newName	the name the cardholder wants it to be changed to
	 */
    public void changeName(String newName) {
    	this.name = newName;
    	System.out.println("Your name has been successfully changed to " + newName);
    }
    /**
	 * Returns the balance of the card if the card is in the cardholder's cards list, none others
	 * 
	 * @param cardNum	the cardnumber of the card that's balance is to be checked
     * @return 
	 * @return the balance of the card if it exists, none otherwise
	 */
    public Object currentBalance(String cardNum){
    	int card = Integer.parseInt(cardNum);
    	for (int count = 0; count < allTravelCards.size(); count++) {
    		if (allTravelCards.get(count).cardNumber == card) {
    			return allTravelCards.get(count).cardBalance;
    		}
    	}
    	System.out.println("We could not find a card with card number " + cardNum + "please try again");
		return null;
    }
    /**
	 * Adds balance to the card  
	 * 
	 * @param cardnum	the card that the balance is supposed to be added to
	 * @param amount	the amount of balance that is to be added to the card
	 */
    public Boolean addBalance(String cardNum, int amount) {
    	int card = Integer.parseInt(cardNum);
    	if (amount == 10 || amount == 20 || amount == 50) {
    		for (int count = 0; count < allTravelCards.size(); count++) {
        		if (allTravelCards.get(count).cardNumber == card) {
        			allTravelCards.get(count).cardBalance += amount;
        			System.out.println("Amount successfully deposited, your balance is " + allTravelCards.get(count).cardBalance);
        			return true;
        		}
    	} 
    } return false;
    }
}
