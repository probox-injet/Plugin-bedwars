package com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoAshGuardPlugin implements CommandExecutor
{
    private AshGuardAll main;
    public InfoAshGuardPlugin(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(cmd.getName().equalsIgnoreCase("info") || cmd.getName().equalsIgnoreCase("information"))
        {
            boolean disablePlugin = DisablePlugin.isDisablePlugin();
            Player player = (Player) sender;
            if(args.length == 0 && disablePlugin == false)
            {
                player.sendMessage(main.getConfig().getString("msgs.info") + "§2✔");
            }
            else
            {
                player.sendMessage(main.getConfig().getString("msgs.info") + "§4✘");
            }
        }
        return false;
    }
}
