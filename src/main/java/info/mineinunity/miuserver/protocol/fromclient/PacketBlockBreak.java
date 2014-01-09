package info.mineinunity.miuserver.protocol.fromclient;

import info.mineinunity.miuserver.api.block.Block;
import info.mineinunity.miuserver.protocol.Packet;
import info.mineinunity.miuserver.protocol.PacketType;

import java.io.Serializable;

public class PacketBlockBreak implements Packet, Serializable {
    private static final long serialVersionUID = -1739894988291076245L;

    private final Block b;

    public PacketBlockBreak(Block b) {
        this.b = b;
    }

    public Block getBlock() {
        return b;
    }

    @Override
    public void onSend() {

    }

    @Override
    public PacketType getType() {
        return PacketType.BLOCK_BREAK;
    }
}
