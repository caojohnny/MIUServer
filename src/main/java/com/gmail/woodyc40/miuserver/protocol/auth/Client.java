package com.gmail.woodyc40.miuserver.protocol.auth;

import java.io.Serializable;

public class Client implements Serializable {
    private static final long serialVersionUID = -7045913813827343817L;

    String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
