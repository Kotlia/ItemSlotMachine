package com.darkblade12.itemslotmachine;

import com.darkblade12.itemslotmachine.coin.CoinManager;
import com.darkblade12.itemslotmachine.command.coin.CoinCommandHandler;
import com.darkblade12.itemslotmachine.command.design.DesignCommandHandler;
import com.darkblade12.itemslotmachine.command.slot.SlotCommandHandler;
import com.darkblade12.itemslotmachine.command.statistic.StatisticCommandHandler;
import com.darkblade12.itemslotmachine.design.DesignManager;
import com.darkblade12.itemslotmachine.hook.VaultHook;
import com.darkblade12.itemslotmachine.message.MessageManager;
import com.darkblade12.itemslotmachine.metrics.MetricsLite;
import com.darkblade12.itemslotmachine.reader.TemplateReader;
import com.darkblade12.itemslotmachine.settings.Settings;
import com.darkblade12.itemslotmachine.slotmachine.SlotMachineManager;
import com.darkblade12.itemslotmachine.statistic.StatisticManager;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class ItemSlotMachine extends JavaPlugin {

    //    public static final String MASTER_PERMISSION = "ItemSlotMachine.*";
    public Logger l;
    private Settings settings;
    public TemplateReader template;
    private VaultHook vaultHook;
    public MessageManager messageManager;
    public DesignManager designManager;
    public DesignCommandHandler designCommandHandler;
    public CoinManager coinManager;
    public SlotMachineManager slotMachineManager;
    public StatisticManager statisticManager;
    public CoinCommandHandler coinCommandHandler;
    public SlotCommandHandler slotCommandHandler;
    public StatisticCommandHandler statisticCommandHandler;

    @Override
    public void onEnable() {
        long check = System.currentTimeMillis();
        l = getLogger();
        settings = new Settings(this);
        try {
            //saveResource("lang_DE.txt",false);
            //saveResource("lang_EN.txt",false);
            //saveResource("lang_JP.txt",false);
            settings.load();
        } catch (Exception e) {
            l.warning("An error occurred while loading the settings from config.yml, plugin will disable! Cause: " + e.getMessage());
            if (Settings.isDebugModeEnabled()) {
                e.printStackTrace();
            }
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        template = new TemplateReader(this, "template.yml", "plugins/ItemSlotMachine/");
        if (!template.readTemplate()) {
            l.warning("Failed to read template.yml, plugin will disable!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        vaultHook = new VaultHook();
        if (vaultHook.load()) {
            l.info("Vault hooked, money distribution is active.");
        }
        messageManager = new MessageManager(this);
        if (!messageManager.onInitialize()) {
            return;
        }
        designManager = new DesignManager(this);
        coinManager = new CoinManager(this);
        slotMachineManager = new SlotMachineManager(this);
        statisticManager = new StatisticManager(this);
        designCommandHandler = new DesignCommandHandler(this);
        coinCommandHandler = new CoinCommandHandler(this);
        slotCommandHandler = new SlotCommandHandler(this);
        statisticCommandHandler = new StatisticCommandHandler(this);
        enableMetrics();
        check = System.currentTimeMillis() - check;
        l.info("Gambling system version " + getDescription().getVersion() + " activated! Have fun ;D (" + check + " ms)");
    }

    @Override
    public void onDisable() {
        if (slotMachineManager != null) {
            slotMachineManager.onDisable();
        }
        l.info("Gambling system deactivated!");
    }

    public void onReload() {
        try {
            settings.reload();
        } catch (Exception e) {
            l.warning("An error occurred while loading the settings from config.yml, plugin will disable! Cause: " + e.getMessage());
            if (Settings.isDebugModeEnabled()) {
                e.printStackTrace();
            }
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (!messageManager.onReload()) {
            return;
        }
        designManager.onReload();
        coinManager.onReload();
        slotMachineManager.onReload();
    }

    public Configuration loadConfig() {
        if (new File("plugins/" + getName() + "/config.yml").exists()) {
            l.info("config.yml successfully loaded.");
        } else {
            saveDefaultConfig();
        }
        return getConfig();
    }

    private void enableMetrics() {
        try {
            MetricsLite m = new MetricsLite(this);
            if (m.isOptOut()) {
                l.warning("Metrics are disabled!");
            } else {
                l.info("This plugin is using Metrics by Hidendra!");
                m.start();
            }
        } catch (Exception e) {
            l.info("An error occurred while enabling Metrics!");
            if (Settings.isDebugModeEnabled()) {
                e.printStackTrace();
            }
        }
    }
}
