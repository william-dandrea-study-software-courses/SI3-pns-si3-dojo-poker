package launcher;

import hands.HandComparator;

/**
 * This will launch the app in command line version
 *
 * @author Gabriel Cogne
 */

public class CmdLineMain {
    public static void main(String[] args) {
        System.out.println("Launching the app...");

        HandComparator comparator = new HandComparator();

        System.out.println("Hand comparator ready : " + comparator);
    }
}
