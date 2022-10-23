# Unit 3 Programming Project

For this project, using the given `Card` class, you will write the `Deck` class. Then, you will write the `Blackjack` class, which has all the functionality to play a simplified version of the game.

## PART A - `PalindromeTester.java`

You must create the `isPalindrome(String s)` in `PalindromeTester.java`.
See the documentation & tests for the method for more information.

## PART B - `Deck.java`

A `Card` class has been provided for you in this project. (Read the file - it has many useful attributes & methods)
You will use the `Card`s to create a `Deck`.
A `Deck` contains 52 unique `Card`s.
The potential values are `[2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace]` and the potential suits are `[Clubs, Spades, Diamonds, Hearts]`.

Your `Deck` class must include the following:

### PART B Required Methods

1. `public Deck()`: Default constructor. Creates 52 unique `Card`s
2. `public int numLeft()`: Returns how many cards have not been dealt in the `Deck`
3. `public Card deal()`: Takes a card off the "top" of the `Deck` and returns it.
4. `public void shuffle()`: Restores the `Deck` to "full" and randomizes the order of the `Card`s to be dealt

You may include any other `private` methods and instance variables you think applicable. You should have nothing else that is `public` other than what is listed above.

## PART C - `Blackjack.java`

For **Part C** you are going to use your newly made `Deck` to create a basic version of the game `Blackjack`.

### Simplified Blackjack Gameplay

Skip to the TLDR if you already know how blackjack works.

### Full Explanation

The goal of the game is to get as close as possible to 21 points without going over. `[2-10]` are worth their face value as points, `[Jack, Queen, King]` are worth 10 points. In our simplified version of the game, `[Ace]` is worth 11 points.

There are two *Players*, the *User* and the *Dealer*. Each are dealt two `Card`s, alternating with the *User* getting the first `Card` dealt.

If either *Player* scores 21 points in the opening hand, the game is over because they have scored a *Blackjack*. If both *Players* score a *Blackjack*, the game ends in a *Push* or tie.

If neither *Player* has *Blackjack*, then the *User* gets to choose to *Hit* or *Stay*. If they choose to *Hit* they are dealt another `Card` from the top of the `Deck`. If the new `Card` makes their points go over 21, they *Bust* and the game ends in their defeat. If they have 21 or less, they are prompted again to *Hit* or *Stay*. This continues until either the *User* *Bust*s or *Stay*s.

If the *User* *Stay*s, the *Dealer* now must *Hit* until their score is greater than or equal to 17, or they *Bust*. If they *Bust* the game is over and the *User* wins. Otherwise, if their score is 17 or higher, the `Card`s are revealed.

When the `Card`s are revealed, the *User* and the *Dealer*'s scores are compared. Whoever has the higher score wins. In the case of a tie, the game ends in a *Push*.

When the game ends, be sure to print the result *Blackjack*, *Bust*, *Win*, *Lose*, or *Push*. Then, prompt the *User* to play again. If they select yes, **do not** shuffle the `Deck` and start over. If not, thank them for their time and end the program. Only shuffle the `Deck` if the `Deck` runs out of `Card`s during gameplay.

#### TLDR Simplified rules

Normal blackjack rules, except aces are always worth 11 points.
See Sample Run below.

### PART C Required Methods

1. `public Blackjack()`: This should instantiate any instance variables you have, especially your `Deck`.
2. `public void play(Scanner scanner)`: The method to be called to actually play a game. This is where you implement the flow of the **Gameplay**. The output should clearly communicate what is happening - cards dealt, comparisons made, etc.
3. `public static int calcPoints(Card[] hand)`: This helper method takes an array of `Card`s and returns the amount of points that hand is worth. Post condition: the parameter should remain unaltered.
4. `public static String determineResult(Card[] userHand, Card[] dealerHand)`: Returns a `String` in one of the following formats: `"User Wins"`, `"User Loses"`, or `"User Pushes"`. Does not alter parameters.
5. `public static boolean isBust(Card[] hand)`: returns `true` if the hand is a *Bust*, `false` otherwise. Does not alter parameters.
6. `public static boolean isBlackjack(Card[] hand)`: returns `true` if the hand is a *Blackjack*, `false` otherwise. Remember, it is only a blackjack in the opening hand (2 card hand). Does not alter parameters.
7. `public static boolean shouldDealerKeepHitting(Card[] hand)`: return `true` if the dealer should keep hitting (score of hand is 16 or less), `false` otherwise. Does  not alter parameters.

