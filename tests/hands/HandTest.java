package hands;

import cards.Card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic JUnit test on <i>Hand</i> class
 *
 * @author Gabriel Cogne
 * @author
 * @author
 * @author
 */

public class HandTest {

    /**
     * This method test the isEmpty(), add(obj), size() and get(index) method on <i>Hand</i> class
     */
    @Test
    public void testAddingGettingCard () {
        Hand hand1 = new Hand();

        Card card1 = new Card(2);
        Card card2 = new Card(13);

        assertTrue (hand1.isEmpty(), "Test isEmpty hand == true");

        hand1.add(card1);
        hand1.add(card2);

        assertEquals(2, hand1.size(), "Test size of hand");

        assertEquals(card2, hand1.get(1), "Test on getter for second card");

        assertTrue (!hand1.isEmpty(), "Test isEmpty hand == false");

        Card card3 = new Card(2);
        Card card4 = new Card(13);
        Card card5 = new Card(2);
        Card card6 = new Card(13);

        hand1.add(card3);
        hand1.add(card4);
        hand1.add(card5);

        try {
            hand1.add(card6);
            fail("An exception should be raised");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Too many cards in the hand", e.getMessage(), "Test taille limite de la liste");
        }
    }

    @Test
    public void testGetHighestCard () {
        HandBuilder builder = new HandBuilder();

        Hand hand1 = builder.buildHandFromString("2Tr RCa");

        assertEquals(13, hand1.getHighestCard().getValue(), "Get highest value");
    }

    @Test
    public void testIsColor () {
        HandBuilder builder = new HandBuilder();

        Hand hand1 = builder.buildHandFromString("");

        assertEquals(null, hand1.isColor(), "Test is not color 1");

        hand1 = builder.buildHandFromString("7Tr 6Tr 8Ca 9Tr 5Co");

        assertEquals(null, hand1.isColor(), "Test is not color 2");

        hand1 = builder.buildHandFromString("7Tr 6Tr 8Tr 9Tr 5Tr");

        assertEquals(9, hand1.isColor().getValue(), "Test is color + return value");
    }

    @Test
    public void testSuites () {
        HandBuilder builder = new HandBuilder();

        Hand hand1 = builder.buildHandFromString("");

        assertEquals(null, hand1.isSuite(), "Test is not suite 1");
        assertEquals(null, hand1.isQuinteFlush(), "Test is not quinte flush 1");

        hand1 = builder.buildHandFromString("7Tr 6Tr DCa 9Tr 5Co");
        assertEquals(null, hand1.isSuite(), "Test is not suite 2");
        assertEquals(null, hand1.isQuinteFlush(), "Test is not quinte flush 2");

        hand1 = builder.buildHandFromString("7Tr 6Tr 8Ca 9Tr 5Co");
        assertEquals(9, hand1.isSuite().getValue(), "Test is suite 1");
        assertEquals(null, hand1.isQuinteFlush(), "Test is not quinte flush 3");

        hand1 = builder.buildHandFromString("7Tr 6Tr 8Tr 9Tr 5Tr");
        assertEquals(9, hand1.isSuite().getValue(), "Test is suite 2");
        assertEquals(9, hand1.isQuinteFlush().getValue(), "Test is quinte flush");
    }
}
