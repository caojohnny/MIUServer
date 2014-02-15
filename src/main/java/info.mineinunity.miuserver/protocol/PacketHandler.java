/*
 * This file is part of MIUServer.
 *
 * Contact: woodyc40(at)gmail(dot)com
 * 
 * Copyright (C) 2013 AgentTroll
 *    
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.mineinunity.miuserver.protocol;

import info.mineinunity.miuserver.Logger;
import info.mineinunity.miuserver.api.entity.Player;
import info.mineinunity.miuserver.framework.AdvancedServer;
import info.mineinunity.miuserver.framework.threadsafe.FinalWrapper;
import info.mineinunity.miuserver.framework.threadsafe.ServerThread;
import info.mineinunity.miuserver.protocol.event.Event;
import info.mineinunity.miuserver.protocol.event.EventAdapter;
import info.mineinunity.miuserver.protocol.event.EventHandler;
import info.mineinunity.miuserver.protocol.event.PacketSend;
import info.mineinunity.miuserver.util.CodeExecutor;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class PacketHandler {
    private static FinalWrapper<PacketHandler> handler;

    public static PacketHandler getInstance() {
        FinalWrapper<PacketHandler> wrapper = handler;
        if (wrapper == null) {
            synchronized (PacketHandler.class) {
                if (handler == null) {
                    handler = new FinalWrapper<>(new PacketHandler());
                }
                wrapper = handler;
            }
        }
        return wrapper.value;
    }

    private void writePacket(ServerThread thread, Packet packet) {
        try {
            ObjectOutputStream stream = thread.getClient().getClientOutput();

            stream.writeObject(packet);
            stream.flush();
            stream.reset();
        } catch (IOException e) {
            Logger.getInstance().logError("Packet could not be written to the stream", e);
        }
    }

    private <T extends Event<?>> void sendPacket(ServerThread thread, Packet packet) {
        writePacket(thread, packet);

        Event<PacketSend> event = new PacketSend(packet, thread.getClient());
        EventAdapter<T> adapter = new EventAdapter<>(new CodeExecutor<Event<T>>((Event<T>) event) {
            @Override
            public void runCode(Event<T> e) {
                e.onEvent(e);
            }
        });
        EventHandler.getInstance().handleEvent(new PacketSend(packet, thread.getClient()));
        EventHandler.getInstance().unregister(adapter);
    }

    public void handlePacket(Packet packet) {
        for (Player p : AdvancedServer.players) {
            sendPacket(p.getStream(), packet);
        }
    }

}
