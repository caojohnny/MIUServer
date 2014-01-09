package com.gmail.woodyc40.miuserver.protocol.fromclient;

import com.gmail.woodyc40.miuserver.api.Location;
import com.gmail.woodyc40.miuserver.api.entity.Player;
import com.gmail.woodyc40.miuserver.protocol.Packet;
import com.gmail.woodyc40.miuserver.protocol.PacketType;

import java.io.Serializable;

public class PacketPlayerMove implements Packet, Serializable {
    private static final long serialVersionUID = -2961847818963671166L;

    Location from;
    Location to;
    Player player;

    public PacketPlayerMove(Location l, Location loc, Player p) {
        this.from = l;
        this.to = loc;
        this.player = p;
    }

    @Override
    public void onSend() {

    }

    @Override
    public PacketType getType() {
        return PacketType.MOVE;
    }
}
