package com.HauvongMC.Modules;

import com.HauvongMC.API.Reflection;
import com.HauvongMC.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
public class Reboot {
    private static int taskid;
    public static void StartReboot(int timeleft, String message) {
        Reflection.JSONMessage text = new Reflection.JSONMessage("");
        Reflection.JSONMessage.ChatExtra chatExtra = new Reflection.JSONMessage.ChatExtra("§eBạn còn lại §a" + timeleft + " giây§e! ");
        Reflection.JSONMessage.ChatExtra chatmessage = new Reflection.JSONMessage.ChatExtra("§a§lNHẤP VÀO ĐÂY");
        Reflection.JSONMessage.ChatExtra chatmessage1 = new Reflection.JSONMessage.ChatExtra("§e để dịch chuyển đến sảnh chính!");
        chatmessage.addClickEvent(Reflection.JSONMessage.ClickEventType.RUN_COMMAND, "/changeserver Lobby");
        chatmessage.addHoverEvent(Reflection.JSONMessage.HoverEventType.SHOW_TEXT, "§7Nhấp để dịch chuyển đến sảnh chính!");
        text.addExtra(chatExtra);
        text.addExtra(chatmessage);
        text.addExtra(chatmessage1);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§c[Thông báo] §eMáy chủ sẽ khởi động lại:§b " + message);
            Reflection.sendChatPacket(p, text.toString());
            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1f, 1f);
        }
        int time = timeleft;
        taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            int lol = time;
            @Override
            public void run() {
                lol--;
                if (lol == 60) {
                    Reflection.JSONMessage text = new Reflection.JSONMessage("");
                    Reflection.JSONMessage.ChatExtra chatExtra = new Reflection.JSONMessage.ChatExtra("§eBạn còn lại §a" + timeleft + " giây§e! ");
                    Reflection.JSONMessage.ChatExtra chatmessage = new Reflection.JSONMessage.ChatExtra("§a§lNHẤP VÀO ĐÂY");
                    Reflection.JSONMessage.ChatExtra chatmessage1 = new Reflection.JSONMessage.ChatExtra("§e để dịch chuyển đến sảnh chính!");
                    chatmessage.addClickEvent(Reflection.JSONMessage.ClickEventType.RUN_COMMAND, "/changeserver Lobby");
                    chatmessage.addHoverEvent(Reflection.JSONMessage.HoverEventType.SHOW_TEXT, "§7Nhấp để dịch chuyển đến sảnh chính!");
                    text.addExtra(chatExtra);
                    text.addExtra(chatmessage);
                    text.addExtra(chatmessage1);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage("§c[Thông báo] §eMáy chủ sẽ khởi động lại:§b " + message);
                        Reflection.sendChatPacket(p, text.toString());
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1f, 1f);
                    }
                }
                if (lol < 6) {
                    canceltask(taskid);
                    return;
                }
                if (lol < 16) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle("", "§c§lKhởi động lại sau §e§l" + lol+"s");
                    }
                }
            }
        }, 0, 20);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            int lol = time;
            @Override
            public void run() {
                lol--;
                if (lol == 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle("§c§l§n" + lol, "§BAW MANNNNNNNNNNN!");
                        p.playSound(p.getLocation(), Sound.EXPLODE, 1f, 1f);
                        p.performCommand("changeserver Lobby");
                    }
                    Bukkit.getServer().shutdown();
                }
                if (lol < 6 && lol > 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle("§c§l§n" + lol, "§bQuay lại sau 5 phút!");
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 1f);
                    }
                }

            }
        }, 0, 20);
    }

    private static void canceltask(int id) {
        Bukkit.getScheduler().cancelTask(id);
    }
}
