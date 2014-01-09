package info.mineinunity.miuserver.protocol;

import info.mineinunity.miuserver.protocol.bothways.PacketBlockBreak;
import info.mineinunity.miuserver.protocol.fromclient.PacketPlayerMove;
import info.mineinunity.miuserver.protocol.toclient.PacketDisconnect;

public enum PacketType {

    DISCONNECT(PacketDisconnect.class), BLOCK_BREAK(PacketBlockBreak.class), MOVE(PacketPlayerMove.class);

    private final Class<?> args;

    PacketType(Class<?> cl) {
        args = cl;
    }

    public Class<?> getArgs() {
        return args;
    }
}
