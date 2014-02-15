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

import info.mineinunity.miuserver.framework.threadsafe.FinalWrapper;

public class Logger {
    private static FinalWrapper<Logger> logger;

    public static Logger getInstance() {
        FinalWrapper<Logger> wrapper = logger;

        if (wrapper == null) {
            synchronized (Logger.class) {
                if (logger == null) {
                    logger = new FinalWrapper<>(new Logger());
                }
                wrapper = logger;
            }
        }
        return wrapper.value;
    }

    private Logger() {
    }

    public void logError(String message, Throwable throwable) {
        System.out.println("---------- ERROR ----------");
        System.out.println(throwable.getMessage() + " - " + throwable.getCause() + ": " + message + "\n");
        throwable.printStackTrace();
        System.out.println("---- END ERROR REPORT -----");
    }

    public void log(String message) {
        System.out.println("[INFO]" + message);
    }

}
