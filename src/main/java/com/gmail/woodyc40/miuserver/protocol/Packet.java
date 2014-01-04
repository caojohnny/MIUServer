package com.gmail.woodyc40.miuserver.protocol;

public interface Packet {
    public void onSend();

    public PacketType getType();
}
