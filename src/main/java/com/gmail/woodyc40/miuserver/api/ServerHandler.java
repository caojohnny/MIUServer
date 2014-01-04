package com.gmail.woodyc40.miuserver.api;

import com.gmail.woodyc40.miuserver.api.entity.Player;
import com.gmail.woodyc40.miuserver.frame.AdvancedServer;

public class ServerHandler {

    public static Player playerForName(String name) {
        for(Player p : AdvancedServer.players) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

}
