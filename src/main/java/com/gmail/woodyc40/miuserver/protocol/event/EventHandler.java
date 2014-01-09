package com.gmail.woodyc40.miuserver.protocol.event;


import com.gmail.woodyc40.miuserver.frame.threadsafe.FinalWrapper;
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

    public void handleEvent(Event<? extends Event<?>> e) {
        for(EventAdapter adapter : getEvent(e)) {
            adapter.callEvent(e);
        }
    }

    private List<EventAdapter> getEvent(Event<?> e) {
        List<EventAdapter> adapterList = new ArrayList<>();
        for(EventAdapter adapter : registered) {
            if(adapter.getType() == e.getType()) {
                adapterList.add(adapter);
            }
        }
        return adapterList;
    }

    public void register(EventAdapter adapter) {
        registered.add(adapter);
    }

    public void register(final Event<Event<?>> e) {
        EventAdapter adapter = new EventAdapter(new CodeExecutor<Event<? extends Event<?>>>(e) {
            @Override
            public void runCode(Event<? extends Event<?>> event) {
                e.onEvent(event);
            }
        });
    }

}
