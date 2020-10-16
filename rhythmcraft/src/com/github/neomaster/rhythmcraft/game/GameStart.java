package com.github.neomaster.rhythmcraft.game;

import com.github.neomaster.rhythmcraft.game.repeat.AdvertiseHosting;
import com.github.neomaster.rhythmcraft.game.repeat.GameRepeat;
import com.github.neomaster.rhythmcraft.game.utils.GameFunc;
import com.github.neomaster.rhythmcraft.game.utils.GameLocations;
import com.github.neomaster.rhythmcraft.game.utils.Variables;
import com.github.neomaster.rhythmcraft.main.Functions;
import com.github.neomaster.rhythmcraft.main.GamePlayVariables;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public final class GameStart {

    public static void RhythmGameHost(Player hostingPlayer) {

        World getPlayingWorld = GamePlayVariables.PlayWorld.get(hostingPlayer);

        GameLocations gl = new GameLocations();
        GameLocations.intializeGameLocations(getPlayingWorld, gl, hostingPlayer);

        Variables.l.add(gl);

        GameRepeat.tpToLocation(hostingPlayer, gl.DJLocation, 30, 135);

        Variables.a.put(hostingPlayer, gl);

        ArrayList<Player> p = new ArrayList<Player>(); p.add(hostingPlayer);
        Variables.listOfPlayingGame.put(hostingPlayer, null);

        ArrayList<Player> djs = new ArrayList<>();
        djs.add(hostingPlayer); Variables.listOfPlayingGame.put(hostingPlayer, djs);

        if (Variables.isEnded.get(hostingPlayer) != null) {
            Variables.isEnded.remove(hostingPlayer);
        }
        Variables.isEnded.put(hostingPlayer, false);
        AdvertiseHosting.Advertise(hostingPlayer);
    }

    public static void RhythmGameJoin(Player hostingPlayer, Player joiningPlayer) {

        GameLocations gl = GameFunc.getLocationsOfHoster(hostingPlayer);

        Variables.isEnded.remove(joiningPlayer);
        Variables.isEnded.put(joiningPlayer, false);

        int size = Variables.listOfPlayingGame.get(hostingPlayer).size();
        if (size == 1) { // 리듬게임 호스터만 있을 때

            Functions.addPlayer(Variables.listOfPlayingGame, hostingPlayer, joiningPlayer);
            GameRepeat.tpToLocation(joiningPlayer, gl.player1, 44, -90);
            joiningPlayer.sendMessage(ChatUtils.regularCodeChat("&b당신은 1번째 플레이어입니다."));

        }
        else if (size == 2) { // player1 이 있을 때

            Functions.addPlayer(Variables.listOfPlayingGame, hostingPlayer, joiningPlayer);
            GameRepeat.tpToLocation(joiningPlayer, gl.player2, 44, 0);
            joiningPlayer.sendMessage(ChatUtils.regularCodeChat("&b당신은 2번째 플레이어입니다."));

        }
        else if (size == 3) { // player2 이 있을 때

            Functions.addPlayer(Variables.listOfPlayingGame, hostingPlayer, joiningPlayer);
            GameRepeat.tpToLocation(joiningPlayer, gl.player3, 44, 90);
            joiningPlayer.sendMessage(ChatUtils.regularCodeChat("&b당신은 3번째 플레이어입니다."));
        }
        else if (size == 4) { // player3 이 있을 떄

            Functions.addPlayer(Variables.listOfPlayingGame, hostingPlayer, joiningPlayer);
            GameRepeat.tpToLocation(joiningPlayer, gl.player4, 44, 180);
            joiningPlayer.sendMessage(ChatUtils.regularCodeChat("&b당신은 4번째 플레이어입니다."));

           new BukkitRunnable() { // 5

               @Override
               public void run() {

                   for (Player p : Variables.listOfPlayingGame.get(hostingPlayer)) {
                       p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 2, 1);
                   }

                   GameFunc.sendTitleToGamers(Variables.listOfPlayingGame.get(hostingPlayer), "&d5");
               }
           }.runTaskLater(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 20 * 2L);

           new BukkitRunnable() { // 4

                @Override
                public void run() {
                    for (Player p : Variables.listOfPlayingGame.get(hostingPlayer)) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 2, 1);
                    }
                    GameFunc.sendTitleToGamers(Variables.listOfPlayingGame.get(hostingPlayer), "&d4");
                }
           }.runTaskLater(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 20 * 3L);

           new BukkitRunnable() {

                @Override
                public void run() {
                    for (Player p : Variables.listOfPlayingGame.get(hostingPlayer)) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 2, 1);
                    }
                    GameFunc.sendTitleToGamers(Variables.listOfPlayingGame.get(hostingPlayer), "&d3");
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 20 * 4L);

           new BukkitRunnable() {

                @Override
                public void run() {
                    for (Player p : Variables.listOfPlayingGame.get(hostingPlayer)) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 2, 1);
                    }
                    GameFunc.sendTitleToGamers(Variables.listOfPlayingGame.get(hostingPlayer), "&d2");
                }
            }.runTaskLater(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 20 * 5L);

           new BukkitRunnable() {

                @Override
                public void run() {
                    for (Player p : Variables.listOfPlayingGame.get(hostingPlayer)) {
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 2, 1);
                    }
                    GameFunc.sendTitleToGamers(Variables.listOfPlayingGame.get(hostingPlayer), "&d1");
                }
           }.runTaskLater(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 20 * 6L);

           new BukkitRunnable() {

                @Override
                public void run() {

                    for (Player p : Variables.listOfPlayingGame.get(hostingPlayer)) {
                        p.playSound(p.getLocation(), "custommusics." + GamePlayVariables.PlaySong.get(hostingPlayer), (float)0.5, 1);
                        if (p == hostingPlayer) {
                            GameRepeat.slotHotbatSetDJ(p);
                        }
                        else GameRepeat.slotHotbarSet(p);
                    }
                    GameFunc.gameIntialize(hostingPlayer);
                }
           }.runTaskLater(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 20 * 7L);

           new BukkitRunnable() { // 게임 끝

               @Override
               public void run() {

                   ArrayList<Player> players = Variables.listOfPlayingGame.get(hostingPlayer);
                   for (Player p : players) {
                       for (int i = 0; i < Variables.q.size(); i++) {
                           for (Player d : Variables.q.get(i).keySet()) {
                               if (d.equals(p)) {
                                   GameFunc.sendMessageToGamers(players, "&d" + p + " &f| &ePERFECT &f= &b" + Integer.toString(Variables.q.get(i).get(p).PERFECT) + " &f| &aGREAT &f= &b" + Integer.toString(Variables.q.get(i).get(p).GREAT) + " &f| &2GOOD &f= &b" + Integer.toString(Variables.q.get(i).get(p).GOOD) + " &f| &7BAD &f= &b" + Integer.toString(Variables.q.get(i).get(p).BAD) + " &f| &8MISS &f= &b" + Integer.toString(Variables.q.get(i).get(p).MISS));
                                   Variables.q.get(i).remove(p);
                               }
                           }
                       }

                   }

                   Variables.isEnded.remove(hostingPlayer);
                   Variables.isEnded.put(hostingPlayer, true);
                   Variables.listOfPlayingGame.remove(hostingPlayer);
                   Variables.a.remove(hostingPlayer);

               }

           }.runTaskLater(Bukkit.getPluginManager().getPlugin("rhythmcraft"), 20 * (7 + Math.round(GamePlayVariables.SongLength.get(GamePlayVariables.PlaySong.get(hostingPlayer)))));
        }
        else { // 꽉 찼을 때

            joiningPlayer.sendMessage(ChatUtils.chat("방이 꽉 찼습니다. :(", "#FF0000"));
        }
    }
}
