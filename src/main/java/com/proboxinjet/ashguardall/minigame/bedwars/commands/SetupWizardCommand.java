package com.proboxinjet.ashguardall.minigame.bedwars.commands;

import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameManager;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.GameWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupWizardCommand implements CommandExecutor
{
    private GameManager gameManager;

    public SetupWizardCommand(GameManager gameManager)
    {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if(!player.hasPermission("bedwars.admin"))
        {
            player.sendMessage("§4You don't have a permission to run this command !");
            return true;
        }

        if(args.length < 1)
        {
            player.sendMessage("§4/setup <map name>");
            return true;
        }

        String mapName = args[0];
        //
        if(mapName.equalsIgnoreCase("exit"))
        {
            gameManager.getSetupWizardManager().removeFromWizard(player);
            return true;
        }
        //
        player.sendMessage("§aLoading worlds, one moment...");

        GameWorld world = new GameWorld(mapName);
        world.loadWorld(()-> gameManager.getSetupWizardManager().activatedSetupWizard(player, world));

        return false/*false*/;
    }
}
