package com.proboxinjet.ashguardall.minigame.bedwars.commands;

import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameManager;
import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor
{
    private GameManager gameManager;

    public StartCommand(GameManager gameManager)
    {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(sender.hasPermission("bedwars.admin"))
        {
            gameManager.setState(GameState.STARTING);
        }
        else
        {
            sender.sendMessage("You don't has permission to run this command");
        }
        return false;
    }
}
