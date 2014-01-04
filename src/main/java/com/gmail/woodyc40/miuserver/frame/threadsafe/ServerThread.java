package com.gmail.woodyc40.miuserver.frame.threadsafe;

import com.gmail.woodyc40.miuserver.frame.ClientObjectStream;

public class ServerThread extends Thread {
    ClientObjectStream client;

    public ServerThread(ClientObjectStream client) {
        this.client = client;
    }

    public ClientObjectStream getClient() {
        return client;
    }

    public void run() {
        while(true) {

        }
    }

}
