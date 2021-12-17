package com.proboxinjet.ashguardall.minigame.bedwars.gui;

import com.proboxinjet.ashguardall.minigame.bedwars.ItemBuilder;
import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameManager;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.TeamColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class SetupWizardIslandSelectorGUI implements GUI
{
    private GameManager gameManager;
    private Inventory inventory;

    public SetupWizardIslandSelectorGUI(GameManager gameManager)
    {
        this.gameManager = gameManager;

        inventory = Bukkit.createInventory(null, 9, getName());

        for(TeamColor color : TeamColor.values())
        {
            inventory.addItem(new ItemBuilder(color.woolMaterial()).setName(color.getChatColor() + color.formattedName()).toItemStack());
        }

        inventory.addItem(new ItemBuilder(Material.BARRIER).setName("§aExit").toItemStack());
    }

    @Override
    public Inventory getInventory()
    {
        return inventory;
    }

    @Override
    public String getName()
    {
        return "Select Island";
    }

    @Override
    public GUI handleClick(Player player, ItemStack itemStack, InventoryView view)
    {
        if(!gameManager.getSetupWizardManager().isInWizard(player))
        {
            return null;
        }

        TeamColor clickedColor = null;

        String itemName = ChatColor.stripColor(itemStack.getItemMeta().getDisplayName());

        for(TeamColor color : TeamColor.values())
        {
            if(itemName.equalsIgnoreCase(color.formattedName()))
            {
                clickedColor = color;
                break;
            }
        }

        if(clickedColor != null)
        {
            gameManager.getSetupWizardManager().teamSetupWizard(player, clickedColor);
        }
        else
        {
            gameManager.getSetupWizardManager().worldSetUpWizard(player, gameManager.getSetupWizardManager().getWorld(player));
        }
        return null;
    }

    @Override
    public boolean isInventory(InventoryView view)
    {
        return view.getTitle().equals(getName());
    }
}
