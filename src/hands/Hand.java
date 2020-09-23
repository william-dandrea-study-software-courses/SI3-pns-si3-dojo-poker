package hands;

import cards.Card;

import java.util.ArrayList;

/**
 * A class that represent a card game hand.
 *
 * @author Gabriel Cogne
 * @author
 * @author
 * @author
 */
public class Hand extends ArrayList<Card> {
    private int max_size = 5;

    /**
     *
     * @param card
     * @return
     * @throws ArrayIndexOutOfBoundsException
     */
    @Override
    public boolean add(Card card) throws ArrayIndexOutOfBoundsException {
        if (this.size() + 1 > max_size)
            throw new ArrayIndexOutOfBoundsException("Too many cards in the hand");
        return super.add(card);
    }
}
