package com.HauvongMC.Modules.Enchant.Enchants;

import java.util.Arrays;
import java.util.List;

public class Creative {

    public static String getPiority() {
        return "Common";
    }
    public static String  getName() {
        return "§9Sáng tạo";
    }
    public static int getMaxLevel() {
        return 3;
    }

    public static List<String> getLore() {
        return Arrays.asList("§7Nhận được %x khối gỗ khi hồi sinh!");
    }

}
