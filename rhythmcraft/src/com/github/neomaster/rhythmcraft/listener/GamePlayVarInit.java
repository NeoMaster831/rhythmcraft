package com.github.neomaster.rhythmcraft.listener;

import com.github.neomaster.rhythmcraft.game.utils.Variables;
import com.github.neomaster.rhythmcraft.main.GamePlayVariables;
import com.github.neomaster.rhythmcraft.main.Startup;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class GamePlayVarInit implements Listener {

    Startup plugin;
    public GamePlayVarInit(Startup plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (GamePlayVariables.PlaySpeed.get(e.getPlayer()) == null) {

            GamePlayVariables.PlaySpeed.put(e.getPlayer(), 0.33);
            GamePlayVariables.PlayWorld.put(e.getPlayer(), Bukkit.getWorld("world"));
            Variables.isEnded.put(e.getPlayer(), true);
            if (new File("plugins/RhythmCraft/songs").listFiles().length == 0) {
                return;
            }
            GamePlayVariables.PlaySong.put(e.getPlayer(), new File("plugins/RhythmCraft/songs").listFiles()[0].getName().split(".ogg")[0]);
        }
        else return;
    }
}
