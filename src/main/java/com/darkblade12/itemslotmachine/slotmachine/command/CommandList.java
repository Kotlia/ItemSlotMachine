package com.darkblade12.itemslotmachine.slotmachine.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collection;

public final class CommandList extends ArrayList<String> implements Cloneable {

    private static final long serialVersionUID = -6972409535891307954L;
    private static final String FORMAT = ".+(;.+)*";

    private CommandList() {
        super();
    }

    private CommandList(Collection<String> c) {
        super(c);
    }

    public static CommandList fromString(String s) throws IllegalArgumentException {
        if (!s.matches(FORMAT)) {
            throw new IllegalArgumentException("has an invalid format");
        }
        CommandList list = new CommandList();
        for (String c : s.split(";")) {
            list.add(c.startsWith("/") ? c.substring(1) : c);
        }
        return list;
    }

    private void execute(CommandSender sender, Placeholder... placeholders) {
        for (String command : this) {
            for (Placeholder p : placeholders) {
                command = p.replace(command);
            }
            Bukkit.dispatchCommand(sender, command);
        }
    }

    public void execute(Placeholder... placeholders) {
        execute(Bukkit.getConsoleSender(), placeholders);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String value : this) {
            if (s.length() > 0) {
                s.append(";");
            }
            s.append(value);
        }
        return s.toString();
    }

    @Override
    public CommandList clone() {
        CommandList strings = (CommandList) super.clone();
        return new CommandList(this);
    }
}
