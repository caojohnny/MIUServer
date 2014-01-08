package com.gmail.woodyc40.miuserver.protocol.event;


import com.gmail.woodyc40.miuserver.frame.threadsafe.FinalWrapper;
import com.gmail.woodyc40.miuserver.protocol.Packet;
import com.gmail.woodyc40.miuserver.util.CodeExecutor;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    static List<EventAdapter> registered = new ArrayList<>();

    private static FinalWrapper<EventHandler> logger;

    public static EventHandler getInstance() {
        FinalWrapper<EventHandler> wrapper = logger;

        if (wrapper == null) {
            synchronized(EventHandler.class) {
                if (logger == null) {
                    logger = new FinalWrapper<>(new EventHandler());
                }
                wrapper = logger;
            }
        }
        return wrapper.value;
    }

    private EventHandler() { }

    public void handleEvent(Packet p) {
        for(EventAdapter adapter : getEvent(p)) {
            adapter.callEvent(p);
        }
    }

    private List<EventAdapter> getEvent(Packet p) {
        List<EventAdapter> adapterList = new ArrayList<>();
        for(EventAdapter adapter : registered) {
            if(adapter.getType() == p.getType()) {
                adapterList.add(adapter);
            }
        }
        return adapterList;
    }

    public static void register(EventAdapter adapter) {
        registered.add(adapter);
    }

    public static void register(final Event e) {
        EventAdapter adapter = new EventAdapter(new CodeExecutor<Event>() {
            @Override
            public void runCode(Event event) {
                if(event instanceof e) {
                    event.onEvent();
                }
            }
        });
    }

}
