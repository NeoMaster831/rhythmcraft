package com.github.neomaster.rhythmcraft.listener;

import com.github.neomaster.rhythmcraft.main.GamePlayVariables;
import com.github.neomaster.rhythmcraft.main.Startup;
import com.github.neomaster.rhythmcraft.ui.GameStartUI;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SetWorldUI implements Listener {

    Startup plugin;
    public SetWorldUI(Startup plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();

        if (title.equals(com.github.neomaster.rhythmcraft.ui.SetWorldUI.inv_name)) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType().equals(Material.GRASS_BLOCK)) {

                String worldName = e.getCurrentItem().getItemMeta().getDisplayName();
                GamePlayVariables.PlayWorld.remove(e.getWhoClicked());

                GamePlayVariables.PlayWorld.put((Player)e.getWhoClicked(), Bukkit.getWorld(worldName));
                e.getWhoClicked().sendMessage(ChatUtils.chat("월드가 설정되었습니다. [" + worldName + "]", "#BDFFBD"));

            }

            else {
                return;
            }
        }
    }
}
