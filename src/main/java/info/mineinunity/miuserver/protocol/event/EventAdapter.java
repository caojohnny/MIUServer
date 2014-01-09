package info.mineinunity.miuserver.protocol.event;

import info.mineinunity.miuserver.util.CodeExecutor;

class EventAdapter {
    private final CodeExecutor<Event<? extends Event<?>>> ce;

    public EventAdapter(CodeExecutor<Event<? extends Event<?>>> ce) {
        this.ce = ce;
        EventHandler.getInstance().register(this);
    }

    public Event<? extends Event<?>> getType() {
        return ce.getType();
    }

    public void callEvent(Event<? extends Event<?>> event) {
        ce.runCode(event);
    }

}
