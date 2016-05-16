//Yu-Yueh Liu
//260688975

import java.util.ArrayList;
import java.util.Collections;

public class CardPile {

	//private Card[] cards;
	private ArrayList<Card> cards = new ArrayList<Card>();
	//private int numCards;
	
	//CardPile constructor
	public CardPile(){
		//this.cards = new Card [52];
		//this.numCards = 0;
	}
	
	//Method that adds a card in a card array
	public void addToBottom(Card c){
		//System.out.println(c);
		//if(cards.size()<52){
			//this.cards[numCards] = c;
			cards.add(c);
			//System.out.println(cards.get(numCards));
			//this.numCards++;
		//}
	}
	
	//method that checks if a deck is empty
	public boolean isEmpty(){
	//we can use isEmpty() method for arraylist;
		return cards.isEmpty();
	}
	

	//method that returns a card at index i 
	public Card get(int i){
		return cards.get(i); 
	}
	
	//Method that removes a card at index i and returns the removed card
	public Card remove(int i){
		Card removed = cards.get(i);
		cards.remove(i);
		//numCards--;
		//System.out.println(removed);
		return removed;
	} 
	
	
	
	//Method that finds a card with a specific suit and value and returns an integer indicating the index at which the card is found 
	public int find(Suit s, Value v){
		for(int i = 0; i<cards.size(); i++){
			//System.out.println(cards[i]);
			if(cards.get(i).getValue() == v && cards.get(i).getSuit() == s){
				return i;
			} 
		}return -1;
	}
	
	//toString method for the CardPile object
	public String toString(){
		String s = "";
		for(int i = 0; i<cards.size(); i++){
			s+= i + ". " + cards.get(i).toString() + " ";
		}
		return s;
	}
	
	//extra method to obtain number of cards in a CardPile
	public int getSize(){
		//return numCards;
		return cards.size();
	}
	
	
	//method that creates a Full Deck of 52 cards
	//containing all the possible cards and then shuffling it
	//return the shuffled deck
	public static CardPile makeFullDeck(){
		CardPile myDeck = new CardPile();
		for(Suit aSuit : Suit.values()){
			for(Value aValue : Value.values()){
				Card newCard = new Card(aSuit, aValue);
				myDeck.addToBottom(newCard);
				//System.out.println(newCard);
			}
		}//System.out.println(myDeck);
		//UtilityCode.shuffle(myDeck.cards, 52);
		Collections.shuffle(myDeck.cards);	
		return myDeck;		
	}
	
	//make n number of new deck and add all together
	public static CardPile makeFullDeck(int n){
		CardPile nDeck = new CardPile();
		for(int i = 0 ; i<n; i++){
			nDeck.cards.addAll(makeFullDeck().cards);
		}
		Collections.shuffle(nDeck.cards);
		return nDeck;
	}
	//returns number of cards in CardPile
	public int getNumCards(){
		return cards.size();
	}
}
