package com.gmail.woodyc40.miuserver;

import com.gmail.woodyc40.miuserver.frame.threadsafe.FinalWrapper;

public class Logger {
    private static FinalWrapper<Logger> logger;

    public static Logger getInstance() {
        FinalWrapper<Logger> wrapper = logger;

        if (wrapper == null) {
            synchronized(Logger.class) {
                if (logger == null) {
                    logger = new FinalWrapper<>(new Logger());
                }
                wrapper = logger;
            }
        }
        return wrapper.value;
    }

    private Logger() { }

    public void logError(String message, Throwable throwable) {
        System.out.println("---------- ERROR ----------");
        System.out.println(throwable.getMessage() + " - " + throwable.getCause() + ": " + message + "\n");
        throwable.printStackTrace();
        System.out.println("---- END ERROR REPORT -----");
    }

    public void log(String message) {
        System.out.println("[INFO]" + message);
    }

}
