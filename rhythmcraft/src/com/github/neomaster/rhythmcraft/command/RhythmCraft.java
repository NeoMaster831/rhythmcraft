package com.github.neomaster.rhythmcraft.command;

import com.github.neomaster.rhythmcraft.game.GameStart;
import com.github.neomaster.rhythmcraft.game.resourcepack.CreateResourcepack;
import com.github.neomaster.rhythmcraft.game.utils.Variables;
import com.github.neomaster.rhythmcraft.main.Functions;
import com.github.neomaster.rhythmcraft.main.Startup;
import com.github.neomaster.rhythmcraft.ui.GameStartUI;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class RhythmCraft implements CommandExecutor {

    Startup plugin;
    public RhythmCraft(Startup plugin) {
        this.plugin = plugin;
        plugin.getCommand("rc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("You can\'t do this on console!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(ChatUtils.chat("인수가 없거나 너무 짧습니다.", "#FF0000"));
            return true;
        }

        if (args[0].equalsIgnoreCase("getpack")) {
            CreateResourcepack.createResoucepack(p, Functions.getSongsFile());
            return true;
        }

        if (args[0].equalsIgnoreCase("host")) {

            if (new File("plugins/RhythmCraft/songs/").listFiles() == null) {
                p.sendMessage(ChatUtils.chat("무슨 DJ가 노래 없이 리듬을 탑니까?", "#7232FF"));
                return false;
            }
            p.openInventory(GameStartUI.StartUI(p));
            return true;
        }

        if (args[0].equalsIgnoreCase("play")) {

            String hoster = args[1];
            Player hosterP = Bukkit.getPlayer(hoster);

            for (Player key : Variables.listOfPlayingGame.keySet()) {
                if (key.equals(hosterP)) {
                    GameStart.RhythmGameJoin(hosterP, p);
                    return true;
                }
            }

            p.sendMessage(ChatUtils.regularCodeChat("&d그 분은 지금 호스팅을 하고 있지 않습니다."));
            return true;
        }

        if (args[0].equalsIgnoreCase("shop")) {
            p.sendMessage(ChatUtils.regularCodeChat("&d아직 구현이 안 된 명령어입니다."));
            p.sendMessage(ChatUtils.regularCodeChat("&d그리고 구현을 안 할 명령어입니다 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ"));
            return true;
        }

        else {
            p.sendMessage(ChatUtils.chat(args[0] + " <- 는 등록되지 않은 arg입니다.", "#FF0000"));
            return true;
        }
    }
}
