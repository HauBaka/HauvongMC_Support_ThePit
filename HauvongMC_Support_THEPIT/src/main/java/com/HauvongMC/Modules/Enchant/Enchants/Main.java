package com.HauvongMC.Modules.Enchant.Enchants;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Main {
    public static String getPiority(String enchant) {
        if (enchant.equalsIgnoreCase(Creative.getName()) || enchant.equalsIgnoreCase(Cricket.getName())) {
            return "§f";
        }
        return null;
    }

    public static String getName(String enchant) {
        if (enchant.contains(Creative.getName())) {
            Creative.getName();
        } else if (enchant.contains(Cricket.getName())) {
            Cricket.getName();
        }
        return null;
    }

    public static List<String> getLore(String enchant) {
        if (enchant.equalsIgnoreCase(Creative.getName())) {
            return Creative.getLore();
        } else if (enchant.equalsIgnoreCase(Cricket.getName())) {
            return Cricket.getLore();
        }
        return null;
    }

    public static ItemStack removeEnchant(ItemStack itemStack) {
        Map<Enchantment, Integer> enchants = itemStack.getEnchantments();
        for (Enchantment enchantment : enchants.keySet()) {
            itemStack.removeEnchantment(enchantment);
        }
        if (itemStack.hasItemMeta()) {
            List<String> lores1 = new ArrayList<>();
            List<String> lores = itemStack.getItemMeta().getLore();
            for (String lore : lores) {
                if (lore.contains(Creative.getName()) || lore.contains("khối gỗ khi hồi sinh!") || lore.equalsIgnoreCase("")) {
                } else {
                    lores1.add(lore);
                }
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(lores1);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    public static Map<String, Integer> getEnchants() {
        Map<String, Integer> enchants = new HashMap<>();
        enchants.put(Creative.getName(), Creative.getMaxLevel());
        enchants.put(Cricket.getName(), Cricket.getMaxLevel());
        return enchants;
    }
}
