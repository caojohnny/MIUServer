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
package info.mineinunity.miuserver.protocol.fromclient;

import info.mineinunity.miuserver.api.Location;
import info.mineinunity.miuserver.api.entity.Entity;
import info.mineinunity.miuserver.protocol.Packet;
import info.mineinunity.miuserver.protocol.PacketType;

import java.io.Serializable;

public class PacketEntityMove implements Packet, Serializable {
    private static final long serialVersionUID = -2961847818963671166L;

    private final Location from;
    private final Location to;
    private final Entity entity;

    public PacketEntityMove(Location l, Location loc, Entity e) {
        this.from = l;
        this.to = loc;
        this.entity = e;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Entity getEntity() {
        return entity;
    }

    @Override
    public void onSend() {

    }

    @Override
    public PacketType getType() {
        return PacketType.MOVE;
    }
}
