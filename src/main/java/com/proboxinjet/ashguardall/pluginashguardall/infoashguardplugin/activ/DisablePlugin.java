package com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ;

import com.proboxinjet.ashguardall.AshGuardAll;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DisablePlugin implements CommandExecutor
{
    private static boolean disablePlugin;
    private AshGuardAll main;
    public DisablePlugin(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {

        disablePlugin = false;
        Player player = (Player) sender;
        if(player.isOp() && args.length == 0 && cmd.getName().equalsIgnoreCase("disable"))
        {
            setDisablePlugin(true);
            player.sendMessage("§2Le plugins viens d'être desactivée !");
            Bukkit.broadcastMessage("§6Le plugin AshGuardAll viens d'être désactivée !\n§b---Les commandes désactivée---" + main.getConfig().getString("msgs.cmds.error.plugin"));
            Bukkit.getOnlinePlayers().forEach(playerOp -> {
                if(playerOp.isOp())
                {
                    playerOp.sendMessage(main.getConfig().getString("msgs.cmds.error.pluginOp"));
                }
            });
        }
        else
        {
            player.sendMessage(main.getConfig().getString("msgs.cmds.error.op"));
        }
        return true;
    }

    public static boolean isDisablePlugin() //getter
    {
        return disablePlugin;
    }
    public static void setDisablePlugin(boolean disablePlugin) //setter
    {
        DisablePlugin.disablePlugin = disablePlugin;
    }
}
