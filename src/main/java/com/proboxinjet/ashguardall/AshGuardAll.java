package com.proboxinjet.ashguardall;

import com.proboxinjet.ashguardall.minigame.bedwars.commands.SetupWizardCommand;
import com.proboxinjet.ashguardall.minigame.bedwars.commands.StartCommand;
import com.proboxinjet.ashguardall.minigame.bedwars.events.BlockUpdateListener;
import com.proboxinjet.ashguardall.minigame.bedwars.events.InventoryClickListener;
import com.proboxinjet.ashguardall.minigame.bedwars.events.PlayerItemInteractListener;
import com.proboxinjet.ashguardall.minigame.bedwars.events.PlayerLoginEventListener;
import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameManager;
import com.proboxinjet.ashguardall.pluginashguardall.commands.GameModePlugin;
import com.proboxinjet.ashguardall.pluginashguardall.commands.Register;
import com.proboxinjet.ashguardall.pluginashguardall.commands.spawn.SetWorldSpawnServer;
import com.proboxinjet.ashguardall.pluginashguardall.commands.spawn.chest.SpawnChestAleatoire;
import com.proboxinjet.ashguardall.pluginashguardall.commands.teleport.Spawn;
import com.proboxinjet.ashguardall.pluginashguardall.commands.teleport.home.*;
import com.proboxinjet.ashguardall.pluginashguardall.commands.teleport.world.*;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.InfoAshGuardPlugin;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AshGuardAll extends JavaPlugin implements Listener
{
    private GameManager gameManager;
    public static AshGuardAll instance;
    public static AshGuardAll getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        Bukkit.setWhitelist(true);
        instance = this;
        this.gameManager = new GameManager(this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new AshGuardAllList(), this);
        getServer().getPluginManager().registerEvents(new PlayerLoginEventListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerItemInteractListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(gameManager), this);
        getServer().getPluginManager().registerEvents(new BlockUpdateListener(gameManager), this);
        System.out.println("Le Plugin AshGuardAll viens de s'allumer");

        getCommand("setup").setExecutor(new SetupWizardCommand(gameManager));
        getCommand("start").setExecutor(new StartCommand(gameManager));
        getCommand("c").setExecutor(new GameModePlugin(this)); //op
        getCommand("s").setExecutor(new GameModePlugin(this)); //op
        getCommand("sp").setExecutor(new GameModePlugin(this)); //op
        getCommand("a").setExecutor(new GameModePlugin(this)); //op
        getCommand("alert").setExecutor(new Register(this));
        getCommand("spawn").setExecutor(new Spawn(this));
        getCommand("tpmap").setExecutor(new TeleportAleatoryMap(this));
        getCommand("nether").setExecutor(new WorldMM(this));
        getCommand("end").setExecutor(new WorldMM(this));
        getCommand("world").setExecutor(new WorldMM(this));
        getCommand("setspawn").setExecutor(new SetWorldSpawnServer(this)); //op
        getCommand("sethome").setExecutor(new TeleportSetHome(this));
        getCommand("home").setExecutor(new TeleportHome(this));
        getCommand("delhome").setExecutor(new TeleportDelHome(this));
        getCommand("info").setExecutor(new InfoAshGuardPlugin(this));
        getCommand("information").setExecutor(new InfoAshGuardPlugin(this));
        getCommand("enable").setExecutor(new EnablePlugin(this)); //op
        getCommand("disable").setExecutor(new DisablePlugin(this)); //op
        getCommand("event").setExecutor(new SpawnChestAleatoire(this)); //op

        for (Player player : Bukkit.getOnlinePlayers())
        {
            gameManager.getScoreboard().addPlayer(player);
        }
    }

    @Override
    public void onDisable()
    {
        this.gameManager.getScoreboard().destroy();
        System.out.println("Le plugin AshGuardAll viens de s'etteindre");
    }
}
