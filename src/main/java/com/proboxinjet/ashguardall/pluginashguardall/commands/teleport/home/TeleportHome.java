package com.proboxinjet.ashguardall.pluginashguardall.commands.teleport.home;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHome implements CommandExecutor
{
    private AshGuardAll main;
    public TeleportHome(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        boolean disablePlugin = DisablePlugin.isDisablePlugin();
        Player player = (Player) sender;
        if(label.equalsIgnoreCase("home") && disablePlugin == false)
        {
            if(sender instanceof Player)
            {
                if(args.length == 0)
                {
                    player.sendMessage("§4Erreur : /home <nom>");
                    return false;
                }

                if(args.length == 1)
                {
                    if(AshGuardAll.getInstance().getConfig().contains("home."+ player.getUniqueId().toString() +"."+ args[0]))
                    {
                        World w = Bukkit.getServer().getWorld(AshGuardAll.getInstance().getConfig().getString("home."+ player.getUniqueId().toString() +"."+ args[0] +".world"));
                        double x = AshGuardAll.getInstance().getConfig().getDouble("home."+ player.getUniqueId().toString() +"."+ args[0] +".x");
                        double y = AshGuardAll.getInstance().getConfig().getDouble("home."+ player.getUniqueId().toString() +"."+ args[0] +".y");
                        double z = AshGuardAll.getInstance().getConfig().getDouble("home."+ player.getUniqueId().toString() +"."+ args[0] +".z");
                        double pitch = AshGuardAll.getInstance().getConfig().getDouble("home."+ player.getUniqueId().toString() +"."+ args[0] +".pitch");
                        double yaw = AshGuardAll.getInstance().getConfig().getDouble("home."+ player.getUniqueId().toString() +"."+ args[0] +".yaw");
                        player.teleport(new Location(w, x, y, z,(float) yaw, (float) pitch));
                        player.sendMessage("§3Vous avez été téléporté à votre home " + "§a" + args[0]);
                        return false;
                    }
                    else
                    {
                        player.sendMessage("§4L'home "+ "§a" + args[0] +" §4n'existe pas !");
                        return false;
                    }
                }

                if(args.length >= 2)
                {
                    player.sendMessage("§4Erreur : /home <nom>");
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
