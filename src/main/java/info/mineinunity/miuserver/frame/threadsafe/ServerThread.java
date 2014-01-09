package info.mineinunity.miuserver.frame.threadsafe;

import info.mineinunity.miuserver.Logger;
import info.mineinunity.miuserver.frame.ClientObjectStream;
import info.mineinunity.miuserver.protocol.Packet;
import info.mineinunity.miuserver.protocol.PacketHandler;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerThread extends Thread {
    private final ClientObjectStream client;

    public ServerThread(ClientObjectStream client) {
        this.client = client;
    }

    public ClientObjectStream getClient() {
        return client;
    }

    public void run() {
        ObjectInputStream in = client.getClientInput();
        Object o;
        try {
            if ((o = in.readObject()) != null && o instanceof Packet) {
                PacketHandler.getInstance().handlePacket((Packet) o);
            }
        } catch (IOException | ClassNotFoundException e) {
            Logger.getInstance().logError("Packet could not be handled", e);
        }
    }

}
