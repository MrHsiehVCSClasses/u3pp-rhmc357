package u3pp;

import java.util.Random;


// MVP version.
public class Deck {
    public static final int NUM_CARDS = Card.SUITS.length * Card.VALUES.length;
    public Card[] cards = new Card[NUM_CARDS];

    private int numDealt;
    private Random rand;

    public Deck() {
        for(int s = 0; s < Card.SUITS.length; s++) {
            for(int v = 0; v < Card.VALUES.length; v++) {
                int index = s * Card.VALUES.length + v;
                cards[index] = new Card(Card.SUITS[s], Card.VALUES[v]);
            }
        }
        rand = new Random();
        shuffle();
    }

    public void shuffle() {
        // fischer yates shuffle
        for(int i = 0; i < cards.length-1; i++) {
            int otherCardIndex = i + rand.nextInt(cards.length-i-1);
            if(i != otherCardIndex) {
                swap(i, otherCardIndex);
            }
        }
        numDealt = 0;
    }

    /**
     * Swaps the cards in the specified indices
     *  */
    private void swap(int i, int j) {
        Card temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }

    public String toString() {
        String output = "";
        for(Card c : cards) {
            output += c.toString();
            output += ",";
        }
        return output;
    }

     public int numLeft() {
        return 52 - numDealt;
     }

     public Card deal() {
        if(numDealt == 52) {
            shuffle();
        }
        Card output = cards[51-numDealt];
        numDealt++;
        return output;
     }


}
