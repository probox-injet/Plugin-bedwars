package com.proboxinjet.ashguardall.pluginashguardall.commands.teleport.world;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldMM implements CommandExecutor
{
    private AshGuardAll main;
    public WorldMM(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        boolean disablePlugin = DisablePlugin.isDisablePlugin();
        Player player = (Player) sender;
        if(sender instanceof Player && player.isOp())
        {
            if(disablePlugin == false)
            {
                if(cmd.getName().equalsIgnoreCase("nether") && args.length == 0)
                {
                    Location nether = new Location(Bukkit.getWorld("world_nether"), 0, 71, 0, 1.8f, 7.4f);
                    player.sendMessage("§3Vous venez d'être tp au nether");
                    player.teleport(nether);
                    return true;
                }

                if(cmd.getName().equalsIgnoreCase("end") && args.length == 0)
                {
                    Location end = new Location(Bukkit.getWorld("world_the_end"), 0, 255, 0, 1.8f, 7.4f);
                    player.sendMessage("§3Vous venez d'être tp a l'end");
                    player.teleport(end);
                    return true;
                }

                if(cmd.getName().equalsIgnoreCase("world") && args.length == 0)
                {
                    Location world = new Location(Bukkit.getWorld("world"), 0, 71, 0, 1.8f, 7.4f);
                    player.sendMessage("§3Vous venez d'être tp dans l'Overworld");
                    player.teleport(world);
                    return true;
                }
            }
            else
            {
                player.sendMessage(main.getConfig().getString("msgs.cdms.error.disable"));
            }
        }
        else
        {
            player.sendMessage(main.getConfig().getString("msgs.cdms.error.op"));
        }
        return false;
    }

}