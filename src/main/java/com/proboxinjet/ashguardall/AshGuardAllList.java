package com.proboxinjet.ashguardall;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AshGuardAllList implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassM = compass.getItemMeta();
        compassM.setDisplayName("§cMenu de Navigation");
        compass.setItemMeta(compassM);
        player.getInventory().setItem(8, compass);
        player.updateInventory();
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        Player player = e.getPlayer();
        ItemStack it = e.getItem();

        if(it == null) return;

        if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§cMenu de Navigation"))
        {
            Inventory inv = Bukkit.createInventory(null, 36, "§8Menu");

            for(int count = 0; count < 36; count++)
            {
                ItemStack glassPain = new ItemStack(Material.LIME_STAINED_GLASS_PANE, 1);
                ItemMeta glassPainM = glassPain.getItemMeta();
                glassPainM.setDisplayName("§2");
                glassPain.setItemMeta(glassPainM);
                inv.setItem(count, glassPain);
            }
            inv.setItem(10, getItem(Material.BOOKSHELF, "§2Enchanting"));
            inv.setItem(19, getItem(Material.ANVIL, "§2Anvil"));
            inv.setItem(12, getItem(Material.CRAFTING_TABLE, "§2Craft"));
            inv.setItem(21, getItem(Material.FURNACE, "§2Furnace"));
            inv.setItem(13, getItem(Material.BREWING_STAND, "§2Potion"));
            inv.setItem(22, getItem(Material.CAULDRON, "§2Trash"));
            inv.setItem(26, getItem(Material.SCAFFOLDING, "§2Amelioration/repair"));
            inv.setItem(7, getItem(Material.OBSIDIAN, "§2Nether"));
            inv.setItem(8, getItem(Material.END_PORTAL_FRAME, "§2End"));
            inv.setItem(17, getItem(Material.COMMAND_BLOCK, "§2Command"));
            player.openInventory(inv);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        String inv = e.getView().getTitle();
        Player player = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();
        if(current == null) return;

        if(inv.equalsIgnoreCase("§8Menu"))
        {
            e.setCancelled(true);
            Inventory invT = Bukkit.createInventory(null, 36, "§8Trash");
            Inventory invCmd = Bukkit.createInventory(null, 9, "§8Command");

            switch(current.getType())
            {
                case CAULDRON:
                    player.closeInventory();
                    player.openInventory(invT);
                    break;
                case COMMAND_BLOCK:
                    player.closeInventory();
                    invCmd.setItem(2, getItem(Material.BARRIER, "§8Spéctacteur"));
                    invCmd.setItem(3, getItem(Material.COMMAND_BLOCK, "§8Créatif"));
                    invCmd.setItem(4, getItem(Material.ARROW, "§8Return"));
                    invCmd.setItem(5, getItem(Material.DIAMOND_SWORD, "§8Survie"));
                    invCmd.setItem(6, getItem(Material.DIAMOND_CHESTPLATE, "§8Adventure"));
                    player.openInventory(invCmd);
                    break;
                default:
                    break;
            }
        }

        if(inv.equalsIgnoreCase("§8Command"))
        {
            e.setCancelled(true);
            player.closeInventory();

            switch (current.getType())
            {
                case BARRIER:
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("§2Mode spéctateur activé !");
                    break;
                case COMMAND_BLOCK:
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("§2Mode créatif activé !");
                    break;
                case DIAMOND_SWORD:
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("§2Mode survie activé !");
                    break;
                case DIAMOND_CHESTPLATE:
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage("§2Mode adventure activé !");
                    break;
                default:
                    break;
            }
        }
    }

    public ItemStack getItem(Material mat, String customName)
    {
        ItemStack it = new ItemStack(mat, 1);
        ItemMeta itM = it.getItemMeta();
        if(customName != null) itM.setDisplayName(customName);
        it.setItemMeta(itM);
        return it;
    }
}
