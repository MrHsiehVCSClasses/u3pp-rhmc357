package u3pp;

//imports the scanner item used to read user input
import java.util.Scanner;

public class Blackjack {
    //creates the variables and items that are needed to play the game
    String name;
    Deck myDeck = new Deck();
    Card[] userHand = new Card[11];
    Card[] dealerHand = new Card[11];

    //everytime blackjack is called it shuffles the deck
    public Blackjack(){
        myDeck.shuffle();
    }

    //checks if there are no cards left in the deck and whether
    //or not it needs to be shuffled
    private void shouldShuffle(){
        if (myDeck.numLeft() == 0) {
            myDeck.shuffle();
        }
    }

    //used in calcPoints function
    //only goes through the cards in the hand that are not null
    //there acn be a max of 11 cards in a hand but not all the slots are filled at once
    //so null is given to the slots where there are no cards
    private static int numNotNull (Card[] hand){
        int numNotNull = 0;
        for (int i = 0; i < hand.length; i++){
            if (hand[i] == null){
                numNotNull = i-1;
                break;
            }
            if (hand[i] != null){
                numNotNull += 1;
            }
        }
        return numNotNull;
    }

    //calculates the number of points and prints out 
    //how many points the given hand has
    public static int calcPoints(Card[] hand){
        int total = 0;
        int value = 0;
        for (int i = 0; i < hand.length; i++){
            if (hand[i] == null){
                continue;
            }
            if (hand[i].getValue() == "Ace"){
                value = 11;
            }
            else if (hand[i].getValue() == "Jack"){
                value = 10;
            }
            else if (hand[i].getValue() == "Queen"){
                value = 10;
            }
            else if (hand[i].getValue() == "King"){
                value = 10;
            }
            else {
                value = Integer.parseInt(hand[i].getValue());
            }
            total = value + total;
        }
        System.out.println("Total points are: " + total);
        return total;
    }

    //checks if the given hand has acheived exactly
    //21 points
    public static boolean isBlackjack(Card[] hand){
        if (calcPoints(hand) == 21 && numNotNull(hand) == 2 && (isBust(hand) == false)){
            System.out.println( "Blackjack: " + true);
            return true;
        }
        System.out.println( "Blackjack: " + false);
        return false;
    }

    //checks if the dealer should conitunue to hit the given hand 
    //can only do this if the total points are less than 17
    public static boolean shouldDealerKeepHitting(Card[] hand){
        if (calcPoints(hand) <= 16){
            System.out.println("Dealer will keep hitting!");
            return true;
        }
        System.out.println("Dealer can no longer hit");
        return false;
    }

    //checks if the given hand has a total point count of 
    //over 21
    public static boolean isBust(Card[] hand){
        if (calcPoints(hand) > 21) {
            return true;
        }
        return false;
    }

    //used when the user chooses to play again
    //makes all the vales of the cards 0
    private void reset(){
        for (int i = 0; i <= numNotNull(userHand); i++){
            userHand[i].setValue("0");
        }
        for (int i = 0; i <= numNotNull(dealerHand); i++){
            dealerHand[i].setValue("0");
        }
    }

    //used to satrt the game every time
    //deals 2 cards to each player
    //seds the cards that have not been dealt to null
    private void startGame(){
        shouldShuffle();
        userHand[0] = myDeck.deal();
        userHand[1] = myDeck.deal();
        dealerHand[0] = myDeck.deal();
        dealerHand[1] = myDeck.deal();
        for (int i = 2; i < 11; i++){
            userHand[i] = null;
        }
        for (int i = 2; i < 11; i++){
            dealerHand[i] = null;
        }
    }

    //determines and returns the resluts of the game
    public static String determineResult(Card[] userHand, Card[] dealerHand){
        if (calcPoints(userHand) == calcPoints(dealerHand)){
            return "User Pushes";
        }
        if ((calcPoints(userHand) < calcPoints(dealerHand) || isBust(userHand) == true) && isBust(dealerHand) == false){
            return "User Loses";
        }
        return "User Wins";
    }

