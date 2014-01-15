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
package info.mineinunity.miuserver.protocol.event;


import info.mineinunity.miuserver.framework.threadsafe.FinalWrapper;
import info.mineinunity.miuserver.util.CodeExecutor;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private static final List<EventAdapter<? extends Event<?>>> registered = new ArrayList<>();

    private static FinalWrapper<EventHandler> logger;

    public static EventHandler getInstance() {
        FinalWrapper<EventHandler> wrapper = logger;

        if (wrapper == null) {
            synchronized (EventHandler.class) {
                if (logger == null) {
                    logger = new FinalWrapper<>(new EventHandler());
                }
                wrapper = logger;
            }
        }
        return wrapper.value;
    }

    private EventHandler() {
    }

    public <T extends Event<T>> void handleEvent(Event<T> e) {
        for (EventAdapter<?> adapter : getEvent(e)) {
            adapter.callEvent(e);
        }
    }

    private <T extends Event<T>> List<EventAdapter<?>> getEvent(Event<T> e) {
        List<EventAdapter<?>> adapterList = new ArrayList<>();
        for (EventAdapter<? extends Event<?>> adapter : registered) {
            if (e.getClass().isInstance(adapter.getType())) {
                adapterList.add(adapter);
            }
        }
        return adapterList;
    }

    public <T extends Event<T>> void register(EventAdapter<T> adapter) {
        registered.add(adapter);
    }

    public <T extends Event<T>> void register(final Event<T> e) {
        EventAdapter<T> adapter = new EventAdapter<>(new CodeExecutor<Event<T>>(e) {
            @Override
            public void runCode(Event<T> event) {
                e.onEvent(event);
            }
        });
    }

    public void unregister(EventAdapter a) {
        if (registered.contains(a))
            registered.remove(a);
    }
}
