package info.mineinunity.miuserver.frame;

import info.mineinunity.miuserver.api.entity.Player;

public interface BasicServer {

    public void openConnections();

    public void disconnect(Player p);

    public void listen();

    public Object output();

    public Object read();

}