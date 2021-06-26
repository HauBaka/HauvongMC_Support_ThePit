package com.HauvongMC.Events;

import com.HauvongMC.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DapLua implements Listener {
    @EventHandler
    public void DapLua(BlockBreakEvent event) {
        Player p = event.getPlayer();
        if (p != null) {
            if(event.getBlock().getType().equals(Material.CROPS) && event.getBlock().getData() == (byte) 7) {
                event.setCancelled(true);
                event.getBlock().setType(Material.CROPS);
                event.getBlock().setData((byte)0);
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.5f, 0.5f);
                event.getBlock().getDrops().clear();
                p.getInventory().addItem(Main.getWheat());
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        event.getBlock().setData((byte)3);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
                            @Override
                            public void run() {
                                event.getBlock().setData((byte) 7);
                            }
                        }, 300);
                    }
                }, 300);
            } else if (event.getBlock().getType().equals(Material.CROPS) && event.getBlock().getData() < 7) {
                event.setCancelled(true);
            }
        }
    }
}
