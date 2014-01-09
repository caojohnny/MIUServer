package info.mineinunity.miuserver.protocol.fromclient;

import info.mineinunity.miuserver.api.Location;
import info.mineinunity.miuserver.api.entity.Player;
import info.mineinunity.miuserver.protocol.Packet;
import info.mineinunity.miuserver.protocol.PacketType;

import java.io.Serializable;

public class PacketPlayerMove implements Packet, Serializable {
    private static final long serialVersionUID = -2961847818963671166L;

    private final Location from;
    private final Location to;
    private final Player player;

    public PacketPlayerMove(Location l, Location loc, Player p) {
        this.from = l;
        this.to = loc;
        this.player = p;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void onSend() {

    }

    @Override
    public PacketType getType() {
        return PacketType.MOVE;
    }
}
