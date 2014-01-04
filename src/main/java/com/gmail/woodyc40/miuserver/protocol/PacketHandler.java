package com.gmail.woodyc40.miuserver.protocol;

import com.gmail.woodyc40.miuserver.Logger;
import com.gmail.woodyc40.miuserver.frame.threadsafe.ServerThread;
import com.gmail.woodyc40.miuserver.protocol.event.EventHandler;

import java.io.IOException;

public class PacketHandler {
    ServerThread thread;

    public void sendPacket(ServerThread thread, Packet packet) {
        try {
            thread.getClient().getClientOutput().writeObject(packet);
        } catch (IOException e) {
            Logger.getInstance().logError("Packet could not be written to the stream", e);
        }
        new EventHandler().handleEvent(packet);
    }

}
