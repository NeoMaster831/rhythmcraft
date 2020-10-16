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

public class SetSongUI implements Listener {

    Startup plugin;
    public SetSongUI(Startup plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();

        if (title.equals(com.github.neomaster.rhythmcraft.ui.SetSongUI.inv_name)) {
            e.setCancelled(true);

            if (e.getCurrentItem().getType().equals(Material.NETHERITE_INGOT)) {

                String songName = e.getCurrentItem().getItemMeta().getDisplayName();
                GamePlayVariables.PlaySong.remove(e.getWhoClicked());

                GamePlayVariables.PlaySong.put((Player)e.getWhoClicked(), songName);
                e.getWhoClicked().sendMessage(ChatUtils.chat("노래가 설정되었습니다.. [" + songName + "]", "#BDFFBD"));

            }

            else {
                return;
            }
        }
    }
}
