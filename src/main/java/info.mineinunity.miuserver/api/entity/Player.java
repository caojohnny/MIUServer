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
package info.mineinunity.miuserver.api.entity;

import info.mineinunity.miuserver.framework.threadsafe.ServerThread;

import java.io.Serializable;

public class Player extends Entity implements Serializable {
    private static final long serialVersionUID = 4377500550270916244L;

    private final ServerThread thread;
    private final String name;

    public Player(ServerThread thread, String name) {
        this.thread = thread;
        this.name = name;
    }

    public ServerThread getStream() {
        return thread;
    }

    public String getName() {
        return name;
    }
}
