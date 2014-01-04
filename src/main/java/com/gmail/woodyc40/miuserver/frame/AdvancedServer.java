package com.gmail.woodyc40.miuserver.frame;

import com.gmail.woodyc40.miuserver.Logger;
import com.gmail.woodyc40.miuserver.frame.threadsafe.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class AdvancedServer implements BasicServer {
    ServerSocket socket = null;
    int port = 6969;

    List<ServerThread> players = new LinkedList<>();

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

                    ServerThread thread = new ServerThread(cos);
                    players.add(thread);
                    thread.start();
                }
            } catch (IOException | ClassNotFoundException e) {
                assert sock != null;
                Logger.getInstance().logError("Failed to bind " + sock.getRemoteSocketAddress().toString(), e);
            }
        }

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void listen() {

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
