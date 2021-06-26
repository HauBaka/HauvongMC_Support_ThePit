package com.HauvongMC.Modules;

public class Tool {
    public static boolean containsNumber(String string) {
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c)) return true;
        }
        return false;
    }
}
