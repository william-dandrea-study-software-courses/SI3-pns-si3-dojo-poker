package interaction;

import cards.Color;

public class ColorVictory extends Victory {
    private final Color winColor;

    public ColorVictory(Victorieu victorieu, ResultType handWinType, int valueWinner, Color winColor) {
        super(victorieu, handWinType, valueWinner);
        this.winColor = winColor;
    }

    public Color getWinColor() {
        return winColor;
    }

    @Override
    public String toString() {
        if (getWinner() == Victorieu.egalite){
            return "Egalite ";
        } else if (getWinner() == Victorieu.main1){
            return "La main 1 gagne par " + types.get(getWinType()) + ":" + getWinColor();
        } else {
            return "La main 2 gagne par " + types.get(getWinType()) + ":" + getWinColor();
        }
    }
}
