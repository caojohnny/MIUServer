package com.gmail.woodyc40.miuserver.api.block;

import com.gmail.woodyc40.miuserver.api.Location;
import com.gmail.woodyc40.miuserver.api.Material;

public class Block {

    Location loc;
    Material m;

    public Block(Location loc, Material m) {
        this.loc = loc;
        this.m = m;
    }

    public Location getLocation() {
        return loc;
    }

    public Material getType() {
        return m;
    }

}
