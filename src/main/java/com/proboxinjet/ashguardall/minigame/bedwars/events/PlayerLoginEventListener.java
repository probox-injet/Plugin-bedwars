package com.proboxinjet.ashguardall.minigame.bedwars.events;

import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameManager;
import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameState;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerLoginEventListener implements Listener
{
    private GameManager gameManager;

    public PlayerLoginEventListener(GameManager gameManager)
    {
        this.gameManager = gameManager;
    }

    //
    @EventHandler
    public void onPrelogin(AsyncPlayerPreLoginEvent e)
    {
        if(gameManager.getState() == GameState.RESET)
        {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "You can't join while a world is resetting");
            return;
        }

        UUID playerUUID = e.getUniqueId();
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUUID);

        if(!player.isOp() && gameManager.getState() == GameState.PRELOBBY)
        {
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "The game hasn't started yet.");
        }
    }
    //

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        e.setJoinMessage(null);

        gameManager.getScoreboard().addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        e.setQuitMessage(null);

        gameManager.getScoreboard().removePlayer(e.getPlayer());
    }
}
