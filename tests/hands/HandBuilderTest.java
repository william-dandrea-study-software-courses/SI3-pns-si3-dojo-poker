package hands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandBuilderTest {
    HandBuilder builder;

    @BeforeEach
    void init () {
        builder = new HandBuilder();
    }

    @Test
    public void testBuild () {
        Hand hand = builder.buildHandFromString("7 6");

        assertFalse(hand.isEmpty(), "Test the hand isn't empty");
        assertEquals(7, hand.get(0).getValue(), "Test card value 1");
        assertEquals(6, hand.get(1).getValue(), "Test card value 2");

        try {
            hand = builder.buildHandFromString("1");
            fail ("An As is built with A not 1 so there should be an exception raised");
        } catch (IllegalArgumentException e) {
            assertEquals("The card value must be in {2, 3, 4, 5, 6, 7, 8, 9, V, D, R, A}",
                    e.getMessage(), "Test on exception raise");
        }
    }
}
