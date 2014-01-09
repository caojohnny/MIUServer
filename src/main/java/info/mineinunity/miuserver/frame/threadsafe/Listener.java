package info.mineinunity.miuserver.frame.threadsafe;

import info.mineinunity.miuserver.util.CodeExecutor;

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