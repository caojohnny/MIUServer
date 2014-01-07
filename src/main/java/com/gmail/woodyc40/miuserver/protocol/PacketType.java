package com.gmail.woodyc40.miuserver.protocol;

import com.gmail.woodyc40.miuserver.protocol.bothways.PacketBlockBreak;
import com.gmail.woodyc40.miuserver.protocol.event.PacketSend;
import com.gmail.woodyc40.miuserver.protocol.toclient.PacketDisconnect;

public enum PacketType {

    SEND(PacketSend.class), DISCONNECT(PacketDisconnect.class), BLOCK_BREAK(PacketBlockBreak.class);

    Class<?> args;
    PacketType(Class<?> cl) {
        args = cl;
    }

    public Class<?> getArgs() {
        return args;
    }
}
