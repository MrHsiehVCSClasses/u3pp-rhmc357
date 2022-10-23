package u3pp;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTests {

    private static final int DECK_SIZE = Card.VALUES.length * Card.SUITS.length;

    @Test
    void constructorAndNumLeftWorkCorrectly() throws Exception {
        Deck testDeck = new Deck();
        assertEquals(DECK_SIZE, testDeck.numLeft(), "A new deck has 52 cards");
    }

    @Test
    void dealAndNumLeftWorkCorrectly() throws Exception {
        Deck testDeck = new Deck();
        int expectedDeckSize = DECK_SIZE;
        for(int j = 0; j < 6; j++) {
            int testTimes = j;
            for(int i = 0; i < testTimes; i++) {
                testDeck.deal();
            }
            expectedDeckSize -= testTimes;
            assertEquals(expectedDeckSize, testDeck.numLeft());
        }
    }

    @Test
    void newDeckDealsOutOnlyUniqueCards() throws Exception {
        Deck testDeck = new Deck();
        ArrayList<Card> dealtCards = DeckTests.dealOutDeck(testDeck);

        assertEquals(0, testDeck.numLeft(),"A new deck is empty after dealing 52 cards");
        assertTrue(DeckTests.allUnique(dealtCards),"A new deck deals out all unique combinations of SUITS and VALUES");
    }

    @Test
    void shuffleRandomizesDeck() throws Exception {
        Deck testDeck = new Deck();
        ArrayList<Card> dealtCards = DeckTests.dealOutDeck(testDeck);
        testDeck.shuffle();
        ArrayList<Card> otherDealtCards = DeckTests.dealOutDeck(testDeck);
        testDeck.shuffle();

        assertEquals(DECK_SIZE, testDeck.numLeft(), "A shuffled deck is full size");
        assertTrue(DeckTests.areDifferent(dealtCards, otherDealtCards), "A shuffled deck is different from a previously dealt deck");
    }

    private static ArrayList<Card> dealOutDeck(Deck deck) {
        ArrayList<Card> dealtCards = new ArrayList<Card>();

        for(int i = 0; i < DECK_SIZE; i++) {
            dealtCards.add(deck.deal());
        }

        return dealtCards;
    }

    private static boolean areDifferent(ArrayList<Card> cards, ArrayList<Card> otherCards) {
        if(cards.size() != cards.size()) {
            return false;
        }

        for(int i = 0; i < cards.size(); i++) {
            if(!cards.get(i).equals(otherCards.get(i))) {
                return true;
            }
        }

        return false;
    }

    private static boolean allUnique(ArrayList<Card> dealtCards) {
        for(int i = 0; i < dealtCards.size(); i++) {
            for(int j = i + 1; j < dealtCards.size(); j++) {
                if(dealtCards.get(i).equals(dealtCards.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}