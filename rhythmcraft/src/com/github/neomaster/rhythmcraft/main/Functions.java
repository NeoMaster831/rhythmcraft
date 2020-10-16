package com.github.neomaster.rhythmcraft.main;

import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public final class Functions {

    public static final ArrayList<File> getSongsFile() {

        File fileRoot = new File("plugins/RhythmCraft/songs");
        File[] files = fileRoot.listFiles();
        ArrayList<File> toReturn = new ArrayList<File>();

        for (File f : files) {
            toReturn.add(f);
        }

        return toReturn;
    }

    public static ItemStack createItem(Inventory inv, String materialID, int amount, int invSlot, String displayName, String... loreString) {

        ItemStack item;
        ArrayList<String> lore = new ArrayList<String>();

        item = new ItemStack(Material.getMaterial(materialID), amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtils.regularCodeChat(displayName));
        for (String s : loreString) {
            lore.add(ChatUtils.regularCodeChat(s));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        inv.setItem(invSlot, item);
        return item;
    }

    public static void addPlayer(HashMap<Player, ArrayList<Player>> l, Player dj, Player regex)  {

        ArrayList<Player> oldPlayers = l.get(dj);
        oldPlayers.add(regex);
        l.remove(dj); l.put(dj, oldPlayers);

    }
}
