package com.github.neomaster.rhythmcraft.utils;

import net.md_5.bungee.api.ChatColor;

public final class ChatUtils {

    public static String chat(String str, String HexCode) { // "String", "#000000"
        return ChatColor.of(HexCode) + str;
    }

    public static String regularCodeChat(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
