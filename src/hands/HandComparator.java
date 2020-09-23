package hands;

import cards.Card;

/**
 * This class is used to compare several hands. She will determine which is the better and why.
 *
 * @author
 * @author Gabriel COGNE
 * @author
 * @author 
 */

public class HandComparator {

    private String explication;

    public Hand compare (Hand h1, Hand h2) {
        Card highest1 = h1.getHighestCard();
        Card highest2 = h2.getHighestCard();

        if (highest1.compareTo(highest2) > 0) {
            explication = " carte la plus élevée : " + highest1.toString();
            return h1;
        } else if (highest1.compareTo(highest2) < 0) {
            explication = " carte la plus élevée : " + highest2.toString();
            return h2;
        }
        explication = "";
        return null;
    }

    /**
     * Return the explication of the method compare
     * @return a string message
     */
    public String getExplication () {
        return explication;
    }

}
