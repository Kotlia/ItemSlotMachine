package com.darkblade12.itemslotmachine.slotmachine.sound;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public final class SoundList extends ArrayList<SoundData> {

    private static final long serialVersionUID = 2932421195026187475L;
    private static final String FORMAT = "[A-Z_]+(-\\d+(\\.\\d+)?){2}(, [A-Z_]+(-\\d+(\\.\\d+)?){2})*";

    private SoundList() {
        super();
    }

    public static SoundList fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT)) {
            throw new IllegalArgumentException("has an invalid format");
        }
        SoundList list = new SoundList();
        for (String d : s.split(", ")) {
            list.add(SoundData.fromString(d));
        }
        return list;
    }

    public void play(Location l) {
        for (SoundData soundData : this) {
            soundData.play(l);
        }
    }

    public void play(Player p, Location l) {
        for (SoundData soundData : this) {
            soundData.play(p, l);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (SoundData soundData : this) {
            if (s.length() > 0) {
                s.append(", ");
            }
            s.append(soundData.toString());
        }
        return s.toString();
    }
}
