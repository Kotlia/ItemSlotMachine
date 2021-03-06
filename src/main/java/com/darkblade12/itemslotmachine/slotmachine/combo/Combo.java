package com.darkblade12.itemslotmachine.slotmachine.combo;

import com.darkblade12.itemslotmachine.item.ItemFactory;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class Combo {

    private final ItemStack[] icons;
    protected Action action;

    protected Combo(ItemStack[] icons, Action action) {
        if (icons.length != 3) {
            throw new IllegalArgumentException("The icons array has an invalid length (not 3)");
        }
        this.icons = icons;
        this.action = action;
    }

    public ItemStack[] getIcons() {
        return icons;
    }

    public boolean isActivated(ItemStack... display) {
        for (int i = 0; i < 3; i++) {
            if (!display[i].isSimilar(icons[i]) && icons[i].getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }

    boolean hasHighPriority() {
        for (ItemStack i : icons) {
            if (i.getType() == Material.AIR) {
                return false;
            }
        }
        return true;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return ItemFactory.toString(icons[0]) + "@" + ItemFactory.toString(icons[1]) + "@" + ItemFactory.toString(icons[2]) + "#" + action.name();
    }
}
