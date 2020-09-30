package hands;

import cards.Card;
import cards.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic JUnit test on <i>HandComparator</i> class
 *
 * @author
 * @author
 * @author
 * @author
 */

public class HandComparatorTest {

    private Hand pairHand, doublePairHand, brelanHand, carreHand, randomHand;
    private HandBuilder builder;


    @BeforeEach
    public void initHands() {
        HandBuilder builder = new HandBuilder();

        pairHand = builder.buildHandFromString("7Pi 3Co 5Pi 2Ca 7Co");
        doublePairHand = builder.buildHandFromString("7Pi 3Co 5Pi 3Ca 7Co");
        brelanHand = builder.buildHandFromString("7Pi 7Co 5Pi 7Ca 9Co");
        carreHand = builder.buildHandFromString("7Pi 7Co 5Ca 7Ca 7Tr");
        randomHand = builder.buildHandFromString("6Pi 4Co 5Ca 7Ca 2Tr");
    }



    @Test
    public void testGetExplicationHighestCard () {
        HandComparator comparator = new HandComparator();

        assertTrue(comparator.getExplication() == null);

        HandBuilder builder = new HandBuilder();
        Hand h1 = builder.buildHandFromString("5Tr 7Ca 10Pi DCa RCo");
        Hand h2 = builder.buildHandFromString("5Tr 7Ca 10Pi DCa ACo");

        Hand winner = comparator.compare(h1, h2);

        assertEquals(h2, winner, "Test 1 winner by highest card");
        assertEquals(" carte la plus élevée : ACo", comparator.getExplication(),
                "Test 1 explication on winner by highest card");

        winner = comparator.compare(h2, h1);
        assertEquals(h2, winner, "Test 2 winner by highest card");
        assertEquals(" carte la plus élevée : ACo", comparator.getExplication(),
                "Test 2 explication on winner by highest card");

        Hand h3 = builder.buildHandFromString("5Tr 7Ca 10Pi DCa ACo");

        winner = comparator.compare(h2, h3);
        assertEquals(null, winner, "Test 3 winner by highest card");
        assertEquals("", comparator.getExplication(),
                "Test 3 explication on winner by highest card");
    }
}
