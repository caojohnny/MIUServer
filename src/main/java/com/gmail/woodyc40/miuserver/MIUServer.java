package com.gmail.woodyc40.miuserver;

import com.gmail.woodyc40.miuserver.frame.AdvancedServer;
import com.gmail.woodyc40.miuserver.frame.threadsafe.Listener;
import com.gmail.woodyc40.miuserver.util.CodeExecutor;

public class MIUServer {
    private static final AdvancedServer server = new AdvancedServer();

    public static void main(String args[]) {
        server.openConnections();
        Logger.getInstance().log("Started Server");

        Logger.getInstance().log("Connections enabled. Players may now join");
        server.enableConnection();
        server.listen();

        Listener listener = new Listener(new CodeExecutor<Boolean>(Boolean.TRUE) {
            @Override
            public void runCode(Boolean aBoolean) {
                while(aBoolean) {
                    String line;
                    if((line = System.console().readLine()) != null && line.equals("stop")) {
                        server.shutdown();
                    }
                }
            }
        });
        listener.start();
    }

}
