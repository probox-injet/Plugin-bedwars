package com.proboxinjet.ashguardall.minigame.bedwars.events;

import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameManager;
import com.proboxinjet.ashguardall.minigame.bedwars.gui.SetupWizardIslandSelectorGUI;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.GameWorld;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.Island;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.generators.Generator;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.generators.GeneratorType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerItemInteractListener implements Listener
{
    private GameManager gameManager;

    public PlayerItemInteractListener(GameManager gameManager)
    {
        this.gameManager = gameManager;
    }

    //Setup wizard
    @EventHandler
    public void onInteractWithSetupWizardItem(PlayerInteractEvent e)
    {
        if(!e.hasItem()) return;
        if(!gameManager.getSetupWizardManager().isInWizard(e.getPlayer())) return;
        if(e.getItem() == null) return;
        if(!e.getItem().hasItemMeta()) return;
        Player player = e.getPlayer();

        String itemName = e.getItem().getItemMeta().getDisplayName();
        itemName = ChatColor.stripColor(itemName);

        Location current = player.getLocation();

        Location clicked = null;
        if(e.getClickedBlock() != null)
        {
            clicked = e.getClickedBlock().getLocation();
        }

        Island island = gameManager.getSetupWizardManager().getIsland(player);

        switch(itemName.toLowerCase())
        {
            case "set diamond generator":
                Generator diamondGenerator = new Generator(current, GeneratorType.DIAMOND);
                gameManager.getConfigurationManager().saveUnownedGenerator(player.getWorld().getName(), diamondGenerator);

                player.sendMessage("set diamond generator");
                break;
            case "set emerald generator":
                Generator emeraldGenerator = new Generator(current, GeneratorType.EMERALD);
                gameManager.getConfigurationManager().saveUnownedGenerator(player.getWorld().getName(), emeraldGenerator);
                player.sendMessage("set emerald generator");
                break;
            case "change island":
                SetupWizardIslandSelectorGUI gui = new SetupWizardIslandSelectorGUI(gameManager);
                gameManager.getGuiManager().setGUI(player, gui);
                break;
            case "first corner stick":
                if(clicked != null)
                {
                    island.setProtectedCornerOne(clicked);
                }
                break;
            case "second corner stick":
                if(clicked != null)
                {
                    island.setProtectedCornerTwo(clicked);
                }
                break;
            case "set shop location":
                island.setShopEntityLocation(current);
                break;
            case "set generator location":
            {
                Generator islandGenerator = new Generator(current, GeneratorType.IRON);
                island.addGenerator(islandGenerator);
            }
            {
                Generator islandGenerator = new Generator(current, GeneratorType.GOLD);
                island.addGenerator(islandGenerator);
            }
            {
                Generator islandGenerator = new Generator(current, GeneratorType.EMERALD);
                island.addGenerator(islandGenerator);
            }
                break;
            case "set team upgrade location":
                island.setUpgradesEntityLocation(current);
                break;
            case "set spawn location":
                island.setSpawnLocation(current);
                break;
            case "set bed location":
                if(clicked != null)
                {
                    island.setBedLocation(clicked);
                }
                break;
            case "set lobby spawn":
                GameWorld gameWorld = gameManager.getSetupWizardManager().getWorld(player);
                gameWorld.setLobbySpawn(current);
                gameManager.getConfigurationManager().saveWorld(gameWorld);
                break;
            case "save island":
                gameManager.getConfigurationManager().saveIsland(island);
                Bukkit.getServer().getScheduler().runTaskLater(gameManager.getPlugin(), task -> gameManager.getSetupWizardManager().worldSetUpWizard(player, island.getGameWorld()),4);
                //gameManager.getSetupWizardManager().worldSetUpWizard(player, island.getGameWorld());
                break;
            default: return;
        }

        e.setCancelled(true);
    }
}