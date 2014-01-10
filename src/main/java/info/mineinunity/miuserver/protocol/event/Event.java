package info.mineinunity.miuserver.protocol.event;

public interface Event<T> {
    public void onEvent(Event<? extends Event<?>> e);

    public Event<?> getType();
}
