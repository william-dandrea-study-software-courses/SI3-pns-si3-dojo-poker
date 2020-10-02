package util.cmdline;

import hands.Hand;
import hands.HandComparator;
import hands.HandBuilder;
import interaction.Victory;

/**
 * This class will control a simple cmd line lifetime.
 *
 * @author Gabriel Cogne
 * @author Clodong Yann
 */

public class CmdLineController {
    // -- Variables --
    private final CmdLineUserInterface ui;
    private final HandComparator comparator;
    private final HandBuilder builder;

    // -- Comparator --
    public CmdLineController () {
        ui = new CmdLineUserInterface();
        comparator = new HandComparator();
        builder = new HandBuilder();
    }

    public Hand askPlayer(String question) {
        String line = ui.readLine(question);

        Hand hand = builder.buildHandFromString(line);

        ui.print("You enter : " + hand.toString());

        return hand;
    }

    public void run () {
        Hand hand1 = null, hand2 = null;

        while(hand1 == null || hand2 == null) {
            try {
                hand1 = askPlayer("Main 1 : ");
                hand2 = askPlayer("Main 2 : ");
            } catch (IllegalArgumentException e) {
                ui.print("Une erreur de saisie à été détecté.");
            }
        }

        Victory victory = comparator.compare(hand1, hand2);
        ui.print(victory);
    }
}
