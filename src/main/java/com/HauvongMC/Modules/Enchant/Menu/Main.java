package com.HauvongMC.Modules.Enchant.Menu;

import com.HauvongMC.Modules.Enchant.Enchants.Creative;
import com.HauvongMC.Modules.Enchant.Enchants.Cricket;
import io.netty.util.internal.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Main implements Listener {
    private static HashMap<Player, Inventory> Inventories = new HashMap<>();
    private static HashMap<Player, Integer> Loops = new HashMap<>();
    private static HashMap<Player, Boolean> countdown = new HashMap<>();
    static List<Integer> slots = new ArrayList<>();
    ItemMeta itemMeta;
    List<String> lores;
    private static ItemStack blackglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
    private static ItemStack pinkglass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 2);
    private static ItemStack orange = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
    private static ItemStack lime = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
    private static ItemStack blue = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
    private static ItemStack yellow = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
    private static List<ItemStack> glasss = new ArrayList<>();
    private static ItemStack enchanttable;
    private static ItemMeta tablemeta;
    int[] slotneedtoupdate = new int[]{10, 11, 12, 21, 30, 29, 28, 19, 20};
    static List<String> Pants_Enchantment = Arrays.asList(Creative.getName());
    List<ItemStack> inksnacks = Arrays.asList(new ItemStack[]{
            new ItemStack(Material.INK_SACK, 1, (short) 1),
            new ItemStack(Material.INK_SACK, 1, (short) 2),
            new ItemStack(Material.INK_SACK, 1, (short) 3),
            new ItemStack(Material.INK_SACK, 1, (short) 4),
            new ItemStack(Material.INK_SACK, 1, (short) 5),
            new ItemStack(Material.INK_SACK, 1, (short) 6),
            new ItemStack(Material.INK_SACK, 1, (short) 9),
            new ItemStack(Material.INK_SACK, 1, (short) 10),
            new ItemStack(Material.INK_SACK, 1, (short) 11),
            new ItemStack(Material.INK_SACK, 1, (short) 12),
            new ItemStack(Material.INK_SACK, 1, (short) 13),
            new ItemStack(Material.INK_SACK, 1, (short) 14)

    });
    @EventHandler
    public void interact(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.hasBlock()) {
                if (event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE)) {
                    openMain(event.getPlayer());
                }
            }
        }
    }
    @EventHandler
    public void invclose(InventoryCloseEvent event) {
        if (event.getInventory().getName().equalsIgnoreCase("Mystic Well")) {
            getItemback((Player) event.getPlayer(), event.getInventory());
            countdown.put((Player) event.getPlayer(), true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(com.HauvongMC.Main.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    countdown.put((Player) event.getPlayer(), false);
                }
            }, 20*3);
        } else if (event.getInventory().getName().equalsIgnoreCase("Mystic Well | Hoàn thành")) {
            getItemback((Player) event.getPlayer(), event.getInventory());
        }
    }

    @EventHandler
    public void playerleave(PlayerQuitEvent event) {
        if (event.getPlayer().getOpenInventory() != null && event.getPlayer().getOpenInventory().getTitle().equalsIgnoreCase("Mystic Well")) {
            getItemback((Player) event.getPlayer(), (Inventory) event.getPlayer().getOpenInventory());
            countdown.remove(event.getPlayer());
        } else if (event.getPlayer().getOpenInventory().getTitle().equalsIgnoreCase("Mystic Well | Hoàn thành")) {
            getItemback((Player) event.getPlayer(), (Inventory) event.getPlayer().getOpenInventory());
        }
    }



    @EventHandler
    public void playerinvclick(InventoryClickEvent event) {
        if (event.getInventory().getName().equalsIgnoreCase("Mystic Well") || event.getInventory().getName().equalsIgnoreCase("Mystic Well | Hoàn thành")) {
            event.setCancelled(true);
            Player p = (Player) event.getWhoClicked();
            if (p != null) {
                /*if (event.getClick().equals(ClickType.SHIFT_LEFT) || event.getClick().equals(ClickType.SHIFT_RIGHT) || event.getClick().equals(ClickType.DOUBLE_CLICK)
                    || event.getClick().equals(ClickType.NUMBER_KEY) || event.getClick().equals(ClickType.WINDOW_BORDER_LEFT) || event.getClick().equals(ClickType.CONTROL_DROP)
                    || event.getClick().equals(ClickType.MIDDLE) || event.getClick().equals(ClickType.UNKNOWN)) {
                    event.setCancelled(true);
                    return;
                }*/
                if (event.getClickedInventory() !=  null && event.getClickedInventory().getName() != null && (event.getClickedInventory().getName().equalsIgnoreCase("Mystic Well") || event.getClickedInventory().getName().equalsIgnoreCase("Mystic Well | Hoàn thành"))) {
                    if (event.getSlot() != 20) {
                        event.setCancelled(true);
                        if (event.getSlot() == 24) {
                            ItemStack itemStack = p.getOpenInventory().getItem(20);
                            p.getOpenInventory().setItem(20, new ItemStack(Material.AIR));
                            if (itemStack != null && (itemStack.getType().toString().endsWith("LEGGINGS") || itemStack.getType().toString().endsWith("SWORD") || itemStack.getType().toString().endsWith("BOW") || itemStack.getType().toString().endsWith("AXE"))) {
                                cancelTask(p);
                                final Inventory inventory = Bukkit.createInventory(p, 54, "Mystic Well | Đang quay");
                                enchanttable = new ItemStack(Material.ENCHANTMENT_TABLE, 1);
                                tablemeta = enchanttable.getItemMeta();
                                List<String> lore = new ArrayList<>();
                                lore.add("§7Quay đều quay đều...");//5
                                tablemeta.setDisplayName("§dMystic Well");
                                tablemeta.setLore(lore);
                                enchanttable.setItemMeta(tablemeta);
                                inventory.setItem(24, enchanttable);
                                p.openInventory(inventory);
                                int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(com.HauvongMC.Main.getPlugin(), new Runnable() {
                                    ItemStack completed = Đập(itemStack);
                                    int i = 1;
                                    @Override
                                    public void run() {
                                        Collections.shuffle(glasss);
                                        Collections.shuffle(inksnacks);
                                        if (i < 5) {
                                            if (i % 2 == 0) {// i = 2, 4 - green
                                                for (int x = 0; x < slotneedtoupdate.length; x++) {
                                                    p.getOpenInventory().setItem(slotneedtoupdate[x], blackglass);
                                                }
                                            } else {
                                                for (int x = 0; x < slotneedtoupdate.length; x++) {
                                                    p.getOpenInventory().setItem(slotneedtoupdate[x], lime);
                                                }
                                            }
                                        } else {
                                            if (i == 5 || i == 13 || i == 21) {
                                                p.getOpenInventory().setItem(19, blackglass);
                                                p.getOpenInventory().setItem(10, glasss.get(0));
                                                p.getOpenInventory().setItem(20, inksnacks.get(0));
                                            } else if (i == 6 || i == 14 || i == 22) {
                                                p.getOpenInventory().setItem(10, blackglass);
                                                p.getOpenInventory().setItem(11, glasss.get(0));
                                                p.getOpenInventory().setItem(20, inksnacks.get(0));
                                            } else if (i == 7 || i == 15 || i == 23) {
                                                p.getOpenInventory().setItem(11, blackglass);
                                                p.getOpenInventory().setItem(12, glasss.get(0));
                                                p.getOpenInventory().setItem(20, inksnacks.get(0));
                                            } else if (i == 8 || i == 16 || i == 24) {
                                                p.getOpenInventory().setItem(12, blackglass);
                                                p.getOpenInventory().setItem(21, glasss.get(0));
                                                p.getOpenInventory().setItem(20, inksnacks.get(0));
                                            } else if (i == 9 || i == 17 || i == 25) {
                                                p.getOpenInventory().setItem(21, blackglass);
                                                p.getOpenInventory().setItem(30, glasss.get(0));
                                                p.getOpenInventory().setItem(20, inksnacks.get(0));
                                            } else if (i == 10 || i == 18 || i == 26) {
                                                p.getOpenInventory().setItem(30, blackglass);
                                                p.getOpenInventory().setItem(29, glasss.get(0));
                                                p.getOpenInventory().setItem(20, inksnacks.get(0));
                                            } else if (i == 11 || i == 19 || i == 27) {
                                                p.getOpenInventory().setItem(29, blackglass);
                                                p.getOpenInventory().setItem(28, glasss.get(0));
                                                p.getOpenInventory().setItem(20, inksnacks.get(0));
                                            } else if (i == 12 || i == 20 || i == 28) {
                                                p.getOpenInventory().setItem(28, blackglass);
                                                p.getOpenInventory().setItem(19, glasss.get(0));
                                                p.getOpenInventory().setItem(20, inksnacks.get(0));
                                            }
                                        }
                                        i++;
                                        if (i == 29) {
                                            p.playSound(p.getLocation(), Sound.ANVIL_USE, 1f, 1f);
                                            p.playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 3);
                                            cancelTask(p);
                                            Inventory inventory = Bukkit.createInventory(p, 54, "Mystic Well | Hoàn thành");
                                            for (int x = 0; x < slotneedtoupdate.length; x++) {
                                                inventory.setItem(slotneedtoupdate[x], blackglass);
                                            }
                                            Inventories.replace(p, inventory);
                                            inventory.setItem(20, completed);
                                            /** ENCHANT TABLE **/
                                            int tier = 0;
                                            boolean check = false;
                                            if (completed != null && completed.getItemMeta() != null && completed.getItemMeta().getLore() != null) {
                                                List<String> lores = completed.getItemMeta().getLore();
                                                for (String lore : lores) {
                                                    if (lore.contains("§7Số lần đã đập:§c ")) {
                                                        tier = Integer.parseInt(lore.replace("§7Số lần đã đập:§c ", ""));
                                                        check = true;
                                                    }
                                                }
                                            }
                                            tier++;
                                            double price = ((tier * 1000) * 1.8) + 1000;
                                            enchanttable = new ItemStack(Material.ENCHANTMENT_TABLE, 1);
                                            tablemeta = enchanttable.getItemMeta();
                                            List<String> lore = new ArrayList<>();
                                            lore.add("§7Nâng cấp:§a Cấp " + tier);//0
                                            lore.add("§7Giá:§6 " + price + "g");//1
                                            lore.add("");//2
                                            lore.add("§eNhấp để tiếp!");//3
                                            tablemeta.setDisplayName("§dMystic Well");
                                            tablemeta.setLore(lore);
                                            enchanttable.setItemMeta(tablemeta);
                                            inventory.setItem(24, enchanttable);
                                            p.openInventory(inventory);
                                            /** **/
                                            int TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(com.HauvongMC.Main.getPlugin(), new Runnable() {
                                                int i = 0;
                                                ItemStack glass = glasss.get(0);
                                                @Override
                                                public void run() {
                                                    if (p.getOpenInventory() != null && p.getOpenInventory().getTitle().equalsIgnoreCase("Mystic Well | Hoàn thành")) {
                                                        int last = i -1;
                                                        if (last < 0) last = slots.size() - 1;
                                                        p.getOpenInventory().setItem(slots.get(last), blackglass);
                                                        p.getOpenInventory().setItem(slots.get(i), glass);
                                                        i++;
                                                        if (i == slots.size()) i = 0;
                                                        p.updateInventory();
                                                    } else cancelTask(p);

                                                }
                                            }, 0, 5);
                                            Loops.put(p, TaskID);
                                        }
                                        if (p.getOpenInventory() != null && p.getOpenInventory().getTitle().equalsIgnoreCase("Mystic Well | Đang quay")) {
                                            p.updateInventory();
                                            Inventories.replace(p, inventory);
                                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 1f, 1f);
                                        }
                                    }
                                },0, 5);
                                p.openInventory(inventory);
                                Loops.replace(p, taskid);
                                p.updateInventory();
                            } else {
                                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 0.5f, 0.5f);
                                p.sendMessage("§cVui lòng đặt vật phẩm cần đập vào ô bên cạnh!");
                            }
                        }
                    } else {
                        if (p.getOpenInventory().getItem(20) != null && p.getOpenInventory().getItem(20) .getType().toString().equalsIgnoreCase("AIR")) return;
                        enchanttable = new ItemStack(Material.ENCHANTMENT_TABLE, 1);
                        tablemeta = enchanttable.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore.add("§7Bạn có thể bỏ hàng của bạn vào");//0
                        lore.add("§7và đập nó lên mạnh hơn hoặc cùi hơn");//1
                        lore.add("");//2
                        lore.add("§7Những Enchant trong đây giúp bạn");//3
                        lore.add("§7có thêm buff nhưng chưa tới mức phá game");//4
                        lore.add("§dBỏ hàng vào ô ở giữa rồi ấn vào đây để quay");//5
                        tablemeta.setDisplayName("§dMystic Well");
                        tablemeta.setLore(lore);
                        enchanttable.setItemMeta(tablemeta);
                        ItemStack itemStack = p.getOpenInventory().getItem(20);
                        p.getInventory().addItem(itemStack);
                        p.getOpenInventory().setItem(20, new ItemStack(Material.AIR));
                        p.getOpenInventory().setItem(24, enchanttable);
                        p.updateInventory();
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.5f, 0.5f);
                    }
                } else {
                    ItemStack itemStack = event.getCurrentItem();
                    if (itemStack == null) return;
                    if (itemStack != null && itemStack.getType().toString().equalsIgnoreCase("AIR")) return;
                    if (itemStack.getType().toString().endsWith("LEGGINGS") || itemStack.getType().toString().endsWith("SWORD") || itemStack.getType().toString().endsWith("BOW") || itemStack.getType().toString().endsWith("AXE")) {
                        ItemStack lol = p.getOpenInventory().getItem(20);
                        p.getInventory().remove(itemStack);
                        p.getOpenInventory().setItem(20, itemStack);
                        p.getInventory().addItem(lol);
                        int tier = 0;
                        boolean check = false;
                        if (itemStack != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getLore() != null) {
                            List<String> lores = itemStack.getItemMeta().getLore();
                            for (String lore : lores) {
                                if (lore.contains("§7Số lần đã đập:§c ")) {
                                    tier = Integer.parseInt(lore.replace("§7Số lần đã đập:§c ", ""));
                                    check = true;
                                }
                            }
                        }
                        tier++;
                        double price = ((tier * 1000) * 1.8) + 1000;
                        enchanttable = new ItemStack(Material.ENCHANTMENT_TABLE, 1);
                        tablemeta = enchanttable.getItemMeta();
                        List<String> lore = new ArrayList<>();
                        lore.add("§7Nâng cấp:§a Cấp " + tier);//0
                        lore.add("§7Giá:§6 " + price + "g");//1
                        lore.add("");//2
                        lore.add("§eNhấp để đập!");//3
                        tablemeta.setDisplayName("§dMystic Well");
                        tablemeta.setLore(lore);
                        enchanttable.setItemMeta(tablemeta);
                        p.getOpenInventory().setItem(24, enchanttable);
                        p.updateInventory();
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.5f, 0.5f);
                    } else {
                        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 0.5f, 0.5f);
                        if (itemStack == null) {
                            p.sendMessage("§cMày thích bấm bừa ko? Tao bắn mày chết đó!");
                            return;
                        }
                        p.sendMessage("§cVật phẩm này không thể đập!");
                    }
                }
            }
        } else if (event.getInventory().getName().equalsIgnoreCase("Mystic Well | Đang quay")) {
            event.setCancelled(true);
        }
    }
    Map<String, Integer> encs = com.HauvongMC.Modules.Enchant.Enchants.Main.getEnchants();
    List<String> encss;
    String enc;
    int maxlv;
    private ItemStack Đập(ItemStack itemStack) {
        itemStack = com.HauvongMC.Modules.Enchant.Enchants.Main.removeEnchant(itemStack);
        lores = new ArrayList<>();
        if (itemStack != null) {
            if (itemStack.hasItemMeta() && itemStack.getItemMeta().getLore() != null) {
                itemMeta = itemStack.getItemMeta();
                int tier = 0;
                boolean check = false;
                lores = itemStack.getItemMeta().getLore();
                for (int i = 0; i < lores.size(); i++) {
                    if (lores.get(i).contains("§7Số lần đã đập:§c ")) {
                        tier = Integer.parseInt(lores.get(i).replace("§7Số lần đã đập:§c ", ""));
                        tier++;
                        lores.set(i, "§7Số lần đã đập:§c " + tier);
                        check = true;
                        break;
                    }
                }
                tier++;
                if (check == false) {
                    for (int i = 0; i < lores.size(); i++) {
                        int x = i + 1;
                        lores.set(x, lores.get(i));
                    }
                    lores.set(0, "§7Số lần đã đập:§c " + tier);
                }
                itemMeta.setLore(lores);
                itemStack.setItemMeta(itemMeta);
            } else {
                itemMeta = itemStack.getItemMeta();
                int tier = 1;
                lores = new ArrayList<>();
                lores.add("§7Số lần đã đập:§c " + tier);
                lores.add("");
            }
            if (itemStack.getType().toString().endsWith("LEGGINGS")) {
                int randomNum = ThreadLocalRandom.current().nextInt(1, 5);
                for (int i = 0; i < randomNum; i++) {
                    encss = new ArrayList<>(encs.keySet());
                    Collections.shuffle(encss);
                    enc = encss.get(0);
                    maxlv = encs.get(enc);
                    int randomlv = ThreadLocalRandom.current().nextInt(1, maxlv + 1);
                    boolean check = false;
                    for (String l : lores) {
                        if (l != null && l.contains(enc)) check = true;
                    }
                    if (check == false) {
                        lores.add("");
                        lores.add(enc + " " + randomlv);
                        for (String s : com.HauvongMC.Modules.Enchant.Enchants.Main.getLore(enc)) {
                            if (enc.equalsIgnoreCase(Creative.getName())) s = s.replace("%x", 16*randomlv + "");
                            if (enc.equalsIgnoreCase(Cricket.getName())) {
                                int chance = 3;
                                switch (randomlv) {
                                    case 0:
                                        chance = 3;
                                        break;
                                    case 1:
                                        chance = 7;
                                        break;
                                    case 3:
                                        chance = 15;
                                        break;
                                }
                                s = s.replace("%x", chance + "");
                            }
                            lores.add(s);
                        }
                    }

                }
            } else {

            }
        }
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    public static void start() {
        glasss.add(blackglass);
        glasss.add(pinkglass);
        glasss.add(orange); glasss.add(lime); glasss.add(blue); glasss.add(yellow);
    }

    private static void openMain(Player p) {
        if (countdown.containsKey(p)) {
            if (countdown.get(p) == true) {
                p.sendMessage("§cVui lòng đợi 3s");
                return;
            }
        } else countdown.put(p, false);
        Collections.shuffle(glasss);
        ItemStack glass = glasss.get(0);
        Inventory inventory = Inventories.get(p);
        if (inventory == null) {
            inventory = Bukkit.createInventory(p, 54, "Mystic Well");
            Inventories.put(p, inventory);
        }
        if (inventory.getName().equalsIgnoreCase("Mystic Well") || inventory.getName().contains("Hoàn thành")) {
            inventory = Bukkit.createInventory(p, 54, "Mystic Well");
            slots.add(10);
            slots.add(11);
            slots.add(12);
            slots.add(21);
            slots.add(30);
            slots.add(29);
            slots.add(28);
            slots.add(19);
            for (int i = 0; i < slots.size(); i++) {
                inventory.setItem(slots.get(i), blackglass);
            }
            Inventory finalInventory = inventory;
            cancelTask(p);
            int TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(com.HauvongMC.Main.getPlugin(), new Runnable() {
                int i = 0;
                @Override
                public void run() {
                    if (p.getOpenInventory() != null && p.getOpenInventory().getTitle().equalsIgnoreCase("Mystic Well")) {
                        int last = i -1;
                        if (last < 0) last = slots.size() - 1;
                        finalInventory.setItem(slots.get(last), blackglass);
                        finalInventory.setItem(slots.get(i), glass);
                        i++;
                        if (i == slots.size()) i = 0;
                        p.updateInventory();
                    } else cancelTask(p);

                }
            }, 0, 5);
            Loops.put(p, TaskID);
            enchanttable = new ItemStack(Material.ENCHANTMENT_TABLE, 1);
            tablemeta = enchanttable.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("§7Bạn có thể bỏ hàng của bạn vào");//0
            lore.add("§7và đập nó lên mạnh hơn hoặc cùi hơn");//1
            lore.add("");//2
            lore.add("§7Những Enchant trong đây giúp bạn");//3
            lore.add("§7có thêm buff nhưng chưa tới mức phá game");//4
            lore.add("§dBỏ hàng vào ô ở giữa rồi ấn vào đây để quay");//5
            tablemeta.setDisplayName("§dMystic Well");
            tablemeta.setLore(lore);
            enchanttable.setItemMeta(tablemeta);
            inventory.setItem(24, enchanttable);

        }
        p.openInventory(inventory);
    }

    private static void cancelTask(Player p) {
        if (Loops.containsKey(p)) Bukkit.getScheduler().cancelTask(Loops.get(p));
    }

    private static void getItemback(Player p, Inventory inventory) {
        /*if (inventory.getItem(20) != null) {
            p.getInventory().addItem(inventory.getItem(20));
            inventory.setItem(20, new ItemStack(Material.AIR));
        }*/
        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack != null && !itemStack.getType().equals(Material.STAINED_GLASS_PANE) && !itemStack.getType().equals(Material.ENCHANTMENT_TABLE)) {
                p.getInventory().addItem(itemStack);
            }
        }
        inventory.clear();
        Inventories.replace(p, inventory);
    }
}
