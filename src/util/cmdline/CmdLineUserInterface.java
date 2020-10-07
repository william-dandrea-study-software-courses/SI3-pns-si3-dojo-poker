package util.cmdline;

import hands.Hand;
import hands.HandBuilder;
import interaction.Victory;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class is the interface in command line between user and computer
 *
 * @author Gabriel Cogne
 * @version 1.0
 */

public class CmdLineUserInterface {
    // -- Attributes --
    private final PrintStream out;
    private final Scanner in;
    private final CmdLineController ctrl;

    // -- Constructors --
    public CmdLineUserInterface (CmdLineController ctrl) {
        this (System.out, System.in, ctrl);
    }

    public CmdLineUserInterface (PrintStream out, InputStream in, CmdLineController ctrl) {
        this.out = out;
        this.in = new Scanner(in);
        this.ctrl = ctrl;
    }

    // -- Methods --
    /**
     * This method will display an object and go to next line (this mean it add a '\n')
     * @param msg the object to display using {@link #toString() toString} method
     */
    public void print (Object msg) {
        out.println(msg);
    }

    /**
     * This method will read the input
     * @return a string
     */
    public String readLine () {
        return in.nextLine();
    }

    /**
     * This method will read the input after the prompt
     * @param prompt a string to be printed before the input
     * @return a string
     */
    public String readLine (String prompt) {
        out.print(prompt);
        return readLine();
    }

    // -- Real business methods --
    public Hand askPlayer(String question, HandBuilder builder) {
        String line = readLine(question);

        Hand hand = builder.buildHandFromString(line);

        // print("You enter : " + hand.toString());

        return hand;
    }

    public void printVictory (Victory victory) {
        print(victory.describe(ctrl.getR()));
    }
}
