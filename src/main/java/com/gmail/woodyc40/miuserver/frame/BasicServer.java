package com.gmail.woodyc40.miuserver.frame;

import com.gmail.woodyc40.miuserver.api.entity.Player;

public interface BasicServer {

    public void openConnections();

    public void disconnect(Player p);

    public void listen();

    public Object output();

    public Object read();

}