package u3pp;

import java.util.Scanner;

// MVP Version. play() not implemented.
public class Blackjack {
    private Deck deck;
    private Card[] playerCards;
    private Card[] dealerCards;

    private static final String[] YES_INPUT = { "y", "ye", "yes" };
    private static final String[] NO_INPUT = { "n", "no" };
    private static final String[] HIT_INPUT = { "h", "hi", "hit" };
    private static final String[] STAY_INPUT = { "s", "st", "sta", "stay" };

    public Blackjack() {
        deck = new Deck();
        deck.shuffle();
        playerCards = new Card[10];
        dealerCards = new Card[10];
    }

    public void play(Scanner scanner) {
        System.out.print("Welcome to Blackjack! What is your name? ");
        String name = scanner.nextLine();
        System.out.println(String.format("Hello %s I am Gambletron 5000! Let's play some cards.", name));

        boolean shouldKeepPlaying = true;
        boolean inputCorrect = true;
        boolean isYesInput = false;
        boolean isNoInput = false;
        while (shouldKeepPlaying) {

            dealCards(playerCards, 2);
            dealCards(dealerCards, 2);
            printHandStates();
            if (isBlackjack(playerCards) || isBlackjack(dealerCards)) {
                if (isBlackjack(playerCards)) {
                    System.out.println(String.format("Congrats, %s got a blackjack!", name));
                } else {
                    System.out.println("The dealer got a blackjack!");
                }
            } else {
                // repeat rounds until you bust or stay
                boolean isStayInput = false;
                while (!isStayInput) {

                    // hit/stay input loop
                    inputCorrect = true;
                    boolean isHitInput = false;

                    while (!(isHitInput || isStayInput)) {
                        if (!inputCorrect) {
                            System.out.println("Input invalid, please try again.");
                        }

                        System.out.print("Would you like to (H)it or (S)tay? ");

                        String input = scanner.nextLine();
                        isHitInput = inputCheck(input, HIT_INPUT);
                        isStayInput = inputCheck(input, STAY_INPUT);

                        inputCorrect = isHitInput || isStayInput;
                    }

                    if (isHitInput) {
                        dealCards(playerCards, 1);
                        printHandStates();
                    }

                    if (isBust(playerCards)) {
                        System.out.println(name + ", I'm sorry but you busted!");
                        break;
                    }
                }

                if (!isBust(playerCards)) {
                    // dealer hits until bust or stay
                    while (shouldDealerKeepHitting(dealerCards)) {
                        System.out.println("The dealer hits!");
                        dealCards(dealerCards, 1);
                        printHandStates();
                    }
                    if (isBust(dealerCards)) {
                        System.out.println("The dealer busted!");
                    }
                }

            }

            // end of each game
            System.out.println("Result: " + determineResult(playerCards, dealerCards));
            clearHand(playerCards);
            clearHand(dealerCards);

            // ask if they would like to play again
            inputCorrect = true;
            isYesInput = false;
            isNoInput = false;
            while (!(isYesInput || isNoInput)) {
                if (!inputCorrect) {
                    System.out.println("Invalid input, try again.");
                }
                System.out.print("Would you like to play again? (Y)es/(N)o: ");
                String playAgainInput = scanner.nextLine();

                isYesInput = inputCheck(playAgainInput, YES_INPUT);
                isNoInput = inputCheck(playAgainInput, NO_INPUT);
                shouldKeepPlaying = !isNoInput;
                inputCorrect = false;
            }
        }
    }

    private boolean inputCheck(String input, String[] allowed) {
        for (String a : allowed) {
            if (input.toLowerCase().equals(a.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private void printHandStates() {
        System.out.println("Your Hand: " + handToString(playerCards));
        System.out.println("Dealer's Hand: " + handToString(dealerCards));
    }

    private String handToString(Card[] hand) {
        String output = "";
        boolean firstCard = true;
        for (Card c : hand) {
            if (c != null) {
                if (firstCard) {
                    firstCard = false;
                } else {
                    output += ", ";
                }
                output += c.toString();
            }
        }
        return output;
    }

    private void clearHand(Card[] hand) {
        for (int i = 0; i < hand.length; i++) {
            hand[i] = null;
        }
    }

    private void dealCards(Card[] hand, int num) {
        for (int i = 0; i < num; i++) {
            dealOneCard(hand);
        }
    }

    private void dealOneCard(Card[] hand) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == null) {
                hand[i] = deck.deal();
                return;
            }
        }
    }

    private static int getScore(Card c) {
        if (c == null) {
            return 0;
        }
        String value = c.getValue().toLowerCase();
        if (value.equals("ace")) {
            return 11;
        }
        if (value.equals("king") || value.equals("queen") || value.equals("jack")) {
            return 10;
        }
        return Integer.valueOf(value);
    }

    public static int calcPoints(Card[] hand) {
        int runningTotal = 0;
        for (Card c : hand) {
            runningTotal += getScore(c);
        }
        return runningTotal;
    }

    // `"User Wins"`, `"User Loses"`, or `"User Pushes"`
    public static String determineResult(Card[] userHand, Card[] dealerHand) {
        int userScore = calcPoints(userHand);
        int dealerScore = calcPoints(dealerHand);
        if (isBust(userHand)) {
            return "User Loses";
        }
        if (userScore > dealerScore) {
            return "User Wins";
        }
        if (userScore == dealerScore) {
            return "User Pushes";
        }
        return "User Loses";
    }

    public static boolean isBust(Card[] hand) {
        return calcPoints(hand) > 21;
    }

    public static boolean isBlackjack(Card[] hand) {
        return getNumCards(hand) == 2 && calcPoints(hand) == 21;
    }

    private static int getNumCards(Card[] hand) {
        int count = 0;
        for (Card c : hand) {
            if (c != null) {
                count++;
            }
        }
        return count;
    }

    public static boolean shouldDealerKeepHitting(Card[] hand) {
        return calcPoints(hand) < 17;
    }
}
