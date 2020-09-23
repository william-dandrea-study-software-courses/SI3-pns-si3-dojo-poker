package hands;

import cards.Card;
import cards.Color;

public class HandBuilder {
    // -- Static fields --
    private final static String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "V", "D", "R", "A"};

    // -- Build methods --
    /**
     * This method build a hand with cards. The hand is describe
     * @param hand
     * @return
     */
    public Hand buildHandFromString(String hand) {
        Hand res = new Hand();

        for (String card : hand.split(" ")) {
            res.add(buildCardFromString(card));
        }

        return res;
    }

    private Card buildCardFromString(String card) {
        final int COLOR_LENGTH = 2;

        int value = getCardValueFromString(card.substring(0, card.length() - COLOR_LENGTH));
        if (value == -1) {
            throw new IllegalArgumentException("The card value must be in {2, 3, 4, 5, 6, 7, 8, 9, V, D, R, A}");
        }

        Color color = Color.valueOf(card.substring(card.length() - COLOR_LENGTH));

        return new Card(value, color);
    }

    /**
     * Return the value of the card as a int using a string value
     * @param value a string that must respect the grammar
     * @return the value that is between 2 and 14, otherwise it return -1 as error
     */
    private int getCardValueFromString(String value) {
        for (int i = 0; i < names.length; i++) {
            if (value.equals(names[i])) {
                return i + 2; // + 2 because the value start with 2 and this As have value 14
            }
        }
        return -1;
    }
}
