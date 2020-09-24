package hands;

import cards.Card;
import cards.Color;

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
            if (c.compareTo(highest) > 0) {
                highest = c;
            }
        }

        return highest;
    }

    public Card getPairCards () {

        Card cardMax = null;

        for (Card c : this) { // TODO guys I think there is a better way to this. maybe try "for (int i = 0; i < this.size(); i++) for (int j = i + 1; j < this.size(); j++);
            for (Card c2 : this) {
                if (c != c2 && c.getValue() == c2.getValue()) {
                    if (cardMax == null || cardMax.getValue() < c.getValue()) {
                        cardMax = c;
                    }
                }
            }
        }
        return cardMax;
    }

    /**
     * This method will check if all cards have the same. If that true then it will return the highest card
     * in the hand, otherwise it will return null.
     * @return The highest card because a color state value is the greatest value in the hand
     */
    public Card isColor () {
        if (isEmpty())
            return null;

        Color firstColor = this.get(0).getColor();

        for (Card c : this) {
            if (!c.getColor().equals(firstColor)) {
                return null;
            }
        }


        return getHighestCard();
    }

    /**
     * This method will sort the hand from the less to higher value. And will then check that there is only
     * 1 as difference between the values of 2 consecutive cards.
     * @return the highest card of the list if it's a suite, null otherwise
     */
    public Card isSuite () {
        if (isEmpty()) {
            return null;
        }

        // This will sort the list from the less to higher value
        this.sort(Card::compareTo);

        System.out.println(this);

        int previousValue = this.get(0).getValue();

        for (int i = 1; i < this.size(); i++) {
            if (Math.abs(previousValue - this.get(i).getValue()) != 1) {
                return null;
            }
            previousValue = this.get(i).getValue();
        }

        return this.get(this.size() - 1);
    }

    public Card isQuinteFlush () {
        Card suite = isSuite();
        Card color = isColor();

        if (suite == null || color == null) {
            return null;
        }
        return suite;
    }

    @Override
    public String toString (){
        StringBuilder f= new StringBuilder ();
        for (Card c : this) {
            f.append(c.toString() + " ");
        }
        return f.toString();
    }
}
