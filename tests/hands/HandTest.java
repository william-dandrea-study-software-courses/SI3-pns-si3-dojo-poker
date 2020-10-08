package hands;

import cards.Card;

import cards.Color;
import cards.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic JUnit test on <i>Hand</i> class
 *
 * @author Gabriel Cogne
 * @author D'Andrea William
 */

public class HandTest {

    /**
     * This method test the isEmpty(), add(obj), size() and get(index) method on <i>Hand</i> class
     */

    private Hand pairHand;
    private Hand brelanHand;
    private HandBuilder builder;


    @BeforeEach
    public void initHands() {
        builder = new HandBuilder();
    }

    @Test
    public void testGetPairCards(){
        pairHand = builder.buildHandFromString("7Pi 3Co 5Pi 2Ca 7Co");
        Hand doublePairHand = builder.buildHandFromString("7Ca 3Ca 5Tr 3Pi 7Tr");
        brelanHand = builder.buildHandFromString("8Pi 8Co 6Pi 8Ca 9Co");
        Hand randomHand = builder.buildHandFromString("6Tr 4Co ACa RCa DTr");

        assertEquals(Value.seven, pairHand.getPairCards().getValue(), "Test for the same Hand (with pair)");
        assertEquals((doublePairHand.getPairCards().getValue()), pairHand.getPairCards().getValue(), "Test if the return card is the same");
        assertNull(randomHand.getPairCards());
        assertNotNull(brelanHand.getPairCards());


    }

    @Test
    public void testGetDoublePairCards(){

        brelanHand = builder.buildHandFromString("8Pi 8Co 6Pi 8Ca 9Co");

        //assertEquals(doublePairHand.getDoublePairCards(), doublePairHand.getDoublePairCards(), " ");
        //System.out.println(builder.buildHandFromString("5Tr 5Pi 7Pi 5Ca 7Co").getDoublePairCards());

        AbstractMap.SimpleEntry<Card, Card> array1 = builder.buildHandFromString("5Tr 5Pi 7Pi 5Ca 7Co").getDoublePairCards();
        //System.out.println(array1);

        assertEquals(array1.getKey().getValue(), Value.seven, "We have two pairs, 7 and 5");
        assertEquals(array1.getValue().getValue(), Value.five, "We have two pairs, 7 and 5");

        AbstractMap.SimpleEntry<Card, Card> array2 = builder.buildHandFromString("6Tr 4Co ACa RCa DTr").getDoublePairCards();
        //System.out.println(array2);
        assertNull(array2);
    }


    @Test
    public void testNPairs() {
        pairHand = builder.buildHandFromString("7Pi 3Co 5Pi 2Ca 7Co");
        brelanHand = builder.buildHandFromString("8Pi 8Co 6Pi 8Ca 9Co");

        assertEquals(pairHand.getCardWhichHaveNLessOneOtherSameValuedCard(2).size(), 1, "Test for N=2 Size Pair Hand");
        assertEquals(pairHand.getCardWhichHaveNLessOneOtherSameValuedCard(2).get(0), new Card(Value.seven, Color.Pi), "Test for N=2 Value Pair Hand");

        assertEquals(pairHand.getCardWhichHaveNLessOneOtherSameValuedCard(3).size(), 0);

        assertEquals(brelanHand.getCardWhichHaveNLessOneOtherSameValuedCard(3).size(), 1);
        assertEquals(brelanHand.getCardWhichHaveNLessOneOtherSameValuedCard(3).get(0).getValue(), Value.eight);
    }

