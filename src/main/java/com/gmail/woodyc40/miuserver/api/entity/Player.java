package com.gmail.woodyc40.miuserver.api.entity;

import com.gmail.woodyc40.miuserver.frame.threadsafe.ServerThread;

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
