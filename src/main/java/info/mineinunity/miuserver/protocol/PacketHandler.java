package info.mineinunity.miuserver.protocol;

import info.mineinunity.miuserver.Logger;
import info.mineinunity.miuserver.api.entity.Player;
import info.mineinunity.miuserver.frame.AdvancedServer;
import info.mineinunity.miuserver.frame.threadsafe.FinalWrapper;
import info.mineinunity.miuserver.frame.threadsafe.ServerThread;
import info.mineinunity.miuserver.protocol.event.EventHandler;
import info.mineinunity.miuserver.protocol.event.PacketSend;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class PacketHandler {
    ServerThread thread;

    private static FinalWrapper<PacketHandler> handler;

    public static PacketHandler getInstance() {
        FinalWrapper<PacketHandler> wrapper = handler;
        if (wrapper == null) {
            synchronized (PacketHandler.class) {
                if (handler == null) {
                    handler = new FinalWrapper<>(new PacketHandler());
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

    private void sendPacket(ServerThread thread, Packet packet) {
        writePacket(thread, packet);
        EventHandler.getInstance().handleEvent(new PacketSend(packet, thread.getClient()));
    }

    public void handlePacket(Packet packet) {
        for (Player p : AdvancedServer.players) {
            sendPacket(p.getStream(), packet);
        }
    }

}
