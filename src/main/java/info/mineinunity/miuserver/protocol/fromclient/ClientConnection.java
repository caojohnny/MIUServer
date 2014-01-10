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
package info.mineinunity.miuserver.protocol.fromclient;

import info.mineinunity.miuserver.protocol.auth.Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ClientConnection {
    private int port = 9001;
    private Socket socket = null;
    private String ip = "";

    private ObjectOutputStream writer = null;
    private ObjectInputStream reader = null;

    public ClientConnection(String address) throws Throwable {
        ip = address;
        socket = new Socket(address, port);
    }

    public boolean connect() throws Throwable {
        boolean successful = false;
        writer = new ObjectOutputStream(socket.getOutputStream());
        reader = new ObjectInputStream(socket.getInputStream());

        sendPacket(new Client("RandomName"));

        long start = System.nanoTime();
        while (true) {
            if (System.nanoTime() - start == (100 / 1000000000.0)) {
                // Timeout when the server does not respond
                // Send to connection timed out screen
                break;
            }

            Object o = reader.readObject();
            if (o != null) {
                // Check protocol
            }
        }

        return successful;
    }

    public ObjectInputStream listenPacketSend() {
        return reader;
    }

    void sendPacket(Object packet) throws Throwable {
        writer.writeObject(packet);
        writer.flush();
        writer.reset();
    }

    public void disconnect() throws Throwable {
        // Send them to the disconnect screen
        writer.close();  //
        reader.close();  // Prevent memory leaks
        socket.close();  //

        ip = null;
        port = 0; // Qualify this object for GC
    }
}
