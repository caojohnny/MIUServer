package info.mineinunity.miuserver.frame;

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
