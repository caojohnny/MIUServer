package info.mineinunity.miuserver.frame.threadsafe;

public class FinalWrapper<T> {
    public final T value;

    public FinalWrapper(T value) {
        this.value = value;
    }
}
