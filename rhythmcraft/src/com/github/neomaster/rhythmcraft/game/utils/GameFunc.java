package com.github.neomaster.rhythmcraft.game.utils;

import com.github.neomaster.rhythmcraft.main.GamePlayVariables;
import com.github.neomaster.rhythmcraft.utils.ChatUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public final class GameFunc {

    public static GameLocations getLocationsOfHoster(Player hoster) {

        for (int i = 0; i < Variables.l.size(); i++) {

            if (Variables.l.get(i).DJ.equals(hoster)) {
                return Variables.l.get(i);
            }
        }

        return null;
    }

    public static boolean isStarted(Player hoster) {

        if (Variables.listOfPlayingGame.get(hoster).size() == 5) {
            return true;
        }
        else return false;
    }
    
    @SuppressWarnings("unused")
    public static void gameIntialize(Player hoster) {

        GameLocations locs = getLocationsOfHoster(hoster);
        GameLocations.intializeGameLocations(GamePlayVariables.PlayWorld.get(hoster), locs, hoster);
        
        for (int i = 0; i < 64; i++) {

            HashMap<Player, Variables.Scores> a = new HashMap<>();
            ArrayList<Player> b = Variables.listOfPlayingGame.get(a);

            for (Player p : b) {
                Variables.Scores c = null;
                c.BAD = 0; c.GOOD = 0; c.MISS = 0; c.GREAT = 0; c.PERFECT = 0;

                a.put(p, c);
            }
            Variables.q.add(a);
        }
    }

    public static void sendTitleToGamers(ArrayList<Player> gamers, String s) {

        for (Player p : gamers) {
            p.sendTitle(ChatUtils.regularCodeChat(s), " ", 0, 30, 0);
        }

    }

    public static void putToQ(HashMap<Player, Variables.Scores> dHash, Player p) {

        for (int i = 0; i < Variables.q.size(); i++) { // HashMap 탐색
            for (Player pe : Variables.q.get(i).keySet()) { //
                if (pe.equals(p)) {

                    Variables.q.add(dHash);
                    return;
                }
            }
        }
    }

    public static void sendMessageToGamers(ArrayList<Player> gamers, String s) {

        for (Player p : gamers) {
            p.sendMessage(ChatUtils.regularCodeChat(s));
        }
    }
}
