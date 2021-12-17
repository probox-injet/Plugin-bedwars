package com.proboxinjet.ashguardall.pluginashguardall.commands;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModePlugin implements CommandExecutor
{
    private AshGuardAll main;
    public GameModePlugin(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        boolean disablePlugin = DisablePlugin.isDisablePlugin();
        Player player = (Player) sender;
        if(sender instanceof Player)
        {
            if(player.isOp())
            {
                if(disablePlugin == false)
                {
                    if(cmd.getName().equalsIgnoreCase("c"))
                    {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("§2Vous venez de vous mettre en créatif !");
                        return true;
                    }

                    if(cmd.getName().equalsIgnoreCase("s"))
                    {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("§2Vous venez de vous mettre en survie !");
                        return true;
                    }

                    if(cmd.getName().equalsIgnoreCase("sp"))
                    {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("§2Vous venez de vous mettre en spectateur !");
                        return true;
                    }

                    if(cmd.getName().equalsIgnoreCase("a"))
                    {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage("§2Vous venez de vous mettre en adventure !");
                        return true;
                    }
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
        return false;
    }

}