    @Test
    public  void testBrelans() {
        //

        HandBuilder builder = new HandBuilder();
        Hand square = builder.buildHandFromString("7Pi 7Ca 7Co 7Tr 4Co");
        Hand pair = builder.buildHandFromString("2Pi 8Tr 5Co 5Tr 2Tr");
        Hand brelan = builder.buildHandFromString("6Pi 6Tr 6Co 5Ca 4Tr");
        Hand nothing = builder.buildHandFromString("RPi DCo 10Pi ATr");

        assertTrue(brelan.getBrelan() != null, "This need to be Brelan");
        assertTrue(pair.getBrelan() == null, "This need to not be Brelan, because it's a pair");
        assertTrue(square.getBrelan() == null, "This need to not be Brelan, because it's square");
        assertTrue(nothing.getBrelan() == null, "This need to not be Brelan, because it's haven't anything repeating");
    }

    @Test
    public void testFull() {
        HandBuilder builder = new HandBuilder();
        Hand full = builder.buildHandFromString("9Pi 9Tr 9Co APi ACo");
        Hand square = builder.buildHandFromString("7Pi 7Ca 7Co 7Tr 4Co");
        Hand pair = builder.buildHandFromString("2Pi 8Tr 5Co 5Tr 2Tr");
        Hand brelan = builder.buildHandFromString("6Pi 6Tr 6Co 5Ca 4Tr");
        Hand nothing = builder.buildHandFromString("RPi DCo 10Pi ATr");

        assertTrue(full.getFull() != null, "This need to be full");
        assertTrue(brelan.getFull() == null, "This need to not be Full, because it's brelan");
        assertTrue(pair.getFull() == null, "This need to not be Full, because it's a pair");
        assertTrue(square.getFull() == null, "This need to not be Full, because it's square");
        assertTrue(nothing.getFull() == null, "This need to not be Full, because it's haven't anything repeating");
    }

    @Test
    public void testAddingGettingCard () {
        Hand hand1 = new Hand();

        Card card1 = new Card(Value.two, Color.Co);
        Card card2 = new Card(Value.king, Color.Ca);

        hand1.add(card1);
        hand1.add(card2);

        assertEquals(2, hand1.size(), "Test size of hand");

        assertEquals(card2, hand1.get(1), "Test on getter for second card");

        assertFalse(hand1.isEmpty(), "Test isEmpty hand == false");

        Card card3 = new Card(Value.two);
        Card card4 = new Card(Value.king);
        Card card5 = new Card(Value.two);
        Card card6 = new Card(Value.king);

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

        assertEquals(Value.king, hand1.getHighestCard().getValue(), "Get highest value");
    }

    @Test
    public void testIsColor () {
        HandBuilder builder = new HandBuilder();

        Hand hand1 = builder.buildHandFromString("");

        assertNull(hand1.isFlush(), "Test is not color 1");

        hand1 = builder.buildHandFromString("7Tr 6Tr 8Ca 9Tr 5Tr");

        assertNull(hand1.isFlush(), "Test is not color 2");

        hand1 = builder.buildHandFromString("7Pi 6Pi 8Pi 9Pi 5Pi");

        assertEquals(Value.nine, hand1.isFlush().getValue(), "Test is color + return value");
    }

    @Test
    public void testSuites () {
        HandBuilder builder = new HandBuilder();

        Hand hand1 = builder.buildHandFromString("");

        assertNull(hand1.isStraight(), "Test is not suite 1");
        assertNull(hand1.isStraightFlush(), "Test is not quinte flush 1");

        hand1 = builder.buildHandFromString("7Ca 6Ca DCa 9Ca 8Ca");
        assertNull(hand1.isStraight(), "Test is not suite 2");
        assertNull(hand1.isStraightFlush(), "Test is not quinte flush 2");

        hand1 = builder.buildHandFromString("7Pi 6Pi 8Pi 9Pi 5Co");
        assertEquals(Value.nine, hand1.isStraight().getValue(), "Test is suite 1");
        assertNull(hand1.isStraightFlush(), "Test is not quinte flush 3");

        hand1 = builder.buildHandFromString("7Tr 6Tr 8Tr 9Tr 5Tr");
        assertEquals(Value.nine, hand1.isStraight().getValue(), "Test is suite 2");
        assertEquals(Value.nine, hand1.isStraightFlush().getValue(), "Test is quinte flush");
    }
}
