package hands;

import cards.Card;
import interaction.ResultType;
import interaction.Victorieu;
import interaction.Victory;

import java.util.List;

/**
 * This class is used to compare several hands. She will determine which is the better and why.
 *
 * @author Gabriel Cogne
 */

public class HandComparator {

    /**
     * This will determine who win following the known poker rules
     * @param h1 the first hand in
     * @param h2 the second hand in
     * @return a {@link Victory victory object} that describe who win and why
     */
    public Victory compare (Hand h1, Hand h2) throws Exception {
        if ((h1 == null) || (h2 == null))
            throw new NullPointerException("All hands must be initialized !!");

        Card valueHand1;
        Card valueHand2 = null;

        // Poker rules
        if (((valueHand1 = h1.isSquare()) != null) || ((valueHand2 = h2.isSquare()) != null))
            return refereeOnQuad(h1, h2, valueHand1, valueHand2);
        else if (((valueHand1 = h1.isStraight()) != null) || ((valueHand2 = h2.isStraight()) != null))
            return refereeOnStraight(h2, valueHand1, valueHand2);
        else if (((valueHand1 = h1.getBrelan()) != null) || ((valueHand2 = h2.getBrelan()) != null))
            return refereeOnTrip(h1, h2, valueHand1, valueHand2);
        else if ((isTwoPair(h1.getDoublePairCards())) || (isTwoPair(h2.getDoublePairCards())))
            return refereeOnTwoPairs(h1, h2);
        else if (((valueHand1 = h1.getPairCards()) != null) || ((valueHand2 = h2.getPairCards()) != null))
            return refereeOnPair(h1, h2, valueHand1, valueHand2);
        else
            return compareOnHighestCard(h1, h2);
    }

    /**
     * This will return the winner hand comparing them by the highest value
     * @param hand1 the first hand in
     * @param hand2 the second hand in
     * @return a {@link Victory victory object} that describe who win and why
     */
    private Victory compareOnHighestCard (Hand hand1, Hand hand2) {
        if (hand1.isEmpty() || hand2.isEmpty())
            return new Victory(Victorieu.egalite, ResultType.higherCard, ResultType.higherCard,
                    null, null);

        Card highest1 = hand1.getHighestCard();
        Card highest2 = hand2.getHighestCard();

        if (highest1.compareTo(highest2) > 0) {
            return new Victory(Victorieu.main1, ResultType.higherCard, ResultType.higherCard, highest1, highest2);
        } else if (highest1.compareTo(highest2) < 0) {
            return new Victory(Victorieu.main2, ResultType.higherCard, ResultType.higherCard, highest2, highest1);
        }
        hand1.remove(highest1);
        hand2.remove(highest2);
        if (hand1.isEmpty() || hand2.isEmpty())
            return new Victory(Victorieu.egalite, ResultType.higherCard, ResultType.higherCard, highest1, highest2);
        else
            return compareOnHighestCard(hand1, hand2);
    }

    /**
     * This will return the winner hand comparing them by pair value
     * @param hand1 the first hand in
     * @param hand2 the second hand in
     * @return a {@link Victory victory object} that describe who win and why
     */
    private Victory refereeOnPair(Hand hand1, Hand hand2, Card valueHand1, Card valueHand2) {
        if (valueHand2 == null)
            valueHand2 = hand2.getPairCards();

        if ((valueHand1 != null) && (valueHand2 != null)) {
            Victorieu winner = compareCards(valueHand1, valueHand2);

            if (winner.equals(Victorieu.egalite)) {
                hand1.removeCardsOfValue(valueHand1);
                hand2.removeCardsOfValue(valueHand1);
                return compareOnHighestCard(hand1, hand2);
            }
            return new Victory(winner, ResultType.pair, ResultType.pair,
                    (winner.equals(Victorieu.main1) ? valueHand1 : valueHand2),
                    (winner.equals(Victorieu.main1) ? valueHand2 : valueHand1));
        } else if (valueHand1 != null)
            return new Victory(Victorieu.main1, ResultType.pair, ResultType.higherCard,
                    valueHand1, hand2.getHighestCard());
        else
            return new Victory(Victorieu.main2, ResultType.pair, ResultType.higherCard,
                    valueHand2, hand1.getHighestCard());
    }

