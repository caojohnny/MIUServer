package com.gmail.woodyc40.miuserver.protocol;

import com.gmail.woodyc40.miuserver.protocol.bothways.PacketBlockBreak;
import com.gmail.woodyc40.miuserver.protocol.fromclient.PacketPlayerMove;
import com.gmail.woodyc40.miuserver.protocol.toclient.PacketDisconnect;

public enum PacketType {

    DISCONNECT(PacketDisconnect.class), BLOCK_BREAK(PacketBlockBreak.class), MOVE(PacketPlayerMove.class);

    Class<?> args;
    PacketType(Class<?> cl) {
        args = cl;
    }

    public Class<?> getArgs() {
        return args;
    }
}
