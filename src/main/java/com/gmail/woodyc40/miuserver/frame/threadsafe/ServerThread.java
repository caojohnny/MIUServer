package com.gmail.woodyc40.miuserver.frame.threadsafe;

import com.gmail.woodyc40.miuserver.frame.ClientObjectStream;
import com.gmail.woodyc40.miuserver.protocol.*;

import java.io.ObjectInputStream;

public class ServerThread extends Thread {
    ClientObjectStream client;

    public ServerThread(ClientObjectStream client) {
        this.client = client;
    }

    public ClientObjectStream getClient() {
        return client;
    }

    public void run() {
        ObjectInputStream in = client.getClientInput();
        Object o;
        if((o = in.readObject()) != null && o instanceof Packet) {
            PacketHandler.handlePacket((Packet) o);
        }
    }

}
