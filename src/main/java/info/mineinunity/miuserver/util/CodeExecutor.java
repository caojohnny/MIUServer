package info.mineinunity.miuserver.util;

public abstract class CodeExecutor<T> {
    private final T t;

    public abstract void runCode(T t);

    public CodeExecutor(T t) {
        this.t = t;
    }

    public T getType() {
        return t;
    }
}
