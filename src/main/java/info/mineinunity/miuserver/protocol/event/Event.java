package info.mineinunity.miuserver.protocol.event;

public interface Event<T> {
    public void onEvent(T e);

    public Event<?> getType();
}
