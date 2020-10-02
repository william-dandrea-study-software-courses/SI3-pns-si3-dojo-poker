package interaction;
import cards.Card;
import interaction.Victorieu;
import interaction.ResultType;

import java.util.HashMap;

public class Victory {
    private HashMap<ResultType,String> types = new HashMap<ResultType,String>(){{
        put(ResultType.brelan,"brelan de ");
        put(ResultType.carre,"carré de ");
        put(ResultType.couleur,"couleur de ");
        put(ResultType.doublePair,"double pair de ");
        put(ResultType.full,"full au ");
        put(ResultType.higherCard,"carte la plus élevée : ");
        put(ResultType.pair,"pair de ");
        put(ResultType.quinteFlush,"quinte flush au ");
        put(ResultType.suite,"suite au ");
    }};
    private Victorieu victorieu;
    private ResultType handWinType, handLoseType;
    private Card cardWinner, cardLoser;

    public Victory(Victorieu victorieu, ResultType handWinType, ResultType handLoseType, Card cardWinner, Card cardLoser){
        this.victorieu = victorieu;
        this.handWinType = handWinType;
        this.cardWinner = cardWinner;
        this.cardLoser = cardLoser;
        this.handLoseType = handLoseType;

    }

    public Victorieu getWinner(){
        return victorieu;
    }
    public ResultType getWinType(){
        return handWinType;
    }
    public ResultType getLoseType(){
        return handLoseType;
    }

    @Override
    public String toString() {
        if (victorieu == Victorieu.egalite){
            //return "il y a égalité avec "+ types.get(handWinType) + " " + cardWinner.toString() ;
            return "Egalite";
        }
        else if (victorieu == Victorieu.main1)
        {
            // return "La main 1 a gagnée " + types.get(handWinType) + " " + cardWinner.toString() + ", la main 2 a perdue " + types.get(handLoseType) + " " + cardLoser.toString();
            String res = "La main 1 gagne avec " + types.get(handWinType);

            if (handWinType.equals(ResultType.couleur))
                res += cardWinner.getColor().name();
            else
                res += cardWinner.getValue();

            return res;
        }
        else {
            //return "La main 2 a gagnée " + types.get(handWinType) + " " + cardWinner.toString() + ", la main 1 a perdue " + types.get(handLoseType) + " " + cardLoser.toString();
            String res = "La main 2 gagne avec " + types.get(handWinType);

            if (handWinType.equals(ResultType.couleur))
                res += cardWinner.getColor().name();
            else
                res += cardWinner.getDisplayableValue();

            return res;
        }
    }

}