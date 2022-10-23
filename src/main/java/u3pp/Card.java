package u3pp;

/*
 * Represents a standard American playing card
 * (values are 2 - 10 and Jack - Ace) 
 * (suits are clubs, diamonds, hearts, spades)
 * 
 * Do not make any changes to this class for this assignment.
 */
public class Card {
    //Instance variables
	private String suit;
	private String value;
	
	//Constants to represent Card suits and values
    public static final String[] SUITS = {"Clubs", "Spades", "Diamonds", "Hearts"};
    public static final String[] VALUES = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    
	/**
	 * Default Constructor sets the suit to Clubs and the value to 2
	 */
	public Card() {
		this.suit = SUITS[0];
		this.value = VALUES[0];
	}
	
	/**
	 * Constructs a Card based on the parameters
	 * @param suit  the suit for the card; may be Clubs, Diamonds, Hearts, or Spades
	 * @param value the face value of the card; may be 2-10, Jack, Queen, King, or Ace
	 */
	public Card(String suit, String value) {
		setSuit(suit);
		setValue(value);
	}

    /**
     * Sets the suit of the card to a given suit if it is valid
     * @param suit may be Clubs, Diamonds, Hearts, or Spades
     */
    private void setSuit(String suit) {
        for(int i = 0; i < SUITS.length; i++) {
            // use toLowerCase to make it case insensitive
            if (SUITS[i].toLowerCase().equals(suit.toLowerCase())) {
                this.suit = SUITS[i];
                return;
            }
        }
    }

	/**
	 * Getter for the suit of the Card. Can be clubs, diamonds, hearts, or spades
	 * @return the suit of the Card
	 */
	public String getSuit() {
		return this.suit;
	}

    /**
     * Sets the value of the card to a given value if it is valid
     * @param suit may be 2-10, Jack, Queen, King, or Ace
     */
    private void setValue(String value) {
        for(int i = 0; i < VALUES.length; i++) {
            // use toLowerCase to make it case insensitive
            if (VALUES[i].toLowerCase().equals(value.toLowerCase())) { 
                this.value = VALUES[i];
                return;
            }
        }
    }
	
	/**
	 * Getter for the face value of the Card. Is a value from 2 - ACE
	 * @return the face value of the Card
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Compares two cards and returns true if they share a suit and value
	 * @param c: the Card to compare this to.
	 * @return true if both the suit and value of this and c are the same
	 */
	public boolean equals (Card c) {
		return this.toString().equals(c.toString());
	}
	
	/**
	 * toString for the Card class
	 * @return "<value> of <suit>"
	 */
	public String toString() {
		return this.value + " of " + this.suit;
	}
	
	/**
     * Implements the Comparable interface.
	 * @param c: a Card to compare with this
	 * @return returns 1 if the value of this card is greater than c, 0 if they are equal, and -1 if this is less than c.
	 * The ranking for values are ACE > KING > QUEEN > JACK > 10 > 9 > 8 > 7 > 6 > 5 > 4 > 3 > 2
	 */
	public int compareTo (Card c) {
        // when values are the same
		if(this.value.equals(c.value)) {
			return 0;
        }
        // this card is greater
        else if (valueGreaterThan(c)) {
            return 1;
        }
        // this card is smaller
        return -1;
    }

    /**
     * Helper function for compareTo. Requires that this.getSuit and c.getSuit both be in Card.SUITS
     * @return true if this.getSuit()'s index in Card.SUITS is greater than c.getSuit's
     */
    private boolean suitGreaterThan(Card c) {
        return Card.getIndex(Card.SUITS, this.getSuit()) > Card.getIndex(Card.SUITS, c.getSuit());
    }

    /**
     * Helper function for compareTo. Requires that this.getValue and c.getValue both be in Card.VALUES
     * @return true if this.getValue()'s index in Card.VALUES is greater than c.getValue's
     */
    private boolean valueGreaterThan(Card c) {
        return Card.getIndex(Card.VALUES, this.getValue()) > Card.getIndex(Card.VALUES, c.getValue());
    }

    /**
     * Generic helper function for comparisons. 
     * Finds the index of a String in a String[]
     * @return the first index of the String in the String[] or -1 if it is not found
     */
    public static int getIndex(String[] arr, String str) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].equals(str)) {
                return i;
            }
        }
        return -1;
    } 
}
