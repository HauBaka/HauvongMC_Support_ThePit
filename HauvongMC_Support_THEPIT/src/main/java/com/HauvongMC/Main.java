package com.HauvongMC;

import com.HauvongMC.Commands.ChangeServer;
import com.HauvongMC.Commands.Reboot;
import com.HauvongMC.Events.DapLua;
import com.HauvongMC.Events.FixPhaLua;
import com.HauvongMC.Events.RemoveArrow;
import com.HauvongMC.Listener.BungeeListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public static ItemStack Wheat;
    private ItemMeta itemMeta;
    private static Plugin plugin;
    @Override
    public void onEnable() {
        plugin  = this;
        Logger logger = Bukkit.getLogger();
        logger.info("Đm tao sống lại r");
        RegisterEvents();
        Wheat = new ItemStack(Material.WHEAT);
        itemMeta = Wheat.getItemMeta();
        itemMeta.setDisplayName("§eLúa");
        Wheat.setItemMeta(itemMeta);
        com.HauvongMC.Modules.Reboot.StartReboot(10800, "Tự động khởi động!");
        this.getCommand("Reboot").setExecutor(new Reboot());
        this.getCommand("changeserver").setExecutor(new ChangeServer());
        this.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", new BungeeListener());
        com.HauvongMC.Modules.Enchant.Menu.Main.start();
    }

    private void RegisterEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new FixPhaLua(),  this);
        pluginManager.registerEvents(new DapLua(),  this);
        pluginManager.registerEvents(new RemoveArrow(),  this);
        pluginManager.registerEvents(new com.HauvongMC.Modules.Enchant.Menu.Main(),  this);
    }

    private void registercommands() {

    }

    public static ItemStack getWheat() {
        return Wheat;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {

    }
}
