package hands;

import cards.Color;
import cards.Value;
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
    public void testBuildSuccess () {
        Hand hand = builder.buildHandFromString("7Tr 6Pi");

        assertFalse(hand.isEmpty(), "Test the hand isn't empty");
        assertEquals(Value.seven, hand.get(0).getValue(), "Test card value 1");
        assertEquals(Color.Tr, hand.get(0).getColor(), "Test card color 1");
        assertEquals(Value.six, hand.get(1).getValue(), "Test card value 2");
        assertEquals(Color.Pi, hand.get(1).getColor(), "Test card color 2");
    }

    @Test
    public void testMistakeInCardGrammar () {
        try {
            builder.buildHandFromString("1Tr");
            fail ("An As is built with A not 1 so there should be an exception raised");
        } catch (IllegalArgumentException e) {
            assertEquals("The card value must be in {2, 3, 4, 5, 6, 7, 8, 9, V, D, R, A}",
                    e.getMessage(), "Test on exception raise");
        }

        try {
            builder.buildHandFromString("API");
            fail ("An Pique is built with Pi not PI so there should be an exception raised");
        } catch (IllegalArgumentException e) {
            assertNotNull(e, "Exception de couleur");
        }
    }

    @Test()
    public void testCardDuplication () {
        builder.buildHandFromString("7Tr");
        try {
            builder.buildHandFromString("7Tr");
            fail("A duplication exception should be raised");
        } catch (RuntimeException e) {
            assertEquals("Card already existing", e.getMessage(),
                    "Test interdiction de duplication ");
        }
    }
}
