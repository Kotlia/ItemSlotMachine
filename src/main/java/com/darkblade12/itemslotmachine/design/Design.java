package com.darkblade12.itemslotmachine.design;

import com.darkblade12.itemslotmachine.cuboid.Cuboid;
import com.darkblade12.itemslotmachine.nameable.Nameable;
import com.darkblade12.itemslotmachine.reader.CompressedStringReader;
import com.darkblade12.itemslotmachine.reference.*;
import com.darkblade12.itemslotmachine.settings.Settings;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Design implements Nameable {

    //    private static final String FORMAT = "\\w+#-?\\d+(@-?\\d+){2}(@\\d+){2}(, -?\\d+(@-?\\d+){2}(@\\d+){2})*#-?\\d+(@-?\\d+){2}@(SOUTH|WEST|NORTH|EAST)(, -?\\d+(@-?\\d+){2}@(SOUTH|WEST|NORTH|EAST)){2}(#-?\\d+(@-?\\d+){2}(@\\d+){2}){2}#-?\\d+(@-?\\d+){2}&-?\\d+(@-?\\d+){2}#(SOUTH|WEST|NORTH|EAST)";
    private static final String FORMAT = "\\w+#-?\\d+(@-?\\d+){2}(@[a-z_\\[\\]:=,]+)(~-?\\d+(@-?\\d+){2}(@[a-z_\\[\\]:=,]+))+#-?\\d+(@-?\\d+){2}@(SOUTH|WEST|NORTH|EAST)(~-?\\d+(@-?\\d+){2}@(SOUTH|WEST|NORTH|EAST)){2}(#-?\\d+(@-?\\d+){2}(@[a-z_\\[\\]:=,]+)){2}#-?\\d+(@-?\\d+){2}&-?\\d+(@-?\\d+){2}#(SOUTH|WEST|NORTH|EAST)";
    private final String name;
    private final CompressedStringReader reader;
    private final Set<ReferenceBlock> blocks;
    private final ReferenceItemFrame[] itemFrames;
    private final ReferenceBlock sign;
    private final ReferenceBlock slot;
    private final ReferenceCuboid region;
    private final Direction initialDirection;

    private Design(String name, Set<ReferenceBlock> blocks, ReferenceItemFrame[] itemFrames, ReferenceBlock sign, ReferenceBlock slot, ReferenceCuboid region, Direction initialDirection) {
        this.name = name;
        reader = new CompressedStringReader(name + ".design", "plugins/ItemSlotMachine/designs/");
        this.blocks = blocks;
        this.itemFrames = itemFrames;
        this.sign = sign;
        this.slot = slot;
        this.region = region;
        this.initialDirection = initialDirection;
    }

    public static Design create(Player p, Cuboid c, String name) throws Exception {
        Set<ReferenceBlock> blocks = new HashSet<>();
        ReferenceItemFrame[] itemFrames = new ReferenceItemFrame[3];
        ReferenceBlock sign = null, slot = null;
        ReferenceCuboid region = new ReferenceCuboid(ReferenceLocation.fromBukkitLocation(p, c.getUpperSW()), ReferenceLocation.fromBukkitLocation(p, c.getLowerNE()));
        Direction direction = Direction.get(p);
        int f = 0;
        for (Block b : c) {
            Material m = b.getType();
            if (Tag.SIGNS.isTagged(m) && sign == null) {
                sign = ReferenceBlock.fromBukkitBlock(p, b);
            } else if (m == Material.JUKEBOX && slot == null) {
                slot = ReferenceBlock.fromBukkitBlock(p, b);
            } else if (m != Material.AIR) {
                blocks.add(ReferenceBlock.fromBukkitBlock(p, b));
            } else if (f < 3) {
                ItemFrame i = ItemFrameFinder.find(b.getLocation());
                if (i != null) {
                    itemFrames[f] = ReferenceItemFrame.fromBukkitItemFrame(p, i);
                    f++;
                }
            }
        }
        if (f < 3) {
            int m = 3 - f;
            throw new Exception(m + " item frame" + (m == 1 ? " is" : "s are") + " missing");
        } else if (sign == null) {
            throw new Exception("The sign is missing");
        } else if (slot == null) {
            throw new Exception("The slot is missing (Jukebox block)");
        }
        return new Design(name, blocks, itemFrames, sign, slot, region, direction);
    }

    public static Design fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT)) {
            throw new IllegalArgumentException("Invalid format");
        }
        String[] p = s.split("#");
        Direction initialDirection = Direction.valueOf(p[6]);
        Set<ReferenceBlock> blocks = new HashSet<>();
        for (String b : p[1].split("~")) {
            blocks.add(ReferenceBlock.fromString(b, initialDirection));
        }
        String[] f = p[2].split("~");
        return new Design(p[0], blocks, new ReferenceItemFrame[]{ReferenceItemFrame.fromString(f[0], initialDirection), ReferenceItemFrame.fromString(f[1], initialDirection), ReferenceItemFrame.fromString(f[2], initialDirection)}, ReferenceBlock.fromString(p[3], initialDirection), ReferenceBlock.fromString(p[4], initialDirection), ReferenceCuboid.fromString(p[5]), initialDirection);
    }

    static Design fromFile(String name) throws Exception {
        return fromString(new CompressedStringReader(name + ".design", "plugins/ItemSlotMachine/designs/").readFromFile());
    }

    public void invertItemFrames() {
        ReferenceItemFrame r = itemFrames[0];
        itemFrames[0] = itemFrames[2];
        itemFrames[2] = r;
        saveToFile();
    }

    private final List<Material> AIR = Arrays.asList(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR);

    public void build(Location c, Direction d) throws Exception {
        if (Settings.isSpaceCheckEnabled()) {
            for (Block b : region.getCuboid(c, d)) {
                Material m = b.getType();
                if (!AIR.contains(m) && !Settings.isBlockIgnored(m)) {
                    throw new Exception("There is not enough space for this design");
                }
            }
        }
        for (ReferenceBlock r : blocks) {
            r.place(c, d);
        }
        sign.place(c, d);
        slot.place(c, d);
        for (ReferenceItemFrame r : itemFrames) {
            r.place(c, d);
        }
    }

    public void build(Player p) throws Exception {
        build(p.getLocation(), Direction.get(p));
    }

    public void destruct(Location c, Direction d) {
        for (ReferenceItemFrame r : itemFrames) {
            ItemFrame i = r.getBukkitItemFrame(c, d);
            if (i != null) {
                i.remove();
            }
        }
        sign.getBukkitBlock(c, d).setType(Material.AIR);
        slot.getBukkitBlock(c, d).setType(Material.AIR);
        for (ReferenceBlock r : blocks) {
            r.getBukkitBlock(c, d).setType(Material.AIR);
        }
    }

    void saveToFile() {
        reader.saveToFile(toString());
    }

    void deleteFile() {
        reader.deleteFile();
    }

    @Override
    public String getName() {
        return name;
    }

    public ReferenceItemFrame[] getItemFrames() {
        return itemFrames;
    }

    public ReferenceBlock getSign() {
        return sign;
    }

    public ReferenceBlock getSlot() {
        return slot;
    }

    public ReferenceCuboid getRegion() {
        return region;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(name + "#");
        int i = 0;
        for (ReferenceBlock r : blocks) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(r.toString(false));
            i++;
        }
        s.append("#").append(itemFrames[0].toString(false)).append(", ").append(itemFrames[1].toString(false)).append(", ").append(itemFrames[2].toString(false)).append("#").append(sign.toString(false)).append("#").append(slot.toString(false)).append("#").append(region).append("#").append(initialDirection.name());
        return s.toString();
    }
}
