package com.gmail.woodyc40.miuserver.frame.threadsafe;

import com.gmail.woodyc40.miuserver.util.CodeExecutor;

public class Listener extends Thread {
    CodeExecutor<Boolean> ce;
    public Listener(CodeExecutor<Boolean> ce) {
        this.ce = ce;
    }

    @Override
    public void run() {
        ce.runCode(true);
    }

}