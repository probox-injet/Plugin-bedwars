package com.proboxinjet.ashguardall.minigame.bedwars.worlds;

import com.proboxinjet.ashguardall.minigame.bedwars.worlds.generators.Generator;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public class LobbyWorld extends GameWorld
{

    public LobbyWorld(String name)
    {
        super(name);
    }

    @Override
    public boolean loadWorld(Runnable runnable)
    {
        return false;
    }

    @Override
    public String getConfigName()
    {
        return null;
    }

    @Override
    public World getWorld()
    {
        return null;
    }

    @Override
    public Location getLobbyPosition()
    {
        return null;
    }

    @Override
    public Location getSpawnForTeamColor(TeamColor color)
    {
        return null;
    }

    @Override
    public List<Generator> generatorList()
    {
        return null;
    }
}
