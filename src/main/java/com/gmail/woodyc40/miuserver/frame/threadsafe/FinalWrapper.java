package com.gmail.woodyc40.miuserver.frame.threadsafe;

public static class FinalWrapper<T> {
    public final T value;
    
    public FinalWrapper(T value) {
        this.value = value;
    }
}
