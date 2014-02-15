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
package info.mineinunity.miuserver;

import info.mineinunity.miuserver.framework.AdvancedServer;
import info.mineinunity.miuserver.framework.threadsafe.Listener;
import info.mineinunity.miuserver.util.CodeExecutor;

public class MIUServer {
    private static final AdvancedServer server = new AdvancedServer();

    public static void main(String args[]) {
        server.openConnections();
        Logger.getInstance().log("Started Server");

        Logger.getInstance().log("Connections enabled. Players may now join");
        server.enableConnection();
        server.listen();

        Listener listener = new Listener(new CodeExecutor<Boolean>(Boolean.TRUE) {
            @Override
            public void runCode(Boolean aBoolean) {
                while (aBoolean) {
                    String line;
                    if ((line = System.console().readLine()) != null && line.equals("stop")) {
                        server.shutdown();
                    }
                }
            }
        });
        listener.start();
    }

}
