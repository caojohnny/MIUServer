package info.mineinunity.miuserver.api;

import info.mineinunity.miuserver.api.entity.Player;
import info.mineinunity.miuserver.frame.AdvancedServer;

public class ServerHandler {

    public static Player playerForName(String name) {
        for (Player p : AdvancedServer.players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

}
