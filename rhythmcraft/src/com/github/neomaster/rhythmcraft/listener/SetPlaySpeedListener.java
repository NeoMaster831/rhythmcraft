package com.github.neomaster.rhythmcraft.listener;

import com.github.neomaster.rhythmcraft.main.GamePlayVariables;
import com.github.neomaster.rhythmcraft.main.Startup;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class SetPlaySpeedListener implements Listener {

    Startup plugin;
    public SetPlaySpeedListener(Startup plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {

        Player p = e.getPlayer();
        for (int i = 0; i < GamePlayVariables.isSettingSpeed.size(); i++) {

            if (GamePlayVariables.isSettingSpeed.get(i).equals(p)) {

                if (e.getMessage() == "back") {
                    GamePlayVariables.isSettingSpeed.get(i).remove();
                    p.sendMessage(ChatUtils.regularCodeChat("&c취소하였습니다."));
                    e.setCancelled(true);
                    return;
                }

                GamePlayVariables.PlaySpeed.remove(p);
                GamePlayVariables.PlaySpeed.put(p, Double.parseDouble(e.getMessage()));

                GamePlayVariables.isSettingSpeed.get(i).remove();
                p.sendMessage(ChatUtils.chat("설정이 완료 되었습니다. [" + e.getMessage() + "]", "#00FF00"));

            }

        }
        return;
    }
}
