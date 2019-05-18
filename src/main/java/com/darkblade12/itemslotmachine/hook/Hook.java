package com.darkblade12.itemslotmachine.hook;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Hook<P extends JavaPlugin> {

    private P plugin;
    private static boolean ENABLED;

    @SuppressWarnings("unchecked")
    public boolean load() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(getPluginName());
        if (plugin != null) {
            this.plugin = (P) plugin;
            ENABLED = initialize();
        }
        return plugin != null && ENABLED;
    }

    protected abstract boolean initialize();

    protected abstract String getPluginName();

    public P getPlugin() {
        return plugin;
    }

    public static boolean isEnabled() {
        return ENABLED;
    }
}
