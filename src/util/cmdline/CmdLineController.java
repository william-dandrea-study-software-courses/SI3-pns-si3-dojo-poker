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

    public void askPlayer() {
        String line = ui.readLine("What the hand : ");

        HandBuilder builder = new HandBuilder();
        Hand hand = builder.buildHandFromString(line);

        ui.print("You enter : " + hand.toString());
    }
}
