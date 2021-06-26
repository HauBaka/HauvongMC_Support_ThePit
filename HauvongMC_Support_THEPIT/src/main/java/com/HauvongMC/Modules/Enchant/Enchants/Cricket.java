package com.HauvongMC.Modules.Enchant.Enchants;

import java.util.Arrays;
import java.util.List;

public class Cricket {
    public static String getPiority() {
        return "Common";
    }
    public static String  getName() {
        return "§9Cricket";
    }
    public static int getMaxLevel() {
        return 3;
    }

    public static List<String> getLore() {
        return Arrays.asList("§7Nhận -%x% sát thương khi đối thủ của bạn đứng trên khối cỏ!");
    }
}
