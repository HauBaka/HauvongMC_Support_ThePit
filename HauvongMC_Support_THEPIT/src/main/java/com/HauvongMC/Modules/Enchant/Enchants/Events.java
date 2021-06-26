package com.HauvongMC.Modules.Enchant.Enchants;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Events implements Listener {
    ItemStack leggings;
    List<String> lores;
    //create
    @EventHandler
    public void playerdeath(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (p.getInventory().getLeggings() != null) {
            leggings = p.getInventory().getLeggings();
            if (leggings.hasItemMeta()) {
                lores = leggings.getItemMeta().getLore();
                for (String lore : lores) {
                    if (lore.contains(Creative.getName())) {
                        int level = Integer.parseInt(lore.replace(Main.getPiority(Creative.getName()) + Creative.getName(), ""));
                        event.getPlayer().getInventory().addItem(new ItemStack(Material.WOOD, level*16));
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerdmg(EntityDamageByEntityEvent event) {
        if (event.getEntityType().equals(EntityType.PLAYER) || event.getDamager().getType().equals(EntityType.PLAYER)) {
            Player victim = (Player) event.getEntity();
            Player dmger = (Player) event.getDamager();
            if (victim != null && dmger != null) {
                //do st
            }
        }
    }
}
