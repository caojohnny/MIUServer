package info.mineinunity.miuserver.protocol.event;

import info.mineinunity.miuserver.frame.ClientObjectStream;
import info.mineinunity.miuserver.protocol.Packet;

public class PacketSend implements Event<PacketSend> {

    private final Packet packet;
    private final ClientObjectStream destination;

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
    public void onEvent(Event<? extends Event<?>> e) {

    }

    @Override
    public Event<?> getType() {
        return this;
    }
}
