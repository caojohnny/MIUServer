package info.mineinunity.miuserver.api.entity;

import info.mineinunity.miuserver.frame.threadsafe.ServerThread;

public class Player {

    ServerThread thread;
    String name;

    public Player(ServerThread thread, String name) {
        this.thread = thread;
        this.name = name;
    }

    public ServerThread getStream() {
        return thread;
    }

    public String getName() {
        return name;
    }
}
