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
package info.mineinunity.miuserver.api.block;

import info.mineinunity.miuserver.api.Location;
import info.mineinunity.miuserver.api.Material;

import java.io.Serializable;

public class Block implements Serializable {
    private static final long serialVersionUID = 7096842357276401602L;

    private final Location loc;
    private final Material m;

    public Block(Location loc, Material m) {
        this.loc = loc;
        this.m = m;
    }

    public Location getLocation() {
        return loc;
    }

    public Material getType() {
        return m;
    }

}
