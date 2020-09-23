package launcher;

import util.cmdline.CmdLineController;

/**
 * This will launch the app in command line version
 *
 * @author Gabriel Cogne
 */

public class CmdLineMain {
    public static void main(String[] args) {
        CmdLineController controller = new CmdLineController();

        controller.displayState();

        controller.askPlayer();
        
    }
}
