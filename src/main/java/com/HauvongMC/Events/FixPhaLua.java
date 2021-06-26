package com.HauvongMC.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class FixPhaLua implements Listener {
    @EventHandler
    public void phalua(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)) {
            if (event.hasBlock()) {
                if (event.getClickedBlock().getType().equals(Material.WHEAT) || event.getClickedBlock().getType().equals(Material.SEEDS) || event.getClickedBlock().getType().equals(Material.CROPS) || event.getClickedBlock().getType().equals(Material.SOIL)) event.setCancelled(true);
            }
        }
    }
}