You may include any other `private` methods and instance variables you think applicable. You should have nothing else that is `public` other than what is listed above.

### Part C Other Requirements

* **User experience (UX) must be very clear.** You might, for the sake of clarity, have extra pauses in gameplay so the player does not get lost.
  * Must print the outcome of the game: Blackjack, Win, Lose, or Push
  * Regularly print the state of the user hand, the state of the dealer hand, and the state of the deck.
* **Your game should account for bad input.** Bad input should not break the game.
* **Finish your main method** Make a Scanner, and call `Blackjack`'s `play` method with it.
* **Must not shuffle the deck between games.** Only shuffle when we would deal a card when the deck is empty.

### PART B Sample Run

*Note:* Wording does not have to match the given example exactly (have some fun with it!) but it should go through the same general flow, and account for upper/lowercase, and also for invalid input. Gameplay will be manually graded by Mr. Hsieh. He will try his best to break your game. :)

```java
Welcome to Blackjack! What is your name? MrHsieh
Hello MrHsieh! I am Gambletron 5000! Let's play some cards.
Your Hand: Jack of Spades Ace of Clubs 
Dealer's Hand: 2 of Spades 5 of Spades 
Your Hand: Jack of Spades Ace of Clubs 
Dealer's Hand: 2 of Spades 5 of Spades 
Congrats MrHsieh you got a Blackjack!
Result: User Wins
Would you like to play again? (Y)es/(N)o: y
Your Hand: 9 of Hearts Queen of Clubs 
Dealer's Hand: 6 of Clubs 9 of Spades 
Would you like to (H)it or (S)tay: s
Your Hand: 9 of Hearts Queen of Clubs 
Dealer's Hand: 6 of Clubs 9 of Spades 5 of Clubs 
Result: User Loses
Would you like to play again? (Y)es/(N)o: asdoiyufwadsfg
Invalid input, try again
Would you like to play again? (Y)es/(N)o: j8h34r
Invalid input, try again
Would you like to play again? (Y)es/(N)o: Yes
Your Hand: 3 of Clubs 2 of Clubs 
Dealer's Hand: 3 of Hearts 5 of Hearts 
Would you like to (H)it or (S)tay: hit
Your new hand: 3 of Clubs 2 of Clubs 10 of Spades 
Would you like to (H)it or (S)tay: h
Your Hand: 3 of Clubs 2 of Clubs 10 of Spades Ace of Diamonds 
Dealer's Hand: 3 of Hearts 5 of Hearts 
MrHsieh I'm so sorry you busted!
Result: User Loses
Would you like to play again? (Y)es/(N)o: y
Your Hand: 10 of Diamonds King of Hearts 
Dealer's Hand: 8 of Hearts 5 of Diamonds 
Would you like to (H)it or (S)tay: oa;sdf
Invalid input, try again
Would you like to (H)it or (S)tay: stay
Your Hand: 10 of Diamonds King of Hearts 
Dealer's Hand: 8 of Hearts 5 of Diamonds 7 of Hearts 
Result: User Pushes
Would you like to play again? (Y)es/(N)o: no
Thanks for playing MrHsieh! Have a great day!
```

## Grading Breakdown

* Tried hard: 3 points
* Proper formatting/indentation: 3 points
* Correctly JavaDoc'ed all Code: 3 points
* Has no `public` members other than those specified: 3 points
* Pass all PalindromeTester test cases: 3 pts
* Pass all Deck test cases: 3 pts
* Pass all Blackjack test cases: 3 pts
* Blackjack functionality: 6 points
* UX: 3 points
Total: 30 points
  
## How to run automated tests

1. In VSCode (or your IDE of choice), open a terminal (Terminal -> new Terminal)
2. type in `mvn package` and press enter
3. green means successful tests, and red means failed tests
   1. a green `BUILD SUCCESS` means you've passed all tests.
   2. Failed tests will tell you exactly which tests you failed. Hopefully, the test function names are descriptive enough to give you a general idea of what the error is. If not, you can always examine the test code to see what failed. If you are completely lost, ask Mr. Hsieh :)
