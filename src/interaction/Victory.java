package interaction;

import java.util.HashMap;

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
    private final int valueWinner;

    public Victory(Victorieu victorieu, ResultType handWinType,  int valueWinner){
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

    public int getValueWinner() {
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

}