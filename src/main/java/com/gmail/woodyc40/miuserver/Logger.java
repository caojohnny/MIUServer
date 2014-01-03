package com.gmail.woodyc40.miuserver;


public class Logger {

    private volatile Logger log = null;

    public Logger getInstance() {
        if (log == null) {
            synchronized(this) {
                if(log == null)
                   log = new Logger();
            }
        }
        return log;
    }

    public void logError(String message, Throwable throwable) {
        System.out.print("---------- ERROR ----------");
        System.out.print(throwable.getCause() + ": " + message);
        throwable.printStackTrace();
        System.out.print("---- END ERROR REPORT -----");
    }

}
