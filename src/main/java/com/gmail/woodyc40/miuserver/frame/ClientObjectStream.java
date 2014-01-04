package com.gmail.woodyc40.miuserver.frame;

import com.gmail.woodyc40.miuserver.Logger;
import com.gmail.woodyc40.miuserver.protocol.auth.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientObjectStream {
    ObjectInputStream clientInStream;
    ObjectOutputStream clientOutStream;
    Socket client;

    public ClientObjectStream connect(Socket client) throws IOException, ClassNotFoundException {
        this.clientInStream = new ObjectInputStream(client.getInputStream());
        this.clientOutStream = new ObjectOutputStream(client.getOutputStream());
        this.client = client;

        if(!(clientInStream.readObject() instanceof Client)) {
            return null;
        }

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
        } catch(IOException e) {
            Logger.getInstance().logError("Could not close connections, memory leaks imminent", e);
        }
    }
    
}
