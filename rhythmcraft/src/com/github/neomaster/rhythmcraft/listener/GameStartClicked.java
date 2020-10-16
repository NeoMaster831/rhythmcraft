package com.github.neomaster.rhythmcraft.listener;

import com.github.neomaster.rhythmcraft.game.GameStart;
import com.github.neomaster.rhythmcraft.game.utils.Variables;
import com.github.neomaster.rhythmcraft.main.GamePlayVariables;
import com.github.neomaster.rhythmcraft.main.Startup;
import com.github.neomaster.rhythmcraft.ui.GameStartUI;
import com.github.neomaster.rhythmcraft.ui.SetSongUI;
import com.github.neomaster.rhythmcraft.ui.SetWorldUI;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GameStartClicked implements Listener {

    Startup plugin;
    public GameStartClicked(Startup plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();

        if (title.equals(GameStartUI.inv_name)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().equals(Material.POLISHED_BLACKSTONE)) { // Start Game

                e.getWhoClicked().closeInventory();
                GameStart.RhythmGameHost((Player)e.getWhoClicked());
            }

            else if (e.getCurrentItem().getType().equals(Material.CHISELED_POLISHED_BLACKSTONE)) { // Set world

                e.getWhoClicked().closeInventory();
                e.getWhoClicked().openInventory(SetWorldUI.StartUI((Player)e.getWhoClicked()));

            }

            else if (e.getCurrentItem().getType().equals(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS)) { // Set Speed

                e.getWhoClicked().closeInventory();
                e.getWhoClicked().sendMessage(ChatUtils.chat("10초 동안 플레이 스피드를 입력해 주세요. [\"back\" = 취소]", "#00FF00"));
                GamePlayVariables.isSettingSpeed.add((Player)e.getWhoClicked());

                Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("rhythmcraft"), new Runnable() { // 10초간 기다린 뒤 실행

                    @Override
                    public void run() {

                        for (int i = 0; i < GamePlayVariables.isSettingSpeed.size(); i++) {
                            if (GamePlayVariables.isSettingSpeed.get(i).equals((Player)e.getWhoClicked())) {
                                GamePlayVariables.isSettingSpeed.remove((Player)e.getWhoClicked());
                                e.getWhoClicked().sendMessage(ChatUtils.chat("10초 동안 입력하지 않아서 취소되었습니다.", "#FF0000"));
                            }
                        }
                    }

                }, 200L /* 200 Ticks = 10 Sec */);
            }

            else if (e.getCurrentItem().getType().equals(Material.BLACKSTONE_SLAB)) {
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().openInventory(SetSongUI.StartUI((Player)e.getWhoClicked()));
            }

            else {
                return;
            }
        }
    }
}
