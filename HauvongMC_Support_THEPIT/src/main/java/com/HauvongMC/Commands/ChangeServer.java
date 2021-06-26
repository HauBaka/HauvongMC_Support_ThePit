package com.HauvongMC.Commands;

import com.HauvongMC.Listener.BungeeListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeServer implements CommandExecutor {
    private BungeeListener bungeeListener = new BungeeListener();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("switchserver") || label.equalsIgnoreCase("ss") || label.equalsIgnoreCase("changeserver") || label.equalsIgnoreCase("cs")) {
            if (!(sender instanceof Player)) return false;
            Player p = ((Player) sender).getPlayer();
            if (args.length == 1) {
                bungeeListener.SendtoServer(p, args[0]);
                p.sendMessage("§aĐang chuyển bạn đến máy chủ " + args[0]);
                return true;
            } else if (args.length == 2) {
                if (p.hasPermission("Changserver.server.player")) {
                    bungeeListener.SendtoServer(Bukkit.getPlayer(args[1]), args[0]);
                    Bukkit.getPlayer(args[1]).sendMessage("Thg " + p.getName() + " ném m sang server " + args[0]);
                    return true;
                }
                p.sendMessage("§cBạn đéo có quyền sử dụng lệnh này");
            } else {
                p.sendMessage("§9--------------------------------------------------");
                p.sendMessage("§aChange Server Commands:");
                p.sendMessage("§e/" + label + " (server) §7- §bChuyển máy chủ");
                p.sendMessage("§e/" + label + " (server) (player) §7-§b Chyển máy chủ của 1 thg ml nào đó");
                p.sendMessage("§9--------------------------------------------------");
            }
        }
        return true;
    }
}
