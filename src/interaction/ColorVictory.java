package interaction;

import cards.Color;
import cards.Value;

import java.util.ResourceBundle;

public class ColorVictory extends Victory {
    private final Color winColor;

    public ColorVictory(Victorieu victorieu, ResultType handWinType, Value valueWinner, Color winColor) {
        super(victorieu, handWinType, valueWinner);
        this.winColor = winColor;
    }

    public Color getWinColor() {
        return winColor;
    }

    @Override
    public String toString() {
        if (getWinner().equals(Victorieu.egalite)) {
            return "Equals";
        } else if (getWinner() == Victorieu.main1){
            return "La main 1 gagne par " + types.get(getWinType()) + ":" + getWinColor();
        } else {
            return "La main 2 gagne par " + types.get(getWinType()) + ":" + getWinColor();
        }
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
        res += r.getString("color_link");
        res += " ";
        res += r.getString(getWinColor().name());

        return res;
    }
}