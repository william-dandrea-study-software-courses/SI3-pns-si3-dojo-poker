package cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit class test for <i>Card</i> class.
 *
 * @author
 * @author
 * @author
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
        int value1 = 2;
        int value2 = 14;
        int value3 = 10;

        card1 = new Card(value1);

        card2 = new Card(value2);

        card3 = new Card(value3);

        assertEquals (value1, card1.getValue(), "Test getter on card 1");
        assertEquals (value2, card2.getValue(), "Test getter on card 2");
        assertEquals (value3, card3.getValue(), "Test getter on card 3");

        assertEquals ("2", card1.getDisplayableValue(), "Test display getter on card 1");
        assertEquals ("A", card2.getDisplayableValue(), "Test display getter on card 2");
        assertEquals ("10", card3.getDisplayableValue(), "Test display getter on card 3");
    }

    /**
     * Test on the compareTo method of the <i>Card</i> class.
     */
    @Test
    public void testCompareTo () {
        card1 = new Card(10);
        card2 = new Card(13);
        card3 = new Card(6);
        Card card4 = new Card(10);

        assertTrue(card1.compareTo(card2) < 0, "Test compare to with greater card");
        assertTrue(card1.compareTo(card3) > 0, "Test compare to with a less card");
        assertEquals(0, card1.compareTo(card4), "Test compare to with an equals card");
    }
}
