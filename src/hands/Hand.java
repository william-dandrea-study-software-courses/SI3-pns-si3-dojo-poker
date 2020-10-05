package hands;

import cards.Card;
import cards.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that represent a card game hand.
 *
 * @author Gabriel Cogne
 * @author D'Andrea William
 * @author Clodong Yann
 * @author Amine CHOUHABI
 */
public class Hand extends ArrayList<Card> {
    // -- Attributes --
    private static final int MAX_SIZE = 5;

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
        if (this.size() + 1 > MAX_SIZE)
            throw new ArrayIndexOutOfBoundsException("Too many cards in the hand");
        return super.add(card);
    }

    // -- Other methods --
    /**
     * Return the card with the highest value
     * @return a card
     */
    public Card getHighestCard () {
        if (isEmpty())
            return null;

        Card highest = this.get(0);

        for (Card c : this) {
            if (c.compareTo(highest) > 0) {
                highest = c;
            }
        }

        return highest;
    }


    /**
     * This method will check if there is a pair in the Hand, if yes, it will return t
     * @return the higher pair card in the hand, null if there is no pair in the hand
     */
    public Card getPairCards () {
        Card cardMax = null;
        // TODO guys I think there is a better way to this. maybe try "for (int i = 0; i < this.size(); i++) for (int j = i + 1; j < this.size(); j++);
        for (int i = 0; i <this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {

                if (i !=j && this.get(i).getValue() == this.get(j).getValue()) {
                    if (cardMax == null || this.get(i).getValue() > cardMax.getValue()) {
                        cardMax = this.get(i);
                    }
                }

            }
        }


        return cardMax;
    }

    /**
     * This method will find if it have 2 pairs in a hand
     * @return an array of the values of the 2 hands if we have 2 pair, and one array with 1 pair and 1 null
     * if we have a brelan or more, null also
     */
    public ArrayList<Card> getDoublePairCards() {

        ArrayList<Card> doublePair = new ArrayList<>();

        Card pairCard1 = null;
        Card pairCard2 = null;


        for (int  i = 0; i < this.size() ; i++) {
            for (int j = 0; j < this.size(); j++) {

                if (i != j && this.get(i).getValue() == this.get(j).getValue()) {

                    if (pairCard1 == null || this.get(i).getValue() != pairCard2.getValue()) {
                        pairCard1= this.get(i);
                    }
                    if (pairCard2 == null || this.get(i).getValue() != pairCard1.getValue()) {
                        pairCard2= this.get(i);
                    }
                }

            }
        }


        //On gère l'exception du brelan, car si brelan, alors pairCard1 = pairCard2
        if (pairCard1 != null && pairCard2 != null && pairCard1.getValue() == pairCard2.getValue()) {
            pairCard2 = null;
        }

        if (pairCard1 != null)
            doublePair.add(pairCard1);
        if (pairCard2 != null)
            doublePair.add(pairCard2);

        doublePair.sort(Card::compareTo);


        return doublePair;
    }

    /**
     * This method will check if there is a brolens in the Hand, if yes, it will return t
     * @return the higher pair card in the hand, null if there is no brolens in the hand
     */
    public Card getBrelan() {
        ArrayList<Card> brelans = getCardWhichHaveNLessOneOtherSameValuedCard(3);

        if(brelans.size() != 0) {
            int max = 0;
            for(int i = 1; i < brelans.size(); i++) {
                if(brelans.get(i).compareTo(brelans.get(max)) > 0) {     //brelans.get(i).getValue() > brelans.get(max).getValue()) {
                    max = i;
                }
            }

            return brelans.get(max);
        }
        return null;
    }

    /**
     * This method return the cards which have the n cousin (Same value) in the hand.
     *
     * I had an {@link java.lang.ArrayIndexOutOfBoundsException ArrayIndexOutOfException} when calling with getBrelan
     * (Index 14 out of bounds for length 13)
     *
     * @return an Array of card (can be empty)
     */
    ArrayList<Card> getCardWhichHaveNLessOneOtherSameValuedCard(int n) {
        // J'avais pas d'idée pour le nom :)

        Hand hand = new Hand();
        hand.addAll(this);

        Card[] instanceOfCardByValues = new Card[13];
        int[] powerOfValues = new int[13];


        for(int i = 0; i < hand.size(); i++) {
            int value = hand.get(i).getValue() - 2;
            powerOfValues[value]++;
            if(instanceOfCardByValues[value] == null) {
                instanceOfCardByValues[value] = hand.get(i);
            }
        }

        ArrayList<Card> results = new ArrayList<>();
        for(int i = 0; i < 13; i++) {
            if(powerOfValues[i] == n) {
                results.add(instanceOfCardByValues[i]);
            }
        }

        return results;
    }

    /**
     * This method will check if all cards have the same. If that true then it will return the highest card
     * in the hand, otherwise it will return null.
     * @return The highest card because a color state value is the greatest value in the hand
     */
    public Card isFlush() {
        if (isEmpty() || size() < MAX_SIZE)
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
    public Card isStraight() {
        if (isEmpty() || size() < MAX_SIZE) {
            return null;
        }

        // This will sort the list from the less to higher value
        this.sort(Card::compareTo);

        int previousValue = this.get(0).getValue();

        for (int i = 1; i < this.size(); i++) {
            if (Math.abs(previousValue - this.get(i).getValue()) != 1) {
                return null;
            }
            previousValue = this.get(i).getValue();
        }

        return this.get(this.size() - 1);
    }

    /**
     * This method will if the hand is a quite flush. That mean, it will test color and suite
     * on this hand. If one of the two previously named methods return null then this method will
     * return null, otherwise, this method return the highest card in the hand.
     * @return null if the hand is not straight flush, the highest card otherwise
     */
    public Card isStraightFlush() {
        Card suite = isStraight();
        Card color = isFlush();

        if (suite == null || color == null) {
            return null;
        }
        return suite;
    }

    @Override
    public String toString (){
        StringBuilder f= new StringBuilder ();
        for (Card c : this) {
            f.append(c.toString());
            f.append(" ");
        }
        return f.toString();
    }

    private Map<Integer,Integer> getNbOccurOfValues () {
        Map<Integer, Integer> res = new HashMap<>();

        for (Card c : this) {
            if (res.containsKey(c.getValue())) {
                res.put(c.getValue(), res.get(c.getValue()) + 1);
            } else {
                res.put(c.getValue(), 1);
            }
        }

        return res;
    }

    /**
     * This method will if the hand is a quite square.
     */
    public Card isSquare () {
        ArrayList<Card> square = getCardWhichHaveNLessOneOtherSameValuedCard(4);
        if(square.isEmpty()) {
            return null;
        }
        return square.get(0);
    }

    public void removeCardsOfValue (Card value) {
        List<Card> toRemove = new ArrayList<>();

        for (Card c : this)
            if (c.getValue() == value.getValue())
                toRemove.add(c);

        this.removeAll(toRemove);
    }
}