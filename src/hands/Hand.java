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
    // -- Variables --
    private int max_size = 5;

    // Use default constructors

    // -- Override method --
    /**
     * This method will first verify the size of the hand with the card to add. If
     * the size is greater than the authorized length, it throw an exception. It add
     * the card in the hand otherwise
     * @param card a new Card to add
     * @return true if the card was added
     * @throws ArrayIndexOutOfBoundsException if the max size is in trouble
     */
    @Override
    public boolean add(Card card) throws ArrayIndexOutOfBoundsException {
        if (this.size() + 1 > max_size)
            throw new ArrayIndexOutOfBoundsException("Too many cards in the hand");
        return super.add(card);
    }

    // -- Other methods --
    /**
     * Return the card with the highest value
     * @return a card
     */
    public Card getHighestCard () {
        Card highest = this.get(0);

        for (Card c : this) {
            if (c.compareTo(highest) > 1) {
                highest = c;
            }
        }

        return highest;
    }
    @override
    public String toString (){
        StringBuilder f= new StringBuilder ();
        for (Card c : this) {
            f.append(c.toString() + " ");
        }
        return f.toString();
    }
}
