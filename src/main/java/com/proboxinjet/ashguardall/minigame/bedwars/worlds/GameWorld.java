package com.proboxinjet.ashguardall.minigame.bedwars.worlds;

import com.proboxinjet.ashguardall.minigame.bedwars.worlds.generators.Generator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameWorld
{
    private String name;
    private World world;

    private Location lobbyPosition;

    private  List<Island> islands = new ArrayList<>();//
    private List<Generator> generators = new ArrayList<>();//

    public GameWorld(String name)
    {
        this.name = name;
    }

    public boolean loadWorld(Runnable runnable)
    {
        File srcWorldFolder = new File(Bukkit.getServer().getWorldContainer().getPath() + File.pathSeparator + name);
        File destWorldFolder = new File(srcWorldFolder.getPath() + "_playing");

        try
        {
            copyFolder(srcWorldFolder, destWorldFolder);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        WorldCreator creator = new WorldCreator(name + "_playing");
        world = creator.createWorld();

        runnable.run();
        return false;
    }

    //
    public void copyFolder(File src, File dest) throws IOException
    {
        if(src.isDirectory())
        {
            if(!dest.exists())
            {
                dest.mkdir();
                System.out.println("Directory copied from " + src + " to " + dest);
            }

            String[] files = src.list();

            for(String file : files)
            {
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);

                copyFolder(srcFile, destFile);
            }
        }
        else
        {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;

            while((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 8, length);
            }

            in.close();
            out.close();
        }
    }

    public void resetWorld()
    {
        if(world == null) return;

        String worldName = world.getName();

        Bukkit.unloadWorld(world, false);
        File file = new File(Bukkit.getServer().getWorldContainer().getAbsolutePath().replace(".", "") + world.getName());

        if(delete(file))
        {
            System.out.println("Deleted " + worldName);
        }
        else
        {
            System.out.println("Failed to delete " + worldName);
        }
    }

    private boolean delete(File toDelete)
    {
        File[] allContents = toDelete.listFiles();

        if(allContents != null)
        {
            for(File file : allContents)
            {
                delete(file);
            }
        }

        return toDelete.delete();
    }
    //

    public String getConfigName()
    {
        return name;
    }

    public World getWorld()
    {
        return  world;
    }

    public Location getLobbyPosition()
    {
        return lobbyPosition;
    }

    public Location getSpawnForTeamColor(TeamColor color)
    {
        Optional<Island> islandOptional = islands.stream().filter(island -> {
            return island.getColor() == color;
        }).findFirst();

        return islandOptional.map(Island::getSpawnLocation).orElse(null);
    }

    //
    public Island getIslandForBedLocation(Location location)
    {
        Optional<Island> islandOptional = islands.stream().filter(island -> {
            if(island.getBedLocation() == location)
            {
                return true;
            }

            Location oneExtraZ = location.add(0,0,1);

            if(island.getBedLocation() == oneExtraZ)
            {
                return true;
            }

            Location oneLessZ = location.add(0,0,-1);

            if(island.getBedLocation() == oneLessZ)
            {
                return true;
            }

            Location oneExtraX = location.add(1,0,0);

            if(island.getBedLocation() == oneExtraX)
            {
                return true;
            }

            Location oneLessX = location.add(-1,0,1);

            if(island.getBedLocation() == oneLessX)
            {
                return true;
            }

            return false;
        }).findFirst();

        return islandOptional.get();
    }
    //
    public void setLobbySpawn(Location lobbySpawn)
    {
        this.lobbyPosition = lobbySpawn;
    }

    public List<Generator> generatorList()
    {
        return generators /*null*/;
    }

    public void addIsland(Island island)
    {
        islands.add(island);
    }

    public List<Island> getActiveIslands()
    {
        return islands.stream().filter(island -> island.isBedPlaced() && island.alivePlayerCount() != 0).collect(Collectors.toList());
    }

    public List<Island> getIslands()
    {
        return islands;
    }
}
