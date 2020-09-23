package hands;

import cards.Card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        assertTrue (hand1.isEmpty(), "Test isEmpty hand == true");

        hand1.add(card1);
        hand1.add(card2);

        assertEquals(2, hand1.size(), "Test size of hand");

        assertEquals(card2, hand1.get(1), "Test on getter for second card");

        assertTrue (!hand1.isEmpty(), "Test isEmpty hand == false");
    }
}
