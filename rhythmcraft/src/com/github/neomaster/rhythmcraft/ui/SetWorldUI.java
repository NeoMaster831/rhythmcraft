package com.github.neomaster.rhythmcraft.ui;

import com.github.neomaster.rhythmcraft.main.Functions;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SetWorldUI {

    public static Inventory inv;
    public static String inv_name;
    public static int inv_rows = 3 * 9;

    public static void intialize() {
        inv_name = ChatUtils.regularCodeChat("&6Set world");
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory StartUI(Player p) {

        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);

        for (int i =0; i <  Bukkit.getWorlds().size(); i++) {

            Functions.createItem(inv, Material.GRASS_BLOCK.toString(), 1, i, Bukkit.getWorlds().get(i).getName(), "&7Click here to Set World to this!");
        }

        toReturn.setContents(inv.getContents());
        return toReturn;
    }
}
