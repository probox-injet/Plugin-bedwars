package com.proboxinjet.ashguardall.minigame.bedwars.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public interface GUI
{
    Inventory getInventory();
    String getName();
    GUI handleClick(Player player, ItemStack itemStack, InventoryView view);
    boolean isInventory(InventoryView view);
}
