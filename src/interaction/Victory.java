package interaction;

import cards.Value;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * This class represent the victory message
 * @author Amine CHOUHABI
 * @author Gabriel Cogne
 */
public class Victory {
    protected final HashMap<ResultType,String> types = new HashMap<>(){{
        put(ResultType.brelan,"brelan de");
        put(ResultType.carre,"carré de");
        put(ResultType.couleur,"couleur au");
        put(ResultType.doublePair,"double pair de");
        put(ResultType.full,"full aux");
        put(ResultType.higherCard,"carte la plus elevee :");
        put(ResultType.pair,"pair de");
        put(ResultType.quinteFlush,"quinte flush au");
        put(ResultType.suite,"suite au");
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
            return "Egalite";
        }
        else {
            if (victorieu == Victorieu.main1)
            {
                return "La main 1 gagne par " + types.get(handWinType) + " " + getValueWinner();
            }
            else {
                return "La main 2 gagne par " + types.get(handWinType) + " " + getValueWinner();
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