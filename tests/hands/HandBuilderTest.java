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
    public void testBuildSuccess () throws Exception {
        Hand hand = builder.buildHandFromString("7Tr 6Pi 5Pi 5Tr 5Ca");

        assertFalse(hand.isEmpty(), "Test the hand isn't empty");
        assertEquals(Value.seven, hand.get(0).getValue(), "Test card value 1");
        assertEquals(Color.Tr, hand.get(0).getColor(), "Test card color 1");
        assertEquals(Value.six, hand.get(1).getValue(), "Test card value 2");
        assertEquals(Color.Pi, hand.get(1).getColor(), "Test card color 2");
    }

    @Test
    public void testMistakeInCardGrammar () throws Exception {
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
    public void testCardDuplication () throws Exception {
        builder.buildHandFromString("7Tr 3Pi 3Tr 3Ca 3Co");
        try {
            builder.buildHandFromString("7Tr");
            fail("A duplication exception should be raised");
        } catch (RuntimeException e) {
            assertEquals("Card already existing", e.getMessage(),
                    "Test interdiction de duplication ");
        }
    }
}