    //pritns out all of the catrds that are in the user and the dealers hands
    private void printNumInHand(Card[] userHand, Card[] dealerHand){
        System.out.println(name + "'s " + "hand: ");
        for (int i = 0; i <= numNotNull(userHand); i++){
            System.out.println(userHand[i]);
        }
        System.out.println("\nDealer's hand: ");
        for (int i = 0; i <= numNotNull(dealerHand); i++){
            System.out.println(dealerHand[i]);
        }
        System.out.println("(Press enter to continue)");
        
    }

    //does the actual playing of the function
    //its huge and I know there is a bunch of repeated code and some of it is inefficient
    //but it works most of the time so its ok
    //when given a choice between two things like h/s you will have to type the second choice two times 
    //if you choose it. IDK why it just works like that
    public void play(Scanner scanner) {
        //starts off with entering the name
        System.out.println("Hi! What's your name?");
        name = scanner.nextLine();
        System.out.println("Hi " + name + " I'm robo. Let's play blackjack! (press enter to begin)");
        scanner.nextLine();
        
        startGame();
        
        printNumInHand(userHand, dealerHand);
        scanner.nextLine(); //pause in game for clarity
        
        //checks if anyone lost first round
        if (isBust(dealerHand) == true){
            System.out.println("dealer got a bust!");
            System.out.println(name + " wins!");
            System.out.println("Would you like to play again? (y/n)");
            if (scanner.nextLine() .equals ("y")){
                reset();
                play(scanner);
            }
            else if (scanner.nextLine() .equals ("n")){
                System.out.println("OK! Bye!");
            }
            else {
                System.out.println("Faulty input. Ending game because u suck at typing");
            }
        }
        
        //checks if anyone has balckjack 
        if (isBlackjack(dealerHand) == true){
            System.out.println("Congrats! dealer got blackjack!");
            System.out.println("Would you like to play again? (y/n)");
            if (scanner.nextLine() .equals ("y")){
                reset();
                play(scanner);
            }
            else if (scanner.nextLine() .equals ("n")){
                System.out.println("OK! Bye!");
            }
            else {
                System.out.println("Faulty input. Ending game because you suck at typing");
            }
        }
        
        if (isBlackjack(userHand) == true) {
            System.out.println("Congrats! " + name + " got blackjack!");
            System.out.println("Would you like to play again? (y/n)");
            if (scanner.nextLine() .equals ("y")){
                reset();
                play(scanner);
            }
            else if (scanner.nextLine() .equals ("n")){
                System.out.println("OK! Bye!");
            }
            else {
                System.out.println("Faulty input. Ending game because you suck at typing");
            }
        }

        //if no one has blackjack then they are asked to hit or stay
        else {
            System.out.println("would you like to hit or stay? (h/s)");
            if (scanner.nextLine() .equals ("h")){
                System.out.println("you have chosen to hit!");
                shouldShuffle();
                userHand[numNotNull(userHand) + 1] = myDeck.deal();
                printNumInHand(userHand, dealerHand);
                if (calcPoints(userHand) == 21){
                    //if soemone gets 21 then they are asked to play again
                    determineResult(userHand, dealerHand);
                    System.out.println("Would you like to play again? (y/n)");
                    if (scanner.nextLine() .equals ("y")){
                        reset();
                        play(scanner);
                    }
                    else if (scanner.nextLine() .equals ("n")){
                        System.out.println("OK! Bye!");
                    }
                    else {
                        System.out.println("Faulty input. Ending game because you suck at typing");
                    }
                }
                scanner.nextLine();
                if (isBust(userHand) == true){
                    System.out.println("Sorry " + name + " you have got a bust!");
                    System.out.println("dealer wins!");
                    System.out.println("Would you like to play again? (y/n)");
                    if (scanner.nextLine() .equals ("y")){
                        reset();
                        play(scanner);
                    }
                    else if (scanner.nextLine() .equals ("n")){
                        System.out.println("OK! Bye!");
                    }
                    else {
                        System.out.println("Faulty input. Ending game because you suck at typing");
                    }   
                }
                else if (calcPoints(userHand) <= 21){
                    System.out.println("would you like to hit or stay? (h/s)");
                    if (scanner.nextLine() .equals ("h")){
                        System.out.println("you have chosen to hit!");
                        shouldShuffle();
                        userHand[numNotNull(userHand) + 1] = myDeck.deal();
                        printNumInHand(userHand, dealerHand);
                        if (calcPoints(userHand) == 21){
                            determineResult(userHand, dealerHand);
                            System.out.println("Would you like to play again? (y/n)");
                            if (scanner.nextLine() .equals ("y")){
                                reset();
                                play(scanner);
                            }
                            else if (scanner.nextLine() .equals ("n")){
                                System.out.println("OK! Bye!");
                            }
                            else {
                                System.out.println("Faulty input. Ending game because you suck at typing");
                            }
                        }
                        scanner.nextLine();
                        if (isBust(userHand) == true){
                            System.out.println("Sorry " + name + " you have got a bust!");
                            System.out.println("dealer wins!");
                            determineResult(userHand, dealerHand);
                            System.out.println("Would you like to play again? (y/n)");
                            if (scanner.nextLine() .equals ("y")){
                                reset();
                                play(scanner);
                            }
                            else if (scanner.nextLine() .equals ("n")){
                                System.out.println("OK! Bye!");
                            }
                            else {
                                System.out.println("Faulty input. Ending game because you suck at typing");
                            }   
                        }
                    }
                    else if (scanner.nextLine() .equals ("s")){
                        int num = 3;
                        while (shouldDealerKeepHitting(dealerHand) == true) {
                            shouldShuffle();
                            dealerHand[numNotNull(dealerHand) + 1] = myDeck.deal();
                            num +=1;
                            if (isBust(userHand) == true){
                                System.out.println("Sorry " + name + " you have got a bust!");
                                System.out.println("dealer wins!");
                                determineResult(userHand, dealerHand);
                                System.out.println("Would you like to play again? (y/n)");
                                if (scanner.nextLine() .equals ("y")){
                                    reset();
                                    play(scanner);
                                }
                                else if (scanner.nextLine() .equals ("n")){
                                    System.out.println("OK! Bye!");
                                }
                                else {
                                    System.out.println("Faulty input. Ending game because you suck at typing");
                                }
                            }
                        }
                        printNumInHand(userHand, dealerHand);
                        scanner.nextLine();
                        System.out.println(determineResult(userHand, dealerHand));
                        System.out.println("Would you like to play again? (y/n)");
                        if (scanner.nextLine() .equals ("y")){
                            reset();
                            play(scanner);
                        }
                        else if (scanner.nextLine() .equals ("n")){
                            System.out.println("OK! Bye!");
                        }
                        else {
                            System.out.println("Faulty input. Ending game because you suck at typing");
                        }
                    }
                    else {
                        System.out.println("Faulty input. Ending game because u suck at typing");
                    }
                }
            }
            //this function is run when the person chooses to stay
            else if (scanner.nextLine() .equals ("s")){
                int num = 3;
                while (shouldDealerKeepHitting(dealerHand) == true) {
                    shouldShuffle();
                    dealerHand[numNotNull(dealerHand) + 1] = myDeck.deal();
                    num +=1;
                    if (isBust(userHand) == true){
                        System.out.println("Sorry " + name + " you have got a bust!");
                        System.out.println("dealer wins!");
                        System.out.println("Would you like to play again? (y/n)");
                        if (scanner.nextLine() .equals ("y")){
                            reset();
                            play(scanner);
                        }
                        else if (scanner.nextLine() .equals ("n")){
                            System.out.println("OK! Bye!");
                        }
                        else {
                            System.out.println("Faulty input. Ending game because u suck at typing");
                        }
                    }
                }
                printNumInHand(userHand, dealerHand);
                scanner.nextLine();
                System.out.println(determineResult(userHand, dealerHand));
                System.out.println("Would you like to play again? (y/n)");
                if (scanner.nextLine() .equals ("y")){
                    reset();
                    play(scanner);
                }
                else if (scanner.nextLine() .equals ("n")){
                    System.out.println("OK! Bye!");
                }
                else {
                    System.out.println("Faulty input. Ending game because u suck at typing");
                }
            }
            else {
                System.out.println("Faulty input. Ending game because u suck at typing");
            }
        }
    }
}