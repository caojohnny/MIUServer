package info.mineinunity.miuserver.protocol.toclient;


import info.mineinunity.miuserver.api.entity.Player;
import info.mineinunity.miuserver.protocol.Packet;
import info.mineinunity.miuserver.protocol.PacketType;

import java.io.Serializable;

public class PacketDisconnect implements Packet, Serializable {
    private static final long serialVersionUID = 5128917118051330428L;

    Player player;

    public PacketDisconnect(Player p) {
        this.player = p;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void onSend() {

    }

    @Override
    public PacketType getType() {
        return PacketType.DISCONNECT;
    }
}
