package hands;

import cards.Card;

import cards.Color;
import org.junit.jupiter.api.BeforeEach;
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

    private Hand pairHand, doublePairHand, brelanHand, randomHand;
    private HandBuilder builder;


    @BeforeEach
    public void initHands() {
        builder = new HandBuilder();
    }

    @Test
    public void testGetPairCards(){
        pairHand = builder.buildHandFromString("7Pi 3Co 5Pi 2Ca 7Co");
        doublePairHand = builder.buildHandFromString("7Ca 3Ca 5Tr 3Pi 7Tr");
        brelanHand = builder.buildHandFromString("8Pi 8Co 6Pi 8Ca 9Co");
        randomHand = builder.buildHandFromString("6Tr 4Co ACa RCa DTr");

        assertEquals(7, pairHand.getPairCards().getValue(), "Test for the same Hand (with pair)");
        assertTrue(pairHand.getPairCards().getValue() == (doublePairHand.getPairCards().getValue()), "Test if the return card is the same");
        assertTrue(randomHand.getPairCards() == null);
        assertFalse(brelanHand.getPairCards() == null);


    }

    @Test
    public void testGetDoublePairCards(){
        pairHand = builder.buildHandFromString("7Pi 3Co 5Pi 2Ca 7Co");
        doublePairHand = builder.buildHandFromString("7Ca 4Ca 3Tr 3Pi 7Tr");
        brelanHand = builder.buildHandFromString("8Pi 8Co 6Pi 8Ca 9Co");
        randomHand = builder.buildHandFromString("6Tr 4Co ACa RCa DTr");

        //assertEquals(doublePairHand.getDoublePairCards(), doublePairHand.getDoublePairCards(), " ");
        System.out.println(doublePairHand.getDoublePairCards());


    }


    @Test
    public void testNPairs() {
        pairHand = builder.buildHandFromString("7Pi 3Co 5Pi 2Ca 7Co");
        brelanHand = builder.buildHandFromString("8Pi 8Co 6Pi 8Ca 9Co");

        assertEquals(pairHand.getCardWhichHaveNLessOneOtherSameValuedCard(2).size(), 1, "Test for N=2 Size Pair Hand");
        assertEquals(pairHand.getCardWhichHaveNLessOneOtherSameValuedCard(2).get(0), new Card(7, Color.Pi), "Test for N=2 Value Pair Hand");

        assertEquals(pairHand.getCardWhichHaveNLessOneOtherSameValuedCard(3).size(), 0);

        assertEquals(brelanHand.getCardWhichHaveNLessOneOtherSameValuedCard(3).size(), 1);
        assertEquals(brelanHand.getCardWhichHaveNLessOneOtherSameValuedCard(3).get(0).getValue(), 8);
    }

    @Test
    public  void testBrelans() {

    }


    @Test
    public void testAddingGettingCard () {
        Hand hand1 = new Hand();

        Card card1 = new Card(2, Color.Co);
        Card card2 = new Card(13, Color.Ca);

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

        hand1 = builder.buildHandFromString("7Tr 6Tr 8Ca 9Tr 5Tr");

        assertEquals(null, hand1.isColor(), "Test is not color 2");

        hand1 = builder.buildHandFromString("7Pi 6Pi 8Pi 9Pi 5Pi");

        assertEquals(9, hand1.isColor().getValue(), "Test is color + return value");
    }

    @Test
    public void testSuites () {
        HandBuilder builder = new HandBuilder();

        Hand hand1 = builder.buildHandFromString("");

        assertEquals(null, hand1.isSuite(), "Test is not suite 1");
        assertEquals(null, hand1.isQuinteFlush(), "Test is not quinte flush 1");

        hand1 = builder.buildHandFromString("7Ca 6Ca DCa 9Ca 8Ca");
        assertEquals(null, hand1.isSuite(), "Test is not suite 2");
        assertEquals(null, hand1.isQuinteFlush(), "Test is not quinte flush 2");

        hand1 = builder.buildHandFromString("7Pi 6Pi 8Pi 9Pi 5Co");
        assertEquals(9, hand1.isSuite().getValue(), "Test is suite 1");
        assertEquals(null, hand1.isQuinteFlush(), "Test is not quinte flush 3");

        hand1 = builder.buildHandFromString("7Tr 6Tr 8Tr 9Tr 5Tr");
        assertEquals(9, hand1.isSuite().getValue(), "Test is suite 2");
        assertEquals(9, hand1.isQuinteFlush().getValue(), "Test is quinte flush");
    }
}
