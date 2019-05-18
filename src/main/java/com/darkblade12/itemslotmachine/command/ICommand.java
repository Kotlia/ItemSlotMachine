package com.darkblade12.itemslotmachine.command;

import com.darkblade12.itemslotmachine.ItemSlotMachine;
import org.bukkit.command.CommandSender;

public interface ICommand {

    void execute(ItemSlotMachine plugin, CommandSender sender, String label, String[] params);
}
