package com.github.neomaster.rhythmcraft.game.repeat;

import com.github.neomaster.rhythmcraft.game.utils.GameFunc;
import com.github.neomaster.rhythmcraft.game.utils.Variables;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AdvertiseHosting {

    public static void Advertise(Player dj) {

        new BukkitRunnable() {
            @Override
            public void run() {

                if (GameFunc.isStarted(dj)) {
                    this.cancel();
                }

                Bukkit.broadcastMessage(ChatUtils.regularCodeChat("&d" + dj.getName() + "님이 게임을 호스트하고 있습니다. " + Integer.toString(Variables.listOfPlayingGame.get(dj).size()) + "/5 &5\\o>"));
                Bukkit.broadcastMessage(ChatUtils.regularCodeChat("&f[&a/rc play " + dj.getName() + "&f] &5<o/"));
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 1L, 20 * 15L); // 15초마다 Advertise

    }
}
