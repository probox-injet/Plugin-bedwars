package com.proboxinjet.ashguardall.pluginashguardall.commands.teleport;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor
{
    private AshGuardAll main;
    public Spawn(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        boolean disablePlugin = DisablePlugin.isDisablePlugin();
        Player player = (Player) sender;
        if(sender instanceof Player && disablePlugin == false)
        {
            if(cmd.getName().equalsIgnoreCase("spawn"))
            {
                if(args.length == 0)
                {
                    player.teleport(player.getWorld().getSpawnLocation());
                    player.sendMessage(main.getConfig().getString("msgs.cmds.tp.spawn"));
                }
            }
        }
        else
        {
            player.sendMessage(main.getConfig().getString("msgs.cmds.error.disable"));
        }
        return false;
    }
}
