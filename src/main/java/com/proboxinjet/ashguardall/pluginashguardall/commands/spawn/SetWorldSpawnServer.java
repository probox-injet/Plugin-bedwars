package com.proboxinjet.ashguardall.pluginashguardall.commands.spawn;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWorldSpawnServer implements CommandExecutor
{
    private AshGuardAll main;
    public SetWorldSpawnServer(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        boolean disablePlugin = DisablePlugin.isDisablePlugin();
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("setspawn"))
        {
            if(args.length == 0)
            {
                if(player.isOp())
                {
                    if(disablePlugin == false)
                    {
                        player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                        player.sendMessage("§2Le point de spawn à été créer au coordonnée: " + "x:" + player.getLocation().getBlockX() + " y:" + player.getLocation().getBlockY() + " z:" + player.getLocation().getBlockX());
                    }
                    else
                    {
                        player.sendMessage(main.getConfig().getString("msgs.cmds.error.disable"));
                    }
                }
                else
                {
                    player.sendMessage(main.getConfig().getString("msgs.cmds.error.op"));
                }
            }
        }
        return false;
    }
}