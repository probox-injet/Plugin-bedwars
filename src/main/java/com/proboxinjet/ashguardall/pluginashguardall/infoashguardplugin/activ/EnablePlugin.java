package com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ;

import com.proboxinjet.ashguardall.AshGuardAll;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnablePlugin implements CommandExecutor
{
    boolean disablePlugin = DisablePlugin.isDisablePlugin();
    private AshGuardAll main;
    public EnablePlugin(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player player = (Player) sender;
        if(player.isOp() && cmd.getName().equalsIgnoreCase("enable") && args.length == 0)
        {
            DisablePlugin.setDisablePlugin(false);
            Bukkit.broadcastMessage("§6Le plugin AshGuardAll viens d'être activée !\n§b----Les commandes activées----"	+ main.getConfig().getString("msgs.cmds.error.plugin"));
            Bukkit.getOnlinePlayers().forEach(playerOp ->{
                if(playerOp.isOp())
                {
                    playerOp.sendMessage(main.getConfig().getString("msgs.cmds.error.pluginOp"));
                }
            });
            return true;
        }
        else
        {
            player.sendMessage(main.getConfig().getString("msgs.cmds.error.op"));
        }
        return false;
    }
}
