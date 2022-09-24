// These is a placeholder package and placeholder class
// Feel free to rename or remove these when you add in your own code (just make sure to add/commit/push any changes made,
//		and let your teammates know to pull the changes. Follow the workflow in the a2 instructions)

package transitapp;




import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import transitapp.com.transit.Admin;
import transitapp.com.transit.Bus;
import transitapp.com.transit.Card;
import transitapp.com.transit.CardHolder;
import transitapp.com.transit.Subway;
import transitapp.com.transit.publicTransport;
import javafx.geometry.Insets;

public class Main extends Application {

	private TextField cardnum;
	private TextField station;
	private Button bustapin_button;
	private Button bustapout_button;
	private Label station_label;
	private Label card_label;
	public Label display;
	private Button subwaytapout_button;
	private Button subwaytapin_button;
	private Button cardholder_button;
	private TextField cardholder_name;
	private TextField cardholder_email;
	private Label cardholder_label;
	public  ArrayList<Card> allcards = new ArrayList<>();
	private Label balance_label;
	private Button balance_button;
	private TextField balance_input;
	private TextField addbus_input;
	private Button addbus_button;
	private Label addbus_label;
	private Label addsubway_label;
	private TextField addsubway_input;
	private Button addsubway_button;
	private Label changename_label;
	private Button changename_button;
	private TextField changename_inputcard;
	private TextField changename_inputname;
	public  ArrayList<CardHolder> allcardHolders = new ArrayList<>();
	private Label addbalance_label;
	private Button addbalance_button;
	private TextField addbalance_amount;
	private TextField addbalance_cardnum;
	private TextField suspend_input;
	private Button suspend_button;
	private Label suspend_label;
	private Label dailyreport_label;
	private Button dailyreport_button;
	  
	
	
	public static List<String> subwayStations = new ArrayList<String>();
	public static List<String> busStations = new ArrayList<String>();
	
	
	
	
	
	
	
	// Helper Function
		/**
		 * finds card object corresponding to the given card number
		 * @param cardnum the card number of the card object to be found
		 * @return card object corresponding to the given card number.
		 */
	public Card findCard(String cardnum) {
		Card cardobj = null;
    	int card = Integer.parseInt(cardnum);
    	for (int count = 0 ; count < allcards.size(); count ++) {
    		if (allcards.get(count).cardNumber == card) {
    			cardobj = allcards.get(count);
 
    		}
    	}
		return cardobj;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		initUI(stage);
	}
	
