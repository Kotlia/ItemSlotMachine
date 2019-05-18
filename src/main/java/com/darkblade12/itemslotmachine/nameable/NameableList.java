package com.darkblade12.itemslotmachine.nameable;

import java.util.ArrayList;
import java.util.Collection;

public final class NameableList<T extends Nameable> extends ArrayList<T> {

    private static final long serialVersionUID = 2132464611329949798L;
    private final boolean ignoreCase;

    public NameableList(boolean ignoreCase) {
        super();
        this.ignoreCase = ignoreCase;
    }

    public NameableList() {
        this(false);
    }

    public NameableList(Collection<T> c) {
        super(c);
        ignoreCase = false;
    }

    public void remove(String name) {
        for (int i = 0; i < size(); i++) {
            String n = get(i).getName();
            if (ignoreCase ? name.equalsIgnoreCase(n) : name.equals(n)) {
                remove(i);
            }
        }
    }

    public T get(String name) {
        for (T e : this) {
            String n = e.getName();
            if (ignoreCase ? name.equalsIgnoreCase(n) : name.equals(n)) {
                return e;
            }
        }
        return null;
    }

    public boolean contains(String name) {
        return get(name) != null;
    }

    public String toString(String separator) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (s.length() > 0) {
                s.append(separator);
            }
            s.append(get(i).toString());
        }
        return s.toString();
    }
}
