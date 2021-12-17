package com.proboxinjet.ashguardall.pluginashguardall.commands.teleport.home;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportDelHome implements CommandExecutor
{
    private AshGuardAll main;
    public TeleportDelHome(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        boolean disablePlugin = DisablePlugin.isDisablePlugin();
        Player player = (Player) sender;
        if(label.equalsIgnoreCase("delhome") && disablePlugin == false)
        {
            if(sender instanceof Player)
            {
                if(args.length == 0)
                {
                    player.sendMessage("§4Erreur : /delhome <nom>");
                    return false;
                }

                if(args.length == 1)
                {
                    if(AshGuardAll.getInstance().getConfig().contains("home."+ player.getUniqueId().toString() +"."+ args[0]))
                    {
                        AshGuardAll.getInstance().getConfig().set("home."+ player.getUniqueId().toString() +"."+ args[0], null);
                        AshGuardAll.getInstance().saveConfig();
                        player.sendMessage("§3Votre home "+ "§a" + args[0] +" §3a bien été suprimé !");
                        return false;
                    }
                    else
                    {
                        player.sendMessage("§4L'home "+ "§a" + args[0] + " §4n'existe pas !");
                        return false;
                    }
                }

                if(args.length >= 2)
                {
                    player.sendMessage("§4Erreur : /delhome <nom>");
                    return false;
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