package com.gmail.woodyc40.miuserver.protocol.event;


import com.gmail.woodyc40.miuserver.frame.ClientObjectStream;
import com.gmail.woodyc40.miuserver.protocol.Packet;
import com.gmail.woodyc40.miuserver.protocol.PacketType;

import java.io.Serializable;

public class PacketSend implements Packet, Serializable {
    private static final long serialVersionUID = 5828032708385937889L;

    Packet packet;
    ClientObjectStream destination;

    public PacketSend(Packet packet, ClientObjectStream destination) {
        this.packet = packet;
        this.destination = destination;
    }

    public Packet getPacket() {
        return packet;
    }

    public ClientObjectStream getDestination() {
        return destination;
    }

    @Override
    public void onSend() {

    }

    @Override
    public PacketType getType() {
        return PacketType.SEND;
    }
}
