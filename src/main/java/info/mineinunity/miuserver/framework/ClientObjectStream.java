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
import info.mineinunity.miuserver.protocol.auth.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientObjectStream {
    private ObjectInputStream clientInStream;
    private ObjectOutputStream clientOutStream;
    private Socket client;

    public Client auth;

    public ClientObjectStream connect(Socket client) throws IOException, ClassNotFoundException {
        this.clientInStream = new ObjectInputStream(client.getInputStream());
        this.clientOutStream = new ObjectOutputStream(client.getOutputStream());
        this.client = client;

        Object packet;
        while (true) {
            if ((packet = clientInStream.readObject()) != null && packet instanceof Client) {
                Logger.getInstance().log("Successfully authenticated " + ((Client) packet).getName());
                break;
            }
        }
        auth = (Client) packet;

        return this;
    }

    public ObjectInputStream getClientInput() {
        return clientInStream;
    }

    public ObjectOutputStream getClientOutput() {
        return clientOutStream;
    }

    public void disconnect() {
        try {
            clientInStream.close();
            clientOutStream.close();
            client.close();
        } catch (IOException e) {
            Logger.getInstance().logError("Could not close connections, memory leaks imminent", e);
        }
    }

}
