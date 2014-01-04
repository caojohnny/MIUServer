package com.gmail.woodyc40.miuserver.frame;

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
}
