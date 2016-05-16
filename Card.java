//Yu-Yueh Liu
//260688975

public class Card{
	//
	private Value value;
	private Suit suit;
	
		
	//Card constructor
	public Card(Suit theSuit, Value theValue){
		this.suit = theSuit;
		this.value = theValue;
	}
	
	//Get method for Suit
	public Suit getSuit(){
		return this.suit;
	}
	
	//Get method for Value
	public Value getValue(){
		return this.value;
	}

	//toString method for Card object
	public String toString(){
		return this.value + " Of " + this.suit; 
	}
}


