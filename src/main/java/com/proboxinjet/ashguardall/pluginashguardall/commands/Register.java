package com.proboxinjet.ashguardall.pluginashguardall.commands;

import com.proboxinjet.ashguardall.AshGuardAll;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Register implements CommandExecutor
{
    private AshGuardAll main;
    public Register(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            if(player.isOp())
            {

                if(cmd.getName().equalsIgnoreCase("alert"))
                {
                    if(args.length == 0)
                    {
                        player.sendMessage("La command est : §6/alert <message>");
                    }
                    if(args.length >= 1)
                    {
                        StringBuilder bc = new StringBuilder();
                        for(String part : args)
                        {
                            bc.append(part + " ");
                        }
                        Bukkit.broadcastMessage("§e[" + player.getName() + "] §4"+bc.toString());
                    }
                    return true;
                }
            }
            else
            {
                player.sendMessage(main.getConfig().getString("msgs.cmds.error.op"));
            }
        }
        return false;
    }
}