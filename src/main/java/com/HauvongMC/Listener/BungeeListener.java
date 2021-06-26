package com.HauvongMC.Listener;

import com.HauvongMC.Main;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeListener implements PluginMessageListener {
    public static String[] arrays = {};
    public static String string;
    public static int number;
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!(channel.equalsIgnoreCase("BungeeCord"))) {
            return;
        }
        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subchannel = input.readUTF();
        if (subchannel.equalsIgnoreCase("GetServer")) {
            string = input.readUTF();
        }
        if (subchannel.equalsIgnoreCase("PlayerList")) {
            string = input.readUTF();
            arrays = input.readUTF().split(", ");
        }
        if (subchannel.equalsIgnoreCase("PlayerCount")) {
            string = input.readUTF();
            number = input.readInt();
        }
        if (subchannel.equalsIgnoreCase("Connect")) return;
    }

    public static String[] getArrays() {
        return arrays;
    }
    public static String getString() {
        return string;
    }
    public static int getNumber() {
        return number;
    }


    public void SendtoServer(Player p, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        p.sendPluginMessage(Main.getPlugin(), "BungeeCord", output.toByteArray());
    }

    public void SendMessage(Player sender, String receiver, String message) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Message");
        output.writeUTF(receiver);
        output.writeUTF(message);
        sender.sendPluginMessage(Main.getPlugin(), "BungeeCord", output.toByteArray());
    }

    public void GetServers(Player sender) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("GetServers");
        sender.sendPluginMessage(Main.getPlugin(), "BungeeCord", output.toByteArray());
    }

    public void GetPlayers(Player sender) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerList");
        output.writeUTF("ALL");
        sender.sendPluginMessage(Main.getPlugin(), "BungeeCord", output.toByteArray());
    }

    public void GetServer(Player sender) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("GetServer");
        sender.sendPluginMessage(Main.getPlugin(), "BungeeCord", output.toByteArray());
    }

    public void GetnumberofPlayer(Player sender, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerCount");
        output.writeUTF(server);
        sender.sendPluginMessage(Main.getPlugin(), "BungeeCord", output.toByteArray());
    }
}