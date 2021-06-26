package com.HauvongMC.Events;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class RemoveArrow implements Listener {
    @EventHandler
    public void playerdeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        if (p != null) {
            ((CraftPlayer)p).getHandle().getDataWatcher().watch(9, (byte) 0);
        }
    }
}
