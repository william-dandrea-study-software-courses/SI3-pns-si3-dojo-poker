package util.cmdline;

import hands.Hand;
import hands.HandComparator;
import hands.HandBuilder;

/**
 * This class will control a simple cmd line lifetime.
 *
 * @author Gabriel Cogne
 * @author
 * @author
 * @author
 */

public class CmdLineController {
    // -- Variables --
    private CmdLineUserInterface ui;
    private HandComparator comparator;

    // -- Comparator --
    public CmdLineController () {
        ui = new CmdLineUserInterface();
        comparator = new HandComparator();
    }

    public boolean isReady () {
        return ui != null && comparator != null;
    }

    public void displayState () {
        ui.print("(Controller) Ready ? " + isReady());
    }

    public Hand askPlayer(String question) {
        String line = ui.readLine(question);

        HandBuilder builder = new HandBuilder();
        Hand hand = builder.buildHandFromString(line);

        ui.print("You enter : " + hand.toString());

        return hand;
    }

    public void run () {
        HandComparator comparator = new HandComparator();

        Hand hand1 = null, hand2 = null;

        while(hand1 == null || hand2 == null) {
            try {
                hand1 = askPlayer("Main 1 : ");
                hand2 = askPlayer("Main 2 : ");
            } catch (IllegalArgumentException e) {
                ui.print("Une erreur de saisie à été détecté.");
            }
        }

        Hand winner = comparator.compare(hand1, hand2);
        if (winner == null) {
            ui.print("Egalite");
        } else if (winner.equals(hand1)){
            ui.print("La main 1 gagne avec" + comparator.getExplication());
        } else if (winner.equals(hand2)) {
            ui.print("La main 2 gagne avec" + comparator.getExplication());
        }
    }
}
