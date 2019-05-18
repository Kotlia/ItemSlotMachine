package com.darkblade12.itemslotmachine.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public final class ItemFactory {

    private static final String FORMAT = "([0-9A-Z_]+)(-\\d+)?";

    private ItemFactory() {
    }

    public static ItemStack fromString(String s, boolean amount) throws IllegalArgumentException {
        if (!s.matches(FORMAT)) {
            throw new IllegalArgumentException("has an invalid format");
        }
        String[] i = s.split("-");
        Material m;
        try {
            m = Material.valueOf(i[0]);
        } catch (Exception e) {
            m = Material.matchMaterial(i[0]);
        }
        if (m == null) {
            throw new IllegalArgumentException("contains an invalid item name");
        }
        return new ItemStack(m, 1);
    }

    public static ItemStack fromString(String s) {
        return fromString(s, true);
    }

    public static String toString(ItemStack i) {
        return i.getType().toString();
    }

    private static ItemStack setName(ItemStack i, String name) {
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        i.setItemMeta(meta);
        return i;
    }

    private static ItemStack setLore(ItemStack i, List<String> lore) {
        ItemMeta meta = i.getItemMeta();
        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }

    private static ItemStack setNameAndLore(ItemStack i, String name, List<String> lore) {
        return setLore(setName(i, name), lore);
    }

    public static ItemStack setNameAndLore(ItemStack i, String name, String... lore) {
        return setNameAndLore(i, name, Arrays.asList(lore));
    }
}
