package com.proboxinjet.ashguardall.pluginashguardall.commands.teleport.world;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class TeleportAleatoryMap implements CommandExecutor
{
    private AshGuardAll main;
    public TeleportAleatoryMap(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        boolean disablePlugin = DisablePlugin.isDisablePlugin();
        Random rand = new Random();
        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("tpmap") && disablePlugin == false)
        {
            int coord = 10000000;
            Location ploc = player.getLocation();
            Location tpmap = new Location(player.getWorld(), ploc.getX() + rand.nextInt(coord), ploc.getY() + rand.nextInt(255), - ploc.getZ() + rand.nextInt(coord));
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*10, 62));
            player.sendMessage("Vous venez d'être téléporter sur la map");
            player.teleport(tpmap);
            return true;
        }
        else
        {
            player.sendMessage(main.getConfig().getString("msgs.cmds.error.disable"));
        }
        return false;
    }
}
