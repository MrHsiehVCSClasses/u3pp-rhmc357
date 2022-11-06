package u3pp;

import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) {
        
        Deck myDeck = new Deck();
        Blackjack myBlackjack = new Blackjack();
        Scanner myScanner = new Scanner(System.in);

        myDeck.shuffle();
        myBlackjack.play(myScanner);
    }

}
