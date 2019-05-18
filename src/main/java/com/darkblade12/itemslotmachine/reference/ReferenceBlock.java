package com.darkblade12.itemslotmachine.reference;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

public final class ReferenceBlock extends ReferenceLocation {

    //    private static final String FORMAT = "-?\\d+(@-?\\d+){2}(@\\d+){2}@(SOUTH|WEST|NORTH|EAST)";
    private static final String FORMAT = "-?\\d+(@-?\\d+){2}(@[a-z_\\[\\]:=,]+)@(SOUTH|WEST|NORTH|EAST)";
    //    private static final String SECOND_FORMAT = "-?\\d+(@-?\\d+){2}(@\\d+){2}";
    private static final String SECOND_FORMAT = "-?\\d+(@-?\\d+){2}(@[a-z_\\[\\]:=,]+)";
    private final BlockData blockData;
    private final Direction initialDirection;

    ReferenceBlock(int l, int f, int u, BlockData blockData, Direction initialDirection) {
        super(l, f, u);
        this.blockData = blockData;
        this.initialDirection = initialDirection;
    }

    public static ReferenceBlock fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT)) {
            throw new IllegalArgumentException("Invalid format");
        }
        String[] p = s.split("@");
        return new ReferenceBlock(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Bukkit.createBlockData(p[3]), Direction.valueOf(p[4]));
    }

    public static ReferenceBlock fromString(String s, Direction d) throws IllegalArgumentException {
        if (!s.matches(SECOND_FORMAT)) {
            throw new IllegalArgumentException("Invalid format");
        }
        String[] p = s.split("@");
        return new ReferenceBlock(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Bukkit.createBlockData(p[3]), d);
    }

    private static ReferenceBlock fromBukkitBlock(Location c, Direction d, Block b) {
        return ReferenceLocation.fromBukkitLocation(c, d, b.getLocation()).toReferenceBlock(b.getBlockData(), d);
    }

    public static ReferenceBlock fromBukkitBlock(Player p, Block b) {
        return fromBukkitBlock(p.getLocation(), Direction.get(p), b);
    }

    private BlockData rotate(Direction d) {
        BlockData bd = blockData;
        for (int i = 1; i <= initialDirection.getRotations(d); i++) {
            bd = BlockRotator.rotateClockwise90(bd);
        }
        return bd;
    }

    public void place(Location c, Direction d) {
        getBukkitBlock(c, d).setBlockData(rotate(d), false);
    }

    public void place(Player p) {
        place(p.getLocation(), Direction.get(p));
    }

    public BlockData getBlockData() {
        return blockData;
    }

    public Direction getInitialDirection() {
        return initialDirection;
    }

    public String toString(boolean direction) {
        return super.toString() + "@" + blockData.toString() + (direction ? "@" + initialDirection : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public ReferenceBlock clone() {
        return new ReferenceBlock(l, f, u, blockData, initialDirection);
    }
}
