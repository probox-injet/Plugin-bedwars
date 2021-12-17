package com.proboxinjet.ashguardall.pluginashguardall.commands.teleport.home;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportSetHome implements CommandExecutor
{
    private AshGuardAll main;
    public TeleportSetHome(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        boolean disablePlugin = DisablePlugin.isDisablePlugin();
        Player player = (Player)sender;
        if(label.equalsIgnoreCase("sethome") && sender instanceof Player && disablePlugin == false)
        {
            if(args.length == 0)
            {
                player.sendMessage("§4Erreur : /sethome <nom>");
                return false;
            }

            if(args.length == 1 && AshGuardAll.getInstance().getConfig().get("home."+ player.getUniqueId().toString() +"."+ args[0]) == null)
            {
                AshGuardAll.getInstance().getConfig().set("home."+ player.getUniqueId().toString() +"."+ args[0] +".world", player.getLocation().getWorld().getName());
                AshGuardAll.getInstance().getConfig().set("home."+ player.getUniqueId().toString() +"."+ args[0] +".x", player.getLocation().getX());
                AshGuardAll.getInstance().getConfig().set("home."+ player.getUniqueId().toString() +"."+ args[0] +".y", player.getLocation().getY());
                AshGuardAll.getInstance().getConfig().set("home."+ player.getUniqueId().toString() +"."+ args[0] +".z", player.getLocation().getZ());
                AshGuardAll.getInstance().getConfig().set("home."+ player.getUniqueId().toString() +"."+ args[0] +".pitch", player.getEyeLocation().getPitch());
                AshGuardAll.getInstance().getConfig().set("home."+ player.getUniqueId().toString() +"."+ args[0] +".yaw", player.getEyeLocation().getYaw());
                AshGuardAll.getInstance().saveConfig();
                player.sendMessage("§3Votre home " + "§a" + args[0] +" §3a été créé !");
                return false;
            }
            else
            {
                player.sendMessage("§4Le home " + "§a" + args[0] +" §3existe déjà !");
            }

            if(args.length >= 2)
            {
                player.sendMessage("§4Erreur : /sethome <nom>");
                return false;
            }
        }
        else
        {
            player.sendMessage(main.getConfig().getString("msgs.cmds.error.disable"));
        }
        return false;
    }

}
