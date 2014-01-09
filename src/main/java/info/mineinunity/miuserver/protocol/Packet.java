package info.mineinunity.miuserver.protocol;

public interface Packet {
    public void onSend();

    public PacketType getType();
}
