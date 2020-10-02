package hands;

import cards.Card;
import interaction.ResultType;
import interaction.Victorieu;
import interaction.Victory;

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
     * @return a {@link Victory victory object} that describe who won and why
     */
    public Victory compare (Hand h1, Hand h2) {
        if ((h1.getPairCards() != null) || (h2.getPairCards() != null))
            return compareOnPair(h1, h2);
        return compareOnHighestCard(h1, h2);
    }

    /**
     * This will return the winner hand comparing them by the highest value
     * @param hand1 the first hand in
     * @param hand2 the second hand in
     * @return a {@link Victory victory object} that describe who won and why
     */
    private Victory compareOnHighestCard (Hand hand1, Hand hand2) {
        Card highest1 = hand1.getHighestCard();
        Card highest2 = hand2.getHighestCard();

        if (highest1.compareTo(highest2) > 0) {
            return new Victory(Victorieu.main1, ResultType.higherCard, ResultType.higherCard, highest1, highest2);
        } else if (highest1.compareTo(highest2) < 0) {
            return new Victory(Victorieu.main2, ResultType.higherCard, ResultType.higherCard, highest2, highest1);
        }
        return new Victory(Victorieu.egalite, ResultType.higherCard, ResultType.higherCard, highest1, highest2);
    }

    private Victory compareOnPair (Hand hand1, Hand hand2) {
        Card pairValueHand1 = hand1.getPairCards();
        Card pairValueHand2 = hand2.getPairCards();

        if ((pairValueHand1 != null) && (pairValueHand2 != null))
            return compareCards(pairValueHand1, pairValueHand2, ResultType.pair);
        else if (pairValueHand1 != null)
            return new Victory(Victorieu.main1, ResultType.pair, ResultType.higherCard,
                    pairValueHand1, hand2.getHighestCard());
        else
            return new Victory(Victorieu.main2, ResultType.pair, ResultType.higherCard,
                    pairValueHand2, hand1.getHighestCard());
    }

    private Victory compareCards (Card valueHand1, Card valueHand2, ResultType resultType) {
        if (valueHand1.compareTo(valueHand2) > 0)
            return new Victory(Victorieu.main1, resultType, resultType, valueHand1, valueHand2);
        else if (valueHand1.compareTo(valueHand2) < 0)
            return new Victory(Victorieu.main2, resultType, resultType, valueHand1, valueHand2);
        else
            return new Victory(Victorieu.egalite, resultType, resultType, valueHand1, valueHand2);
    }
}
