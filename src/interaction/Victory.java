package interaction;
import cards.Card;
import interaction.Victorieu;
import interaction.ResultType;

import java.util.HashMap;

public class Victory {
    private HashMap<ResultType,String> types = new HashMap<ResultType,String>(){{
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
            return "il y a égalité avec "+ types.get(handWinType) + " " + cardWinner.toString() ;
        }
        else {
            if (victorieu == Victorieu.main1)
            {
                return "La main 1 a gagnée " + types.get(handWinType) + " " + cardWinner.toString() + ", la main 2 a perdue " + types.get(handLoseType) + " " + cardLoser.toString();
            }
            else {
                return "La main 2 a gagnée " + types.get(handWinType) + " " + cardWinner.toString() + ", la main 1 a perdue " + types.get(handLoseType) + " " + cardLoser.toString();
            }
        }
    }

}