package com.gmail.woodyc40.miuserver.protocol;

import com.gmail.woodyc40.miuserver.protocol.event.PacketSend;

public enum PacketType {

    SEND(PacketSend.class);

    Class<?> args;
    PacketType(Class<?> cl) {
        args = cl;
    }

    public Class<?> getArgs() {
        return args;
    }
}
