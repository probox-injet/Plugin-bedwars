package com.proboxinjet.ashguardall.minigame.bedwars.config;

import com.proboxinjet.ashguardall.minigame.bedwars.gamemanager.GameManager;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.TeamColor;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.GameWorld;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.Island;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.generators.Generator;
import com.proboxinjet.ashguardall.minigame.bedwars.worlds.generators.GeneratorType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConfigurationManager
{
    private GameManager gameManager;
    private ConfigurationSection mapsConfiguration;

    public ConfigurationManager(GameManager gameManager)
    {
        this.gameManager = gameManager;
        FileConfiguration fileConfiguration = gameManager.getPlugin().getConfig();
        if(!fileConfiguration.isConfigurationSection("maps"))
        {
            mapsConfiguration = fileConfiguration.createSection("maps");
        }
        else
        {
            mapsConfiguration = fileConfiguration.getConfigurationSection("maps");
        }

        gameManager.getPlugin().saveConfig();
    }

    //
    public String randomMapName()
    {
        String[] mapNames = mapsConfiguration.getKeys(false).toArray(new String[]{});

        if(mapNames.length <= 0)
        {
            throw new IllegalArgumentException(mapNames);
        }

        return mapNames[ThreadLocalRandom.current().nextInt(mapNames.length - 1)];
    }
    //

    public ConfigurationSection getMapSection(String mapName)
    {
        if (!mapsConfiguration.isConfigurationSection(mapName))
        {
            mapsConfiguration.createSection(mapName);
        }

        return mapsConfiguration.getConfigurationSection(mapName);
    }

    //Loading
    //
    public void loadWorld(String mapName, Consumer<GameWorld> consumer)
    {
        GameWorld gameWorld = new GameWorld(mapName);
        gameWorld.loadWorld(() -> {
            ConfigurationSection mapSection = getMapSection(mapName);
            for(String key : mapSection.getKeys(false))
            {
                if(Arrays.stream(TeamColor.values()).anyMatch((t) -> t.name().equals(key)))
                {
                    Island island = loadIsland(gameWorld, mapSection.getConfigurationSection(key));
                    gameWorld.addIsland(island);
                }
            }

            consumer.accept(gameWorld);
        });
    }

    public Island loadIsland(GameWorld world, ConfigurationSection section)
    {
        TeamColor color = TeamColor.valueOf(section.getName());
        Island island = new Island(world, color);

        try
        {
            island.setBedLocation(locationFrom(world.getWorld(), section.getConfigurationSection("bed")));
            island.setSpawnLocation(locationFrom(world.getWorld(), section.getConfigurationSection("spawn")));
            island.setUpgradesEntityLocation(locationFrom(world.getWorld(), section.getConfigurationSection("upgradeEntity")));
            island.setShopEntityLocation(locationFrom(world.getWorld(), section.getConfigurationSection("shopEntity")));
            island.setProtectedCornerOne(locationFrom(world.getWorld(), section.getConfigurationSection("protectedCornerOne")));
            island.setProtectedCornerTwo(locationFrom(world.getWorld(), section.getConfigurationSection("protectedCornerTwo")));

            island.setGenerators(loadGenerators(world, section.getConfigurationSection("generators")));
        }
        catch(Exception ex)
        {
            Bukkit.getServer().getLogger().severe("Invalid " + color.formattedName() + " island in " + world.getConfigName());
        }

        return island;
    }

    public List<Generator> loadGenerators(GameWorld world, ConfigurationSection section)
    {
        List<Generator> generators = section.getKeys(false).stream().map((key) -> {
            Location location = locationFrom(world.getWorld(), section.getConfigurationSection(key));
            String typeString = section.getString("type");

            if(!Arrays.stream(GeneratorType.values()).anyMatch((t) -> t.name().equals(typeString)))
            {
                return null;
            }

            GeneratorType type = GeneratorType.valueOf(typeString);
            return new Generator(location, type);
        }).collect(Collectors.toList());

        return generators;
    }
    //

    //Saving
    public void saveWorld(GameWorld world)
    {
        ConfigurationSection mapSection = getMapSection(world.getConfigName());
        ConfigurationSection lobbySection;

        if((mapSection.isConfigurationSection("lobbySpawn")))
        {
            lobbySection = mapSection.getConfigurationSection("lobbySpawn");
        }
        else
        {
            lobbySection = mapSection.createSection("lobbySpawn");
        }

        writeLocation(world.getLobbyPosition(), lobbySection);

        gameManager.getPlugin().saveConfig();
    }

    public void saveUnownedGenerator(String worldConfigName, Generator generator)
    {
        ConfigurationSection mapSection = getMapSection(worldConfigName);

        ConfigurationSection generatorSection;
        if(mapSection.isConfigurationSection("generators"))
        {
            generatorSection = mapSection.getConfigurationSection("generators");
        }
        else
        {
            generatorSection = mapSection.createSection("generators");
        }

        ConfigurationSection section = generatorSection.createSection(UUID.randomUUID().toString());
        section.set("type", generator.getType().toString());
        writeLocation(generator.getLocation(), section.createSection("location"));

        gameManager.getPlugin().saveConfig();
    }

    public void saveIsland(Island island)
    {
        ConfigurationSection mapSection = getMapSection(island.getGameWorld().getConfigName());

        ConfigurationSection colorSection;
        if(mapSection.isConfigurationSection(island.getColor().toString()))
        {
            colorSection = mapSection.getConfigurationSection(island.getColor().toString());
        }
        else
        {
            colorSection = mapSection.createSection(island.getColor().toString());
        }

        Map<String, Location> locationToWrite = new HashMap<>();
        locationToWrite.put("upgradeEntity", island.getUpgradeEntityLocation());
        locationToWrite.put("bed", island.getBedLocation());
        locationToWrite.put("shopEntity", island.getShopEntityLocation());
        locationToWrite.put("spawn", island.getSpawnLocation());
        locationToWrite.put("protectedCornerOne", island.getProtectedCornerOne());
        locationToWrite.put("protectedCornerTwo", island.getProtectedCornerTwo());

        for(Map.Entry<String, Location> entry : locationToWrite.entrySet())
        {
            ConfigurationSection section;

            if(!colorSection.isConfigurationSection(entry.getKey()))
            {
                section = colorSection.createSection(entry.getKey());
            }
            else
            {
                section = colorSection.getConfigurationSection(entry.getKey());
            }

            if(entry.getValue() != null)
            {
                writeLocation(entry.getValue(), section);
            }
        }

        colorSection.set("generators", null);
        ConfigurationSection generatorSection = mapSection.createSection("generators");

        for (Generator generator : island.getGenerators())
        {
            ConfigurationSection section = generatorSection.createSection(UUID.randomUUID().toString());
            section.set("type", generator.getType().toString());
            writeLocation(generator.getLocation(), section.createSection("location"));
        }

        gameManager.getPlugin().saveConfig();
    }

    public void writeLocation(Location location, ConfigurationSection section)
    {
        section.set("x", location.getX());
        section.set("y", location.getY());
        section.set("z", location.getZ());
        section.set("yaw", location.getYaw());
        section.set("pitch", location.getPitch());
    }

    private Location locationFrom(World world, ConfigurationSection section)
    {
        return new Location(world, section.getDouble("x"), section.getDouble("y"), section.getDouble("z"), (float) section.getDouble("yaw"),(float) section.getDouble("pitch"));
    }
}
