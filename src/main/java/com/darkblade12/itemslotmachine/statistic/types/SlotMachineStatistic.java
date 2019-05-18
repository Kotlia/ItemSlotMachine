package com.darkblade12.itemslotmachine.statistic.types;

import com.darkblade12.itemslotmachine.nameable.Nameable;
import com.darkblade12.itemslotmachine.reader.CompressedStringReader;
import com.darkblade12.itemslotmachine.statistic.Statistic;
import com.darkblade12.itemslotmachine.statistic.Type;

public final class SlotMachineStatistic extends Statistic implements Nameable {

    private final String name;
    private final CompressedStringReader reader;

    private SlotMachineStatistic(String name) {
        super(Type.TOTAL_SPINS, Type.WON_SPINS, Type.LOST_SPINS);
        this.name = name;
        reader = new CompressedStringReader(name + ".statistic", "plugins/ItemSlotMachine/statistics/slot machine/");
    }

    public static SlotMachineStatistic fromFile(String name) throws Exception {
        SlotMachineStatistic s = new SlotMachineStatistic(name);
        s.loadStatistic();
        return s;
    }

    private void loadStatistic() throws Exception {
        if (reader.getOuputFile().exists()) {
            loadStatistic(reader.readFromFile());
        } else {
            saveToFile();
        }
    }

    public void saveToFile() {
        reader.saveToFile(toString());
    }

    public void deleteFile() {
        reader.deleteFile();
    }

    @Override
    public String getName() {
        return name;
    }
}
