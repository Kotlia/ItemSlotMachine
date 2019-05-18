package com.darkblade12.itemslotmachine.reference;

import com.darkblade12.itemslotmachine.cuboid.Cuboid;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class ReferenceCuboid {

    private static final String FORMAT = "-?\\d+(@-?\\d+){2}&-?\\d+(@-?\\d+){2}";
    private final ReferenceLocation firstLocation;
    private final ReferenceLocation secondLocation;

    public ReferenceCuboid(ReferenceLocation firstLocation, ReferenceLocation secondLocation) {
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
    }

    public static ReferenceCuboid fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT)) {
            throw new IllegalArgumentException("Invalid format");
        }
        String[] p = s.split("&");
        return new ReferenceCuboid(ReferenceLocation.fromString(p[0]), ReferenceLocation.fromString(p[1]));
    }

    public Cuboid getCuboid(Location c, Direction d) {
        return new Cuboid(firstLocation.getBukkitLocation(c, d), secondLocation.getBukkitLocation(c, d));
    }

    @Override
    public String toString() {
        return firstLocation + "&" + secondLocation;
    }
}
