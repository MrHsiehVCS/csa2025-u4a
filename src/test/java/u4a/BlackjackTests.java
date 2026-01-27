package u4a;
import java.io.*;
import java.util.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackTests {

    private static Card[] 
        hand1 = {}, 
        hand2 = { new Card("Clubs", "Ace"), new Card("Spades", "Jack") },
        hand3 = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "7") },
        hand4 = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "7"),
                new Card("Diamonds", "Ace"), new Card("Hearts", "3") },
        hand5 = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "9") },
        hand6 = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "4") },
        hand7 = { new Card("Diamonds", "10"), new Card("Spades", "7"), new Card("Hearts", "3") };

    private static Card[] 
        hand1Copy = {}, 
        hand2Copy = { new Card("Clubs", "Ace"), new Card("Spades", "Jack") },
        hand3Copy = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "7") },
        hand4Copy = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "7"),
                new Card("Diamonds", "Ace"), new Card("Hearts", "3") },
        hand5Copy = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "9") },
        hand6Copy = { new Card("Clubs", "2"), new Card("Diamonds", "10"), new Card("Spades", "4") },
        hand7Copy = { new Card("Diamonds", "10"), new Card("Spades", "7"), new Card("Hearts", "3") };

    @Test
    void testCalcPointsEmptyHand() {
        assertEquals(0, Blackjack.calcPoints(hand1), "An empty hand should have 0 points.");
    }

    @Test
    void testCalcPointsBlackjack() {
        assertEquals(21, Blackjack.calcPoints(hand2), "A blackjack hand (Ace and Jack) should have 21 points.");
    }

    @Test
    void testCalcPointsNormalHand() {
        assertEquals(19, Blackjack.calcPoints(hand3), "A normal hand with 2, 10, and 7 should have 19 points.");
    }

    @Test
    void testCalcPointsBustHand() {
        assertEquals(33, Blackjack.calcPoints(hand4), "A bust hand over 21 should still calculate to 33 points.");
    }

    @Test
    void testCalcPointsDoesNotAlterEmptyHand() {
        Blackjack.calcPoints(hand1);
        assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1), "Calculating points should not change the empty hand.");
    }

    @Test
    void testCalcPointsDoesNotAlterBlackjackHand() {
        Blackjack.calcPoints(hand2);
        assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2), "Calculating points should not change the blackjack hand.");
    }

    @Test
    void testCalcPointsDoesNotAlterNormalHand() {
        Blackjack.calcPoints(hand3);
        assertEquals(Arrays.toString(hand3Copy), Arrays.toString(hand3), "Calculating points should not change the normal hand.");
    }

    @Test
    void testIsBustEmptyHand() {
        assertFalse(Blackjack.isBust(hand1), "An empty hand is not a bust.");
    }

    @Test
    void testIsBustBlackjackHand() {
        assertFalse(Blackjack.isBust(hand2), "A blackjack hand is not a bust.");
    }

    @Test
    void testIsBustNormalHand() {
        assertFalse(Blackjack.isBust(hand3), "A normal hand under 21 is not a bust.");
    }

    @Test
    void testIsBustBustHand() {
        assertTrue(Blackjack.isBust(hand4), "A hand over 21 is a bust.");
    }

    @Test
    void testIsBustDoesNotAlterEmptyHand() {
        Blackjack.isBust(hand1);
        assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1), "Checking if bust should not change the empty hand.");
    }

    @Test
    void testIsBustDoesNotAlterBlackjackHand() {
        Blackjack.isBust(hand2);
        assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2), "Checking if bust should not change the blackjack hand.");
    }

    @Test
    void testIsBustDoesNotAlterNormalHand() {
        Blackjack.isBust(hand3);
        assertEquals(Arrays.toString(hand3Copy), Arrays.toString(hand3), "Checking if bust should not change the normal hand.");
    }

    @Test
    void testIsBlackjackEmptyHand() {
        assertFalse(Blackjack.isBlackjack(hand1), "An empty hand is not a blackjack.");
    }

    @Test
    void testIsBlackjackBlackjackHand() {
        assertTrue(Blackjack.isBlackjack(hand2), "A hand with Ace and Jack is a blackjack.");
    }

    @Test
    void testIsBlackjackBustHand() {
        assertFalse(Blackjack.isBlackjack(hand4), "A bust hand is not a blackjack.");
    }

    @Test
    void testIsBlackjackNormalHand() {
        assertFalse(Blackjack.isBlackjack(hand5), "A normal hand is not a blackjack.");
    }

    @Test
    void testIsBlackjackDoesNotAlterEmptyHand() {
        Blackjack.isBlackjack(hand1);
        assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1), "Checking if blackjack should not change the empty hand.");
    }

    @Test
    void testIsBlackjackDoesNotAlterBlackjackHand() {
        Blackjack.isBlackjack(hand2);
        assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2), "Checking if blackjack should not change the blackjack hand.");
    }

    @Test
    void testIsBlackjackDoesNotAlterNormalHand() {
        Blackjack.isBlackjack(hand3);
        assertEquals(Arrays.toString(hand3Copy), Arrays.toString(hand3), "Checking if blackjack should not change the normal hand.");
    }

    @Test
    void testIsBlackjackDoesNotAlterHand5() {
        Blackjack.isBlackjack(hand5);
        assertEquals(Arrays.toString(hand5Copy), Arrays.toString(hand5), "Checking if blackjack should not change hand5.");
    }

    @Test
    void testDealerKeepHittingEmptyHand() {
        assertTrue(Blackjack.dealerKeepHitting(hand1), "An empty hand means the dealer should keep hitting.");
    }

    @Test
    void testDealerKeepHittingBlackjackHand() {
        assertFalse(Blackjack.dealerKeepHitting(hand2), "A blackjack hand means the dealer should stop hitting.");
    }

    @Test
    void testDealerKeepHittingBustHand() {
        assertFalse(Blackjack.dealerKeepHitting(hand4), "A bust hand means the dealer should stop hitting.");
    }

    @Test
    void testDealerKeepHittingLowHand() {
        assertTrue(Blackjack.dealerKeepHitting(hand6), "A low hand under 17 means the dealer should keep hitting.");
    }

    @Test
    void testDealerKeepHittingDoesNotAlterEmptyHand() {
        Blackjack.dealerKeepHitting(hand1);
        assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1), "Checking if dealer keeps hitting should not change the empty hand.");
    }

    @Test
    void testDealerKeepHittingDoesNotAlterBlackjackHand() {
        Blackjack.dealerKeepHitting(hand2);
        assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2), "Checking if dealer keeps hitting should not change the blackjack hand.");
    }

    @Test
    void testDealerKeepHittingDoesNotAlterBustHand() {
        Blackjack.dealerKeepHitting(hand4);
        assertEquals(Arrays.toString(hand4Copy), Arrays.toString(hand4), "Checking if dealer keeps hitting should not change the bust hand.");
    }

    @Test
    void testDealerKeepHittingDoesNotAlterLowHand() {
        Blackjack.dealerKeepHitting(hand6);
        assertEquals(Arrays.toString(hand6Copy), Arrays.toString(hand6), "Checking if dealer keeps hitting should not change the low hand.");
    }

    @Test
    void testDetermineResultUserLosesEmptyVsBlackjack() {
        assertEquals("User Loses", Blackjack.determineResult(hand1, hand2), "An empty hand loses to a blackjack.");
    }

    @Test
    void testDetermineResultUserLosesLowVsHigh() {
        assertEquals("User Loses", Blackjack.determineResult(hand6, hand7), "A lower hand loses to a higher hand.");
    }

    @Test
    void testDetermineResultUserWinsBlackjackVsNormal() {
        assertEquals("User Wins", Blackjack.determineResult(hand2, hand7), "A blackjack wins against a normal hand.");
    }

    @Test
    void testDetermineResultUserWinsHighVsLow() {
        assertEquals("User Wins", Blackjack.determineResult(hand7, hand6), "A higher hand wins against a lower hand.");
    }

    @Test
    void testDetermineResultUserPushesBlackjackVsBlackjack() {
        assertEquals("User Pushes", Blackjack.determineResult(hand2, hand2), "Two blackjacks result in a tie.");
    }

    @Test
    void testDetermineResultUserPushesEqualHands() {
        assertEquals("User Pushes", Blackjack.determineResult(hand7, hand7), "Equal hands result in a tie.");
    }

    @Test
    void testDetermineResultDoesNotAlterUserHand1() {
        Blackjack.determineResult(hand1, hand2);
        assertEquals(Arrays.toString(hand1Copy), Arrays.toString(hand1), "Determining result should not change the user's hand.");
    }

    @Test
    void testDetermineResultDoesNotAlterDealerHand2() {
        Blackjack.determineResult(hand1, hand2);
        assertEquals(Arrays.toString(hand2Copy), Arrays.toString(hand2), "Determining result should not change the dealer's hand.");
    }

    @Test
    void testDetermineResultDoesNotAlterUserHand6() {
        Blackjack.determineResult(hand6, hand7);
        assertEquals(Arrays.toString(hand6Copy), Arrays.toString(hand6), "Determining result should not change the user's hand.");
    }

    @Test
    void testDetermineResultDoesNotAlterDealerHand7() {
        Blackjack.determineResult(hand6, hand7);
        assertEquals(Arrays.toString(hand7Copy), Arrays.toString(hand7), "Determining result should not change the dealer's hand.");
    }

    // Tests for Blackjack.play() using dependency injection and I/O simulation
    @Test
    void testPlayUserBlackjack() throws Exception {
        testPlayScenario(
            "TestUser\nn\n", // input: name, no play again
            Arrays.asList(
                new Card("Spades", "Jack"), // user1
                new Card("Spades", "2"),    // dealer1
                new Card("Clubs", "Ace"),  // user2
                new Card("Spades", "5")    // dealer2
            ),
            Arrays.asList("Blackjack", "User Wins")
        );
    }

    @Test
    void testPlayDealerBlackjack() throws Exception {
        testPlayScenario(
            "TestUser\nn\n",
            Arrays.asList(
                new Card("Hearts", "7"),   // user1
                new Card("Clubs", "Ace"),  // dealer1
                new Card("Diamonds", "8"), // user2
                new Card("Spades", "Jack") // dealer2
            ),
            Arrays.asList("Blackjack", "User Loses")
        );
    }

    @Test
    void testPlayBothBlackjackPush() throws Exception {
        testPlayScenario(
            "TestUser\nn\n",
            Arrays.asList(
                new Card("Clubs", "Ace"),  // user1
                new Card("Diamonds", "Ace"), // dealer1
                new Card("Spades", "Jack"), // user2
                new Card("Hearts", "Jack")  // dealer2
            ),
            Arrays.asList("Blackjack", "User Pushes")
        );
    }

    @Test
    void testPlayUserHitsAndWins() throws Exception {
        testPlayScenario(
            "TestUser\ns\nn\n", // stay, no play again
            Arrays.asList(
                new Card("Hearts", "9"),   // user1
                new Card("Clubs", "6"),    // dealer1
                new Card("Diamonds", "Queen"), // user2
                new Card("Spades", "9"),   // dealer2
                new Card("Hearts", "2")    // dealer hit -> 15+2=17
            ),
            Arrays.asList("User Wins")
        );
    }

    @Test
    void testPlayUserBusts() throws Exception {
        testPlayScenario(
            "TestUser\nh\nh\nn\n", // hit, hit, no play again
            Arrays.asList(
                new Card("Clubs", "3"),    // user1
                new Card("Hearts", "3"),   // dealer1
                new Card("Diamonds", "2"), // user2
                new Card("Spades", "5"),   // dealer2
                new Card("Hearts", "10"),  // user hit1
                new Card("Clubs", "Ace")   // user hit2 -> bust
            ),
            Arrays.asList("busted", "User Loses")
        );
    }

    @Test
    void testPlayDealerBusts() throws Exception {
        testPlayScenario(
            "TestUser\ns\nn\n", // stay, no play again
            Arrays.asList(
                new Card("Hearts", "10"),  // user1
                new Card("Clubs", "6"),    // dealer1
                new Card("Diamonds", "7"), // user2
                new Card("Spades", "9"),   // dealer2
                new Card("Hearts", "Ace")  // dealer hit -> 15+11=26 bust
            ),
            Arrays.asList("User Wins")
        );
    }

    @Test
    void testPlayUserLoses() throws Exception {
        testPlayScenario(
            "TestUser\ns\nn\n", // stay, no play again
            Arrays.asList(
                new Card("Hearts", "9"),   // user1
                new Card("Clubs", "10"),   // dealer1
                new Card("Diamonds", "Queen"), // user2
                new Card("Spades", "10")   // dealer2 -> dealer 20, user 19
            ),
            Arrays.asList("User Loses")
        );
    }

    @Test
    void testPlayPush() throws Exception {
        testPlayScenario(
            "TestUser\ns\nn\n", // stay, no play again
            Arrays.asList(
                new Card("Hearts", "10"),  // user1
                new Card("Clubs", "10"),   // dealer1
                new Card("Diamonds", "6"), // user2
                new Card("Spades", "6"),   // dealer2
                new Card("Hearts", "2")    // dealer hit -> 16+2=18
            ),
            Arrays.asList("User Loses")
        );
    }

    @Test
    void testPlayInvalidInput() throws Exception {
        testPlayScenario(
            "TestUser\ninvalid\ny\ns\nn\n", // invalid play again, then yes, then stay, then no
            Arrays.asList(
                new Card("Spades", "Jack"),
                new Card("Spades", "2"),
                new Card("Clubs", "Ace"),
                new Card("Spades", "5")
            ),
            Arrays.asList("Invalid input", "Blackjack", "User Wins")
        );
    }

    private void testPlayScenario(String input, List<Card> deckCards, List<String> expectedOutputs) throws Exception {
        // Save original streams
        PrintStream originalOut = System.out;

        try {
            // Capture output
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));

            // Create controlled deck
            Deck testDeck = new Deck();
            testDeck.setCards(deckCards);

            // Create scanner from input
            Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

            // Create Blackjack with injected dependencies
            Blackjack bj = new Blackjack(testDeck, scanner);

            // Call play
            bj.play();

            // Check output
            String output = out.toString();
            for (String expected : expectedOutputs) {
                assertTrue(output.toLowerCase().contains(expected.toLowerCase()), 
                    "Expected output to contain: " + expected + "\nActual output:\n" + output);
            }
        } finally {
            // Restore streams
            System.setOut(originalOut);
        }
    }
}