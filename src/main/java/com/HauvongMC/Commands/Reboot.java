package com.HauvongMC.Commands;

import com.HauvongMC.Modules.Tool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reboot implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("HauvongMC.Reboot")) {
                if (args.length > 0) {
                    String message = "";
                    for (int i = 1; i  < args.length; i++) {
                        message += args[i] + " ";
                    }
                    if (Tool.containsNumber(args[0])) {
                        com.HauvongMC.Modules.Reboot.StartReboot(Integer.parseInt(args[0]), message);
                        return true;
                    } else {
                        sender.sendMessage("§cThời gian không hợp lệ!");
                    }
                } else sender.sendMessage("§cSử dụng: /Reboot [giây] [tin nhắn]");
            }
        } else {
            if (args.length > 0) {
                String message = "";
                for (int i = 1; i  < args.length; i++) {
                    message += args[i] + " ";
                }
                if (Tool.containsNumber(args[0])) {
                    com.HauvongMC.Modules.Reboot.StartReboot(Integer.parseInt(args[0]), message);
                    return true;
                } else {
                    sender.sendMessage("§cThời gian không hợp lệ!");
                }
            } else sender.sendMessage("§cSử dụng: /Reboot [giây] [tin nhắn]");
        }
        return false;
    }
}
