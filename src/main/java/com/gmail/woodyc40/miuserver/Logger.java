package com.gmail.woodyc40.miuserver;

import com.gmail.woodyc40.miuserver.frame.threadsafe.FinalWrapper;

public class Logger {
    private static FinalWrapper<Logger> logger;

    public static Logger getInstance() {
        FinalWrapper<Logger> wrapper = logger;
        Logger log = new Logger();

        if (wrapper == null) {
            synchronized(log) {
                if (logger == null) {
                    logger = new FinalWrapper<>(log);
                }
                wrapper = logger;
            }
        }
        return wrapper.value;
    }

    private Logger() { }

    public void logError(String message, Throwable throwable) {
        System.out.print("---------- ERROR ----------");
        System.out.print(throwable.getCause() + ": " + message);
        throwable.printStackTrace();
        System.out.print("---- END ERROR REPORT -----");
    }

}
