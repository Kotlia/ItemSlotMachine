package com.darkblade12.itemslotmachine.reference;

import com.darkblade12.itemslotmachine.util.ReflectionUtil;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.EntityItemFrame;
import net.minecraft.server.v1_14_R1.EnumDirection;
import net.minecraft.server.v1_14_R1.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

public final class ReferenceItemFrame extends ReferenceLocation {

    private static final String FORMAT = "-?\\d+(@-?\\d+){2}@(SOUTH|WEST|NORTH|EAST)@(SOUTH|WEST|NORTH|EAST)";
    private static final String SECOND_FORMAT = "-?\\d+(@-?\\d+){2}@(SOUTH|WEST|NORTH|EAST)";
    private final Direction initialFacing;
    private final Direction initialDirection;

    ReferenceItemFrame(int l, int f, int u, Direction initialFacing, Direction initialDirection) {
        super(l, f, u);
        this.initialFacing = initialFacing;
        this.initialDirection = initialDirection;
    }

    public static ReferenceItemFrame fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT)) {
            throw new IllegalArgumentException("Invalid format");
        }
        String[] p = s.split("@");
        return new ReferenceItemFrame(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Direction.valueOf(p[3]), Direction.valueOf(p[4]));
    }

    public static ReferenceItemFrame fromString(String s, Direction d) throws IllegalArgumentException {
        if (!s.matches(SECOND_FORMAT)) {
            throw new IllegalArgumentException("Invalid format");
        }
        String[] p = s.split("@");
        return new ReferenceItemFrame(Integer.parseInt(p[0]), Integer.parseInt(p[1]), Integer.parseInt(p[2]), Direction.valueOf(p[3]), d);
    }

    private static ReferenceItemFrame fromBukkitItemFrame(Location c, Direction d, ItemFrame i) {
        return fromBukkitLocation(c, d, i.getLocation()).toReferenceItemFrame(Direction.fromBlockFace(i.getFacing()), d);
    }

    public static ReferenceItemFrame fromBukkitItemFrame(Player p, ItemFrame i) {
        return fromBukkitItemFrame(p.getLocation(), Direction.get(p), i);
    }

    private Direction rotate(Direction d) {
        Direction facing = initialFacing;
        for (int i = 1; i <= initialDirection.getRotations(d); i++) {
            facing = facing.getNextDirection();
        }
        return facing;
    }

    public void place(Location c, Direction d) {
        Location l = getBukkitLocation(c, d);
        org.bukkit.World w = l.getWorld();
        try {
            World world = (World) ReflectionUtil.invokeMethod("getHandle", w.getClass(), w);
            BlockPosition position = new BlockPosition(l.getX(), l.getY(), l.getZ());
            EnumDirection direction = EnumDirection.valueOf(rotate(d).name());
            EntityItemFrame frame = new EntityItemFrame(world, position, direction);
            world.addEntity(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void place(Player p) {
        place(p.getLocation(), Direction.get(p));
    }

    public Direction getInitialFacing() {
        return initialFacing;
    }

    public Direction getInitialDirection() {
        return initialDirection;
    }

    public Block getAttachedBlock(Location c, Direction d) {
        return getBukkitBlock(c, d).getRelative(rotate(d).getOppositeDirection().toBlockFace());
    }

    public ItemFrame getBukkitItemFrame(Location c, Direction d) {
        return ItemFrameFinder.find(getBukkitLocation(c, d));
    }

    public ItemFrame getBukkitItemFrame(Player p) {
        return getBukkitItemFrame(p.getLocation(), Direction.get(p));
    }

    public String toString(boolean direction) {
        return super.toString() + "@" + initialFacing.name() + (direction ? "@" + initialDirection : "");
    }

    @Override
    public String toString() {
        return toString(true);
    }

    @Override
    public ReferenceItemFrame clone() {
        return new ReferenceItemFrame(l, f, u, initialFacing, initialDirection);
    }
}
