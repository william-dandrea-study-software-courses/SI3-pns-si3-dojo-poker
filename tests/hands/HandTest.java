package hands;

import cards.Card;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Basic JUnit test on <i>Hand</i> class
 *
 * @author Gabriel Cogne
 * @author
 * @author
 * @author
 */

public class HandTest {

    /**
     * This method test the isEmpty(), add(obj), size() and get(index) method on <i>Hand</i> class
     */
    @Test
    public void testAddingGettingCard () {
        Hand hand1 = new Hand();

        Card card1 = new Card(2);
        Card card2 = new Card(13);

        assertTrue ("Test isEmpty hand == true", hand1.isEmpty());

        hand1.add(card1);
        hand1.add(card2);

        assertEquals("Test size of hand", 2, hand1.size());

        assertEquals("Test on getter for second card", card2, hand1.get(1));

        assertTrue ("Test isEmpty hand == false", !hand1.isEmpty());
    }
}
