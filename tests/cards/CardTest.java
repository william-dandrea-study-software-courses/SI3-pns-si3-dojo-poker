package cards;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        assertEquals ("Test getter on card 1", value1, card1.getValue());
        assertEquals ("Test getter on card 2", value2, card2.getValue());
        assertEquals ("Test getter on card 3", value3, card3.getValue());

        assertEquals ("Test display getter on card 1", "2", card1.getDisplayableValue());
        assertEquals ("Test display getter on card 2", "A", card2.getDisplayableValue());
        assertEquals ("Test display getter on card 3", "10", card3.getDisplayableValue());
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

        assertTrue("Test compare to with greater card", card1.compareTo(card2) < 0);
        assertTrue("Test compare to with a less card",card1.compareTo(card3) > 0);
        assertEquals("Test compare to with an equals card", 0, card1.compareTo(card4));
    }
}
