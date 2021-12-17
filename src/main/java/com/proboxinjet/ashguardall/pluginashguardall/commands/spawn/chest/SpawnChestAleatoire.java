package com.proboxinjet.ashguardall.pluginashguardall.commands.spawn.chest;

import com.proboxinjet.ashguardall.AshGuardAll;
import com.proboxinjet.ashguardall.pluginashguardall.infoashguardplugin.activ.DisablePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpawnChestAleatoire implements CommandExecutor
{
    boolean disablePlugin = DisablePlugin.isDisablePlugin();
    private AshGuardAll main;
    public SpawnChestAleatoire(AshGuardAll ashGuard)
    {
        this.main = ashGuard;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        //stock le Material et %age
        Map<Material, Float> tauxSpawnItem = new HashMap<>();
        //%<%  tauxSpawnItem.put(Materiaux, %);
        //nether
        tauxSpawnItem.put(Material.WITHER_SKELETON_SKULL, 0.000001f);
        tauxSpawnItem.put(Material.NETHERITE_BLOCK, 0.000066f);
        tauxSpawnItem.put(Material.NETHERITE_CHESTPLATE, 0.000075f);
        tauxSpawnItem.put(Material.NETHERITE_LEGGINGS, 0.000085f);
        tauxSpawnItem.put(Material.NETHERITE_HELMET, 0.00012f);
        tauxSpawnItem.put(Material.NETHERITE_BOOTS, 0.00015f);
        tauxSpawnItem.put(Material.NETHERITE_INGOT, 0.0006f);
        tauxSpawnItem.put(Material.NETHERITE_SCRAP, 0.002f);
        tauxSpawnItem.put(Material.SHROOMLIGHT, 0.004f);
        tauxSpawnItem.put(Material.NETHER_GOLD_ORE, 0.005f);
        tauxSpawnItem.put(Material.SOUL_TORCH, 0.05f);
        tauxSpawnItem.put(Material.CRIMSON_NYLIUM, 0.01f);
        tauxSpawnItem.put(Material.WARPED_NYLIUM, 0.01f);
        tauxSpawnItem.put(Material.CRIMSON_ROOTS, 0.015f);
        tauxSpawnItem.put(Material.CRIMSON_FUNGUS, 0.015f);
        tauxSpawnItem.put(Material.WEEPING_VINES, 0.015f);
        tauxSpawnItem.put(Material.WARPED_FUNGUS, 0.015f);
        tauxSpawnItem.put(Material.TWISTING_VINES, 0.015f);
        tauxSpawnItem.put(Material.NETHER_SPROUTS, 0.015f);
        tauxSpawnItem.put(Material.WARPED_ROOTS, 0.015f);
        tauxSpawnItem.put(Material.NETHER_QUARTZ_ORE, 0.02f);
        tauxSpawnItem.put(Material.NETHER_STAR, 0.003f);
        tauxSpawnItem.put(Material.CRIMSON_STEM, 0.05f);
        tauxSpawnItem.put(Material.CRIMSON_HYPHAE, 0.05f);
        tauxSpawnItem.put(Material.STRIPPED_CRIMSON_HYPHAE, 0.05f);
        tauxSpawnItem.put(Material.WARPED_STEM, 0.05f);
        tauxSpawnItem.put(Material.WARPED_HYPHAE, 0.05f);
        tauxSpawnItem.put(Material.STRIPPED_CRIMSON_STEM, 0.05f);
        tauxSpawnItem.put(Material.CRIMSON_SLAB, 0.05f);
        tauxSpawnItem.put(Material.CRIMSON_STAIRS, 0.05f);
        tauxSpawnItem.put(Material.CRIMSON_FENCE_GATE, 0.05f);
        tauxSpawnItem.put(Material.WARPED_FENCE, 0.05f);
        tauxSpawnItem.put(Material.WARPED_FENCE_GATE, 0.05f);
        tauxSpawnItem.put(Material.WARPED_STAIRS, 0.05f);
        tauxSpawnItem.put(Material.WARPED_SLAB, 0.05f);
        tauxSpawnItem.put(Material.BLACKSTONE_SLAB, 0.1f);
        tauxSpawnItem.put(Material.BLACKSTONE, 0.1f);
        tauxSpawnItem.put(Material.MAGMA_BLOCK, 0.1f);
        tauxSpawnItem.put(Material.BLACKSTONE_WALL, 0.1f);
        tauxSpawnItem.put(Material.NETHER_BRICK_WALL, 0.1f);
        tauxSpawnItem.put(Material.NETHERRACK, 1f);
        tauxSpawnItem.put(Material.NETHER_BRICKS, 0.101f);
        tauxSpawnItem.put(Material.NETHER_BRICK_FENCE, 0.102f);
        tauxSpawnItem.put(Material.NETHER_BRICK_SLAB, 0.103f);
        tauxSpawnItem.put(Material.NETHER_BRICK_STAIRS, 0.104f);
        tauxSpawnItem.put(Material.NETHER_BRICKS, 0.105f);
        tauxSpawnItem.put(Material.NETHER_BRICK, 0.106f);
        tauxSpawnItem.put(Material.BLACKSTONE_STAIRS, 0.111f);
        tauxSpawnItem.put(Material.BASALT, 0.12f);
        tauxSpawnItem.put(Material.SOUL_SOIL, 0.125f);
        tauxSpawnItem.put(Material.SOUL_SAND, .02f);
        tauxSpawnItem.put(Material.SOUL_LANTERN, 0.2f);

        //end
        tauxSpawnItem.put(Material.ELYTRA, 0.000000001f);
        tauxSpawnItem.put(Material.DRAGON_HEAD, 0.00001f);
        tauxSpawnItem.put(Material.END_CRYSTAL, 0.0009f);
        tauxSpawnItem.put(Material.CHORUS_FLOWER, 0.005f);
        tauxSpawnItem.put(Material.CHORUS_FRUIT, 0.02f);
        tauxSpawnItem.put(Material.CHORUS_PLANT, 0.02f);
        tauxSpawnItem.put(Material.ENDER_EYE, 0.04f);
        tauxSpawnItem.put(Material.ENDER_PEARL, 0.09f);
        tauxSpawnItem.put(Material.PURPUR_BLOCK, 0.01f);
        tauxSpawnItem.put(Material.END_STONE, 50f);
        tauxSpawnItem.put(Material.END_STONE_BRICK_SLAB, 50f);
        tauxSpawnItem.put(Material.END_STONE_BRICK_STAIRS, 50f);
        tauxSpawnItem.put(Material.END_STONE_BRICK_WALL, 50f);
        tauxSpawnItem.put(Material.END_STONE_BRICKS, 50f);

        //world

        //enchant

        Player player = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("event") || cmd.getName().equalsIgnoreCase("e") && player.isOp())
        {
            if(disablePlugin == false)
            {

                int xyz = 25501;
                Random r = new Random();
                int turn = r.nextInt(27);
                double x = 0 + r.nextInt(xyz);
                double y = 0 + r.nextInt(100);
                double z = 0 + r.nextInt(xyz);

                Location spawnChest = new Location(Bukkit.getWorld("world"), x, y, z);

                if(y > 4)
                {
                    spawnChest.getBlock().setType(Material.CHEST);
                }

                Bukkit.broadcastMessage("§eUn coffre a spawn en x: " + x + " y: " + y + " z: " + z + "\n§r------------------------------\n§6Nombre(s) d'item(s) " + turn);
                Chest chest = (Chest) spawnChest.getBlock().getState();
                Inventory chestMenu = chest.getInventory();


                for(int i = 0; i < turn; i++)
                {
                    int number = r.nextInt((65 + turn) / 2);
                    float percent = (float) Math.random();
                    for(Map.Entry<Material, Float> material :tauxSpawnItem.entrySet())
                    {
                        if(material.getValue() >= percent && material.getValue() <= percent + 0.02 && number >= 1)
                        {
                            chestMenu.setItem(getRandomSlot(chestMenu, r), new ItemStack(material.getKey(), number));
                            Bukkit.broadcastMessage("§2L'item généré : §c" + material.getKey() + " §9: " + number + " §2slot: " + "§9" + getRandomSlot(chestMenu, r) + " §2Taux de chance :§9" + percent * 100 + "§2%");
                        }
                    }
                }
                Bukkit.broadcastMessage("§r------------------------------\n§rLa génération est terminée !");
                if(player.isOp())
                {
                    player.teleport(spawnChest);
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
        return false;
    }

    public int getRandomSlot(Inventory inventory, Random random)
    {
        int rand = random.nextInt(inventory.getSize());
        final ItemStack item = inventory.getItem(rand);
        if (item != null && item.getType() != Material.AIR)
        {
            return getRandomSlot(inventory, random);
        }
        return rand;
    }

}