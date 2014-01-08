package com.gmail.woodyc40.miuserver.protocol;

import com.gmail.woodyc40.miuserver.Logger;
import com.gmail.woodyc40.miuserver.api.entity.Player;
import com.gmail.woodyc40.miuserver.frame.AdvancedServer;
import com.gmail.woodyc40.miuserver.frame.threadsafe.FinalWrapper;
import com.gmail.woodyc40.miuserver.frame.threadsafe.ServerThread;
import com.gmail.woodyc40.miuserver.protocol.event.EventHandler;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class PacketHandler {
    ServerThread thread;

    private static FinalWrapper<PacketHandler> handler;

    public static Logger getInstance() {
        FinalWrapper<PacketHandler> wrapper = logger;

        if (wrapper == null) {
            synchronized(PacketHandler.class) {
                if (handler == null) {
                    handler = new FinalWrapper<>(new Logger());
                }
                wrapper = handler;
            }
        }
        return wrapper.value;
    }

    private void writePacket(ServerThread thread, Packet packet) {
        try {
            ObjectOutputStream stream = thread.getClient().getClientOutput();
            
            stream.writeObject(packet);
            stream.flush();
            stream.reset();
        } catch (IOException e) {
            Logger.getInstance().logError("Packet could not be written to the stream", e);
        }
    }

    public static void sendPacket(ServerThread thread, Packet packet) {
        writePacket(thread, packet);
        EventHandler.getInstance().handleEvent(packet);
    }

    public static void handlePacket(Packet packet) {
        for(Player p : AdvancedServer.players) {
            sendPacket(p.getStream(), packet);
        }
    }

}
