package com.gmail.woodyc40.miuserver.frame;

import com.gmail.woodyc40.miuserver.Logger;
import com.gmail.woodyc40.miuserver.api.entity.Player;
import com.gmail.woodyc40.miuserver.frame.threadsafe.ServerThread;
import com.gmail.woodyc40.miuserver.protocol.Packet;
import com.gmail.woodyc40.miuserver.protocol.PacketHandler;
import com.gmail.woodyc40.miuserver.protocol.auth.Client;
import com.gmail.woodyc40.miuserver.protocol.toclient.PacketDisconnect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class AdvancedServer implements BasicServer {
    ServerSocket socket = null;
    int port = 6969;

    public static List<Player> players = new LinkedList<>();

    @Override
    public void openConnections() {
        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            Logger.getInstance().logError("The socket could not bind to port", e);
        }

        Socket sock = null;
        while(true) {
            try {
                if((sock = socket.accept()) != null) {
                    ClientObjectStream cos = new ClientObjectStream().connect(sock);
                    if(cos == null) {
                        continue;
                    }

                    System.out.print("Connected client from " + sock.getRemoteSocketAddress().toString());

                    Object authentication = cos.getClientInput().readObject();
                    String name = ((Client) authentication).getName();

                    ServerThread thread = new ServerThread(cos);
                    Player p = new Player(thread, name);

                    players.add(p);
                    thread.start();
                }
            } catch (IOException | ClassNotFoundException e) {
                assert sock != null;
                Logger.getInstance().logError("Failed to bind " + sock.getRemoteSocketAddress().toString(), e);
            }
        }

    }

    @Override
    public void disconnect(Player p) {
        PacketHandler.sendPacket(p.getStream(), new PacketDisconnect(p));
    }

    @Override
    public void listen() {
        while(true) {
            for(Player p : players) {
                ObjectInputStream stream = p.getStream().getClient().getClientInput();
                Packet packet = null;
                try {
                    packet = (Packet) stream.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    Logger.getInstance().logError("Packet could not be read", e);
                }
                if(packet != null) {
                    PacketHandler.handlePacket(packet);
                }
            }
        }
    }

    @Override
    public Object output() {
        return null;
    }

    @Override
    public Object read() {
        return null;
    }
}
