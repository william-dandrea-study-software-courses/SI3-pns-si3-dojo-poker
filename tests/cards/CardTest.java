package cards;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit class test for <i>Card</i> class.
 *
 * @author Gabriel Cogne
 *
 * @version 0.1
 */
public class CardTest {
    private Card card1;
    private Card card2;
    private Card card3;

    /**
     * Test on the getters of the field value + the constructor of a card.
     */
    @Test
    public void testValueGetter () {
        Value value1 = Value.two;
        Value value2 = Value.ace;
        Value value3 = Value.ten;

        card1 = new Card(value1, Color.Co);

        card2 = new Card(value2);

        card3 = new Card(value3);

        assertEquals (value1, card1.getValue(), "Test getter on card 1");
        assertEquals (value2, card2.getValue(), "Test getter on card 2");
        assertEquals (value3, card3.getValue(), "Test getter on card 3");

        assertEquals ("two", card1.getDisplayableValue(), "Test display getter on card 1");
        assertEquals ("ace", card2.getDisplayableValue(), "Test display getter on card 2");
        assertEquals ("ten", card3.getDisplayableValue(), "Test display getter on card 3");
    }

    /**
     * Test on the compareTo method of the <i>Card</i> class.
     */
    @Test
    public void testCompareTo () {
        card1 = new Card(Value.ten);
        card2 = new Card(Value.king);
        card3 = new Card(Value.six);
        Card card4 = new Card(Value.ten);

        assertTrue(card1.compareTo(card2) < 0, "Test compare to with greater card");
        assertTrue(card1.compareTo(card3) > 0, "Test compare to with a less card");
        assertEquals(0, card1.compareTo(card4), "Test compare to with an equals card");

        try {
            card1.compareTo(null);
            fail ("Can't compare with null pointer");
        } catch (NullPointerException e) {
            assertEquals("compare can be done with a null object", e.getMessage(),
                    "Test exception compareTo");
        }
    }

    @Test
    public void testColorGetter () {
        card1 = new Card (Value.king, Color.Ca);
        card2 = new Card (Value.king, Color.valueOf("Pi"));

        assertEquals(Color.Ca, card1.getColor(), "Test color 1");
        assertEquals(Color.Pi, card2.getColor(), "Test color 2");

        assertNotEquals(card2.getColor(), card1.getColor());
    }
}
