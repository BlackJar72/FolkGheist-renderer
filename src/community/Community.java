package community;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import community.ui.UI;
import community.simulation.*;
import community.ui.models.Mesh;
import community.ui.models.OBJLoader;
import community.ui.models.TextureLoader;


/**
 *
 * @author jared
 */
public class Community {
    public static Game game = new Game();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UI.init(game);
        UI.loop();
        UI.cleanup();
    }
}
