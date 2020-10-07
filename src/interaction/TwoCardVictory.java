package interaction;

public class TwoCardVictory extends Victory{

    private int valueWinner2;


    public TwoCardVictory(Victorieu victorieu, ResultType handWinType, int valueWinner, int cardWinner2) {
        super(victorieu, handWinType, valueWinner);
        this.valueWinner2 = cardWinner2;
    }

    public int getValueWinner2() {
        return valueWinner2;
    }

    @Override
    public String toString() {
        String res = super.toString();
        res += " par les " + valueWinner2;
        return res;
    }
}
