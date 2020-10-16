package com.github.neomaster.rhythmcraft.ui;

import com.github.neomaster.rhythmcraft.main.Functions;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class SetSongUI {

    public static Inventory inv;
    public static String inv_name;
    public static int inv_rows = 3 * 9;

    public static void intialize() {
        inv_name = ChatUtils.regularCodeChat("&6Set song");
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory StartUI(Player p) {

        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);

        for (int i =0; i < new File("plugins/RhythmCraft/songs").listFiles().length; i++) {

            Functions.createItem(inv, Material.NETHERITE_INGOT.toString(), 1, i, new File("plugins/RhythmCraft/songs").listFiles()[i].getName().split(".ogg")[0].toLowerCase().replace(' ', '_'), "&7Click here to Set song!");
        }

        toReturn.setContents(inv.getContents());
        return toReturn;
    }
}
