package hands;

import cards.Card;
import interaction.ResultType;
import interaction.Victorieu;
import interaction.Victory;

/**
 * This class is used to compare several hands. She will determine which is the better and why.
 *
 * @author
 * @author Gabriel Cogne
 * @author
 * @author 
 */

public class HandComparator {

    private String explication;

    public Victory compare (Hand h1, Hand h2) {
        Card highest1 = h1.getHighestCard();
        Card highest2 = h2.getHighestCard();

        if (highest1.compareTo(highest2) > 0) {
            explication = " carte la plus élevée : " + highest1.toString();
            return new Victory(Victorieu.main1, ResultType.higherCard, ResultType.higherCard, highest1, highest2);
        } else if (highest1.compareTo(highest2) < 0) {
            explication = " carte la plus élevée : " + highest2.toString();
            return new Victory(Victorieu.main2, ResultType.higherCard, ResultType.higherCard, highest1, highest2);
        }
        explication = "";
        return new Victory(Victorieu.egalite, ResultType.higherCard, ResultType.higherCard, highest1, highest2);
    }

    /**
     * Return the explication of the method compare
     * @return a string message
     */
    public String getExplication () {
        return explication;
    }

}
