package com.proboxinjet.ashguardall.minigame.bedwars.events;

import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameManager;
import com.proboxinjet.ashguardall.minigame.bedwars.gui.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClickListener implements Listener
{
    private GameManager gameManager;

    public InventoryClickListener(GameManager gameManager)
    {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if(e.getCurrentItem() == null) return;
        if(!e.getCurrentItem().hasItemMeta()) return;

        Player player = (Player) e.getWhoClicked();

        GUI gui = gameManager.getGuiManager().getOpenGUI(player);

        //if(gui == null) return;

        GUI newGUI = gui.handleClick(player, e.getCurrentItem(), e.getView());

        e.getView().close();

        gameManager.getGuiManager().setGUI(player, newGUI);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e)
    {
        Player player = (Player) e.getPlayer();

        //gameManager.getGuiManager().setGUI(player, null);
        gameManager.getGuiManager().clear(player);
    }
}
