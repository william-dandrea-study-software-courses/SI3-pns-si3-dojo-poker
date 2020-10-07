package interaction;

import cards.Card;

public class TwoCardVictory extends Victory{

    private Card cardWinner2;


    public TwoCardVictory(Victorieu victorieu, ResultType handWinType, Card cardWinner, Card cardWinner2) {
        super(victorieu, handWinType, cardWinner);
        this.cardWinner2 = cardWinner2;
    }

    public Card getCardWinner2() {
        return cardWinner2;
    }

    @Override
    public String toString() {
        String res = super.toString();
        res += " par les " + cardWinner2.getDisplayableValue();
        return res;
    }
}
