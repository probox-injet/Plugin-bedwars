package com.proboxinjet.ashguardall.minigame.bedwars.worlds.generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class Generator
{
    private Location location;
    private GeneratorType type;

    public Generator(Location location, GeneratorType type)
    {
        this.location = location;
        this.type = type;
    }

    private boolean activated = false;

    public void spawn()
    {
        if(!activated) return;
        Item item = (Item) location.getWorld().spawnEntity(location, EntityType.DROPPED_ITEM);

        switch (type)
        {
            case IRON:
                item.setItemStack(new ItemStack(Material.IRON_INGOT));
                break;
            case GOLD:
                item.setItemStack(new ItemStack(Material.GOLD_INGOT));
                break;
            case DIAMOND:
                item.setItemStack(new ItemStack(Material.DIAMOND));
                break;
            case EMERALD:
                item.setItemStack(new ItemStack(Material.EMERALD));
                break;
        }
    }

    public Location getLocation()
    {
        return location;
    }

    public GeneratorType getType()
    {
        return type;
    }
}
