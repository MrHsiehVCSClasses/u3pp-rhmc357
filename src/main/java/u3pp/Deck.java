package u3pp;

//imports the random function needed for the deal
import java.util.Random;

public class Deck {
    
    //keeps track of the number of cards dealt
    private int discardCount = 0;

    //creats a list that can contain the card values
    //instantiates a new random object
    Random myRandom = new Random(); 
    
    private Card[] Cards = new Card[52];

    //creates a deck with 52 random cards
    //puts that deck inside the Cards array
    public  Deck() {
        int index = 0;
        for (int i = 0; i < Card.VALUES.length; i++){
            for (int a = 0; a < Card.SUITS.length; a++){
                Card myCard = new Card(Card.SUITS[a], Card.VALUES[i]);
                
                Cards [index] = myCard;
                index += 1;
                
            }
        }
        
    }

    //Shuffles each of the cards in the Cards array
    //does this by swapping each of the values
    public void shuffle() {
        
        discardCount = 0;
        for (int i = 0; i < Cards.length; i++){
            int ran;
            
            ran = myRandom.nextInt(Cards.length);
            
            Card temp;
            temp = Cards[ran];
            Cards[ran] = Cards[i];
            Cards[i] = temp;
        }
        discardCount = 0;
    }
    

    //This makes sure that there are enough cards in the deck
    //before dealing. If there are not enough it shuffles
    //when dealing it gives the most recent card not in the discard pile
    public Card deal() {
        
        if (discardCount == 52){
            System.out.println("none left, shuffling deck");
            return null;
        }
        discardCount += 1;
        
        return Cards[51 - (discardCount - 1)];
        
    }

    //this gives how many cards are left in the deck 
    public int numLeft(){
        
        return (Cards.length - discardCount);
        
        
    }
}
