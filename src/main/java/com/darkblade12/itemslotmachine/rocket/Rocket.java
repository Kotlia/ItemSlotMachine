package com.darkblade12.itemslotmachine.rocket;

import com.darkblade12.itemslotmachine.ItemSlotMachine;
import com.darkblade12.itemslotmachine.reader.CompressedStringReader;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rocket {

    private static final Random RANDOM = new Random();
    private static final Color[] COLORS;
    private final FireworkMeta meta;

    static {
        DyeColor[] values = DyeColor.values();
        COLORS = new Color[values.length];
        for (int i = 0; i < values.length; i++) {
            COLORS[i] = values[i].getFireworkColor();
        }
    }

    private Rocket(FireworkMeta meta, boolean isClean) {
        if (isClean) {
            this.meta = meta;
        } else {
            this.meta = getCleanMeta();
            this.meta.setPower(meta.getPower());
            this.meta.addEffects(meta.getEffects());
        }
    }

    public static Rocket randomize() {
        FireworkMeta meta = getCleanMeta();
        meta.setPower(RANDOM.nextInt(3) + 1);
        meta.addEffects(randomizeEffects(1, 3, 1, 5));
        return new Rocket(meta, true);
    }

    private static Rocket fromString(String s) {
        try {
            FireworkMeta meta = getCleanMeta();
            String[] p = s.split("@");
            meta.setPower(Integer.parseInt(p[0]));
            String[] e = p[1].split("#");
            for (String effect : e) {
                String[] d = effect.split(",");
                String[] c = d[3].split("~");
                List<Color> colors = new ArrayList<>();
                List<Color> fadeColors = new ArrayList<>();
                for (int i = 0; i < c.length; i++) {
                    for (String rgb : c[i].split("-")) {
                        Color col = Color.fromRGB(Integer.parseInt(rgb));
                        if (i == 0) {
                            colors.add(col);
                        } else {
                            fadeColors.add(col);
                        }
                    }
                }
                meta.addEffect(FireworkEffect.builder().flicker(Boolean.parseBoolean(d[0])).trail(Boolean.parseBoolean(d[1])).with(Type.valueOf(d[2])).withColor(colors).withFade(fadeColors).build());
            }
            return new Rocket(meta, true);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Rocket format");
        }
    }

    public static Rocket fromFile(String name) throws Exception {
        return fromString(new CompressedStringReader(name + ".rckt", "plugins/UltimateRockets/rockets/").readFromFile());
    }

    private static int calculateRandom(int min, int max) throws IllegalArgumentException {
        if (min < 0) {
            throw new IllegalArgumentException("Min value can not be lower than 0");
        } else if (max < 1) {
            throw new IllegalArgumentException("Min value can not be lower than 1");
        } else if (max < min) {
            throw new IllegalArgumentException("Max value can not be lower than min value");
        }
        return min == max ? min : RANDOM.nextInt((max - min) + 1) + min;
    }

    private static List<Color> randomizeColors(int min, int max) {
        List<Color> colors = new ArrayList<>();
        for (int a = 1; a <= calculateRandom(min, max); a++) {
            Color c = COLORS[RANDOM.nextInt(COLORS.length)];
            if (!colors.contains(c)) {
                colors.add(c);
            }
        }
        return colors;
    }

    private static List<FireworkEffect> randomizeEffects(int min, int max, int minColors, int maxColors) {
        List<FireworkEffect> effects = new ArrayList<>();
        for (int a = 1; a <= calculateRandom(min, max); a++) {
            effects.add(FireworkEffect.builder().flicker(RANDOM.nextBoolean()).with(Type.values()[RANDOM.nextInt(Type.values().length)]).trail(RANDOM.nextBoolean()).withColor(randomizeColors(minColors, maxColors)).withFade(randomizeColors(minColors, maxColors)).build());
        }
        return effects;
    }

    private Firework launch(Location l) {
        Firework f = l.getWorld().spawn(l, Firework.class);
        f.setFireworkMeta(meta);
        return f;
    }

    public void displayEffects(ItemSlotMachine plugin, Location l) {
        Firework f = launch(l);
        new BukkitRunnable() {
            @Override
            public void run() {
                f.detonate();
            }
        }.runTaskLater(plugin, 1);
    }

    private static FireworkMeta getCleanMeta() {
        return (FireworkMeta) new ItemStack(Material.FIREWORK_ROCKET).getItemMeta();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(meta.getPower() + "@");
        int e = 1;
        for (FireworkEffect f : meta.getEffects()) {
            s.append(f.hasFlicker()).append(",").append(f.hasTrail()).append(",").append(f.getType().name()).append(",");
            int a = 1;
            for (Color c : f.getColors()) {
                s.append(c.asRGB()).append(a == f.getColors().size() ? f.getFadeColors().size() > 0 ? "~" : "" : "-");
                a++;
            }
            int b = 1;
            for (Color c : f.getFadeColors()) {
                s.append(c.asRGB()).append(b == f.getFadeColors().size() ? "" : "-");
                b++;
            }
            if (e != meta.getEffectsSize()) {
                s.append("#");
            }
            e++;
        }
        return s.toString();
    }
}
