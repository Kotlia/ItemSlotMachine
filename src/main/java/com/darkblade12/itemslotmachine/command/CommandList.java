package com.darkblade12.itemslotmachine.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandList extends ArrayList<ICommand> {

    private static final long serialVersionUID = -3228460490008738846L;
    private final Map<String, ICommand> map;

    CommandList() {
        super();
        map = new HashMap<>();
    }

    public CommandList(Collection<ICommand> c) {
        this();
        addAll(c);
    }

    @Override
    public boolean add(ICommand e) {
        CommandDetails c = getDetails(e);
        if (c != null) {
            map.put(c.name(), e);
            return super.add(e);
        }
        return false;
    }

    public ICommand get(String name) {
        return map.get(name.toLowerCase());
    }

    static CommandDetails getDetails(ICommand i) {
        return i.getClass().getAnnotation(CommandDetails.class);
    }
}
