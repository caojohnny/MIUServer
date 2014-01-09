package info.mineinunity.miuserver;

import info.mineinunity.miuserver.frame.AdvancedServer;
import info.mineinunity.miuserver.frame.threadsafe.Listener;
import info.mineinunity.miuserver.util.CodeExecutor;

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
                while (aBoolean) {
                    String line;
                    if ((line = System.console().readLine()) != null && line.equals("stop")) {
                        server.shutdown();
                    }
                }
            }
        });
        listener.start();
    }

}
