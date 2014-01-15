/*
 * This file is part of MIUServer.
 *
 * Contact: woodyc40(at)gmail(dot)com
 * 
 * Copyright (C) 2013 AgentTroll
 *    
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.mineinunity.miuserver.framework;

import info.mineinunity.miuserver.Logger;
import info.mineinunity.miuserver.api.entity.Player;
import info.mineinunity.miuserver.framework.threadsafe.ServerThread;
import info.mineinunity.miuserver.protocol.Packet;
import info.mineinunity.miuserver.protocol.PacketHandler;
import info.mineinunity.miuserver.protocol.toclient.PacketDisconnect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdvancedServer implements BasicServer {
    private ServerSocket socket = null;
    private boolean enabled = false;
    private int port = 9001;

    public static final List<Player> players = new CopyOnWriteArrayList<>();

    @Override
    public void openConnections() {
        if (enabled) {
            return;
        }

        try {
            socket = new ServerSocket(port);
            enabled = true;
        } catch (IOException e) {
            Logger.getInstance().logError("The socket could not bind to port", e);
        }
    }

    public void enableConnection() {
        if (!enabled) {
            return;
        }

        Socket sock = null;
        while (true) {
            try {
                if ((sock = socket.accept()) != null) {
                    ClientObjectStream cos = new ClientObjectStream().connect(sock);
                    if (cos == null) {
                        Logger.getInstance().log("Illegal player tried to login, disconnecting");
                        continue;
                    }

                    String name = cos.auth.getName();

                    ServerThread thread = new ServerThread(cos);
                    thread.start();
                    players.add(new Player(thread, name));
                }
            } catch (IOException | ClassNotFoundException e) {
                assert sock != null;
                Logger.getInstance().logError("Failed to bind " + sock.getRemoteSocketAddress().toString(), e);
                shutdown();
                break;
            }
        }
    }

    @Override
    public void disconnect(Player p) {
        if (!enabled) {
            return;
        }

        PacketHandler.getInstance().handlePacket(new PacketDisconnect(p));
        p.getStream().getClient().disconnect();
        players.remove(p);
    }

    @Override
    public void listen() {
        if (!enabled) {
            return;
        }

        while(true) {
            for (Player p : players) {
                ObjectInputStream stream = p.getStream().getClient().getClientInput();
                Packet packet = null;
                try {
                    packet = (Packet) stream.readObject();
                } catch (IOException | ClassNotFoundException | ClassCastException e) {
                    Logger.getInstance().logError("Packet could not be read", e);
                }

                if (packet != null) {
                    PacketHandler.getInstance().handlePacket(packet);
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

    public void shutdown() {
        if (enabled) {
            return;
        }
        enabled = false;

        for (Player p : players) {
            disconnect(p);
        }

        try {
            socket.close();
        } catch (IOException e) {
            Logger.getInstance().logError("Socket could not be closed", e);
        }
        port = 0;

        Logger.getInstance().log("Server shutdown successful");
        System.exit(0);
    }

    public void loadPlugins() {

    }
}
