//Yu-Yueh Liu
//260688975
//Section 01

import java.util.Hashtable;
import java.util.Scanner;


public class Blackjack {

	public static void main(String[] args) {
		
		int initialChips = Integer.parseInt(args[0]);
		//create 4 decks
		CardPile mainDeck =
				CardPile.makeFullDeck(4);
		
		//Loop : play until not enough cards (less or equal than 10 cards left)
		int counter = 0;
		int x = 0;
		for(int i = 0; i < 100; i++){
			if(mainDeck.getNumCards() > 10){
				for(int y = 0; y<=counter;y++){
					Scanner bet = new Scanner(System.in);
					
					//Player inputs amount to bet
					System.out.print("Choose the amount of chips you want to bet: ");
					int myBet = bet.nextInt();
					x = myBet;
					if(myBet > initialChips){
						System.out.println("You do not have enough chips...");
						System.out.println();
						counter++;
						
					//Two cases where player leaves
					} else if(myBet < 0){
						System.out.println("You left the game...");
						return;
					} else if(initialChips == 0){
						System.out.println("You must leave.");
						return;	
					} 
						
				}
				//Play a round of blackjack
				Results r = playRound(mainDeck); 
				System.out.println(r);
				System.out.println();
				
				
				//update chips according to different cases
				if(r.equals(Results.BLACKJACK)){
					initialChips += Math.floor((x*1.5));
				} else if(r.equals(Results.PLAYER_WINS)){
					initialChips += x;
				} else if(r.equals(Results.DEALER_WINS)){
					initialChips -= x;
				}
			System.out.println("Amount of Chips you have: " + initialChips);
			
			}
		} 
		System.out.println("Not enough cards...");
		
		/* TEST:
		CardPile A = new CardPile();
		Card a = new Card(Suit.HEARTS, Value.ACE);
		Card b = new Card(Suit.HEARTS, Value.ACE);
		Card c = new Card(Suit.HEARTS, Value.THREE);
		Card d = new Card(Suit.HEARTS, Value.FOUR);
		A.addToBottom(a);
		A.addToBottom(b);
		A.addToBottom(c);
		A.addToBottom(d);
		System.out.println(countValues(A));*/
		
		
	}

	//method to get the score of Card c
	public static int getScore(Card c){
		//get Value of card c
		Value v = c.getValue();
		
		//Hashtable with the key as a card Value and the value as an int
		Hashtable <Value, Integer> cardValue = new Hashtable <Value, Integer>();
		cardValue.put(Value.ACE, 11);
		cardValue.put(Value.TWO, 2);
		cardValue.put(Value.THREE, 3);
		cardValue.put(Value.FOUR, 4);
		cardValue.put(Value.FIVE, 5);
		cardValue.put(Value.SIX, 6);
		cardValue.put(Value.SEVEN, 7);
		cardValue.put(Value.EIGHT, 8);
		cardValue.put(Value.NINE, 9);
		cardValue.put(Value.TEN, 10);
		cardValue.put(Value.JACK, 10);
		cardValue.put(Value.QUEEN, 10);
		cardValue.put(Value.KING, 10);
		
		return cardValue.get(v);
	}
	
	//method that Counts total value of cards in one's hand
	//if value > 21 with ace, than ace is worth 1 instead of 11
	public static int countValues(CardPile thePile){
		int bestScore = 0;
		int counter = 0;
		for(int i = 0;i<thePile.getSize();i++){
			if(thePile.get(i).getValue().equals(Value.ACE)){
				counter++;
			}
			
			bestScore += getScore(thePile.get(i));
			
			if(bestScore > 21 && counter > 0){
				bestScore = bestScore-10;
				counter--;
			}
		}
		return bestScore;
	}
	
	
	// Play one round of BlackJack
	public static Results playRound(CardPile Deck){
		Scanner UserIn = new Scanner(System.in);
		
		CardPile PlayerPile = new CardPile();
		CardPile DealerPile = new CardPile();
		
		//Dealing cards to Player, initially 2 cards		
		for(int i =0; i<2;i++){
			Card k = Deck.remove(i);
				PlayerPile.addToBottom(k);
		} 
		//BlackJack case
		if(countValues(PlayerPile) == 21 && countValues(DealerPile) != 21){
			System.out.println("My cards: " + PlayerPile);
			return Results.BLACKJACK;
		} else if(countValues(PlayerPile) == countValues(DealerPile)){
			return Results.TIE;
		}
	
		System.out.println("My cards: " + PlayerPile);
		
		//Dealing cards to Dealer, initially 2 cards
		for(int j = 0 ; j<2;j++){
			Card h = Deck.remove(j);
			DealerPile.addToBottom(h);
		} 
		System.out.println("The dealer's second card: " + DealerPile.get(1));
		
		//If user inputs "HIT"
		int counter = 0;
		for(int u = 0; u<=counter;u++){
			System.out.print("Input an action: ");
			String userInput = UserIn.next();
			if(userInput.equalsIgnoreCase("hit")){
				System.out.println();
				Card c = Deck.remove(0);
				PlayerPile.addToBottom(c);
				System.out.println("My cards: " + PlayerPile);
				System.out.println("My score: " + countValues(PlayerPile));
				counter++;
				if(countValues(PlayerPile) > 21){
					//System.out.println("My score: " + countValues(PlayerPile));
					return Results.DEALER_WINS;
				}
				
			//If user inputs "STAY"
			} else if(userInput.equalsIgnoreCase("stay")){
				System.out.println();
				System.out.println("My cards: " + PlayerPile);
				
				//the Dealer's turn to play
				for(int i = 0; i < 10; i++){
					if(countValues(DealerPile)<18){
						Card d = Deck.remove(0);
						DealerPile.addToBottom(d);
					}
				}
				//different results cases
				System.out.println("My score: " + countValues(PlayerPile));
				System.out.println("The Dealer's Score: " + countValues(DealerPile));
				System.out.println("The Dealer's Cards: " + DealerPile);
				
				if(countValues(DealerPile) > 21){
					return Results.PLAYER_WINS;
				} else if(countValues(DealerPile) > countValues(PlayerPile)){
					return Results.DEALER_WINS;
				} else if(countValues(DealerPile) < countValues(PlayerPile)){
					return Results.PLAYER_WINS;
				} else if(countValues(DealerPile) == countValues(PlayerPile)){
					return Results.TIE;
				}
				return Results.TIE;

			//if user doesn't input "hit" or "stay"
			} else {
				System.out.println("Please choose 'Stay' or 'Hit' ");
				counter++;
			}
		} return Results.TIE;
	}
	
	
	//Results 
	public enum Results {
		DEALER_WINS,
		PLAYER_WINS, 
		TIE, 
		BLACKJACK
	}
}