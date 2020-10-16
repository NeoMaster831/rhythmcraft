package com.github.neomaster.rhythmcraft.ui;

import com.github.neomaster.rhythmcraft.main.Functions;
import com.github.neomaster.rhythmcraft.main.GamePlayVariables;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GameStartUI {

    public static Inventory inv;
    public static String inv_name;
    public static int inv_rows = 3 * 9;

    public static void intialize() {
        inv_name = ChatUtils.regularCodeChat("&5Start Game");
        inv = Bukkit.createInventory(null, inv_rows);
    }

    public static Inventory StartUI(Player p) {

        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inv_name);

        Functions.createItem(inv, Material.CHISELED_POLISHED_BLACKSTONE.toString(), 1 , 11, "&6&lSet World", "&7Set world what will you play.", "&7Click here!", "&7Current value: " + GamePlayVariables.PlayWorld.get(p).getName());
        Functions.createItem(inv, Material.CRACKED_POLISHED_BLACKSTONE_BRICKS.toString(), 1, 4, "&b&lSet Speed", "&7 set the regualar speed will you play.", "&7Click here!", "&7Current value: " + GamePlayVariables.PlaySpeed.get(p));
        Functions.createItem(inv, Material.BLACKSTONE_SLAB.toString(), 1, 22, "&d&lSet Song", "&7Set song abount playing couse.", "&cRESOURCEPACK NEEDED!!", "&7Current value: " + GamePlayVariables.PlaySong.get(p));
        Functions.createItem(inv, Material.POLISHED_BLACKSTONE.toString(), 1, 15, "&d&lP&5&ll&d&la&5&ly&d&l!", "&7Click here to host a game.");

        toReturn.setContents(inv.getContents());
        return toReturn;
    }
}
