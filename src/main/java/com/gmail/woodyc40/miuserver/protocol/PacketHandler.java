package com.gmail.woodyc40.miuserver.protocol;

import com.gmail.woodyc40.miuserver.Logger;
import com.gmail.woodyc40.miuserver.frame.threadsafe.ServerThread;
import com.gmail.woodyc40.miuserver.protocol.event.EventHandler;

import java.io.IOException;

public class PacketHandler {
    ServerThread thread;

    public static void sendPacket(ServerThread thread, Packet packet) {
        try {
            ObjectOutputStream stream = thread.getClient().getClientOutput();
            
            stream.writeObject(packet);
            stream.flush();
            stream.reset();
        } catch (IOException e) {
            Logger.getInstance().logError("Packet could not be written to the stream", e);
        }
        new EventHandler().handleEvent(packet);
    }

    public static void handlePacket(Packet packet) {
        //TODO
    }

}
