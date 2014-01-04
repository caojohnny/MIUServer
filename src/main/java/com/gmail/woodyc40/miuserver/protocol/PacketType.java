package com.gmail.woodyc40.miuserver.protocol;

import com.gmail.woodyc40.miuserver.protocol.event.PacketSend;
import com.gmail.woodyc40.miuserver.protocol.toclient.PacketDisconnect;

public enum PacketType {

    SEND(PacketSend.class), DISCONNECT(PacketDisconnect.class);

    Class<?> args;
    PacketType(Class<?> cl) {
        args = cl;
    }

    public Class<?> getArgs() {
        return args;
    }
}
