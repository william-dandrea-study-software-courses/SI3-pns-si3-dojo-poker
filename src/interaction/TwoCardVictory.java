package interaction;

import cards.Value;

import java.util.ResourceBundle;

public class TwoCardVictory extends Victory{

    private final Value valueWinner2;


    public TwoCardVictory(Victorieu victorieu, ResultType handWinType, Value valueWinner, Value cardWinner2) {
        super(victorieu, handWinType, valueWinner);
        this.valueWinner2 = cardWinner2;
    }

    public Value getValueWinner2() {
        return valueWinner2;
    }

    @Override
    public String toString() {
        String res = super.toString();
        res += " par les " + valueWinner2;
        return res;
    }

    @Override
    public String describe(ResourceBundle r) {
        if (getWinner().equals(Victorieu.egalite))
            return r.getString("draw");

        String res = r.getString(getWinner().name()) + " ";

        res += r.getString(getWinType().name());
        res += " ";
        res += r.getString(getValueWinner().name());
        res += " ";
        res += r.getString("value_link");
        res += " ";
        res += r.getString(getValueWinner2().name());

        return res;
    }
}
