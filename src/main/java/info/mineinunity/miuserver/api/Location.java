package info.mineinunity.miuserver.api;

import java.io.Serializable;

public class Location implements Serializable {

    private static final long serialVersionUID = -6970657521343949284L;

    private final double x;
    private final double y;
    private final double z;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

}
