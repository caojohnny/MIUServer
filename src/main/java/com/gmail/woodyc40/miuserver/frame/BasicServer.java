package com.gmail.woodyc40.miuserver.frame;

public interface BasicServer {

    public void openConnections();

    public void disconnect();

    public void listen();

    public Object output();

    public Object read();

}