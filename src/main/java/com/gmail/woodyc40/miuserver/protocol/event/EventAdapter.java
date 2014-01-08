package com.gmail.woodyc40.miuserver.protocol.event;

import com.gmail.woodyc40.miuserver.Logger;
import com.gmail.woodyc40.miuserver.protocol.Packet;
import com.gmail.woodyc40.miuserver.protocol.PacketType;
import com.gmail.woodyc40.miuserver.util.CodeExecutor;

import java.lang.reflect.ParameterizedType;

public class EventAdapter {
    PacketType type;
    CodeExecutor<Packet> executor;
    Packet packet;

    public EventAdapter(PacketType type, CodeExecutor<Packet> executor) {       
        this.type = type;
        this.executor = executor;

        EventHandler.register(this);
    }

    public EventAdapter(CodeExecutor<? implements Event> ce) {
        this.executor = ce;
        EventHandler.register(this);
    }

    public Packet getPacket() {
        return packet;
    }

    public PacketType getType() {
        return type;
    }

    public void callEvent(Packet packet) {
        this.packet = packet;

        executor.runCode(getPacket());
    }

}
