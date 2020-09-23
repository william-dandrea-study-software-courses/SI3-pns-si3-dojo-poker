package hands;

/**
 * This class is used to compare several hands. She will determine which is the better and why.
 *
 * @author
 * @author
 * @author
 * @author 
 */

public class HandComparator {

    public Hand compare (Hand h1, Hand h2) {
        Card highest1 = h1.getHighestCard();
        Card highest2 = h2.getHighestCard();

        if (highest1.compareTo(highest2) > 0) {
            return h1;
        } else if (highest1.compareTo(highest2) < 0) {
            return h2;
        }
        return null;
    }

}
