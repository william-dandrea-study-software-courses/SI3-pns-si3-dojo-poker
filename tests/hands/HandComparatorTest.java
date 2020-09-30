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

    private HandBuilder builder;


    @BeforeEach
    public void initHands() {
        builder = new HandBuilder();
    }



    @Test
    public void testGetExplicationHighestCard () {
        HandComparator comparator = new HandComparator();

        assertTrue(comparator.getExplication() == null);

        Hand h1 = builder.buildHandFromString("5Tr 7Ca 10Pi DCa RCo");
        Hand h2 = builder.buildHandFromString("5Ca 7Co 10Tr DCo ACo");

        Hand winner = comparator.compare(h1, h2);

        assertEquals(h2, winner, "Test 1 winner by highest card");
        assertEquals(" carte la plus élevée : ACo", comparator.getExplication(),
                "Test 1 explication on winner by highest card");

        winner = comparator.compare(h2, h1);
        assertEquals(h2, winner, "Test 2 winner by highest card");
        assertEquals(" carte la plus élevée : ACo", comparator.getExplication(),
                "Test 2 explication on winner by highest card");

        Hand h3 = builder.buildHandFromString("5Pi 7Tr 10Ca DPi ACa");

        winner = comparator.compare(h2, h3);
        assertEquals(null, winner, "Test 3 winner by highest card");
        assertEquals("", comparator.getExplication(),
                "Test 3 explication on winner by highest card");
    }
}
