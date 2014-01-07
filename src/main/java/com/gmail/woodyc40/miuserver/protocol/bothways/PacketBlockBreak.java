package com.gmail.woodyc40.miuserver.protocol.bothways;

import com.gmail.woodyc40.miuserver.api.block.Block;
import com.gmail.woodyc40.miuserver.protocol.Packet;
import com.gmail.woodyc40.miuserver.protocol.PacketType;

import java.io.Serializable;

public class PacketBlockBreak implements Packet, Serializable {
    private static final long serialVersionUID = -1739894988291076245L;

    Block b;

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
