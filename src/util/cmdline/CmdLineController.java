package util.cmdline;

import hands.HandComparator;

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
    public void askPlayer(){
        ui.print("What is your hand? :");
        String line=ui.readline()
    }
}
