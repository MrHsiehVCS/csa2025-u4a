package u4a;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckTests {

    private static final int DECK_SIZE = Card.VALUES.length * Card.SUITS.length;

    @Test
    void testNumLeftAfterConstructor() {
        Deck testDeck = new Deck();
        assertEquals(DECK_SIZE, testDeck.numLeft(), "A new deck should start with 52 cards.");
    }

    @Test
    void testDealReducesNumLeftByOne() {
        Deck testDeck = new Deck();
        int initialSize = testDeck.numLeft();
        testDeck.deal();
        assertEquals(initialSize - 1, testDeck.numLeft(), "Dealing one card should reduce the deck size by 1.");
    }

    @Test
    void testNumLeftAfterDealingAllCards() {
        Deck testDeck = new Deck();
        for (int i = 0; i < DECK_SIZE; i++) {
            testDeck.deal();
        }
        assertEquals(0, testDeck.numLeft(), "After dealing all 52 cards, the deck should be empty.");
    }

    @Test
    void testNewDeckDealsOutAllCards() {
        Deck testDeck = new Deck();
        ArrayList<Card> dealtCards = dealAllCardsFromDeck(testDeck);
        assertEquals(0, testDeck.numLeft(), "Dealing all cards from a new deck should leave 0 cards left.");
    }

    @Test
    void testNewDeckDealsUniqueCards() {
        Deck testDeck = new Deck();
        ArrayList<Card> dealtCards = dealAllCardsFromDeck(testDeck);
        assertTrue(allUnique(dealtCards), "All cards in a new deck should be unique with no duplicates.");
    }

    @Test
    void testShuffleRestoresFullDeck() {
        Deck testDeck = new Deck();
        dealAllCardsFromDeck(testDeck); // Empty the deck
        testDeck.shuffle();
        assertEquals(DECK_SIZE, testDeck.numLeft(), "Shuffling an empty deck should refill it to 52 cards.");
    }

    @Test
    void testShuffleChangesOrder() {
        Deck testDeck = new Deck();
        ArrayList<Card> originalOrder = dealAllCardsFromDeck(testDeck);
        testDeck.shuffle();
        ArrayList<Card> shuffledOrder = dealAllCardsFromDeck(testDeck);
        assertTrue(areDifferent(originalOrder, shuffledOrder), "Shuffling should change the order of the cards.");
    }

    @Test
    void testShuffleOnEmptyDeck() {
        Deck testDeck = new Deck();
        dealAllCardsFromDeck(testDeck); // Empty the deck
        // Shuffle should not throw an exception
        testDeck.shuffle();
        assertEquals(DECK_SIZE, testDeck.numLeft(), "Shuffling an empty deck should refill it to 52 cards.");
    }

    @Test
    void testSetCardsUpdatesDeck() {
        Deck testDeck = new Deck();
        ArrayList<Card> customCards = new ArrayList<>();
        customCards.add(new Card("Hearts", "Ace"));
        customCards.add(new Card("Spades", "King"));
        testDeck.setCards(customCards);
        assertEquals(2, testDeck.numLeft(), "Setting custom cards should update the deck size to match the list.");
        assertEquals(new Card("Hearts", "Ace"), testDeck.deal(), "The first card dealt after setting should be the first in the custom list.");
        assertEquals(new Card("Spades", "King"), testDeck.deal(), "The second card dealt should be the second in the custom list.");
    }

    @Test
    void testSetCardsWithEmptyList() {
        Deck testDeck = new Deck();
        testDeck.setCards(new ArrayList<>());
        assertEquals(0, testDeck.numLeft(), "Setting an empty list should make the deck have 0 cards.");
        assertThrows(IllegalStateException.class, () -> testDeck.deal(), "Trying to deal from a deck set to empty should cause an error.");
    }

    /**
     * Helper method to deal all cards from the deck.
     * Assumes the deck has cards; otherwise, it will throw an exception.
     */
    private static ArrayList<Card> dealAllCardsFromDeck(Deck deck) {
        ArrayList<Card> dealtCards = new ArrayList<>();
        while (deck.numLeft() > 0) {
            dealtCards.add(deck.deal());
        }
        return dealtCards;
    }

    /**
     * Helper method to check if two lists of cards are different in order.
     */
    private static boolean areDifferent(ArrayList<Card> cards, ArrayList<Card> otherCards) {
        if (cards.size() != otherCards.size()) {
            return false;
        }
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).equals(otherCards.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to check if all cards in the list are unique.
     * Note: This is O(n^2) for 52 cards, which is acceptable for testing.
     */
    private static boolean allUnique(ArrayList<Card> dealtCards) {
        for (int i = 0; i < dealtCards.size(); i++) {
            for (int j = i + 1; j < dealtCards.size(); j++) {
                if (dealtCards.get(i).equals(dealtCards.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
