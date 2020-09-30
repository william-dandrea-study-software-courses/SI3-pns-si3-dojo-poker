package hands;

import cards.Card;
import cards.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allow to create a hand from a string content. Using it require to follow some
 * rules about the string content. The rules are :<br>
 *     - A hand is only created with several cards that are splinted by a space<br>
 *     - A card is created by a value and a color. those two elements are not splinted<br>
 *     - A value can be "2", "3", "4", "5", "6", "7", "8", "9", "10", "V", "D", "R" or "A"<br>
 *     - A color can be "Ca", "Co", "Pi", "Tr". Attention the system is case-sensitive<br>
 */

public class HandBuilder {
    // -- Static fields --
    private final static String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "V", "D", "R", "A"};

    // -- fields --
    private List<Card> knownCards;

    // -- Constructor --
    public HandBuilder () {
        knownCards = new ArrayList<>();
    }

    // -- Build methods --
    /**
     * This method build a hand with cards. The hand is describe
     *
     * @param hand A String that contains several cards with some rules as describe in the {@link HandBuilder class
     * documentation}
     * @return a hand that will contain as many cards as in the string content and as the system
     *         allow in a hand
     */
    public Hand buildHandFromString(String hand) {
        Hand res = new Hand();

        for (String card : hand.split(" ")) {
            if (card.isBlank())
                continue;
            res.add(buildCardFromString(card));
        }

        return res;
    }

    /**
     * Create a card from a string
     * @param card a string that describe the value and the color as {value}{color} without space between them
     * @return the created card
     * @throws IllegalArgumentException - One of the arguments is bad written
     */
    private Card buildCardFromString(String card) throws IllegalArgumentException {
        final int COLOR_LENGTH = 2;

        int value = getCardValueFromString(card.substring(0, card.length() - COLOR_LENGTH));
        if (value == -1) {
            throw new IllegalArgumentException("The card value must be in {2, 3, 4, 5, 6, 7, 8, 9, V, D, R, A}");
        }

        Color color = Color.valueOf(card.substring(card.length() - COLOR_LENGTH));

        Card res = new Card(value, color);

        if (knownCards.contains(res)) {
            // This mean that a card with same value and color has been already created
            throw new RuntimeException("Card already existing");
        }

        knownCards.add(res);

        return res;
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