    private Victory refereeOnTrip (Hand hand1, Hand hand2, Card valueHand1, Card valueHand2) throws Exception {
        if (valueHand2 == null)
            valueHand2 = hand2.getBrelan();

        if ((valueHand1 != null) && (valueHand2 != null)) {
            Victorieu winner = compareCards(valueHand1, valueHand2);

            if (winner.equals(Victorieu.egalite)) {
                throw new Exception("two same value trips in a four colors games is impossible");
            }
            return new Victory(winner, ResultType.brelan, ResultType.brelan,
                    (winner.equals(Victorieu.main1) ? valueHand1 : valueHand2),
                    (winner.equals(Victorieu.main1) ? valueHand2 : valueHand1));
        } else if (valueHand1 != null)
            return new Victory(Victorieu.main1, ResultType.brelan, null,
                    valueHand1, hand2.getHighestCard());
        else
            return new Victory(Victorieu.main2, ResultType.brelan, null,
                    valueHand2, hand1.getHighestCard());
    }

    private Victory refereeOnQuad (Hand hand1, Hand hand2, Card valueHand1, Card valueHand2) throws Exception {
        if (valueHand2 == null)
            valueHand2 = hand2.isSquare();

        if ((valueHand1 != null) && (valueHand2 != null)) {
            Victorieu winner = compareCards(valueHand1, valueHand2);

            if (winner.equals(Victorieu.egalite)) {
                throw new Exception("two same value quads in a four colors games is impossible");
            }
            return new Victory(winner, ResultType.carre, ResultType.carre,
                    (winner.equals(Victorieu.main1) ? valueHand1 : valueHand2),
                    (winner.equals(Victorieu.main1) ? valueHand2 : valueHand1));
        } else if (valueHand1 != null)
            return new Victory(Victorieu.main1, ResultType.carre, null,
                    valueHand1, hand2.getHighestCard());
        else
            return new Victory(Victorieu.main2, ResultType.carre, null,
                    valueHand2, hand1.getHighestCard());
    }

    private Victory refereeOnTwoPairs (Hand hand1, Hand hand2) {
        List<Card> valueHand1 = hand1.getDoublePairCards();
        for (Card c : valueHand1)
            if (c == null)
                valueHand1.remove(null);
        valueHand1.sort(Card::compareTo);
        List<Card> valueHand2 = hand2.getDoublePairCards();
        for (Card c : valueHand2)
            if (c == null)
                valueHand2.remove(null);
        valueHand2.sort(Card::compareTo);

        if ((valueHand1.size() == 2) && (valueHand2.size() == 2)) {
            Victorieu winner = compareCards(valueHand1.get(0), valueHand2.get(0));

            if (winner.equals(Victorieu.egalite)) {
                winner = compareCards(valueHand1.get(1), valueHand2.get(1));
                if (winner.equals(Victorieu.egalite)) {
                    hand1.removeCardsOfValue(valueHand1.get(0));
                    hand1.removeCardsOfValue(valueHand1.get(1));
                    hand2.removeCardsOfValue(valueHand2.get(0));
                    hand2.removeCardsOfValue(valueHand2.get(1));
                    return compareOnHighestCard(hand1, hand2);
                }
            }
            return new Victory(winner, ResultType.doublePair, ResultType.doublePair,
                    (winner.equals(Victorieu.main1) ? valueHand1.get(0) : valueHand2.get(0)),
                    (winner.equals(Victorieu.main1) ? valueHand2.get(0) : valueHand1.get(0)));
        } else if (valueHand1.size() == 2)
            return new Victory(Victorieu.main1, ResultType.doublePair, null,
                    valueHand1.get(0), null);
        else
            return new Victory(Victorieu.main2, ResultType.doublePair, null,
                    valueHand2.get(0), null);
    }

    private Victory refereeOnStraight (Hand hand2, Card valueHand1, Card valueHand2) throws Exception {
        if (valueHand2 == null)
            valueHand2 = hand2.isStraight();

        if ((valueHand1 != null) && (valueHand2 != null)) {
            Victorieu winner = compareCards(valueHand1, valueHand2);
            return new Victory(winner, ResultType.suite, ResultType.suite,
                    (winner.equals(Victorieu.main1) ? valueHand1 : valueHand2),
                    (winner.equals(Victorieu.main1) ? valueHand2 : valueHand1));
        } else if (valueHand1 != null)
            return new Victory(Victorieu.main1, ResultType.suite, null, valueHand1, null);
        else
            return new Victory(Victorieu.main2, ResultType.suite, null, valueHand2, null);
    }

    private Victorieu compareCards (Card valueHand1, Card valueHand2) {
        if (valueHand1.compareTo(valueHand2) > 0)
            return Victorieu.main1;
        else if (valueHand1.compareTo(valueHand2) < 0)
            return Victorieu.main2;
        else {
            return Victorieu.egalite;
        }
    }

    private boolean isTwoPair (List<Card> values) {
        for (Card c : values)
            if (c == null)
                return false;
        return true;
    }
}
