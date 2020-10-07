package interaction;

import cards.Value;

import java.util.HashMap;
import java.util.ResourceBundle;

public class Victory {
    protected final HashMap<ResultType,String> types = new HashMap<>(){{
        put(ResultType.brelan,"Brelan");
        put(ResultType.carre,"carré");
        put(ResultType.couleur,"couleur");
        put(ResultType.doublePair,"double pair");
        put(ResultType.full,"Full");
        put(ResultType.higherCard,"Higher Card");
        put(ResultType.pair,"Pair");
        put(ResultType.quinteFlush,"Quinte Flush");
        put(ResultType.suite,"Suite");
    }};
    private final Victorieu victorieu;
    private final ResultType handWinType;
    private final Value valueWinner;

    public Victory(Victorieu victorieu, ResultType handWinType,  Value valueWinner){
        this.victorieu = victorieu;
        this.handWinType = handWinType;
        this.valueWinner = valueWinner;

    }

    public Victorieu getWinner(){
        return victorieu;
    }
    public ResultType getWinType(){
        return handWinType;
    }

    public Value getValueWinner() {
        return valueWinner;
    }

    @Override
    public String toString() {
        if (victorieu == Victorieu.egalite){
            return "Egalite "+ types.get(handWinType) + " " + getValueWinner();
        }
        else {
            if (victorieu == Victorieu.main1)
            {
                return "La main 1 gagne par " + types.get(handWinType) + ":" + getValueWinner();
            }
            else {
                return "La main 2 gagne par " + types.get(handWinType) + ":" + getValueWinner();
            }
        }
    }

    public String describe (ResourceBundle r) {
        if (getWinner().equals(Victorieu.egalite))
            return r.getString("draw");

        String res = r.getString(getWinner().name()) + " ";

        res += r.getString(getWinType().name());
        res += " ";
        res += r.getString(getValueWinner().name());

        return res;
    }
}