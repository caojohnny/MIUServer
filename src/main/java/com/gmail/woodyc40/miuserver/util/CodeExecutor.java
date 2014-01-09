package com.gmail.woodyc40.miuserver.util;

public abstract class CodeExecutor<T> {
    T t;

    public abstract void runCode(T t);

    public CodeExecutor(T t) {
        this.t = t;
    }

    public T getType() {
        return t;
    }
}
