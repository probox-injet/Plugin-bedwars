package com.proboxinjet.ashguardall.minigame.bedwars.gamemanager;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.jscoreboards.JPerPlayerScoreboard;
import com.proboxinjet.ashguardall.jscoreboards.JScoreboard;
import com.proboxinjet.ashguardall.minigame.bedwars.config.ConfigurationManager;
import com.proboxinjet.ashguardall.minigame.bedwars.gui.GUIManager;
import com.proboxinjet.ashguardall.minigame.bedwars.setup.SetupWizardManager;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.GameWorld;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager
{
    private AshGuardAll plugin;
    private JPerPlayerScoreboard scoreboard;

    private SetupWizardManager setupWizardManager;
    private ConfigurationManager configurationManager;
    private GUIManager guiManager;

    private GameWorld gameWorld; //

    private GameState state;

    public GameManager(AshGuardAll plugin)
    {
        this.plugin = plugin;

        this.setupWizardManager = new SetupWizardManager(this);
        this.configurationManager = new ConfigurationManager(this);
        this.guiManager = new GUIManager();

        this.configurationManager.loadWorld(this.configurationManager.randomMapName(), gameWorld-> {
            this.gameWorld = gameWorld;
            setState(GameState.LOBBY);
        }); //

        this.scoreboard = new JPerPlayerScoreboard(player ->{
            return "§4§lBedWars";
        }, player -> {
            List<String>lines = new ArrayList<>();
            //todo scoreboard
            return lines;
        });
    }

    public void setState(GameState state)
    {
        this.state = state;

        switch (state)
        {
            case STARTING:
                //start a countdown
                break;
            case ACTIVE:
                //tp people into area
                break;
            case WON:
                // announce winner, ect
                break;
            case RESET:
                for(Player player : Bukkit.getOnlinePlayers())
                {
                    player.kickPlayer("Restarting Game!");
                }

                Bukkit.getServer().getScheduler().runTaskLater(plugin, bukkitTask -> {
                    gameWorld.resetWorld();
                    Bukkit.getServer().shutdown();
                }, 20);
                break;
        }
    }

    public GameState getState()
    {
        return state;
    }

    public GameWorld getGameWorld()
    {
        return gameWorld;
    }

    public JScoreboard getScoreboard()
    {
        return scoreboard;
    }

    public AshGuardAll getPlugin()
    {
        return this.plugin;
    }

    public ConfigurationManager getConfigurationManager()
    {
        return configurationManager;
    }

    public GUIManager getGuiManager()
    {
        return guiManager;
    }

    public SetupWizardManager getSetupWizardManager()
    {
        return setupWizardManager;
    }
}