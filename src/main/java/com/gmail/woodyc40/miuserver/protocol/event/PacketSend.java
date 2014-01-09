package com.gmail.woodyc40.miuserver.protocol.event;

import com.gmail.woodyc40.miuserver.frame.ClientObjectStream;
import com.gmail.woodyc40.miuserver.protocol.Packet;

public class PacketSend implements Event<PacketSend> {

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
    public void onEvent(PacketSend e) {
        
    }

    @Override
    public Event<?> getType() {
        return this;
    }
}
