package hands;

import cards.Card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    public static void main(String args[]) {

        TestNPairs("6Tr 6Pi 6Pi 5Ca", 3, 6);
        TestNPairs("6Tr 6Pi 6Pi 5Ca", 4, -1);
        TestNPairs("6Tr 6Pi 6Pi 6Ca", 4, 6);
        TestNPairs("6Tr 5Pi 6Pi 7Ca", 2, 6);

        TestBrelans("6Tr 6Pi 6Pi 5Ca", 6);
        TestBrelans("6Tr 6Pi 3Pi 5Ca", -1);
        //TestBrelans("6Tr 6Pi 6Ca 8Pi 8Tr 8Ca", 8);
    }

    static void TestNPairs(String test, int n, int valueResult) {
        HandBuilder builder = new HandBuilder();
        Hand h = builder.buildHandFromString(test);

        if(valueResult == -1) {
            assertEquals(0, h.getCardWhichHaveNLessOneOtherSameValuedCard(n).size());
        } else {
            assertEquals(valueResult, h.getCardWhichHaveNLessOneOtherSameValuedCard(n).get(0).getValue());
        }

        /*int pairs = h.getCardWhichHaveNLessOneOtherSameValuedCard(2).size();
        int triplets = h.getCardWhichHaveNLessOneOtherSameValuedCard(3).size();
        int quadruplets = h.getCardWhichHaveNLessOneOtherSameValuedCard(4).size();

        System.out.println(String.format("There is %d pairs, %d triplets, %d quadruplets", pairs, triplets, quadruplets));*/
    }

    static  void TestBrelans(String test, int value) {
        HandBuilder builder = new HandBuilder();
        Hand h = builder.buildHandFromString(test);

        if(value == -1) {
            assertEquals(null, h.getBrelan());
        } else {
            assertEquals(value, h.getBrelan().getValue());
        }
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
