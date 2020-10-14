package util.cmdline;

import hands.Hand;
import hands.HandComparator;
import hands.HandBuilder;
import interaction.Victory;

import java.util.Locale;
import java.util.ResourceBundle;

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

    private ResourceBundle r;

    private boolean highway2toString = false;

    // -- Comparator --
    public CmdLineController () {
        ui = new CmdLineUserInterface(this);
        comparator = new HandComparator();
        builder = new HandBuilder();

        try {
            r = ResourceBundle.getBundle("values.strings", Locale.getDefault());
        } catch (Exception e) {
            r = null;
            highway2toString = true;
        }
    }

    // -- Getters --
    public ResourceBundle getR() {
        return r;
    }

    // -- Other methods --
    public void run () {
        Hand hand1 = null, hand2 = null;

        while(hand1 == null || hand2 == null) {
            try {
                if (highway2toString) {
                    hand1 = ui.askPlayer("Main 1 : ", builder);
                    hand2 = ui.askPlayer("Main 2 : ", builder);
                } else {
                    hand1 = ui.askPlayer(r.getString("hand") + " 1 : ", builder);
                    hand2 = ui.askPlayer(r.getString("hand") + " 2 : ", builder);
                }
            } catch (Exception e) {
                if (highway2toString)
                    ui.print("Une erreur de saisie à été détecté.");
                else
                    ui.print(r.getString("hand_creation_error"));
            }
        }

        Victory victory = null;
        try {
            victory = comparator.compare(hand1, hand2);
        } catch (Exception e) {
            ui.print(e.getMessage());
        }

        assert victory != null;
        
        if (highway2toString)
            ui.print(victory);
        else
            ui.printVictory(victory);
    }
}
