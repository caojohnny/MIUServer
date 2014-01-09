package info.mineinunity.miuserver.api.block;

import info.mineinunity.miuserver.api.Location;
import info.mineinunity.miuserver.api.Material;

public class Block {

    private final Location loc;
    private final Material m;

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