	private void initUI(Stage stage) {
		GridPane pane = new GridPane();
		
		pane.setPadding(new Insets(15));
		pane.setVgap(10);
		pane.setStyle("-fx-background-color: #4BA2C5;");
		
		ListView<String> bus_stations = new ListView<>();
		bus_stations.getItems().add("BUS STATIONS:");
		bus_stations.getItems().add("Laari Adda");
		bus_stations.getItems().add("Lahore");
		bus_stations.getItems().add("Islamabad");
		bus_stations.getItems().add("Multan");
		bus_stations.getItems().add("Karachi");
		bus_stations.getItems().add("Rawalpindi");
		
		
		busStations.add("Laari Adda");
		busStations.add("Lahore");
		busStations.add("Islamabad");
		busStations.add("Multan");
		busStations.add("Karachi");
		busStations.add("Rawalpindi");

		ListView<String> subway_stations = new ListView<String>();
		subway_stations.getItems().add("SUBWAY STATIONS:");
		subway_stations.getItems().add("Punjab");
		subway_stations.getItems().add("Balochistan");
		subway_stations.getItems().add("Kashmir");
		subway_stations.getItems().add("Sindh");
		subway_stations.getItems().add("KPK");
		subway_stations.getItems().add("Laari Adda");
		
		
		subwayStations.add("Punjab");
		subwayStations.add("Balochistan");
		subwayStations.add("Kashmir");
		subwayStations.add("Sindh");
		subwayStations.add("KPK");
		subwayStations.add("Laari Adda");
		
		
		
		cardnum = new TextField();
		station = new TextField();
		bustapin_button = new Button("BUS TAP IN");
		bustapout_button = new Button("BUS TAP OUT");
		card_label = new Label("Card Number: ");
		station_label = new Label("Station Name: ");
		display = new Label();
		subwaytapin_button = new Button("SUBWAY TAP IN");
		subwaytapout_button = new Button("SUBWAY TAP OUT");
		cardholder_button = new Button("Create Cardholder");
		cardholder_label = new Label("To create a new cardholder, please enter a Name and E-mail address \nbelow and press the Create Cardholder Button:");
		cardholder_name = new TextField("Name...");
		cardholder_email = new TextField("Email...");
		balance_label = new Label("To check card  balance, please input Card Number:");
		balance_button = new Button("CHECK BALANCE");
		balance_input = new TextField();
		addbus_input = new TextField();
		addsubway_input = new TextField();
		addbus_button = new Button("ADD BUS STATION");
		addsubway_button = new Button("ADD SUBWAY STATION");
		addbus_label = new Label("To add a new Bus Station, please input Station Name below: ");
		addsubway_label = new Label("To add a new Subway Station, please input Station Name below: ");
		changename_button = new Button("CHANGE NAME");
		changename_label = new Label("To change the name on a card, please input Card Holder ID \nand new Name below:");
		changename_inputcard = new TextField("Card Holder ID...");
		changename_inputname = new TextField("New Name...");
		addbalance_label = new Label("To add balance to your card, please enter the card number\n and the amount (10, 20, 50)");
		addbalance_button = new Button("ADD BALANCE");
		addbalance_cardnum = new TextField("Card Number...");
		addbalance_amount = new TextField("Amount...");
		suspend_input = new TextField("Card Number...");
		suspend_button = new Button("SUSPEND CARD");
		suspend_label = new Label("To suspend a card, please enter the Card Number below");
		dailyreport_label = new Label();
		dailyreport_button = new Button("GENERATE DAILY REPORT");
		
		
		cardholder_name.setMaxWidth(300);
		cardholder_email.setMaxWidth(300);
		dailyreport_label.setMinSize(80, 80);
		
		
		
		Admin admin = new Admin();
		Subway subway = new Subway(0.5,subwayStations.size());
		Bus bus = new Bus(2,busStations.size());
		publicTransport subwaytransport = new publicTransport(0.5, subwayStations.size());
		publicTransport bustransport = new publicTransport(2, busStations.size());
	
		FlowPane card_pane = new FlowPane();
		card_pane.getChildren().add(card_label);
		card_pane.getChildren().add(cardnum);
		
		
		FlowPane station_pane = new FlowPane();
		station_pane.getChildren().add(station_label);
		station_pane.getChildren().add(station);
		
		FlowPane bus_taps = new FlowPane();
		bus_taps.getChildren().add(bustapin_button);
		bus_taps.getChildren().add(bustapout_button);
		
		FlowPane subway_taps = new FlowPane();
		subway_taps.getChildren().add(subwaytapin_button);
		subway_taps.getChildren().add(subwaytapout_button);
		
		FlowPane balance_pane = new FlowPane();
		balance_pane.getChildren().add(balance_label);
		balance_pane.getChildren().add(balance_input);
		balance_pane.getChildren().add(balance_button);
		
		FlowPane addbus = new FlowPane();
		addbus.getChildren().add(addbus_label);
		addbus.getChildren().add(addbus_input);
		addbus.getChildren().add(addbus_button);
		
		FlowPane addsubway = new FlowPane();
		addsubway.getChildren().add(addsubway_label);
		addsubway.getChildren().add(addsubway_input);
		addsubway.getChildren().add(addsubway_button);
		
		FlowPane changename = new FlowPane();
		changename.getChildren().add(changename_label);
		changename.getChildren().add(changename_inputcard);
		changename.getChildren().add(changename_inputname);
		changename.getChildren().add(changename_button);
		
		FlowPane addbalance = new FlowPane();
		addbalance.getChildren().add(addbalance_label);
		addbalance.getChildren().add(addbalance_cardnum);
		addbalance.getChildren().add(addbalance_amount);
		addbalance.getChildren().add(addbalance_button);
		
		FlowPane suspendcard = new FlowPane();
		suspendcard.getChildren().add(suspend_label);
		suspendcard.getChildren().add(suspend_input);
		suspendcard.getChildren().add(suspend_button);
		
		
		
		display.setMinSize(50, 70);
		
		pane.add(display, 0, 0);
		pane.add(card_pane, 0, 1);
		pane.add(station_pane,0,2);
		pane.add(bus_taps,0,3);
		pane.add(subway_taps, 0, 4);
		pane.add(bus_stations,0,6);
		pane.add(subway_stations,1,6);
		pane.add(cardholder_label, 0, 7);
		pane.add(cardholder_name, 0, 8);
		pane.add(cardholder_email, 0, 9);
		pane.add(cardholder_button, 0, 10);
		pane.add(balance_pane, 1,0 );
		pane.add(addbus, 1, 1);
		pane.add(addsubway, 1, 3);
		pane.add(changename, 1, 7);
		pane.add(addbalance, 1, 9);
		pane.add(suspendcard, 2, 0);
		pane.add(dailyreport_button, 2, 1);
		pane.add(dailyreport_label, 2, 2);
		
		
		
		
		bustapin_button.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	String cardnumber = cardnum.getText();
            	String station_string = station.getText();
            	
            	Card cardobj = findCard(cardnumber);
            	if (cardobj!= null) {
            		Boolean status = bus.tapIn(cardobj, station_string);
    				if (status) {
    	                display.setText(cardnumber + " tapped in at " + station_string + " at " + java.time.LocalTime.now());
    				} else {
    					display.setText("Your card balance is not enough for the ride, please recharge card!");
    				}
            	} else {
            		display.setText("We could not find a card with card number " + cardnumber + " please try again");
            	}
            	
				
            }
        });
		
		bustapout_button.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	String cardnumber = cardnum.getText();
            	String station_string = station.getText();
            	
            	Card cardobj = findCard(cardnumber);
            	if (cardobj!= null) {
            		Boolean status = bus.tapout(cardobj, station_string);
    				if (status) {
    	                display.setText(cardnumber + " tapped out at " + station_string + " at " + java.time.LocalTime.now());
    				} else {
    					display.setText("The card was not tapped in!");
    				}
            	} else {
            		display.setText("We could not find a card with card number " + cardnumber + " please try again");
            	}
            	
            }
        });
		
		subwaytapin_button.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	String cardnumber = cardnum.getText();
            	String station_string = station.getText();
            	Card cardobj = findCard(cardnumber);
            	if (cardobj!= null) {
            		Boolean status = subway.tapIn(cardobj, station_string);
    				if (status) {
    	                display.setText(cardnumber + " tapped in at " + station_string + " at " + java.time.LocalTime.now());
    				} else {
    					display.setText("Your card balance is not enough for the ride, please recharge card!");
    				}
            	} else {
            		display.setText("We could not find a card with card number " + cardnumber + " please try again");
            	}
            }
        });
		
		subwaytapout_button.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	String cardnumber = cardnum.getText();
            	String station_string = station.getText();
            	
            	Card cardobj = findCard(cardnumber);
            	if (cardobj!= null) {
            		Boolean status = subway.tapOut(cardobj, station_string);
    				if (status) {
    	                display.setText(cardnumber + " tapped out at " + station_string + " at " + java.time.LocalTime.now());
    				} else {
    					display.setText("The card was not tapped in!");
    				}
            	} else {
            		display.setText("We could not find a card with card number " + cardnumber + " please try again");
            	}
            }
        });
		cardholder_button.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
            public void handle(ActionEvent event) {
            	String name = cardholder_name.getText();
            	String email = cardholder_email.getText();
				
            	
				CardHolder cardholder = new CardHolder(name,email);
				allcards.add(cardholder.showCard());
				allcardHolders.add(cardholder);
				
				
				display.setText("New Cardholder with Name: " + name + " and E-mail: \n" + email + " has been created with assigned Card \nNumber: " + cardholder.showCardNum() + " and Cardholder ID: " + cardholder.showCardHolderID());
            }
        });
		balance_button.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
            public void handle(ActionEvent event) {
            	String cardnum = balance_input.getText();
          
				Card cardobj = findCard(cardnum);
				
            	if (cardobj!= null) {
            		display.setText("The current balance on card: " + cardnum + " is $" + cardobj.cardBalance);
            	} else {
            		display.setText("We could not find a card with card number " + cardnum + " please try again");            		
            	}
				
            }
        });
		
		addbus_button.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
            public void handle(ActionEvent event) {
            	String station_name = addbus_input.getText();
            	busStations.add(station_name);
            	bus_stations.getItems().add(station_name);
			}  	
        });
		
		addsubway_button.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
            public void handle(ActionEvent event) {
            	String station_name = addsubway_input.getText();
            	subwayStations.add(station_name);
            	subway_stations.getItems().add(station_name);
			}  	
        });
		
		changename_button.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
            public void handle(ActionEvent event) {
            	String cardholderid = changename_inputcard.getText();
            	String newname = changename_inputname.getText();
            	
            	CardHolder cardholder = null;
            	int card = Integer.parseInt(cardholderid);
            	for (int count = 0 ; count < allcardHolders.size(); count ++) {
            		if (allcardHolders.get(count).cardholder_id == card) {
            			cardholder = allcardHolders.get(count);
         
            		}
            	}
            	if (cardholder != null) {
            		cardholder.changeName(newname);
            		display.setText("Cardholder name has been successfully changed to: " + newname);
            	} else {
            		display.setText("Cardholer with ID: " + cardholderid + " was not found, Please Try Again!");
            	}
        		
            	
			}  	
        });
		addbalance_button.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
            public void handle(ActionEvent event) {
            	String cardnum = addbalance_cardnum.getText();
            	String amount = addbalance_amount.getText();
            	
            	int amount_int = Integer.parseInt(amount);
            	Card cardobj = findCard(cardnum);
            	
            	if (cardobj != null) {
            		Boolean status = cardobj.addBalance(amount_int);
            		if (status) {
            			display.setText("Amount successfully deposited, your balance is " + cardobj.cardBalance);
            		} else {
            			display.setText("INVALID AMOUNT! Please enter and amount of 10, 20, or 50");
            		}
            	} else {
            		display.setText("We could not find a card with card number " + cardnum + " please try again");
            	}
			}  	
        });
		suspend_button.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
            public void handle(ActionEvent event) {
            	String cardnum = suspend_input.getText();
            	
            	Card cardobj = findCard(cardnum);
            	if (cardobj != null) {
            		cardobj.suspendCard();
            		allcards.remove(cardobj);
            		display.setText("Card: " + cardnum + " has been suspended and removed from our database!");
            	} else {
            		display.setText("We could not find a card with card number " + cardnum + " please try again");
            	}
			}  	
        });
		
		dailyreport_button.setOnAction(new EventHandler<ActionEvent>() {
			 
			@Override
            public void handle(ActionEvent event) {
				double totalfares = bus.totalFaresCollected() + subway.totalFaresCollected();
				double totalstops = bus.totalTapOuts + subway.totalTapOuts;
            	dailyreport_label.setText("DAILY REPORT: \n Total fares collected:$" + totalfares + "\n Total stops reached: " + totalstops );
			}  	
        });
		
		
		
		Scene scene = new Scene(pane, 1300, 650);
		
		stage.setTitle("Desi Boys Transporation");
		stage.setScene(scene);
		stage.show();
	}
}


