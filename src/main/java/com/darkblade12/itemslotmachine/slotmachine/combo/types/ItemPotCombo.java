package com.darkblade12.itemslotmachine.slotmachine.combo.types;

import com.darkblade12.itemslotmachine.item.ItemFactory;
import com.darkblade12.itemslotmachine.item.ItemList;
import com.darkblade12.itemslotmachine.slotmachine.combo.Action;
import com.darkblade12.itemslotmachine.slotmachine.combo.Combo;
import org.bukkit.inventory.ItemStack;

public final class ItemPotCombo extends Combo {

    //    #Combos: 279@279@279#ADD_TO_POT_AND_DISTRIBUTE#264-3, 267-1, 19-20
    //    Combos: DIAMOND_AXE@DIAMOND_AXE@DIAMOND_AXE#ADD_TO_POT_AND_DISTRIBUTE#DIAMOND-3, IRON_SWORD-1, SPONGE-20
    //    private static final String FORMAT = "(\\d+|[\\w\\s]+)(-\\d+)?(@(\\d+|[\\w\\s]+)(-\\d+)?){2}#\\w+(#(\\d+|\\w+)(-\\d+){0,2}(, (\\d+|\\w+)(-\\d+){0,2})*)?";
    private static final String FORMAT = "([A-Z_]+)(@[A-Z_]+){2}#\\w+(#([A-Z_]+)(-\\d+){0,2}(, ([A-Z_]+)(-\\d+){0,2})*)?";
    private final ItemList items;

    private ItemPotCombo(ItemStack[] icons, Action action, ItemList items) {
        super(icons, action);
        this.items = items;
    }

    public static ItemPotCombo fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT)) {
            throw new IllegalArgumentException("has an invalid format");
        }
        String[] p = s.split("#");
        String[] i = p[0].split("@");
        if (i.length != 3) {
            throw new IllegalArgumentException("contains an invalid format");
        }
        ItemStack[] icons = new ItemStack[]{ItemFactory.fromString(i[0], false), ItemFactory.fromString(i[1], false), ItemFactory.fromString(i[2], false)};
        Action action = Action.fromName(p[1]);
        if (action == null || !action.isApplicable(ItemPotCombo.class)) {
            throw new IllegalArgumentException("contains an invalid action name");
        }
        ItemList items = null;
        if (action.requiresInput()) {
            try {
                items = ItemList.fromString(p[2]);
            } catch (Exception e) {
                throw new IllegalArgumentException("contains an invalid item list");
            }
        }
        return new ItemPotCombo(icons, Action.fromName(p[1]), items);
    }

    public ItemList getItems() {
        return items.clone();
    }

    @Override
    public String toString() {
        return super.toString() + (action.requiresInput() ? "#" + items.toString() : "");
    }
}
